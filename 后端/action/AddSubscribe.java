package myServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddSubscribe
 */
@WebServlet("/AddSubscribe")
public class AddSubscribe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddSubscribe() {
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

		// 获取表单信息
		String bookname = request.getParameter("bookname");
		String press = request.getParameter("press");
		String amount = request.getParameter("amount");
		String tel = request.getParameter("tel");
		String mess = request.getParameter("message");

		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date now = new java.util.Date();
		String str_date1 = formatter.format(now);
		String Ptime = str_date1.substring(0, 10);

		String id = (String) request.getSession().getAttribute("id");
		System.out.println(id);

		// 将信息写入数据库
		String mySql = "insert into askedbooklist (abookname,press,abooknumber,time,tel,userid,username,mess) values('"
				+ bookname
				+ "','"
				+ press
				+ "','"
				+ amount
				+ "','"
				+ Ptime
				+ "','"
				+ tel
				+ "','"
				+ id
				+ "',(select username from shopuser where userid='"
				+ id
				+ "'),'" + mess + "')";
		PackingDatabase packing = new PackingDatabase();
		try {
			// 执行查询方法
			packing.update(mySql);
			response.sendRedirect("subscribe.html");
		} catch (Exception e) {
			System.out.println("数据库插入异常:" + e.getMessage());
		}

	}

}
