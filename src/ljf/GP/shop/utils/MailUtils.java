package ljf.GP.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 激活邮件发送工具类
 * 
 * @author ljf
 */
public class MailUtils {
	/**
	 * 
	 * @param to       :收件人
	 * @param code：激活码
	 */
	public static void sendMail(String to, String code) {
		// 获得连接对象
		Properties props = new Properties();
		// stmp服务器身份验证，校验用户名及密码
		// 这里我用的自己的163邮箱发送的
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.host", "smtp.163.com");
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPA() {
				return new PasswordAuthentication("junfan3229@163.com", "ljf19980531");
			}
		});
		// 创建邮件对象
		Message message = new MimeMessage(session);
		try {
			// 设置发件人
			message.setFrom(new InternetAddress("junfan3229@163.com"));
			// 设置收件人:
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			message.setSubject("来自金珏果蔬商城官方激活邮件");
			// 设置邮件正文:
			message.setContent(
					"<h1>金珏果蔬商城官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://localhost:8080/shop/user_active.action?code="
							+ code + "'>http://localhost:8080/shop/user_active.action?code=" + code + "</a></h3>",
					"text/html;charset=UTF-8");
			// 3.发送邮件:
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
