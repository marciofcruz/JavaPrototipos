package br.com.aps.biometria.controller;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileFilter;

import br.com.aps.biometria.model.FuncoesGerais;

public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private PainelImagemAutenticacao painelImagemAutenticacao;
	private PainelHistograma painelHistogram;
	private PainelImagemDB painelImagemDB;
	private PainelNotificacao painelNotificacao;
	private JSlider controleDeTempoDeBusca;

	private JMenuBar menuBar;
	private JMenu menuOpcoes;
	private JMenu menuEditar;
	private JMenuItem itemImportarDB;
	private JMenuItem itemExportarPlanilha;
	private JMenuItem itemSair;
	private JMenuItem itemLimparTela;

	private int tempoDeBusca;
	private GridBagConstraints gbc;
	private Controller controller = new Controller();

	public MainMenu() throws Exception {
		loadComponents();
		loadLayouts();
		customizeComponents();
		addComponents();
		addListeners();
		limparTela();
		setVisible(true);
	}

	private void loadComponents() throws Exception {
		gbc = new GridBagConstraints();
		painelImagemAutenticacao = new PainelImagemAutenticacao();
		painelImagemDB = new PainelImagemDB();
		painelHistogram = new PainelHistograma();
		painelNotificacao = new PainelNotificacao();
		controleDeTempoDeBusca = new JSlider(JSlider.HORIZONTAL, 0, 1000,
				(tempoDeBusca = 100));
		menuBar = new JMenuBar();
		menuOpcoes = new JMenu("Opções");
		menuEditar = new JMenu("Editar");

		itemImportarDB = new JMenuItem("Importar banco de imagens");
		itemExportarPlanilha = new JMenuItem("Exportar planilha eletrônica");
		itemSair = new JMenuItem("Sair");
		itemLimparTela = new JMenuItem("Limpar Tela");

		controller.setPainelImagemDB(painelImagemDB);
		controller.setPainelNotificacao(painelNotificacao);
		controller.setPainelHistograma(painelHistogram);
		controller.setMainMenu(this);
		controller.carregarListaFrequencia();

		painelImagemAutenticacao.setController(controller);
	}

	private void loadLayouts() {
		getContentPane().setLayout(new GridBagLayout());
	}

	private void customizeComponents() {
		setIconImage(new ImageIcon(FuncoesGerais.getLogo()).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("APS 2014 - Comparação de Histograma de imagem biométrica - Marcio, Diego e Thiago");
		setSize(1020, 760);
		setMinimumSize(new Dimension(1020, 760));
		setLocationRelativeTo(null);
		setResizable(true);
		menuBar.setPreferredSize(new Dimension(0, 30));
		controleDeTempoDeBusca.setMajorTickSpacing(10);
		controleDeTempoDeBusca.setPaintTicks(true);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(0, new JLabel("0 seg/digital"));
		labelTable.put(tempoDeBusca, new JLabel("Padrão"));
		labelTable.put(1000, new JLabel("1 seg/digital"));
		controleDeTempoDeBusca.setLabelTable(labelTable);
		controleDeTempoDeBusca.setPaintLabels(true);
	}

	private void addComponents() {
		setJMenuBar(menuBar);
		menuBar.add(menuOpcoes);
		menuOpcoes.add(itemImportarDB);
		menuOpcoes.add(itemExportarPlanilha);
		menuOpcoes.add(itemSair);
		menuBar.add(menuEditar);
		menuEditar.add(itemLimparTela);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.anchor = GridBagConstraints.NORTHWEST;

		gbc.weightx = 300;
		gbc.weighty = 20;
		gbc.gridwidth = 3;
		gbc.gridheight = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 0);
		getContentPane().add(controleDeTempoDeBusca, gbc);

		gbc.weightx = 300;
		gbc.weighty = 300;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(8, 8, 0, 0);
		getContentPane().add(painelImagemAutenticacao, gbc);

		gbc.weightx = 300;
		gbc.weighty = 300;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.insets = new Insets(8, 8, 8, 0);
		getContentPane().add(painelImagemDB, gbc);

		gbc.weightx = 300;
		gbc.weighty = 300;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 2;
		gbc.gridy = 1;
		gbc.insets = new Insets(8, 8, 0, 8);
		getContentPane().add(painelHistogram, gbc);

		gbc.weightx = 300;
		gbc.weighty = 300;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.insets = new Insets(8, 8, 8, 8);
		getContentPane().add(painelNotificacao, gbc);
	}

	public void addListeners() {
		itemSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		itemImportarDB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.gerarBancoDeDadosImagem();
				} catch (Exception e1) {
					painelNotificacao.addText("Base de dados não encontrada..");
					e1.printStackTrace();
				}
			}
		});

		itemExportarPlanilha.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exportarExcel();
			}
		});

		itemLimparTela.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limparTela();
			}
		});

		controleDeTempoDeBusca.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent evt) {
				tempoDeBusca = controleDeTempoDeBusca.getValue();
			}
		});
	}

	public void ativarComandos(boolean option) {
		painelImagemAutenticacao.setDragEnabled(option);
		menuEditar.setEnabled(option);
		menuOpcoes.setEnabled(option);
	}

	public void limparTela() {
		painelImagemAutenticacao.removeImagem();
		painelImagemAutenticacao.addImagem(FuncoesGerais.getImagemArrastar());
		painelImagemDB.addImagem(FuncoesGerais.getImagemPadrao());
		painelHistogram.clear();
		painelNotificacao.clear();
	}

	private void exportarExcel() {
		try {
			JFileChooser saveFile = new JFileChooser();// new save dialog
			saveFile.setAcceptAllFileFilterUsed(false);
			saveFile.setDialogType(JFileChooser.SAVE_DIALOG);
			saveFile.setDialogTitle("Exportar usuários com digitais do Banco de Dados");

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
			int result = saveFile.showSaveDialog(this);

			if (result == JFileChooser.APPROVE_OPTION) {
				String strFileName = saveFile.getSelectedFile().getName();

				if (!strFileName.isEmpty()) {
					if (!strFileName.toLowerCase().endsWith("xlsx"))
						throw new IOException(
								"Arquivo deve ter a extensão xlsx");

					String nomeArquivo = saveFile.getSelectedFile()
							.getAbsoluteFile().toString();

					controller.exportarPlanilha(nomeArquivo);
				}
			}

		} catch (Exception er) {
			JOptionPane.showMessageDialog(this, er.getMessage(), "Erro", 0);
		}
	}

	public int getTempoDeBusca() {
		return tempoDeBusca;
	}

}
