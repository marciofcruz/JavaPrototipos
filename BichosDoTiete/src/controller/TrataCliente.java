package controller;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.text.BadLocationException;

public class TrataCliente implements Runnable {
 
   private InputStream cliente;
   private Servidor servidor;
 
   public TrataCliente(InputStream cliente, Servidor servidor) {
     this.cliente = cliente;
     this.servidor = servidor;
   }
 
   public void run() {
     // quando chegar uma msg, distribui pra todos
     Scanner s = new Scanner(this.cliente);
     while (s.hasNextLine()) {
       try {
		servidor.distribuiMensagem(s.nextLine());
	} catch (BadLocationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     }
     s.close();
   }
 }