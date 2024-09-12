package com.consultora.TabelaFipe.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

//Variables in portuguese to connect directly to the API
@JsonIgnoreProperties(ignoreUnknown = true)
public record Models(List<Datas> modelos) {
}
