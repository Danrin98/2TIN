using LinqExamples.Models;

namespace LinqExamples;

public class JoinExamples
{
    public int[] GetEvenNumbersOfIntersection(int[] firstList, int[] secondList)
    {
        IEnumerable<int> intersectionInt =
            from number in firstList
            join number2 in secondList on number equals number2
            where number2 % 2 == 0
            select number2;

        return intersectionInt.ToArray();

    }

    public IList<string> MatchPersonsOnBirthMonth(IList<Person> group1, IList<Person> group2)
    {
        // TODO: join the two groups on the month of their birth date
        //       and return a list of strings formatted like this: "{person1.Name} and {person2.Name}"

        IEnumerable<string> matchedPersons =
            from person in group1
            join partner in group2 on person.BirthDate.Month equals partner.BirthDate.Month
            select person.Name + " and " + partner.Name;

        return matchedPersons.ToArray();
    }
}