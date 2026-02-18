package be.pxl.dice.domain;

import be.pxl.dice.exception.InvalidDieValue;

import java.util.Random;

public class Die implements Rollable {
    public static final String SIX_SIDED_DIE_EMOJI = "\uD83C\uDFB2";
    private int value;
    private int sides;
    private final Random random = new Random();

    public Die(int sides) {
        if (sides < 4) {
            throw new IllegalArgumentException("A die must have at least 4 sides");
        }
        this.sides = sides;
        roll();
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        if (value < 1 || value > sides) {
            throw new InvalidDieValue("Invalid die value: " + value);
        }
        this.value = value;
    }

    public int getSides() {
        return sides;
    }

    @Override
    public String toString() {
        return "[" + value + "]";
    }

    @Override
    public void roll() {
        this.value = random.nextInt(sides) + 1;
    }

}
