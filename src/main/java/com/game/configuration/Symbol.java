package com.game.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Symbol {
    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;
    private String type;
    private String impact;
    private Double extra;

}