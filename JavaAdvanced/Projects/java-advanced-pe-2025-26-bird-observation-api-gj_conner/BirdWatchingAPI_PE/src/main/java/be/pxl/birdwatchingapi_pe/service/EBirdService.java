package be.pxl.birdwatchingapi_pe.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EBirdService {

    public static class EBirdSpecies {

        private String speciesCode;
        private String familyCode;

        public String getSpeciesCode() {
            return speciesCode;
        }

        public void setSpeciesCode(String speciesCode) {
            this.speciesCode = speciesCode;
        }

        public String getFamilyCode() {
            return familyCode;
        }

        public void setFamilyCode(String familyCode) {
            this.familyCode = familyCode;
        }
    }

    private final RestTemplate restTemplate;

    public EBirdService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getFamilyCode(String speciesCode) {

        // Validate input
        if (speciesCode == null || speciesCode.isBlank()) {
            throw new IllegalArgumentException("speciesCode cannot be null or empty.");
        }

        String url = "https://api.ebird.org/v2/ref/taxonomy/ebird?species="
                + speciesCode + "&fmt=json";

        ResponseEntity<EBirdSpecies[]> response =
                restTemplate.getForEntity(url, EBirdSpecies[].class);

        // Validate HTTP status
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Failed to fetch data from eBird API: HTTP "
                    + response.getStatusCode());
        }

        EBirdSpecies[] data = response.getBody();

        // Validate response content
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("No species found for code: " + speciesCode);
        }

        return data[0].getFamilyCode();
    }
}
