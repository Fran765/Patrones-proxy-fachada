package ejercicio3.dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FachadaParaBD implements DBFacade {

	private static final String USER = "root";
	private static final String PWD = "";

	private String conn;
	private Connection connection;

	public FachadaParaBD(String conn) {
		Objects.requireNonNull(conn);
		this.conn = conn;
	}

	@Override
	public void open() {
		try {
			String url = this.conn;
			String user = USER;
			String password = PWD;

			connection = DriverManager.getConnection(url, user, password);

		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public List<Map<String, String>> queryResultAsAsociation(String sql) {

		this.open();
		try (PreparedStatement statement = this.connection.prepareStatement(sql);) {

			ResultSet result = statement.executeQuery();

			List<Map<String, String>> miLista = new ArrayList<>();

			ResultSetMetaData metaData = result.getMetaData();
			int CantidadColumnas = metaData.getColumnCount();

			String nombreColumna = null;
			String datoColumna = null;

			while (result.next()) {

				Map<String, String> miMapa = new HashMap<>();

				for (int i = 1; i <= CantidadColumnas; i++) {

					nombreColumna = metaData.getColumnName(i);
					datoColumna = result.getString(i);

					miMapa.put(nombreColumna, datoColumna);
				}

				miLista.add(miMapa);
			}

			return miLista;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<String[]> queryResultAsArray(String sql) {

		this.open();
		try (PreparedStatement statement = this.connection.prepareStatement(sql);) {

			ResultSet result = statement.executeQuery();

			List<String[]> miLista = new ArrayList<String[]>();

			ResultSetMetaData metaData = result.getMetaData();
			int CantidadColumnas = metaData.getColumnCount();

			while (result.next()) {
				String[] lectura = new String[CantidadColumnas + 1];

				for (int i = 1; i <= CantidadColumnas; i++) {
					lectura[i - 1] = result.getString(i);
				}
				lectura[CantidadColumnas] = " / ";
				miLista.add(lectura);
			}

			return miLista;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void close() {

		try {
			this.connection.close();
		} catch (SQLException e) {

			throw new RuntimeException(e);
		}
	}

}
