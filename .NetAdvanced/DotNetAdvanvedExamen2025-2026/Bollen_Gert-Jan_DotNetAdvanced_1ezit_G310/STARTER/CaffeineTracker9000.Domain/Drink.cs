using System.ComponentModel.DataAnnotations;

namespace CaffeineTracker9000.Domain;

public class Drink
{
    public int Id { get; set; }

    [Required(ErrorMessage = "Please enter your name")]
    public string Name { get; set; } = string.Empty;

    [StringLength(100)]
    public string? Description { get; set; }
    [Range(0, 2000)]
    public int CaffeineMgPerServing { get; set; }

    public ICollection<Consumption> Consumptions { get; set; } = new List<Consumption>();

}



