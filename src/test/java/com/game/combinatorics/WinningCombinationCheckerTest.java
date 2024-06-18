
package com.game.combinatorics;

import com.game.configuration.Config;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


public class WinningCombinationCheckerTest {
    String[][] matrix;
    Config config;

    @Before
    public void setup() throws IOException {
        // Prepare the test data
        config = Config.fromFile("config.json"); // Assume this is a valid Config object
    }

    @Test
    public void testCheckSameSymbols() throws IOException {
        matrix = new String[][]{
                {"A", "B", "A"},
                {"A", "A", "B"},
                {"B", "A", "A"}
        };
        WinningCombinationChecker checker = new WinningCombinationChecker(config);

        // Call the method to test
        Map<String, List<String>> result = checker.findWinningCombinations(matrix);

        // Prepare the expected result
        Map<String, List<String>> expectedResult = new HashMap<>();
        expectedResult.put("A", List.of("same_symbol_3_times", "same_symbol_4_times", "same_symbol_5_times", "same_symbol_6_times", "same_symbols_diagonally_left_to_right"));
        expectedResult.put("B", List.of("same_symbol_3_times"));

        // Assert that the result matches the expected result
        assertEquals(expectedResult, result);
    }
}