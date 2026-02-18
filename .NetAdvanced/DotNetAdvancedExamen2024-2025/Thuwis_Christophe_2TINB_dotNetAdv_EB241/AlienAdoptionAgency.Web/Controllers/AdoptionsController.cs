using AlienAdoptionAgency.AppLogic;
using AlienAdoptionAgency.Domain;
using AlienAdoptionAgency.Infrastructure;
using Microsoft.AspNetCore.Mvc;

namespace AlienAdoptionAgency.Web.Controllers
{
    public class AdoptionsController : Controller
    {
        private readonly IAdoptionRepository _adoptionRepository;

		public AdoptionsController(IAdoptionRepository adoptionRepository)
		{
			_adoptionRepository = adoptionRepository;
		}
		public IActionResult Adopted()
        {
			IEnumerable<AdoptionRequest> requests = _adoptionRepository.GetAllAdoptionRequests();
			return View(requests);
        }
    }
}
