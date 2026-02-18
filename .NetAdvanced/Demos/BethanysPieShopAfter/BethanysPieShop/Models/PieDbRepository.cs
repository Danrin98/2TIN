
namespace BethanysPieShop.Models
{
    public class PieDbRepository : IPieRepository
    {
        private readonly PieShopDbContext _context;

        public PieDbRepository(PieShopDbContext context)
        {
            _context = context;
        }

        public IEnumerable<Pie> AllPies => _context.Pies.ToList();

        public IEnumerable<Pie> PiesOfTheWeek => _context.Pies.Where(p => p.IsPieOfTheWeek).ToList();

        public void AddPie(Pie pie)
        {
            _context.Pies.Add(pie);
            _context.SaveChanges();
        }

        public Pie? GetPieById(int pieId)
        {
            Pie? pie = _context.Pies.Find(pieId);
            return pie;
        }

        public IEnumerable<Pie> SearchPies(string searchQuery)
        {
            throw new NotImplementedException();
        }
    }
}
