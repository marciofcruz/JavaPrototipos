package br.marciofcruz.apsmetodosordenacao.testes;

import java.io.FileNotFoundException;

import br.marciofcruz.apsmetodosordenacao.imagem.ImagemSatelite;



public class TesteImagemSatelite {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		ImagemSatelite imagemSatelite = new ImagemSatelite("C:\\trab\\projetos\\java\\APSMetodosOrdenacao\\imagensSatelite\\ms_294_483_10.jpg");
		
		System.out.println("Nome Imagem: "+imagemSatelite.getNomeImagem());
		System.out.println("Chave: "+imagemSatelite.hashCode());

	}

}
