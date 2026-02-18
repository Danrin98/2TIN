package be.pxl.activity.exception;


import be.pxl.activity.api.request.ActivityRequest;

import java.util.Objects;

public class ActivityRequestValidator {
    public static void validate(ActivityRequest activityRequest) {
        if (activityRequest.getActivity().isEmpty()) {
            throw new IllegalArgumentException("Activity is required and cannot be null or empty.");
        }
        if (activityRequest.getDistance() == null) {
            throw new IllegalArgumentException("Distance is required and cannot be null or empty.");
        }
        if (activityRequest.getEnd() == null) {
            throw new IllegalArgumentException("End is required and cannot be null or empty.");
        }
        if (activityRequest.getStart() == null) {
            throw new IllegalArgumentException("Start is required and cannot be null or empty.");
        }

    }
}
