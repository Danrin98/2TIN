package be.pxl.birdwatchingapi_pe.util;

import org.junit.jupiter.api.Test;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DurationFormatterTest {

    private final DurationFormatter formatter = new DurationFormatter();

    @Test
    void formatZeroDuration() {
        assertEquals("0 seconds", formatter.format(Duration.ZERO));
    }

    @Test
    void formatSecondsOnly() {
        assertEquals("5 seconds", formatter.format(Duration.ofSeconds(5)));
    }

    @Test
    void formatMinutesAndSeconds() {
        assertEquals("15 minutes 1 second", formatter.format(
                Duration.ofMinutes(15).plusSeconds(1)));
    }

    @Test
    void formatHoursMinutesSeconds() {
        assertEquals("1 hour 45 minutes 10 seconds", formatter.format(
                Duration.ofHours(1).plusMinutes(45).plusSeconds(10)));
    }

    @Test
    void formatDaysHoursSeconds() {
        assertEquals("1 day 1 hour 3 seconds", formatter.format(
                Duration.ofDays(1).plusHours(1).plusSeconds(3)));
    }

    @Test
    void formatMultipleDays() {
        assertEquals("2 days 5 hours", formatter.format(
                Duration.ofDays(2).plusHours(5)));
    }
}