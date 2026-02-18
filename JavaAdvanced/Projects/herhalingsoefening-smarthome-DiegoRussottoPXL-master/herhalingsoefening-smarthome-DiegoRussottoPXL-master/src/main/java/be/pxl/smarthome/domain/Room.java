package be.pxl.smarthome.domain;

import jakarta.persistence.*;

@Entity
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;

	public Room(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Room() {
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
