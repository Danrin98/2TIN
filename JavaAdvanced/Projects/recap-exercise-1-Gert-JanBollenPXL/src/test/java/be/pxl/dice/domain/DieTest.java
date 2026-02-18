package be.pxl.dice.domain;

import be.pxl.dice.exception.InvalidDieValue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DieTest {
    @Test
    void constructorRejectsTooFewSides() {
        assertThrows(IllegalArgumentException.class, () -> new Die(3));
    }

    @Test
    void rollProducesValueWithinRange() {
        Die die = new Die(6);
        for (int i = 0; i < 100; i++) {
            die.roll();
            assertTrue(die.getValue() >= 1 && die.getValue() <= 6);
        }
    }

    @Test
    void setValueAcceptsValidValue() {
        Die die = new Die(6);
        die.setValue(5);
        assertEquals(5, die.getValue());
    }

    @Test
    void setValueRejectsInvalidValue() {
        Die die = new Die(6);
        assertThrows(InvalidDieValue.class, () -> die.setValue(7));
    }

    @Test
    void toStringFormatIsCorrect() {
        Die die = new Die(6);
        die.setValue(3);
        assertEquals("[3]", die.toString());
    }
}
