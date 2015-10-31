package customer;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class useredit
 */
@MultipartConfig()
@WebServlet("/useredit")
public class useredit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public useredit() {
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
		// String p=null;
		String q = "";
		HttpSession session = request.getSession();
		q = (String) session.getAttribute("id");
		System.out.println(q);
		Part part = request.getPart("file");
		String filename = getFilename(part);
		part.write(this.getServletContext().getRealPath("/") + "images\\"
				+ filename);
		request.setAttribute(filename, filename);

		request.getAttribute(filename);
		System.out.println(filename);
		String Nickname = request.getParameter("nickname");
		String Realname = request.getParameter("realname");
		String Address = request.getParameter("address");
		String Postcode = request.getParameter("postcode");
		String Tel = request.getParameter("tel");

		System.out.println(Tel);
		try {
			Connection conn = eb.javaweb.DbUtil.getConnection();
			PreparedStatement ptmt = conn
					.prepareStatement("update shopuser set username=?,realname=?,tel=?,address=?,userimg=?,postcode=? where userid=?");

			ptmt.setString(1, Nickname);
			ptmt.setString(2, Realname);
			ptmt.setString(3, Tel);
			ptmt.setString(4, Address);
			ptmt.setString(5, filename);
			ptmt.setString(6, Postcode);
			ptmt.setString(7, q);
			ptmt.execute();
			ptmt.close();
			conn.close();

		}

		catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		System.out.print(filename);
		response.sendRedirect("careers.html");
	}

	private String getFilename(Part part) {
		String header = part.getHeader("Content-Disposition");
		String filename = header.substring(header.indexOf("\"") + 1,
				header.length());
		filename = filename.substring(filename.indexOf("\"") + 1,
				filename.length());
		filename = filename.substring(filename.indexOf("\"") + 1,
				filename.length());
		filename = filename.substring(filename.lastIndexOf("\\")+1);
		filename = filename.substring(0, filename.indexOf("\""));
		return filename;
	}

}
