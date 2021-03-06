package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBLoader {

	public DBLoader() {
	}

	public void loadFromDatabase() {
		Connection con = null;
		Statement stmt_Select = null;
		ResultSet rs = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			/*
			String URL = "jdbc:oracle:thin:@192.168.128.152:1521:ora11g";
			String USER = "d3b07";
			String PASS = "d3b";
			con = DriverManager.getConnection(URL, USER, PASS);
			*/
			con = DriverManager.getConnection("jdbc:oracle:thin:d3b07/d3b!@192.168.128.152:1512:ora11g");
			stmt_Select = con.createStatement();
			rs = stmt_Select.executeQuery("SELECT * FROM Schueler");
			while (rs.next()) {
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.println(rs.getString(3));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt_Select.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
