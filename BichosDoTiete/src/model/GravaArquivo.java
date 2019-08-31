package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class GravaArquivo {
	protected ObjectOutputStream output;
	private String nomeArquivo;
	
	public GravaArquivo(String nomeArquivo) {
		super();
		this.nomeArquivo = nomeArquivo;
	}
	
	public void abrirArquivo() throws IOException {
		FileOutputStream file = new FileOutputStream(nomeArquivo);
		output = new ObjectOutputStream(file);
	}	
	
	public void fecharArquivo() throws IOException {
		if (output != null) {
			output.close();
		}
	}	
	
	
}
