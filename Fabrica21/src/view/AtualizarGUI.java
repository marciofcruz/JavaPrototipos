package view;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import controller.ControllerEmpresa;

/**
 * Camada: View; 
 * Classe responsável para atualizar os componentes GUI na tela;
 */

public class AtualizarGUI {
	
	private ViewGame viewGame;
	private ControllerEmpresa controllerEmpresa;
	private String pathImagem = System.getProperty("user.dir") + "\\imagens\\";
	
	private BufferedImage imagemAmanhecer;
	private BufferedImage imagemInicioAnoitecer;
	private BufferedImage imagemNoite;
	private BufferedImage imagemManha08diaUtil;
	private BufferedImage imagemManha08fds;
	private BufferedImage imagemManha09diaUtil;
	private BufferedImage imagemManha09fds;
	private BufferedImage imagemManha10diaUtil;
	private BufferedImage imagemManha10fds;
	private BufferedImage imagemManha11diaUtil;
	private BufferedImage imagemManha11fds;
	private BufferedImage imagemManha12diaUtil;
	private BufferedImage imagemManha12fds;
	private BufferedImage imagemTarde13diaUtil;
	private BufferedImage imagemTarde13fds;
	private BufferedImage imagemTarde14diaUtil;
	private BufferedImage imagemTarde14fds;
	private BufferedImage imagemTarde15diaUtil;
	private BufferedImage imagemTarde15fds;
	private BufferedImage imagemTarde16diaUtil;
	private BufferedImage imagemTarde16fds;
	private BufferedImage imagemTarde17diaUtil;
	private BufferedImage imagemTarde17fds;
	private BufferedImage imagemTarde18diaUtil;
	private BufferedImage imagemTarde18fds;
	
	public String getPathImagem() {
		return pathImagem;
	}

	public AtualizarGUI(ViewGame viewGame, ControllerEmpresa controllerEmpresa) {
		super();
		
		try {
			imagemAmanhecer = ImageIO.read(new File(pathImagem+"amanhecer.png"));
			imagemInicioAnoitecer = ImageIO.read(new File(pathImagem+"inicioanoitecer.png"));
			imagemNoite = ImageIO.read(new File(pathImagem+"noite.png"));
			imagemManha08diaUtil = ImageIO.read(new File(pathImagem+"manha08diautil.png"));
			imagemManha08fds = ImageIO.read(new File(pathImagem+"manha08fds.png"));
			imagemManha09diaUtil = ImageIO.read(new File(pathImagem+"manha09diautil.png"));
			imagemManha09fds = ImageIO.read(new File(pathImagem+"manha09fds.png"));
			imagemManha10diaUtil = ImageIO.read(new File(pathImagem+"manha10diautil.png"));
			imagemManha10fds = ImageIO.read(new File(pathImagem+"manha10fds.png"));
			imagemManha11diaUtil = ImageIO.read(new File(pathImagem+"manha11diautil.png"));
			imagemManha11fds = ImageIO.read(new File(pathImagem+"manha11fds.png"));
			imagemManha12diaUtil = ImageIO.read(new File(pathImagem+"manha12diautil.png"));
			imagemManha12fds = ImageIO.read(new File(pathImagem+"manha12fds.png"));
			imagemTarde13diaUtil = ImageIO.read(new File(pathImagem+"tarde13diautil.png"));
			imagemTarde13fds = ImageIO.read(new File(pathImagem+"tarde13fds.png"));
			imagemTarde14diaUtil = ImageIO.read(new File(pathImagem+"tarde14diautil.png"));
			imagemTarde14fds = ImageIO.read(new File(pathImagem+"tarde14fds.png"));
			imagemTarde15diaUtil = ImageIO.read(new File(pathImagem+"tarde15diautil.png"));
			imagemTarde15fds = ImageIO.read(new File(pathImagem+"tarde15fds.png"));
			imagemTarde16diaUtil = ImageIO.read(new File(pathImagem+"tarde16diautil.png"));
			imagemTarde16fds = ImageIO.read(new File(pathImagem+"tarde16fds.png"));
			imagemTarde17diaUtil = ImageIO.read(new File(pathImagem+"tarde17diautil.png"));
			imagemTarde17fds = ImageIO.read(new File(pathImagem+"tarde17fds.png"));
			imagemTarde18diaUtil = ImageIO.read(new File(pathImagem+"tarde18diautil.png"));
			imagemTarde18fds = ImageIO.read(new File(pathImagem+"tarde18fds.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.viewGame = viewGame;
		this.controllerEmpresa = controllerEmpresa;
		
		carregarImagemFabrica(imagemManha08diaUtil);
	}
	
	private void carregarImagemFabrica(BufferedImage imagem) {
		viewGame.areaImagem.setImagem(imagem);
	}
	
	public void atualizar(String mensagem) throws IOException {
		boolean gameOver = controllerEmpresa.gameOver();
		
		if (gameOver) {
			viewGame.areaImagem.setMensagemFimJogo("Fim jogo: Não há saldo suficiente para continuar a empresa em funcionamento!");
		}
		else {
			if (!mensagem.isEmpty()) {
				viewGame.areaImagem.setMensagemFimJogo(mensagem);
			}
		}
		
		viewGame.areaImagem.setNotaNatureza(controllerEmpresa.getNotaNatureza());
		viewGame.areaImagem.setSaldoAtual(controllerEmpresa.getSaldoAtual());
		viewGame.areaImagem.setEstoqueMateriaPrima(controllerEmpresa.getEstoqueAtualMateriaPrima());
		viewGame.areaImagem.setEstoqueProdutoAcabado(controllerEmpresa.getEstoqueAtualProdutoAcabado());
		viewGame.areaImagem.setDataCompleta(controllerEmpresa.getDataAtual());
		viewGame.areaImagem.setOperacao(controllerEmpresa.getHorarioComercial());
		
		// atualizar os labels
		boolean negociacaoEmAberto = controllerEmpresa.getNegociacaoEmAberto() && !gameOver;
		
		viewGame.btnComprarMateriaPrima.setEnabled(negociacaoEmAberto);
		viewGame.btnOrdemProducao.setEnabled(negociacaoEmAberto && controllerEmpresa.temEstoqueMateriaPrima());
		viewGame.btnVenderProdutoAcabado.setEnabled(negociacaoEmAberto && controllerEmpresa.temEstoqueProdutoAcabado());
		viewGame.btnPalestraSustentabilidade.setEnabled(negociacaoEmAberto);
		viewGame.btnFiltroFumaca.setEnabled(negociacaoEmAberto);
		viewGame.btnReciclagem.setEnabled(negociacaoEmAberto);
		viewGame.btnEcoEficiencia.setEnabled(negociacaoEmAberto);
		
		// fazer um teste aqui para carregar uma imagem dinamicamente
		
		if (controllerEmpresa.getFimDeSemana().isEmpty()) {
			
			switch (controllerEmpresa.getPeriodoDia()) {
			case amanhecer:
				carregarImagemFabrica(imagemAmanhecer);
				break;
			case inicioAnoitecer:
				carregarImagemFabrica(imagemInicioAnoitecer);
				break;
			case manha08:
				carregarImagemFabrica(imagemManha08diaUtil);
				break;
			case manha09:
				carregarImagemFabrica(imagemManha09diaUtil);
				break;
			case manha10:
				carregarImagemFabrica(imagemManha10diaUtil);
				break;
			case manha11:
				carregarImagemFabrica(imagemManha11diaUtil);
				break;
			case manha12:
				carregarImagemFabrica(imagemManha12diaUtil);
				break;
			case noite:
				carregarImagemFabrica(imagemNoite);
				break;
			case tarde13:
				carregarImagemFabrica(imagemTarde13diaUtil);
				break;
			case tarde14:
				carregarImagemFabrica(imagemTarde14diaUtil);
				break;
			case tarde15:
				carregarImagemFabrica(imagemTarde15diaUtil);
				break;
			case tarde16:
				carregarImagemFabrica(imagemTarde16diaUtil);
				break;
			case tarde17:
				carregarImagemFabrica(imagemTarde17diaUtil);
				break;
			case tarde18:
				carregarImagemFabrica(imagemTarde18diaUtil);
				break;
			default:
				break;
			}
		}
		else {
			switch (controllerEmpresa.getPeriodoDia()) {
			case amanhecer:
				carregarImagemFabrica(imagemAmanhecer);
				break;
			case inicioAnoitecer:
				carregarImagemFabrica(imagemInicioAnoitecer);
				break;
			case manha08:
				carregarImagemFabrica(imagemManha08fds);
				break;
			case manha09:
				carregarImagemFabrica(imagemManha09fds);
				break;
			case manha10:
				carregarImagemFabrica(imagemManha10fds);
				break;
			case manha11:
				carregarImagemFabrica(imagemManha11fds);
				break;
			case manha12:
				carregarImagemFabrica(imagemManha12fds);
				break;
			case noite:
				carregarImagemFabrica(imagemNoite);
				break;
			case tarde13:
				carregarImagemFabrica(imagemTarde13fds);
				break;
			case tarde14:
				carregarImagemFabrica(imagemTarde14fds);
				break;
			case tarde15:
				carregarImagemFabrica(imagemTarde15fds);
				break;
			case tarde16:
				carregarImagemFabrica(imagemTarde16fds);
				break;
			case tarde17:
				carregarImagemFabrica(imagemTarde17fds);
				break;
			case tarde18:
				carregarImagemFabrica(imagemTarde18fds);
				break;
			default:
				break;
			}
			
			
		}
	}
	
}
