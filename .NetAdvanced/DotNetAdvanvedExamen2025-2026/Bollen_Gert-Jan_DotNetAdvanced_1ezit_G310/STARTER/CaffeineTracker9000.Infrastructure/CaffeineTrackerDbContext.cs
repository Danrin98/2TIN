using CaffeineTracker9000.Domain;
using Microsoft.EntityFrameworkCore;

namespace CaffeineTracker9000.Infrastructure;

public class CaffeineTrackerDbContext : DbContext
{
    // do not change this constructor
    public CaffeineTrackerDbContext(DbContextOptions options) : base(options) 
    {
    }
    // Add the necessary sets to this class
    public DbSet<Drink> Drinks { get; set; }
    public DbSet<Consumption> Consumptions { get; set; }
}

