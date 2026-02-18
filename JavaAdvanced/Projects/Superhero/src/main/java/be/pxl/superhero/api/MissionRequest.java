package be.pxl.superhero.api;

public class MissionRequest {
    private String name;

    public MissionRequest() {
    }

    public MissionRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
