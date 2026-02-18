package be.pxl.dice.service;

import be.pxl.dice.domain.DiceSet;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiceService {
    private DiceSet currentDiceSet;
    private int highestSum = 0;

    public void createDiceSet(int numberOfDice, int maxNumber) {
        this.currentDiceSet = new DiceSet(maxNumber, numberOfDice);
        updateHighestSum();
    }

    public DiceSet getCurrentDiceSet() {
        if (currentDiceSet == null) {
            throw new IllegalStateException("No DiceSet created yet");
        }
        return currentDiceSet;
    }

    public List<Integer> getCurrentValues() {
        ensureDiceSetExists();
        return currentDiceSet.values();
    }

    public List<Integer> rollAll() {
        ensureDiceSetExists();
        currentDiceSet.roll();
        updateHighestSum();
        return currentDiceSet.values();
    }

    public List<Integer> rollOne(int index) {
        ensureDiceSetExists();
        currentDiceSet.rollIndividual(index);
        updateHighestSum();
        return currentDiceSet.values();
    }

    public int getHighestSum() {
        return highestSum;
    }

    private void updateHighestSum() {
        if (currentDiceSet != null) {
            highestSum = Math.max(highestSum, currentDiceSet.sum());
        }
    }

    private void ensureDiceSetExists() {
        if (currentDiceSet == null) {
            throw new IllegalStateException("DiceSet not created yet");
        }
    }
}
