package tipos;

import model.FuncoesGerais;

public enum Animal {
	capivara, mamiferosSilvestres, gato, cobra, avesSilvestres, anfibio, coruja, cachorro, nenhum;

	public static String getNomeImagemBig(Animal animal) {
		switch (animal) {
		case anfibio:
			return FuncoesGerais.getPathImagens() + "anfibio_big.png";
		case avesSilvestres:
			return FuncoesGerais.getPathImagens() + "avesilvestre_big.png";
		case cachorro:
			return FuncoesGerais.getPathImagens() + "cachorro_big.png";
		case capivara:
			return FuncoesGerais.getPathImagens() + "capivara_big.png";
		case cobra:
			return FuncoesGerais.getPathImagens() + "cobra_big.png";
		case coruja:
			return FuncoesGerais.getPathImagens() + "coruja_big.png";
		case gato:
			return FuncoesGerais.getPathImagens() + "gato_big.png";
		case mamiferosSilvestres:
			return FuncoesGerais.getPathImagens() + "mamiferosilvestre_big.png";
		default:
			return null;

		}
	}
	
	public static String getNomeImagemSmall(Animal animal) {
		switch (animal) {
		case anfibio:
			return FuncoesGerais.getPathImagens() + "anfibio_small.png";
		case avesSilvestres:
			return FuncoesGerais.getPathImagens() + "avesilvestre_small.png";
		case cachorro:
			return FuncoesGerais.getPathImagens() + "cachorro_small.png";
		case capivara:
			return FuncoesGerais.getPathImagens() + "capivara_small.png";
		case cobra:
			return FuncoesGerais.getPathImagens() + "cobra_small.png";
		case coruja:
			return FuncoesGerais.getPathImagens() + "coruja_small.png";
		case gato:
			return FuncoesGerais.getPathImagens() + "gato_small.png";
		case mamiferosSilvestres:
			return FuncoesGerais.getPathImagens() + "mamiferosilvestre_small.png";
		default:
			return null;

		}
	}	

	public static String getDescricao(Animal animal) {
		switch (animal) {
		case coruja:
			return "Coruja";
		case capivara:
			return "Capivara";
		case cobra:
			return "Cobra";
		case anfibio:
			return "Anfibio";
		case cachorro:
			return "Cachorro";
		case gato:
			return "Gato";
		case avesSilvestres:
			return "Aves Silvestres";
		case mamiferosSilvestres:
			return "Mamíferos Silvestres";
		default:
			return null;

		}
	}
}
