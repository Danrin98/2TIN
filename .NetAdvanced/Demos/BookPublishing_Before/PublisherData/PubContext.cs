using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using PublisherDomain;

namespace PublisherData
{
    public class PubContext :DbContext
    {
        public DbSet<Book> Books { get; set; }
        public DbSet<Author> Author { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            optionsBuilder.UseSqlServer(
                "Server=(localdb)\\MSSQLLocalDB; Initial Catalog = PubDatabase;Trusted_Connection=True"
                );
        }

    }
}
