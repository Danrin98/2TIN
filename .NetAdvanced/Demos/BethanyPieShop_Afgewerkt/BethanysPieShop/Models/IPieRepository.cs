namespace BethanysPieShop.Models
{
    public interface IPieRepository
    {
        IEnumerable<Pie> AllPies { get; }
        Pie? GetPieById(int pieId);
        IEnumerable<Pie> GetPiesOfTheWeek();
        void AddPie(Pie pie);
        void DeletePie(int pieId);
        IEnumerable<Pie> SearchPies(string searchQuery);
    }
}
