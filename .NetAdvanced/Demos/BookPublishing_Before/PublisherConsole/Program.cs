using Microsoft.EntityFrameworkCore;
using Microsoft.Identity.Client;
using PublisherData;
using PublisherDomain;

Console.WriteLine("Hello, World!");

//using (PubContext context = new PubContext())
//{
//   context.Database.EnsureCreated();
//}

//addAuthor();

GetAuthor();

void GetAuthor()
{
    using PubContext context = new PubContext();

    var author = context.Author
            .Where(a => a.FirstName.ToLower().StartsWith("jul"))
            .Include(a => a.Books)
            .First();
};


void addAuthor()
{
    var author = new Author { FirstName = "Julie", LastName = "Lerman" };
    author.Books.Add(new Book
    {
        Title = "Programming Entity Framework",
        PublishDate = new DateOnly(2009, 1, 1)
    });
    author.Books.Add(new Book
    {
        Title = "Programming Entity Framework 2nd Ed",
        PublishDate = new DateOnly(2010, 8, 1)
    });

    using PubContext context = new PubContext();
    context.Author.Add(author);

    context.SaveChanges();
}

