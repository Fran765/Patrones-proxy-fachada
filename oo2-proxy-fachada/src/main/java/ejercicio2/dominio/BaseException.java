package ejercicio2.dominio;

public class BaseException extends Exception {

	public BaseException(Exception ex, String msg) {
		super(msg, ex);
	}
}
