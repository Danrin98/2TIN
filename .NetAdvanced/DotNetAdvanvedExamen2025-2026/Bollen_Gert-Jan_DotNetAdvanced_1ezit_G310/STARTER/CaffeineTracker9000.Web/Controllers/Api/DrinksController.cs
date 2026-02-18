using CaffeineTracker9000.AppLogic;
using CaffeineTracker9000.Domain;
using CaffeineTracker9000.Web.Models.Dto;
using Microsoft.AspNetCore.Mvc;

namespace CaffeineTracker9000.Web.Controllers.Api;

[ApiController]
[Route("api/[controller]")]
public class DrinksController: ControllerBase
{
    private readonly IDrinkRepository _drinkRepository;

    public DrinksController(IDrinkRepository drinkRepository)
    {
        _drinkRepository = drinkRepository;
    }
    /*
        POST
     Add a new drink to the system using a repository that implements IDrinkRepository.
     Accepts a DrinkCreateDto object in the request body and returns the created Drink object.
     Use attribute-based routing. This action should be accessible via a POST request to /api/drinks.
     */
    [HttpPost]
    public ActionResult<Drink> PostDrink([FromBody] DrinkCreateDto dto)
    {
        Drink drink = new Drink();

        drink.Description = dto.Description;
        drink.Name = dto.Name;
        drink.CaffeineMgPerServing = dto.CaffeineMgPerServing;
        
        _drinkRepository.Add(drink);
        return Ok(drink);
    }

    /*
        GET
     Retrieve a drink by its unique identifier using a repository that implements IDrinkRepository.
     Accepts the drink Id as a route parameter and returns the corresponding Drink object.
     Use attribute-based routing. This action should be accessible via a GET request to /api/drinks/id (id must be a number).
     */
    [HttpGet("{id}")]
    public ActionResult<Drink> GetById(int id)
    {
        Drink? drink = _drinkRepository.GetById(id);
        return Ok(drink);
    }
}

