package pronosticodeportivo;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public class pronostico {
	
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
	//private String mfec;
	private LocalDate fecha;
	private LocalTime hora;
	private String[] primerPar = new String[4];
	private String[] segundoPar = new String[4];
	
	static Path fechasPar = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\fechas.csv");
	static Path partidos = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\zonas.csv");
	static Path resultados = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\resultado.csv");
	static Path reducida = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\resullista.csv");
	static Path pronosreducido = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\pronosredu.csv");
	static Path ganador = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\ganadoresfinales.csv");
	
	
	Path pronostico = Paths.get("pronostico.csv");
	



//constructor para grabar los 2 pronosticos
public pronostico(String nombre, String zona, String[] primerPar, String[] segundoPar) {
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
public pronostico(String[] segundoPartido, String nombre, String zona, String[] primerPar) {
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
public pronostico(String[] primerPar, String[] segundoPar, String nombre, String zona) {
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

}
