package controller;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

public class Cliente {

	private String host;
	private int porta;
	private JTextArea textArea;
	private Socket socket;
	
	public boolean getEstahConectado() {
		return (socket != null && socket.isConnected());
	}

	public Cliente(String host, int porta, JTextArea textArea)
			throws UnknownHostException, IOException {
		this.host = host;
		this.porta = porta;
		this.textArea = textArea;
		
		socket = new Socket(this.host, this.porta);

		Recebedor r = new Recebedor(socket.getInputStream(), this.textArea);
		new Thread(r).start();
	}

	public void enviarMensagem(String mensagem) throws UnknownHostException,
			IOException {

		// thread para receber mensagens do servidor
		PrintStream saida = new PrintStream(socket.getOutputStream());
		saida.println(mensagem);
		
		textArea.setCaretPosition(textArea.getText().length());
	}
}
