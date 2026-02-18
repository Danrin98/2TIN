namespace AlienAdoptionAgency.Domain
{
    /* DO NOT touch this file */
    public class Alien
    {
        public Alien()
        {
            IsAdoptable = true;

            Name = string.Empty;
            Species = string.Empty;
            Planet = string.Empty;
            SpecialNeeds = string.Empty;
            FavoriteSnack = string.Empty;
        }

        public Alien(string name, string species, string planet, string specialNeeds, string favoriteSnack)
        {
            Name = name;
            Species = species;
            Planet = planet;
            SpecialNeeds = specialNeeds;
            FavoriteSnack = favoriteSnack;
            IsAdoptable = true;
        }

        public int AlienId { get; set; }
        public string Name { get; set; }
        public string Species { get; set; }
        public string Planet { get; set; }
        public string SpecialNeeds { get; set; }
        public string FavoriteSnack { get; set; }
        public bool IsAdoptable { get; set; }
    }
}
