package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import bll.Fuﬂballer;
import bll.Fuﬂballer.VEREINE;
import util.DateHelper;

public class DatabaseConnecter {

	private static DatabaseConnecter instance;
	private Connection con;
	private static String connectionLocal;
	private static String connectionPublic;

	public static DatabaseConnecter getInstance() {
		if (instance == null) {
			instance = new DatabaseConnecter();
			connectionLocal = readConnectionString(true);
			connectionPublic = readConnectionString(false);
		}
		return instance;
	}

	public void connect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			DriverManager.setLoginTimeout(5);
			try {
				System.out.println("Versuche mit oeffentlicher IP auf DB zu verbinden...");
				con = DriverManager.getConnection(connectionPublic);
			} catch (SQLException e) {
				System.out.println("Versuche mit localer IP auf DB zu verbinden...");
				con = DriverManager.getConnection(connectionLocal);
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void writeFootballer(ArrayList<Fuﬂballer> fl) {
		DateHelper dh = DateHelper.getInstance();
		fl.forEach(f -> {
			try {
				PreparedStatement ps = con.prepareStatement("INSERT INTO footballer VALUES (?,?,?,?,?)");
				ps.setString(1, f.getVorname());
				ps.setString(2, f.getNachname());
				ps.setString(3, f.getVerein().toString());
				ps.setBoolean(4, f.isGesperrt());
				ps.setDate(5, dh.dateToSql(f.getEintrittsdatum()));
				ps.execute();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public ArrayList<Fuﬂballer> readFootballer() {
		ArrayList<Fuﬂballer> fl = new ArrayList<Fuﬂballer>();
		DateHelper dh = DateHelper.getInstance();
		ResultSet rs = null;
		Statement selectS = null;
		Fuﬂballer newF = null;
		String vorname = null;
		String nachname = null;
		VEREINE verein = null;
		boolean gesperrt = false;
		Date eintrittsdatum = null;
		try {
			selectS = con.createStatement();
			rs = selectS.executeQuery("SELECT * FROM footballer");
			while (rs.next()) {
				vorname = rs.getString(1);
				nachname = rs.getString(2);
				verein = VEREINE.valueOf(rs.getString(3));
				gesperrt = rs.getBoolean(4);
				eintrittsdatum = dh.dateToUtil(rs.getDate(5));
				newF = new Fuﬂballer(vorname, nachname, verein, gesperrt, eintrittsdatum);
				fl.add(newF);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return fl;
	}

	private static String readConnectionString(boolean isLocalConnection) {
		PropertyManager pm = PropertyManager.getInstance();
		return isLocalConnection ? pm.readProperty("connectionStringLocal") : pm.readProperty("connectionStringPublic");
	}
}
