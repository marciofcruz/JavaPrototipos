package view;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.Timer;

import controller.ControllerEmpresa;

/**
 * Camada: View; Classe responsável para chamar o timer do sistema, fazer nova
 * jogada e fazer a chamada para atualização dos componentes
 */

public class AgendadorTarefa {

	private Timer timer;
	private ControllerEmpresa controllerEmpresa;
	private AtualizarGUI atualizarGUI;

	public AgendadorTarefa(ControllerEmpresa camadaController,
			AtualizarGUI atualizarGUI) {
		super();

		ActionListener action = new ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {

				try {
					novaJogada();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		};

		this.atualizarGUI = atualizarGUI;

		this.timer = new Timer(1500, action); // rodar a cada 3 segundos
		timer.start();

		this.controllerEmpresa = camadaController;
	}

	private void novaJogada() throws IOException {
		String retorno = controllerEmpresa.novaJogada();
		timer.stop();
		
		atualizarGUI.atualizar(retorno);
		
		if (retorno.isEmpty()) {
			timer.start();
		}
	}
}
