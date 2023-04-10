package pronosticodeportivo;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import java.nio.file.Paths;
import java.nio.file.Path;

public class Puntaje {

	static String reducir;
	static String redupronos;
	static String contenido1;
	static String contenido2;
	static String ganadores;
	static int rep;
	static String repnom;
	static String repnom2;
	static String nombregana;
	
	public Puntaje() {
	
	}

	


	
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
	
	public void puntajes() {
		if ((!Files.exists(reducida))|| (!Files.exists(pronosreducido))){
			System.out.println("AUN NO SE GRABARON LOS PRONOSTICOS NI LOS RESULTADOS DE LOS PARTIDOS");
		}else {
			try {
					for (String leerlista : Files.readAllLines(reducida)) {
					reducir = String.valueOf(leerlista);
					}
					
					for (String leerlista1 : Files.readAllLines(pronosreducido)) {
						redupronos = String.valueOf(leerlista1);
					}
	
			}
			catch(IOException e) {
				System.err.print(e);
			}
			//EN ESTA ZONA SE COMPARA LOS DATOS DE LOS PRONOSTICOS RESUMIDOS(CON CAMPO CLAVE) Y LOS RESULTADOS RESUMIDOS (CON CAMPO CLAVE)
			String lista[]=reducir.split(",");
			List<String> resuoriginal = new ArrayList<String>();
			for (int re=0;re<lista.length;re++) {
				resuoriginal.add(lista[re]);
			}
			List<String> resultadofinal = new ArrayList<String>();
			String[] lista1 = redupronos.split(",");
			List<String> comprobar =new ArrayList<String>();
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
			rep=0;
			List<Object> resultados = new ArrayList<Object>();
			for(int fi=0;fi<resultadofinal.size();fi++) {
				repnom=resultadofinal.get(fi).toString();
				for(int fin=0;fin<resultadofinal.size();fin++) {
					repnom2=resultadofinal.get(fin).toString();
					if(repnom.equals(repnom2)) {
						rep++;
					}
				}
				if(resultados.contains(repnom+" "+rep)) {
					;
				}else {
					resultados.add(repnom+" "+rep);
				}
				rep=0;
			}
			String grabarnombres = resultados.toString();
			System.out.println(resultados);
			System.out.println(" ");
			try {
				if(!Files.exists(ganador)) {
					Files.createFile(ganador);
				}
				Files.writeString(ganador, grabarnombres);
			}
			catch (IOException e) {
				System.err.print(e);
			}
		}
	}
}
