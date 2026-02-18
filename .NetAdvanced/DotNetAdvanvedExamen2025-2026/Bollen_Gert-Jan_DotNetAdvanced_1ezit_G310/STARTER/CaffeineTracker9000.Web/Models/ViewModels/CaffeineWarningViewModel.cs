using CaffeineTracker9000.AppLogic;

namespace CaffeineTracker9000.Web.Models.ViewModels;

public class CaffeineWarningViewModel
{
    public int TotalMg { get; set; }
    public DangerLevel CurrentLevel { get; set; }

    public CaffeineWarningViewModel(int totalMg, DangerLevel level)
    {
        TotalMg = totalMg;
        CurrentLevel = level;
    }
}