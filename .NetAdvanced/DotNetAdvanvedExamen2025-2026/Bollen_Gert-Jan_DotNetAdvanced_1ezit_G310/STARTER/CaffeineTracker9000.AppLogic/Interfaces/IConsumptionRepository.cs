using CaffeineTracker9000.Domain;

namespace CaffeineTracker9000.AppLogic;

/*
    Do NOT touch this file.
 */
public interface IConsumptionRepository
{
    void Add(Consumption consumption);
    int GetConsumptionOfToday();
}

