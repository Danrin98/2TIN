package be.pxl.birdwatchingapi_pe.api;

public class FamilyObservationsDto {
    private String familyCode;
    private long numberOfObservations;

    public FamilyObservationsDto(String familyCode, long numberOfObservations) {
        this.familyCode = familyCode;
        this.numberOfObservations = numberOfObservations;
    }

    public String getFamilyCode() {
        return familyCode;
    }

    public void setFamilyCode(String familyCode) {
        this.familyCode = familyCode;
    }

    public long getNumberOfObservations() {
        return numberOfObservations;
    }

    public void setNumberOfObservations(long numberOfObservations) {
        this.numberOfObservations = numberOfObservations;
    }
}
