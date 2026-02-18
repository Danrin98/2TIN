namespace Route98.Web
{
    public class Program
    {
        public static void Main(string[] args)
        {
            var builder = WebApplication.CreateBuilder(args);
            builder.Logging.AddDebug();

            // Add services to the container.
            builder.Services.AddControllersWithViews();
            
            var app = builder.Build();

            // Configure the HTTP request pipeline.
            if (!app.Environment.IsDevelopment())
            {
                app.UseExceptionHandler("/Home/Error");
                // The default HSTS value is 30 days. You may want to change this for production scenarios, see https://aka.ms/aspnetcore-hsts.
                app.UseHsts();
            }

            app.UseHttpsRedirection();
            app.UseStaticFiles();

            app.MapControllerRoute(
            name: "categories",
            pattern: "categories/{categoryCode:regex(^[A-Za-z]+$)}/products/{productsPerPage:int?}",
            defaults: new { controller = "Product", action = "Overview" });

            app.MapControllerRoute(
            name: "productDetail",
            pattern: "detail/of/product/{id:guid}",
            defaults: new { controller = "Product", action = "Detail" }
            );

            app.MapControllerRoute(
            name: "privacyOfHome",
            pattern: "privacy/of/home",
            defaults: new { controller = "Home", action = "Privacy" }
            );

            //app.MapControllerRoute(name: "default", pattern: "{action}/of/{controller}/{id:guid?}");

            app.MapDefaultControllerRoute();


            app.UseAuthorization();

            app.Run();
        }
    }
}