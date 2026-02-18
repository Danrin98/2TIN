package be.pxl.birdwatchingapi_pe.api;

public class FavoriteResponse {
    private String speciesCode;

    public FavoriteResponse(String speciesCode) {
        this.speciesCode = speciesCode;
    }

    public String getSpeciesCode() {
        return speciesCode;
    }
}

