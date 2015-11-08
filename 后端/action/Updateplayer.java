package myServlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UpdateBook
 */
@MultipartConfig
@WebServlet("/UpdateBook")
public class Updatplayer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateBook() {
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
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String path = this.getServletContext().getRealPath("/images/");
		System.out.println(path);

		String id = (String) request.getSession().getAttribute("id");
		String bookid = request.getParameter("bookid");

		System.out.println(id + bookid);
		String bookname = request.getParameter("bookname");
		String store = request.getParameter("store");
		String price = request.getParameter("price");
		String press = request.getParameter("press");
		String isbn = request.getParameter("isbn");
		String major = request.getParameter("major");
		String grade = request.getParameter("grade");
		String peisong = request.getParameter("peisong");
		String mess = request.getParameter("information");

		Part part = request.getPart("bookpicture");
		String filename = getFilename(part);
		writeTo(path, filename, part);

		String mySql = "update booklist set bookname='" + bookname
				+ "',store='" + store + "',price='" + price + "',bookpicture='"
				+ filename + "',press='" + press + "',information='" + mess
				+ "',grade='" + grade + "',major='" + major + "',peisong='"
				+ peisong + "',isbn='" + isbn + "' where userid=" + id
				+ " and bookid=" + bookid;
		PackingDatabase packing = new PackingDatabase();
		try {
			// 执行查询方法
			packing.update(mySql);
			response.sendRedirect("mybooklist.html");
		} catch (Exception e) {
			System.out.println("数据库更新异常" + e.getMessage());
		}

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

}
