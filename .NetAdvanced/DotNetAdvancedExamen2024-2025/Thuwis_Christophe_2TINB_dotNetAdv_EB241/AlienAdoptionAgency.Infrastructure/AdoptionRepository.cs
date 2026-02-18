using AlienAdoptionAgency.AppLogic;
using AlienAdoptionAgency.Domain;
using Microsoft.EntityFrameworkCore;

namespace AlienAdoptionAgency.Infrastructure
{
    public class AdoptionRepository : IAdoptionRepository
    {
        private readonly AgencyDbContext _context;

		public AdoptionRepository(AgencyDbContext context)
		{
			_context = context;
		}

		public void AddAdoptionRequest(AdoptionRequest request)
        {
            // Save the request in the database
            _context.AdoptionRequests.Add(request);
            _context.SaveChanges();
        }

        public IList<AdoptionRequest> GetAllAdoptionRequests()
        {
            // Return a list of AdoptionRequests
            // Sort this list on RequestDate, the NEWEST should be first! 
            // Make sure the returned requests have a FILLED IN Alien property! 
            return _context.AdoptionRequests.Include(a => a.Alien).OrderByDescending(a => a.RequestDate).ToList();
        }
    }
}
