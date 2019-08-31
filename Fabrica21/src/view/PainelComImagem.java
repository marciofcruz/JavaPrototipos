package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class PainelComImagem extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private String notaNatureza = "";
	private String saldoAtual = "";
	private String estoqueMateriaPrima = "";
	private String estoqueProdutoAcabado = "";
	private String dataCompleta = "";
	private String operacao = "";

	public String getNotaNatureza() {
		return notaNatureza;
	}

	public void setNotaNatureza(String notaNatureza) {
		this.notaNatureza = notaNatureza;
	}

	private BufferedImage imagemFundo = null;
	private String mensagemAviso = "";
	private String mensagemFimJogo = "";

	public void setMensagemFimJogo(String mensagemFimJogo) {
		this.mensagemFimJogo = mensagemFimJogo;
	}

	public void setMensagemAviso(String mensagemAviso) {
		this.mensagemAviso = mensagemAviso;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();

		g2d.drawImage(imagemFundo, 0, 0, this.getWidth(), this.getHeight(),
				null);

		if (!mensagemAviso.isEmpty()) {
			g2d.setColor(Color.BLUE);
			g2d.drawString("Aviso: " + mensagemAviso, 110, 50);

			mensagemAviso = "";
		} else if (!mensagemFimJogo.isEmpty()) {
			g2d.setColor(Color.BLUE);
			g2d.drawString(mensagemFimJogo, 110, 50);
		}
		
		g2d.setColor(Color.BLUE);
		g2d.drawString("Natureza: " +getNotaNatureza(), 05, 360);
		
		g2d.setColor(Color.BLUE);
		g2d.drawString("Saldo R$: " +getSaldoAtual(), 05, 380);
		
		g2d.setColor(Color.BLUE);
		g2d.drawString("Matéria Prima: " +getEstoqueMateriaPrima(), 150, 360);
		
		g2d.setColor(Color.BLUE);
		g2d.drawString("Produto Acabado: " +getEstoqueProdutoAcabado(), 150, 380);
		
		g2d.setColor(Color.BLUE);
		g2d.drawString(getDataCompleta(), 300, 360);
		
		g2d.setColor(Color.BLUE);
		g2d.drawString(getOperacao(), 300, 380);

		g2d.dispose();
	}

	public void setImagem(BufferedImage bufferedImage) {
		if (bufferedImage == null) {
			throw new NullPointerException("Imagem inválida!");
		}

		this.imagemFundo = bufferedImage;

		repaint();
	}

	/**
	 * @return the saldoAtual
	 */
	public String getSaldoAtual() {
		return saldoAtual;
	}

	/**
	 * @param saldoAtual the saldoAtual to set
	 */
	public void setSaldoAtual(String saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	/**
	 * @return the estoqueMateriaPrima
	 */
	public String getEstoqueMateriaPrima() {
		return estoqueMateriaPrima;
	}

	/**
	 * @param estoqueMateriaPrima the estoqueMateriaPrima to set
	 */
	public void setEstoqueMateriaPrima(String estoqueMateriaPrima) {
		this.estoqueMateriaPrima = estoqueMateriaPrima;
	}

	/**
	 * @return the estoqueProdutoAcabado
	 */
	public String getEstoqueProdutoAcabado() {
		return estoqueProdutoAcabado;
	}

	/**
	 * @param estoqueProdutoAcabado the estoqueProdutoAcabado to set
	 */
	public void setEstoqueProdutoAcabado(String estoqueProdutoAcabado) {
		this.estoqueProdutoAcabado = estoqueProdutoAcabado;
	}

	/**
	 * @return the dataCompleta
	 */
	public String getDataCompleta() {
		return dataCompleta;
	}

	/**
	 * @param dataCompleta the dataCompleta to set
	 */
	public void setDataCompleta(String dataCompleta) {
		this.dataCompleta = dataCompleta;
	}

	/**
	 * @return the operacao
	 */
	public String getOperacao() {
		return operacao;
	}

	/**
	 * @param operacao the operacao to set
	 */
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}
}
