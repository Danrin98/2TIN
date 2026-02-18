using System.Drawing;

namespace BalloonFun.Console;

public struct Balloon
{
    public readonly Color Color { get; }
    public readonly int Size { get; }
    public string ?Name { get; private set; }

    public Balloon(Color color, int size)
    {
        Color = color;
        Size = size;
        Name = null;
    }

    /// <summary>
    /// Personalizes the balloon by giving it a name.
    /// </summary>
    public void Baptize(string name)
    {
        Name = name;
    }
}