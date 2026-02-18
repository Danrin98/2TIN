package be.pxl.superhero.api;

public class SuperheroMissionRequest {
    private long superheroId;
    private long missionId;

    public SuperheroMissionRequest(long missionId, long superheroId) {
        this.missionId = missionId;
        this.superheroId = superheroId;
    }

    public SuperheroMissionRequest() {
    }

    public long getSuperheroId() {
        return superheroId;
    }

    public void setSuperheroId(long superheroId) {
        this.superheroId = superheroId;
    }

    public long getMissionId() {
        return missionId;
    }

    public void setMissionId(long missionId) {
        this.missionId = missionId;
    }
}
