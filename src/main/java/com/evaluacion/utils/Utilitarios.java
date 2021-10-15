package com.evaluacion.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilitarios {

	public static boolean verificarLetras(String cadena) {
		boolean respuesta= false;
		for (int i = 0; i < cadena.length(); i++)
		{
			char caracter = cadena.toUpperCase().charAt(i);
			int valorASCII = (int)caracter;
			if (valorASCII != 165 && (valorASCII < 65 || valorASCII > 90))
				respuesta=true; 
		}
		return respuesta;
	}
	
	public static boolean verificarCedula(String cadena) {
		boolean cadenaCorrecta = false;
		 
		try {
		 
		if (cadena.length() == 10) // ConstantesApp.Longitudcadena
		{
		int tercerDigito = Integer.parseInt(cadena.substring(2, 3));
		if (tercerDigito < 6) {
		// Coeficientes de validación cédula
		// El decimo digito se lo considera dígito verificador
		 int[] coefValcadena = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
		 int verificador = Integer.parseInt(cadena.substring(9,10));
		 int suma = 0;
		 int digito = 0;
		for (int i = 0; i < (cadena.length() - 1); i++) {
		 digito = Integer.parseInt(cadena.substring(i, i + 1))* coefValcadena[i];
		 suma += ((digito % 10) + (digito / 10));
		}
		 
		if ((suma % 10 == 0) && (suma % 10 == verificador)) {
		 cadenaCorrecta = true;
		}
		else if ((10 - (suma % 10)) == verificador) {
		 cadenaCorrecta = true;
		} else {
		 cadenaCorrecta = false;
		}
		} else {
		cadenaCorrecta = false;
		}
		} else {
		cadenaCorrecta = false;
		}
		} catch (NumberFormatException nfe) {
		cadenaCorrecta = false;
		} catch (Exception err) {
		System.out.println("Una excepcion ocurrio en el proceso de validadcion");
		cadenaCorrecta = false;
		}
		 
		if (!cadenaCorrecta) {
		System.out.println("La Cédula ingresada es Incorrecta");
		}
		return cadenaCorrecta;
		}
	
	public static boolean verificarCorreo(String cadena) {
		boolean respuesta= false;
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(cadena);
 
        if (mather.find() == true) {
            respuesta=false;
        } else {
            respuesta=true;
        }
		return respuesta;
	}
	
	public static boolean verificarNumeros(String cadena) {
		try {
			Long.parseLong(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}
	
	public static boolean verificarNoVacio(String cadena) {
		boolean respuesta= false;
		if(cadena==null ||cadena.equals(""))
			respuesta=true;
		return respuesta;
	}
	
}
