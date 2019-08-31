package br.marciofcruz.apsmetodosordenacao.apresentacao;

import java.security.InvalidAlgorithmParameterException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import br.marciofcruz.apsmetodosordenacao.eficiencia.Eficiencia;
import br.marciofcruz.apsmetodosordenacao.eficiencia.ListaEficiencia;
import br.marciofcruz.apsmetodosordenacao.eficiencia.MetodoTempoProcessamento;
import br.marciofcruz.apsmetodosordenacao.tipos.ClassificacaoMetodo;
import br.marciofcruz.apsmetodosordenacao.tipos.TipoCriterioOrdem;
import br.marciofcruz.apsmetodosordenacao.tipos.TipoMetodoOrdenacao;

/**
 * Esta classe tem métodos auxiliares para a criação de um arquivo HTML de
 * apresentação dos resultados
 * 
 * @author B22816-4 Marcio Fernandes Cruz
 * @see java.lang.Object
 * @version 1.00
 * @since 1.0
 */

public class AuxiliarHTML {

	private Date dataProcessamento;
	private StringBuilder texto;
	private int[] faixaDeQuantidades;

	public AuxiliarHTML(StringBuilder texto, int[] faixaDeQuantidades,
			Date dataProcessamento) {
		this.texto = texto;
		this.faixaDeQuantidades = faixaDeQuantidades;
		this.dataProcessamento = dataProcessamento;
	}

	public StringBuilder getTexto() {
		return texto;
	}

	public void adicionar(StringBuilder dado) {
		texto.append(dado.toString());
	}

	public void adicionar(String dado) {
		texto.append(dado);
	}

	public void inserirImagem(List<Grafico> graficos) {

		StringBuilder auxiliar = new StringBuilder();

		Iterator<Grafico> iterator = graficos.iterator();

		auxiliar.append("<p>");

		while (iterator.hasNext()) {
			auxiliar.append("<center><img src=\"");
			auxiliar.append(iterator.next().getNomeArquivo());
			auxiliar.append("\" alt=\"Resultados\"></center>\n");
		}

		auxiliar.append("</p>\n");

		adicionar(auxiliar);
	}

	public void imprimirTabelaComparativoMetodos(String tituloRelatorio,
			TipoCriterioOrdem tipoCriterioOrdem, List<Resultado> lista)
			throws InvalidAlgorithmParameterException {

		boolean impressaoImpar = true;

		StringBuilder auxiliar = new StringBuilder();

		auxiliar.append("<p>");
		auxiliar.append("<br><hr><center><font size=\"5\" color=#000099><strong>");
		auxiliar.append(tituloRelatorio);
		auxiliar.append("</strong></font></center>");

		auxiliar.append("<div class=\"datagrid\"><table>\n");

		auxiliar.append("<thead>\n");
		auxiliar.append("<th>");
		auxiliar.append("<strong>Qtd. Imagens</strong>");
		auxiliar.append("</th>\n");

		for (int i = 0; i < faixaDeQuantidades.length; i++) {
			auxiliar.append("<th>");
			auxiliar.append(faixaDeQuantidades[i]);
			auxiliar.append("</th>\n");
		}

		auxiliar.append("</tr></thead>\n");
		auxiliar.append("<tbody>\n");

		TipoMetodoOrdenacao metodoAnterior = null;

		Iterator<Resultado> iterator = lista.iterator();

		while (iterator.hasNext()) {
			Resultado resultado = iterator.next();

			if (metodoAnterior != resultado.getTipoMetodoOrdenacao()) {
				if (impressaoImpar) {
					auxiliar.append("<tr>\n");
				} else {
					auxiliar.append("<tr class=\"alt\">\n");
				}
				
				auxiliar.append("<td>");
				auxiliar.append("<font size=\"4\">");
				auxiliar.append(TipoMetodoOrdenacao.getNome(resultado
						.getTipoMetodoOrdenacao()));
				auxiliar.append("</font>");
				auxiliar.append("</br>");
				auxiliar.append("Tipo: ");
				auxiliar.append(ClassificacaoMetodo.getNome(TipoMetodoOrdenacao.getTipoClassificacaoMetodo(resultado.getTipoMetodoOrdenacao())));
				auxiliar.append("</td>\n");

				metodoAnterior = resultado.getTipoMetodoOrdenacao();

				impressaoImpar = !impressaoImpar;
			}

			NumberFormat format = new DecimalFormat("###,###.###");

			auxiliar.append("<td>");

			auxiliar.append("<strong>");
			auxiliar.append(format.format(Math.round(resultado
					.getTempoEmMicroSegundo())));
			auxiliar.append(" µs</strong></br>");

			auxiliar.append("</td>\n");

			if (metodoAnterior != resultado.getTipoMetodoOrdenacao()) {
				auxiliar.append("</tr>\n");
			}

			metodoAnterior = resultado.getTipoMetodoOrdenacao();
		}

		auxiliar.append("</tbody></table></div>\n");
		auxiliar.append("</p>");

		adicionar(auxiliar);
	}

	public void imprimirTabelaEficiencia(ListaEficiencia listaEficiencia)
			throws InvalidAlgorithmParameterException {
		StringBuilder auxiliar = new StringBuilder();

		auxiliar.append("<p>");
		auxiliar.append("<br><hr><center><font size=\"5\" color=#000099><strong>");
		auxiliar.append("Eficiência dos métodos em Tempo de Processamento");
		auxiliar.append("</strong></font></center>");

		auxiliar.append("<div class=\"datagrid\"><table>\n");

		auxiliar.append("<thead>\n");

		for (int i = 0; i < faixaDeQuantidades.length; i++) {
			auxiliar.append("<th>");
			auxiliar.append(faixaDeQuantidades[i]);
			auxiliar.append(" imagens</th>\n");
		}

		auxiliar.append("</tr></thead>\n");
		auxiliar.append("<tbody>\n");

		DecimalFormat format = new DecimalFormat("###,###,###,##0.##");

		auxiliar.append("<tr>\n");
		for (int i = 0; i < faixaDeQuantidades.length; i++) {
			auxiliar.append("<td align=\"left\">");

			Eficiencia eficiencia = listaEficiencia
					.getEficienciaPorQuantidadeImagens(faixaDeQuantidades[i]);

			Iterator<MetodoTempoProcessamento> iterator = eficiencia
					.getListaMetodoTempoProcessamento().iterator();
			while (iterator.hasNext()) {
				MetodoTempoProcessamento metodoTempoProcessamento = iterator
						.next();

				auxiliar.append(TipoMetodoOrdenacao
						.getNome(metodoTempoProcessamento
								.getTipoMetodoOrdenacao()));

				double porcentagem = metodoTempoProcessamento
						.getEficienciaEmPorcentagem();
				
				porcentagem = Math.round(porcentagem);

				if (metodoTempoProcessamento.getEficienciaEmPorcentagem() != 0) {
					auxiliar.append("</br>");
					auxiliar.append("&#09;&uarr;&nbsp;");
					auxiliar.append(format.format(porcentagem));
					auxiliar.append("%");
				}
				else
				{
					auxiliar.append("</br>");
				}
				
				auxiliar.append("</br></br>");
			}

			auxiliar.append("</td>\n");
		}
		auxiliar.append("</tr>\n");

		auxiliar.append("</tbody></table></div>\n");
		auxiliar.append("</p>");

		adicionar(auxiliar);
	}

	public void inserirDadosProcessamento() {
		StringBuilder auxiliar = new StringBuilder();

		Properties p = System.getProperties();
		Runtime runtime = Runtime.getRuntime();

		// Imprimir Informações do ambiente no HTML
		auxiliar.append("</br>");
		auxiliar.append("<font size=\"4\" color=#000099>");
		auxiliar.append("<strong>Data do Processamento:</strong>");
		auxiliar.append(new SimpleDateFormat("dd/MM/yyyy - E - HH:mm:ss")
				.format(dataProcessamento).toString());
		auxiliar.append("<br>\n");

		auxiliar.append("</br><strong>Especificação da VM: </strong>");
		auxiliar.append(p.getProperty("java.vm.specification.name"));

		auxiliar.append("</br><strong>Arquitetura: </strong>");
		auxiliar.append(p.getProperty("sun.arch.data.model"));
		auxiliar.append(" bits");

		auxiliar.append("</br><strong>Versão da VM: </strong>");
		auxiliar.append(p.getProperty("java.vm.specification.version"));

		auxiliar.append("</br><strong>Fabricante da VM: </strong>");
		auxiliar.append(p.getProperty("java.vendor"));

		auxiliar.append("</br><strong>CPU: </strong>");
		auxiliar.append(p.getProperty("sun.cpu.isalist"));

		auxiliar.append("</br><strong>Total de CPUs: </strong>");
		auxiliar.append(runtime.availableProcessors());

		auxiliar.append("</br><strong>Sistema Operacional: </strong>");
		auxiliar.append(System.getProperty("os.name"));

		auxiliar.append("</br>");
		auxiliar.append("</br>");

		auxiliar.append("<strong>Trabalho de APS para Estrutura de Dados 2013</strong><br>\n");
		auxiliar.append("<strong>Campus:</strong> Unip/Marquês<br><br>\n");

		auxiliar.append("<strong>Aluno 1:</strong> B22816-4 MARCIO FERNANDES CRUZ<br>\n");
		auxiliar.append("<strong>Aluno 2:</strong> B54ECG-9 MAURICIO JOSE F OLIVEIRA<br>\n");
		auxiliar.append("<strong>Aluno 3:</strong> T618FA-6 RENATO GOULART RODRIGUES<br>\n");
		auxiliar.append("<strong>Aluno 4:</strong> B20309-9 RODOLFO YURI DE A FONTANA<br>\n");
		auxiliar.append("</font>");

		adicionar(auxiliar);
	}

	public void inicioHTML(String tituloRelatorio) {
		StringBuilder auxiliar = new StringBuilder();

		auxiliar.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01//EN\">\n");
		auxiliar.append("<html> <head><title>");
		auxiliar.append(tituloRelatorio);
		auxiliar.append(" CC2P13");

		auxiliar.append("</title>\n");
		auxiliar.append("<style type=\"text/css\">\n");
		auxiliar.append(".datagrid table { border-collapse: collapse; text-align: center; width: 100%; }\n");
		auxiliar.append(".datagrid {font: normal 12px/150% Arial, Helvetica, sans-serif; background: #fff; overflow: hidden; }\n");
		auxiliar.append(".datagrid table td, .datagrid table th { padding: 3px 10px; }\n");
		auxiliar.append(".datagrid table thead th {background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F) );\n");
		auxiliar.append("background:-moz-linear-gradient( center top, #006699 5%, #00557F 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F');\n");
		auxiliar.append("background-color:#006699; color:#FFFFFF; font-size: 12px; font-weight: bold; border-left: 1px solid #0070A8; }\n");
		auxiliar.append(".datagrid table thead th:first-child { border: none; }.datagrid table tbody td { color: #00496B; border-left: 1px solid #E1EEF4;font-size: 12px;font-weight: normal; }\n");
		auxiliar.append(".datagrid table tbody .alt td { background: #E1EEF4; color: #00496B; }.datagrid table tbody td:first-child { border-left: none; }\n");
		auxiliar.append(".datagrid table tbody tr:last-child td { border-bottom: none; }.datagrid table tfoot td div { border-top: 1px solid #006699;background: #E1EEF4;}\n");
		auxiliar.append(".datagrid table tfoot td { padding: 0; font-size: 12px } .datagrid table tfoot td div{ padding: 2px; }\n");
		auxiliar.append(".datagrid table tfoot td ul { margin: 0; padding:0; list-style: none; text-align: right; }\n");
		auxiliar.append(".datagrid table tfoot  li { display: inline; }\n");
		auxiliar.append(".datagrid table tfoot li a { text-decoration: none; display: inline-block;  padding: 2px 8px; margin: 1px;color: #FFFFFF;border: 1px solid #006699;-webkit-border-radius: 3px; -moz-border-radius: 3px; border-radius: 3px; background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #006699), color-stop(1, #00557F) );background:-moz-linear-gradient( center top, #006699 5%, #00557F 100% );filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#006699', endColorstr='#00557F');background-color:#006699; }\n");
		auxiliar.append(".datagrid table tfoot ul.active,\n");
		auxiliar.append(".datagrid table tfoot ul a:hover { text-decoration: none;border-color: #006699; color: #FFFFFF; background: none; background-color:#00557F;}\n");
		auxiliar.append("</style>\n");

		auxiliar.append("</head>\n");

		auxiliar.append("<body>\n");

		adicionar(auxiliar);
	}

}
