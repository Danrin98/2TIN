package be.pxl.smarthome.api.request;

import jakarta.validation.constraints.NotNull;

//De naam van het scenario is verplicht.
//Geef httpstatus 400 (Bad Request) als dit niet is ingevuld.
public record ScenarioRequest(@NotNull String name) {
}
