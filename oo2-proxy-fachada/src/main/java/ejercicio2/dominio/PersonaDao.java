package ejercicio2.dominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PersonaDao {

	private String str;

	public PersonaDao(String str) {
		Objects.requireNonNull(str);
		this.str = str;
	}

	private Connection obtenerConexion() throws BaseException {
		Conn connStr = new Conn(this.str);
		return connStr.open();
	}

	public Persona personaPorId(int id) throws BaseException {

		String sql = "select p.nombre,t.numero " + "from personas p, telefonos t "
				+ "where p.id = t.idpersona and p.id = ?";

		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, id);

			ResultSet result = statement.executeQuery();
			Set<Telefono> telefonos = new HashSet<Telefono>();
			String nombrePersona = null;

			while (result.next()) {
				nombrePersona = result.getString(1);
				telefonos.add(new Telefono(result.getString(2)));
			}

			return new Persona(id, nombrePersona, new ProxyInformacionTelefonos(id, new TelefonoDao(str)));

		} catch (SQLException e) {
			throw new BaseException(e, "Error en la consulta a la base.");
		}
	}

}
