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

	//Voorzie in de repository DeviceRepository een methode (query) om alle
	//Devices met het opgegeven DeviceType op te halen uit de databank. Roep deze
	//methode aan in de methode ‘List<RoomDTO>
	//getAllDevicesByDeviceType(DeviceType deviceType)’ in de DeviceService en geef
	//het resultaat terug. Het resultaat bevat de verschillende kamers en per kamer de
	//verschillende devices.

	public List<RoomDTO> getAllDevicesByDeviceType(DeviceType deviceType) {
		// TODO implement the query in DeviceRepository, call the query and return the result.
		return deviceRepository.findAllByDeviceType(deviceType).stream().map(this::mapToRoomDTO).toList();
	}

	@Transactional
	public void toggleDevice(int deviceId) {
		Device device = deviceRepository.findById(deviceId).orElseThrow(() -> new NotFoundException("No device with id [" + deviceId + "]"));
		device.toggle();
	}

	//call the query from device dto
	private RoomDTO mapToRoomDTO(RoomDTO room) {
		return new RoomDTO(room.getName(), room.getDevices());
	}
}
