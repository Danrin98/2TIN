package be.pxl.smarthome.api.dto;

import java.util.List;

public class RoomDTO {
	private String name;
	private List<DeviceDTO> devices;

	public RoomDTO(String name, List<DeviceDTO> devices) {
		this.name = name;
		this.devices = devices;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<DeviceDTO> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceDTO> devices) {
		this.devices = devices;
	}
}
