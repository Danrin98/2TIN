using CaffeineTracker9000.AppLogic;
using CaffeineTracker9000.Domain;
using CaffeineTracker9000.Web.Models.ViewModels;
using Microsoft.AspNetCore.Mvc;

namespace CaffeineTracker9000.Controllers;

public class DrinksController : Controller
{
    private readonly IDrinkRepository _drinkRepository;
    private readonly ICaffeineTrackerService _caffeineTrackerService;

    public DrinksController(IDrinkRepository drinkRepository, ICaffeineTrackerService metrics)
    {
        _drinkRepository = drinkRepository;
        _caffeineTrackerService = metrics;
    }

    public IActionResult Index()
    {
        var drinks = _drinkRepository.GetAll();

        var listOfSummaries = drinks.Select(ToSummary).OrderByDescending(s => s.ConsumptionCount).ToList();

        return View(listOfSummaries);
    }


    // Private helper to map a Drink to a DrinkSummaryViewModel
    private DrinkSummaryViewModel ToSummary(Drink drink)
    {
        var total = _caffeineTrackerService.TotalForDrink(drink);
        return new DrinkSummaryViewModel(
            drink.Id,
            drink.Name,
            drink.Description,
            drink.CaffeineMgPerServing,
            drink.Consumptions?.Count ?? 0,
            total,
            _caffeineTrackerService.SingleDoseToDangerLevel(drink.CaffeineMgPerServing));
    }
}

