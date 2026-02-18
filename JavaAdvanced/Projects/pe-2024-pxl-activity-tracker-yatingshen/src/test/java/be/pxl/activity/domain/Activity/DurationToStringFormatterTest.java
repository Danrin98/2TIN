package be.pxl.activity.domain.Activity;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import be.pxl.activity.domain.Activity.DurationToStringFormatter;

public class DurationToStringFormatterTest {
    DurationToStringFormatter durationToStringFormatter;
    @Test
    void testFormat_OnlySeconds() {
        Duration duration = Duration.ofSeconds(45);
        durationToStringFormatter = new DurationToStringFormatter(duration);
        String result = durationToStringFormatter.getDuration();

        assertEquals("45 seconds", result);
    }

    @Test
    void testFormat_OnlyMinutes() {
        Duration duration = Duration.ofMinutes(15);
        durationToStringFormatter = new DurationToStringFormatter(duration);
        String result = durationToStringFormatter.getDuration();

        assertEquals("15 minutes", result);
    }

    @Test
    void testFormat_OnlyHours() {
        Duration duration = Duration.ofHours(2);
        durationToStringFormatter = new DurationToStringFormatter(duration);
        String result = durationToStringFormatter.getDuration();

        assertEquals("2 hours", result);
    }

    @Test
    void testFormat_HoursMinutesSeconds() {
        Duration duration = Duration.ofHours(1).plusMinutes(20).plusSeconds(5);
        durationToStringFormatter = new DurationToStringFormatter(duration);
        String result = durationToStringFormatter.getDuration();

        assertEquals("1 hour 20 minutes 5 seconds", result);
    }

    @Test
    void testFormat_MinutesAndSeconds() {
        Duration duration = Duration.ofMinutes(10).plusSeconds(3);
        durationToStringFormatter = new DurationToStringFormatter(duration);
        String result = durationToStringFormatter.getDuration();

        assertEquals("10 minutes 3 seconds", result);
    }

    @Test
    void testFormat_HoursAndSeconds() {
        Duration duration = Duration.ofHours(1).plusSeconds(30);
        durationToStringFormatter = new DurationToStringFormatter(duration);
        String result = durationToStringFormatter.getDuration();

        assertEquals("1 hour 30 seconds", result);
    }

    @Test
    void testFormat_ZeroDuration() {
        Duration duration = Duration.ZERO;
        durationToStringFormatter = new DurationToStringFormatter(duration);
        String result = durationToStringFormatter.getDuration();

        assertEquals("0 seconds", result);
    }

    @Test
    void testFormat_SingleUnits() {
        Duration duration = Duration.ofHours(1).plusMinutes(1).plusSeconds(1);
        durationToStringFormatter = new DurationToStringFormatter(duration);
        String result = durationToStringFormatter.getDuration();

        assertEquals("1 hour 1 minute 1 second", result);
    }
}
