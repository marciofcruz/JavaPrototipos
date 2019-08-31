package controller;

import java.io.InputStream;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import model.FuncoesGerais;

public class Recebedor implements Runnable {

	private InputStream servidor;
	private JTextArea textArea;

	public Recebedor(InputStream servidor, JTextArea textArea) {
		this.servidor = servidor;
		this.textArea = textArea;
	}

	public void run() {
		// recebe msgs do servidor e imprime na tela
		Scanner s = new Scanner(this.servidor);
		while (s.hasNextLine()) {
			String msg = s.nextLine()+"\n";
			
			try {
				if (FuncoesGerais.podeIncluirLinhaTextArea(textArea, msg)) {
					textArea.append(msg);
				}
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		textArea.setCaretPosition(textArea.getText().length());
	}
}