package com.game.matrix;

import com.game.configuration.Config;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MatrixGeneratorTest {
    private MatrixGenerator matrixGenerator;
    private Config config;

    @Before
    public void setup() throws IOException {
        // Initialize your Config object here
        config = Config.fromFile("config.json");
        matrixGenerator = new MatrixGenerator(config);
    }

    @Test
    public void testGenerate() {
        String[][] matrix = matrixGenerator.generate();
        assertNotNull(matrix);
        assertEquals(config.getRows(), matrix.length);
        assertEquals(config.getColumns(), matrix[0].length);
    }

    @Test
    public void testGetRandomSymbol() {
        Map<String, Integer> symbolProbabilities = new HashMap<>();
        symbolProbabilities.put("A", 1);
        symbolProbabilities.put("B", 1);
        String symbol = matrixGenerator.getRandomSymbol(symbolProbabilities);
        assertNotNull(symbol);
        // Check that the symbol is one of the keys in the symbolProbabilities map
        assertTrue(symbolProbabilities.containsKey(symbol));
    }
}