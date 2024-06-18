package com.game.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.game.configuration.Config;
import com.game.service.Game;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameTest {
    private Game game;
    private Config config;
    private double betAmount;

    @Before
    public void setup() throws IOException {
        config = Config.fromFile("config.json");
        betAmount = 100.0;
        game = new Game(config, betAmount);
    }

    @Test
    public void testPlay() throws JsonProcessingException {
        String result = game.play();
        assertNotNull(result);
    }
}