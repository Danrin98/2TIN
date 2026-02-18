using CaffeineTracker9000.AppLogic;
using CaffeineTracker9000.Domain;

namespace CaffeineTracker9000.Infrastructure;

public class ConsumptionRepository : IConsumptionRepository
{
    private readonly CaffeineTrackerDbContext _context;

    public ConsumptionRepository(CaffeineTrackerDbContext context)
    {
        _context = context;
    }

    public void Add(Consumption consumption)
    {
        _context.Consumptions.Add(consumption);
        _context.SaveChanges();
    }

    public int GetConsumptionOfToday()
    {
        // USE the QUERY syntax
        // Calculate the total intake of Caffeine TODAY
        // TIP: You can use 'DateTime.Today' to get the current date
        int total = 0;
        DateTime today = DateTime.Today;
        var consumptions = _context.Consumptions;
        var drinks =
            from drink in _context.Drinks
            join consumption in consumptions on drink.Id equals consumption.DrinkId
            where drink.Consumptions.Count() > 0 && consumption.Time.Equals(today)
            select drink;
        foreach (var drink in drinks) 
        {
            total += drink.CaffeineMgPerServing;
        }
        return total;
        //return 1230;
    }
}

