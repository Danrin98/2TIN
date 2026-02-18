using Microsoft.AspNetCore.Mvc;

namespace AlienAdoptionAgency.Web.Controllers
{
    public class WelcomeController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }
    }
}
