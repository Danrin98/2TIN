using CaffeineTracker9000.Domain;

namespace CaffeineTracker9000.AppLogic;

public class CaffeineTrackerService : ICaffeineTrackerService
{
    public int TotalForDrink(Drink drink)
    {
        // Check if there are consumptions and if so:
        // multiply the number of consumptions with the
        // caffeine dose in mg

        // OPGELET: het is in deze klasse niet nodig om met LINQ te werken (ondanks wat de opgave suggereert)
        if (drink.Consumptions.Any())
        {
            int amount = drink.Consumptions.Count();
            int total = drink.CaffeineMgPerServing * amount;
            return total;
        }
        return 0;

    }

    public DangerLevel SingleDoseToDangerLevel(int caffeineMg)
    {
        if (caffeineMg >= 300) return DangerLevel.TooMuch;
        if (caffeineMg >= 200) return DangerLevel.YellowAlert;
        else return DangerLevel.Manageable;
    }

    public DangerLevel DailyDoseToDangerLevel(int caffeineMg)
    {
        if (caffeineMg >= 8000) return DangerLevel.TooMuch;
        if (caffeineMg >= 2000) return DangerLevel.YellowAlert;
        else return DangerLevel.Manageable;
    }
}


