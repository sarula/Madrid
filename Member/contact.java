package customer;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class contact
 */
@WebServlet("/contact")
public class contact extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public contact() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Properties prop=new Properties();
		prop.setProperty("mail.smtp.host", "smtp.126.com");
		prop.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.smtp.socketFactory.fallback", "false");
		prop.setProperty("mail.smtp.port","25");
		prop.setProperty("mail.smtp.socketFactory.port", "25");
		prop.setProperty("mail.smtp.auth", "true");
		Session session=Session.getDefaultInstance(prop,new Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication(){
				return new PasswordAuthentication("duanjs1995@126.com","dian128223");
			}
		});

		String to = request.getParameter("email");
		String userName = request.getParameter("username");
		String f = request.getParameter("text");

		

		Message message = new MimeMessage(session);
		try {
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(
					"756592478@qq.com"));
			message.setFrom(new InternetAddress("duanjs1995@126.com"));
			message.setSubject("用户反馈信息");
			message.setText("来自用户名为：\"" + userName + "\"的用户信息:" + f
					+ "邮箱地址为：'" + to + "'");
			message.setSentDate(new Date());
			Transport.send(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		response.sendRedirect("contact.html");
	}

}
