using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using ToDoListApp.AppLogic;
using ToDoListApp.Domain;

namespace ToDoListApp.Infrastructure
{
    public class ToDoItemRepository : IToDoItemRepository
    {
        private readonly ToDoListContext _toDoListContext;

        public ToDoItemRepository(ToDoListContext toDoListContext)
        {
            _toDoListContext = toDoListContext;
        }
        public int GetTotalOfItemsThatAreNotDone()
        {
            return _toDoListContext.ToDoLists
           .SelectMany(l => l.Items)
           .Count(i => !i.IsDone);        
        }

        public ToDoItem Update(Guid id, bool isDone)
        {
            var item = _toDoListContext.ToDoLists
                .SelectMany(l => l.Items)
                .FirstOrDefault(i => i.Id == id);
            item.IsDone = isDone;
            _toDoListContext.SaveChanges();
            return item;
        }
    }
}
