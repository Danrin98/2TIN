package be.pxl.birdwatchingapi_pe.service;

import be.pxl.birdwatchingapi_pe.domain.FavoriteSpecies;
import be.pxl.birdwatchingapi_pe.api.FavoriteResponse;
import be.pxl.birdwatchingapi_pe.api.FavoriteRequest;
import be.pxl.birdwatchingapi_pe.repository.FavoriteSpeciesRepository;
import be.pxl.birdwatchingapi_pe.repository.UserRepository;
import be.pxl.birdwatchingapi_pe.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteSpeciesService {

    private final FavoriteSpeciesRepository favoriteSpeciesRepository;
    private final UserRepository userRepository;
    private final EBirdService eBirdService;

    public FavoriteSpeciesService(FavoriteSpeciesRepository favoriteSpeciesRepository, UserRepository userRepository, EBirdService eBirdService) {
        this.favoriteSpeciesRepository = favoriteSpeciesRepository;
        this.userRepository = userRepository;
        this.eBirdService = eBirdService;
    }

    public FavoriteResponse addFavorite(FavoriteRequest request, String email) {
        User user = userRepository.findByEmail(email);

        eBirdService.getFamilyCode(request.getSpeciesCode());

        if(favoriteSpeciesRepository.existsBySpeciesCodeAndUser(request.getSpeciesCode(), user)) {
            throw new IllegalArgumentException("Species already favorited.");
        }

        FavoriteSpecies saved = favoriteSpeciesRepository.save(new FavoriteSpecies(request.getSpeciesCode(), user));
        return new FavoriteResponse(saved.getSpeciesCode());
    }

    public List<FavoriteResponse> getFavorites(String email) {
        User user = userRepository.findByEmail(email);
        return favoriteSpeciesRepository.findAllByUser(user)
                .stream()
                .map(f -> new FavoriteResponse(f.getSpeciesCode()))
                .toList();
    }

    @Transactional
    public void deleteFavorite(String speciesCode, String email) {
        User user = userRepository.findByEmail(email);

        if (!favoriteSpeciesRepository.existsBySpeciesCodeAndUser(speciesCode, user)) {
            throw new IllegalArgumentException("You don't have this species as favorite.");
        }

        favoriteSpeciesRepository.deleteBySpeciesCodeAndUser(speciesCode, user);
    }
}
