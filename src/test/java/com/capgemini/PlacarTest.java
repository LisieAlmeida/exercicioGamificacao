package com.capgemini;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.HashMap;
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
	
	/*@Test
    public void testPontosDoUsuario() throws IOException {
        String usuario = "Alice";
        Map<String, Integer> pontos = new HashMap<>();
        pontos.put("Estrela", 20);
        pontos.put("Moeda", 10);

        when(armazenamentoMock.recuperarPontosUsuario(usuario)).thenReturn(pontos);

        assertEquals(pontos, placar.pontosDoUsuario(usuario));
    }
	*/

}
