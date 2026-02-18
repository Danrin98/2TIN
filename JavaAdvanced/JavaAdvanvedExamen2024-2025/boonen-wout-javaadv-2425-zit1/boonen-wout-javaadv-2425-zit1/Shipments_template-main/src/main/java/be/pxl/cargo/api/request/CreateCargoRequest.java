package be.pxl.cargo.api.request;

import be.pxl.cargo.domain.Location;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

public record CreateCargoRequest(@NotEmpty String code, @Min(100) double weight, Location origin, Location destination) {
}
