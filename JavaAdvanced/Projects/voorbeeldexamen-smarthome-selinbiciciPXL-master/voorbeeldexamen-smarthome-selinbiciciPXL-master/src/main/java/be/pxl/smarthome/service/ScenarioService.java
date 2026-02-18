package be.pxl.smarthome.service;

import be.pxl.smarthome.api.request.ActionRequest;
import be.pxl.smarthome.api.request.ScenarioRequest;
import be.pxl.smarthome.domain.Action;
import be.pxl.smarthome.domain.Device;
import be.pxl.smarthome.domain.DeviceState;
import be.pxl.smarthome.domain.Scenario;
import be.pxl.smarthome.exception.*;
import be.pxl.smarthome.repository.DeviceRepository;
import be.pxl.smarthome.repository.ScenarioRepository;
import be.pxl.smarthome.thirdparty.DummySmartHomeBridge;
import be.pxl.smarthome.thirdparty.SmartHomeBridge;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ScenarioService {
	// TODO: add ScenarioRepository as a dependency
	private final DeviceRepository deviceRepository;
	private final ScenarioRepository scenarioRepository;
	private final SmartHomeBridge smartHomeBridge;

	public ScenarioService(DeviceRepository deviceRepository,
						   ScenarioRepository scenarioRepository, DummySmartHomeBridge deviceBridge) {
		this.deviceRepository = deviceRepository;
		this.scenarioRepository = scenarioRepository;
		this.smartHomeBridge = deviceBridge;
	}

	public void createScenario(ScenarioRequest scenarioRequest) {
		if (!scenarioRepository.findByName(scenarioRequest.name()).equals(Optional.empty())) {
			throw new DuplicateScenarioException("The scenario with name '" + scenarioRequest.name() + "' already exists.");
		}
		//Als er al een scenario bestaat met (exact dezelfde) opgegeven naam, dan
		//wordt een DuplicateScenarioException opgegooid. Zorg dat deze exception
		//httpstatus 409 (Conflict) geeft. Er wordt geen scenario opgeslagen in de
		//databank.
		Scenario scenario = new Scenario();
		scenario.setName(scenarioRequest.name());
		scenarioRepository.save(scenario);
	}

	//PUT TOEVOEGEN ACTION AAN SCENARIO
	public void addAction(Integer scenarioId, ActionRequest actionRequest) {
		// TODO: retrieve scenario with scenarioId
		Scenario scenario = scenarioRepository.findById(scenarioId).orElseThrow(() -> new NotFoundException("The scenario with id [" + scenarioId + "] doesn't exist."));

		// TODO: retrieve device by actionRequest.deviceId()
		Device device = deviceRepository.findById(actionRequest.deviceId()).orElseThrow(() -> new NotFoundException("The device with id [" + actionRequest.deviceId() + "] doesn't exist."));
		Action action = new Action();
		action.setDevice(device);
		action.setDeviceState(actionRequest.deviceState());
		action.setSettings(actionRequest.settings());
		//Als het scenario en het device bestaan, dan wordt een nieuw Action-object aangemaakt en opgeslagen in de databank.
		// TODO: add new action to the scenario and save scenario
		// REMARK: make sure the bi-directional relationship between scenario and action is maintained correctly
		scenario.addAction(action);
		scenarioRepository.save(scenario);
	}

	public void triggers(Integer scenarioId) {
		// TODO: retrieve scenario with scenarioId
		Scenario scenario = scenarioRepository.findById(scenarioId)
				.orElseThrow(() -> new NotFoundException("The scenario with id [" + scenarioId + "] doesn't exist."));

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
		//Als de sensor niet de gewenste status heeft, dan worden
		//de acties niet verder uitgevoerd en wordt de InvalidDeviceStateException
		//opgegooid die resulteert in httpstatus 406 (NOT_ACCEPTABLE).

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



}
