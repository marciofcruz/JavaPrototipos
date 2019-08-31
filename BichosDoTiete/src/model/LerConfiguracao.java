package model;

import java.io.IOException;

import tipos.Configuracao;

public class LerConfiguracao extends LerArquivo {

	public LerConfiguracao() {
		super(FuncoesGerais.getPathSistema() + "config.dat");
	}
	
	public Configuracao getConfiguracao() throws ClassNotFoundException, IOException {
		Configuracao registro = (Configuracao) input.readObject();
		
		return registro;
	}

}
