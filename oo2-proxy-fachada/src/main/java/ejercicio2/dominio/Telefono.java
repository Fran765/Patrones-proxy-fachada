package ejercicio2.dominio;

public class Telefono {
	private String numero;

	public Telefono(String numero) {
		this.numero = numero;
	}

	public String numero() {
		return numero;
	}

	@Override
	public String toString() {
		return numero;
	}

}
