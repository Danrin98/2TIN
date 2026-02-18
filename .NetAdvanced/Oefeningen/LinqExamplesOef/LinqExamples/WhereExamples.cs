using LinqExamples.Models;

namespace LinqExamples;

public class WhereExamples
{
    public int[] FilterOutNumbersDivisibleByTen(int[] numbers)
    {
        var query = 
            from n in numbers
            where n % 10 == 0
            select n;

        return query.ToArray();
    }

    public IList<Person> FilterOutPersonsThatAreEighteenOrOlder(List<Person> persons)
    {
        var query =
            from p in persons
            where DateTime.Now.Year - p.BirthDate.Year >= 18
            select p;

        return query.ToArray();
    }

    public IList<Person> FilterOutPersonsBornInFebruaryInAYearDividableBy4(List<Person> persons)
    {
        var query = 
            from p in persons
            where p.BirthDate.Year % 4 == 0 && !(p.BirthDate.Month == 2)
            select p;

        return query.ToArray();
    }
}