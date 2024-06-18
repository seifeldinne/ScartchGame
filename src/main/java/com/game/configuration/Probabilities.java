package com.game.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Probabilities {
    @JsonProperty("standard_symbols")
    private List<SymbolProbability> standardSymbols;
    @JsonProperty("bonus_symbols")
    private BonusSymbol bonusSymbols;
}