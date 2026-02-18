using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace AlienAdoptionAgency.Infrastructure.Migrations
{
    /// <inheritdoc />
    public partial class Initial : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Aliens",
                columns: table => new
                {
                    AlienId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    Name = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    Species = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    Planet = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    SpecialNeeds = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    FavoriteSnack = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    IsAdoptable = table.Column<bool>(type: "bit", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Aliens", x => x.AlienId);
                });

            migrationBuilder.CreateTable(
                name: "AdoptionRequests",
                columns: table => new
                {
                    AdoptionRequestId = table.Column<int>(type: "int", nullable: false)
                        .Annotation("SqlServer:Identity", "1, 1"),
                    RequestDate = table.Column<DateTime>(type: "datetime2", nullable: false),
                    ApplicantName = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    ApplicantEmail = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    ReasonForAdoption = table.Column<string>(type: "nvarchar(max)", nullable: false),
                    AlienId = table.Column<int>(type: "int", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_AdoptionRequests", x => x.AdoptionRequestId);
                    table.ForeignKey(
                        name: "FK_AdoptionRequests_Aliens_AlienId",
                        column: x => x.AlienId,
                        principalTable: "Aliens",
                        principalColumn: "AlienId",
                        onDelete: ReferentialAction.Cascade);
                });

            migrationBuilder.CreateIndex(
                name: "IX_AdoptionRequests_AlienId",
                table: "AdoptionRequests",
                column: "AlienId");
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "AdoptionRequests");

            migrationBuilder.DropTable(
                name: "Aliens");
        }
    }
}
