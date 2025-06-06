package com.senai.projetointegrador.mecanicaespecializadabackend.strategy;

public class CalculoPecaStrategy implements CalculoValorStrategy{
    @Override
    public double calcularValorTotal(int quantidade, double valorUnitario) {
        double valor = quantidade * valorUnitario;
        if (quantidade > 10) {
            valor *= 0.9;
        }
        return valor;
    }
}
