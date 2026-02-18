package be.pxl.birdwatchingapi_pe.api;

import java.time.LocalDateTime;

public class ObservationResponse {
    private int observationId;
    private String speciesCode;
    private String familyCode;
    private String Location;
    private LocalDateTime timeStamp;
    private String note;

    public ObservationResponse() {
    }

    public ObservationResponse(int observationId,  String speciesCode, String familyCode, String Location, LocalDateTime timeStamp, String note) {
        this.observationId = observationId;
        this.speciesCode = speciesCode;
        this.familyCode = familyCode;
        this.Location = Location;
        this.timeStamp = timeStamp;
        this.note = note;
    }

    public int getObservationId() {
        return observationId;
    }

    public void setObservationId(int observationId) {
        this.observationId = observationId;
    }

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

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
