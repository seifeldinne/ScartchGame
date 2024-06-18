package com.game.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

@Data
public class Config {
    private int columns;
    private int rows;
    private Map<String, Symbol> symbols;
    private Probabilities probabilities;
    @JsonProperty("win_combinations")
    private Map<String, WinCombination> winCombinations;

    public static Config fromFile(String fileName) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        URL resource = Config.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new FileNotFoundException("Resource not found: " + fileName);
        }
        try (InputStream is = resource.openStream()) {
            return mapper.readValue(is, Config.class);
        }
       }

}

