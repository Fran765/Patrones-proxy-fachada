package ejercicio1.main;

import ejercicio1.dominio.Clima;
import ejercicio1.dominio.PorWebObtenerClima;

public class MainSinCache {

	public static void main(String[] args) {

		Clima porWeb = new PorWebObtenerClima("7d5536c9de5773ac0574b17c23335dc7");

		long start = System.currentTimeMillis();
		String estadoClima = porWeb.estadoDelDia();
		long end = System.currentTimeMillis();
		System.out.println((end - start) / 1000f);

		long start2 = System.currentTimeMillis();
		String estadoClima2 = porWeb.estadoDelDia();
		long end2 = System.currentTimeMillis();
		System.out.println((end2 - start2) / 1000f);

		long start3 = System.currentTimeMillis();
		String estadoClima3 = porWeb.estadoDelDia();
		long end3 = System.currentTimeMillis();
		System.out.println((end3 - start3) / 1000f);
		
		System.out.println(estadoClima);
		System.out.println(estadoClima2);
		System.out.println(estadoClima3);
	}

}
