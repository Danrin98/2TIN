using LinqExamples.Models;

namespace LinqExamples
{
    public class SelectExamples
    {
        public IList<int> GetLengthOfWords(IEnumerable<string?> words)
        {
            IEnumerable<int> selectedStrings =
                from word in words
                select word != null ? word.Length : 0;

            return selectedStrings.ToList();

        }

        public IList<AngleInfo> ConvertAnglesToAngleInfos(IEnumerable<double> anglesInDegrees)
        {
            //Tip: You can use Math.Cos and Math.Sin methods to calculate the cosinus and sinus of an angle.
            //     These methods expect the angle to be in radians.
            //     You can convert degrees to radians using this formula: radians = degrees * Math.PI / 180


            IEnumerable<AngleInfo> convertedAngles =
                from angle in anglesInDegrees
                select new AngleInfo{ 
                    Angle = angle, 
                    Cosinus = Math.Cos(angle * Math.PI / 180), 
                    Sinus = Math.Sin(angle * Math.PI / 180) 
                };

            return convertedAngles.ToList();
        }
    }
}