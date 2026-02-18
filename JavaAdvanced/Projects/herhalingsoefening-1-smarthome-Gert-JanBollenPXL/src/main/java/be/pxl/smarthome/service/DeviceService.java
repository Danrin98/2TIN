package be.pxl.smarthome.service;

import be.pxl.smarthome.api.dto.DeviceDTO;
import be.pxl.smarthome.api.dto.RoomDTO;
import be.pxl.smarthome.domain.Device;
import be.pxl.smarthome.domain.DeviceType;
import be.pxl.smarthome.domain.Room;
import be.pxl.smarthome.exception.NotFoundException;
import be.pxl.smarthome.repository.DeviceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        List<Device> devices = deviceRepository.findAllByDeviceType(deviceType);

        Map<String, List<DeviceDTO>> devicesPerRoom = devices.stream()
                .map(device -> new DeviceDTO(
                        device.getId(),
                        device.getName(),
                        device.getDeviceType(),
                        device.getRoom().getName(),
                        device.getDeviceState()
                ))
                .collect(Collectors.groupingBy(DeviceDTO::room));

        return devicesPerRoom.entrySet().stream()
                .map(entry -> new RoomDTO(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
	}

	@Transactional
	public void toggleDevice(int deviceId) {
		Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new NotFoundException("No device with id [" + deviceId + "]"));
		device.toggle();
	}
}
