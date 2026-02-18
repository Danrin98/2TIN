using BethanysPieShop.Models;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllersWithViews();

builder.Services.AddScoped<ICategoryRepository, MockCategoryRepository>();
builder.Services.AddScoped<IPieRepository, PieDbRepository>();
builder.Services.AddScoped<DbInitializer>();

builder.Services.AddDbContext<PieShopDbContext>(
    options =>
    {
        string connectionString = builder.Configuration["ConnectionStrings:DevConString"];
        options.UseSqlServer(connectionString);
    });

builder.Services.AddScoped<IShoppingCart, ShoppingCart>(provider =>
{
    IHttpContextAccessor httpContextAccessor = provider.GetRequiredService<IHttpContextAccessor>();
    ISession? session = httpContextAccessor.HttpContext?.Session;
    PieShopDbContext dbContext = provider.GetRequiredService<PieShopDbContext>();

    return ShoppingCart.GetCart(session, dbContext);
});
builder.Services.AddSession();
builder.Services.AddHttpContextAccessor();

var app = builder.Build();

//app.MapGet("/", () => "Hello World!");
if (app.Environment.IsDevelopment())
{
    app.UseDeveloperExceptionPage();
}

app.UseSession();

app.UseStaticFiles();

app.MapControllerRoute(name: "route1",
    pattern: "taartjes",
    defaults: new {controller = "Pie", action = "List" });

app.MapDefaultControllerRoute();

// Create a Dependency Injection scope to run the DbInitializer
IServiceScope scope = app.Services.CreateScope();
DbInitializer initializer = scope.ServiceProvider.GetRequiredService<DbInitializer>();
initializer.Seed();

app.Run();
