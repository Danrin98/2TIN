using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using PublisherDomain;

namespace PublisherData
{
    public class PubContext : DbContext
    {
        public DbSet<Author> Authors { get; set; }
        public DbSet<Book> Books { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            string connString = "Server=(localdb)\\MSSQLLocalDB;Initial Catalog=PubDatabase;Trusted_Connection=True";

            optionsBuilder.UseSqlServer(connString)
                .LogTo(Console.WriteLine,
                new[] { DbLoggerCategory.Database.Command.Name },
                LogLevel.Information)
                .EnableSensitiveDataLogging();
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            Author auth = new Author { Id = 1, FirstName = "Rhoda", LastName = "Lerman" };
            modelBuilder.Entity<Author>().HasData(auth);

            var authorList = new Author[]{
                new Author {Id = 2, FirstName = "Ruth", LastName = "Ozeki" },
                new Author {Id = 3, FirstName = "Sofia", LastName = "Segovia" },
                new Author {Id = 4, FirstName = "Ursula K.", LastName = "LeGuin" },
                new Author {Id = 5, FirstName = "Hugh", LastName = "Howey" },
                new Author {Id = 6, FirstName = "Isabelle", LastName = "Allende" }
            };
            modelBuilder.Entity<Author>().HasData(authorList);
        }
    }
}
