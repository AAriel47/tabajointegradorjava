package pronosticodeportivo;
	import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
	//static int resul1;
	//static int resul2;
	static String[] zonaselc;
	
	static Path fechasPar = Paths.get("fechas.csv");
	static Path partidos = Paths.get("zonas.csv");
	static Path resultados = Paths.get("resultado.csv");
	static Path reducida = Paths.get("resullista.csv");
	static Path pronosreducido = Paths.get("pronosredu.csv");
	static Path ganador = Paths.get("ganadoresfinales.csv");

	static String carga;
	static String carga2;
	static String[] seleccion=new String[4];
	static String[] primerPartido = new String[4];
	static String[] segundoPartido = new String[4];
	static String opcion;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner teclado = new Scanner(System.in);
		System.out.println("PRONOSTICO COPA DEL MUNDO 2022 - FECHA DEL PRONOSTICO: "+LocalDate.now()+" "+LocalTime.now());
		System.out.println("POR CADA ACIERTO UN PUNTO");
		System.out.println("SELECCIONE UNA ACTIVIDAD: ");
		System.out.println("a. Realizar un pronostico - b. Ver los equipos participantes");
		System.out.println("c. Ingresar los resultados finales de los partidos");
		System.out.println("d. Ver los ganadores");
		opcion = teclado.nextLine().substring(0,1).toLowerCase();
		switch(opcion) {
		case "a": 
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
						System.out.println("POR FAVOR INGRESE LOS GRUPOS EN ZONA.CVS");
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
				primerPartido[2]=seleccion[1];
				System.out.println(primerPartido[2]);
				System.out.println("Goles: ");
				primerPartido[3]=teclado.next();
				
				System.out.println("Fecha del partido: dd/mm/yyyy");
				fecha = teclado.next();
				permitir = control(primerPartido, fecha);
				
				System.out.println("SEGUNDO PARTIDO");
				segundoPartido[0]=seleccion[2];
				System.out.println(segundoPartido[0]);
				System.out.println("Goles: ");
				segundoPartido[1]=teclado.next();
				segundoPartido[2]=seleccion[3];
				System.out.println(segundoPartido[2]);
				System.out.println("Goles: ");
				segundoPartido[3] = teclado.next();

				System.out.println("Fecha del partido: dd/mm/yyyy");
				fecha2 = teclado.next();
				permitir1=control(segundoPartido, fecha2);		
				System.out.println("llego"+permitir+permitir1);
				if (permitir&&permitir1) {
					constructor cons1 = new constructor(nombre, zona, primerPartido, segundoPartido);
					cons1.grabar();
				}else if(permitir) {System.out.println("permitir");
					constructor primerpartido = new constructor(segundoPartido,nombre, zona, primerPartido);
					primerpartido.grabar();
				}else if(permitir1) {System.out.println("permitir1");
					constructor segundopartido = new constructor(primerPartido, segundoPartido, nombre,zona);
					segundopartido.grabar();
				}
				break;
		case "b":
			try {
				if (!Files.exists(partidos)) {
					System.out.println("No se ha creado el archivo zonas.csv");
				}else {
					for (String leerlineas : Files.readAllLines(partidos)) {
					participantes = String.valueOf(leerlineas);
					}
					
					String listado[]= participantes.split(";");
					if (null==participantes) {
						System.out.println("No se han cargado los equipos participantes");
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
			}
			catch(IOException e) {
				System.err.print(e);
			}
			break;
		case "c": 
			//******************
			System.out.println("SELECCIONE LA FASE POR FAVOR");
			System.out.println("1. FASES DE GRUPOS - 2. OCTAVOS DE FINAL - 3. CUARTOS DE FINAL - 4. SEMI-FINAL - 5. FINAL");
			fasopc=teclado.next().toLowerCase().substring(0,1);
			switch (fasopc) {
			case "1": resulfase = fases[0];
					break;
			case "2": resulfase = fases[1];
					break;
			case "3": resulfase = fases[2];
					break;
			case "4": resulfase = fases[3];
					break;
			case "5": resulfase = fases[4];
					break;
			default: System.out.println("OPCION NO VALIDA, VUELVA A INGRESAR LA FASE POR FAVOR");
					break;
			}
			
			System.out.println("ZONAS A, B, C, D, E, F, G, H");
			System.out.println(" ");
			System.out.println("Seleccione una zona por favor: ");
			zona = teclado.next().toUpperCase();
			try {
				if(!Files.exists(resultados)){
					Files.createFile(resultados);
					System.out.println("POR FAVOR INGRESE LOS GRUPOS EN RESULTADO.CVS");
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
			resopc = teclado.next().substring(0,1);
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
						/* 
						 *fecha1 = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("d/M/yyyy"));
						System.out.println(fecha1);
						LocalDate dia = LocalDate.parse("30/02/2023", DateTimeFormatter.ofPattern("d/M/yyyy"));
						if (fecha1.isAfter(dia)) {
							System.out.println("No se puede ingresar mas");
						}else {
							System.out.println("Si se puede ingresar");
						}*/
						
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
						//constructor cons2 = new constructor(primerPartido, resulfase, zona);
						constructor cons2 = new constructor(primerPartido, resulfase, zona);
						constructor cons4 = new constructor(fecha, primerPartido);
						cons4.grabarfechas();
						cons2.grabarfases();
						break;
			case "b" :
				
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
						constructor cons3 = new constructor(resulfase, primerPartido, zona);
						constructor cons5 = new constructor(fecha, primerPartido);
						cons3.grabarfases();
						cons5.grabarfechas();
						break;
			default: System.out.println("OPCION NO VALIDA");
						break;
			}
			//*******
		case "d":
				try {
						for (String leerlista : Files.readAllLines(reducida)) {
						reducir = String.valueOf(leerlista);
						}
						
						for (String leerlista1 : Files.readAllLines(pronosreducido)) {
							redupronos = String.valueOf(leerlista1);
						}

						//for(int a=0, a<lista1. )
				}
				catch(IOException e) {
					System.err.print(e);
				}
				String lista[]=reducir.split(",");
				List resuoriginal = new ArrayList();
				for (int re=0;re<lista.length;re++) {
					resuoriginal.add(lista[re]);
				}
				List resultadofinal = new ArrayList();
				String[] lista1 = redupronos.split(",");
				List comprobar =new ArrayList();
				for (int con=0;con<lista1.length;con++) {
					comprobar.add(lista1[con]);
				}
				for(int e = 0; e<resuoriginal.size();e++) {
					contenido1=resuoriginal.get(e).toString();
					for(int i = 0;i<comprobar.size();i++) {
						contenido2=comprobar.get(i).toString();
						if (contenido1.equals(contenido2)) {
							i++;
							ganadores=comprobar.get(i).toString();
							resultadofinal.add(ganadores);
						}
					}
				}
				System.out.println(resultadofinal);
				rep=0;
				List resultados = new ArrayList();
				for(int fi=0;fi<resultadofinal.size();fi++) {
					repnom=resultadofinal.get(fi).toString();
					for(int fin=0;fin<resultadofinal.size();fin++) {
						repnom2=resultadofinal.get(fin).toString();
						if(repnom.equals(repnom2)) {
							rep++;
						}
					}
					if(resultados.contains(repnom+rep)) {
						;
					}else {
						resultados.add(repnom+rep);
					}
					rep=0;
				}
				//}
				String grabarnombres = resultados.toString();
				System.out.println(resultados);

				try {
					if(!Files.exists(ganador)) {
						Files.createFile(ganador);
					}
					Files.writeString(ganador, grabarnombres);
				}
				catch (IOException e) {
					System.err.print(e);
				}
				break;
			
		default: System.out.println("OPCION NO VALIDA, SELECCIONE OTRA");
		teclado.close();
		}
		
	}

	public static boolean control(String[]primerPartido, String fecha) {
		//carfec = LocalDate.format(DateTimeFormatter.ofPattern("d/M/yyyy")).toString();	
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
					System.out.println(carfec1+"  "+carfec1.length()+"  carfec1seguro "+fecact+"  "+fecact.length());

				if (carfec1.equals(fecact)) {
					i++;
					cc = mfecha[i].toString();
					cc = cc.substring(1);
					System.out.println("diadepusfecha1"+dia+"   "+cc);
					
						fecha1=LocalDate.parse(cc, DateTimeFormatter.ofPattern("d/M/yyyy"));
						if (dia.isAfter(fecha1)) {
							permi=false;
							System.out.println("YA NO SE PUEDE EFECTUAR UN PRONOSTICO");
						}else {
							permi=true;
							System.out.println("siiiii");
						}
					
				}
			}
return permi;
}	
}
