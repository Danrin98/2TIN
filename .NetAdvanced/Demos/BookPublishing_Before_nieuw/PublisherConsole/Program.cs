
using Microsoft.EntityFrameworkCore;
using PublisherData;
using PublisherDomain;

Console.WriteLine("Hello, World!");

using PubContext _context = new PubContext();

void UpdateAuthor()
{
    Author? authorToChange = _context.Authors.Where(a => a.LastName == "Lerman" &&  a.FirstName == "Julie").FirstOrDefault();
    if (authorToChange is not null)
    {
        authorToChange.FirstName = "Julia";
        _context.SaveChanges();
    }
}

void SearchAuthor()
{
    Author? author = _context.Authors.Where(a => a.LastName == "Lerman" && a.FirstName == "Julia").Include(a => a.Books).FirstOrDefault();
}
void AddAuthor()
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

    _context.Authors.Add(author);
    _context.SaveChanges();
}