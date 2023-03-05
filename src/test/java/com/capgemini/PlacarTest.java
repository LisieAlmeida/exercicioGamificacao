package com.capgemini;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


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
	    

	    doNothing().when(armazenamentoMock).armazenarPonto(usuario, tipo, pontos);
	    

	    placar.registrarPonto(usuario, tipo, pontos);
	    

	    verify(armazenamentoMock).armazenarPonto(usuario, tipo, pontos);
	}
	
	@Test
    public void testPontosDoUsuario() throws IOException {

        when(armazenamentoMock.listarTipos()).thenReturn(Arrays.asList("pontos", "moedas", "estrelas"));
	
        when(armazenamentoMock.recuperarPonto("joao", "pontos")).thenReturn(10);
        when(armazenamentoMock.recuperarPonto("joao", "moedas")).thenReturn(20);
        when(armazenamentoMock.recuperarPonto("joao", "estrelas")).thenReturn(0);
        
        Map<String, Integer> pontosEsperados = new HashMap<>();
        pontosEsperados.put("pontos", 10);
        pontosEsperados.put("moedas", 20);
        Map<String, Integer> pontosObtidos = placar.pontosDoUsuario("joao");
        assertEquals(pontosEsperados, pontosObtidos);
    }
	
	@Test
    public void testRanking() throws IOException {
        String tipo = "pontos";
        String usuario1 = "usuario1";
        String usuario2 = "usuario2";
        String usuario3 = "usuario3";

        when(armazenamentoMock.listarUsuarios()).thenReturn(Arrays.asList(usuario1, usuario2, usuario3));
        when(armazenamentoMock.recuperarPonto(usuario1, tipo)).thenReturn(10);
        when(armazenamentoMock.recuperarPonto(usuario2, tipo)).thenReturn(5);
        when(armazenamentoMock.recuperarPonto(usuario3, tipo)).thenReturn(8);

        List<String> ranking = placar.ranking(tipo);

        assertEquals(Arrays.asList(usuario1, usuario3, usuario2), ranking);
    }
	

}
