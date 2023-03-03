package com.capgemini;

import java.util.List;

public interface ArmazenamentoInterface {
    void armazenarPontos(String usuario, String tipoPonto, int quantidade);
    int recuperarPontos(String usuario, String tipoPonto);
    List<String> usuariosComPontos();
    List<String> tiposDePontosRegistrados();
}