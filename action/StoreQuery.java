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
 * Servlet implementation class StoreQuery
 */
@WebServlet("/StoreQuery")
public class StoreQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StoreQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		String bookid=request.getParameter("bookid");

		String mySql = "select store from booklist where bookid='" + bookid + "'";
		PackingDatabase packing = new PackingDatabase();
		try {
			// ִ�в�ѯ����
			ResultSet rs = packing.query(mySql);
			// response.setContentType("text/x-json;charset=utf-8");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			PrintWriter out = response.getWriter();
			// List<Cart> cart = new ArrayList<Cart>();
			JSONObject jo = new JSONObject();
			while (rs.next()) {
				jo.put("bookid", bookid);
				jo.put("store", rs.getString("store"));
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
