package com.senai.projetointegrador.mecanicaespecializadabackend.strategy;

public class CalculoServicoStrategy implements CalculoValorStrategy{
    @Override
    public double calcularValorTotal(int quantidade, double valorUnitario) {
        return quantidade * valorUnitario;
    }
}
