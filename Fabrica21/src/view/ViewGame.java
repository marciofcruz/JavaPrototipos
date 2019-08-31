package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import controller.ControllerEmpresa;

import java.text.ParseException;

public class ViewGame implements ActionListener {

	public JButton btnComprarMateriaPrima;
	public JButton btnOrdemProducao;
	public JButton btnVenderProdutoAcabado;
	public JButton btnPalestraSustentabilidade;
	public JButton btnFiltroFumaca;
	public JButton btnReciclagem;
	public JButton btnEcoEficiencia;

	public PainelComImagem areaImagem;

	private ControllerEmpresa controllerEmpresa;
	private AtualizarGUI atualizarGUI;

	public ViewGame() throws ParseException {
		areaImagem = new PainelComImagem();
		areaImagem.setBounds(10, 10, 600, 390);
		areaImagem.setBackground(Color.WHITE);
		
		controllerEmpresa = new ControllerEmpresa();

		atualizarGUI = new AtualizarGUI(this, controllerEmpresa);

		new AgendadorTarefa(controllerEmpresa, atualizarGUI);
	}

	public static void main(String[] args) {
		ViewGame b;
		try {
			b = new ViewGame();
			b.janela();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public void janela() {
		JFrame frmJanela = new JFrame("Fabrica 21");
		frmJanela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJanela.setResizable(false);
		frmJanela.setLayout(null);

		/*
		 * Container Principal que contém todos os Componentes
		 */
		JPanel jpnContainer = new JPanel();
		jpnContainer.setBounds(0, 0, 800, 500);
		jpnContainer.setLayout(null);

		JPanel areaBotoes = new JPanel();
		areaBotoes.setBounds(10, 410, 600, 102);
		areaBotoes.setBackground(Color.WHITE);
		areaBotoes.setLayout(null);

		int left;
		int width;

		left = 3;
		width = 83;

		btnComprarMateriaPrima = new JButton("");
		btnComprarMateriaPrima.setIcon(new javax.swing.ImageIcon(atualizarGUI
				.getPathImagem() + "comprarmateriaprima.png"));
		btnComprarMateriaPrima.setBounds(left, 10, width, width);
		btnComprarMateriaPrima
				.setToolTipText("Clique aqui para comprar 50 Kg Matéria Prima para produção");
		btnComprarMateriaPrima.setEnabled(false);
		btnComprarMateriaPrima.addActionListener(this);

		left += width + 2;
		btnOrdemProducao = new JButton("");
		btnOrdemProducao.setIcon(new javax.swing.ImageIcon(atualizarGUI
				.getPathImagem() + "ordemproducao.png"));
		btnOrdemProducao.setBounds(left, 10, width, width);
		btnOrdemProducao
				.setToolTipText("Clique aqui para fabricar 5 peças de produto acabado!");
		btnOrdemProducao.setEnabled(false);
		btnOrdemProducao.addActionListener(this);

		left += width + 2;
		btnVenderProdutoAcabado = new JButton("");
		btnVenderProdutoAcabado.setIcon(new javax.swing.ImageIcon(atualizarGUI
				.getPathImagem() + "vender.png"));
		btnVenderProdutoAcabado.setBounds(left, 10, width, width);
		btnVenderProdutoAcabado
				.setToolTipText("Clique aqui para vender 5 peças de produto acabado!");
		btnVenderProdutoAcabado.setEnabled(false);
		btnVenderProdutoAcabado.addActionListener(this);

		left += width + 2;
		btnPalestraSustentabilidade = new JButton("");
		btnPalestraSustentabilidade.setIcon(new javax.swing.ImageIcon(atualizarGUI
				.getPathImagem() + "palestrasustentabilidade.png"));
		btnPalestraSustentabilidade.setBounds(left, 10, width, width);
		btnPalestraSustentabilidade
				.setToolTipText("Investir em Palestra de sustentabilidade!");
		btnPalestraSustentabilidade.setEnabled(false);
		btnPalestraSustentabilidade.addActionListener(this);
		btnPalestraSustentabilidade.setBackground(Color.GREEN);

		left += width + 2;
		btnFiltroFumaca = new JButton("");
		btnFiltroFumaca.setIcon(new javax.swing.ImageIcon(atualizarGUI
				.getPathImagem() + "filtro.png"));
		btnFiltroFumaca.setBounds(left, 10, width, width);
		btnFiltroFumaca
				.setToolTipText("Investir em Filtros para reduzir emissão de gases tóxicos!");
		btnFiltroFumaca.setEnabled(false);
		btnFiltroFumaca.addActionListener(this);
		btnFiltroFumaca.setBackground(Color.GREEN);

		left += width + 2;
		btnReciclagem = new JButton("");
		btnReciclagem.setIcon(new javax.swing.ImageIcon(atualizarGUI
				.getPathImagem() + "reciclagem.png"));
		btnReciclagem.setBounds(left, 10, width, width);
		btnReciclagem.setToolTipText("Investir em programas de Reciclagem!");
		btnReciclagem.setEnabled(false);
		btnReciclagem.addActionListener(this);
		btnReciclagem.setBackground(Color.GREEN);

		left += width + 2;
		btnEcoEficiencia = new JButton("");
		btnEcoEficiencia.setIcon(new javax.swing.ImageIcon(atualizarGUI
				.getPathImagem() + "ecodesenvolvimento.png"));
		btnEcoEficiencia.setBounds(left, 10, width, width);
		btnEcoEficiencia
				.setToolTipText("Investir em programas de Ecodesenvolvimento!");
		btnEcoEficiencia.setEnabled(false);
		btnEcoEficiencia.addActionListener(this);
		btnEcoEficiencia.setBackground(Color.GREEN);


		// == ADICIONAR COMPONENTES =================
		frmJanela.getContentPane().add(areaImagem);
		frmJanela.getContentPane().add(areaBotoes);

		areaBotoes.add(btnComprarMateriaPrima);
		areaBotoes.add(btnOrdemProducao);
		areaBotoes.add(btnVenderProdutoAcabado);
		areaBotoes.add(btnPalestraSustentabilidade);
		areaBotoes.add(btnFiltroFumaca);
		areaBotoes.add(btnReciclagem);
		areaBotoes.add(btnEcoEficiencia);
		
		frmJanela.getContentPane().add(jpnContainer);
		// ====================

		frmJanela.setSize(630, 550);
		frmJanela.setLocationRelativeTo(null);
		frmJanela.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String retorno = "";
		
		if (e.getSource() == btnComprarMateriaPrima) {
			retorno = controllerEmpresa.comprarMateriaPrima();
		} else if (e.getSource() == btnOrdemProducao) {
			retorno = controllerEmpresa.executarOrdemProducao();
		} else if (e.getSource() == btnVenderProdutoAcabado) {
			retorno = controllerEmpresa.venderProdutoAcabado();
		} else if (e.getSource() == btnPalestraSustentabilidade) {
			retorno = controllerEmpresa.palestraSustentabilidade();
		} else if (e.getSource() == btnFiltroFumaca) {
			retorno = controllerEmpresa.filtroFumaca();
		} else if (e.getSource() == btnReciclagem) {
			retorno = controllerEmpresa.reciclagem();
		} else if (e.getSource() == btnEcoEficiencia) {
			retorno = controllerEmpresa.ecoEficiencia();
		}
		
		if (! retorno.isEmpty()) {
			areaImagem.setMensagemAviso(retorno);
		}

		try {
			atualizarGUI.atualizar("");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
