package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class UserQuery
 */
@WebServlet("/UserQuery")
public class UserQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserQuery() {
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

		String id = (String) request.getSession().getAttribute("id");

		String mySql = "select * from shopuser where userid='" + id + "'";
		PackingDatabase packing = new PackingDatabase();
		try {
			// 执行查询方法
			ResultSet rs = packing.query(mySql);
			// response.setContentType("text/x-json;charset=utf-8");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			// List<Cart> cart = new ArrayList<Cart>();
			JSONObject jo = new JSONObject();
			while (rs.next()) {
				jo.put("userid", id);
				jo.put("username", rs.getString("username"));
				jo.put("email", rs.getString("email"));
				jo.put("tel", rs.getString("tel"));
				jo.put("address", rs.getString("address"));
				jo.put("postcode", rs.getString("postcode"));
				jo.put("realname", rs.getString("realname"));
				jo.put("userimg", rs.getString("userimg"));
				break;
			}
			rs.close();
			out.print(jo.toString());
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
