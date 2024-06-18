package com.game;

import com.game.configuration.Config;
import com.game.service.Game;

import java.io.IOException;


public class Main {

    public static void main(String[] args) {
        System.out.println(args.length);
        if (args.length != 4) {
            System.err.println("Usage: java -jar scratch-game.jar --config config.json --betting-amount 100");
            return;
        }
        String configFileName = null;
        double bettingAmount = 0;
        for (int i = 0; i < args.length; i++) {
            if ("--config" .equals(args[i])) {
                configFileName = args[i + 1];
            } else if ("--betting-amount" .equals(args[i])) {
                bettingAmount = Double.parseDouble(args[i + 1]);
            }
        }
        play(configFileName, bettingAmount);
    }

    private static void play(String configFile, double bettingAmount) {

        try {
            Config config = Config.fromFile(configFile);
            Game game = new Game(config, bettingAmount);
            String results = game.play();
            System.out.println(results);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


