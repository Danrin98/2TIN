using System.ComponentModel.DataAnnotations;

namespace CaffeineTracker9000.Domain;

public class Consumption
{
    public int Id { get; set; }

    [Required(ErrorMessage = "Please enter your name")]
    [Display(Name = "User")]
    [StringLength(80)]
    public string UserName { get; set; } = string.Empty;

    [Required(ErrorMessage = "Please enter your email")]
    public string Email { get; set; } = string.Empty;

    [Required(ErrorMessage = "Please enter your location")]
    public string Location { get; set; } = string.Empty;

    [Required(ErrorMessage = "Please enter the time")]
    [Display(Name = "Time of consumption")]
    public DateTime Time { get; set; } = DateTime.UtcNow;

    [Required(ErrorMessage = "Please enter your drink")]
    [Display(Name = "Drink")]
    public int DrinkId { get; set; }
    public Drink? Drink { get; set; }

}

