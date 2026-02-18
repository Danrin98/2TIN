package be.pxl.activity.exception;

import be.pxl.activity.api.request.UserDetailRequest;

public class UserDetailRequestValidator {
    public static void validate(UserDetailRequest userDetailRequest) {

        if (userDetailRequest.getHeight_cm()== 0) {
            throw new IllegalArgumentException("Height is required and cannot be null or empty.");
        }
        if (userDetailRequest.getDate_of_birth() == null) {
            throw new IllegalArgumentException("Date_of_birth is required and cannot be null or empty.");
        }
        if (userDetailRequest.getWeight_kg() == 0) {
            throw new IllegalArgumentException("Weight is required and cannot be null or empty.");
        }
    }
}
