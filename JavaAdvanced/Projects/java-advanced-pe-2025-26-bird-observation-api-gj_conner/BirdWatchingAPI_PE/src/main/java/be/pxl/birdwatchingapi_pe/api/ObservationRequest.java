package be.pxl.birdwatchingapi_pe.api;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class ObservationRequest {
    @NotBlank(message = "speciesCode cannot be empty")
    private String speciesCode;
    @NotBlank(message = "Location cannot be empty")
    private String Location;
    private LocalDateTime timeStamp;
    private String note;

    public ObservationRequest() {}

    public ObservationRequest(String speciesCode, String Location, LocalDateTime timeStamp, String note) {
        this.speciesCode = speciesCode;
        this.Location = Location;
        this.timeStamp = timeStamp;
        this.note = note;
    }

    public String getSpeciesCode() {
        return speciesCode;
    }

    public void setSpeciesCode(String speciesCode) {
        this.speciesCode = speciesCode;
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
