package ApplicationLogic.SystemTesting;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StorageStub {
	public static boolean reinitializeDatabase() {
		try {
			File SQL_file = new File("init_db.sql");
			if (!SQL_file.exists()) throw new FileNotFoundException("Could not find SQL init script at: " + SQL_file.getAbsolutePath());
			
			StringBuilder SQL = new StringBuilder();
			BufferedReader SQL_reader = new BufferedReader(new FileReader(SQL_file));
			String s;
			
			while ((s = SQL_reader.readLine()) != null) {
				SQL.append(s);
			}
			SQL_reader.close();
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schedule_maker?user=root&password=");
			conn.setAutoCommit(false);
			conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			conn.setSavepoint();
			
			try {
				for (String SQL_statement : SQL.toString().split(";")) {
					conn.createStatement().execute(SQL_statement);
				}
				
				conn.commit();
			} catch (SQLException sqlEx) {
				conn.rollback();
				throw sqlEx;
			} finally {
				conn.close();
			}
			
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}