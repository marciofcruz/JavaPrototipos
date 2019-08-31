package br.com.aps.biometria.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.aps.biometria.model.*;
import br.com.aps.biometria.tipos.Frequencia;

public class Controller {

	private List<Frequencia> listaFrequencia;
	private PainelNotificacao painelNotificacao;
	private PainelImagemDB painelImagemDB;
	private PainelHistograma painelHistograma;
	private MainMenu mainMenu;

	public int getQuantidadeDigitais() {
		return listaFrequencia.size();
	}

	public void setPainelNotificacao(PainelNotificacao painelNotificacao) {
		this.painelNotificacao = painelNotificacao;
	}

	public void setPainelImagemDB(PainelImagemDB painelImagemDB) {
		this.painelImagemDB = painelImagemDB;
	}

	public PainelNotificacao getPainelNotificacao() {
		return painelNotificacao;
	}

	public void setPainelHistograma(PainelHistograma painelHistograma) {
		this.painelHistograma = painelHistograma;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}

	public void gerarBancoDeDadosImagem() throws Exception {
		painelNotificacao.addText("Iniciando importação das imagens..");

		// pegando imagens do disco e jogando num List
		this.listaFrequencia = new GerarListaFrequencia().getFrequencias();

		// pegando do list e enviando para um .dat no disco
		GravaFrequencia grava = new GravaFrequencia();
		grava.abrirArquivo();
		grava.setLista(listaFrequencia);
		grava.fecharArquivo();

		painelNotificacao.addText("FIM da Importação: Total "
				+ getQuantidadeDigitais());
	}

	public void carregarListaFrequencia() throws Exception {
		LerFrequencia ler = new LerFrequencia();

		if (ler.existeArquivo()) {
			ler.abrirArquivo();
			listaFrequencia = ler.getLista();
			ler.fecharArquivo();
		} else
			listaFrequencia = new ArrayList<Frequencia>();

		painelNotificacao.addText("Total de registros de digitais: "
				+ getQuantidadeDigitais());
	}

	public void exportarPlanilha(String arquivo) throws IOException {
		new ExportaPlanilhaFrequencia(listaFrequencia, arquivo);
		painelNotificacao.addText("Arquivo de usuários exportado para: "
				+ arquivo);
	}

	public void autenticarImagem(File arquivoAutenticacao) throws IOException {
		Frequencia frequenciaArquivoAutenticacao = new Frequencia(
				arquivoAutenticacao.getPath());
		new Thread(new ProcessoDeAutenticacao(frequenciaArquivoAutenticacao))
				.start();
	}

	public class ProcessoDeAutenticacao implements Runnable {

		private Frequencia frequenciaArquivoAutenticacao;

		public ProcessoDeAutenticacao(Frequencia frequenciaArquivoAutenticacao) {
			this.frequenciaArquivoAutenticacao = frequenciaArquivoAutenticacao;
		}

		@Override
		public void run() {

			mainMenu.ativarComandos(false);
			boolean encontrou = false;
			int i = 1, total = listaFrequencia.size();

			for (Frequencia registroBase : listaFrequencia) {
				painelNotificacao.setLoadingValue((i * 100) / total);
				painelImagemDB.addImagem(registroBase.getNomeArquivo());
				painelHistograma.setFrequenciaImagem(registroBase);

				if (frequenciaArquivoAutenticacao.equals(registroBase)) {
					painelImagemDB.setNome(registroBase.getNomeUsuario()
							+ " - Nivel:" + registroBase.getNivelAcesso());
					painelNotificacao.addText("Usuário: "
							+ registroBase.getNomeUsuario()
							+ ", Nivel de Acesso: "
							+ registroBase.getNivelAcesso());

					painelHistograma.setFrequenciaImagem(registroBase);
					painelNotificacao.setLoadingValue(100);
					encontrou = true;
					break;
				}

				i++;
				try {
					Thread.sleep(mainMenu.getTempoDeBusca());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			if (!encontrou) {
				painelHistograma.clear();
				painelNotificacao.addText("Usuário não encontrado");
				painelImagemDB.addImagem(FuncoesGerais.getImagemNaoLocalizada());
			}

			mainMenu.ativarComandos(true);
			mainMenu.repaint();
		}
	}

}