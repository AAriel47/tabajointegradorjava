package pronosticodeportivo;
//import java.util.ArrayList;
import java.util.Arrays;
//import java.util.List;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
	public class Pronosticodeportivo {
	static boolean permi;
	static String participantes;
	static String zona;
	static String nombre;
	static boolean continuar = false;
	static int[] result;
	static String resul1;
	static String fasopc;
	static String resulfase;
	static String resopc;
	static String carfec;
	static String carfec1;
	static String linfec;
	static String[] fases= {"FASES DE GRUPOS","OCTAVOS DE FINAL","CUARTOS DE FINAL","SEMI-FINAL","FINAL"};
	static String contenido1;
	static String contenido2;
	static String ganadores;
	static String nombregana;
	static String gol;
	static int gol2;
	
	static String fecact;
	static boolean permitir;
	static boolean permitir1;
	static boolean permitir2;
	static String reducir;
	static String redupronos;

	static String[] mfecha;
	static LocalDate fecha1;
	static String fecha;
	static String fecha2;
	static String mfec;
	static LocalTime hora;
	static int rep;
	static String repnom;
	static String repnom2;
	static String[] zonaselc;
	
	static Path fechasPar = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\fechas.csv");
	static Path partidos = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\zonas.csv");
	static Path resultados = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\resultado.csv");
	static Path reducida = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\resullista.csv");
	static Path pronosreducido = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\pronosredu.csv");
	static Path ganador = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\ganadoresfinales.csv");

	static String carga;
	static String carga2;
	static String[] seleccion=new String[4];
	static String[] primerPartido = new String[4];
	static String[] segundoPartido = new String[4];
	static String opcion;
	public static void main(String[] args){

	Scanner teclado = new Scanner(System.in);
	while(true) {
		System.out.println("PRONOSTICO COPA DEL MUNDO 2022 - FECHA DEL PRONOSTICO: "+LocalDate.now()+" "+LocalTime.now());
		System.out.println("POR CADA ACIERTO UN PUNTO");
		System.out.println("SELECCIONE UNA ACTIVIDAD: ");
		System.out.println("a. Realizar un pronostico");
		System.out.println("b. Ver los equipos participantes");
		System.out.println("c. Ingresar los resultados finales de los partidos");
		System.out.println("d. Ver los ganadores de los pronosticos");
		System.out.println("e. Ver resultado de los partidos: Perdedor - Ganador - Empate");
		System.out.println("f. Salir");
		opcion = teclado.nextLine().toLowerCase();
		switch(opcion) {
		case "a": 
				//Bloque de sentencias para realizar el pronostico
			
				System.out.println(" ");
				System.out.println("Ingrese el nombre del participante:");
				nombre = teclado.nextLine();
				System.out.println(" ");
				System.out.println("ZONAS A, B, C, D, E, F, G, H");
				System.out.println("Seleccione una zona por favor: ");
				zona = teclado.nextLine().toUpperCase().substring(0, 1);
				try {
					if(!Files.exists(partidos)){
						Files.createFile(partidos);
						System.out.println("POR FAVOR INGRESE LOS GRUPOS EN ZONA.CVS");//EL ARCHIVO ZONA.CVS CONTIENE LOS GRUPOS DE LOS EQUIPOS
					}
	
					for(String linea : Files.readAllLines(partidos)){
						carga = String.valueOf(linea);
					}
					String[] selecciones = carga.split(";");
					int c = 0;
					for(int i=0;i<selecciones.length;i++) {
	
						carga2 = selecciones[i];
	
						if (zona.equalsIgnoreCase(carga2.substring(0, 1))) {
							seleccion[c]= carga2;
							c++;
						}
						if (c==4) {
							break;
						}
					}
				}
				catch (IOException e) {
					System.out.print(e);
				}
				System.out.println("Zona: "+ zona + Arrays.toString(seleccion));
				System.out.println(" ");
				
				System.out.println("PRIMER PARTIDO");
				primerPartido[0] = seleccion[0];
				System.out.println(primerPartido[0]);
				System.out.println("Goles: ");
				primerPartido[1]=teclado.next();
				
				try {
					gol=primerPartido[1];
					gol2 = Integer.parseInt(gol);
				}
				catch (NumberFormatException ex) {
					System.err.println(ex);
					System.out.println("VALOR NO NUMERICO, VUELVA A INGRESAR LOS DATOS");
					break;
				}				
				
				primerPartido[2]=seleccion[1];
				System.out.println(primerPartido[2]);
				System.out.println("Goles: ");
				primerPartido[3]=teclado.next();
				
				try {
					gol=primerPartido[3];
					gol2=Integer.parseInt(gol);
				}
				catch(NumberFormatException ex) {
					System.err.println(ex);
					System.out.println("VALOR NO NUMERICO, VUELVA A INGRESAR LOS DATOS");
					break;
				}				
				//LA FECHA LA USO PARA SABER SI TODAVIA SE PUEDE HACER UNA APUESTA, SE PUEDE HASTA EL DIA DEL JUEGO
				System.out.println("Fecha del partido: dd/mm/yyyy");
				fecha = teclado.next();
				//USO EL OBJETO F PARA VALIDAR LA FECHA
				ValidarF f = new ValidarF();
				try {
					f.Validar(fecha);
				}
				catch(ControlFecha ex) {
					System.out.print("ERROR AL INGRESAR LA FECHA");
					break;
				}				
				//EL PROCEDIMIENTO CONTROL ES EL QUE VERIFICA QUE SE PUEDA REALIZAR LA APUESTA, CONTROLA QUE NO SE REALIZA LA APUESTA SI EL PARTIDO YA SE JUGO
				permitir = control(primerPartido, fecha);
				
				System.out.println("SEGUNDO PARTIDO");
				segundoPartido[0]=seleccion[2];
				System.out.println(segundoPartido[0]);
				System.out.println("Goles: ");
				segundoPartido[1]=teclado.next();
				
				try {
					gol=segundoPartido[1];
					gol2=Integer.parseInt(gol);
				}
				catch(NumberFormatException ex) {
					System.err.println(ex);
					System.out.println("VALOR NO NUMERICO, VUELVA A INGRESAR LOS DATOS");
					break;
				}				
				
				segundoPartido[2]=seleccion[3];
				System.out.println(segundoPartido[2]);
				System.out.println("Goles: ");
				segundoPartido[3] = teclado.next();

				try {
					gol=segundoPartido[3];
					gol2=Integer.parseInt(gol);
				}
				catch(NumberFormatException ex) {
					System.err.println(ex);
					System.out.println("VALOR NO NUMERICO, VUELVA A INGRESAR LOS DATOS");
					break;
				}				
				
				System.out.println("Fecha del partido: dd/mm/yyyy");
				fecha2 = teclado.next();
				
				ValidarF f2 = new ValidarF();
				try {
					f2.Validar(fecha2);
				}
				catch(ControlFecha ex) {
					System.out.print("ERROR AL INGRESAR LA FECHA");
					break;
				}
				
				//EL PROCEDIMIENTO CONTROL ES EL QUE VERIFICA QUE SE PUEDA REALIZAR LA APUESTA, CONTROLA QUE NO SE REALIZA LA APUESTA SI EL PARTIDO YA SE JUGO
				permitir1=control(segundoPartido, fecha2);	
				//OBJETOS QUE PERMITE LA GRABACIÓN DE DATOS
				if (permitir&&permitir1) {
					pronostico cons1 = new pronostico(nombre, zona, primerPartido, segundoPartido);
					cons1.grabar();
				}else if(permitir) {
					pronostico primerpartido = new pronostico(segundoPartido,nombre, zona, primerPartido);
					primerpartido.grabar();
				}else if(permitir1) {//System.out.println("permitir1");
					pronostico segundopartido = new pronostico(primerPartido, segundoPartido, nombre,zona);
					segundopartido.grabar();
				}
				break;
		case "b":
			//BLOQUE QUE PERMITE TENER UN LISTADO DE LOS EQUIPOS POR ZONA
			try {
				if (!Files.exists(partidos)) {
					System.out.println("No se ha creado el archivo zonas.csv".toUpperCase());
				}else {
					for (String leerlineas : Files.readAllLines(partidos)) {
					participantes = String.valueOf(leerlineas);
					}
					
					String listado[]= participantes.split(";");
					if (null==participantes) {
						System.out.println("No se han cargado los equipos participantes".toUpperCase());
					}else {
						int imp = 1;
						for(int i=0; i<listado.length;i++) {
							if ((imp<4)) {
								System.out.print("GRUPO:  "+listado[i]+" - ");
								imp++;
							}else {
								System.out.println("GRUPO: "+listado[i]+" - ");
								imp =1;
							}
						}
					}
				}
				System.out.println("PRESIONE UNA TECLA PARA CONTINUAR");
				teclado.next();
				System.out.println(" ");

			}
			catch(IOException e) {
				System.err.print(e);
			}
			break;
		case "c": 
			
			//BLOQUE QUE PERMITE EL INGRESO DE LOS RESULTADOS DE LOS PARTIDOS PARA CONTROLAR OFICIALMENTE LAS APUESTAS
			while(true) {

				System.out.println("SELECCIONE LA FASE POR FAVOR");
				//System.out.println("1. FASES DE GRUPOS - 2. OCTAVOS DE FINAL - 3. CUARTOS DE FINAL - 4. SEMI-FINAL - 5. FINAL");
				System.out.println("1. FASES DE GRUPOS ");

				fasopc=teclado.next().toLowerCase();
				if ("1".equals(fasopc)) {
					resulfase = fases[0];
					break;
				}else {System.out.println("OPCION NO VALIDA, VUELVA A INGRESAR LA FASE POR FAVOR");
						continue;
				}
			}
			System.out.println("ZONAS A, B, C, D, E, F, G, H");
			System.out.println(" ");
			System.out.println("Seleccione una zona por favor: ");
			zona = teclado.next().toUpperCase();
			try {
				if(!Files.exists(resultados)){
					Files.createFile(resultados);
					System.out.println("ARCHIVO CREADO, YA PUEDE INGRESAR LOS GRUPOS EN RESULTADO.CVS");
				}else {
					for(String selector : Files.readAllLines(resultados)) {
						resul1 = String.valueOf(selector);
					}
				}
				for(String linea : Files.readAllLines(partidos)){
					carga = String.valueOf(linea);
				}
				String[] selecciones = carga.split(";");
				int c = 0;
				for(int i=0;i<selecciones.length;i++) {
					carga2 = selecciones[i];
					if (zona.equalsIgnoreCase(carga2.substring(0, 1))) {
						seleccion[c]= carga2;
						c++;
					}
					if (c==4) {
						break;
					}
				}
			}
			catch (IOException e) {
				System.out.print(e);
			}
			System.out.println("Zona: "+ zona + " "+((Arrays.toString(seleccion))));
			System.out.println("SELECCIONE: a. PRIMER PARTIDO - b. SEGUNDO PARTIDO");
			resopc = teclado.next().toLowerCase().substring(0,1);
			switch (resopc) {
			case "a" :
						try {
							if (!Files.exists(fechasPar)) {
								Files.createFile(fechasPar);
							}
						}
						catch(IOException e) {
							System.err.print(e);
						}
				
						System.out.println("INGRESE LA FECHA: dd/mm/aaaa");
						fecha=teclado.next();
						//VALIDACION DE LA FECHA (FORMATO FECHA)
						ValidarF f3 = new ValidarF();
						try {
							f3.Validar(fecha);
						}
						catch(ControlFecha ex) {
							System.out.print("ERROR AL INGRESAR LA FECHA");
							break;
						}							
						
						System.out.println(" ");
						System.out.println("PRIMER PARTIDO");
						primerPartido[0] = seleccion[0];
						System.out.println(primerPartido[0]);
						System.out.println("Goles: ");
						primerPartido[1]=teclado.next();
						primerPartido[2]=seleccion[1];
						System.out.println(primerPartido[2]);
						System.out.println("Goles: ");
						primerPartido[3]=teclado.next();
						//GRABO LOS RESULTADOS DEL PARTIDO Y CREO OTRO ARCHIVO RESUMIDO PARA CONTROL DEL PROCESO
						Partido cons2 = new Partido(primerPartido, resulfase, zona);
						Partido cons4 = new Partido(fecha, primerPartido);
						cons4.grabarfechas();
						cons2.grabarfases();
						break;
			case "b" :
						//BLOQUE DE SENTENCIAS IDEM AL ANTERIOR PERO PARA EL SEGUNDO PARTIDO
						try {
							if (!Files.exists(fechasPar)) {
								Files.createFile(fechasPar);
							}
						}
						catch(IOException e) {
							System.err.print(e);
						}
				
						System.out.println("INGRESE LA FECHA: dd/mm/aaaa");
						fecha=teclado.next();
				
						ValidarF f4 = new ValidarF();
						try {
							f4.Validar(fecha);
						}
						catch(ControlFecha ex) {
							System.out.print("ERROR AL INGRESAR LA FECHA");
							break;
						}						
						
						System.out.println(" ");
						System.out.println("SEGUNDO PARTIDO");
						primerPartido[0] = seleccion[2];
						System.out.println(primerPartido[0]);
						System.out.println("Goles: ");
						primerPartido[1]=teclado.next();
						primerPartido[2]=seleccion[3];
						System.out.println(primerPartido[2]);
						System.out.println("Goles: ");
						primerPartido[3]=teclado.next();
						Partido cons3 = new Partido(resulfase, primerPartido, zona);
						Partido cons5 = new Partido(fecha, primerPartido);
						cons3.grabarfases();
						cons5.grabarfechas();
						break;
			default: System.out.println("OPCION NO VALIDA, PRESIONE UNA TECLA...");
						teclado.next();
						break;
			}
		case "d":
				//BLOQUE DE SENTENCIAS PARA OBTENER EL LISTADO DE LOS GANADORES
				//LECTURA DE ARCHIVO RESUMIDO CON CAMPOS ID
				Puntaje Puntos = new Puntaje();
				Puntos.puntajes();
			
			
				break;
		case "e":
				Resultado mostrare = new Resultado();
				mostrare.mostrar();
				break;
				
		case "f":
				break;
		default: 
				System.out.println("OPCION NO VALIDA...");
		}
		System.out.println(" ");
		if("f".equals(opcion)) {
			teclado.close();
			break;
		}
	}
}
//METODO DE CONTROL POR FECHA PARA VERIFICAR QUE TODAVIA SE PUEDEN REALIZAR LAS APUESTAS	
	public static boolean control(String[]primerPartido, String fecha) {
		if(!Files.exists(resultados)){
			return true;
		}
		carfec1 = primerPartido[0]+" "+primerPartido[2];
		LocalDate dia = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("d/M/yyyy"));		
		try {
			for(String linea: Files.readAllLines(fechasPar)) {
				linfec = linea.toString().substring(0,linea.length());
				mfecha = linfec.split(",");
			}
		}
		catch(IOException e) {
			System.err.print(e);
		}
		String cc;
		for(int i = 0; i<mfecha.length;i++) {
			fecact = mfecha[i].toString();
				if (carfec1.equals(fecact)) {
					i++;
					cc = mfecha[i].toString();
					cc = cc.substring(1);
						fecha1=LocalDate.parse(cc, DateTimeFormatter.ofPattern("d/M/yyyy"));
						if (dia.isAfter(fecha1)) {
							permi=false;
							System.out.println("YA NO SE PUEDE EFECTUAR UN PRONOSTICO, EL PARTIDO YA SE JUGÓ");
						}else {
							permi=true;
						}
					
				}else {
					permi=true;
				}
			
			}
return permi;
}	
}
