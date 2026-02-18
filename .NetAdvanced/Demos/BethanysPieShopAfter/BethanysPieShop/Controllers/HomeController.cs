using BethanysPieShop.Models;
using BethanysPieShop.ViewModels;
using Microsoft.AspNetCore.Mvc;

namespace BethanysPieShop.Controllers
{
    public class HomeController : Controller
    {
        private readonly IPieRepository _pieRepository;

        public HomeController(IPieRepository repo)
        {
            _pieRepository = repo;
        }
        public IActionResult Index()
        {
            HomeViewModel vm = new HomeViewModel();
            vm.PiesOfTheWeek = _pieRepository.PiesOfTheWeek;
            return View(vm);
        }
    }
}
