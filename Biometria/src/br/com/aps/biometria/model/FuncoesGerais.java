package br.com.aps.biometria.model;

public class FuncoesGerais {

	private static String pathSistema = System.getProperty("user.dir") + "\\";

	private static String pathBaseDados = pathSistema + "basedados\\";
	private static String pathDigitais = pathSistema + "digitais\\";
	private static String pathImage = pathSistema + "image\\";

	public static String getPathImage() {
		return pathImage;
	}

	public static String getPathSistema() {
		return pathSistema;
	}

	public static String getNomeArquivoRegistroFrequencia() {
		return pathBaseDados + "registrofrequencia.dat";
	}

	public static String getPathDigitais() {
		return pathDigitais;
	}

	public static String getImagemNaoLocalizada() {
		return pathImage + "naolocalizada.png";
	}

	public static String getImagemPadrao() {
		return pathImage + "telapadrao.png";
	}

	public static String getImagemArrastar() {
		return pathImage + "arrastar.png";
	}

	public static String getLogo() {
		return pathImage + "logo.png";
	}
	
}
