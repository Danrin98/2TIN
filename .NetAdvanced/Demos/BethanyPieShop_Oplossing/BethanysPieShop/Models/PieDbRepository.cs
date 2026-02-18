using Microsoft.EntityFrameworkCore;

namespace BethanysPieShop.Models
{
    public class PieDbRepository : IPieRepository
    {
        private readonly BethanysPieShopDbContext _context;

        public PieDbRepository(BethanysPieShopDbContext context)
        {
            _context = context;
        }

        public IEnumerable<Pie> AllPies
        {
            get
            {
                return _context.Pies.Include(c => c.Category);
            }
        }

        public Pie? GetPieById(int pieId)
        {
            return _context.Pies.FirstOrDefault(p => p.PieId == pieId);
        }

        public IEnumerable<Pie> GetPiesOfTheWeek()
        {
            return _context.Pies.Include(c => c.Category).Where(p => p.IsPieOfTheWeek);
        }

        public IEnumerable<Pie> SearchPies(string searchQuery)
        {
            throw new NotImplementedException();
        }
    }
}
