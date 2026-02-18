
using CaffeineTracker9000.AppLogic;
using CaffeineTracker9000.Domain;
using CaffeineTracker9000.Infrastructure;
using CaffeineTracker9000.Web.Models.ViewModels;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
namespace CaffeineTracker9000.Controllers;

public class ConsumptionController : Controller
{
    private readonly IDrinkRepository _drinkRepository;

    public ConsumptionController(IConsumptionRepository consumptionRepository, IDrinkRepository drinkRepository)
    {
        _drinkRepository = drinkRepository;
    }

    /*
        Implement the Create action methods (GET and POST) to add a new Consumption. Use the NewConsumptionViewModel class as the view model.
        After successfully adding a new Consumption, redirect to the home page.
        Attention: The list of drinks will not be submitted with the form, so you need to refill it in the POST action if the model state is invalid.
     */

    // NIET AF!
    [HttpGet]
    public IActionResult Create()
    {
        return View();
    }

    [HttpPost]
    public IActionResult Create(int? drinkId)
    {
        if (drinkId == null)
        {
            drinkId = 0;
        }
        Consumption consumption = new Consumption { Time = DateTime.UtcNow, DrinkId = drinkId.Value };

        NewConsumptionViewModel viewModel = new NewConsumptionViewModel(consumption);
        viewModel.FillDrinkSelectList(_drinkRepository, drinkId);
        if (!ModelState.IsValid)
        {
            return View(viewModel);
        }
        return RedirectToAction("Index", "Home");
    }
}

