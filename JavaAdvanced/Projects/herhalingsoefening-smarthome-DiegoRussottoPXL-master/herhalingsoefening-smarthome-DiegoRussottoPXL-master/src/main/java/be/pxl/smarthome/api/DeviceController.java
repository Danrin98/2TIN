package be.pxl.smarthome.api;

import be.pxl.smarthome.api.dto.DeviceDTO;
import be.pxl.smarthome.service.DeviceService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "devices")
public class DeviceController {
	private final DeviceService deviceService;

	public DeviceController(DeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@GetMapping
	public List<DeviceDTO> getAllDevices() {
		return deviceService.getAllDevices();
	}
}
