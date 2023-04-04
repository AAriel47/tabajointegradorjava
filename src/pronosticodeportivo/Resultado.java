package pronosticodeportivo;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;


public class Resultado {
	String reducir;
	
	Path redugana= Paths.get("C:\\Users\\lucas\\OneDrive\\Escritorio\\Curso Java\\ganadores.csv");
	
	public Resultado() {
		
	}
	
	public void mostrar() {	
		try {
			if (!Files.exists(redugana)) {
				System.out.println("NO SE HAN INGRESADO DATOS TODAVÍA");
			}else {
				for (String leerlista : Files.readAllLines(redugana)) {
						reducir = String.valueOf(leerlista);
				}
						if (null!=reducir) {

							System.out.println(reducir);
						}else {
							System.out.println("NO SE HAN INGRESADO DATOS TODAVÍA");
	
						}
				}
			}
			catch(IOException e) {
				System.err.print(e);
			}
	}
}