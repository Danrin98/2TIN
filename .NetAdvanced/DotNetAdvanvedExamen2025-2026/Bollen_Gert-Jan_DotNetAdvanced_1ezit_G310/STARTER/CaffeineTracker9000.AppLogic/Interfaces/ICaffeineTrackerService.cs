using CaffeineTracker9000.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CaffeineTracker9000.AppLogic;

public interface ICaffeineTrackerService
{
    int TotalForDrink(Drink drink);
    DangerLevel DailyDoseToDangerLevel(int caffeineMg);
    DangerLevel SingleDoseToDangerLevel(int caffeineMg);
}

