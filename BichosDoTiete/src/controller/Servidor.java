package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import model.FuncoesGerais;

public class Servidor {

	private int porta;
	private List<PrintStream> clientes;
	private JTextArea textArea;

	public Servidor(int porta, JTextArea textArea) {
		this.porta = porta;
		this.textArea = textArea;
		this.clientes = new ArrayList<PrintStream>();
	}

	public void executa() throws IOException {
		ServerSocket servidor = new ServerSocket(this.porta);
		textArea.append("Conexão aberta através da porta " + porta + ".\n");
		
		textArea.setCaretPosition(textArea.getText().length());

		while (true) {
			Socket cliente = servidor.accept();
			textArea.append("Nova conexão com o cliente "
					+ cliente.getInetAddress().getHostAddress()+"\n");

			PrintStream ps = new PrintStream(cliente.getOutputStream());
			this.clientes.add(ps);

			TrataCliente tc = new TrataCliente(cliente.getInputStream(), this);
			new Thread(tc).start();
			
			textArea.setCaretPosition(textArea.getText().length());
		}
	}

	public void distribuiMensagem(String msg) throws BadLocationException {
		// envia msg para todo mundo
		if (FuncoesGerais.podeIncluirLinhaTextArea(textArea, msg+"\n")) {
			textArea.append(msg+"\n");
		}
		
		for (PrintStream p : this.clientes) {
			p.println(msg+"\n");
		}
		
		textArea.setCaretPosition(textArea.getText().length());
	}
	
	public int getQtdeClientes () {
		return clientes.size();
	}
}