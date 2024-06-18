package com.game.bonus;

import com.game.configuration.Config;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class BonusSymbolApplier {
    private Config config;
    private Random random;

    public BonusSymbolApplier(Config config) {
        this.config = config;
        this.random = new Random();
    }

    public String applyBonusSymbols(String[][] matrix, Map<String, List<String>> winningCombinations) {
        if (winningCombinations.isEmpty()) {
            return null;
        }

        String bonusSymbol = getRandomBonusSymbol();
        if (bonusSymbol != null && bonusSymbol.equals("MISS")) {
            return null;
        }

        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                if (matrix[row][column].equals(bonusSymbol)) {
                    return bonusSymbol;
                }
            }
        }
        return null;
    }

    public String getRandomBonusSymbol() {
        Map<String, Integer> bonusSymbolProbabilities = config.getProbabilities().getBonusSymbols().getSymbols();
        int total = bonusSymbolProbabilities.values().stream().mapToInt(Integer::intValue).sum();
        int randomValue = random.nextInt(total);

        int cumulative = 0;
        for (Map.Entry<String, Integer> entry : bonusSymbolProbabilities.entrySet()) {
            cumulative += entry.getValue();
            if (randomValue < cumulative) {
                return entry.getKey();
            }
        }
        return null;
    }
}