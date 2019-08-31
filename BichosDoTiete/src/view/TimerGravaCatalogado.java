package view;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import controller.ControllerBichos;

public class TimerGravaCatalogado {

	private Timer timer;
	
	private ControllerBichos controller;

	public TimerGravaCatalogado(ControllerBichos controller) {
		super();

		ActionListener action = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				try {
					executar();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};

		this.controller = controller;

		this.timer = new Timer(1000*10, action); // rodar a cada 10 segundos
		timer.start();
	}

	private void executar() throws IOException {
		controller.setListaCatalogado();
	}

}
