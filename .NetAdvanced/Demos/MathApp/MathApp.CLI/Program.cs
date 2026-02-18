namespace MathApp.CLI
{
    public class Program
    {
        static void Main(string[] args)
        {
            var calculator = new Calculator();
            int a = 3;
            int b = 5;
            Console.WriteLine($"a + b = {calculator.Add(a, b)}");
            Console.WriteLine($"a * b = {calculator.Multiply(a, b)}");
            Console.WriteLine("Press any key to exit...");
            Console.ReadKey(true);
        }
    }
}