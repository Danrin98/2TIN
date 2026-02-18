namespace MathApp.CLI;

public class Calculator
{
    public int Add(int a, int b)
    {
        ArgumentOutOfRangeException.ThrowIfNegative(a);
        ArgumentOutOfRangeException.ThrowIfNegative(b);
        return a + b;
    }

    public int Multiply(int a, int b)
    {
        return a * b;
    }

    public int Divide(int value, int by)
    {
        ArgumentOutOfRangeException.ThrowIfGreaterThan(value, 100);
        return value / by;
    }
}
