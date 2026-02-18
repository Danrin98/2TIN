using System.ComponentModel.DataAnnotations;
using ToDoListApp.Domain;

namespace ToDoListApp.Web.Models;

public class ToDoListSearchViewModel
{
    [Display(Name = "Title")]
    public string? TitleFilter { get; set; }
    public IList<ToDoList> ToDoLists { get; set; }
    public ToDoListSearchViewModel()
    {
        ToDoLists = new List<ToDoList>();
        TitleFilter = null;
    }
}