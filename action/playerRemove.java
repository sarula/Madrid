package myServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookRemove
 */
@WebServlet("/BookRemove")
public class BookRemove extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookRemove() {
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
		request.setCharacterEncoding("UTF-8");

		String bookid = request.getParameter("bookid");
		System.out.println("alkhdkla为" + bookid);
		String mySql = "delete booklist where bookid='" + bookid + "'";
		PackingDatabase packing = new PackingDatabase();
		try {
			// 执行查询方法
			packing.update(mySql);
			response.sendRedirect("mybooklist.html");
		} catch (Exception e) {
			System.out.println("数据库更新异常" + e.getMessage());
		}

	}

}
