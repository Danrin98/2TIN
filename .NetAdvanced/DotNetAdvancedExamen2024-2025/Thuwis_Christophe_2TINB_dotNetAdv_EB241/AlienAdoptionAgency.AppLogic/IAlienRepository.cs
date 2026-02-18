using AlienAdoptionAgency.Domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace AlienAdoptionAgency.AppLogic
{
    public interface IAlienRepository
    {
        IList<Alien> GetAllAliens(bool onlyAdoptables = false);
        IList<Alien> GetAliensPaged(int pagenumber = 0, int pageSize = 5);
        Alien? GetAlienById(int id);
        int GetTotalAlienCount();

        void AddAlien(Alien alien);

        public void ToggleAlienAdoptionStatus(int id);
    }
}
