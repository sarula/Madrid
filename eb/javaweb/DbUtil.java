package eb.javaweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

	static String url = "jdbc:sqlserver://localhost:1433; DatabaseName=bookshop";
	static String user = "sa";
	static String password = "123456";
	static Connection conn = null;

	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}