package customer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
 * Servlet implementation class addbook
 */
@MultipartConfig
@WebServlet("/addbook")
public class addbook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addbook() {
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
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		

		String type = "";   
		String[] type1 = request.getParameterValues("grade");   
		String q = "";
		HttpSession session = request.getSession();
		q = (String) session.getAttribute("id");
		System.out.println(q);
		
		String path = getPath("/images/");
		Part part = request.getPart("bookpicture");
		String filename = getFilename(part);
		writeTo(path, filename, part);
		System.out.println(filename);
		
		
		String Bookname = request.getParameter("bookname");
		String Store = request.getParameter("store");
		String Price = request.getParameter("price");
		String Major = request.getParameter("s_county");

		String Isbn = request.getParameter("isbn");
		String Press = request.getParameter("press");
	
		
		String Peisong = request.getParameter("peisong");
		String Infor = request.getParameter("information");
		System.out.println(Major);
		for (int i = 0; i < type1.length; i++) {   
			   type += type1[i] + ",";   
		} 
		System.out.println(type);
			 
		try {
			Connection conn = eb.javaweb.DbUtil.getConnection();
			PreparedStatement ptmt = conn
					.prepareStatement("insert into booklist values(?,?,?,?,?,?,?,?,?,?,?)");
			ptmt.setString(1, Bookname);
			ptmt.setString(2, Store);
			ptmt.setString(3, Price);
			ptmt.setString(4, filename);
			ptmt.setString(5, Press);
			ptmt.setString(6, q);
			ptmt.setString(7, Infor);
			ptmt.setString(8, type);
			ptmt.setString(9, Major);
			ptmt.setString(10, Peisong);
			ptmt.setString(11, Isbn);
			ptmt.execute();

		}

		catch (SQLException e) {
		}
		response.sendRedirect("mybooklist.html");
		
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

	private void writeTo(String path, String filename, Part part)
			throws IOException, FileNotFoundException {
		InputStream in = part.getInputStream();
		OutputStream out = new FileOutputStream(path + filename);
		byte[] buffer = new byte[1024];
		int length = -1;
		while ((length = in.read(buffer)) != -1) {
			out.write(buffer, 0, length);
		}
		in.close();
		out.close();
	}
	
	public String getPath(String p){
		return this.getServletContext().getRealPath(p);
	}
	
	
}
