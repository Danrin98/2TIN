package be.pxl.delivery.api.request;

import be.pxl.delivery.domain.Location;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateMealRequest(
        @NotBlank(message = "You must have a Code attribute")
        String code,
        @NotBlank(message = "You must have a Name attribute")
        String name,
        @NotNull(message = "You must have an origin: CENTER, NORTH, EAST, SOUTH, WEST")
        Location origin,
        @NotNull(message = "You must have a destination: CENTER, NORTH, EAST, SOUTH, WEST")
        Location destination) {
}
