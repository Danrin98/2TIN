using System.ComponentModel.DataAnnotations;

namespace CaffeineTracker9000.Web.Models.Dto;

public class DrinkCreateDto
{
    [Required, StringLength(120)]
    public string Name { get; set; } = string.Empty;

    [StringLength(500)]
    public string? Description { get; set; }

    [Range(0, 2000)]
    public int CaffeineMgPerServing { get; set; }
}

