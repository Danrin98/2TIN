package be.pxl.activity.domain.Activity;

import java.time.Duration;

public class StringToDurationFormatter {

    private String durationString;

    public StringToDurationFormatter(String durationString){
        this.durationString = durationString;
    }

    public Duration parse(){
        String[] parts = durationString.split("\\s+");
        long hours = 0, minutes = 0, seconds = 0;

        for (int i = 0; i < parts.length; i++) {
            switch (parts[i]) {
                case "hour":
                case "hours":
                    hours = Long.parseLong(parts[i - 1]);
                    break;
                case "minute":
                case "minutes":
                    minutes = Long.parseLong(parts[i - 1]);
                    break;
                case "second":
                case "seconds":
                    seconds = Long.parseLong(parts[i - 1]);
                    break;
            }
        }
        return Duration.ofHours(hours).plusMinutes(minutes).plusSeconds(seconds);
    }

}
