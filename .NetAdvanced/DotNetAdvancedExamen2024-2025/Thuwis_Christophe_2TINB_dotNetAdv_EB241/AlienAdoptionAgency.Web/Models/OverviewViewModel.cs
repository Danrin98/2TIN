using AlienAdoptionAgency.Domain;

namespace AlienAdoptionAgency.Web.Models
{
    public class OverviewViewModel
    {
        public IEnumerable<Alien> Aliens { get; set; } = Enumerable.Empty<Alien>();

        // For pagination: 
        public int TotalCount { get; set; }
        public int PageSize { get; set; }
        public int CurrentPage { get; set; }
        public int TotalPages => (int)Math.Ceiling((double)TotalCount / PageSize);
    }
}
