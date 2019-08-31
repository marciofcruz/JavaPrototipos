package controller;

import java.io.IOException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import tipos.Animal;
import tipos.Catalogado;
import tipos.Configuracao;
import model.ExportaPlanilha;
import model.FuncoesGerais;
import model.GravaAnimalCatalogado;
import model.GravaConfiguracao;
import model.LerAnimalCatalogado;
import model.LerConfiguracao;

public class ControllerBichos {

	private List<Catalogado> listaCatalogado = new ArrayList<Catalogado>();
	private ContaCatalogado contaCatalogado = new ContaCatalogado();
	private JTextArea textArea;
	private Servidor servidor = null;
	private Cliente cliente = null;
	private boolean fecharSistema = false;

	SimpleDateFormat sdf = new SimpleDateFormat("E dd/MM/yyyy HH:mm:ss");

	public boolean getFecharSistema() {
		return fecharSistema;
	}
	
	public int getQuantidadeClientesConectados() {
		int auxiliar=0;
		
		if (servidor != null) {
			auxiliar = servidor.getQtdeClientes();
		}
		
		return auxiliar;
	}

	public void tratarServidor(Configuracao configuracao) throws IOException {

		if (!fecharSistema) {
			if (configuracao.getEhServidor()) {
				servidor = new Servidor(Integer.parseInt(configuracao
						.getPorta()), textArea);

				servidor.executa();
			}

			textArea.setCaretPosition(textArea.getText().length());

		}
	}

	public void conectarCliente(Configuracao configuracao) {
		if (!fecharSistema) {
			if (!configuracao.getEhServidor()
					&& !configuracao.getHost().equals("")
					&& !configuracao.getPorta().equals("")) {
				int porta = Integer.parseInt(configuracao.getPorta());
				try {
					cliente = new Cliente(configuracao.getHost(), porta, textArea);
				} catch (UnknownHostException e) {
					cliente = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					cliente = null;
				}
			}
		}
	}
	
	private String getLinhaCatalogado(Catalogado catalogado) {
		StringBuilder stb = new StringBuilder();
		stb.append(sdf.format(catalogado.getDataRegistro()) + " - ");
		stb.append(Animal.getDescricao(catalogado.getAnimal()) + " - ");
		stb.append("Local:" + catalogado.getLocalizacao() + " - ");
		stb.append("Usuário:" + catalogado.getUsuario());

		if (!catalogado.getObservacao().equals("")) {
			stb.append(" - Observação:" + catalogado.getObservacao());
		}

		return stb.toString();
	}

	private void addMemo(Catalogado catalogado) {
		textArea.append(getLinhaCatalogado(catalogado)+"\n");
		textArea.setCaretPosition(textArea.getText().length());

	}

	public ControllerBichos(JTextArea textArea) {
		this.textArea = textArea;

		this.fecharSistema = false;

		LerAnimalCatalogado arquivo = new LerAnimalCatalogado(
				FuncoesGerais.getNomeArquivoCatalogado());
		try {
			arquivo.abrirArquivo();
			listaCatalogado = arquivo.getCatalogado();
			arquivo.fecharArquivo();

			for (Catalogado c : listaCatalogado) {
				addMemo(c);
			}

			for (int i = 0; i < listaCatalogado.size(); i++) {
				contaCatalogado.add(listaCatalogado.get(i).getAnimal());
			}
		} catch (Exception e) {
			listaCatalogado = new ArrayList<Catalogado>();
		}
	}

	public int getQtdeCatalogado(Animal animal) {
		switch (animal) {
		case anfibio:
			return contaCatalogado.getAnfibio();
		case avesSilvestres:
			return contaCatalogado.getAvesSilvestres();
		case cachorro:
			return contaCatalogado.getCachorro();
		case capivara:
			return contaCatalogado.getCapivara();
		case cobra:
			return contaCatalogado.getCobra();
		case coruja:
			return contaCatalogado.getCoruja();
		case gato:
			return contaCatalogado.getGato();
		case mamiferosSilvestres:
			return contaCatalogado.getMamiferosSilvestres();
		default:
			return -1;

		}
	}

	public void setConfiguracao(String localizacao, String usuario,
			String host, String porta, Boolean ehServidor,
			Configuracao configuracaoAnterior) throws Exception {
		Configuracao configuracao = new Configuracao();

		configuracao.setLocalizacao(localizacao);
		configuracao.setUsuario(usuario);
		configuracao.setHost(host);
		configuracao.setPorta(porta.toString());
		configuracao.setEhServidor(ehServidor);

		GravaConfiguracao gravaConfiguracao = new GravaConfiguracao();
		gravaConfiguracao.abrirArquivo();
		gravaConfiguracao.salvar(configuracao);
		gravaConfiguracao.fecharArquivo();

		if (!configuracaoAnterior.getPorta().equals(configuracao.getPorta())
				|| !configuracaoAnterior.getEhServidor().equals(
						configuracao.getEhServidor())) {
			fecharSistema = true;

			servidor = null;
			cliente = null;

			JOptionPane
					.showMessageDialog(
							null,
							"Troca de configuração de conexão. Favor fechar e abrir o programa!",
							"Informação", JOptionPane.INFORMATION_MESSAGE);

			System.exit(0);
		}
	}

	public Configuracao getConfiguracao() throws ClassNotFoundException,
			IOException {
		LerConfiguracao lerConfiguracao = new LerConfiguracao();
		lerConfiguracao.abrirArquivo();
		Configuracao auxiliar = lerConfiguracao.getConfiguracao();
		lerConfiguracao.fecharArquivo();

		return auxiliar;
	}

	public void catalogar(Animal animal, Configuracao configuracao,
			String observacao) throws IOException, BadLocationException {
		Catalogado catalogado = new Catalogado();
		catalogado.setObservacao(observacao);
		catalogado.setAnimal(animal);
		catalogado.setLocalizacao(configuracao.getLocalizacao());
		catalogado.setUsuario(configuracao.getUsuario());
		catalogado.setDataRegistro(new GregorianCalendar().getTime());

		listaCatalogado.add(catalogado);

		contaCatalogado.add(catalogado.getAnimal());

		addMemo(catalogado);

		if (configuracao.getEhServidor()) {
			servidor.distribuiMensagem(getLinhaCatalogado(catalogado));
		} else {
			if (cliente != null) {
				if (!cliente.getEstahConectado()) {
					int porta = Integer.parseInt(configuracao.getPorta());
					cliente = new Cliente(configuracao.getHost(), porta,
							textArea);
				}
			}

			if (cliente != null && cliente.getEstahConectado()) {
				cliente.enviarMensagem(getLinhaCatalogado(catalogado)+"\n");
			}
		}
	}

	public void setListaCatalogado() throws IOException {
		GravaAnimalCatalogado gravaAnimalCatalogado = new GravaAnimalCatalogado(
				FuncoesGerais.getNomeArquivoCatalogado());
		gravaAnimalCatalogado.abrirArquivo();

		gravaAnimalCatalogado.setLista(listaCatalogado);

		gravaAnimalCatalogado.fecharArquivo();

	}

	public String getPathIconeExcel() {
		return FuncoesGerais.getPathImagens() + "excel.png";
	}

	public void exportarPlanilha(String arquivo) throws IOException {
		new ExportaPlanilha(listaCatalogado, arquivo);
	}

	public boolean getClienteEstahConectado() {
		return (cliente != null && cliente.getEstahConectado());
	}
}
