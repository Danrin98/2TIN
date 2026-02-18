using System.Text.Json.Serialization;
using CaffeineTracker9000.AppLogic;
using CaffeineTracker9000.Infrastructure;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllersWithViews().AddJsonOptions(options =>
{
    options.JsonSerializerOptions.ReferenceHandler = ReferenceHandler.IgnoreCycles;
});

builder.Services.AddScoped<IDrinkRepository, DrinkRepository>();
builder.Services.AddScoped<IConsumptionRepository, ConsumptionRepository>();
builder.Services.AddSingleton<ICaffeineTrackerService, CaffeineTrackerService>();

builder.Services.AddDbContext<CaffeineTrackerDbContext>(options => {
    options.UseSqlServer(
        builder.Configuration["ConnectionStrings:DefaultConnection"]);
});

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

////Seed the database with initial data:
using (var scope = app.Services.CreateScope())
{
    var context = scope.ServiceProvider.GetRequiredService<CaffeineTrackerDbContext>();
    SeedData.EnsureSeedData(context);
}

// Configure the HTTP request pipeline.
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();
app.UseStaticFiles();
app.UseRouting();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Home}/{action=Index}/{id?}");

app.Run();
