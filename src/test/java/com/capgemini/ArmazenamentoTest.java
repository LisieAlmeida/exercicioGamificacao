package com.capgemini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArmazenamentoTest {
	
	@Before
	public void limparArquivo() throws IOException {
	    PrintWriter writer = new PrintWriter("pontos.txt");
	    writer.print("");
	    writer.close();
	}
    
	@Test
	public void testArmazenarPontos() throws IOException {
	    String usuario = "guerra";
	    String tipoPonto = "estrela";
	    int quantidade = 10;
	    Armazenamento armazenamento = new Armazenamento("pontos.txt");
	    armazenamento.armazenarPonto(usuario, tipoPonto, quantidade);

	    BufferedReader reader = new BufferedReader(new FileReader("pontos.txt"));
	    String linha;
	    
	    while ((linha = reader.readLine()) != null) {
	        String[] tokens = linha.split(":");
	        if (tokens[0].equals(usuario)) {
	            String[] pontos = tokens[1].split(",");
	            for (String ponto : pontos) {
	                String[] pontoTokens = ponto.split("-");
	                if (pontoTokens[0].equals(tipoPonto)) {
	                    assertEquals(quantidade, Integer.parseInt(pontoTokens[1]));
	                }
	            }
	        }
	    }
	    reader.close();
	}
	
	@Test
    public void testRecuperarPontos() throws IOException {
		String usuario = "guerra";
	    String tipoPonto = "estrela";
	    int quantidade = 10;
	    Armazenamento armazenamento = new Armazenamento("pontos.txt");
	    armazenamento.armazenarPonto(usuario, tipoPonto, quantidade);
        
        
        int pontos = armazenamento.recuperarPonto("guerra", "estrela");
        Assertions.assertEquals(10, pontos);
        limparArquivo();
       
    }
	
	@Test
    void testListarUsuarios() throws IOException {
		Armazenamento armazenamento = new Armazenamento("pontos.txt");
        try {
            armazenamento.armazenarPonto("guerra", "estrela", 10);
            armazenamento.armazenarPonto("fernandes", "moeda", 20);
            armazenamento.armazenarPonto("rodrigo", "estrela", 5);
            armazenamento.armazenarPonto("guerra", "moeda", 5);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> usuariosEsperados = new ArrayList<String>();
        usuariosEsperados.add("guerra");
        usuariosEsperados.add("fernandes");
        usuariosEsperados.add("rodrigo");
        List<String> usuarios = null;
        usuarios = armazenamento.listarUsuarios();
        Collections.sort(usuariosEsperados);
        assertEquals(usuariosEsperados, usuarios);
    }
	
	@Test
	void testListarTipos() throws IOException {
	    Armazenamento armazenamento = new Armazenamento("pontos.txt");
	    armazenamento.armazenarPonto("guerra", "estrela", 10);
	    armazenamento.armazenarPonto("guerra", "moeda", 20);
	    armazenamento.armazenarPonto("fernandes", "estrela", 19);
	    armazenamento.armazenarPonto("rodrigo", "estrela", 17);
	    List<String> tiposEsperados = Arrays.asList("estrela", "moeda");
	    
	    List<String> tipos = armazenamento.listarTipos();
	    Collections.sort(tiposEsperados);

	    assertEquals(tiposEsperados, tipos);
	}
	
	@Test
	void deveRetornarTodosOsUsuariosQueJaReceberamAlgumTipoDePonto() throws IOException {
	    Armazenamento armazenamento = new Armazenamento("pontos.txt");

	    armazenamento.armazenarPonto("guerra", "moeda", 10);
	    armazenamento.armazenarPonto("fernandes", "estrela", 5);
	    armazenamento.armazenarPonto("rodrigo", "estrela", 10);
	    armazenamento.armazenarPonto("toco", "curtida", 2);

	    List<String> usuarios = armazenamento.todosUsuariosComPontos();

	    assertEquals(4, usuarios.size());
	    assertTrue(usuarios.contains("guerra"));
	    assertTrue(usuarios.contains("fernandes"));
	    assertTrue(usuarios.contains("rodrigo"));
	    assertTrue(usuarios.contains("toco"));
	}





 
   
}

