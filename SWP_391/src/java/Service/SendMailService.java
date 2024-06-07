/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import model.Common.Order;

/**
 *
 * @author ifyou
 */
public class SendMailService {

    public void sendVerifyAccount(String toEmail, String verifyCode) {
        //provide recipient's email ID
        String to = toEmail;
        //provide sender's email
        String from = "OnlineShoesStore@example.com";
        //provide Mailtrap's username
        final String username = "e63e649587e337";
        //provide Mailtrap's password
        final String password = "340e5584d2008f";
        //provide Mailtrap's host address
        String host = "smtp.mailtrap.io";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set email subject field
            message.setSubject("Kich Hoat Tai Khoan!");
            //set the content of the email message
//            message.setText("Just discovered that Jakarta Mail is fun and easy to use");
            message.setContent("<div\n"
                    + "      style=\"\n"
                    + "      width: 50vw;\n"
                    + "      margin: 0 auto;\n"
                    + "      padding: 20px 100px;\n"
                    + "      text-align: center; \n"
                    + "      border: 3px solid orange; \n"
                    + "      border-radius: 10px\n"
                    + "      \"\n"
                    + "    >\n"
                    + "    <h1>Quý khách hàng thân mến!</h1>\n"
                    + "      <h3>Cam on ban vi da tro thanh khach hang cua Online Shoes Store</h3>\n"
                    + "      <small\n"
                    + "        >Cam on ban vi da tao tai khoan trong he thong. Xin hay click vao button ben duoi de kich hoat tai khoan:</small>\n"
                    + "      <small\n"
                    + "        >Neu ban khong tao tai khoan, xin hay bo qua email nay.</small\n"
                    + "      >\n"
                    + "      <button\n"
                    + "        style=\"\n"
                    + "          padding: 10px;\n"
                    + "          border-radius: 10px;\n"
                    + "          border-color: rgb(255,69,0);\n"
                    + "          display: block;\n"
                    + "          margin: 20px auto;\n"
                    + "        \"\n"
                    + "      >\n"
                    + "        <a style=\"text-decoration: none\" href=\"http://localhost:8080/SWP_391/verify?token=" + verifyCode + "\">Kick hoat tai khoan</a>\n"
                    + "      </button>\n"
                    + "      <h5>Neu ban co cau hoi? <a href=\"\">Chung toi o day de giup ban</a></h5>\n"
                    + "    </div>",
                    "text/html");
            //send the email message
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendResetPassword(String toEmail, String resetPasswordToken) {
        //provide recipient's email ID
        String to = toEmail;
        //provide sender's email
        String from = "OnlineShoesStore@example.com";
        //provide Mailtrap's username
        final String username = "e63e649587e337";
        //provide Mailtrap's password
        final String password = "340e5584d2008f";
        //provide Mailtrap's host address
        String host = "smtp.mailtrap.io";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set email subject field
            message.setSubject("Reset mat khau!");
            //set the content of the email message
//            message.setText("Just discovered that Jakarta Mail is fun and easy to use");
            message.setContent("<div\n"
                    + "      style=\"\n"
                    + "      width: 50vw;\n"
                    + "      margin: 0 auto;\n"
                    + "      padding: 20px 100px;\n"
                    + "      text-align: center; \n"
                    + "      border: 3px solid orange; \n"
                    + "      border-radius: 10px\n"
                    + "      \"\n"
                    + "    >\n"
                    + "    <h1>Quy khach hang than men!</h1>\n"
                    + "      <h3>Mat khau cua ban da duoc reset thanh cong!</h3>\n"
                    + "      <small\n"
                    + "        >Xin hay click vao button ben duoi de dat lai mat khau:</small>\n"
                    + "      <small\n"
                    + "        >Neu ban khong reset mat khau, ban co the bo qua email nay.</small\n"
                    + "      >\n"
                    + "      <button\n"
                    + "        style=\"\n"
                    + "          padding: 10px;\n"
                    + "          border-radius: 10px;\n"
                    + "          border-color: rgb(255,69,0);\n"
                    + "          display: block;\n"
                    + "          margin: 20px auto;\n"
                    + "        \"\n"
                    + "      >\n"
                    + "        <a style=\"text-decoration: none\" href=\"http://localhost:8080/SWP_391/SetNewPassword?token=" + resetPasswordToken + "\">Dat lai mat khau</a>\n"
                    + "      </button>\n"
                    + "      <h5>Neu ban co cau hoi?  <a href=\"\">Chung toi o day de giup do ban</a></h5>\n"
                    + "    </div>",
                    "text/html");
            //send the email message
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendNewAccount(String toEmail, String newPassword) {
        //provide recipient's email ID
        String to = toEmail;
        //provide sender's email
        String from = "OnlineShoesStore@example.com";
        //provide Mailtrap's username
        final String username = "e63e649587e337";
        //provide Mailtrap's password
        final String password = "340e5584d2008f";
        //provide Mailtrap's host address
        String host = "smtp.mailtrap.io";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set email subject field
            message.setSubject("Kich hoat tai khoan!");
            //set the content of the email message
//            message.setText("Just discovered that Jakarta Mail is fun and easy to use");
            message.setContent("<div\n"
                    + "      style=\"\n"
                    + "      width: 50vw;\n"
                    + "      margin: 0 auto;\n"
                    + "      padding: 20px 100px;\n"
                    + "      text-align: center; \n"
                    + "      border: 3px solid orange; \n"
                    + "      border-radius: 10px\n"
                    + "      \"\n"
                    + "    >\n"
                    + "    <h1>Cam on ban vi da tro thanh nhan vien cua Online Shoes Store!</h1>\n"
                    + "      <h3>Quan tri vien da tao tai khoan cho ban thanh cong!</h3>\n"
                    + "      <h3>Mat khau cua ban la: " + newPassword + "</h3>\n"
                    + "      <small\n"
                    + "        >Vui long click vao button ben duoi de tien hanh dang nhap</small>\n"
                    + "      <small\n"
                    + "      <button\n"
                    + "        style=\"\n"
                    + "          padding: 10px;\n"
                    + "          border-radius: 10px;\n"
                    + "          border-color: rgb(255,69,0);\n"
                    + "          display: block;\n"
                    + "          margin: 20px auto;\n"
                    + "        \"\n"
                    + "      >\n"
                    + "        <a style=\"text-decoration: none\" href=\"http://localhost:8080/SWP_391/login" + "\">Dang nhap</a>\n"
                    + "      </button>\n"
                    + "      <h5>Neu ban co cau hoi?  <a href=\"\">Chung toi o day de giup do</a></h5>\n"
                    + "    </div>",
                    "text/html");
            //send the email message
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendOrderNoti1(String toEmail, Order order) {
        //provide recipient's email ID
        String to = toEmail;
        //provide sender's email
        String from = "OnlineShoesStore@example.com";
        //provide Mailtrap's username
        final String username = "e63e649587e337";
        //provide Mailtrap's password
        final String password = "340e5584d2008f";
        //provide Mailtrap's host address
        String host = "smtp.mailtrap.io";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set email subject field
            message.setSubject("Thong bao don hang!");
            //set the content of the email message
//            message.setText("Just discovered that Jakarta Mail is fun and easy to use");
            message.setContent("<div\n"
                    + "      style=\"\n"
                    + "      width: 50vw;\n"
                    + "      margin: 0 auto;\n"
                    + "      padding: 20px 100px;\n"
                    + "      text-align: center; \n"
                    + "      border: 3px solid orange; \n"
                    + "      border-radius: 10px\n"
                    + "      \"\n"
                    + "    >\n"
                    + "    <h1>Cam on ban vi da dat hang tai Online Shoes Store!</h1>\n"
                    + "      <h3>Don hang cua ban da duoc dat hang thanh cong va dang cho dong goi!</h3>\n"
                    + "      <h3>Ma don hang cua ban la: " + order.getOrderCode() + "</h3>\n"
                    + "      <small\n"
                    + "        >Vui long click vao button ben duoi de kiem tra thong tin don hang</small>\n"
                    + "      <small\n"
                    + "      <button\n"
                    + "        style=\"\n"
                    + "          padding: 10px;\n"
                    + "          border-radius: 10px;\n"
                    + "          border-color: rgb(255,69,0);\n"
                    + "          display: block;\n"
                    + "          margin: 20px auto;\n"
                    + "        \"\n"
                    + "      >\n"
                    + "        <a style=\"text-decoration: none\" href=\"http://localhost:8080/SWP_391/SearchOrder.jsp" + "\">Kiem tra</a>\n"
                    + "      </button>\n"
                    + "      <h5>Neu ban co cau hoi?  <a href=\"\">Chung toi o day de giup do ban</a></h5>\n"
                    + "    </div>",
                    "text/html");
            //send the email message
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendOrderNoti2(String toEmail, Order order) {
        //provide recipient's email ID
        String to = toEmail;
        //provide sender's email
        String from = "OnlineShoesStore@example.com";
        //provide Mailtrap's username
        final String username = "e63e649587e337";
        //provide Mailtrap's password
        final String password = "340e5584d2008f";
        //provide Mailtrap's host address
        String host = "smtp.mailtrap.io";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set email subject field
            message.setSubject("Thong bao don hang!");
            //set the content of the email message
//            message.setText("Just discovered that Jakarta Mail is fun and easy to use");
            message.setContent("<div\n"
                    + "      style=\"\n"
                    + "      width: 50vw;\n"
                    + "      margin: 0 auto;\n"
                    + "      padding: 20px 100px;\n"
                    + "      text-align: center; \n"
                    + "      border: 3px solid orange; \n"
                    + "      border-radius: 10px\n"
                    + "      \"\n"
                    + "    >\n"
                    + "    <h1>Cam on ban da dat hang tai Online Shoes Store!</h1>\n"
                    + "      <h3>Don hang cua ban da duoc dong goi xong va dang cho giao cho don vi van chuyen!</h3>\n"
                    + "      <h3>Ma don hang cua ban la: " + order.getOrderCode() + "</h3>\n"
                    + "      <small\n"
                    + "        >Vui long click vao button ben duoi de kiem tra thong tin don hang</small>\n"
                    + "      <small\n"
                    + "      <button\n"
                    + "        style=\"\n"
                    + "          padding: 10px;\n"
                    + "          border-radius: 10px;\n"
                    + "          border-color: rgb(255,69,0);\n"
                    + "          display: block;\n"
                    + "          margin: 20px auto;\n"
                    + "        \"\n"
                    + "      >\n"
                    + "        <a style=\"text-decoration: none\" href=\"http://localhost:8080/SWP_391/SearchOrder.jsp" + "\">Kiem tra</a>\n"
                    + "      </button>\n"
                    + "      <h5>Neu ban co cau hoi?  <a href=\"\">Chung toi o day de giup ban</a></h5>\n"
                    + "    </div>",
                    "text/html");
            //send the email message
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendOrderNoti3(String toEmail, Order order) {
        //provide recipient's email ID
        String to = toEmail;
        //provide sender's email
        String from = "OnlineShoesStore@example.com";
        //provide Mailtrap's username
        final String username = "e63e649587e337";
        //provide Mailtrap's password
        final String password = "340e5584d2008f";
        //provide Mailtrap's host address
        String host = "smtp.mailtrap.io";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set email subject field
            message.setSubject("Thong bao don hang!");
            //set the content of the email message
//            message.setText("Just discovered that Jakarta Mail is fun and easy to use");
            message.setContent("<div\n"
                    + "      style=\"\n"
                    + "      width: 50vw;\n"
                    + "      margin: 0 auto;\n"
                    + "      padding: 20px 100px;\n"
                    + "      text-align: center; \n"
                    + "      border: 3px solid orange; \n"
                    + "      border-radius: 10px\n"
                    + "      \"\n"
                    + "    >\n"
                    + "    <h1>Cam on ban vi da dat hang tai Online Shoes Store!</h1>\n"
                    + "      <h3>Don vi van chuyen da nhan don hang cua ban va dang tien hanh van chuyen!</h3>\n"
                    + "      <h3>Ngay giao hang du kien la: " + order.getDeliveryDate() + "</h3>\n"
                    + "      <h3>Ma don hang cua ban la: " + order.getOrderCode() + "</h3>\n"
                    + "      <small\n"
                    + "        >Vui long click vao button ben duoi de kiem tra thong tin don hang</small>\n"
                    + "      <small\n"
                    + "      <button\n"
                    + "        style=\"\n"
                    + "          padding: 10px;\n"
                    + "          border-radius: 10px;\n"
                    + "          border-color: rgb(255,69,0);\n"
                    + "          display: block;\n"
                    + "          margin: 20px auto;\n"
                    + "        \"\n"
                    + "      >\n"
                    + "        <a style=\"text-decoration: none\" href=\"http://localhost:8080/SWP_391/SearchOrder.jsp" + "\">Kiểm tra</a>\n"
                    + "      </button>\n"
                    + "      <h5>Neu ban co cau hoi?  <a href=\"\">Chung toi o day de giup do ban</a></h5>\n"
                    + "    </div>",
                    "text/html");
            //send the email message
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void sendOrderNoti4(String toEmail, Order order) {
        //provide recipient's email ID
        String to = toEmail;
        //provide sender's email
        String from = "OnlineShoesStore@example.com";
        //provide Mailtrap's username
        final String username = "e63e649587e337";
        //provide Mailtrap's password
        final String password = "340e5584d2008f";
        //provide Mailtrap's host address
        String host = "smtp.mailtrap.io";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set email subject field
            message.setSubject("Thong bao don hang!");
            //set the content of the email message
//            message.setText("Just discovered that Jakarta Mail is fun and easy to use");
            message.setContent("<div\n"
                    + "      style=\"\n"
                    + "      width: 50vw;\n"
                    + "      margin: 0 auto;\n"
                    + "      padding: 20px 100px;\n"
                    + "      text-align: center; \n"
                    + "      border: 3px solid orange; \n"
                    + "      border-radius: 10px\n"
                    + "      \"\n"
                    + "    >\n"
                    + "    <h1>Cam on ban da dat hang tai Online Shoes Store!</h1>\n"
                    + "      <h3>Don hang cua ban da duoc giao hang thanh cong!</h3>\n"
                    + "      <h3>Ma don hang cua ban la: " + order.getOrderCode() + "</h3>\n"
                    + "      <small\n"
                    + "        >Vui long click vao button ben duoi de xem thong tin don hang</small>\n"
                    + "      <small\n"
                    + "      <button\n"
                    + "        style=\"\n"
                    + "          padding: 10px;\n"
                    + "          border-radius: 10px;\n"
                    + "          border-color: rgb(255,69,0);\n"
                    + "          display: block;\n"
                    + "          margin: 20px auto;\n"
                    + "        \"\n"
                    + "      >\n"
                    + "        <a style=\"text-decoration: none\" href=\"http://localhost:8080/SWP_391/SearchOrder.jsp" + "\">Kiem tra</a>\n"
                    + "      </button>\n"
                    + "      <h5>Neu ban co cau hoi?  <a href=\"\">Chung toi o day de giup do ban</a></h5>\n"
                    + "    </div>",
                    "text/html");
            //send the email message
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
        public void sendOrderNoti6(String toEmail, Order order) {
        //provide recipient's email ID
        String to = toEmail;
        //provide sender's email
        String from = "OnlineShoesStore@example.com";
        //provide Mailtrap's username
        final String username = "e63e649587e337";
        //provide Mailtrap's password
        final String password = "340e5584d2008f";
        //provide Mailtrap's host address
        String host = "smtp.mailtrap.io";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From email field
            message.setFrom(new InternetAddress(from));
            //set To email field

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set email subject field
            message.setSubject("Thong bao don hang!");
            //set the content of the email message
//            message.setText("Just discovered that Jakarta Mail is fun and easy to use");
            message.setContent("<div\n"
                    + "      style=\"\n"
                    + "      width: 50vw;\n"
                    + "      margin: 0 auto;\n"
                    + "      padding: 20px 100px;\n"
                    + "      text-align: center; \n"
                    + "      border: 3px solid orange; \n"
                    + "      border-radius: 10px\n"
                    + "      \"\n"
                    + "    >\n"
                    + "    <h1>Cam on ban vi da dat hang tai Online Shoes Store!</h1>\n"
                    + "      <h3>Don hang cua ban da duoc huy thanh cong!</h3>\n"
                    + "      <small\n"
                    + "        >Vui long click vao button ben duoi de tien hanh mua sam</small>\n"
                    + "      <small\n"
                    + "      <button\n"
                    + "        style=\"\n"
                    + "          padding: 10px;\n"
                    + "          border-radius: 10px;\n"
                    + "          border-color: rgb(255,69,0);\n"
                    + "          display: block;\n"
                    + "          margin: 20px auto;\n"
                    + "        \"\n"
                    + "      >\n"
                    + "        <a style=\"text-decoration: none\" href=\"http://localhost:8080/SWP_391/" + "\">Trang chu</a>\n"
                    + "      </button>\n"
                    + "      <h5>Neu ban co cau hoi?  <a href=\"\">Chung toi o day de giup do ban</a></h5>\n"
                    + "    </div>",
                    "text/html");
            //send the email message
            Transport.send(message);
            System.out.println("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        SendMailService se = new SendMailService();
        se.sendNewAccount("ifyouwant9612@gmail.com", "hello123");
    }
}
