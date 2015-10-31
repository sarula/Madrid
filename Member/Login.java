package customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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
		String auto = request.getParameter("check");
		String pw = request.getParameter("password");

		System.out.println("autoֵΪ" + auto);
		
		
		
		String p = "";
		String id = "";
		try {
			Connection conn = eb.javaweb.DbUtil.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("select * from shopuser where username=? ");
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				p = rs.getString("password");
				id = rs.getString("userid");

			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {

		}
		System.out.println("passwordֵΪ" + p);
		System.out.println("id:" + id);
		if (pw.equals(p)) {
			// login successfully
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			session.setAttribute("userid", id);
			session.setAttribute("login", "logined");
			session.setAttribute("username", userName);
			if (auto != null && auto=="on") {
				Cookie cookie = new Cookie("username", userName);
				// cookie.setMaxAge(7*24*60*60);
				cookie.setMaxAge(60 * 60);
				System.out.println("cookie创建成功");
				response.addCookie(cookie);
			}else{
				
				Cookie cookie = new Cookie("username", null);
				cookie.setMaxAge(0);
				
			}
			
			// ת���¼��ĵ�һ��ҳ��
			response.sendRedirect("index.html");
		} else {
			System.out.print("验证失败");
			response.sendRedirect("login.html");

		}

	}

}
