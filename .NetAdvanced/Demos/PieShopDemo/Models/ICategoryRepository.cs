namespace PieShopDemo.Models
{
    public interface ICategoryRepository
    {
        public IEnumerable<Category> AllCategories { get; }
    }
}
