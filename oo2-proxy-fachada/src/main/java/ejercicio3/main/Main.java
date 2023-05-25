package ejercicio3.main;

import java.util.List;

import ejercicio3.dominio.DBFacade;
import ejercicio3.dominio.FachadaParaBD;

public class Main {

	public static void main(String[] args) {

		DBFacade miFachada = new FachadaParaBD("jdbc:mysql://localhost:3306/oop2-proxy-e2");

		System.out.println(miFachada.queryResultAsAsociation("SELECT * FROM personas"));

		List<String[]> miLista = miFachada.queryResultAsArray("SELECT * FROM personas");

		recorrer(miLista);
		System.out.println();

		System.out.println(miFachada.queryResultAsAsociation("SELECT * FROM telefonos"));

		List<String[]> miLista1 = miFachada.queryResultAsArray("SELECT * FROM telefonos");

		recorrer(miLista1);

	}

	private static void recorrer(List<String[]> unaLista) {

		for (String[] dato : unaLista) {
			for (String valor : dato) {
				System.out.print(valor);
			}
		}

	}

}
