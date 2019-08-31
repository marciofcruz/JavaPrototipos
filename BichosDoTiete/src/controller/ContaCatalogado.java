package controller;

import tipos.Animal;

public class ContaCatalogado {
	private int capivara = 0;
	private int mamiferosSilvestres = 0;
	private int gato = 0;
	private int cobra = 0;
	private int avesSilvestres = 0;
	private int anfibio = 0;
	private int coruja = 0;
	private int cachorro = 0;
	
	public void add(Animal animal) {
		switch (animal) {
		case anfibio:
			this.anfibio++;
			break;
		case avesSilvestres:
			this.avesSilvestres++;
			break;
		case cachorro:
			this.cachorro++;
			break;
		case capivara:
			this.capivara++;
			break;
		case cobra:
			this.cobra++;
			break;
		case coruja:
			this.coruja++;
			break;
		case gato:
			this.gato++;
			break;
		case mamiferosSilvestres:
			this.mamiferosSilvestres++;
			break;
		default:
			break;
		}
	}

	public int getCapivara() {
		return capivara;
	}

	public int getMamiferosSilvestres() {
		return mamiferosSilvestres;
	}

	public int getGato() {
		return gato;
	}

	public int getCobra() {
		return cobra;
	}

	public int getAvesSilvestres() {
		return avesSilvestres;
	}

	public int getAnfibio() {
		return anfibio;
	}

	public int getCoruja() {
		return coruja;
	}

	public int getCachorro() {
		return cachorro;
	}
}
