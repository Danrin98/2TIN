using CaffeineTracker9000.AppLogic;
using CaffeineTracker9000.Domain;

namespace CaffeineTracker9000.Infrastructure
{
    public class MockDrinkRepository : IDrinkRepository
    {
        private List<Drink> _inMemoryMockList;

        public MockDrinkRepository()
        {
            _inMemoryMockList = new List<Drink>
                {
                    new Drink
                    {
                        Id = 1,
                        Name = "Triple Espresso of Destiny",
                        Description = "Small cup, galaxy-sized buzz.",
                        CaffeineMgPerServing = 225,
                        Consumptions = new List<Consumption>
                        {
                            new Consumption { DrinkId = 1, UserName = "Luna", Email = "luna@pxl.be", Location = "PXL Library", Time = DateTime.UtcNow.AddHours(-5) },
                            new Consumption { DrinkId = 1, UserName = "Milo", Email = "milo@student.pxl.be", Location = "Aula B", Time = DateTime.UtcNow.AddHours(-3) }
                        }
                    },
                    new Drink
                    {
                        Id = 2,
                        Name = "Mega Mate 3000",
                        Description = "Mate infusion with rocket booster.",
                        CaffeineMgPerServing = 180,
                        Consumptions = new List<Consumption>
                        {
                            new Consumption { DrinkId = 2, UserName = "Sam", Email = "sam@pxl.be", Location = "Student Café", Time = DateTime.UtcNow.AddHours(-2) }
                        }
                    },
                    new Drink
                    {
                        Id = 3,
                        Name = "Night-Shift Nitro",
                        Description = "Nitro cold brew built for PXL all-nighters.",
                        CaffeineMgPerServing = 260,
                        Consumptions = new List<Consumption>
                        {
                            new Consumption { DrinkId = 3, UserName = "Tim", Email = "tim@pxl.be", Location = "Corda iSpace", Time = DateTime.UtcNow.AddHours(-8) }
                        }
                    },
                    new Drink
                    {
                        Id = 4,
                        Name = "Second Breakfast Brew",
                        Description = "Because one coffee is never enough.",
                        CaffeineMgPerServing = 190,
                        Consumptions = new List<Consumption>
                        {
                            new Consumption { DrinkId = 4, UserName = "Jonas", Email = "jonas@pxl.be", Location = "Sports Hall", Time = DateTime.UtcNow.AddHours(-1) }
                        }
                    },
                    new Drink
                    {
                        Id = 5,
                        Name = "Lab Energy Reactor",
                        Description = "Energy drink smuggled from the robotics lab fridge.",
                        CaffeineMgPerServing = 210,
                        Consumptions = new List<Consumption>
                        {
                            new Consumption { DrinkId = 5, UserName = "Pippin", Email = "fool.of.a.took@middle.earth", Location = "Bridge of Khazad-dûm (Study Corner)", Time = DateTime.UtcNow.AddHours(-12) }
                        }
                    },
                    new Drink
                    {
                        Id = 6,
                        Name = "Coursework Crusher",
                        Description = "Vanilla latte with a hint of impending deadline.",
                        CaffeineMgPerServing = 150,
                        Consumptions = new List<Consumption>
                        {
                            new Consumption { DrinkId = 6, UserName = "Femke", Email = "femke@pxl.be", Location = "Project Studio", Time = DateTime.UtcNow.AddHours(-4) }
                        }
                    },
                    new Drink
                    {
                        Id = 7,
                        Name = "Thesis Thunderbolt",
                        Description = "Double shot + guarana syrup combo.",
                        CaffeineMgPerServing = 310,
                        Consumptions = new List<Consumption>() // Empty list for this one
                    }
                };
        }

        public void Add(Drink drink)
        {
            throw new NotImplementedException();
        }

        public List<Drink> GetAll()
        {
            return _inMemoryMockList;
        }

        public Drink? GetById(int id)
        {
            foreach (var drink in _inMemoryMockList)
            {
                if (drink.Id == id) { return drink; }
            }
            return null;
        }
    }
}
