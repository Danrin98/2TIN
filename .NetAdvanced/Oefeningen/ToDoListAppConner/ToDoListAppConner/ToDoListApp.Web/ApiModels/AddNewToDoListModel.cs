using System.ComponentModel.DataAnnotations;

namespace ToDoListApp.Web.ApiModels
{
    public class AddNewToDoListModel
    {
        [Required(ErrorMessage = "The title is required.")]
        [MinLength(4, ErrorMessage = "De titel moet minimaal 4 tekens lang zijn.")]
        public string Title { get; set; } = string.Empty;
    }
}
