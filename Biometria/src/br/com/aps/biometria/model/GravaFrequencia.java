package br.com.aps.biometria.model;

import java.io.IOException;
import java.util.List;

import br.com.aps.biometria.tipos.Frequencia;

public class GravaFrequencia extends GravaArquivo {

	public GravaFrequencia() {
		super(FuncoesGerais.getNomeArquivoRegistroFrequencia());
	}

	public void setLista(List<Frequencia> lista) throws IOException {
		for (int i = 0; i < lista.size(); i++)
			output.writeObject(lista.get(i));
	}
	
}
