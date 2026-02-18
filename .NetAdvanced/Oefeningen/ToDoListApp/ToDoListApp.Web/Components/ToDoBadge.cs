using Microsoft.AspNetCore.Mvc;
using ToDoListApp.AppLogic;

namespace ToDoListApp.Web.Components
{
    public class ToDoBadge: ViewComponent
    {
        private readonly IToDoItemRepository _toDoItemRepository;

        public ToDoBadge(IToDoItemRepository toDoItemRepository)
        {
            _toDoItemRepository = toDoItemRepository;
        }

        public IViewComponentResult Invoke()
        {
            var toDos = _toDoItemRepository.GetTotalOfItemsThatAreNotDone();
            return View(toDos);
        }
    }
}
