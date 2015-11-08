package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class ShowQuery
 */
@WebServlet("/ShowQuery")
public class ShowQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowQuery() {
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
		request.setCharacterEncoding("utf-8");

		String majorid = request.getParameter("majorid");
		String mySql = "select * from booklist,majors where booklist.major=majors.major and majors.majorId='"
				+ majorid + "'";
		PackingDatabase packing = new PackingDatabase();
		try {
			// 执行查询方法
			ResultSet rs = packing.query(mySql);
			response.setContentType("text/x-json;charset=utf-8");
			// response.setContentType("application/json");
			// response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			JSONArray ja = new JSONArray();
			while (rs.next()) {
				JSONObject jo = new JSONObject();
				jo.put("bookname", rs.getString("bookname"));
				jo.put("press", rs.getString("press"));
				jo.put("price", rs.getString("price"));
				jo.put("major", rs.getString("major"));
				jo.put("bookpicture", rs.getString("bookpicture"));
				jo.put("bookid", rs.getString("bookid"));
				ja.put(jo);
			}
			rs.close();
			out.print(ja.toString());
			out.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
