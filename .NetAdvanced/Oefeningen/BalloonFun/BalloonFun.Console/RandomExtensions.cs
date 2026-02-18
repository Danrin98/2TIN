using System.Drawing;

namespace BalloonFun.Console;

public static class RandomExtensions
{
    public static Balloon NextBalloon( this Random random, int maxSize)
    {
        return new Balloon(GenerateRandomColor(random), maxSize + 1);
    }

    public static Balloon NextBalloonFromArray( this Random random, Balloon[] balloons)
    {
        return balloons[random.Next(balloons.Length)];
    }

    private static Color GenerateRandomColor(Random random)
    {
        int red = random.Next(0, 256);
        int green = random.Next(0, 256);
        int blue = random.Next(0, 256);
        return Color.FromArgb(red, green, blue);
    }
}