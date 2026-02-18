package be.pxl.musicplaylist.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SongTest {

    Song song;
    @BeforeEach
    void setUp() {
        song = new Song(
                "Title",
                "Artist",
                0,
                Genre.POP
        );
    }

    @Test
    public void it_should_return_SHORT_when_durationseconds_smaller_than_180() {
        // Arrange
        song.setDurationSeconds(60);
        // Act
        String durationCategory = song.getDurationCategory();
        // Assert
        assertEquals("SHORT", durationCategory, "Duration category should be SHORT");
    }

    @Test
    public void it_should_return_LONG_when_durationseconds_bigger_than_180() {
        // Arrange
        song.setDurationSeconds(200);
        // Act
        String durationCategory = song.getDurationCategory();
        // Assert
        assertEquals("LONG", durationCategory, "Duration category should be LONG");
    }

}