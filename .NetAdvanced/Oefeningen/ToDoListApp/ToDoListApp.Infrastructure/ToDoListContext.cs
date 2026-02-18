using Microsoft.EntityFrameworkCore;
using ToDoListApp.Domain;

namespace ToDoListApp.Infrastructure;

public class ToDoListContext: DbContext
{
    public DbSet<ToDoList> ToDoLists { get; set; }

    public ToDoListContext(DbContextOptions<ToDoListContext> options) : base(options)
    {

    }
}