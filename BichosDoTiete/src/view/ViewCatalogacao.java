package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

import controller.ControllerBichos;

import tipos.Animal;
import tipos.Configuracao;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class ViewCatalogacao implements ActionListener {

	private ControllerBichos controller;
	private JFrame frmJanela;

	private Configuracao configuracao;

	private JButton btnAnfibio;
	private JButton btnAvesSilvestres;
	private JButton btnCachorro;
	private JButton btnCapivara;
	private JButton btnCobra;
	private JButton btnCoruja;
	private JButton btnGato;
	private JButton btnMamiferosSilvestres;

	private JLabel lblCatalogadoAnfibio;
	private JLabel lblCatalogadoAvesSilvestres;
	private JLabel lblCatalogadoCachorro;
	private JLabel lblCatalogadoCapivara;
	private JLabel lblCatalogadoCobra;
	private JLabel lblCatalogadoCoruja;
	private JLabel lblCatalogadoGato;
	private JLabel lblCatalogadoMamiferosSilvestres;

	private JButton btnExportarExcel;
	private JLabel lblEstadoConexao;

	private JLabel lblLocalizacao;
	private JTextField edtLocalizacao;

	private JLabel lblUsuario;
	private JTextField edtUsuario;

	private JLabel lblHost;
	private JTextField edtHost;

	private JLabel lblPorta;
	private JTextField edtPorta;

	private JCheckBox ckbEhServidor;

	private JTextArea textArea;
	private JScrollPane jscroolPane;

	public ViewCatalogacao() throws ParseException {
	}

	private void carregarConfiguracao() throws ClassNotFoundException,
			IOException {
		configuracao = controller.getConfiguracao();
		
		edtLocalizacao.setText(configuracao.getLocalizacao());
		edtUsuario.setText(configuracao.getUsuario());
		edtHost.setText(configuracao.getHost());
		edtPorta.setText(configuracao.getPorta());
		ckbEhServidor.setSelected(configuracao.getEhServidor());
	}

	public static void main(String[] args) throws ClassNotFoundException,
			IOException {
		ViewCatalogacao b;
		try {
			b = new ViewCatalogacao();
			b.janela();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void janela() throws ClassNotFoundException, IOException {
		frmJanela = new JFrame("Projeto Bichos do Tietê - 2014");
		frmJanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJanela.setResizable(false);
		frmJanela.setLayout(null);
		frmJanela.setBackground(Color.darkGray);

		textArea = new JTextArea(5, 200);
		textArea.setColumns(60);
		textArea.setLineWrap(true);
		textArea.setRows(18);
		textArea.setEditable(false);
		textArea.setWrapStyleWord(true);
		textArea.setForeground(Color.green);
		textArea.setBackground(Color.black);

		jscroolPane = new JScrollPane(textArea);

		JPanel jpnContainer = new JPanel();
		jpnContainer.setBounds(0, 0, 700, 630);
		jpnContainer.setLayout(null);
		jpnContainer.setBackground(Color.darkGray);

		JPanel areaConfig = new JPanel();
		areaConfig.setBounds(05, 05, 685, 50);
		areaConfig.setBackground(Color.BLACK);
		areaConfig.setLayout(null);

		JPanel areaBotoes = new JPanel();
		areaBotoes.setBounds(05, 59, 685, 115);
		areaBotoes.setBackground(Color.BLACK);
		areaBotoes.setLayout(null);

		JPanel areaMemo = new JPanel();
		areaMemo.setBounds(05, 177, 685, 300);
		areaMemo.setBackground(Color.BLACK);

		JPanel areaOperacao = new JPanel();
		areaOperacao.setBounds(05, 475, 685, 470);
		areaOperacao.setBackground(Color.BLACK);
		areaOperacao.setLayout(null);

		int left;
		int width;

		left = 3;
		lblLocalizacao = new JLabel("Localização:");
		lblLocalizacao.setLocation(left, 10);
		lblLocalizacao.setSize(75, 15);
		lblLocalizacao.setForeground(Color.white);

		lblHost = new JLabel("Host:");
		lblHost.setLocation(left + 42, 30);
		lblHost.setSize(75, 15);
		lblHost.setForeground(Color.white);

		left += 75;
		edtLocalizacao = new JTextField();
		edtLocalizacao.setLocation(left, 10);
		edtLocalizacao.setSize(220, 15);
		edtLocalizacao.setForeground(Color.white);
		edtLocalizacao.setBackground(Color.black);

		edtHost = new JTextField();
		edtHost.setLocation(left, 30);
		edtHost.setSize(120, 15);
		edtHost.setForeground(Color.white);
		edtHost.setBackground(Color.black);

		left += 240;
		lblUsuario = new JLabel("Usuário:");
		lblUsuario.setLocation(left, 10);
		lblUsuario.setSize(50, 15);
		lblUsuario.setForeground(Color.white);

		lblPorta = new JLabel("Porta:");
		lblPorta.setLocation(left + 13, 30);
		lblPorta.setSize(50, 15);
		lblPorta.setForeground(Color.white);

		left += 50;
		edtUsuario = new JTextField();
		edtUsuario.setLocation(left, 10);
		edtUsuario.setSize(220, 15);
		edtUsuario.setForeground(Color.white);
		edtUsuario.setBackground(Color.black);

		edtPorta = new JTextField();
		edtPorta.setLocation(left, 30);
		edtPorta.setSize(40, 15);
		edtPorta.setForeground(Color.white);
		edtPorta.setBackground(Color.black);

		ckbEhServidor = new JCheckBox("Esta máquina é o Servidor", false);
		ckbEhServidor.setLocation(left + 50, 30);
		ckbEhServidor.setForeground(Color.white);
		ckbEhServidor.setBackground(Color.black);
		ckbEhServidor.setSize(190, 15);
		ckbEhServidor.addActionListener(this);

		left = 3;
		width = 83;

		btnAnfibio = new JButton("");
		btnAnfibio.setIcon(new javax.swing.ImageIcon(Animal
				.getNomeImagemSmall(Animal.anfibio)));
		btnAnfibio.setBounds(left, 10, width, width);
		btnAnfibio.setToolTipText("Catalogar um Anfíbio");
		btnAnfibio.addActionListener(this);

		lblCatalogadoAnfibio = new JLabel("Qtde:");
		lblCatalogadoAnfibio.setLocation(left, 95);
		lblCatalogadoAnfibio.setSize(width, 15);
		lblCatalogadoAnfibio.setForeground(Color.white);

		left += width + 2;
		btnAvesSilvestres = new JButton("");
		btnAvesSilvestres.setIcon(new javax.swing.ImageIcon(Animal
				.getNomeImagemSmall(Animal.avesSilvestres)));
		btnAvesSilvestres.setBounds(left, 10, width, width);
		btnAvesSilvestres.setToolTipText("Catalogar uma Ave Silvestre");
		btnAvesSilvestres.addActionListener(this);

		lblCatalogadoAvesSilvestres = new JLabel("Qtde:");
		lblCatalogadoAvesSilvestres.setLocation(left, 95);
		lblCatalogadoAvesSilvestres.setSize(width, 15);
		lblCatalogadoAvesSilvestres.setForeground(Color.white);

		left += width + 2;
		btnCachorro = new JButton("");
		btnCachorro.setIcon(new javax.swing.ImageIcon(Animal
				.getNomeImagemSmall(Animal.cachorro)));
		btnCachorro.setBounds(left, 10, width, width);
		btnCachorro.setToolTipText("Catalogar um Cachorro");
		btnCachorro.addActionListener(this);

		lblCatalogadoCachorro = new JLabel("Qtde:");
		lblCatalogadoCachorro.setLocation(left, 95);
		lblCatalogadoCachorro.setSize(width, 15);
		lblCatalogadoCachorro.setForeground(Color.white);

		left += width + 2;
		btnCapivara = new JButton("");
		btnCapivara.setIcon(new javax.swing.ImageIcon(Animal
				.getNomeImagemSmall(Animal.capivara)));
		btnCapivara.setBounds(left, 10, width, width);
		btnCapivara.setToolTipText("Catalogar uma Capivara");
		btnCapivara.addActionListener(this);

		lblCatalogadoCapivara = new JLabel("Qtde:");
		lblCatalogadoCapivara.setLocation(left, 95);
		lblCatalogadoCapivara.setSize(width, 15);
		lblCatalogadoCapivara.setForeground(Color.white);

		left += width + 2;
		btnCobra = new JButton("");
		btnCobra.setIcon(new javax.swing.ImageIcon(Animal
				.getNomeImagemSmall(Animal.cobra)));
		btnCobra.setBounds(left, 10, width, width);
		btnCobra.setToolTipText("Catalogar uma Cobra");
		btnCobra.addActionListener(this);

		lblCatalogadoCobra = new JLabel("Qtde:");
		lblCatalogadoCobra.setLocation(left, 95);
		lblCatalogadoCobra.setSize(width, 15);
		lblCatalogadoCobra.setForeground(Color.white);

		left += width + 2;
		btnCoruja = new JButton("");
		btnCoruja.setIcon(new javax.swing.ImageIcon(Animal
				.getNomeImagemSmall(Animal.coruja)));
		btnCoruja.setBounds(left, 10, width, width);
		btnCoruja.setToolTipText("Catalogar uma Coruja");
		btnCoruja.addActionListener(this);

		lblCatalogadoCoruja = new JLabel("Qtde:");
		lblCatalogadoCoruja.setLocation(left, 95);
		lblCatalogadoCoruja.setSize(width, 15);
		lblCatalogadoCoruja.setForeground(Color.white);

		left += width + 2;
		btnGato = new JButton("");
		btnGato.setIcon(new javax.swing.ImageIcon(Animal
				.getNomeImagemSmall(Animal.gato)));
		btnGato.setBounds(left, 10, width, width);
		btnGato.setToolTipText("Catalogar um Gato");
		btnGato.addActionListener(this);

		lblCatalogadoGato = new JLabel("Qtde:");
		lblCatalogadoGato.setLocation(left, 95);
		lblCatalogadoGato.setSize(width, 15);
		lblCatalogadoGato.setForeground(Color.white);

		left += width + 2;
		btnMamiferosSilvestres = new JButton("");
		btnMamiferosSilvestres.setIcon(new javax.swing.ImageIcon(Animal
				.getNomeImagemSmall(Animal.mamiferosSilvestres)));
		btnMamiferosSilvestres.setBounds(left, 10, width, width);
		btnMamiferosSilvestres
				.setToolTipText("Catalogar um Mamífero Silvestre");
		btnMamiferosSilvestres.addActionListener(this);

		lblCatalogadoMamiferosSilvestres = new JLabel("Qtde:");
		lblCatalogadoMamiferosSilvestres.setLocation(left, 95);
		lblCatalogadoMamiferosSilvestres.setSize(width, 15);
		lblCatalogadoMamiferosSilvestres.setForeground(Color.white);

		controller = new ControllerBichos(textArea);
		new TimerGravaCatalogado(controller);
		
		left = 3;

		btnExportarExcel = new JButton("");
		btnExportarExcel.setIcon(new javax.swing.ImageIcon(controller
				.getPathIconeExcel()));
		btnExportarExcel.setBounds(left, 10, width, width);
		btnExportarExcel
				.setToolTipText("Exportar animais catalogados para uma planilha Excel");
		btnExportarExcel.addActionListener(this);

		lblEstadoConexao = new JLabel("");
		lblEstadoConexao.setLocation(left+150, 10);
		lblEstadoConexao.setSize(300, 15);
		
		// == ADICIONAR COMPONENTES =================
		frmJanela.getContentPane().add(areaConfig);
		frmJanela.getContentPane().add(areaBotoes);
		frmJanela.getContentPane().add(areaMemo);
		frmJanela.getContentPane().add(areaOperacao);

		areaBotoes.add(btnAnfibio);
		areaBotoes.add(lblCatalogadoAnfibio);
		areaBotoes.add(btnAvesSilvestres);
		areaBotoes.add(lblCatalogadoAvesSilvestres);
		areaBotoes.add(btnCachorro);
		areaBotoes.add(lblCatalogadoCachorro);
		areaBotoes.add(btnCapivara);
		areaBotoes.add(lblCatalogadoCapivara);
		areaBotoes.add(btnCobra);
		areaBotoes.add(lblCatalogadoCobra);
		areaBotoes.add(btnCoruja);
		areaBotoes.add(lblCatalogadoCoruja);
		areaBotoes.add(btnGato);
		areaBotoes.add(lblCatalogadoGato);
		areaBotoes.add(btnMamiferosSilvestres);
		areaBotoes.add(lblCatalogadoMamiferosSilvestres);

		areaConfig.add(lblLocalizacao);
		areaConfig.add(edtLocalizacao);
		areaConfig.add(lblUsuario);
		areaConfig.add(edtUsuario);
		areaConfig.add(lblHost);
		areaConfig.add(edtHost);
		areaConfig.add(lblPorta);
		areaConfig.add(edtPorta);
		areaConfig.add(ckbEhServidor);

		areaMemo.add(jscroolPane);

		areaOperacao.add(btnExportarExcel);
		areaOperacao.add(lblEstadoConexao);

		frmJanela.getContentPane().add(jpnContainer);
		// ====================

		frmJanela.setSize(700, 600);
		frmJanela.setLocationRelativeTo(null);
		frmJanela.setVisible(true);

		carregarConfiguracao();
		stualizarLabelQuantidadeRegistrada();

		textArea.setCaretPosition(textArea.getText().length());

		if (configuracao.getEhServidor()) {
			new TimerQuantidadeClientesConectados(controller, lblEstadoConexao);
			controller.tratarServidor(configuracao);	
		}
		else {
			controller.conectarCliente(configuracao);	
			new TimerVerificaConexaoCliente(controller, lblEstadoConexao);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {

			Animal animal = Animal.nenhum;

			if (e.getSource() == ckbEhServidor) {
				controller.setConfiguracao(edtLocalizacao.getText(),
						edtUsuario.getText(), edtHost.getText(),
						edtPorta.getText(), ckbEhServidor.isSelected(), configuracao);
				carregarConfiguracao();
			}
			else if (e.getSource() == btnExportarExcel) {
				exportarExcel();
			} else if (e.getSource() == btnAnfibio) {
				animal = Animal.anfibio;
			} else if (e.getSource() == btnAvesSilvestres) {
				animal = Animal.avesSilvestres;
			} else if (e.getSource() == btnCachorro) {
				animal = Animal.cachorro;
			} else if (e.getSource() == btnCapivara) {
				animal = Animal.capivara;
			} else if (e.getSource() == btnCobra) {
				animal = Animal.cobra;
			} else if (e.getSource() == btnCoruja) {
				animal = Animal.coruja;
			} else if (e.getSource() == btnGato) {
				animal = Animal.gato;
			} else if (e.getSource() == btnMamiferosSilvestres) {
				animal = Animal.mamiferosSilvestres;
			}

			if (animal != Animal.nenhum) {

				if (!controller.getFecharSistema()) {
					controller.setConfiguracao(edtLocalizacao.getText(),
							edtUsuario.getText(), edtHost.getText(),
							edtPorta.getText(), ckbEhServidor.isSelected(), configuracao);
					carregarConfiguracao();

					String observacao = JOptionPane
							.showInputDialog("Observação sobre "
									+ Animal.getDescricao(animal));

					if (observacao != null) {
						observacao = observacao.trim();
						controller.catalogar(animal, configuracao, observacao);

						stualizarLabelQuantidadeRegistrada();
					}

					
				}
			
			}

		} catch (Exception e1) {
			JOptionPane
					.showMessageDialog(frmJanela, e1.getMessage(), "Erro", 0);
		}
	}

	private void stualizarLabelQuantidadeRegistrada() {
		lblCatalogadoAnfibio.setText("Qtd: "
				+ controller.getQtdeCatalogado(Animal.anfibio));
		lblCatalogadoAvesSilvestres.setText("Qtd: "
				+ controller.getQtdeCatalogado(Animal.avesSilvestres));
		lblCatalogadoCachorro.setText("Qtd: "
				+ controller.getQtdeCatalogado(Animal.cachorro));
		lblCatalogadoCapivara.setText("Qtd: "
				+ controller.getQtdeCatalogado(Animal.capivara));
		lblCatalogadoCobra.setText("Qtd: "
				+ controller.getQtdeCatalogado(Animal.cobra));
		lblCatalogadoCoruja.setText("Qtd: "
				+ controller.getQtdeCatalogado(Animal.coruja));
		lblCatalogadoGato.setText("Qtd: "
				+ controller.getQtdeCatalogado(Animal.gato));
		lblCatalogadoMamiferosSilvestres.setText("Qtd: "
				+ controller.getQtdeCatalogado(Animal.mamiferosSilvestres));
	}

	private void exportarExcel() {
		try {
			JFileChooser saveFile = new JFileChooser();// new save dialog
			saveFile.setAcceptAllFileFilterUsed(false);
			saveFile.setDialogType(JFileChooser.SAVE_DIALOG);
			saveFile.setDialogTitle("Exportar animais catalogados");

			saveFile.addChoosableFileFilter(new FileFilter() {

				String description = "Excel (*.xlsx)";
				String extension = "xlsx";

				public String getDescription() {
					return description;
				}

				public boolean accept(File f) {
					if (f == null)
						return false;
					if (f.isDirectory())
						return true;
					return f.getName().toLowerCase().endsWith(extension);
				}
			});
			saveFile.setCurrentDirectory(new File("*.srt"));
			int result = saveFile.showSaveDialog(frmJanela);

			if (result == JFileChooser.APPROVE_OPTION) {
				String strFileName = saveFile.getSelectedFile().getName();

				if (!strFileName.isEmpty()) {
					if (!strFileName.toLowerCase().endsWith("xlsx")) {
						throw new IOException(
								"Arquivo deve ter a extensão xlsx");
					}

					String nomeArquivo = saveFile.getSelectedFile()
							.getAbsoluteFile().toString();

					controller.exportarPlanilha(nomeArquivo);

					JOptionPane.showMessageDialog(frmJanela, "Arquivo "
							+ nomeArquivo + " criado!", "Informação",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}

		} catch (Exception er) {
			JOptionPane
					.showMessageDialog(frmJanela, er.getMessage(), "Erro", 0);
		}

	}

}
