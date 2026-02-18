package be.pxl.smarthome.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest {
    @Test
    public void toggle_fromOpenToClosed() {
        Device device = new Device();
        device.setDeviceState(DeviceState.OPEN);

        device.toggle();

        assertEquals(DeviceState.CLOSED, device.getDeviceState());
    }

    @Test
    public void toggle_fromClosedToOpen() {
        Device device = new Device();
        device.setDeviceState(DeviceState.CLOSED);

        device.toggle();

        assertEquals(DeviceState.OPEN, device.getDeviceState());
    }
}
