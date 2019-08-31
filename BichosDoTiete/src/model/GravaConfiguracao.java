package model;

import tipos.Configuracao;

public class GravaConfiguracao extends GravaArquivo {

	public GravaConfiguracao() {
		super(FuncoesGerais.getPathSistema() + "config.dat");
	}

	public void salvar(Configuracao configuracao) throws Exception {
		
		configuracao.setLocalizacao(configuracao.getLocalizacao().trim());
		configuracao.setUsuario(configuracao.getUsuario().trim());
		
		if (configuracao.getLocalizacao().isEmpty()) {
			throw new Exception("Localização não pode ser vazia!");
		}
		
		if (configuracao.getUsuario().isEmpty()) {
			throw new Exception("Usuário não pode ser vazio!");
		}
		
		output.writeObject(configuracao);
	}
}
