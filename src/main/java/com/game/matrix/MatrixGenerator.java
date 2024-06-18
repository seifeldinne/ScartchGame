package com.game.matrix;

import com.game.configuration.Config;

import java.util.Map;
import java.util.Random;

public class MatrixGenerator {
    private final Config config;
    private final Random random;

    public MatrixGenerator(Config config) {
        this.config = config;
        this.random = new Random();
    }

    public String[][] generate() {
        String[][] matrix = new String[config.getRows()][config.getColumns()];
        for (int row = 0; row < config.getRows(); row++) {
            for (int column = 0; column < config.getColumns(); column++) {
                matrix[row][column] = getRandomSymbol((config.getProbabilities().getStandardSymbols().get(row).getSymbols()));
            }
        }
        addBonusSymbol(matrix);
        return matrix;
    }

    public String getRandomSymbol(Map<String, Integer> symbolProbabilities) {
        int totalWeight = symbolProbabilities.values().stream().mapToInt(Integer::intValue).sum();
        int randomValue = random.nextInt(totalWeight);
        for (Map.Entry<String, Integer> entry : symbolProbabilities.entrySet()) {
            randomValue -= entry.getValue();
            if (randomValue < 0) {
                return entry.getKey();
            }
        }
        throw new IllegalStateException("Should not reach here");
    }


    public void addBonusSymbol(String[][] matrix) {
        int row = random.nextInt(config.getRows());
        int column = random.nextInt(config.getColumns());
        matrix[row][column] = getRandomSymbol(config.getProbabilities().getBonusSymbols().getSymbols());
    }
}