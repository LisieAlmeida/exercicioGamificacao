package com.capgemini;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Armazenamento {
	private final File file;

    public Armazenamento(String filename) throws IOException {
        file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
    }
    
    public void armazenarPonto(String usuario, String tipo, int quantidade) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            props.load(fis);
        }
        props.put(usuario + "." + tipo, String.valueOf(quantidade));
        try (FileOutputStream fos = new FileOutputStream(file)) {
            props.store(fos, null);
        }
    }

    public int recuperarPonto(String usuario, String tipo) throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            props.load(fis);
        }
        String value = props.getProperty(usuario + "." + tipo);
        if (value == null) {
            return 0;
        } else {
            return Integer.parseInt(value);
        }
    }
    
    

    public List<String> listarUsuarios() throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            props.load(fis);
        }
        Set<String> usuarios = new HashSet<>();
        for (String key : props.stringPropertyNames()) {
            usuarios.add(key.split("\\.")[0]);
        }
        
        List<String> usuariosList = new ArrayList<>(usuarios);
        Collections.sort(usuariosList);
        return usuariosList;
    }

    public List<String> listarTipos() throws IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(file)) {
            props.load(fis);
        }
        Set<String> tipos = new HashSet<>();
        for (String key : props.stringPropertyNames()) {
            tipos.add(key.split("\\.")[1]);
        }
        List<String> tiposList = new ArrayList<>(tipos);
        Collections.sort(tiposList);
        return tiposList;
    }
    
    public Map<String, Integer> recuperarPontosUsuario(String usuario) throws IOException {
        Map<String, Integer> pontosUsuario = new HashMap<>();

        // recuperar todos os tipos de pontos
        List<String> tipos =  listarTipos();

        // para cada tipo, recuperar os pontos e adicionar no mapa
        for (String tipo : tipos) {
            int pontos = recuperarPonto(usuario, tipo);
            pontosUsuario.put(tipo, pontos);
        }

        return pontosUsuario;
    }
    
    

	

}
