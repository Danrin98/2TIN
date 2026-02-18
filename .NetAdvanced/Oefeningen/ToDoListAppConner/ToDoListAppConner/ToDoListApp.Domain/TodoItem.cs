using System.ComponentModel.DataAnnotations;

namespace ToDoListApp.Domain;

public class ToDoItem
{
    public Guid Id { get; private set; } // private set for EF (EF can set properties with a private setter)

    [Required(ErrorMessage = "The New item discription field is required.")]
    [MinLength(4, ErrorMessage = "The field New item description must be a string or array type with a minimum length of 4.")]
    public string Description { get; private set; } // private set for EF (EF can set properties with a private setter)

    [Display(Name = "Is done")]
    public bool IsDone { get; set; }

    private ToDoItem() // private constructor for EF
    {
        Id = Guid.Empty;
        Description = string.Empty;
        IsDone = false;
    }

    public static ToDoItem CreateNew(string description)
    {
        if (string.IsNullOrWhiteSpace(description))
        {
            throw new ArgumentException("The description required.", nameof(description));
        }

        return new ToDoItem
        {
            Description = description
        };
    }
}  