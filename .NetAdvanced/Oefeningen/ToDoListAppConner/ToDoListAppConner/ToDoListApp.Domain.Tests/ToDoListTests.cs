namespace ToDoListApp.Domain.Tests;

[TestFixture]
public class ToDoListTests
{
    [Test]
    [TestCase("List")]
    public void CreateNew_ValidTitle_ShouldReturnNewListWithIdAndTitle(string title)
    {
        var newList = ToDoList.CreateNew(title);

        Assert.That(newList.Title, Is.EqualTo(title));
        Assert.That(newList.Id, Is.Not.EqualTo(Guid.Empty));
        Assert.That(newList.Id, Is.TypeOf<Guid>());
    }

    [Test]
    [TestCase("")]
    public void CreateNew_TitleIsNullOrEmpty_ShouldThrowArgumentException(string title)
    {
        Assert.That(() => ToDoList.CreateNew(title), Throws.TypeOf<ArgumentException>());
        Assert.That(() => ToDoList.CreateNew(null!), Throws.TypeOf<ArgumentException>());
    }
}