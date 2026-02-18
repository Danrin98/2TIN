using CaffeineTracker9000.AppLogic;

namespace CaffeineTracker9000.Web.Models.ViewModels;

public class DrinkSummaryViewModel
{
    public int Id { get; set; }
    public string Name { get; set; } = string.Empty;
    public string? Description { get; set; }
    public int CaffeineMgPerServing { get; set; }
    public int ConsumptionCount { get; set; }
    public int TotalCaffeineMg { get; set; }
    public DangerLevel Level { get; set; }

    public DrinkSummaryViewModel() { }

    public DrinkSummaryViewModel(int id, string name, string? description, int caffeineMgPerServing, int consumptionCount, int totalCaffeineMg, DangerLevel badge)
    {
        Id = id;
        Name = name;
        Description = description;
        CaffeineMgPerServing = caffeineMgPerServing;
        ConsumptionCount = consumptionCount;
        TotalCaffeineMg = totalCaffeineMg;
        Level = badge;
    }
}

