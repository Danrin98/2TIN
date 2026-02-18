package be.pxl.dice.domain;

import java.util.ArrayList;
import java.util.List;

public class DiceSet implements Rollable{
    private final List<Die> dice;
    private final int sidesOnEachDie;

    public DiceSet(int sidesOnEachDie, int numberOfDice) {
        if (numberOfDice < 2) {
            throw new IllegalArgumentException("A DiceSet must contain at least 2 dice");
        }
        if (sidesOnEachDie < 4) {
            throw new IllegalArgumentException("Dice must have at least 4 sides");
        }

        this.sidesOnEachDie = sidesOnEachDie;
        this.dice = new ArrayList<>();

        for (int i = 0; i < numberOfDice; i++) {
            dice.add(new Die(sidesOnEachDie));
        }
    }

    public String getDescriptor() {
        return dice.size() + "d" + sidesOnEachDie;
    }

    public int sum() {
        return dice.stream().mapToInt(d -> d.getValue()).sum();
    }

    public void rollIndividual(int i) {
        checkIndex(i);
        dice.get(i).roll();
    }

    public int getIndividual(int i) {
        checkIndex(i);
        return dice.get(i).getValue();
    }

    public void setIndividual(int i, int value) {
        checkIndex(i);
        dice.get(i).setValue(value);
    }

    public List<Integer> values() {
        return dice.stream().map(d -> d.getValue()).toList();
    }

    private void checkIndex(int i) {
        if (i < 0 || i >= dice.size()) {
            throw new IndexOutOfBoundsException("Invalid die index: " + i);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < dice.size(); i++) {
            sb.append(dice.get(i).toString());
        }
        return sb.toString();
    }

    @Override
    public void roll() {
        dice.forEach(e -> e.roll());
    }
}
