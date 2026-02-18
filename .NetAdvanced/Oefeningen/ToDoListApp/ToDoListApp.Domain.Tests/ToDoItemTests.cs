namespace ToDoListApp.Domain.Tests;

[TestFixture]
public class ToDoItemTests
{
    [Test]
    public void CreateNew_ValidDescription_ShouldReturnNewItem_ThatIsNotDone_AndWithIdAndDescription()
    {
        // Arrange
        String description = "description";

        // Act
        ToDoItem toDoItem = ToDoItem.CreateNew(description);

        // Assert
        Assert.That(toDoItem.Description, Is.Not.Null);
        Assert.That(toDoItem.Id, Is.Not.EqualTo(null));
        Assert.That(toDoItem.IsDone, Is.False);
    }

    [TestCase(null)]
    [TestCase("")]
    public void CreateNew_DescriptionIsNullOrEmpty_ShouldThrowArgumentException(string? description)
    {
        // Act & Assert
        Assert.That(() => ToDoItem.CreateNew(description), Throws.TypeOf<ArgumentException>().With.Message.Contains("description cannot be empty"));
    }
}