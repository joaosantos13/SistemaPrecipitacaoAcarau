package com.example.util;

import com.example.model.RegistroPluviometrico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<RegistroPluviometrico> lerCSV(String caminhoArquivo) {

        List<RegistroPluviometrico> registros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {

            String linha;

            br.readLine();

            while ((linha = br.readLine()) != null) {

                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                double valor = Double.parseDouble(dados[1]);
                LocalDate data = LocalDate.parse(dados[2]);
                int posto = Integer.parseInt(dados[3]);

                RegistroPluviometrico registro =
                        new RegistroPluviometrico(id, valor, data, posto);

                registros.add(registro);
            }

        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo CSV: " + e.getMessage());
        }

        return registros;
    }
}