using BethanysPieShop.Models;
using Microsoft.AspNetCore.Mvc;

namespace BethanysPieShop.Controllers
{
    public class OrderController : Controller
    {
        public IActionResult Checkout()
        {
            return View();
        }

        [HttpPost]
        public IActionResult Checkout(Order order)
        {
            if (ModelState.IsValid)
            {
                return View(order);
            }
            else
            {
                return View(order);
            }
        }
    }
}
