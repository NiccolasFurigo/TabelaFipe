package com.consultora.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle(
        @JsonAlias("Valor") String value,
        @JsonAlias("Marca") String mark,
        @JsonAlias("Marca") String Modelo,
        @JsonAlias("Marca") Integer AnoModelo,
        @JsonAlias("Marca") String Combustivel
) {
}
