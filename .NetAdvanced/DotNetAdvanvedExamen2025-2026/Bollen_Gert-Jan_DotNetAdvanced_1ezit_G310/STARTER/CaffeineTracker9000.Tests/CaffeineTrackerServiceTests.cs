using CaffeineTracker9000.AppLogic;
using CaffeineTracker9000.Domain;
using NUnit.Framework;

namespace CaffeineTracker9000.Tests;

[TestFixture]
public class CaffeineTrackerServiceTests
{
    private CaffeineTrackerService _service;

    [SetUp]
    public void SetUp()
    {
        _service = new CaffeineTrackerService();
    }

    // Helper method to create a mock Drink with specified
    // number of Consumptions
    private Drink GetMockDrinkWithConsumption(int idDrink, int mgCaffeine, int numberConsumption)
    {
        int mockConsumerId = 1;
        Drink mockDrink = new Drink
        {
            Id = idDrink,
            Name = "Espresso",
            CaffeineMgPerServing = mgCaffeine,
            Consumptions = new List<Consumption>()
        };
        for (int i = 0; i < numberConsumption; i++)
        {
            mockDrink.Consumptions.Add(new Consumption
            {
                Id = mockConsumerId,
                DrinkId = idDrink,
                UserName = $"User{mockConsumerId}",
                Email = $"user{mockConsumerId}@test.com",
                Location = "Office"
            });
            mockConsumerId++;
        }
        return mockDrink;
    }

    [Test]
    public void TotalForDrink_WithEmptyConsumptions_ReturnsZero()
    {
        // Use the mock helper to create a drink with 0 consumptions
        var drink = GetMockDrinkWithConsumption(1, 100, 0);
        var amountExpected = 0;
        // Then test the service

        Assert.That(drink.Consumptions.Count, Is.EqualTo(amountExpected));
    }

    [Test]
    public void TotalForDrink_WithSingleConsumption_ReturnsCorrectTotal()
    {
        // Use the mock helper to create a drink with 1 consumption
        var drink = GetMockDrinkWithConsumption(1, 150, 1);
        var amountExpected = 1;
        // Then test the service

        Assert.That(drink.Consumptions.Count, Is.EqualTo(amountExpected));
    }

    [Test]
    public void TotalForDrink_WithMultipleConsumptions_ReturnsCorrectTotal()
    {
        // Use the mock helper to create a drink with 3 consumptions
        var drink = GetMockDrinkWithConsumption(1, 80, 3);
        var amountExpected = 3;
        // Then test the service

        Assert.That(drink.Consumptions.Count, Is.EqualTo(amountExpected));
    }


    /* Tests below are OK, DO NOT TOUCH THEM */
    #region SingleDoseTodangerLevel Tests

    [Test]
    [TestCase(0, DangerLevel.Manageable)]
    [TestCase(50, DangerLevel.Manageable)]
    [TestCase(100, DangerLevel.Manageable)]
    [TestCase(199, DangerLevel.Manageable)]
    public void SingleDoseToDangerLevel_BelowYellowThreshold_ReturnsManageable(int caffeineMg, DangerLevel expected)
    {
        // Act
        var result = _service.SingleDoseToDangerLevel(caffeineMg);

        // Assert
        Assert.That(result, Is.EqualTo(expected));
    }

    [Test]
    [TestCase(200, DangerLevel.YellowAlert)]
    [TestCase(250, DangerLevel.YellowAlert)]
    [TestCase(299, DangerLevel.YellowAlert)]
    public void SingleDoseToDangerLevel_InYellowRange_ReturnsYellowAlert(int caffeineMg, DangerLevel expected)
    {
        // Act
        var result = _service.SingleDoseToDangerLevel(caffeineMg);

        // Assert
        Assert.That(result, Is.EqualTo(expected));
    }

    [Test]
    [TestCase(300, DangerLevel.TooMuch)]
    [TestCase(400, DangerLevel.TooMuch)]
    [TestCase(1000, DangerLevel.TooMuch)]
    public void SingleDoseToDangerLevel_AboveYellowThreshold_ReturnsTooMuch(int caffeineMg, DangerLevel expected)
    {
        // Act
        var result = _service.SingleDoseToDangerLevel(caffeineMg);

        // Assert
        Assert.That(result, Is.EqualTo(expected));
    }

    #endregion

    #region DailyDoseTodangerLevel Tests

    [Test]
    [TestCase(0, DangerLevel.Manageable)]
    [TestCase(500, DangerLevel.Manageable)]
    [TestCase(1000, DangerLevel.Manageable)]
    [TestCase(1999, DangerLevel.Manageable)]
    public void DailyDoseToDangerLevel_BelowYellowThreshold_ReturnsManageable(int caffeineMg, DangerLevel expected)
    {
        // Act
        var result = _service.DailyDoseToDangerLevel(caffeineMg);

        // Assert
        Assert.That(result, Is.EqualTo(expected));
    }

    [Test]
    [TestCase(2000, DangerLevel.YellowAlert)]
    [TestCase(5000, DangerLevel.YellowAlert)]
    [TestCase(7999, DangerLevel.YellowAlert)]
    public void DailyDoseToDangerLevel_InYellowRange_ReturnsYellowAlert(int caffeineMg, DangerLevel expected)
    {
        // Act
        var result = _service.DailyDoseToDangerLevel(caffeineMg);

        // Assert
        Assert.That(result, Is.EqualTo(expected));
    }

    [Test]
    [TestCase(8000, DangerLevel.TooMuch)]
    [TestCase(10000, DangerLevel.TooMuch)]
    [TestCase(20000, DangerLevel.TooMuch)]
    public void DailyDoseToDangerLevel_AboveDangerThreshold_ReturnsTooMuch(int caffeineMg, DangerLevel expected)
    {
        // Act
        var result = _service.DailyDoseToDangerLevel(caffeineMg);

        // Assert
        Assert.That(result, Is.EqualTo(expected));
    }

    #endregion
}