using BethanysPieShop.Models;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllersWithViews();

//builder.Services.AddScoped<ICategoryRepository, MockCategoryRepository>();
//builder.Services.AddScoped<IPieRepository, MockPieRepository>();

builder.Services.AddScoped<ICategoryRepository, CategoryDbRepository>();
builder.Services.AddScoped<IPieRepository, PieDbRepository>();

builder.Services.AddScoped<IShoppingCart, ShoppingCart>(provider =>
{
    IHttpContextAccessor httpContextAccessor = provider.GetRequiredService<IHttpContextAccessor>();
    ISession? session = httpContextAccessor.HttpContext?.Session;
    BethanysPieShopDbContext dbContext = provider.GetRequiredService<BethanysPieShopDbContext>();

    return ShoppingCart.GetCart(session, dbContext);
});
builder.Services.AddSession(); 
builder.Services.AddHttpContextAccessor();

builder.Services.AddDbContext<BethanysPieShopDbContext>(options => {
    options.UseSqlServer(
        builder.Configuration["ConnectionStrings:BethanysPieShopDbContextConnection"]);
});

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.UseDeveloperExceptionPage();
}

app.UseStaticFiles();
app.UseSession();

//app.MapDefaultControllerRoute();
app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

DbInitializer.Seed(app);
app.Run();
