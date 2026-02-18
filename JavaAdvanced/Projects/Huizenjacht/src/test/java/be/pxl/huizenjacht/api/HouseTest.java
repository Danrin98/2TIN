package be.pxl.huizenjacht.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HouseTest {
    private House house;

    @BeforeEach
    public void setUp() {
        house = new House();
    }

    @Test
    public void it_should_return_the_code_for_the_house()
    {
        //arrange
        house.setCode("ABC123");
        //act & assert
        assertEquals("ABC123", house.getCode());
    }

    @Test
    public void testGetPrice() {
        //arrange
        house.setArea(150);
        //act
        double expectedPrice = 150 * House.BASEPRICE * EPCScore.B.getPercentage();
        //assert
        assertEquals(expectedPrice, house.getPrice(), 0.001);
    }
    @Test
    public void testGetPriceWithCity() {
        //arrange
        house.setArea(150);
        house.setCity("Hasselt");
        //act
        double expectedPrice = 150 * House. BASEPRICE * EPCScore.B.getPercentage() * 1.25;
        //assert
        assertEquals(expectedPrice, house.getPrice(), 0.001);
    }
}