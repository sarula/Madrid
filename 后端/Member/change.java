package customer;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class change
 */
@WebServlet("/change")
public class change extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public change() {
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
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String q = "";
		HttpSession session = request.getSession();
		q = (String) session.getAttribute("id");
		String p1 = request.getParameter("pwd1");
		String p2 = request.getParameter("pwd2");
		String p3 = request.getParameter("pwd3");

		String p = "";
		PrintWriter out = response.getWriter();
		out.println(p1);
		// out.println(userName);

		System.out.print(q);

		try {
			Connection conn = eb.javaweb.DbUtil.getConnection();
			PreparedStatement s = conn
					.prepareStatement("select password from shopuser where userid=?");
			s.setString(1, q);
			s.execute();

			ResultSet rs = s.executeQuery();
			while (rs.next()) {
				p = rs.getString("password");
				if (p.equals(p1) && p2.equals(p3)) {

					PreparedStatement ptmt = conn
							.prepareStatement("update shopuser set password=? where userid=?");

					ptmt.setString(1, p2);
					ptmt.setString(2, q);
					ptmt.execute();
					response.sendRedirect("careers.html");

				} else {
					response.sendRedirect("pwdchange.html");

				}

			}
		} catch (SQLException e) {
			System.out.print("SQLException " + e.getMessage());
		}

	}
}