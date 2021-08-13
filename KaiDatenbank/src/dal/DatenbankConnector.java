package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bll.User;

public class DatenbankConnector {

	private static DatenbankConnector instanz;

	private DatenbankConnector() {
	}

	public static DatenbankConnector getInstanz() {
		if (instanz == null) {
			instanz = new DatenbankConnector();
		}
		return instanz;
	}

	public void sendDaten(ArrayList<User> users) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("");
			for (User u : users) {
				stmt = con.prepareStatement("INSERT INTO tabelle VALUES(?, ?, ?)");
				stmt.setInt(1, u.getId());
				stmt.setString(2, u.getName());
				stmt.setInt(3, u.getAlter());
				stmt.execute();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<User> readDaten() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<User> userlist = new ArrayList<User>();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("");
			stmt = con.createStatement();
			rs = stmt.executeQuery("SELECT * FROM tabelle");
			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2), rs.getInt(3));
				userlist.add(user);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userlist;
	}

	public void changeName(int id, String name) {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("");
			stmt = con.prepareStatement("UPDATE tabelle SET name = '?' WHERE id = '?'");
			stmt.setString(1, name);
			stmt.setInt(2, id);
			stmt.execute();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
