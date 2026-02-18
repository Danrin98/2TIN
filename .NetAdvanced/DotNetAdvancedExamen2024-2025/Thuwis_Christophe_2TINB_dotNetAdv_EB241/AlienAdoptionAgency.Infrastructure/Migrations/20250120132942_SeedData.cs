using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

#pragma warning disable CA1814 // Prefer jagged arrays over multidimensional

namespace AlienAdoptionAgency.Infrastructure.Migrations
{
    /// <inheritdoc />
    public partial class SeedData : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.InsertData(
                table: "Aliens",
                columns: new[] { "AlienId", "FavoriteSnack", "IsAdoptable", "Name", "Planet", "SpecialNeeds", "Species" },
                values: new object[,]
                {
                    { 1, "Asteroid Soup", true, "Blorp", "Gloop-7", "Moisture-rich environment", "Squigglenaut" },
                    { 2, "Space Jelly", true, "Zzork", "Nebula-42", "Dislikes sunlight", "Blobulous" },
                    { 3, "Nebula Nectar", true, "Fizzle", "Stardust-5", "Needs glitter for energy", "Glitterbug" },
                    { 4, "Meteor Bites", true, "Glorp", "Quazar-9", "Prefers low gravity", "Spikydoodle" },
                    { 5, "Stardust Wafers", false, "Zizzle", "Nebulon-12", "Requires a constant supply of sparkling water", "Floopticon" },
                    { 6, "Comet Chips", true, "Borko", "Glimmerfall", "Needs a UV-safe habitat", "Tentapuff" },
                    { 7, "Magnetic Pebbles", true, "Clink", "Titanium-X", "Occasionally needs an oil bath", "Metalion" },
                    { 8, "Nebula Gummies", false, "Snorblat", "Sticky-2", "Allergic to plastic", "Bubblegumpher" },
                    { 9, "Electric Eclairs", true, "Fizzle", "Electrovoid", "Emits small electric shocks when happy", "Zapwhisker" },
                    { 10, "Liquid Pops", true, "Wobble", "Jiggleprime", "Must stay hydrated to maintain form", "Gelatinian" },
                    { 11, "Photon Crunch", true, "Krizzle", "Orbit-X", "Noisy during full moons", "Quarkbat" },
                    { 12, "Galactic Gels", false, "Blibble", "Slimeathos", "Extremely slippery", "Oozalian" },
                    { 13, "Swirly Sticks", true, "Squizzle", "Dizzydome", "Spins uncontrollably when nervous", "Spinaxian" },
                    { 14, "Solar Beans", true, "Zobbit", "Hyperflora", "Needs to be constantly moving", "Nimbletooth" },
                    { 15, "Fluffy Cakes", false, "Frizzle", "Hairyworld", "Requires regular grooming", "Poofian" },
                    { 16, "Shimmering Treats", true, "Glitz", "Shiny-P3", "Sheds glitter daily", "Glitterhug" },
                    { 17, "Asteroid Bars", true, "Plorb", "Craterholm", "Can’t jump", "Chunkaphant" },
                    { 18, "Sky Bites", false, "Fuzzle", "Nimbus-V", "Floats unpredictably", "Hoverbeast" },
                    { 19, "Prickly Pears", true, "Grum", "Cactarion", "Sensitive to loud noises", "Spikeback" },
                    { 20, "Zappy Zips", true, "Zapra", "Thunderspire", "Prone to static buildup", "Bolt Hopper" },
                    { 21, "Gear Biscuits", true, "Klinkle", "Cognium-9", "Loves puzzles", "Gearclink" },
                    { 22, "Gloop Worms", false, "Floopy", "Jellycross", "Extremely wiggly", "Wiggleworm" },
                    { 23, "Bite-sized Moons", true, "Quibble", "Fidgetron", "Chews on everything", "Nibblenot" }
                });

            migrationBuilder.InsertData(
                table: "AdoptionRequests",
                columns: new[] { "AdoptionRequestId", "AlienId", "ApplicantEmail", "ApplicantName", "ReasonForAdoption", "RequestDate" },
                values: new object[,]
                {
                    { 1, 5, "professor.quasar@cosmos.edu", "Professor Quasar", "Wants to study Floopticons in a low-gravity lab.", new DateTime(2024, 10, 15, 0, 0, 0, 0, DateTimeKind.Unspecified) },
                    { 2, 8, "captain.nebula@galaxynet.space", "Captain Nebula", "Loves bubble art and wants to host exhibitions.", new DateTime(2024, 11, 1, 0, 0, 0, 0, DateTimeKind.Unspecified) },
                    { 3, 12, "dr.ooze@slimeworld.science", "Dr. Ooze", "Wants to study the unique slippery properties for science.", new DateTime(2024, 11, 10, 0, 0, 0, 0, DateTimeKind.Unspecified) },
                    { 4, 15, "contact@galaxygroomers.space", "Galaxy Groomers Inc.", "Perfect mascot for their alien grooming business.", new DateTime(2024, 12, 5, 0, 0, 0, 0, DateTimeKind.Unspecified) },
                    { 5, 18, "nimbus.support@skypark.quaz", "Nimbus Skypark", "Wants an attraction for floating aliens.", new DateTime(2024, 10, 28, 0, 0, 0, 0, DateTimeKind.Unspecified) },
                    { 6, 22, "fred.fidgets@funland.quaz", "Fidgety Fred", "Wants a wiggly companion for his entertainment park.", new DateTime(2024, 11, 22, 0, 0, 0, 0, DateTimeKind.Unspecified) }
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DeleteData(
                table: "AdoptionRequests",
                keyColumn: "AdoptionRequestId",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "AdoptionRequests",
                keyColumn: "AdoptionRequestId",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "AdoptionRequests",
                keyColumn: "AdoptionRequestId",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "AdoptionRequests",
                keyColumn: "AdoptionRequestId",
                keyValue: 4);

            migrationBuilder.DeleteData(
                table: "AdoptionRequests",
                keyColumn: "AdoptionRequestId",
                keyValue: 5);

            migrationBuilder.DeleteData(
                table: "AdoptionRequests",
                keyColumn: "AdoptionRequestId",
                keyValue: 6);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 1);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 2);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 3);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 4);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 6);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 7);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 9);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 10);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 11);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 13);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 14);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 16);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 17);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 19);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 20);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 21);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 23);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 5);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 8);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 12);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 15);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 18);

            migrationBuilder.DeleteData(
                table: "Aliens",
                keyColumn: "AlienId",
                keyValue: 22);
        }
    }
}
