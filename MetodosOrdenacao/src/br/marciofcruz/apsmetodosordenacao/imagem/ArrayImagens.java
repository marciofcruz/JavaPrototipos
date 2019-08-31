package br.marciofcruz.apsmetodosordenacao.imagem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import br.marciofcruz.apsmetodosordenacao.tipos.TipoCriterioOrdem;

/**
 * Esta classe representa, para cada quantidade de imagens, um array ordenado e
 * outro aleatório
 * 
 * @author B22816-4 Marcio Fernandes Cruz
 * @see java.lang.Object
 * @version 1.00
 * @since 1.0
 */

public class ArrayImagens {

	private int qtdeImagens;
	private ImagemSatelite[] crescente;
	private ImagemSatelite[] embaralhado;
	private ImagemSatelite[] decrescente;

	public ArrayImagens(int qtdeImagens, String path)
			throws FileNotFoundException {
		this.qtdeImagens = qtdeImagens;

		ImagemSatelite[] objetos = CriarArrayImagens(path, "JPG", qtdeImagens);

		this.crescente = objetos.clone(); 
		this.embaralhado = crescenteToEmbaralhado(objetos.clone());
		this.decrescente = crescenteToReverso(objetos.clone());
	}
	
	public ImagemSatelite[] getImagensOrdem(TipoCriterioOrdem tipoCriterioOrdem) {
		switch (tipoCriterioOrdem) {
		case crescente:
			return crescente;
		case decrescente:
			return decrescente;
		case embaralhado:
			return embaralhado;
		default:
			return embaralhado;
		}
	}

	public int qtdeImagens() {
		return qtdeImagens;
	}

	private ImagemSatelite[] crescenteToEmbaralhado(ImagemSatelite[] origem) {
		ImagemSatelite[] retorno = new ImagemSatelite[origem.length];

		List<ImagemSatelite> lista = Arrays.asList(origem);

		Collections.shuffle(lista);

		int cont = 0;

		Iterator<ImagemSatelite> iterator = lista.iterator();
		while (iterator.hasNext()) {
			retorno[cont] = iterator.next();
			cont++;
		}

		return retorno;
	}

	/**
	 * Retorna o array em ordem reversa
	 */
	private ImagemSatelite[] crescenteToReverso(ImagemSatelite[] origem) {
		ImagemSatelite[] retorno = new ImagemSatelite[origem.length];

		List<ImagemSatelite> lista = Arrays.asList(origem);

		Collections.reverse(lista);

		int cont = 0;

		Iterator<ImagemSatelite> iterator = lista.iterator();
		while (iterator.hasNext()) {
			retorno[cont] = iterator.next();
			cont++;
		}

		return retorno;
	}
	
	/**
	 * Busca as imagens de array em determinado diretório e cria um array de retorno
	 */
	
	private ImagemSatelite[] CriarArrayImagens(String pastaImagens, String extensaoImagem,
			int numeroImagens) throws FileNotFoundException {
		
		int quantidadeImagensCapturadas = 0;
		
		File diretorioImagens = new File(pastaImagens);

		String[] listaArquivos = diretorioImagens.list();

		String extensao = "." + extensaoImagem.toUpperCase();

		ImagemSatelite[] imagensDeSatelite = new ImagemSatelite[numeroImagens];
		
		for (int i = 1; (i < listaArquivos.length) && (i <= numeroImagens); i++) {

			if (listaArquivos[i].toString().toUpperCase().endsWith(extensao)) {
				
				imagensDeSatelite[quantidadeImagensCapturadas] = new ImagemSatelite(pastaImagens+"\\"+listaArquivos[i].toString());
				
				quantidadeImagensCapturadas++;
			}
		}
		return imagensDeSatelite;
	}

}
