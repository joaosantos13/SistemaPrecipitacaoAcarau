package com.example.model;

import java.time.LocalDate;

public class RegistroPluviometrico {

    private int id;
    private double valor;
    private LocalDate data;
    private int posto;

    public RegistroPluviometrico(int id, double valor, LocalDate data, int posto) {
        this.id = id;
        this.valor = valor;
        this.data = data;
        this.posto = posto;
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public LocalDate getData() {
        return data;
    }

    public int getPosto() {
        return posto;
    }

    @Override
    public String toString() {
        return "Data: " + data + " | Precipitação: " + valor + " mm";
    }
}