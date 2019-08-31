package tipos;

public enum PeriodoDia {
	amanhecer, manha08, manha09, manha10, manha11, manha12, 
	tarde13, tarde14, tarde15, tarde16, tarde17, tarde18, inicioAnoitecer, noite;
	
	public static String getDescricao(PeriodoDia periodoDia) {
		switch (periodoDia) {
		case amanhecer:
			return "Amanhecer";
		case tarde13:
			return "Tarde 13";
		case tarde14:
			return "Tarde 14";
		case tarde15:
			return "Tarde 15";
		case tarde16:
			return "Tarde 16";
		case tarde17:
			return "Tarde 17";
		case tarde18:
			return "Tarde 18";
		case inicioAnoitecer:
			return "Inicio Anoitecer";
		case manha08:
			return "Manhã 08";
		case manha09:
			return "Manhã 09";
		case manha10:
			return "Manhã 10";
		case manha11:
			return "Manhã 11";
		case manha12:
			return "Manhã 12";
		case noite:
			return "Noite";
		default:
			return null;
		
		}
	}
}

/*
manha 08:
362 / 230

manha 09:
366 / 158

manha 10:
370 / 68

manha 11:
310  / 32

manhas 12:
198 / 26

tarde 13:
126 / 38

tarde 14:
76 / 50:

tarde 15:
48 / 84

tarde 16:
22 / 136

tarde 17:
20 / 202

tarde 18:
10 / 250


*/