package ejercicio2.main;

import ejercicio2.dominio.BaseException;
import ejercicio2.dominio.Persona;
import ejercicio2.dominio.PersonaDao;

public class MainSinTelefonos {

	public static void main(String[] args) throws BaseException {
		PersonaDao dao = new PersonaDao("jdbc:mysql://localhost:3306/oop2-proxy-e2");

		Persona p = dao.personaPorId(3);

		System.out.println(p.nombre());

	}

}
