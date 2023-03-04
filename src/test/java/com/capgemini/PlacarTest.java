package com.capgemini;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class PlacarTest {
	
	 private Placar placar;
	
	@Mock
	 private Armazenamento armazenamentoMock;
	
	@BeforeEach
	void setUp() {
	    MockitoAnnotations.openMocks(this);
	    placar = new Placar(armazenamentoMock);
	}
	
	@Test
	public void testRegistrarPonto() throws IOException {
	    String usuario = "guerra";
	    String tipo = "estrela";
	    int pontos = 10;
	    
	    //define o comportamento do mock de Armazenamento
	    doNothing().when(armazenamentoMock).armazenarPonto(usuario, tipo, pontos);
	    
	    //chama o método a ser testado
	    placar.registrarPonto(usuario, tipo, pontos);
	    
	    //verifica se o método de Armazenamento foi chamado com os parâmetros corretos
	    verify(armazenamentoMock).armazenarPonto(usuario, tipo, pontos);
	}
	
	@Test
    public void testPontosDoUsuario() throws IOException {
        // Mockando o comportamento do método listarTipos do armazenamento
        when(armazenamentoMock.listarTipos()).thenReturn(Arrays.asList("pontos", "moedas", "estrelas"));
	
        // Mockando o comportamento do método recuperarPonto do armazenamento
        when(armazenamentoMock.recuperarPonto("joao", "pontos")).thenReturn(10);
        when(armazenamentoMock.recuperarPonto("joao", "moedas")).thenReturn(20);
        when(armazenamentoMock.recuperarPonto("joao", "estrelas")).thenReturn(0);
        
        // Testando o método pontosDoUsuario
        Map<String, Integer> pontosEsperados = new HashMap<>();
        pontosEsperados.put("pontos", 10);
        pontosEsperados.put("moedas", 20);
        Map<String, Integer> pontosObtidos = placar.pontosDoUsuario("joao");
        assertEquals(pontosEsperados, pontosObtidos);
    }
	

}
