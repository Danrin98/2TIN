using AlienAdoptionAgency.AppLogic;
using AlienAdoptionAgency.Domain;
using Microsoft.EntityFrameworkCore;

namespace AlienAdoptionAgency.Infrastructure
{
    public class AlienRepository : IAlienRepository
    {

        private readonly AgencyDbContext _context;

		public AlienRepository(AgencyDbContext context)
		{
			_context = context;
		}

		public IList<Alien> GetAllAliens(bool onlyAdoptables = false)
        {
            // Make a query that gets all Aliens
            // IMPORTANT: use the QUERY syntax to achieve this

            // The list is sorted by the name of the Alien

            // If onlyAdoptables is true: filter out aliens that 
            // are not adoptable
            // Return a sorted list of aliens
            var aliens =
                from alien in _context.Aliens
                where !onlyAdoptables || alien.IsAdoptable
                orderby alien.Name descending
                select alien;

            return aliens.ToList();
        }

        public Alien? GetAlienById(int id)
        {
            // Return the alien with the given Id
            // Return null if this alien does not exist.
            Alien? alien = _context.Aliens.Find(id);
            return alien;
        }

        public void ToggleAlienAdoptionStatus(int id)
        {
            // Change the IsAdoptable status of an alien
            // Get the alien by id, if the alien is not found: do nothing

            // Change the status from true to false or from false to true
            // Save the new status to the database
            Alien? alien = _context.Aliens.Find(id);
			if (alien == null)
            {
                throw new ArgumentException("Alien not found");
            }
            if (alien.IsAdoptable == true)
            {
                alien.IsAdoptable = false;
            }
            else
            {
                alien.IsAdoptable = true;
            }
            _context.SaveChanges();
        }

        public void AddAlien(Alien alien)
        {
            // Add the alien to the database
            _context.Aliens.Add(alien);
            _context.SaveChanges();
        }

        public int GetTotalAlienCount()
        {
            // Return the total amount of aliens
            return _context.Aliens.Count();
        }

        public IList<Alien> GetAliensPaged(int pagenumber = 0, int pageSize = 5)
        {
            // Return only the aliens associated with the pagenumber 
            // Sorted by name
            return _context.Aliens.OrderByDescending(a => a.Name).Skip(pagenumber * pageSize).Take(pageSize).ToList();
        }
    }
}
