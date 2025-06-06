package com.senai.projetointegrador.mecanicaespecializadabackend.factory;

import com.senai.projetointegrador.mecanicaespecializadabackend.strategy.CalculoPecaStrategy;
import com.senai.projetointegrador.mecanicaespecializadabackend.strategy.CalculoServicoStrategy;
import com.senai.projetointegrador.mecanicaespecializadabackend.strategy.CalculoValorStrategy;

public class CalculoValorStrategyFactory {
    public enum TipoItem {
        SERVICO, PECA
    }

    public static CalculoValorStrategy getStrategy(TipoItem tipo) {
        return switch (tipo) {
            case SERVICO -> new CalculoServicoStrategy();
            case PECA -> new CalculoPecaStrategy();
        };
    }
}
