package br.com.aps.biometria.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.aps.biometria.tipos.Frequencia;

public class GerarListaFrequencia {

	private List<Frequencia> frequencias;

	public List<Frequencia> getFrequencias() {
		return frequencias;
	}

	public GerarListaFrequencia() throws IOException {
		super();
		this.frequencias = gerarArrayImagens();
	}

	private List<Frequencia> gerarArrayImagens() throws IOException {

		File diretorioImagens = new File(FuncoesGerais.getPathDigitais());

		String[] listaArquivos = diretorioImagens.list();

		List<Frequencia> frequencias = new ArrayList<Frequencia>();

		for (int i = 1; i < listaArquivos.length; i++) {

			if (listaArquivos[i].toString().toUpperCase().endsWith("PNG"))
				frequencias.add(new Frequencia(FuncoesGerais.getPathDigitais()
						+ listaArquivos[i].toString()));
		}
		return frequencias;
	}
	
}
