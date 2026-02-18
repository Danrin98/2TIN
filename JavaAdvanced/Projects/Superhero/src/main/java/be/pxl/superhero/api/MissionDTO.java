package be.pxl.superhero.api;

public class MissionDTO {
    private long id;
    private String name;
    private boolean completed;

    public MissionDTO() {
    }

    public MissionDTO(long id, String name, boolean completed) {
        this.id = id;
        this.name = name;
        this.completed = completed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
