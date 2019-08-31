package br.com.aps.biometria.controller;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainBiometrico {

	public static void main(String[] args) {

		try {
			for (UIManager.LookAndFeelInfo info : UIManager
					.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						new MainMenu();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException ex) {
			System.err.println("Houve um erro na inicialização (CLASSE: MAIN)");
			System.err.println("CAUSA:");
			ex.printStackTrace();
		}
	}

}
