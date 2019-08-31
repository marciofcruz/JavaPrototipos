package view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.Timer;

import controller.ControllerBichos;

public class TimerVerificaConexaoCliente {

	private ControllerBichos controller;
	private JLabel lblEstadoConexao;
	private Timer timer;

	private void executar() {
		if (controller.getClienteEstahConectado()) {
			lblEstadoConexao.setForeground(Color.GREEN);
			lblEstadoConexao.setText("CONECTADO COM O SERVIDOR");
		} else {
			lblEstadoConexao.setForeground(Color.red);
			lblEstadoConexao.setText("SEM CONEXÃO COM O SERVIDOR");
		}
	}

	public TimerVerificaConexaoCliente(ControllerBichos controller,
			JLabel lblEstadoConexao) {
		this.controller = controller;
		this.lblEstadoConexao = lblEstadoConexao;

		ActionListener action = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				executar();
			}
		};
		
		this.timer = new Timer(1000*2, action); // rodar a cada 2 segundos
		timer.start();
	}
}
