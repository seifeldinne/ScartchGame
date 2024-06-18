package com.game.reward;

import com.game.configuration.Config;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RewardCalculatorTest {
    private RewardCalculator rewardCalculator;
    private Config config;

    @Before
    public void setup() throws IOException {

        config = Config.fromFile("config.json");
        rewardCalculator = new RewardCalculator(config);
    }

    @Test
    public void testCalculateReward() {
        Map<String, List<String>> winningCombinations = new HashMap<>();
        String appliedBonusSymbol = "bonusSymbol";
        double betAmount = 100.0;
        double reward = rewardCalculator.calculate(winningCombinations, appliedBonusSymbol, betAmount);
        assertEquals(0.0, reward, 0.001);
    }


    @Test
    public void testCalculateRewardWithBonusSymbol() {
        Map<String, List<String>> winningCombinations = new HashMap<>();
        winningCombinations.put("A", Arrays.asList("same_symbol_3_times"));
        winningCombinations.put("B", Arrays.asList("same_symbol_4_times"));
        String appliedBonusSymbol = "A";
        double betAmount = 200.0;
        double reward = rewardCalculator.calculate(winningCombinations, appliedBonusSymbol, betAmount);
        // Replace 0.0 with the expected reward based on your configuration
        assertEquals(9500, reward, 0.001);
    }
}