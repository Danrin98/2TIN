using Microsoft.AspNetCore.Mvc;
using ToDoListApp.AppLogic;
using ToDoListApp.Infrastructure;
using ToDoListApp.Web.Models;

namespace ToDoListApp.Web.Controllers
{
    public class ToDoListController : Controller
    {
        private readonly IToDoListRepository _toDoListRepository;
        private readonly IToDoItemRepository _toDoItemRepository;

        public ToDoListController(IToDoListRepository toDoListRepository, IToDoItemRepository toDoItemRepository)
        {
            _toDoListRepository = toDoListRepository;
            _toDoItemRepository = toDoItemRepository;
        }

        public IActionResult Search(string? titlefilter)
        {
            if (String.IsNullOrEmpty(titlefilter))
            {
                var model = new ToDoListSearchViewModel();
                return View(model);
            }

            var lists = _toDoListRepository.Find(titlefilter);
            var viewModel = new ToDoListSearchViewModel
            {
                TitleFilter = titlefilter,
                ToDoLists = lists
            };
            return View(viewModel);
        }

        [HttpGet]
        public IActionResult Detail(Guid id)
        {
            var toDoList = _toDoListRepository.GetById(id);
            if (toDoList == null)
            {
                return RedirectToAction("Index", "Home");
            }

            var model = new ToDoListDetailViewModel(toDoList);
            return View(model);
        }

        [HttpPost]
        public IActionResult Detail(ToDoListDetailViewModel model)
        {
            var toDoList = _toDoListRepository.GetById(model.ListId);
            if (toDoList == null)
            {
                return RedirectToAction("Index", "Home");
            }

            // Manual validation because no DataAnnotation on NewItemDescription
            if (string.IsNullOrWhiteSpace(model.NewItemDescription) || model.NewItemDescription.Length < 4)
            {
                ModelState.AddModelError(nameof(model.NewItemDescription), "Description must be at least 4 characters");
            }

            if (!ModelState.IsValid)
            {
                // Return view model with list loaded so items can be displayed
                var vmWithList = new ToDoListDetailViewModel(toDoList)
                {
                    NewItemDescription = model.NewItemDescription
                };
                return View(vmWithList);
            }

            _toDoListRepository.AddItemToExistingList(model.ListId, model.NewItemDescription);

            // Reload updated list and clear input field
            var updatedList = _toDoListRepository.GetById(model.ListId);
            var updatedModel = new ToDoListDetailViewModel(updatedList);

            ModelState.Clear();

            return View(updatedModel);
        }

        [HttpPost]
        public IActionResult MarkAsDone(Guid itemId, Guid listId, bool IsDone)
        {
            _toDoItemRepository.Update(itemId, IsDone);
            return RedirectToAction("Detail", new { id = listId });
        }


        [HttpGet]
        public IActionResult Create()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Create(AddNewToDoListModel model)
        {
            if (!ModelState.IsValid)
            {
                return View(model);
            }

            var newList = _toDoListRepository.AddNew(model.Title);

            // Redirect to detail page of the newly created list
            return RedirectToAction("Detail", new { id = newList.Id });
        }
    }
}
