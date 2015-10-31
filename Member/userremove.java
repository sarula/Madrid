package customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class userremove
 */
@WebServlet("/userremove")
public class userremove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public userremove() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");

		String q = "";
		HttpSession session = request.getSession();
		q = (String) session.getAttribute("id");
		System.out.println(q);
		String Email = request.getParameter("email");
		String Tel = request.getParameter("tel");
		String Password = request.getParameter("password");
		try {
			Connection conn = eb.javaweb.DbUtil.getConnection();
			PreparedStatement s = conn
					.prepareStatement("select * from shopuser where userid=? and password=? and email=? and  tel=? ");
			s.setString(1, q);
			s.setString(2, Password);
			s.setString(3, Email);
			s.setString(4, Tel);
			ResultSet rs = s.executeQuery();
			if (rs.next()) {

				try {
					PreparedStatement ptmt = conn
							.prepareStatement("delete shopuser where userid=?");
					ptmt.setString(1, q);
					ptmt.execute();
					
					ptmt.close();
				} catch (SQLException ee) {
					System.out.println(ee.getMessage());
				}
			}
			s.close();
			conn.close();
		} catch (SQLException e) {
		}
		response.sendRedirect("index.html");
	}

}
