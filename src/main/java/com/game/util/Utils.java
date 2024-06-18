package com.game.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {
    public static final String SAME_SYMBOLS = "same_symbols";
    public static final String LINEAR_SYMBOLS = "linear_symbols";
    public static final String APPLIED_WINNING_COMBINATIONS = "applied_winning_combinations";
    public static final String APPLIED_BONUS_SYMBOL = "applied_bonus_symbol";
    public static final String REWARD = "reward";
    public static final String MATRIX = "matrix";

    public static Map<String, Integer> countSymbolsInMatrix(String[][] matrix) {
        Map<String, Integer> symbolCounts = new HashMap<>();
        for (String[] strings : matrix) {
            for (String symbol : strings) {
                symbolCounts.put(symbol, symbolCounts.getOrDefault(symbol, 0) + 1);
            }
        }
        return symbolCounts;
    }

    public static Map<String, Integer> countSymbolsInCoveredArea(List<String> coveredArea, String[][] matrix) {
        Map<String, Integer> symbolCounts = new HashMap<>();
        for (String cell : coveredArea) {
            String[] parts = cell.split(":");
            int row = Integer.parseInt(parts[0]);
            int column = Integer.parseInt(parts[1]);
            String symbol = matrix[row][column];
            symbolCounts.put(symbol, symbolCounts.getOrDefault(symbol, 0) + 1);
        }
        return symbolCounts;
    }
    public static String generateGameResultAsJson(String[][] matrix, Map<String, List<String>> winningCombinations, String appliedBonusSymbol, double reward) throws JsonProcessingException {
        Map<String, Object> result = new HashMap<>();
        result.put(MATRIX, matrix);
        if (!winningCombinations.isEmpty()) {
            result.put(APPLIED_WINNING_COMBINATIONS, winningCombinations);
        }
        if (appliedBonusSymbol != null && !appliedBonusSymbol.isEmpty()) {
            result.put(APPLIED_BONUS_SYMBOL, appliedBonusSymbol);
        }
        result.put(REWARD, reward);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(result);
    }
}
