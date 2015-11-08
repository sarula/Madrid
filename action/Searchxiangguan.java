package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Searchxiangguan
 */
@WebServlet("/Searchxiangguan")
public class Searchxiangguan extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Searchxiangguan() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		// String keyword = request.getParameter("keyword");
		// String psfs = request.getParameter("psfs");
		// String jiage = request.getParameter("jgqj");

		// Integer jgqj=Integer.parseInt((String) jiage.subSequence(0, 1));

		// System.out.print(jiage);
		// System.out.print(jiage);

		String m = request.getParameter("major");
		String major = m.substring(0, m.indexOf("&"));

		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=bookshop";
		String username = "sa";
		String password = "123456";
		Connection conn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			conn = DriverManager.getConnection(url, username, password);

			PreparedStatement stmt = conn
					.prepareStatement("select * from booklist where major=?");

			stmt.setString(1, major);

			ResultSet rs = stmt.executeQuery();

			response.setContentType("text/x-json");

			PrintWriter pw = response.getWriter();

			String j = "{\"rows\": [";

			while (rs.next()) {

				j += "{";
				j += "\"price\": \"" + rs.getInt("price") + "\",";
				j += "\"picture\": \"" + rs.getString("bookpicture") + "\",";
				j += "\"bookname\": \"" + rs.getString("bookname") + "\"";
				j += "}";
				j += ", ";

			}

			if (j != "{\"rows\": [") {
				j = j.substring(0, j.length() - 2);
			}

			j += "]}";
			pw.print(j);

			// { "success": true, "rows": [{"id": 1, "name": "zhang"}, {"id": 2,
			// "name": "wang"},
			// {"id": 3, "name": "li"}] }

			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
