package be.pxl.birdwatchingapi_pe.repository;

import be.pxl.birdwatchingapi_pe.domain.FavoriteSpecies;
import be.pxl.birdwatchingapi_pe.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteSpeciesRepository extends JpaRepository<FavoriteSpecies, Long> {
    List<FavoriteSpecies> findAllByUser(User user);
    boolean existsBySpeciesCodeAndUser(String speciesCode, User user);
    void deleteBySpeciesCodeAndUser(String speciesCode, User user);
}

