package be.pxl.smarthome.api;

import be.pxl.smarthome.api.dto.DeviceDTO;
import be.pxl.smarthome.api.dto.RoomDTO;
import be.pxl.smarthome.domain.Device;
import be.pxl.smarthome.domain.DeviceType;
import be.pxl.smarthome.exception.NotFoundException;
import be.pxl.smarthome.repository.DeviceRepository;
import be.pxl.smarthome.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "devices")
public class DeviceController {
	private final DeviceService deviceService;
    private final DeviceRepository deviceRepository;

	public DeviceController(DeviceService deviceService, DeviceRepository deviceRepository) {
		this.deviceService = deviceService;
        this.deviceRepository = deviceRepository;
	}

	@GetMapping
	public List<DeviceDTO> getAllDevices() {
		return deviceService.getAllDevices();
	}

    @PostMapping("/{deviceId}/toggle")
    public ResponseEntity<Void> toggleDeviceStatus(@PathVariable Integer deviceId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new NotFoundException("Device not found"));

        device.toggle();
        deviceRepository.save(device);

        return ResponseEntity.ok().build();
    }
}
