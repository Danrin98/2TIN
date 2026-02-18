using System.Security.Principal;
using Microsoft.EntityFrameworkCore;

namespace BethanysPieShop.Models
{
    public class PieShopDbContext : DbContext
    {
        public DbSet<Pie> Pies { get; set; }
        public DbSet<Category> Categories { get; set; }
        public DbSet<ShoppingCartItem> ShoppingCartItems { get; set; } = null!;

        public PieShopDbContext(DbContextOptions<PieShopDbContext> options)
                : base(options)
        {

        }
    }
}