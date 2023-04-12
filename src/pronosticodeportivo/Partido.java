package pronosticodeportivo;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
//import java.time.LocalDate;
//import java.time.LocalTime;
import java.util.Arrays;

public class Partido {
	
	static boolean graba = true;
	String datos1;
	String copiared;
	int con = 0;
	int con1 = 1;
	int con3 = 3;
	
	static String basefec;
	String datos2;
	String resgrupos;
	String resulfase;
	String variospronos;
	private String zona;
	String reducir;
	String redgan;
	String redupronos;
	private String mfec;
	private String[] primerPar = new String[4];
	
	/*static Path fechasPar = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\fechas.csv");
	static Path partidos = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\zonas.csv");
	static Path resultados = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\resultado.csv");
	static Path reducida = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\resullista.csv");
	static Path pronosreducido = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\pronosredu.csv");
	static Path ganador = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\ganadoresfinales.csv");
	*/
	
	static Path fechasPar = Paths.get("fechas.csv");
	static Path partidos = Paths.get("zonas.csv");
	static Path resultados = Paths.get("resultado.csv");
	static Path reducida = Paths.get("resullista.csv");
	static Path pronosreducido = Paths.get("pronosredu.csv");
	static Path ganador = Paths.get("ganadoresfinales.csv");
	
	
	Path pronostico = Paths.get("pronostico.csv");
	Path redugana= Paths.get("ganadores.csv");
	
//constructor para grabar los resultados oficiales del primer partido
public Partido(String[] primerPar, String fases, String zona) {
	this.resulfase = fases;
	this.zona=zona;
	this.primerPar[0]=primerPar[0];
	this.primerPar[1]=primerPar[1];
	this.primerPar[2]=primerPar[2];
	this.primerPar[3]=primerPar[3];
}

//constructor para controlar si ya se jugaron los partidos
public Partido(String fechas, String[] primerPar) {
	this.primerPar[0]=primerPar[0];
	this.primerPar[1]=primerPar[2];
	this.mfec=fechas;
}

//constructor para grabar los resultados oficiales del segundo partido
public Partido(String fases, String[] primerPar, String zona) {
	this.resulfase = fases;
	this.zona=zona;
	this.primerPar[0]=primerPar[0];
	this.primerPar[1]=primerPar[1];
	this.primerPar[2]=primerPar[2];
	this.primerPar[3]=primerPar[3];
	
}

public void grabarfechas() {
	try {
		for(String linea: Files.readAllLines(fechasPar)) {
			basefec = linea.toString();
		}
		if (null!=basefec) {
			basefec=basefec+","+primerPar[0]+" "+primerPar[1]+", "+mfec;
		}else {
			basefec = primerPar[0]+" "+primerPar[1]+", "+mfec;
		}
		Files.writeString(fechasPar, basefec);
	}
	catch(IOException e) {
		System.err.print(e);
	}

}

//METODO PARA GRABAR LOS RESULTADOS DE LOS PARTIDOS
public void grabarfases() {
	String grabaresultado[] = new String[6];
	grabaresultado[0] = this.resulfase;
	grabaresultado[1] = this.primerPar[0];
	grabaresultado[2] = this.primerPar[1];
	grabaresultado[3] = this.primerPar[2];
	grabaresultado[4] = this.primerPar[3];
	grabaresultado[5] = this.zona;

	datos1 = Arrays.toString(grabaresultado);		
	try {
		for (String leerlineas : Files.readAllLines(resultados)) {
			resgrupos = String.valueOf(leerlineas);
			}
		if (null!=resgrupos) {
			String gruposres[] = resgrupos.split(",");

			for(int i = 0; i<gruposres.length;i++) {

				if ((grabaresultado[0].equals(gruposres[con]))&&((" "+grabaresultado[1]).equals(gruposres[con1]))&&((" "+grabaresultado[3]).equals(gruposres[con3]))) {
					System.out.println("EL PARTIDO YA FUE CARGADO");
					graba = false;
					break;
				}
				System.out.println(con);
				con = con + 6;
				con1 = con1 + 6;
				con3 = con3 + 6;
				if (con3>gruposres.length) {
					System.out.println(con3+"-dg"+gruposres.length+"no es igual");
					graba = true;
					break;
					
				}

			}
			
			if (graba) {
				datos1 = datos1.substring(1, datos1.length()-1)+","+resgrupos;//.substring(0,datos2.length()-1);		

			}
			
		}else {graba = true;
				System.out.print("no existe");
				datos1 = datos1.substring(1, datos1.length()-1);
		}
		if (graba) {
			Files.writeString(resultados, datos1);
			System.out.println("RESULTADOS DE ZONA DE GRUPOS GUARDADOS");
			
			try {
				if (!Files.exists(reducida)) {
					Files.createFile(reducida);
				}
				if (!Files.exists(redugana)) {
					Files.createFile(redugana);
				}
					for (String leerlista : Files.readAllLines(reducida)) {
					reducir = String.valueOf(leerlista);
					}
					for (String leerlistas : Files.readAllLines(redugana)) {
					redgan = String.valueOf(leerlistas);
					}
					if (null!=reducir) {
						reducir =reducir+","+this.primerPar[0].toString()+" "+this.primerPar[1].toString()+" "+this.primerPar[2].toString()+" "+this.primerPar[3].toString();
						if (Integer.parseInt(this.primerPar[1])>Integer.parseInt(this.primerPar[3])){
							redgan = redgan +","+this.primerPar[0].toString()+" "+this.primerPar[1].toString()+" GANADOR "+this.primerPar[2].toString()+" "+this.primerPar[3].toString()+" PERDEDOR";
						}else if (Integer.parseInt(this.primerPar[1])<Integer.parseInt(this.primerPar[3])) {
							redgan = redgan +","+this.primerPar[0].toString()+" "+this.primerPar[1].toString()+" PERDEDOR "+this.primerPar[2].toString()+" "+this.primerPar[3].toString()+" GANADOR";
						}else {
							redgan = redgan +","+this.primerPar[0].toString()+" "+this.primerPar[1].toString()+" EMPATE "+this.primerPar[2].toString()+" "+this.primerPar[3].toString()+" EMPATE";
						}
					}else {
						reducir =this.primerPar[0].toString()+" "+this.primerPar[1].toString()+" "+this.primerPar[2].toString()+" "+this.primerPar[3].toString();
						System.out.println("sissss");
						if (Integer.parseInt(this.primerPar[1])>Integer.parseInt(this.primerPar[3])){
							redgan = this.primerPar[0].toString()+" "+this.primerPar[1].toString()+" GANADOR "+this.primerPar[2].toString()+" "+this.primerPar[3].toString()+" PERDEDOR";
						}else if (Integer.parseInt(this.primerPar[1])<Integer.parseInt(this.primerPar[3])) {
							redgan = this.primerPar[0].toString()+" "+this.primerPar[1].toString()+" PERDEDOR "+this.primerPar[2].toString()+" "+this.primerPar[3].toString()+" GANADOR";
						}else {
							redgan = this.primerPar[0].toString()+" "+this.primerPar[1].toString()+" EMPATE "+this.primerPar[2].toString()+" "+this.primerPar[3].toString()+" EMPATE";
						}
					}

				Files.writeString(reducida, reducir);
				Files.writeString(redugana, redgan);
				
			}
			catch(IOException e) {
				System.err.print(e);
			}
			
		}else {
			System.out.println("RESULTADOS NO SE GUARDARON");

		}
	}
	catch(IOException e) {
		System.err.print(e);
	}
	
}



public void mostrar() {

}
}
