package model;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

public class FuncoesGerais {
	
	private static String pathSistema = System.getProperty("user.dir")+"\\";
	
	private static String pathBaseDados = pathSistema + "basedados\\";	
	private static String pathImagens = pathSistema + "imagens\\";	
	
	public static String getPathSistema() {
		return pathSistema;
	}
	
	public static String getNomeArquivoCatalogado (){
		return pathBaseDados + "animais.dat";
	}
	
	public static String getPathImagens() {
		return pathImagens;
	}
	
	public static boolean podeIncluirLinhaTextArea(JTextArea textArea, String msg) throws BadLocationException {
		int qtdeLinhas = textArea.getLineCount();
		boolean encontrou = false;
		
		for(int i = 0; i < qtdeLinhas; i++) {
			int inicio = textArea.getLineStartOffset(i);
			int fim = textArea.getLineEndOffset(i);
			
			String linha = textArea.getText(inicio, fim - inicio);
			
			if (linha.equals(msg)) {
				encontrou = true;
				break;
			}
		}
		
		return !encontrou;
	}
	
}
