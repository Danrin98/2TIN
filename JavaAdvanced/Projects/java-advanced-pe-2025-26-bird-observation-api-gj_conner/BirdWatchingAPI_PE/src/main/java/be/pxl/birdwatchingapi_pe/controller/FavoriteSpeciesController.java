package be.pxl.birdwatchingapi_pe.controller;

import be.pxl.birdwatchingapi_pe.api.FavoriteResponse;
import be.pxl.birdwatchingapi_pe.api.FavoriteRequest;
import be.pxl.birdwatchingapi_pe.service.FavoriteSpeciesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteSpeciesController {

    FavoriteSpeciesService favoriteSpeciesService;
    @Autowired
    public FavoriteSpeciesController(FavoriteSpeciesService favoriteSpeciesService) {
        this.favoriteSpeciesService = favoriteSpeciesService;
    }

    @PostMapping
    public ResponseEntity<?> addFavorite(@RequestBody @Valid FavoriteRequest request, Principal principal) {
        FavoriteResponse response = favoriteSpeciesService.addFavorite(request, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<FavoriteResponse>> getFavorites(Principal principal) {
        return ResponseEntity.status(HttpStatus.OK).body(favoriteSpeciesService.getFavorites(principal.getName()));
    }

    @DeleteMapping("/{speciesCode}")
    public ResponseEntity<?> deleteFavorite(@PathVariable String speciesCode, Principal principal) {
        try {
            favoriteSpeciesService.deleteFavorite(speciesCode, principal.getName());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}
