package be.pxl.smarthome.repository;

import be.pxl.smarthome.api.dto.RoomDTO;
import be.pxl.smarthome.domain.Device;
import be.pxl.smarthome.domain.DeviceType;
import be.pxl.smarthome.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {


   List<RoomDTO> findAllByDeviceType(DeviceType deviceType);
}

