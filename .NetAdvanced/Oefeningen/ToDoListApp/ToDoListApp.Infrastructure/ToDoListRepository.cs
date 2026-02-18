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
    public class ToDoListRepository : IToDoListRepository
    {
        private readonly ToDoListContext _context;

        public ToDoListRepository(ToDoListContext context)
        {
            _context = context;
        }

        public void AddItemToExistingList(Guid listId, string itemDescription)
        {
            ToDoItem toDoItem = ToDoItem.CreateNew(itemDescription);
            //var toDoList = _context.ToDoLists.Single(l  => l.Id == listId);
            ToDoList? toDoList = _context
                .ToDoLists
                .Include(l => l.Items)
                .Where(l => l.Id == listId)
                .FirstOrDefault();
            if (toDoList == null)
            {
                throw new ArgumentException("ToDoList not found");
            }
            toDoList.Items.Add(toDoItem);
            _context.Set<ToDoItem>().Add(toDoItem);
            //_context.ToDoLists.Update(ToDoList); is wrss best practive
            _context.SaveChanges();
        }

        public ToDoList AddNew(string title)
        {
            ToDoList toDoList = ToDoList.CreateNew(title);
            _context.ToDoLists.Add(toDoList);
            _context.SaveChanges();
            return toDoList;
        }

        public IList<ToDoList> Find(string? titleFilter)
        {
            return _context.Set<ToDoList>().Where(l => l.Title.Contains(titleFilter)).ToList();
        }

        public ToDoList? GetById(Guid id)
        {
            ToDoList? toDoList = _context
                .ToDoLists
                .Include(l => l.Items)
                .FirstOrDefault(l => l.Id == id);
            return toDoList;
        }
    }
}
