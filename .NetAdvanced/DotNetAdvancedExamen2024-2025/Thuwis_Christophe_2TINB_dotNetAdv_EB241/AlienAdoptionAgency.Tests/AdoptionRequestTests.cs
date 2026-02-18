using AlienAdoptionAgency.Domain;
using System.Security.Cryptography;

namespace AlienAdoptionAgency.Tests
{
    public class AdoptionRequestTests
    {

        public void RequestDate_Setter_ValueIsBefore1990_ShouldThrowArgumentOutOfRangeException()
        {
            var request = new AdoptionRequest();
            DateTime earliestDate = new DateTime(1990, 01, 01);

            request.RequestDate = earliestDate;

            Assert.That(() => request.RequestDate, Throws.ArgumentException);
        }
    }

    // Waarschijnlijk de juiste implementatie:
    //[Test]
    //    public void RequestDate_Setter_ValueIsBefore1990_ShouldThrowArgumentOutOfRangeException()
    //    {
    //        var request = new AdoptionRequest();
    //        DateTime invalidDate = new DateTime(1989, 12, 31);

    //        Assert.That(
    //            () => request.RequestDate = invalidDate,
    //            Throws.TypeOf<ArgumentOutOfRangeException>()
    //        );
    //    }
}