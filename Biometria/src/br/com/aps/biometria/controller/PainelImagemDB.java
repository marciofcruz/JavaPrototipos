package br.com.aps.biometria.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PainelImagemDB extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelName;
	private JScrollPane scrollImage;
	private JLabel labelImage, labelName;

	public PainelImagemDB() {
		loadComponents();
		loadLayouts();
		customizeComponents();
		addComponents();
	}

	private void loadComponents() {
		panelName = new JPanel();
		labelImage = new JLabel("", JLabel.CENTER);
		labelName = new JLabel(" Imagem não encontrada..");
		scrollImage = new JScrollPane();
	}

	private void loadLayouts() {
		setLayout(new BorderLayout());
		labelImage.setLayout(new GridLayout());
		panelName.setLayout(new GridLayout());
	}

	private void customizeComponents() {
		setBorder(BorderFactory.createTitledBorder("Imagem BD"));
		panelName.setBackground(Color.white);
		labelImage.setBackground(Color.white);
		labelImage.setOpaque(true);
		scrollImage.setPreferredSize(new Dimension(200, 0));
		labelName.setPreferredSize(new Dimension(200, 15));
	}

	private void addComponents() {
		panelName.add(labelName);
		scrollImage.setViewportView(labelImage);
		add(scrollImage, BorderLayout.CENTER);
		add(panelName, BorderLayout.SOUTH);
	}

	public void addImagem(String path) {
		ImageIcon imageIcon = new ImageIcon(path);
		labelName.setText(" Nome");
		labelImage.setIcon(imageIcon);
	}

	public void removeImage() {
		labelName.setText(null);
		labelImage.setIcon(null);
	}

	public void setNome(String nome) {
		labelName.setText(nome);
	}
}
