package com.consultora.TabelaFipe.main;

import com.consultora.TabelaFipe.model.Datas;
import com.consultora.TabelaFipe.model.Models;
import com.consultora.TabelaFipe.model.Vehicle;
import com.consultora.TabelaFipe.service.ApiConsumption;
import com.consultora.TabelaFipe.service.DataConvert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private Scanner reading = new Scanner(System.in);
    private ApiConsumption consumption = new ApiConsumption();
    private DataConvert convert = new DataConvert();

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

        var brands = convert.getList(json, Datas.class);
        brands.stream()
                .sorted(Comparator.comparing(Datas::codigo))
                .forEach(System.out::println);

        System.out.println("Informe o código da marca para consulta");
        var brandcode = reading.nextLine();

        address = address + "/" + brandcode + "/modelos";
        json = consumption.getData(address);
        var listModels = convert.getData(json, Models.class);
        System.out.println("\n Modelos dessa marca: ");
        listModels.modelos().stream()
                .sorted(Comparator.comparing(Datas::codigo))
                .forEach(System.out::println);

        System.out.println("\nDigite um trecho do nome do carro a ser buscado");
        var vehicleName = reading.nextLine();
        List<Datas> filterModels = listModels.modelos().stream()
                .filter(m -> m.nome().toLowerCase().contains(vehicleName.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println("\nModelos filtrados: ");
        filterModels.forEach(System.out::println);

        System.out.println("Digite o código do modelo para buscar os valores");
        var modelCode = reading.nextLine();
        address = address + "/" + modelCode + "/anos";
        json = consumption.getData(address);
        List<Datas> years = convert.getList(json, Datas.class);
        List<Vehicle> vehicles = new ArrayList<>();

        for (int i = 0; i < years.size(); i++) {
            var addressYear = address + "/" + years.get(i).codigo();
            json = consumption.getData(addressYear);
            Vehicle vehicle = convert.getData(json, Vehicle.class);
            vehicles.add(vehicle);
        }

        System.out.println("\nTodos os veículos filtrados com avaliações por ano: ");
        vehicles.forEach(System.out::println);
    }
}
