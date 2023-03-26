package pronosticodeportivo;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class constructor {

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
	String redupronos;
	private String nombre;
	private String mfec;
	private LocalDate fecha;
	private LocalTime hora;
	private String[] primerPar = new String[4];
	private String[] segundoPar = new String[4];

	Path fechasPar = Paths.get("fechas.csv");
	Path pronostico = Paths.get("pronostico.csv");
	Path reducida = Paths.get("resullista.csv");
	Path pronosreducido = Paths.get("pronosredu.csv");
	static Path resultados = Paths.get("resultado.csv");

	//constructor para grabar los resultados oficiales del primer partido
	public constructor(String[] primerPar, String fases, String zona) {
		this.resulfase = fases;
		this.zona=zona;
		this.primerPar[0]=primerPar[0];
		this.primerPar[1]=primerPar[1];
		this.primerPar[2]=primerPar[2];
		this.primerPar[3]=primerPar[3];
	}

	//constructor para controlar si ya se jugaron los partidos
	public constructor(String fechas, String[] primerPar) {
		this.primerPar[0]=primerPar[0];
		this.primerPar[1]=primerPar[2];
		this.mfec=fechas;
	}

	//constructor para grabar los resultados oficiales del segundo partido
	public constructor(String fases, String[] primerPar, String zona) {
		this.resulfase = fases;
		this.zona=zona;
		this.primerPar[0]=primerPar[0];
		this.primerPar[1]=primerPar[1];
		this.primerPar[2]=primerPar[2];
		this.primerPar[3]=primerPar[3];

	}

	//constructor para grabar los 2 pronosticos
	public constructor(String nombre, String zona, String[] primerPar, String[] segundoPar) {
		this.nombre = nombre;
		this.zona=zona;
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();
		this.primerPar[0]=primerPar[0];
		this.primerPar[1]=primerPar[1];
		this.primerPar[2]=primerPar[2];
		this.primerPar[3]=primerPar[3];
		this.segundoPar[0]=segundoPar[0];
		this.segundoPar[1]=segundoPar[1];
		this.segundoPar[2]=segundoPar[2];
		this.segundoPar[3]=segundoPar[3];
	}
	//constructor para grabar solo el primer pronostico
	public constructor(String[] segundoPartido, String nombre, String zona, String[] primerPar) {
		this.zona=zona;
		this.nombre = nombre;
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();
		this.primerPar[0]=primerPar[0];
		this.primerPar[1]=primerPar[1];
		this.primerPar[2]=primerPar[2];
		this.primerPar[3]=primerPar[3];
		this.segundoPar[0]="NO DISPONIBLE";
		this.segundoPar[1]="NO DISPONIBLE";
		this.segundoPar[2]="NO DISPONIBLE";
		this.segundoPar[3]="NO DISPONIBLE";	
	}

	//constructor para grabar solo el segundo pronostico
	public constructor(String[] primerPar, String[] segundoPar, String nombre, String zona) {
		this.zona=zona;
		this.nombre = nombre;
		this.fecha = LocalDate.now();
		this.hora = LocalTime.now();
		this.primerPar[0]="NO DISPONIBLE";
		this.primerPar[1]="NO DISPONIBLE";
		this.primerPar[2]="NO DISPONIBLE";
		this.primerPar[3]="NO DISPONIBLE";
		this.segundoPar[0]=segundoPar[0];
		this.segundoPar[1]=segundoPar[1];
		this.segundoPar[2]=segundoPar[2];
		this.segundoPar[3]=segundoPar[3];
	}

	//METODO PARA GRABAR LOS PRONOSTICOS
	public void grabar() {
		String grabarpronos[] = new String[12];
		grabarpronos[0] = this.nombre;
		grabarpronos[1] = this.fecha.toString();
		grabarpronos[2] = this.hora.toString();
		grabarpronos[3] = this.zona;
		grabarpronos[4] = this.primerPar[0];
		grabarpronos[5] = this.primerPar[1];
		grabarpronos[6] = this.primerPar[2];
		grabarpronos[7] = this.primerPar[3];
		grabarpronos[8] = this.segundoPar[0];
		grabarpronos[9] = this.segundoPar[1];
		grabarpronos[10] = this.segundoPar[2];
		grabarpronos[11] = this.segundoPar[3];
		String datos = Arrays.toString(grabarpronos);		
		try {
			if (!Files.exists(pronostico)) {
				Files.createFile(pronostico);
			}else {
				for (String leerlineas : Files.readAllLines(pronostico)) {
					variospronos = String.valueOf(leerlineas);
				}
				if (null!=variospronos) {
					datos =datos+variospronos;
				}
			}
			Files.writeString(pronostico, datos);

			//CREO EL ARCHIVO DE PRONOSTICO REDUCIDO - QUE USO COMO TABLA INDICE

			try {
				if (!Files.exists(pronosreducido)) {
					Files.createFile(pronosreducido);
				}
				for (String leerlista : Files.readAllLines(pronosreducido)) {
					redupronos = String.valueOf(leerlista);
				}
				if (null!=redupronos) {
					redupronos =redupronos+","+this.primerPar[0].toString()+" "+this.primerPar[1].toString()+" "+this.primerPar[2].toString()+" "+this.primerPar[3].toString()+","+this.nombre+","+this.segundoPar[0].toString()+" "+this.segundoPar[1].toString()+" "+this.segundoPar[2].toString()+" "+this.segundoPar[3].toString()+","+this.nombre;
				}else {
					redupronos =this.primerPar[0].toString()+" "+this.primerPar[1].toString()+" "+this.primerPar[2].toString()+" "+this.primerPar[3].toString()+","+this.nombre+","+this.segundoPar[0].toString()+" "+this.segundoPar[1].toString()+" "+this.segundoPar[2].toString()+" "+this.segundoPar[3].toString()+","+this.nombre;
				}
				Files.writeString(pronosreducido, redupronos);

			}
			catch(IOException e) {
				System.err.print(e);
			}

		}
		catch(IOException e) {
			System.err.print(e);
		}
		System.out.println("PRONOSTICO GUARDADO");
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
					for (String leerlista : Files.readAllLines(reducida)) {
						reducir = String.valueOf(leerlista);
					}
					if (null!=reducir) {
						reducir =reducir+","+this.primerPar[0].toString()+" "+this.primerPar[1].toString()+" "+this.primerPar[2].toString()+" "+this.primerPar[3].toString();
					}else {
						reducir =this.primerPar[0].toString()+" "+this.primerPar[1].toString()+" "+this.primerPar[2].toString()+" "+this.primerPar[3].toString();
						System.out.println(reducir);
					}
					Files.writeString(reducida, reducir);

				}
				catch(IOException e) {
					System.err.print(e);
				}


				//---------------
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
