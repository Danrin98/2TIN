using LinqExamples.Models;

namespace LinqExamples;

public class OrderByExamples
{
    public double[] SortAnglesFromBigToSmall(double[] angles)
    {
        IEnumerable<double> sortedAngles =
            from angle in angles
            orderby angle descending
            select angle;
       
        return sortedAngles.ToArray();
    }

    public IList<Person> SortPersonsFromYoungToOldAndThenOnNameAlphabetically(List<Person> persons)
    {
        IEnumerable<Person> sortedPersons =
            from person in persons
            orderby person.BirthDate
            orderby person.Name
            select person;

        return sortedPersons.ToList();
    }
}