using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToDoListApp.AppLogic;
using ToDoListApp.Domain;

namespace ToDoListApp.Infrastructure
{
    public class ToDoItemRepository : IToDoItemRepository
    {
        private readonly ToDoListContext _context;

        public ToDoItemRepository(ToDoListContext context)
        {
            _context = context;
        }

        public int GetTotalOfItemsThatAreNotDone()
        {
            return _context.Set<ToDoItem>().Count(item => !item.IsDone);
        }

        public ToDoItem Update(Guid id, bool isDone)
        {
            var toDoItem = _context.Set<ToDoItem>().FirstOrDefault(i => i.Id == id);

            if (toDoItem == null)
            {
                throw new ArgumentException("Item not found");
            }
            toDoItem.IsDone = isDone;

            _context.SaveChanges();
            return toDoItem;
        }
    }
}
