using Microsoft.AspNetCore.Mvc;
using PieShopDemo.Models;

namespace PieShopDemo.Controllers
{
    public class PieController : Controller
    {
        private IPieRepository _pieRepository;

        public PieController(IPieRepository repo)
        {
            _pieRepository = repo;
        }
        public IActionResult List()
        {
            List<Pie> allPies = _pieRepository.AllPies.ToList();
            return View(allPies);
        }
    }
}
