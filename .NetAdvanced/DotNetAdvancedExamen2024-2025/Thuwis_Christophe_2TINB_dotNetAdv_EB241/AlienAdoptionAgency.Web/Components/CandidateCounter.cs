using AlienAdoptionAgency.AppLogic;
using AlienAdoptionAgency.Web.Models;
using Microsoft.AspNetCore.Mvc;

namespace AlienAdoptionAgency.Web.Components
{
    public class CandidateCounter : ViewComponent
    {
		private readonly IAlienRepository _alienRepository;

		public CandidateCounter(IAlienRepository alienRepository)
		{
			_alienRepository = alienRepository;
		}

		public IViewComponentResult Invoke()
        {
            var vm = new CandidateCounterViewModel();
            // TODO: set the Count property to the total of adoptable aliens
            vm.Count = _alienRepository.GetAllAliens(true).Count;
            return View(vm);
        }
    }
}
