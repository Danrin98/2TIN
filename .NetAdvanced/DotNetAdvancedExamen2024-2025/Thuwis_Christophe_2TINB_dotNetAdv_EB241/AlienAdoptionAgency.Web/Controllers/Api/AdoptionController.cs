using AlienAdoptionAgency.AppLogic;
using AlienAdoptionAgency.Domain;
using AlienAdoptionAgency.Web.Models;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace AlienAdoptionAgency.Web.Controllers.Api
{
	[Route("api/[controller]")]
	[ApiController]
	public class AdoptionController : ControllerBase
    {

		private readonly IAlienRepository _alienRepository;
		private readonly IAdoptionRepository _adoptionRepository;

		public AdoptionController(IAlienRepository alienRepository, IAdoptionRepository adoptionRepository)
		{
			_alienRepository = alienRepository;
			_adoptionRepository = adoptionRepository;
		}

		[HttpPost]
		public IActionResult Adoption([FromBody] AdoptionRequest model)
		{
			if (!ModelState.IsValid)
			{
				return BadRequest();
			}
			Alien? alien = _alienRepository.GetAlienById(model.AlienId);
			if (alien == null)
			{
				return NotFound();
			}
			if (alien.IsAdoptable == false)
			{
				return BadRequest();
			}
			AdoptionRequest adoptionRequest = new AdoptionRequest{
				ApplicantName = model.ApplicantName,
				ApplicantEmail = model.ApplicantEmail,
				ReasonForAdoption = model.ReasonForAdoption,
                AlienId = alien.AlienId,
                Alien = alien
			};
            _adoptionRepository.AddAdoptionRequest(adoptionRequest);
            _alienRepository.ToggleAlienAdoptionStatus(alien.AlienId);
			return Ok();
		}
		// Use attribute-based routing (POST to /api/Adoption)

		// Check if the alien with the given id exists
		// Check if IsAdopable is true
		//  If not: return 400
		// Save a new AdoptionRequest in the database 
		// toggle the status of the adopted alien
		//  return 200
	}
}
