using AlienAdoptionAgency.Domain;
using Microsoft.EntityFrameworkCore;
using static Microsoft.EntityFrameworkCore.DbLoggerCategory.Database;

namespace AlienAdoptionAgency.Infrastructure
{
    public class AgencyDbContext : DbContext
    {
        public DbSet<AdoptionRequest> AdoptionRequests { get; set; }

        public DbSet<Alien> Aliens { get; set; }

		public AgencyDbContext(DbContextOptions<AgencyDbContext> options) : base(options)
		{
		}

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<AdoptionRequest>(c =>
            c.HasData(
                GetAdoptionRequestsToSeed()));
            modelBuilder.Entity<Alien>(c =>
            c.HasData(
                GetAliensToSeed()));

            base.OnModelCreating(modelBuilder);
        }

        private AdoptionRequest[] GetAdoptionRequestsToSeed()
        {
            return new AdoptionRequest[]
            {
                new AdoptionRequest
                {
                    AdoptionRequestId = 1,
                    AlienId = 5, // Zizzle
                    ApplicantName = "Professor Quasar",
                    ApplicantEmail = "professor.quasar@cosmos.edu",
                    ReasonForAdoption = "Wants to study Floopticons in a low-gravity lab.",
                    RequestDate = DateTime.Parse("2024-10-15")
                },
                new AdoptionRequest
                {
                    AdoptionRequestId = 2,
                    AlienId = 8, // Snorblat
                    ApplicantName = "Captain Nebula",
                    ApplicantEmail = "captain.nebula@galaxynet.space",
                    ReasonForAdoption = "Loves bubble art and wants to host exhibitions.",
                    RequestDate = DateTime.Parse("2024-11-01")
                },
                new AdoptionRequest
                {
                    AdoptionRequestId = 3,
                    AlienId = 12, // Blibble
                    ApplicantName = "Dr. Ooze",
                    ApplicantEmail = "dr.ooze@slimeworld.science",
                    ReasonForAdoption = "Wants to study the unique slippery properties for science.",
                    RequestDate = DateTime.Parse("2024-11-10")
                },
                new AdoptionRequest
                {
                    AdoptionRequestId = 4,
                    AlienId = 15, // Frizzle
                    ApplicantName = "Galaxy Groomers Inc.",
                     ApplicantEmail = "contact@galaxygroomers.space",
                    ReasonForAdoption = "Perfect mascot for their alien grooming business.",
                    RequestDate = DateTime.Parse("2024-12-05")
                },
                new AdoptionRequest
                {
                    AdoptionRequestId = 5,
                    AlienId = 18, // Fuzzle
                    ApplicantName = "Nimbus Skypark",
                     ApplicantEmail = "nimbus.support@skypark.quaz",
                    ReasonForAdoption = "Wants an attraction for floating aliens.",
                    RequestDate = DateTime.Parse("2024-10-28")
                },
                new AdoptionRequest
                {
                    AdoptionRequestId = 6,
                    AlienId = 22, // Floopy
                    ApplicantName = "Fidgety Fred",
                     ApplicantEmail = "fred.fidgets@funland.quaz",
                    ReasonForAdoption = "Wants a wiggly companion for his entertainment park.",
                    RequestDate = DateTime.Parse("2024-11-22")
                }
            };
        }

        private static Alien[] GetAliensToSeed()
        {
            return new Alien[]
            {
                new Alien
                {
                    AlienId = 1,
                    Name = "Blorp",
                    Species = "Squigglenaut",
                    Planet = "Gloop-7",
                    SpecialNeeds = "Moisture-rich environment",
                    FavoriteSnack = "Asteroid Soup",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 2,
                    Name = "Zzork",
                    Species = "Blobulous",
                    Planet = "Nebula-42",
                    SpecialNeeds = "Dislikes sunlight",
                    FavoriteSnack = "Space Jelly",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 3,
                    Name = "Fizzle",
                    Species = "Glitterbug",
                    Planet = "Stardust-5",
                    SpecialNeeds = "Needs glitter for energy",
                    FavoriteSnack = "Nebula Nectar",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 4,
                    Name = "Glorp",
                    Species = "Spikydoodle",
                    Planet = "Quazar-9",
                    SpecialNeeds = "Prefers low gravity",
                    FavoriteSnack = "Meteor Bites",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 5,
                    Name = "Zizzle",
                    Species = "Floopticon",
                    Planet = "Nebulon-12",
                    SpecialNeeds = "Requires a constant supply of sparkling water",
                    FavoriteSnack = "Stardust Wafers",
                    IsAdoptable = false
                },
                new Alien
                {
                    AlienId = 6,
                    Name = "Borko",
                    Species = "Tentapuff",
                    Planet = "Glimmerfall",
                    SpecialNeeds = "Needs a UV-safe habitat",
                    FavoriteSnack = "Comet Chips",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 7,
                    Name = "Clink",
                    Species = "Metalion",
                    Planet = "Titanium-X",
                    SpecialNeeds = "Occasionally needs an oil bath",
                    FavoriteSnack = "Magnetic Pebbles",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 8,
                    Name = "Snorblat",
                    Species = "Bubblegumpher",
                    Planet = "Sticky-2",
                    SpecialNeeds = "Allergic to plastic",
                    FavoriteSnack = "Nebula Gummies",
                    IsAdoptable = false
                },
                new Alien
                {
                    AlienId = 9,
                    Name = "Fizzle",
                    Species = "Zapwhisker",
                    Planet = "Electrovoid",
                    SpecialNeeds = "Emits small electric shocks when happy",
                    FavoriteSnack = "Electric Eclairs",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 10,
                    Name = "Wobble",
                    Species = "Gelatinian",
                    Planet = "Jiggleprime",
                    SpecialNeeds = "Must stay hydrated to maintain form",
                    FavoriteSnack = "Liquid Pops",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 11,
                    Name = "Krizzle",
                    Species = "Quarkbat",
                    Planet = "Orbit-X",
                    SpecialNeeds = "Noisy during full moons",
                    FavoriteSnack = "Photon Crunch",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 12,
                    Name = "Blibble",
                    Species = "Oozalian",
                    Planet = "Slimeathos",
                    SpecialNeeds = "Extremely slippery",
                    FavoriteSnack = "Galactic Gels",
                    IsAdoptable = false
                },
                new Alien
                {
                    AlienId = 13,
                    Name = "Squizzle",
                    Species = "Spinaxian",
                    Planet = "Dizzydome",
                    SpecialNeeds = "Spins uncontrollably when nervous",
                    FavoriteSnack = "Swirly Sticks",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 14,
                    Name = "Zobbit",
                    Species = "Nimbletooth",
                    Planet = "Hyperflora",
                    SpecialNeeds = "Needs to be constantly moving",
                    FavoriteSnack = "Solar Beans",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 15,
                    Name = "Frizzle",
                    Species = "Poofian",
                    Planet = "Hairyworld",
                    SpecialNeeds = "Requires regular grooming",
                    FavoriteSnack = "Fluffy Cakes",
                    IsAdoptable = false
                },
                new Alien
                {
                    AlienId = 16,
                    Name = "Glitz",
                    Species = "Glitterhug",
                    Planet = "Shiny-P3",
                    SpecialNeeds = "Sheds glitter daily",
                    FavoriteSnack = "Shimmering Treats",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 17,
                    Name = "Plorb",
                    Species = "Chunkaphant",
                    Planet = "Craterholm",
                    SpecialNeeds = "Can’t jump",
                    FavoriteSnack = "Asteroid Bars",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 18,
                    Name = "Fuzzle",
                    Species = "Hoverbeast",
                    Planet = "Nimbus-V",
                    SpecialNeeds = "Floats unpredictably",
                    FavoriteSnack = "Sky Bites",
                    IsAdoptable = false
                },
                new Alien
                {
                    AlienId = 19,
                    Name = "Grum",
                    Species = "Spikeback",
                    Planet = "Cactarion",
                    SpecialNeeds = "Sensitive to loud noises",
                    FavoriteSnack = "Prickly Pears",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 20,
                    Name = "Zapra",
                    Species = "Bolt Hopper",
                    Planet = "Thunderspire",
                    SpecialNeeds = "Prone to static buildup",
                    FavoriteSnack = "Zappy Zips",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 21,
                    Name = "Klinkle",
                    Species = "Gearclink",
                    Planet = "Cognium-9",
                    SpecialNeeds = "Loves puzzles",
                    FavoriteSnack = "Gear Biscuits",
                    IsAdoptable = true
                },
                new Alien
                {
                    AlienId = 22,
                    Name = "Floopy",
                    Species = "Wiggleworm",
                    Planet = "Jellycross",
                    SpecialNeeds = "Extremely wiggly",
                    FavoriteSnack = "Gloop Worms",
                    IsAdoptable = false
                },
                new Alien
                {
                    AlienId = 23,
                    Name = "Quibble",
                    Species = "Nibblenot",
                    Planet = "Fidgetron",
                    SpecialNeeds = "Chews on everything",
                    FavoriteSnack = "Bite-sized Moons",
                    IsAdoptable = true
                }

            };
        }
    }
}