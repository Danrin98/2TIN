using CaffeineTracker9000.AppLogic;
using CaffeineTracker9000.Domain;
using Microsoft.AspNetCore.Mvc.Rendering;

namespace CaffeineTracker9000.Web.Models.ViewModels
{
    public class NewConsumptionViewModel
    {
        public SelectList DrinkSelectList { get; private set; }
        public Consumption Consumption { get; }

        public NewConsumptionViewModel()
        {
            DrinkSelectList = new SelectList(new List<Drink>(), nameof(Drink.Id), nameof(Drink.Name));
            Consumption = new Consumption();
        }

        public NewConsumptionViewModel(Consumption consumption) : this()
        {
            Consumption = consumption;
        }

        public void FillDrinkSelectList(IDrinkRepository drinkRepository, int? selectedDrinkId)
        {
            IList<Drink> drinks = drinkRepository.GetAll().OrderBy(d => d.Name).ToList();
            DrinkSelectList = new SelectList(drinks, nameof(Drink.Id), nameof(Drink.Name), selectedDrinkId);
        }
    }
}
