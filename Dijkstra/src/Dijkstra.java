/* Trabalho de Teoria dos Grafos */

// Implementação basezda no algoritimo da UFSC acessado em 16/03/2014,
// 17h00
// http://www.inf.ufsc.br/grafos/temas/custo-minimo/dijkstra.html

// Desenvolvido por Marcio Fernandes Cruz
// a pedido do professor Mariano, disciplina Teoria dos Grafos
// Março 2014

import java.util.Scanner;

public class Dijkstra {

	static Scanner scanner;
	static final int infinito = -2;
	static int[][] matrizAdjacencia;
	static int[] estimativas;
	static int[] fechados;
	static int verticeOrigem;
	static int quantidadeVertices;
	static String modoTeste;

	static private String getModoTeste() {
		System.out
				.println("Preencher com valores para teste com 5 Vertices(S/N)?");

		String retorno;

		boolean ok;

		do {
			scanner = new Scanner(System.in);

			retorno = scanner.next();

			ok = retorno.equals("S") || retorno.equals("N");
		} while (!ok);

		return retorno;
	}

	static private int getVerticeOrigem() {
		System.out
				.println("Escolha o Vertice inicial para cálculo de estimativa [1.."
						+ quantidadeVertices + "]: ");

		int retorno;

		boolean ok;

		do {
			scanner = new Scanner(System.in);
			retorno = scanner.nextInt();

			ok = retorno >= 1 && retorno <= quantidadeVertices;
		} while (!ok);

		return retorno - 1;
	}

	static private int getQuantidadeVertices() {
		System.out.println("Quantos vertices tem o Grafo [1..50]: ");

		int retorno;

		boolean ok;

		do {
			scanner = new Scanner(System.in);
			retorno = scanner.nextInt();

			ok = retorno >= 1 && retorno <= 50;
		} while (!ok);

		return retorno;
	}

	static void setMatrizAdjacencia() {

		int aux = 0;

		for (int x = 0; x < quantidadeVertices; x++) {

			for (int y = 0; y < quantidadeVertices; y++) {

				aux = matrizAdjacencia[y][x];

				System.out.print("Valor da Matriz de Adjacencia (0-ausencia) ["
						+ (x + 1) + "," + (y + 1) + "]=");

				if (aux == 0 && x != y) {
					scanner = new Scanner(System.in);

					aux = scanner.nextInt();

					if (aux < 0) {
						aux = 0;
					}

					matrizAdjacencia[x][y] = aux;
				}
				{
					System.out.println(aux);
					matrizAdjacencia[x][y] = aux;

				}
			}
		}
	}

	static void imprimirMatrizAdjacencia() {

		System.out.println("\n\n******MATRIZ DE ADJACENCIA*****");
		System.out.print("VERTICE\t");
		for (int x = 0; x < quantidadeVertices; x++) {
			System.out.format("%5d", x + 1);
		}
		System.out.print("\n");

		for (int x = 0; x < quantidadeVertices; x++) {
			System.out.format("%3d", x + 1);
			System.out.print("\t");

			for (int y = 0; y < quantidadeVertices; y++) {
				System.out.format("%5d", matrizAdjacencia[x][y]);
			}
			System.out.print("\n");

			if (x == quantidadeVertices) {
				System.out.println("\n");
			}
		}
	}

	static void imprimirEstimativa() {
		System.out.println("\n\n******ESTIMATIVA DE CUSTO A PARTIR DA ORIGEM "
				+ (verticeOrigem + 1) + "*****");

		System.out.print("VERTICE\t");
		for (int x = 0; x < quantidadeVertices; x++) {
			System.out.format("%5d", x + 1);
		}
		System.out.print("\nCUSTO\t");

		for (int x = 0; x < quantidadeVertices; x++) {
			System.out.format("%5d", estimativas[x]);
		}

		System.out.println();
	}
	
	static private void recalcularEstimativas(int verticeBase) {
		int indiceVerticeEstimativaMinima = -1;
		int menorPeso = 0;
		
		fechados[verticeBase] = 1;

		for (int indice = 0; indice < quantidadeVertices; indice++) {

			if (fechados[indice] == 0) {
				int peso = matrizAdjacencia[verticeBase][indice];

				if (peso > 0) {
					int estimativaAuxiliar = estimativas[verticeBase] + peso;
					
					if (estimativaAuxiliar <  estimativas[indice] || estimativas[indice]==infinito) {
					
					
					estimativas[indice] = estimativaAuxiliar;
					}

					if (indiceVerticeEstimativaMinima == -1 || peso < menorPeso) {
						indiceVerticeEstimativaMinima = indice;
						menorPeso = peso;
					}
				}
			}
		}
		
		System.out.println();

		if (indiceVerticeEstimativaMinima != -1) {
			recalcularEstimativas(indiceVerticeEstimativaMinima);
		}
	}

	static void calcularCaminhoMinimoDijkstra() {
		for (int x = 0; x < quantidadeVertices; x++) {
			if (x == verticeOrigem) {
				estimativas[x] = 0;
			} else {
				estimativas[x] = infinito;
			}

			fechados[x] = 0;
		}

		recalcularEstimativas(verticeOrigem);
	}

	public static void main(String[] args) {

		System.out.println("Ciencia da Computacao - Unip - 4.o / 5.o semestre");
		System.out.println("Professor Mariano - Disciplina Teoria dos Grafos");
		System.out.println("Senhores alunos: O ideal eh desenhar um grafo qualquer e uma matriz de adjacencia valorada para testes e simulacao do resultado!");
		System.out.println();
		System.out.println("Agradecimentos:");
		System.out.println("UFSC pela explicacao facilitada em http://www.inf.ufsc.br/grafos/temas/custo-minimo/dijkstra.html");
		System.out.println("Edsger Dijkstra: brilhante http://pt.wikipedia.org/wiki/Edsger_Dijkstra");
		System.out.println("Professor Mariano, por apresentar o algoritimo e dar a oportunidade de implementa-lo.");
		System.out.println();
		
		String modoTeste = getModoTeste();

		System.out.println();

		if (modoTeste.equals("S")) {
			quantidadeVertices = 5;
		} else {
			quantidadeVertices = getQuantidadeVertices();
			System.out.println();
		}

		verticeOrigem = getVerticeOrigem();
		estimativas = new int[quantidadeVertices];
		fechados = new int[quantidadeVertices];

		matrizAdjacencia = new int[quantidadeVertices][quantidadeVertices];
		int[] fechado = new int[quantidadeVertices];

		for (int x = 0; x < quantidadeVertices; x++) {
			for (int y = 0; y < quantidadeVertices; y++) {
				matrizAdjacencia[x][y] = 0;
			}
		}

		if (modoTeste.equals("S")) {

			// Código abaixo serve apenas para facilitar o teste de mesa
			matrizAdjacencia[0][0] = 0;
			matrizAdjacencia[0][1] = 3;
			matrizAdjacencia[0][2] = 5;
			matrizAdjacencia[0][3] = 0;
			matrizAdjacencia[0][4] = 0;

			matrizAdjacencia[1][0] = 3;
			matrizAdjacencia[1][1] = 0;
			matrizAdjacencia[1][2] = 2;
			matrizAdjacencia[1][3] = 4;
			matrizAdjacencia[1][4] = 4;

			matrizAdjacencia[2][0] = 5;
			matrizAdjacencia[2][1] = 2;
			matrizAdjacencia[2][2] = 0;
			matrizAdjacencia[2][3] = 0;
			matrizAdjacencia[2][4] = 6;

			matrizAdjacencia[3][0] = 0;
			matrizAdjacencia[3][1] = 4;
			matrizAdjacencia[3][2] = 0;
			matrizAdjacencia[3][3] = 0;
			matrizAdjacencia[3][4] = 2;

			matrizAdjacencia[4][0] = 0;
			matrizAdjacencia[4][1] = 4;
			matrizAdjacencia[4][2] = 6;
			matrizAdjacencia[4][3] = 2;
			matrizAdjacencia[4][4] = 0;
		}

		for (int x = 0; x < quantidadeVertices; x++) {

			fechado[x] = 0;
		}

		if (modoTeste.equals("N")) {
			setMatrizAdjacencia();
		}

		imprimirMatrizAdjacencia();

		calcularCaminhoMinimoDijkstra();

		imprimirEstimativa();
	}
}
