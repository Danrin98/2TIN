package be.pxl.smarthome.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Scenario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
    @OneToMany(mappedBy = "scenario",  cascade = CascadeType.ALL)
    private List<Action> actions = new ArrayList<>();

    public Scenario() {}

    public Scenario(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

	public Integer getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Action> getActions() {
		// TODO: implement this method when field actions is available
		return actions;
	}
}
