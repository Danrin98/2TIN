namespace ToDoListApp.Domain.Tests;

[TestFixture]
public class ToDoListTests
{
    [Test]
    public void CreateNew_ValidTitle_ShouldReturnNewListWithIdAndTitle()
    {
        // Arrange
        String title = "title";
        List<ToDoItem> expectedList = new List<ToDoItem>();

        // Act
        ToDoList toDoList = ToDoList.CreateNew(title);

        // Assert
        Assert.That(toDoList.Title, Is.Not.Null);
        Assert.That(toDoList.Id, Is.Not.EqualTo(null));
        Assert.That(toDoList.Items, Is.EquivalentTo(expectedList));
    }

    [TestCase(null)]
    [TestCase("")]
    public void CreateNew_TitleIsNullOrEmpty_ShouldThrowArgumentException(string? title)
    {
        // Act & Assert
        Assert.That(() => ToDoList.CreateNew(title), Throws.TypeOf<ArgumentException>().With.Message.Contains("title cannot be empty"));
    }
}