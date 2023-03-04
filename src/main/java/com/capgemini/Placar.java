package com.capgemini;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Placar {

    private final Armazenamento armazenamento;

    public Placar(Armazenamento armazenamento) {
        this.armazenamento = armazenamento;
    }


	public void registrarPonto(String usuario, String tipo, int quantidade) throws IOException {
        armazenamento.armazenarPonto(usuario, tipo, quantidade);
    }
    
    public Map<String, Integer> pontosDoUsuario(String usuario) throws IOException {
        Map<String, Integer> pontos = new HashMap<>();
        for (String tipo : armazenamento.listarTipos()) {
            int quantidade = armazenamento.recuperarPonto(usuario, tipo);
            if (quantidade > 0) {
                pontos.put(tipo, quantidade);
            }
        }
        return pontos;
    }

}
