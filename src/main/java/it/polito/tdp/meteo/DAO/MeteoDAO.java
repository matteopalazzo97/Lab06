package it.polito.tdp.meteo.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.model.Rilevamento;

public class MeteoDAO {
	/*
	public List<Rilevamento> getAllRilevamenti() {

		final String sql = "SELECT Localita, Data, Umidita FROM situazione ORDER BY data ASC";

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getDate("Data"), rs.getInt("Umidita"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	*/
	
	/*
	 * questo porcoddio di metodo dovrebbe, dato un mese, restituire la media di umidità
	 * di ciascuna località.
	 * ma è strutturato male a partire dai parametri che vuole passati, quindi lo modifico
	 */

	public List<Rilevamento> getRilevamentiMediMese(int mese) {
		
		final String sql = "SELECT Localita, AVG(Umidita) AS Media "
				+ "FROM Situazione s "
				+ "WHERE MONTH(data)=? "      //s.`Data` LIKE \"%?___\" " spiegami perchè così non funziona da eclipse
				+ "GROUP BY s.`Localita`";    //ma su sequelpro funziona MANNAGGIA AL PADRE ETERNO

		List<Rilevamento> rilevamenti = new ArrayList<Rilevamento>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, mese);
			ResultSet rs = st.executeQuery();
			

			while (rs.next()) {

				Rilevamento r = new Rilevamento(rs.getString("Localita"), rs.getInt("Media"));
				rilevamenti.add(r);
			}

			conn.close();
			return rilevamenti;

		} catch (SQLException e) {

			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}


}
