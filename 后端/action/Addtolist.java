package myServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Addtocart
 */
@WebServlet("/Addtocart")
public class Addtoclist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addtocart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");

		// ��ȡ����Ϣ
		String bookid= request.getParameter("bookid");
		String number = request.getParameter("number");
		
		//java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//java.util.Date now = new java.util.Date();
		
		String userid = (String) request.getSession().getAttribute("id");
		System.out.println(userid);

		// ����Ϣд�����ݿ�
		String Sql = "insert into cart (bookid,userid,number,status) values('"+bookid+"','"+userid+"','"+number+"','已添加')";
		PackingDatabase packing = new PackingDatabase();
		try {
			// ִ�в�ѯ����
			packing.update(Sql);
			response.sendRedirect("index.html");
		} catch (Exception e) {
			System.out.println("���ݿ�����쳣:" + e.getMessage());
		}

	}



	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
