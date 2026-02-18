package be.pxl.dice.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class DiceSetTest {
    @Test
    void constructorRejectsTooFewDice() {
        assertThrows(IllegalArgumentException.class, () -> new DiceSet(6, 1));
    }

    @Test
    void descriptorIsCorrect() {
        DiceSet set = new DiceSet(5, 6);
        assertEquals("6d5", set.getDescriptor());
    }

    @Test
    void sumReturnsCorrectTotal() {
        DiceSet set = new DiceSet(6, 3);
        set.setIndividual(0, 1);
        set.setIndividual(1, 2);
        set.setIndividual(2, 3);
        assertEquals(6, set.sum());
    }

    @Test
    void valuesReturnsAllValues() {
        DiceSet set = new DiceSet(6, 2);
        set.setIndividual(0, 4);
        set.setIndividual(1, 6);

        List<Integer> values = set.values();
        assertEquals(List.of(4, 6), values);
    }

    @Test
    void toStringConcatenatesDice() {
        DiceSet set = new DiceSet(6, 2);
        set.setIndividual(0, 2);
        set.setIndividual(1, 5);
        assertEquals("[2][5]", set.toString());
    }

    @Test
    void rollIndividualOnlyChangesOneDie() {
        DiceSet set = new DiceSet(6, 2);
        set.setIndividual(0, 1);
        set.setIndividual(1, 1);

        set.rollIndividual(0);

        assertEquals(1, set.getIndividual(1));
    }
}
