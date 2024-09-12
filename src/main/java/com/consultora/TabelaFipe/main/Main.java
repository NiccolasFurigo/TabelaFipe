package com.consultora.TabelaFipe.main;

import com.consultora.TabelaFipe.service.ApiConsumption;

import java.util.Scanner;

public class Main {

    private Scanner reading = new Scanner(System.in);
    private ApiConsumption consumption = new ApiConsumption();

    private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void MenuDisplay() {
        var menu = """
                *** Opções ***
                Carro
                Moto
                Caminhão
                
                Digite uma das opções para consultar:
                """;
        System.out.println(menu);
        var option = reading.nextLine();
        String address;

        if (option.toLowerCase().contains("carr")){
            address = URL_BASE + "carros/marcas";
        } else if (option.toLowerCase().contains("mot")){
            address = URL_BASE + "motos/marcas";
        } else {
            address = URL_BASE + "caminhoes/marcas";
        }

        var json = consumption.getData(address);
        System.out.println(json);
    }
}
