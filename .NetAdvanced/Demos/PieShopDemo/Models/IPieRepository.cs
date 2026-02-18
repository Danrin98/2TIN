namespace PieShopDemo.Models
{
    public interface IPieRepository
    {
        public IEnumerable<Pie> AllPies { get; }
        public Pie GetPieById(int pieId);
    }
}