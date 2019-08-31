package view;

import java.awt.Color;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

import controller.ControllerBichos;

public class TimerQuantidadeClientesConectados {

	private ControllerBichos controller;
	private JLabel lblEstadoConexao;
	private Timer timer;

	private void executar() {
		lblEstadoConexao.setForeground(Color.BLUE);

		int auxiliar = controller.getQuantidadeClientesConectados();

		lblEstadoConexao.setText("CLIENTES CONECTADOS: " + auxiliar);
	}

	public TimerQuantidadeClientesConectados(ControllerBichos controller,
			JLabel lblEstadoConexao) {
		this.controller = controller;
		this.lblEstadoConexao = lblEstadoConexao;

		ActionListener action = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				executar();
			}
		};

		this.timer = new Timer(1000 * 2, action); // rodar a cada 2 segundos
		timer.start();
	}

}
