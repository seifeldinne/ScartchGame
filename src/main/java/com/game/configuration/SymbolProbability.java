package com.game.configuration;

import lombok.Data;

import java.util.Map;

@Data
public class SymbolProbability {
    private int column;
    private int row;
    private Map<String, Integer> symbols;

}