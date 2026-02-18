package be.pxl.smarthome.service;

import be.pxl.smarthome.api.request.ActionRequest;
import be.pxl.smarthome.domain.Action;
import be.pxl.smarthome.domain.Device;
import be.pxl.smarthome.domain.DeviceState;
import be.pxl.smarthome.domain.Scenario;
import be.pxl.smarthome.exception.DeviceUnavailableException;
import be.pxl.smarthome.exception.DuplicateScenarioException;
import be.pxl.smarthome.exception.InvalidDeviceStateException;
import be.pxl.smarthome.exception.WrongDeviceException;
import be.pxl.smarthome.repository.DeviceRepository;
import be.pxl.smarthome.repository.ScenarioRepository;
import be.pxl.smarthome.thirdparty.DummySmartHomeBridge;
import be.pxl.smarthome.thirdparty.SmartHomeBridge;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ScenarioService {
	// TODO: add ScenarioRepository as a dependency
	private final ScenarioRepository scenarioRepository;
	private final DeviceRepository deviceRepository;
	private final SmartHomeBridge smartHomeBridge;

	public ScenarioService(DeviceRepository deviceRepository, DummySmartHomeBridge deviceBridge, ScenarioRepository scenarioRepository) {
		this.deviceRepository = deviceRepository;
		this.smartHomeBridge = deviceBridge;
		this.scenarioRepository = scenarioRepository;
	}

	public void addAction(Integer scenarioId, ActionRequest actionRequest) {
		Scenario scenario = null; // TODO: retrieve scenario with scenarioId
		Device device = null; // TODO: retrieve device by actionRequest.deviceId()
		Action action = new Action();
		action.setDevice(device);
		action.setDeviceState(actionRequest.deviceState());
		action.setSettings(actionRequest.settings());
		// TODO: add new action to the scenario and save scenario
		// REMARK: make sure the bi-directional relationship between scenario and action is maintained correctly
	}

	public void triggers(Integer scenarioId) {
		Scenario scenario = null; // TODO: retrieve scenario with scenarioId
		List<Action> actions = scenario.getActions();
		// check all sensors
		actions.stream().filter(a -> a.getDevice().isSensor()).forEach(this::checkSensor);
		// trigger all not-sensor devices
		actions.stream().filter(a -> !a.getDevice().isSensor()).forEach(this::trigger);
	}

	/**
	 * If the device in the action is a sensor, check if the sensor has the desired status.
	 * If the device hasn't the desired state, an InvalidDeviceStateException is thrown.
	 *
	 * @param action the action to be checked
	 */
	public void checkSensor(Action action) {
		Device device = action.getDevice();
		if (!device.isSensor()) {
			throw new WrongDeviceException("Only devices with type sensor can be checked.");
		}
		if (device.getDeviceState() != action.getDeviceState()) {
			throw new InvalidDeviceStateException(device.getName() + " is not in state " + action.getDeviceState());
		}
	}

	/**
	 * If the action's device is a sensor, the WrongDeviceExcepton is thrown.
	 * If the action's device is not a sensor, the smartHomeBridge is used to send the new state and settings to device.
	 * The new state and settings are saved in the database.
	 * The smartHomeBridge throws a DeviceUnavailableException when the device is not available. In that case the state of the
	 * device is set to UNAVAILABLE.
	 * @param action the action to be triggered
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public void trigger(Action action) {
		Device device = action.getDevice();
		if (device.isSensor()) {
			throw new WrongDeviceException("Devices with type sensor cannot be triggered.");
		}
		try {
			smartHomeBridge.setState(device.getAddress(), action.getDeviceState(), action.getSettings());
			device.setDeviceState(action.getDeviceState());
			device.setSettings(action.getSettings());
		} catch (DeviceUnavailableException e) {
			device.setDeviceState(DeviceState.UNAVAILABLE);
		}
		deviceRepository.save(device);
	}

	@Transactional
	public Scenario createScenario(String name) {
		if (Objects.isNull(name) || name.isBlank()) {
			throw new IllegalArgumentException("Scenario name is required.");
		}

		if (scenarioRepository.equals(name)) {
			throw new DuplicateScenarioException("A scenario with the same name already exists.");
		}

		Scenario scenario = new Scenario();
		scenario.setName(name);

		return scenarioRepository.save(scenario);
	}
}
