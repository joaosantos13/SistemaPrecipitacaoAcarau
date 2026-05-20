package com.example;

import java.time.Month;
import java.util.List;
import java.util.Map;

import com.example.model.RegistroPluviometrico;
import com.example.service.PluviometriaService;
import com.example.util.CSVReader;

public class Main {

    public static void main(String[] args) {

        String caminhoArquivo = "system/src/main/resources/dados/PluviometriaFuncemeNormalizada.csv";

        List<RegistroPluviometrico> registros =
                CSVReader.lerCSV(caminhoArquivo);

        PluviometriaService service =
                new PluviometriaService(registros);

        int ano = 2025;

        System.out.println("========== TOTAL POR MÊS ==========");

        Map<Month, Double> totais = service.totalPorMes(ano);

        totais.forEach((mes, total) ->
                System.out.println(mes + ": " + total + " mm")
        );

        System.out.println("\n========== MAIOR PRECIPITAÇÃO ==========");
        System.out.println(service.maiorPrecipitacao(ano));

        System.out.println("\n========== MENOR PRECIPITAÇÃO ==========");
        System.out.println(service.menorPrecipitacao(ano));

        System.out.println("\n========== MÊS MAIS CHUVOSO ==========");
        System.out.println(service.mesMaiorPrecipitacao(ano));

        System.out.println("\n========== MÊS MENOS CHUVOSO ==========");
        System.out.println(service.mesMenorPrecipitacao(ano));

        System.out.println("\n========== MÉDIA ANUAL ==========");
        System.out.println(service.mediaAnual(ano) + " mm");

        System.out.println("\n========== MÉDIA POR MÊS ==========");

        Map<Month, Double> medias = service.mediaPorMes(ano);

        medias.forEach((mes, media) ->
                System.out.println(mes + ": " + media + " mm")
        );

        System.out.println("\n========== TOP 10 DIAS MAIS CHUVOSOS ==========");

        List<RegistroPluviometrico> top10 =
                service.top10DiasChuvosos(ano);

        top10.forEach(System.out::println);
    }
}