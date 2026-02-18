package be.pxl.activity.domain.Activity;

import be.pxl.activity.domain.Activity.Distance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceTest {
    @Test
    void testAdd_BothDistancesInKm() {
        Distance distance1 = new Distance(5, Unit.KM);
        Distance distance2 = new Distance(3, Unit.KM);

        Distance result = distance1.add(distance2);

        assertEquals(8, result.getValue());
        assertEquals(Unit.KM, result.getUnit());
    }

    @Test
    void testAdd_DistancesInDifferentUnits() {
        Distance distance1 = new Distance(5, Unit.KM); // 5 km
        Distance distance2 = new Distance(2000, Unit.M); // 2000 m = 2 km

        Distance result = distance1.add(distance2);

        assertEquals(7, result.getValue());
        assertEquals(Unit.KM, result.getUnit());
    }

    @Test
    void testToUnit_FromKmToM() {
        Distance distance = new Distance(3, Unit.KM);

        Distance result = distance.toUnit(Unit.M);

        assertEquals(3000, result.getValue());
        assertEquals(Unit.M, result.getUnit());
    }

    @Test
    void testToUnit_FromMToKm() {
        Distance distance = new Distance(1500, Unit.M);

        Distance result = distance.toUnit(Unit.KM);

        assertEquals(1.5, result.getValue(), 0.0001); // Tolerantie voor floating-point-afwijkingen
        assertEquals(Unit.KM, result.getUnit());
    }

    @Test
    void testToUnit_SameUnit() {
        Distance distance = new Distance(3, Unit.KM);

        Distance result = distance.toUnit(Unit.KM);

        assertEquals(3, result.getValue());
        assertEquals(Unit.KM, result.getUnit());
    }

    @Test
    void testAdd_WithZeroDistance() {
        Distance distance1 = new Distance(5, Unit.KM);
        Distance distance2 = new Distance(0, Unit.M); // 0 m

        Distance result = distance1.add(distance2);

        assertEquals(5, result.getValue());
        assertEquals(Unit.KM, result.getUnit());
    }

    @Test
    void testAdd_NegativeDistance() {
        Distance distance1 = new Distance(5, Unit.KM);
        Distance distance2 = new Distance(-2, Unit.KM);

        Distance result = distance1.add(distance2);

        assertEquals(3, result.getValue());
        assertEquals(Unit.KM, result.getUnit());
    }
}
