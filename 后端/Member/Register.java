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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
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
		String userName = request.getParameter("username");
		String pw = request.getParameter("password");
		String pw1 = request.getParameter("password2");
		String email = request.getParameter("email");
		String p = "";
		String q = "";

		try {
			Connection conn = eb.javaweb.DbUtil.getConnection();
			PreparedStatement s = conn
					.prepareStatement("select * from shopuser where username=? ");
			s.setString(1, userName);
			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				p = rs.getString("username");
			}

			if (p.isEmpty() && pw.equals(pw1)) {

				PreparedStatement ptmt = conn
						.prepareStatement("insert into shopuser(username,password,email) values(?,?,?)");
				ptmt.setString(1, userName);
				ptmt.setString(2, pw);
				ptmt.setString(3, email);
				ptmt.execute();
				HttpSession sission = request.getSession();
				sission.setAttribute("name", userName);
				HttpSession session2 = request.getSession();
				q = (String) session2.getAttribute("name");
				System.out.println(q);
				response.sendRedirect("usercomplete.html");

			} else {
				response.sendRedirect("register.html");

			}

		} catch (SQLException e) {
		}
	}

}
