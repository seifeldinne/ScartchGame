package com.game.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.game.bonus.BonusSymbolApplier;
import com.game.combinatorics.WinningCombinationChecker;
import com.game.configuration.Config;
import com.game.matrix.MatrixGenerator;
import com.game.reward.RewardCalculator;

import java.util.List;
import java.util.Map;

import static com.game.util.Utils.generateGameResultAsJson;

public class Game {
    private final Config config;
    private final double betAmount;
    private final MatrixGenerator matrixGenerator;
    private final WinningCombinationChecker combinationChecker;
    private final BonusSymbolApplier bonusApplier;
    private final RewardCalculator rewardCalculator;

    public Game(Config config, double betAmount) {
        this.config = config;
        this.betAmount = betAmount;
        matrixGenerator = new MatrixGenerator(config);
        combinationChecker = new WinningCombinationChecker(config);
        bonusApplier = new BonusSymbolApplier(config);
        rewardCalculator = new RewardCalculator(config);
    }

    public String play() throws JsonProcessingException {
        String[][] matrix = matrixGenerator.generate();
        Map<String, List<String>> winningCombinations = combinationChecker.findWinningCombinations(matrix);
        String appliedBonusSymbol = bonusApplier.applyBonusSymbols(matrix, winningCombinations);
        double reward = rewardCalculator.calculate(winningCombinations, appliedBonusSymbol, betAmount);
        return generateGameResultAsJson(matrix, winningCombinations, appliedBonusSymbol, reward);
    }


}