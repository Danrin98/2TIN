using CaffeineTracker9000.AppLogic;
using CaffeineTracker9000.Web.Models.ViewModels;
using Microsoft.AspNetCore.Mvc;

namespace CaffeineTracker9000.Web.Components
{
    public class TodaysDrinksViewComponent: ViewComponent
    {
        private readonly IConsumptionRepository _consumptionRepository;
        private readonly ICaffeineTrackerService _caffeineTrackerService;

        public TodaysDrinksViewComponent(IConsumptionRepository consumptionRepository, ICaffeineTrackerService caffeineTrackerService)
        {
            _consumptionRepository = consumptionRepository;
            _caffeineTrackerService = caffeineTrackerService;
        }

        public IViewComponentResult Invoke()
        {
            var consumptionToday = _consumptionRepository.GetConsumptionOfToday();
            CaffeineWarningViewModel? vm = null;
            if (consumptionToday > 0)
            {
                vm = new CaffeineWarningViewModel(consumptionToday, _caffeineTrackerService.DailyDoseToDangerLevel(consumptionToday));
            }
            return View(vm);
        }
    }
}
