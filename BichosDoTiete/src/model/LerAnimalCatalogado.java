package model;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

import tipos.Catalogado;

public class LerAnimalCatalogado extends LerArquivo {

	public LerAnimalCatalogado(String nomeArquivo) {
		super(nomeArquivo);
	}

	public List<Catalogado> getCatalogado() throws Exception {
		List<Catalogado> lista = new ArrayList<Catalogado>();
		
		Catalogado registro;

		try {
			while (true) {
				registro = (Catalogado) input.readObject();

				lista.add(registro);
			}
		} catch (EOFException endOfFileException) {
			return lista;
		} catch (Exception exception) {
			throw new Exception(exception.getMessage());
		}

	}

}
