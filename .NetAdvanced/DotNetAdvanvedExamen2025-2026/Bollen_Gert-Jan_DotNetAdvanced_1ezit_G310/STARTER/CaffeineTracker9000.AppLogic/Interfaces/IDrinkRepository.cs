using CaffeineTracker9000.Domain;

namespace CaffeineTracker9000.AppLogic;

/*
    Do NOT touch this file.
 */
public interface IDrinkRepository
{
    List<Drink> GetAll();
    Drink? GetById(int id);
    void Add(Drink drink);
}

