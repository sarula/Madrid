package myServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import myJavaBean.Cart;



import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class CartQuery
 */
@WebServlet("/IndentQuerya")
public class IndentQuerya extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndentQuerya() {
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

		String mySql = "select price*number as sumprice,indentId,bookname,price,number,status,dilivery,pay from indent,booklist where indent.status='已完成' and indent.bookid=booklist.bookid and indent.userid='"
				+ id + "'";
		PackingDatabase packing = new PackingDatabase();
		try {
			// ִ�в�ѯ����
			ResultSet rs = packing.query(mySql);
			//response.setContentType("text/x-json;charset=utf-8");
			response.setContentType("application/json");  
		    response.setCharacterEncoding("UTF-8");  
			
			PrintWriter out = response.getWriter();
			//List<Cart> cart = new ArrayList<Cart>();
			JSONArray ja = new JSONArray();
			while (rs.next()) {
				JSONObject jo = new JSONObject();
				jo.put("indentId", rs.getString("indentId"));
				jo.put("userid", id);
				jo.put("bookname", rs.getString("bookname"));
				jo.put("price", rs.getString("price"));
				jo.put("number", rs.getString("number"));
				jo.put("status", rs.getString("status"));
				jo.put("dilivery", rs.getString("dilivery"));
				jo.put("pay", rs.getString("pay"));
				jo.put("sumprice", rs.getString("sumprice"));
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
		doGet(request,response);
	}

}
