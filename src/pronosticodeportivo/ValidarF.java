package pronosticodeportivo;

import java.time.LocalDate;

public class ValidarF {
	static LocalDate confecha;

	public ValidarF() {

	}

	public void Validar(String fecha)throws ControlFecha {
		if (fecha.length()!=10){
			throw new ControlFecha("Fecha no valida");
		}
		try {
			Integer.parseInt(fecha.substring(0, 2));
			Integer.parseInt(fecha.substring(3,5));
			Integer.parseInt(fecha.substring(6, 10));
		}
		catch(NumberFormatException e) {
			throw new ControlFecha("Fecha no valida");
		}
		System.out.print(Integer.parseInt(fecha.substring(0,2))+" - "+Integer.parseInt(fecha.substring(3,5)));
		if((Integer.parseInt(fecha.substring(0,2))>31)&&(Integer.parseInt(fecha.substring(3,5))==1)) {
			throw new ControlFecha("Fecha no valida");
		}else if((Integer.parseInt(fecha.substring(0,2))>28)&&(Integer.parseInt(fecha.substring(3,5))==2)) {
			throw new ControlFecha("Fecha no valida");
		}else if((Integer.parseInt(fecha.substring(0,2))>31)&&(Integer.parseInt(fecha.substring(3,5))==3)) {
			throw new ControlFecha("Fecha no valida");
		}else if((Integer.parseInt(fecha.substring(0,2))>30)&&(Integer.parseInt(fecha.substring(3,5))==4)) {
			throw new ControlFecha("Fecha no valida");
		}else if((Integer.parseInt(fecha.substring(0,2))>31)&&(Integer.parseInt(fecha.substring(3,5))==5)) {
			throw new ControlFecha("Fecha no valida");
		}else if((Integer.parseInt(fecha.substring(0,2))>30)&&(Integer.parseInt(fecha.substring(3,5))==6)) {
			throw new ControlFecha("Fecha no valida");
		}else if((Integer.parseInt(fecha.substring(0,2))>31)&&(Integer.parseInt(fecha.substring(3,5))==7)) {
			throw new ControlFecha("Fecha no valida");
		}else if((Integer.parseInt(fecha.substring(0,2))>31)&&(Integer.parseInt(fecha.substring(3,5))==8)) {
			throw new ControlFecha("Fecha no valida");
		}else if((Integer.parseInt(fecha.substring(0,2))>30)&&(Integer.parseInt(fecha.substring(3,5))==9)) {
			throw new ControlFecha("Fecha no valida");
		}else if((Integer.parseInt(fecha.substring(0,2))>31)&&(Integer.parseInt(fecha.substring(3,5))==10)) {
			throw new ControlFecha("Fecha no valida");
		}else if((Integer.parseInt(fecha.substring(0,2))>30)&&(Integer.parseInt(fecha.substring(3,5))==11)) {
			throw new ControlFecha("Fecha no valida");
		}else if((Integer.parseInt(fecha.substring(0,2))>31)&&(Integer.parseInt(fecha.substring(3,5))==12)) {
			throw new ControlFecha("Fecha no valida");
		}

	}

}
