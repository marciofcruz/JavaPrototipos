package model;

import java.io.IOException;
import java.util.List;

import tipos.Catalogado;

public class GravaAnimalCatalogado extends GravaArquivo {

	public GravaAnimalCatalogado(String nomeArquivo) {
		super(nomeArquivo);
	}

	public void setLista(List<Catalogado> lista) throws IOException {
		for(int i=0; i < lista.size(); i++) {
			output.writeObject(lista.get(i));
		}
		
	}
}
