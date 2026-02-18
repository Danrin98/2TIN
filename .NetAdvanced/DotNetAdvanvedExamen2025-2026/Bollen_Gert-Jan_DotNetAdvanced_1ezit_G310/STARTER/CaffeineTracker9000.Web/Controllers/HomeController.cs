using CaffeineTracker9000.AppLogic;
using CaffeineTracker9000.Infrastructure;
using CaffeineTracker9000.Web.Models.ViewModels;
using Microsoft.AspNetCore.Mvc;

namespace CaffeineTracker9000.Controllers;

public class HomeController : Controller
{
    private readonly IConsumptionRepository _consumptionRepository;
    private readonly ICaffeineTrackerService _caffeineTrackerService;
    public HomeController(IConsumptionRepository repo, ICaffeineTrackerService caffeineTrackerService)
    {
        _consumptionRepository = repo;
        _caffeineTrackerService = caffeineTrackerService;
    }

    public IActionResult Index()
    {
        //ConsumptionRepository repo = new ConsumptionRepository();
        //CaffeineTrackerService service = new CaffeineTrackerService();

        //var consumptionToday = _consumptionRepository.GetConsumptionOfToday();
        //CaffeineWarningViewModel? vm = null;
        //if (consumptionToday > 0)
        //{
        //    vm = new CaffeineWarningViewModel(consumptionToday, _caffeineTrackerService.DailyDoseToDangerLevel(consumptionToday));
        //}
        return View();
    }

    public IActionResult Privacy()
    {
        return View();
    }
}
