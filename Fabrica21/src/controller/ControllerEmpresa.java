package controller;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import tipos.PeriodoDia;
import tipos.TipoInvestimentoNatureza;

import model.ModelEmpresa;

public class ControllerEmpresa {

	private DecimalFormat df = new DecimalFormat("###,##0.00");
	SimpleDateFormat sdf = new SimpleDateFormat("E dd/MM/yyyy HH:mm");

	private ModelEmpresa camadaModel = new ModelEmpresa();

	public ControllerEmpresa() throws ParseException {
		camadaModel.iniciarJogo();
	}

	public String getFimDeSemana() {
		if (camadaModel.getfimDeSemana()) {
			return "Fim de Semana";
		} else {
			return "";
		}
	}
	
	public String palestraSustentabilidade() {
		try {
			camadaModel.investirSustentabilidade(TipoInvestimentoNatureza.palestraSustentabilidade);
			return "";
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}
	
	public String filtroFumaca(){
		try {
			camadaModel.investirSustentabilidade(TipoInvestimentoNatureza.filtroFumaca);
			return "";
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}
	
	public String reciclagem(){
		try {
			camadaModel.investirSustentabilidade(TipoInvestimentoNatureza.reciclagem);
			return "";
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}
	
	public String ecoEficiencia(){
		try {
			camadaModel.investirSustentabilidade(TipoInvestimentoNatureza.ecoEficiencia);
			return "";
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}

	public String comprarMateriaPrima() {
		try {
			camadaModel.comprarMateriaPrima(50);
			return "";
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}

	public String executarOrdemProducao() {
		try {
			camadaModel.fabricarProdutoAcabado(5);
			novaJogada();
			return "";
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}
	
	public String venderProdutoAcabado() {
		try {
			camadaModel.venderProdutoAcabado(5);;
			return "";
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}
	
	public String getNotaNatureza() {
		float auxiliar = camadaModel.getNotaNatureza();
		
		if (auxiliar > 10) {
			auxiliar = 10;
		}
		
		return df.format(auxiliar);
	}
	
	public String getPrecoCustoProdutoAcabado() {
		return "R$ "+df.format(camadaModel.getPrecoCustoProdutoAcabado());
	}
	
	public String getPrecoVenda() {
		return "R$ "+df.format(camadaModel.getPrecoVenda()); 
	}
	
	public String getCustoFabricaDia() {
		return "R$ "+df.format(camadaModel.getCustoFabricaDia());
	}

	public boolean gameOver() {
		return camadaModel.getGameOver();
	}

	public String getEstoqueAtualMateriaPrima() {
		return camadaModel.getEstoqueAtualMateriaPrima().toString()+" Kg";
	}

	public String getEstoqueAtualProdutoAcabado() {
		return camadaModel.getEstoqueAtualProdutoAcabado().toString()+ " Pç";
	}

	public boolean temEstoqueMateriaPrima() {
		return camadaModel.getEstoqueAtualMateriaPrima() != 0;
	}
	
	public boolean temEstoqueProdutoAcabado() {
		return camadaModel.getEstoqueAtualProdutoAcabado() != 0;
	}

	public String getSaldoDiaAnterior() {
		return df.format(camadaModel.getSaldoDiaAnterior());
	}

	public String getDespesaDia() {
		return df.format(camadaModel.getDespesaDia());
	}

	public String getReceitaDia() {
		return df.format(camadaModel.getReceitaDia());
	}

	public String getSaldoAtual() {
		return df.format(camadaModel.getSaldoAtual());
	}

	public int getDiasOperacao() {
		return camadaModel.getDiasOperacao();
	}

	public String getDataAtual() {
		return sdf.format(camadaModel.getDataAtual().getTime());
	}

	public String novaJogada() {
		try {
			camadaModel.novaJogada();
			return "";
		} catch (RuntimeException e) {
			return e.getMessage();
		}
	}

	public PeriodoDia getPeriodoDia() {
		return camadaModel.getPeriodoDia();
	}

	public String getHorarioComercial() {
		if (camadaModel.getfimDeSemana()) {
			return "Fim de Semana";
		} else {
			if (camadaModel.gethorarioComercial()) {
				return "Horário Comercial";
			} else {
				return "Fora do Horário Comercial";
			}
		}
	}

	public boolean getNegociacaoEmAberto() {
		return camadaModel.getNegociacaoemAberto();
	}

}
