package customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Mima
 */
@WebServlet("/Mima")
public class Mima extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Mima() {
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

		Properties prop = new Properties();
		prop.setProperty("mail.smtp.host", "smtp.126.com");
		prop.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.smtp.socketFactory.fallback", "false");
		prop.setProperty("mail.smtp.port", "25");
		prop.setProperty("mail.smtp.socketFactory.port", "25");
		prop.setProperty("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("zpf12345678910@126.com",
						"zpf123456789");

			}
		});

		String to = request.getParameter("email");
		String userName = request.getParameter("username");
		// String TEL=request.getParameter("tel");
		String m = "";
		String p = "";
		System.out.print(to);

		PrintWriter out = response.getWriter();

		try {
			Connection conn = eb.javaweb.DbUtil.getConnection();
			PreparedStatement stmt = conn
					.prepareStatement("select * from shopuser where username=?");
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				p = rs.getString("password");
				m = rs.getString("email");
			}
			if (m.equals(to)) {
				Message message = new MimeMessage(session);
				message.setRecipient(Message.RecipientType.TO,
						new InternetAddress(to));
				message.setFrom(new InternetAddress("zpf12345678910@126.com"));
				message.setSubject("BookPress用户密码找回");
				message.setText("尊敬的用户，您在本网站进行找回密码操作成功，您的密码为：" + p+" 请妥善保管您的用户信息");
				message.setSentDate(new Date());
				Transport.send(message);
				out.println("用户验证通过，密码已发至您的注册邮箱，请注意查收<br />");
				out.println("返回请点击<a href=\"login.html\">这里</a>");
			} else {
				out.println("用户验证失败，返回请点击<a href=\"login.html\">这里</a>");
				out.close();
			}

		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
