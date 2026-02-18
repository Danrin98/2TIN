using AlienAdoptionAgency.AppLogic;
using AlienAdoptionAgency.Domain;
using AlienAdoptionAgency.Web.Models;
using Microsoft.AspNetCore.Mvc;

namespace AlienAdoptionAgency.Web.Controllers
{
    public class AliensController : Controller
    {
        private readonly IAlienRepository _alienRepository;

		public AliensController(IAlienRepository alienRepository)
		{
			_alienRepository = alienRepository;
		}

		public IActionResult Overview(int page = 0, int pageSize = 5)
        {
            // Tip: Use an OverviewViewModel (see Models folder)
            OverviewViewModel model = new OverviewViewModel
            {
                PageSize = pageSize,
                CurrentPage = page,
                TotalCount = _alienRepository.GetTotalAlienCount(),
                Aliens = _alienRepository.GetAliensPaged(page, pageSize)
            };
			return View(model);
        }

        public IActionResult Adoptables()
        {
            IEnumerable<Alien> aliens = _alienRepository.GetAllAliens(true);
            return View(aliens);
        }


        public IActionResult Details(int id)
        {
            Alien? alien = _alienRepository.GetAlienById(id);
            if (alien == null)
            {
                return NotFound();
            }
            return View(alien);
        }
    }
}
