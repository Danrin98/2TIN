package be.pxl.smarthome.service;

import be.pxl.smarthome.builders.ActionBuilder;
import be.pxl.smarthome.builders.DeviceBuilder;
import be.pxl.smarthome.builders.ScenarioBuilder;
import be.pxl.smarthome.domain.*;
import be.pxl.smarthome.exception.NotFoundException;
import be.pxl.smarthome.repository.DeviceRepository;
import be.pxl.smarthome.repository.ScenarioRepository;
import be.pxl.smarthome.thirdparty.DummySmartHomeBridge;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ScenarioServiceTest {

    @Mock
    private DummySmartHomeBridge smartHomeBridge;

    @Mock
    private DeviceRepository deviceRepository;
    @Mock
    private ScenarioRepository scenarioRepository;

    @InjectMocks
    private ScenarioService scenarioService;

	private static final UUID THERMOSTAT_UUID = UUID.randomUUID();
    private final Action actionWithSensor = ActionBuilder.anAction()
            .withDevice(DeviceBuilder.aDevice().withDeviceType(DeviceType.DOOR_WINDOW_CONTACT).build())
            .build();
    private final Device thermostat = DeviceBuilder.aDevice()
            .withAddress(THERMOSTAT_UUID)
            .withDeviceType(DeviceType.THERMOSTAT)
            .withDeviceState(DeviceState.OFF)
            .build();
    private final Action actionWithThermostat = ActionBuilder.anAction()
                .withDevice(thermostat)
                .withSettings("{'temp':18.5}")
                .withDeviceState(DeviceState.ON)
                .build();

    private final Scenario scenario = ScenarioBuilder.aScenario()
            .withName("Leaving Kitchen")
            .withActions(List.of(actionWithSensor, actionWithThermostat))
            .build();

    @Test
    public void throwsScenarioNotFoundExceptionWhenScenarioIdDoesntExist() {
        when(scenarioRepository.findById(scenario.getId()))
                .thenReturn(Optional.empty());
        NotFoundException notFoundException = assertThrows(NotFoundException.class,
                () -> scenarioService.triggers(scenario.getId()));
    }
}
