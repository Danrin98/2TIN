using Microsoft.AspNetCore.Mvc;
using ToDoListApp.AppLogic;
using ToDoListApp.Domain;
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

        [HttpGet("/ToDoList/Search")]
        public IActionResult Search()
        {
            return View(new ToDoListSearchViewModel());
        }

        [HttpPost("/ToDoList/Search")]
        public IActionResult Search(ToDoListSearchViewModel model)
        {
            var viewModel = new ToDoListSearchViewModel
            {
                TitleFilter = model.TitleFilter,
                ToDoLists = _toDoListRepository.Find(model.TitleFilter) ?? new List<ToDoList>()
            };
            return View(viewModel);
        }

        [HttpGet("/ToDoList/Details/{id}")]
        public IActionResult Detail(Guid id)
        {
            ToDoList? toDoList = _toDoListRepository.GetById(id);

            if (toDoList == null)
            {
                return RedirectToAction("Index", "Home");
            }

            ViewData["Title"] = $"{toDoList.Title} – TODO";

            var viewModel = new ToDoListDetailViewModel(toDoList);
            return View(viewModel);
        }

        [HttpPost("/ToDoList/Details/{id}")]
        public IActionResult Detail(ToDoListDetailViewModel model)
        {
            if (ModelState.IsValid)
            {
                _toDoListRepository.AddItemToExistingList(model.ListId, model.NewItemDescription);
                ModelState.Clear();
                return RedirectToAction("Detail", new { id = model.ListId });
            }

            var toDoList = _toDoListRepository.GetById(model.ListId);

            var errorModel = new ToDoListDetailViewModel(toDoList)
            {
                NewItemDescription = model.NewItemDescription
            };
            return View(errorModel);
        }

        [HttpPost("/ToDoList/MarkAsDone")]
        public IActionResult MarkAsDone(Guid itemId, Guid listId, bool IsDone)
        {
            _toDoItemRepository.Update(itemId, IsDone);
            return RedirectToAction("Detail", new { id = listId });
        }

    }
}

    

