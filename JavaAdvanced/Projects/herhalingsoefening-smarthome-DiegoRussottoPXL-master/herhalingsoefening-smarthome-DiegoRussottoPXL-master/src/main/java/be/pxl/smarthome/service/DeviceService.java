package be.pxl.smarthome.service;

import be.pxl.smarthome.api.dto.DeviceDTO;
import be.pxl.smarthome.api.dto.RoomDTO;
import be.pxl.smarthome.domain.Device;
import be.pxl.smarthome.domain.DeviceType;
import be.pxl.smarthome.exception.NotFoundException;
import be.pxl.smarthome.repository.DeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DeviceService {

	private final DeviceRepository deviceRepository;

	public DeviceService(DeviceRepository deviceRepository) {
		this.deviceRepository = deviceRepository;
	}

	public List<DeviceDTO> getAllDevices() {
		return deviceRepository.findAll().stream()
				.map(d -> new DeviceDTO(d.getId(), d.getName(), d.getDeviceType(), d.getRoom().getName(), d.getDeviceState()))
				.toList();
	}

	public List<RoomDTO> getAllDevicesByDeviceType(DeviceType deviceType) {
		// TODO implement the query in DeviceRepository, call the query and return the result.
		return null;
	}

	@Transactional
	public void toggleDevice(int deviceId) {
		Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new NotFoundException("No device with id [" + deviceId + "]"));
		device.toggle();
	}
}
