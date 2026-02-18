namespace PublisherDomain
{
    public class Author
    {
        public int Id { get; set; }
        public string FirstName { get; set; } = string.Empty;
        public string LastName { get; set; } = string.Empty;
        public int BirthYear { get; set; } = 1970;
        public List<Book> Books { get; set; } = new List<Book>();
    }
}
