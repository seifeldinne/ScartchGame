package com.game.reward;

import com.game.configuration.Config;
import com.game.configuration.Symbol;
import com.game.configuration.WinCombination;

import java.util.List;
import java.util.Map;

public class RewardCalculator {
    private Config config;

    public RewardCalculator(Config config) {
        this.config = config;
    }

    public double calculate(Map<String, List<String>> winningCombinations, String appliedBonusSymbol, double betAmount) {
        double totalReward = 0.0;

        for (Map.Entry<String, List<String>> entry : winningCombinations.entrySet()) {
            String symbol = entry.getKey();
            List<String> combinations = entry.getValue();
            Symbol symbolConfig = config.getSymbols().get(symbol);

            for (String combinationName : combinations) {
                WinCombination winCombination = config.getWinCombinations().get(combinationName);
                totalReward += betAmount * symbolConfig.getRewardMultiplier() * winCombination.getRewardMultiplier();
            }
        }

        if (appliedBonusSymbol != null) {
            Symbol bonusConfig = config.getSymbols().get(appliedBonusSymbol);
            if (bonusConfig != null)
                totalReward *= bonusConfig.getRewardMultiplier();
        }

        return totalReward;
    }
}