package com.poly.servlet.user;

import java.io.IOException;
import java.util.Properties;
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
@WebServlet({"/sendemail/form.php","/sendemail/submit.php"})
public class SendEmailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/views/user/sendemail.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {				
		try {
			
			req.setCharacterEncoding("utf-8");
			resp.setCharacterEncoding("utf-8");
			//Thong so ket noi SMTP Server
            Properties p = new Properties();
            p.put("mail.smtp.auth", "true");
            p.put("mail.smtp.starttls.enable", "true");
            p.put("mail.smtp.host", "smtp.gmail.com");
            p.put("mail.smtp.port", "587");
            p.put("mail.smtp.ssl.protocols", "TLSv1.2");
                        
            //Ket noi SMTP Server
            Session s = Session.getInstance(p, new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                        	String username = "hungnvps20689@fpt.edu.vn";
                            String password = "nipcaskwqtgiskyp"; //
                            return new PasswordAuthentication(username,password );
                        }});          
            
            String from = req.getParameter("from");
    		String to = req.getParameter("to");	
    		String subject = req.getParameter("subject");
    		String body = req.getParameter("body");
    		
    		//Tao message
            MimeMessage msg = new MimeMessage(s);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject,"utf-8");
            msg.setText(body,"utf-8","html");
            msg.setReplyTo(msg.getFrom());
            
            Transport.send(msg);
            
            req.setAttribute("message", "Gửi email thành công !");
        } catch (Exception ex) {           
            req.setAttribute("message", "Gửi email thất bại !");
            ex.printStackTrace();
        }   
		this.doGet(req, resp);	
	}
}
