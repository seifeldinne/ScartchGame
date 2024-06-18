package com.game.combinatorics;

import com.game.configuration.Config;
import com.game.configuration.WinCombination;

import java.util.*;

import static com.game.util.Utils.LINEAR_SYMBOLS;
import static com.game.util.Utils.SAME_SYMBOLS;
import static com.game.util.Utils.countSymbolsInCoveredArea;
import static com.game.util.Utils.countSymbolsInMatrix;

public class WinningCombinationChecker {

    private final Config config;

    public WinningCombinationChecker(Config config) {
        this.config = config;
    }

    public Map<String, List<String>> findWinningCombinations(String[][] matrix) {
        Map<String, List<String>> winningCombinations = new HashMap<>();
        Map<String, Integer> symbolCounts = countSymbolsInMatrix(matrix);
        for (Map.Entry<String, WinCombination> entry : config.getWinCombinations().entrySet()) {
            String combinationName = entry.getKey();
            WinCombination winCombination = entry.getValue();
            if (winCombination.getWhen().equals(SAME_SYMBOLS)) {
                checkSameSymbols(symbolCounts, combinationName, winCombination, winningCombinations);
            } else if (winCombination.getWhen().equals(LINEAR_SYMBOLS)) {
                checkLinearSymbols(matrix, combinationName, winCombination, winningCombinations);
            }
        }
        return winningCombinations;
    }

    private void checkSameSymbols(Map<String, Integer> symbolCounts, String combinationName, WinCombination winCombination, Map<String, List<String>> winningCombinations) {
        for (Map.Entry<String, Integer> entry : symbolCounts.entrySet()) {
            if (entry.getValue() >= winCombination.getCount()) {
                winningCombinations
                        .computeIfAbsent(entry.getKey(), k -> new ArrayList<>())
                        .add(combinationName);
            }
        }
    }


    private void checkLinearSymbols(String[][] matrix, String combinationName, WinCombination winCombination, Map<String, List<String>> winningCombinations) {
        for (List<String> coveredArea : winCombination.getCoveredAreas()) {
            Map<String, Integer> symbolCounts = countSymbolsInCoveredArea(coveredArea, matrix);
            for (Map.Entry<String, Integer> entry : symbolCounts.entrySet()) {
                if (entry.getValue() == coveredArea.size()) {
                    winningCombinations
                            .computeIfAbsent(entry.getKey(), k -> new ArrayList<>())
                            .add(combinationName);
                }
            }
        }
    }
}