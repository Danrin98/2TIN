package be.pxl.smarthome.domain;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Device {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@OneToOne
	private Room room;
	private String name;
	private UUID address;

	@Enumerated(value = EnumType.STRING)
	private DeviceType deviceType;

	@Enumerated(value = EnumType.STRING)
	private DeviceState deviceState;
	private String settings;

	public Integer getId() {
		return id;
	}

	public Room getRoom() {
		return room;
	}

	public String getName() {
		return name;
	}

	public UUID getAddress() {
		return address;
	}

	public DeviceState getDeviceState() {
		return deviceState;
	}

	public void setDeviceState(DeviceState deviceState) {
		this.deviceState = deviceState;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public String getSettings() {
		return settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

	public void setAddress(UUID address) {
		this.address = address;
	}

	public boolean isSensor() {
		return deviceType.isSensor();
	}


	public void toggle() {
		deviceState = deviceState.toggle();
	}
}
