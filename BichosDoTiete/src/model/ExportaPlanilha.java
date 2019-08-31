package model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import tipos.Animal;
import tipos.Catalogado;

public class ExportaPlanilha {

	SimpleDateFormat sdfDiaSemana = new SimpleDateFormat("E");
	SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdfHora = new SimpleDateFormat("HH:mm");

	XSSFWorkbook workbook = new XSSFWorkbook();
	XSSFSheet sheet = workbook.createSheet("Animais catalogados");

	public ExportaPlanilha(List<Catalogado> listaCatalogado, String nomePlanilha) throws IOException {
		super();

		Map<String, Object[]> data = new TreeMap<String, Object[]>();

		data.put("1", new Object[] { "Posição", "Dia Semana", "Data", "Hora",
				"Local", "Animal", "Usuário", "Observação" });

		Integer cont = 2;

		for (int i = 0; i < listaCatalogado.size(); i++) {
			data.put(
					cont.toString(),
					new Object[] {
							i + 1,
							sdfDiaSemana.format(listaCatalogado.get(i)
									.getDataRegistro()),
							sdfData.format(listaCatalogado.get(i)
									.getDataRegistro()),
							sdfHora.format(listaCatalogado.get(i)
									.getDataRegistro()),
							listaCatalogado.get(i).getLocalizacao(),
							Animal.getDescricao(listaCatalogado.get(i)
									.getAnimal()),
							listaCatalogado.get(i).getUsuario(),
							listaCatalogado.get(i).getObservacao() });

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
