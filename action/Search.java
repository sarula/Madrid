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
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search() {
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
		String keyword = request.getParameter("keyword");
		String psfs = request.getParameter("psfs");
		String jiage = request.getParameter("jgqj");

		// Integer jgqj=Integer.parseInt((String) jiage.subSequence(0, 1));

		// System.out.print(jiage);
		// System.out.print(jiage);

		String bookname = request.getParameter("bookname");
		String bookid = request.getParameter("bookid");

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

			String sql = "select * from booklist where 1=1";

			if (keyword != null && keyword != "") {
				sql += " and grade='" + keyword + "' ";
			}

			if (jiage != null && jiage != "") {

				if (jiage.length() <= 4) {
					Integer jgqj = Integer.parseInt((String) jiage.subSequence(
							0, 1));
					sql += " and price<= '" + jgqj + "'";

				} else {
					Integer jgqj = Integer.parseInt((String) jiage.subSequence(
							0, 2));
					sql += " and price<= '" + jgqj + "'";

				}

			}

			if (psfs != null && psfs != "") {
				sql += " and peisong='" + psfs + "' ";
			}

			if (bookid != null && bookid != "") {
				sql += " and bookid =" + bookid + "";
			}

			if (bookname != null && bookname != "") {
				sql += " and bookname like'%" + bookname + "%' or isbn like'%"
						+ bookname + "%'";
			}

			PreparedStatement stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			response.setContentType("text/x-json");

			PrintWriter pw = response.getWriter();

			String j = "{\"rows\": [";

			while (rs.next()) {

				j += "{";
				j += "\"bookid\": \"" + rs.getString("bookid") + "\",";
				j += "\"userid\": \"" + rs.getString("userid") + "\",";
				j += "\"store\": \"" + rs.getString("store") + "\",";
				j += "\"price\": \"" + rs.getInt("price") + "\",";
				j += "\"press\": \"" + rs.getString("press") + "\",";
				j += "\"grade\": \"" + rs.getString("grade") + "\",";
				j += "\"peisong\": \"" + rs.getString("peisong") + "\",";
				j += "\"isbn\": \"" + rs.getString("isbn") + "\",";
				j += "\"picture\": \"" + rs.getString("bookpicture") + "\",";
				j += "\"information\": \"" + rs.getString("information")
						+ "\",";
				j += "\"major\": \"" + rs.getString("major") + "\",";

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

			/*
			 * pw.print("<table>");
			 * 
			 * while(rs.next()){ pw.print("<tr>"); pw.print("<td>" +
			 * rs.getInt("RowId") + "</td>"); pw.print("<td>" +
			 * rs.getString("Name") + "</td>"); pw.print("</tr>"); }
			 * 
			 * pw.print("</table>");
			 */

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
