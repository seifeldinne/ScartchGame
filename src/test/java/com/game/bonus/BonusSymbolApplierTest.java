package com.game.bonus;

import com.game.configuration.Config;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BonusSymbolApplierTest {
    private BonusSymbolApplier bonusSymbolApplier;
    private Config config;

    @Before
    public void setup() throws IOException {
        config = Config.fromFile("config.json");
        bonusSymbolApplier = new BonusSymbolApplier(config);
    }



    @Test
    public void testApplyBonusSymbolsWithEmptyWinningCombinations() {
        String[][] matrix = new String[][]{
                {"A", "B", "C"},
                {"B", "A", "C"},
                {"C", "B", "A"}
        };
        Map<String, List<String>> winningCombinations = new HashMap<>();
        String appliedBonusSymbol = bonusSymbolApplier.applyBonusSymbols(matrix, winningCombinations);
        // Assert the appliedBonusSymbol is null
        assertNull(appliedBonusSymbol);
    }
}