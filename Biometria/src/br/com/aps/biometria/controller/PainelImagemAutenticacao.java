package br.com.aps.biometria.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import br.com.aps.biometria.file.FileTransfer;

public class PainelImagemAutenticacao extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel panelName;
	private JLabel labelImage, labelName;
	private JScrollPane scrollImage;
	private FileTransfer fileTransfer;
	private Controller controller;

	public PainelImagemAutenticacao() {
		loadComponents();
		loadLayouts();
		customizeComponents();
		addComponents();
		addListeners();
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	private void loadComponents() {
		panelName = new JPanel();
		labelImage = new JLabel("", JLabel.CENTER);
		labelName = new JLabel(" Arrastar aqui a imagem");
		scrollImage = new JScrollPane();
		fileTransfer = new FileTransfer();
	}

	private void loadLayouts() {
		setLayout(new BorderLayout());
		labelImage.setLayout(new GridLayout());
		panelName.setLayout(new GridLayout());
	}

	private void customizeComponents() {
		setBorder(BorderFactory.createTitledBorder("Autenticação de Imagem"));
		panelName.setBackground(Color.white);
		labelImage.setBackground(Color.white);
		labelImage.setOpaque(true);
		scrollImage.setPreferredSize(new Dimension(200, 0));
		labelName.setPreferredSize(new Dimension(200, 15));
		labelImage.setTransferHandler(fileTransfer);
	}

	private void addComponents() {
		panelName.add(labelName);
		scrollImage.setViewportView(labelImage);
		add(scrollImage, BorderLayout.CENTER);
		add(panelName, BorderLayout.SOUTH);
	}

	private void addImagem(File file) throws IOException {
		addImagem(file.getPath());
		labelName.setText(" " + file.getName());
		fileTransfer.setFileOnDrag(false);
		controller.autenticarImagem(file);
	}

	public void addImagem(String arquivo) {
		ImageIcon imageIcon = new ImageIcon(arquivo);
		labelImage.setIcon(imageIcon);
	}

	public void removeImagem() {
		fileTransfer.removeFile();
		labelImage.setIcon(null);
		labelName.setText(" Arrastar aqui a imagem");
	}
	
	public void setDragEnabled(boolean option) {
		fileTransfer.setDragEnabled(option);
	}

	public void addListeners() {
		labelImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (fileTransfer.isFileOnDrag())
					if (fileTransfer.getFile() != null) {
						controller.getPainelNotificacao().addText(
								"Carregando a imagem do arquivo: "
										+ fileTransfer.getFile());
						try {
							addImagem(fileTransfer.getFile());
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
			}
		});
	}

}
