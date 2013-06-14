package ar.com.admin.utils;

import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rdiaz
 * 
 *         Contiene metodos statics para la validacion de los objetos es para
 *         hacer validaciones usando AJAX.
 * 
 */
public abstract class Validator {

	public static String XML_HEADER = "<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?> ";

	public static StringBuffer abrirXML() {
		return new StringBuffer(XML_HEADER + "\n<datos>");
	}

	public static StringBuffer cerrarXML(StringBuffer xml) {
		return xml.append("\n</datos>");
	}

	public static StringBuffer addErrorXML(StringBuffer xml, String pMensaje) {
		xml.append("\n" + "<error>" + pMensaje + "</error>");
		return xml;
	}

	public static StringBuffer addIdJspXML(StringBuffer xml, String pMensaje) {
		xml.append("\n" + "<formId>" + pMensaje + "</formId>");
		return xml;
	}

	public static boolean requerido(String entrada, String label,
			StringBuffer pError) {
		if (entrada != null && !entrada.equals("")) {
			return true;
		}
		addErrorXML(pError, label + " es un dato obligatorio");

		return false;
	}

	public static boolean requerido(Object entrada, String label,
			StringBuffer pError) {
		if (entrada != null) {
			return true;
		}
		addErrorXML(pError, label + " es un dato obligatorio");

		return false;
	}

	public static boolean validarRequeridoSi(String entradaCondicion,
			String valorCondicion, String entrada, String label,
			StringBuffer pError) {

		if (entradaCondicion != null && entradaCondicion.equals(valorCondicion)) {
			if (entrada != null && !entrada.equals("")) {
				return true;
			}
			addErrorXML(pError, label + " es un dato obligatorio");

			return false;
		}

		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido
	 */
	public static boolean validarComboRequerido(String valorEntradaDistinto,
			String entrada, String label, StringBuffer pError) {

		if (entrada == null || entrada.equals("")) {
			return true;
		}
		if (entrada.equals(valorEntradaDistinto)) {
			addErrorXML(pError, label + " es un dato obligatorio");
			return false;
		}
		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido
	 */
	public static boolean validarComboRequeridoSi(String entradaCondicion,
			String valorCondicion, String valorEntradaDistinto, String entrada,
			String label, StringBuffer pError) {

		if (entradaCondicion != null && entradaCondicion.equals(valorCondicion)) {
			if (entrada != null && !entrada.equals(valorEntradaDistinto)) {
				return true;
			}
			addErrorXML(pError, label + " es un dato obligatorio");

			return false;
		}

		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido
	 */
	public static boolean validarEnteroMayorQue(int numeroMinimo,
			String entrada, String label, StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			int entradaInt = Integer.parseInt(entrada);
			if (entradaInt <= numeroMinimo) {
				addErrorXML(pError, label + " debe ser un número mayor a "
						+ Integer.toString(numeroMinimo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError, label + " debe ser un número entero");
			return false;
		}
		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido
	 */
	public static boolean validarEnteroMenorQue(int numeroMaximo,
			String entrada, String label, StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			int entradaInt = Integer.parseInt(entrada);
			if (entradaInt >= numeroMaximo) {
				addErrorXML(pError, label + " debe ser un número menor a "
						+ Integer.toString(numeroMaximo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError, label + " debe ser un número entero");
			return false;
		}
		return true;
	}

	public static boolean validarLongMayorQue(int numeroMinimo, String entrada,
			String label, StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			long entradaLong = Long.parseLong(entrada);
			if (entradaLong <= numeroMinimo) {
				addErrorXML(pError, label + " debe ser un número mayor a "
						+ Integer.toString(numeroMinimo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError, " debe ser un número entero");
			return false;
		}

		return true;
	}

	public static boolean validarDoubleMayorQue(int numeroMinimo,
			String entrada, String label, StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			double entradaDouble = Double.parseDouble(entrada);
			if (entradaDouble <= numeroMinimo) {
				addErrorXML(pError, label + " debe ser un número mayor a "
						+ Integer.toString(numeroMinimo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError,
					" debe ser un número entero con decimales v�lido");
			return false;
		}

		return true;
	}

	public static boolean validarDoubleMayorQue(double numeroMinimo,
			String entrada, String label, StringBuffer pError) {
		if (entrada == null || entrada.equals("")) {
			return true;
		}
		try {
			double entradaDouble = Double.parseDouble(entrada);
			if (entradaDouble <= numeroMinimo) {
				addErrorXML(pError, label + " debe ser un número mayor a "
						+ Double.toString(numeroMinimo));
				return false;
			}
		} catch (NumberFormatException e) {
			addErrorXML(pError,
					" debe ser un número entero con decimales v�lido");
			return false;
		}

		return true;
	}

	/*
	 * Si la entrada es nula entonces se considera valido chequea que el año se
	 * mayor que 1900 y menor que 2100
	 */
	public static boolean validarFechaValida(String pEntrada, String label,
			StringBuffer pError) {
		if (pEntrada == null || pEntrada.equals("")) {
			return true;
		}
		String entrada = new String(pEntrada);
		try {
			int positionBarra = entrada.indexOf('/');
			String dia = entrada.substring(0, positionBarra);
			entrada = entrada.substring(positionBarra + 1);
			positionBarra = entrada.indexOf('/');
			String mes = entrada.substring(0, positionBarra);
			entrada = entrada.substring(positionBarra + 1);
			String anio = entrada;
			int anioInt = Integer.parseInt(anio);
			int mesInt = Integer.parseInt(mes);
			int diaInt = Integer.parseInt(dia);
			if (diaInt > 31 || diaInt < 1) {
				addErrorXML(
						pError,
						label
								+ " invalida, el dia debe ser menor igual que 31 y mayor que 0");
				return false;
			} else if (mesInt > 12 || mesInt < 1) {
				addErrorXML(
						pError,
						label
								+ " invalida, el mes debe ser menor igual que 12 y mayor que 0");
				return false;
			} else if (anioInt > 2100 || anioInt < 1900) {
				addErrorXML(
						pError,
						label
								+ " invalida, el año debe ser menor igual que 2100 y mayor que 1900");
				return false;
			}

			return DateUtils.validateDate(pEntrada, true, "dd/MM/yyyy");

		} catch (Exception e) {
			addErrorXML(
					pError,
					label
							+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
			return false;
		}

	}

	/**
	 * Verifica que la fecha sea v�lida y anterior a fecha actual. si es nula o
	 * blanco devuelve true
	 * 
	 * @param pEntrada
	 * @param label
	 * @param pError
	 * @return boolean
	 */
	public static boolean validarFechaPasadaValida(String pEntrada,
			String label, StringBuffer pError) {
		boolean salida = true;
		if (pEntrada == null || pEntrada.equals("")) {
			return true;
		}
		if (!DateUtils.validateDate(pEntrada, true, "dd/MM/yyyy")) {
			addErrorXML(
					pError,
					label
							+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
			return false;
		}
		if (!DateUtils.validateDatePeriod(
				DateUtils.dateFromString(pEntrada, "dd/MM/yyyy"),
				GregorianCalendar.getInstance().getTime())) {
			addErrorXML(pError, label + " invalida, debe ser una fecha pasada.");
			salida = false;
		}

		return salida;
	}

	/**
	 * Verifica que la fecha sea v�lida y anterior a fecha actual. si es nula o
	 * blanco devuelve true
	 * 
	 * @param pEntrada
	 * @param label
	 * @param pError
	 * @return boolean
	 */
	public static boolean validarFechaMenorA(String pFechaDesde,
			String pFechaHasta, String labelFechaDesde, String labelFechaHasta,
			StringBuffer pError) {

		boolean salida = true;
		if (pFechaDesde == null || pFechaDesde.equals("")) {
			addErrorXML(pError, labelFechaDesde + " es un dato obligatorio");
			salida = false;
		} else {
			if (!DateUtils.validateDate(pFechaDesde, true, "dd/MM/yyyy")) {
				addErrorXML(
						pError,
						labelFechaDesde
								+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
				salida = false;
			}
		}

		if (pFechaHasta == null || pFechaHasta.equals("")) {
			addErrorXML(pError, labelFechaHasta + " es un dato obligatorio");
			salida = false;
		} else {
			if (!DateUtils.validateDate(pFechaHasta, true, "dd/MM/yyyy")) {
				addErrorXML(
						pError,
						labelFechaHasta
								+ " debe ser una fecha Valida, el formato esperado es dd/mm/yyyy");
				salida = false;
			}
		}

		if (salida
				&& DateUtils.isBefore(
						DateUtils.dateFromString(pFechaHasta, "dd/MM/yyyy"),
						DateUtils.dateFromString(pFechaDesde, "dd/MM/yyyy"))) {
			addErrorXML(pError, "Fecha Desde debe ser menor a Fecha Hasta");
			salida = false;
		}

		return salida;
	}

	public static boolean validarSN(String tipoLote) {
		return (tipoLote.equals("S") || tipoLote.equals("N"));
	}

	public static boolean validarEmail(String email, String label,
			StringBuffer pError) {
		if (email == null || email.equals("")) {
			return true;
		}
		// Input the string for validation
		// String email = "xyz@.com";
		// Set the email pattern string
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");

		// Match the given string with the pattern
		Matcher m = p.matcher(email);

		// check whether match is found
		boolean matchFound = m.matches();

		StringTokenizer st = new StringTokenizer(email, ".");
		String lastToken = null;
		while (st.hasMoreTokens()) {
			lastToken = st.nextToken();
		}

		if (matchFound && lastToken.length() >= 2
				&& email.length() - 1 != lastToken.length()) {
			// validate the country code
			return true;
		}

		addErrorXML(pError, label + " no es un e-mail v�lido");
		return false;

	}

	public static boolean validarAlfaNumerico(String valor, String label,
			StringBuffer pError) {
		if (valor == null || valor.equals("")) {
			return true;
		}
		Pattern p = Pattern.compile("[\\w]*");
		Matcher m = p.matcher(valor);
		if (m.matches()) {
			return true;
		}
		addErrorXML(pError, label + " no es un AlfaNumérico v�lido");
		return false;
	}

	public static boolean validarNumerico(String valor, String label,
			StringBuffer pError) {
		if (valor == null || valor.equals("")) {
			return true;
		}
		Pattern p = Pattern.compile("(?=[^A-Za-z]+$).*[0-9].*");
		Matcher m = p.matcher(valor);
		if (m.matches()) {
			return true;
		}
		addErrorXML(pError, label + " no es un Numérico v�lido");
		return false;
	}

	public static boolean validarCaracter(String valor, String label,
			StringBuffer pError) {
		if (valor == null || valor.equals("")) {
			return true;
		}
		Pattern p = Pattern.compile("[a-zA-Z]");
		Matcher m = p.matcher(valor);
		if (m.matches()) {
			return true;
		}
		addErrorXML(pError, label + " no es un Caracter v�lido");
		return false;
	}

	public static boolean validarLongitudHasta(String valor, int longitud,
			String label, StringBuffer pError) {
		if ((valor == null) || valor.length() <= longitud) {
			return true;
		}
		addErrorXML(pError, label + " no es v�lido, se permite hasta "
				+ longitud + " posiciones");
		return false;
	}

	/**
	 * true si es null , string vacío o letras, si el valor longitudMaxima es
	 * 0, no toma cuenta el largo del string.
	 */
	public static boolean validarLetras(String valor, int longitudMaxima,
			String label, StringBuffer pError) {
		if (valor == null || valor.equals("")) {
			return true;
		}
		if ((valor.matches(" *[a-zA-Z]* *"))
				&& (longitudMaxima == 0 || valor.length() <= longitudMaxima)) {
			return true;
		}
		addErrorXML(pError, label
				+ " no es v�lido, solo letras"
				+ (longitudMaxima != 0 ? " hasta " + longitudMaxima
						+ " posiciones" : ""));
		return false;
	}

	public static boolean validarLetras(String valor, String label,
			StringBuffer pError) {
		return validarLetras(valor, 0, label, pError);
	}

}