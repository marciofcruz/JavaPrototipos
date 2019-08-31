package br.com.aps.biometria.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import br.com.aps.biometria.tipos.Frequencia;

public class ExportaPlanilhaFrequencia {

	XSSFWorkbook workbook = new XSSFWorkbook();
	XSSFSheet sheet = workbook.createSheet("Digitais importadas no Banco");

	public ExportaPlanilhaFrequencia(List<Frequencia> listaFrequencia,
			String nomePlanilha) throws IOException {
		super();

		Map<String, Object[]> data = new TreeMap<String, Object[]>();

		data.put("1", new Object[] { "Arquivo", "Usuario", "Nivel Acesso" });

		Integer cont = 2;

		for (int i = 0; i < listaFrequencia.size(); i++) {
			data.put(cont.toString(), new Object[] {
					listaFrequencia.get(i).getNomeArquivo(),
					listaFrequencia.get(i).getNomeUsuario(),
					listaFrequencia.get(i).getNivelAcesso() });

			cont++;
		}

		// Iterate over data and write to sheet
		Set<String> keyset = data.keySet();
		int rownum = 0;
		for (String key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
			}
		}

		FileOutputStream out = new FileOutputStream(new File(nomePlanilha));
		workbook.write(out);
		out.close();
	}

}
