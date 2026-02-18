using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;

namespace AlienAdoptionAgency.Domain
{
    public class AdoptionRequest
    {
        public AdoptionRequest()
        {
            // A new request gets the current datetime as RequestDate
            RequestDate = DateTime.UtcNow;

            ApplicantName = string.Empty;
            ApplicantEmail = string.Empty;
            ReasonForAdoption = string.Empty;
        }

        public DateTime RequestDate { get; set; }

        public int AdoptionRequestId { get; set; }
        [Required]
        public string ApplicantName { get; set; }
        public string ApplicantEmail { get; set; }
        [MinLength(20)]
        public string ReasonForAdoption { get; set; }
        public int AlienId { get; set; }
        /* Do NOT remove the JsonIgnore attribute */
        [JsonIgnore] 
        public Alien? Alien { get; set; }

    }
}
