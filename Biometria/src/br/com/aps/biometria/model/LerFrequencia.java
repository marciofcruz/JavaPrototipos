package br.com.aps.biometria.model;

import java.io.EOFException;
import java.util.ArrayList;
import java.util.List;

import br.com.aps.biometria.tipos.Frequencia;

public class LerFrequencia extends LerArquivo {

	public LerFrequencia() {
		super(FuncoesGerais.getNomeArquivoRegistroFrequencia());
	}

	public List<Frequencia> getLista() throws Exception {
		List<Frequencia> lista = new ArrayList<Frequencia>();
		Frequencia registro;

		try {
			while (true) {
				registro = (Frequencia) input.readObject();
				lista.add(registro);
			}
		} catch (EOFException endOfFileException) {
			return lista;
		} catch (Exception exception) {
			throw new Exception(exception.getMessage());
		}

	}

}
