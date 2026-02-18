package be.pxl.activity.domain.User;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserDetailsTest {
    @Test
    void testGetAge_WhenDateOfBirthIsInPast() {
        // Arrange
        UserDetails userDetails = new UserDetails();
        userDetails.setDate_of_birth(LocalDate.of(2000, 12, 15)); // 24 years old as of 2024-12-15

        // Act
        int age = userDetails.getAge();

        // Assert
        assertEquals(24, age, "Age should be 24 for a user born on 2000-12-15.");
    }

    @Test
    void testGetAge_WhenDateOfBirthIsToday() {
        // Arrange
        UserDetails userDetails = new UserDetails();
        userDetails.setDate_of_birth(LocalDate.now());

        // Act
        int age = userDetails.getAge();

        // Assert
        assertEquals(0, age, "Age should be 0 for a user born today.");
    }

    @Test
    void testGetAge_WhenDateOfBirthIsNull() {
        // Arrange
        UserDetails userDetails = new UserDetails();

        // Act & Assert
        Exception exception = assertThrows(IllegalStateException.class, userDetails::getAge);
        assertEquals("Date of birth is not set.", exception.getMessage());
    }

    @Test
    void testGetAge_WhenDateOfBirthIsInFuture() {
        // Arrange
        UserDetails userDetails = new UserDetails();
        userDetails.setDate_of_birth(LocalDate.of(2030, 1, 1)); // A future date

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, userDetails::getAge);
        assertEquals("Date of birth cannot be in the future.", exception.getMessage());
    }
}
