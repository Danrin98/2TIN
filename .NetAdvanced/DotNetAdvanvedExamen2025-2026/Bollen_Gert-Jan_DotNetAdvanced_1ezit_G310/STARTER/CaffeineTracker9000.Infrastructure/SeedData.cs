using CaffeineTracker9000.Domain;
namespace CaffeineTracker9000.Infrastructure;

public static class SeedData
{
    public static void EnsureSeedData(CaffeineTrackerDbContext context)
    {
        context.Database.EnsureCreated();

        if ( context.Drinks.Any())
        {
            return;
        }

        var drinks = new[]
        {
            new Drink { Name = "Triple Espresso of Destiny", Description = "Small cup, galaxy-sized buzz.", CaffeineMgPerServing = 225 },
            new Drink { Name = "Mega Mate 3000", Description = "Mate infusion with rocket booster.", CaffeineMgPerServing = 180 },
            new Drink { Name = "Night-Shift Nitro", Description = "Nitro cold brew built for PXL all-nighters.", CaffeineMgPerServing = 260 },
            new Drink { Name = "Second Breakfast Brew", Description = "Because one coffee is never enough.", CaffeineMgPerServing = 190 },
            new Drink { Name = "Lab Energy Reactor", Description = "Energy drink smuggled from the robotics lab fridge.", CaffeineMgPerServing = 210 },
            new Drink { Name = "Coursework Crusher", Description = "Vanilla latte with a hint of impending deadline.", CaffeineMgPerServing = 150 },
            new Drink { Name = "Thesis Thunderbolt", Description = "Double shot + guarana syrup combo.", CaffeineMgPerServing = 310 }
        };

        context.Drinks.AddRange(drinks);
        context.SaveChanges();

        var consumptions = new[]
        {
            new Consumption { DrinkId = drinks[0].Id, UserName = "Luna", Email = "luna@pxl.be", Location = "PXL Library", Time = DateTime.UtcNow.AddHours(-5) },
            new Consumption { DrinkId = drinks[0].Id, UserName = "Milo", Email = "milo@student.pxl.be", Location = "Aula B", Time = DateTime.UtcNow.AddHours(-3) },
            new Consumption { DrinkId = drinks[1].Id, UserName = "Sam", Email = "sam@pxl.be", Location = "Student Café", Time = DateTime.UtcNow.AddHours(-2) },
            new Consumption { DrinkId = drinks[2].Id, UserName = "Tim", Email = "tim@pxl.be", Location = "Corda iSpace", Time = DateTime.UtcNow.AddHours(-8) },
            new Consumption { DrinkId = drinks[4].Id, UserName = "Pippin", Email = "fool.of.a.took@middle.earth",Location = "Bridge of Khazad-dûm (Study Corner)", Time = DateTime.UtcNow.AddHours(-12) },
            new Consumption { DrinkId = drinks[3].Id, UserName = "Jonas", Email = "jonas@pxl.be", Location = "Sports Hall", Time = DateTime.UtcNow.AddHours(-1) },
            new Consumption { DrinkId = drinks[5].Id, UserName = "Femke", Email = "femke@pxl.be", Location = "Project Studio", Time = DateTime.UtcNow.AddHours(-4) }
        };

        context.Consumptions.AddRange(consumptions);
        context.SaveChanges();
    }
}

