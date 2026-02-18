package be.pxl.activity.exception;

import be.pxl.activity.api.request.UserRequest;

public class UserRequestValidator {
    public static void validate(UserRequest userRequest) {
        if (userRequest.getEmail() == null || userRequest.getEmail().isEmpty()) {
            throw new IllegalArgumentException("Email is required and cannot be null or empty.");
        }
        if (userRequest.getName() == null || userRequest.getName().isEmpty()) {
            throw new IllegalArgumentException("Name is required and cannot be null or empty.");
        }
        if (userRequest.getPassword() == null || userRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password is required and cannot be null or empty.");
        }

    }
}
