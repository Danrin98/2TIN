package be.pxl.smarthome.domain;

import jakarta.persistence.*;

@Entity
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    @ManyToOne
	private Scenario scenario;
    @ManyToOne
	private Device device;
    @Enumerated(value = EnumType.STRING)
	private DeviceState deviceState;
	private String settings;

    public Action() {}

    public Action(Scenario scenario, Device device, DeviceState deviceState, String settings) {
        this.scenario = scenario;
        this.device = device;
        this.deviceState = deviceState;
        this.settings = settings;
    }

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
