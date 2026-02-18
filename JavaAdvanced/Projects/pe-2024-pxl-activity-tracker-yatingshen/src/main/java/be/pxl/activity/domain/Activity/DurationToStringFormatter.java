package be.pxl.activity.domain.Activity;

import java.time.Duration;

public class DurationToStringFormatter {

    private Duration duration;

    public DurationToStringFormatter(Duration duration){
        this.duration = duration;
    }

    public String getDuration(){
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        StringBuilder result = new StringBuilder();

        if (hours > 0) {
            result.append(hours).append(" hour");
            if (hours > 1) {
                result.append("s");
            }
        }
        if (minutes > 0) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(minutes).append(" minute");
            if (minutes > 1) {
                result.append("s");
            }
        }
        if (seconds > 0 || result.length() == 0) {
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(seconds).append(" second");
            if (seconds != 1) {
                result.append("s");
            }
        }

        return result.toString();
    }

}
