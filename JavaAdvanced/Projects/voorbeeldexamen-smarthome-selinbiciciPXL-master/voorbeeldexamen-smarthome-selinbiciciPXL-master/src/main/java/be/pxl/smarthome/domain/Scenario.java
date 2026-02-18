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

	@OneToMany(mappedBy = "scenario")
	private List<Action> actions = new ArrayList<>();

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


	//zie service
	public void addAction(Action action) {
		actions.add(action);
	}
}
