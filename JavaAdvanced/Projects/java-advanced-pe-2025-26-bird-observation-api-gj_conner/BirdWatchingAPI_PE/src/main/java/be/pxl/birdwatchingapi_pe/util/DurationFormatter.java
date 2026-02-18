package be.pxl.birdwatchingapi_pe.util;

import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class DurationFormatter {

    public String format(Duration duration) {
        if (duration == null || duration.isZero() || duration.isNegative()) {
            return "0 seconds";
        }

        long totalSeconds = duration.getSeconds();

        long days = totalSeconds / 86400;
        totalSeconds %= 86400;

        long hours = totalSeconds / 3600;
        totalSeconds %= 3600;

        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;

        StringBuilder result = new StringBuilder();

        if (days > 0) {
            result.append(days).append(days == 1 ? " day " : " days ");
        }
        if (hours > 0) {
            result.append(hours).append(hours == 1 ? " hour " : " hours ");
        }
        if (minutes > 0) {
            result.append(minutes).append(minutes == 1 ? " minute " : " minutes ");
        }
        if (seconds > 0) {
            result.append(seconds).append(seconds == 1 ? " second" : " seconds");
        }

        return result.toString().trim();
    }
}
