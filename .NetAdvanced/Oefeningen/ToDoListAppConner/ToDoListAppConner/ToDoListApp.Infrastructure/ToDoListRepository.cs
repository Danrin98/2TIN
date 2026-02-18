using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ToDoListApp.AppLogic;
using ToDoListApp.Domain;
using Microsoft.EntityFrameworkCore;

namespace ToDoListApp.Infrastructure
{
    public class ToDoListRepository : IToDoListRepository
    {
        private readonly ToDoListContext _toDoListContext;

        public ToDoListRepository(ToDoListContext toDoListContext)
        {
            _toDoListContext = toDoListContext;
        }
        public void AddItemToExistingList(Guid listId, string itemDescription)
        {
            ToDoList? toDoList = _toDoListContext.ToDoLists
                .Include(x => x.Items)
                .Where(x => x.Id == listId)
                .FirstOrDefault();
            ToDoItem newItem = ToDoItem.CreateNew(itemDescription);
            toDoList.Items.Add(newItem);
            _toDoListContext.SaveChanges();
        }

        public ToDoList AddNew(string title)
        {
            ToDoList newToDoList = ToDoList.CreateNew(title);
            _toDoListContext.ToDoLists.Add(newToDoList);
            _toDoListContext.SaveChanges();
            return newToDoList;
        }

        public IList<ToDoList> Find(string? titleFilter)
        {
            if (!string.IsNullOrEmpty(titleFilter))
            {
                return _toDoListContext.ToDoLists
                    .Include(x => x.Items)
                    .Where(x => x.Title.Contains(titleFilter))
                    .ToList();
            }
            return _toDoListContext.ToDoLists
                   .Include(x => x.Items)
                   .ToList();

        }

        public ToDoList? GetById(Guid id)
        {
            return _toDoListContext.ToDoLists
               .Include(x => x.Items)
               .Where(x => x.Id == id)
               .FirstOrDefault();
        }
    }
}
