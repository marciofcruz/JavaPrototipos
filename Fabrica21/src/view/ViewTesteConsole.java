package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import tipos.PeriodoDia;

import controller.ControllerEmpresa;

public class ViewTesteConsole {

	public static void main(String args[]) throws IOException, ParseException {

		ControllerEmpresa camadaController = new ControllerEmpresa();

		while (true) {
			if (!camadaController.gameOver()) {
				System.out.println(camadaController.getNotaNatureza()
						+ "  "
						+

						"MP: "
						+ camadaController.getEstoqueAtualMateriaPrima()
						+ "\t"
						+ "PV: "
						+ camadaController.getEstoqueAtualProdutoAcabado()
						+ "\t"
						+ camadaController.getSaldoDiaAnterior()
						+ "\t"
						+ camadaController.getReceitaDia()
						+ "\t"
						+ camadaController.getDespesaDia()
						+ "\t"
						+ camadaController.getSaldoAtual()
						+ "\t"
						+ camadaController.getDataAtual()
						+ "  "
						+ PeriodoDia.getDescricao(camadaController
								.getPeriodoDia()) + "  "
						+ camadaController.getFimDeSemana() + "  "
						+ camadaController.getHorarioComercial() + "  "

						+ "\n");

				System.out.println("\nOpcoes Disponiveis:");

				if (camadaController.getNegociacaoEmAberto()) {
					System.out.println("1 - Comprar 10 Kg de matéria-prima");

					if (camadaController.temEstoqueMateriaPrima()) {
						System.out
								.println("2 - Produzir 5 unidades de produto acabado.");
					}

					if (camadaController.temEstoqueProdutoAcabado()) {
						System.out
								.println("3 - Vender 5 unidades de produto acabado.");
					}
					
					System.out.println("4 - Pesquisar ideias sustentaveis");
					System.out.println("5 - Filtro de fumaça");
					System.out.println("6 - Reciclagem");
					System.out.println("7 - EcoEficiencia");
				}

				System.out.println("<ENTER> - Nova Jogada");
			} else {
				System.out.println("Fim de Jogo!");
			}

			String opcao = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				opcao = br.readLine();
			} catch (IOException ioe) {
				System.out.println("Erro ao ler string");
			}

			opcao = opcao.toUpperCase();

			String mensagemRetorno = "";

			switch (opcao) {
			case "1":
				mensagemRetorno = camadaController.comprarMateriaPrima();
				break;
			case "2":
				mensagemRetorno = camadaController.executarOrdemProducao();
				break;
			case "3":
				mensagemRetorno = camadaController.venderProdutoAcabado();
				break;
			case "4":
				mensagemRetorno = camadaController.palestraSustentabilidade();
				break;
			case "5":
				mensagemRetorno = camadaController.filtroFumaca();
				break;
			case "6":
				mensagemRetorno = camadaController.reciclagem();
				break;
			case "7":
				mensagemRetorno = camadaController.ecoEficiencia();
				break;
			}

			if (mensagemRetorno != "") {
				System.out.println("Aviso: " + mensagemRetorno);
			}

			mensagemRetorno = camadaController.novaJogada();

			if (mensagemRetorno != "") {
				System.out.println("Aviso: " + mensagemRetorno);
			}

		}

	}
}
