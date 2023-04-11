package pronosticodeportivo;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Participantes {

	static String reducir;
	static String contenido1;
	static String contenido2;
	static String ganadores;
	static String perdedores;
	static int rep;
	static String repnom;
	static String repnom2;
	static String nombregana;
	static String valores;
	static String variospronos;
	static String redupronos;
	List<Object> resultadofina = new ArrayList<Object>();
	List<Object>resultadofinal = new ArrayList<Object>();


	public Participantes(){

	}
		
		/*static Path fechasPar = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\fechas.csv");
		static Path partidos = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\zonas.csv");
		static Path resultados = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\resultado.csv");
		static Path reducida = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\resullista.csv");
		static Path pronosreducido = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\pronosredu.csv");
		static Path ganador = Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\ganadoresfinales.csv");
		*/
		
	static Path reducida = Paths.get("resullista.csv");
	static Path pronosreducido = Paths.get("pronosredu.csv");
	static Path ganador = Paths.get("ganadoresfinales.csv");
	
	public void mostrar() {
		try {
			if (!Files.exists(pronosreducido)) {
				System.out.println("NO SE CARGARON PRONOSTICOS TODAVÍA");
			}else {
				for (String leerlineas : Files.readAllLines(pronosreducido)) {
				variospronos = String.valueOf(leerlineas);
				}
				if (null==variospronos) {
					System.out.println("NO SE CARGARON PRONOSTICOS TODAVÍA");
				}else {
					for (String leerlista : Files.readAllLines(ganador)) {
					valores = String.valueOf(leerlista);
					}
					String [] varios = variospronos.split(",");
					System.out.println("Pronosticos realizados");
					for(int i=0;i<varios.length;i++) {
						System.out.print(varios[i]+" ");
						if(i%2!=0) {
							System.out.println(" ");
						}
	
					}
				//**************************************
				
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
									ganadores=contenido1+" " +comprobar.get(i).toString()+" GANADOR, ";
									resultadofina.add(ganadores);
								}else {
									i++;
									perdedores=contenido1+" "+comprobar.get(i).toString()+" PERDEDOR, ";
									resultadofinal.add(perdedores);
								}
							}
						}
					}
					System.out.println(" ");
					System.out.println("GANADORES: "+resultadofina.toString());
					System.out.println(" ");
					System.out.println("PERDEDORES: "+resultadofinal.toString());
					System.out.println(" ");

				//****************************************

					System.out.println("PARTICIPANTE - PUNTOS: ");
					System.out.println(valores);
				}
			}
		}
		catch(IOException e) {
			System.err.print(e);
		}
	}
}	
	
