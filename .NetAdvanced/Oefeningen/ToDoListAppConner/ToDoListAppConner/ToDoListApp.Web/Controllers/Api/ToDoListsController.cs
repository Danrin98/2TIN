using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ToDoListApp.AppLogic;
using ToDoListApp.Domain;
using ToDoListApp.Web.ApiModels;
using ToDoListApp.Web.Models;

namespace ToDoListApp.Web.Controllers.Api
{

    [ApiController]
    [Route("api/todolists")]
    public class ToDoListsController : ControllerBase
    {
        private readonly IToDoListRepository _toDoListRepository;
        private readonly IToDoItemRepository _toDoItemRepository;

        public ToDoListsController(IToDoListRepository toDoListRepository, IToDoItemRepository toDoItemRepository)
        {
            _toDoListRepository = toDoListRepository;
            _toDoItemRepository = toDoItemRepository;
        }

        [HttpGet("{id}")]
        public ActionResult<ToDoList> GetToDoList(Guid id)
        {
            ToDoList? toDoList = _toDoListRepository.GetById(id);
            if (toDoList == null)
            {
                return NotFound();
            }
            return Ok(toDoList);
        }

        [HttpPost]
        public ActionResult<ToDoList> PostTodoList(AddNewToDoListModel model)
        {
            ToDoList newToDoList = _toDoListRepository.AddNew(model.Title);
            return CreatedAtAction(nameof(GetToDoList), new { id = newToDoList.Id }, newToDoList);
        }

        [HttpPut("{listId}/items/{id}")]
        public ActionResult<ToDoItem?> Update(Guid listId, Guid id, bool isDone)
        {
            ToDoItem? toDoItem = _toDoItemRepository.Update(id, isDone);
            return Ok(toDoItem);
        }

    }
}