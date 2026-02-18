namespace ToDoListApp.Domain.Tests;

[TestFixture]
public class ToDoItemTests
{
    [Test]
    [TestCase("homework")]
    public void CreateNew_ValidDescription_ShouldReturnNewItem_ThatIsNotDone_AndWithIdAndDescription(string description)
    {
        var newItem = ToDoItem.CreateNew(description);

        Assert.That(newItem.Description, Is.EqualTo(description));
        Assert.That(newItem.IsDone, Is.EqualTo(false));
    }

    [Test]
    [TestCase("")]
    public void CreateNew_DescriptionIsNullOrEmpty_ShouldThrowArgumentException(string description)
    {
        Assert.That(() => ToDoItem.CreateNew(description), Throws.TypeOf<ArgumentException>());
        Assert.That(() => ToDoItem.CreateNew(null!), Throws.TypeOf<ArgumentException>());
    }
}