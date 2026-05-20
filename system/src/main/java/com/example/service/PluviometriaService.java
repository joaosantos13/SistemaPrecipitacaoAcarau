package com.example.service;

import java.time.Month;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.model.RegistroPluviometrico;

public class PluviometriaService {

    private List<RegistroPluviometrico> registros;

    public PluviometriaService(List<RegistroPluviometrico> registros) {
        this.registros = registros;
    }

    // Total de precipitação por mês
    public Map<Month, Double> totalPorMes(int ano) {

        Map<Month, Double> totais = new HashMap<>();

        registros.stream()
                .filter(r -> r.getData().getYear() == ano)
                .forEach(r -> {

                    Month mes = r.getData().getMonth();

                    totais.put(
                            mes,
                            totais.getOrDefault(mes, 0.0) + r.getValor()
                    );
                });

        return totais;
    }

    // Dia de maior precipitação
    public RegistroPluviometrico maiorPrecipitacao(int ano) {

        return registros.stream()
                .filter(r -> r.getData().getYear() == ano)
                .max(Comparator.comparingDouble(RegistroPluviometrico::getValor))
                .orElse(null);
    }

    // Dia de menor precipitação
    // Dia de menor precipitação
    public RegistroPluviometrico menorPrecipitacao(int ano) {

        return registros.stream()
                .filter(r -> r.getData().getYear() == ano)
                .min(Comparator.comparingDouble(RegistroPluviometrico::getValor))
                .orElse(null);
    }

    // Mês de maior precipitação
    public Month mesMaiorPrecipitacao(int ano) {

        return totalPorMes(ano)
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    // Mês de menor precipitação
    public Month mesMenorPrecipitacao(int ano) {

        return totalPorMes(ano)
                .entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }

    // Média anual
    public double mediaAnual(int ano) {

        return registros.stream()
                .filter(r -> r.getData().getYear() == ano)
                .mapToDouble(RegistroPluviometrico::getValor)
                .average()
                .orElse(0.0);
    }

    // Média de precipitação por mês
    public Map<Month, Double> mediaPorMes(int ano) {

        Map<Month, Double> medias = new HashMap<>();

        for (Month mes : Month.values()) {

            double media = registros.stream()
                    .filter(r -> r.getData().getYear() == ano)
                    .filter(r -> r.getData().getMonth() == mes)
                    .mapToDouble(RegistroPluviometrico::getValor)
                    .average()
                    .orElse(0.0);

            medias.put(mes, media);
        }

        return medias;
    }

    // Top 10 dias de maior precipitação
    public List<RegistroPluviometrico> top10DiasChuvosos(int ano) {

        return registros.stream()
                .filter(r -> r.getData().getYear() == ano)
                .sorted(
                        Comparator.comparingDouble(
                                RegistroPluviometrico::getValor
                        ).reversed()
                )
                .limit(10)
                .collect(Collectors.toList());
    }
}