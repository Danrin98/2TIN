package be.pxl.cargo.api.request;

import be.pxl.cargo.domain.Location;

public record CreateCargoRequest(String code, double weight, Location origin, Location destination) {
}
