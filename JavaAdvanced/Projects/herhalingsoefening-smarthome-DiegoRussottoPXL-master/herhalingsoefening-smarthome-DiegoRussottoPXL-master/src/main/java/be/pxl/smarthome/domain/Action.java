package be.pxl.smarthome.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "actions")
public class Action {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name = "scenario_id")
	private Scenario scenario;
	@OneToOne
	private Device device;
	private DeviceState deviceState;
	private String settings;

	public void setDevice(Device device) {
		this.device = device;
	}

	public void setDeviceState(DeviceState deviceState) {
		this.deviceState = deviceState;
	}

	public DeviceState getDeviceState() {
		return deviceState;
	}

	public Device getDevice() {
		return device;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

	public String getSettings() {
		return settings;
	}

	public void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}
}
