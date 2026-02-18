package be.pxl.birdwatchingapi_pe.api;

import jakarta.validation.constraints.NotBlank;

public class FavoriteRequest {
    @NotBlank(message = "speciesCode cannot be empty")
    private String speciesCode;

    public String getSpeciesCode() {
        return speciesCode;
    }
}

