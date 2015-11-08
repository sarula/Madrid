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
@WebServlet("/AddOrder")
public class AddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOrder() {
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

		// ��ȡ����Ϣ
		String dilivery = request.getParameter("dilivery");
		System.out.println("dilivery:"+dilivery);
		String pay = request.getParameter("pay");
		String address = request.getParameter("address");
		String tel = request.getParameter("tel");
		String bz = request.getParameter("bz");
		String number=request.getParameter("number");
		Integer bookid=Integer.parseInt(request.getParameter("bookid"));
		
		
		
		

		String id = (String) request.getSession().getAttribute("id");
		System.out.println(id);
		String mySql = "update indent set status='待收货',dilivery='"+dilivery+"',pay='"+pay+"',address='"+address+"',tel='"+tel+"',bz='"+bz+"'  where userid='"+id+"' and status='结算中'";
		PackingDatabase packing = new PackingDatabase();
		String Sql="update cart set status='待收货' where userid='"+id+"' and status='结算中'";
		PackingDatabase packing1 = new PackingDatabase();
		String Sql2 = "update booklist set store=store-"+number+"where bookid="+bookid+"";
		PackingDatabase packing2 = new PackingDatabase();
		try {
			// ִ�в�ѯ����
			packing.update(mySql);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			// ִ�в�ѯ����
			packing2.update(Sql2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		
		try {
			// ִ�в�ѯ����
			packing1.update(Sql);
			System.out.println(mySql);
			System.out.println(Sql);
			response.sendRedirect("orderdetail.html");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
