using AlienAdoptionAgency.Domain;

namespace AlienAdoptionAgency.AppLogic
{
    public interface IAdoptionRepository
    {
        void AddAdoptionRequest(AdoptionRequest request);
        public IList<AdoptionRequest> GetAllAdoptionRequests();
    }
}
