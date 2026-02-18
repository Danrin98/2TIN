using CaffeineTracker9000.AppLogic;
using CaffeineTracker9000.Domain;
using Microsoft.EntityFrameworkCore;

namespace CaffeineTracker9000.Infrastructure;

public class DrinkRepository : IDrinkRepository
{
    private readonly CaffeineTrackerDbContext _context;

    public DrinkRepository(CaffeineTrackerDbContext context)
    {
        _context = context;
    }

    public List<Drink> GetAll()
    {   // get all drinks WITH consumptions
        return _context.Drinks.Where(d => d.Consumptions.Any()).ToList();
    }

    public Drink? GetById(int id)
    {
        // get a drink by id WITH consumptions
        return _context.Drinks.Where(d => d.Consumptions.Any()).First(d => d.Id == id);
    }

    public void Add(Drink drink)
    {
        _context.Drinks.Add(drink);
        _context.SaveChanges();
    }
}

