package com.capgemini;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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


    public List<String> ranking(String tipo) throws IOException {
        Map<String, Integer> pontosPorUsuario = new HashMap<>();
        for (String usuario : armazenamento.listarUsuarios()) {
            int quantidade = armazenamento.recuperarPonto(usuario, tipo);
            if (quantidade > 0) {
                pontosPorUsuario.put(usuario, quantidade);
            }
        }
        return pontosPorUsuario.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
