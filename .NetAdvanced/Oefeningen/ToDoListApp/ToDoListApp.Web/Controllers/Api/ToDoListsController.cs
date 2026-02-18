using System.IO.Pipelines;
using Microsoft.AspNetCore.Mvc;
using ToDoListApp.AppLogic;
using ToDoListApp.Domain;
using ToDoListApp.Web.Models;

namespace ToDoListApp.Web.Controllers.Api
{
    [ApiController]
    [Route("api/[controller]")]
    public class ToDoListsController : ControllerBase
    {

        private readonly IToDoListRepository _toDoListRepository;
        private readonly IToDoItemRepository _toDoItemRepository;

        public ToDoListsController(IToDoListRepository toDoListRepository, IToDoItemRepository toDoItemRepository)
        {
            _toDoListRepository = toDoListRepository;
            _toDoItemRepository = toDoItemRepository;
        }

        [HttpGet("{id:guid}")]
        public ActionResult<ToDoList> GetList(Guid id)
        {
            var toDoList = _toDoListRepository.GetById(id);

            if (toDoList == null)
            {
                return NotFound();
            }

            return Ok(toDoList);
        }

        [HttpPost]
        public ActionResult<ToDoList> PostList(AddNewToDoListModel listModel)
        {
            ToDoList list = _toDoListRepository.AddNew(listModel.Title);
            return CreatedAtAction(nameof(GetList), new { id = list.Id }, list);

        }

        [HttpPut("{listId:guid}/items/{itemId:guid}")]
        public ActionResult<ToDoItem> UpdateItem(Guid listId, Guid itemId, UpdateToDoItemModel model)
        {
            var updatedItem = _toDoItemRepository.Update(itemId, model.IsDone);
            return Ok(updatedItem);
        }
    }
}
