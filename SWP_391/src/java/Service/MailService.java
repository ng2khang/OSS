///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package Service;
//
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
///**
// *
// * @author ifyou
// */
//public class MailService {
//
//    public void sendVerifyAccount(String toEmail, String verifyCode) {
//        //provide sender's email ID
//        String fromEmail = "kn97502@gmail.com";
//        String fromPassword = "tzrzytbzbeaxmfnu";
//
//        //provide Mailtrap's host address
//        String host = "smtp.gmail.com";
//        //configure Mailtrap's SMTP server details
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
//
//        //create the Session object
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(fromEmail, fromPassword);
//            }
//        });
//
//        try {
//            //create a MimeMessage object
//            Message message = new MimeMessage(session);
//
//            //set From email field
//            message.setFrom(new InternetAddress(fromEmail));
//
//            //set To email field
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(toEmail));
//
//            //set email subject field
//            message.setSubject("Online Shore Store");
//
//            //set the content of the email message
//            message.setContent("<div\n"
//                    + "      style=\"\n"
//                    + "      width: 50vw;\n"
//                    + "      margin: 0 auto;\n"
//                    + "      padding: 20px 100px;\n"
//                    + "      text-align: center; \n"
//                    + "      border: 3px solid orange; \n"
//                    + "      border-radius: 10px\n"
//                    + "      \"\n"
//                    + "    >\n"
//                    + "    <h1>Quý khách hàng thân mến!</h1>\n"
//                    + "      <h3>Cảm ơn bạn vì đã trở thành khách hàng của Online Shoes Store</h3>\n"
//                    + "      <small\n"
//                    + "        >Cảm ơn bạn vì đã tạo tài khoản. Xin hãy click vào nút bên dưới để kích hoạt tài khoản:</small>\n"
//                    + "      <small\n"
//                    + "        >Nếu bạn không tạo tài khoản, xin hãy bỏ qua email này.</small\n"
//                    + "      >\n"
//                    + "      <button\n"
//                    + "        style=\"\n"
//                    + "          padding: 10px;\n"
//                    + "          border-radius: 10px;\n"
//                    + "          border-color: rgb(255,69,0);\n"
//                    + "          display: block;\n"
//                    + "          margin: 20px auto;\n"
//                    + "        \"\n"
//                    + "      >\n"
//                    + "        <a style=\"text-decoration: none\" href=\"http://localhost:8080/SWP_391/verify?token=" + verifyCode + "\">Kích hoạt tài khoản</a>\n"
//                    + "      </button>\n"
//                    + "      <h5>Nếu bạn có câu hỏi? <a href=\"\">Chúng tôi ở đây để giúp đỡ bạn</a></h5>\n"
//                    + "    </div>",
//                    "text/html");
//
//            //send the email message
//            Transport.send(message);
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public void sendResetPassword(String toEmail, String resetPasswordToken) {
//        //provide sender's email ID
//        String fromEmail = "kn97502@gmail.com";
//        String fromPassword = "lgsrpjfrvhhkrxhw";
//
//        //provide Mailtrap's host address
//        String host = "smtp.gmail.com";
//        //configure Mailtrap's SMTP server details
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", "587");
//
//        //create the Session object
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(fromEmail, fromPassword);
//            }
//        });
//
//        try {
//            //create a MimeMessage object
//            Message message = new MimeMessage(session);
//
//            //set From email field
//            message.setFrom(new InternetAddress(fromEmail));
//
//            //set To email field
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(toEmail));
//
//            //set email subject field
//            message.setSubject("Online Shore Store");
//
//            //set the content of the email message
//            message.setContent("<div\n"
//                    + "      style=\"\n"
//                    + "      width: 50vw;\n"
//                    + "      margin: 0 auto;\n"
//                    + "      padding: 20px 100px;\n"
//                    + "      text-align: center; \n"
//                    + "      border: 3px solid orange; \n"
//                    + "      border-radius: 10px\n"
//                    + "      \"\n"
//                    + "    >\n"
//                    + "    <h1>Quý khách hàng thân mến!</h1>\n"
//                    + "      <h3>Mật khẩu của bạn đã được reset thành công!</h3>\n"
//                    + "      <small\n"
//                    + "        >Xin hãy click vào nút bên dưới để tiến hành đặt lại mật khẩu:</small>\n"
//                    + "      <small\n"
//                    + "        >Nếu bạn không reset mật khẩu, bạn có thể bỏ qua email này.</small\n"
//                    + "      >\n"
//                    + "      <button\n"
//                    + "        style=\"\n"
//                    + "          padding: 10px;\n"
//                    + "          border-radius: 10px;\n"
//                    + "          border-color: rgb(255,69,0);\n"
//                    + "          display: block;\n"
//                    + "          margin: 20px auto;\n"
//                    + "        \"\n"
//                    + "      >\n"
//                    + "        <a style=\"text-decoration: none\" href=\"http://localhost:8080/SWP_391/SetNewPassword?token=" + resetPasswordToken + "\">Đặt lại mật khẩu</a>\n"
//                    + "      </button>\n"
//                    + "      <h5>Nếu bạn có câu hỏi?  <a href=\"\">Chúng tôi ở đây để giúp đỡ bạn</a></h5>\n"
//                    + "    </div>",
//                    "text/html");
//
//            //send the email message
//            Transport.send(message);
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}