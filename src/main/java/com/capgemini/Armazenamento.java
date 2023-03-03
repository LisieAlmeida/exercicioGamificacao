package com.capgemini;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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

	

}
