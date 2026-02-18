using AlienAdoptionAgency.AppLogic;
using AlienAdoptionAgency.Infrastructure;
using Microsoft.EntityFrameworkCore;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container (like services needed for MVC, EF) 
builder.Services.AddDbContext<AgencyDbContext>(options =>
{
	options.UseSqlServer(builder.Configuration.GetConnectionString("DefaultConnection"));
});
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
builder.Services.AddControllersWithViews();

builder.Services.AddScoped<IAdoptionRepository, AdoptionRepository>();
builder.Services.AddScoped<IAlienRepository, AlienRepository>();

var app = builder.Build();

app.UseSwagger();
app.UseSwaggerUI();
app.UseHttpsRedirection();
app.UseStaticFiles();

app.MapControllerRoute(
    name: "default",
    pattern: "{controller=Welcome}/{action=Index}/{id?}");

app.Run();
