package br.com.aps.biometria.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LerArquivo {

	protected ObjectInputStream input;
	private String nomeArquivo;

	public boolean existeArquivo() {
		File file = new File(nomeArquivo);
		return file.exists();
	}

	public void abrirArquivo() throws IOException {
		FileInputStream file = new FileInputStream(nomeArquivo);
		input = new ObjectInputStream(file);
	}

	public LerArquivo(String nomeArquivo) {
		super();
		this.nomeArquivo = nomeArquivo;
	}

	public void fecharArquivo() throws IOException {
		if (input != null)
			input.close();
	}
	
}
