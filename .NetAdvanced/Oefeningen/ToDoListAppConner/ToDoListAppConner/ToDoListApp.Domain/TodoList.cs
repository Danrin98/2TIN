using System.ComponentModel.DataAnnotations;

namespace ToDoListApp.Domain;

public class ToDoList
{
    public Guid Id { get; private set; } // private set for EF (EF can set properties with a private setter)

    [Required(ErrorMessage = "The title is required.")]
    [MinLength(4, ErrorMessage = "The field New list title must be a string or array type with a minimum length of 4.")]
    public string Title { get; set; }

    public IList<ToDoItem> Items { get; private set; }

    private ToDoList(Guid id, string title) // EF can use constructors of which the parameter names match the property names
    {
        Id = id;
        Title = title;
        Items = new List<ToDoItem>();
    }

    public static ToDoList CreateNew(string title)
    {
        if (string.IsNullOrWhiteSpace(title))
        {
            throw new ArgumentException("The title is required.", nameof(title));
        }

        Guid id = Guid.NewGuid();
        return new ToDoList(id, title);
    }
}