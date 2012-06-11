package util;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ufcg.si1.infoCarona.model.ArgumentoInexistenteException;

public class EnviarEmail {  
    
    private static String mailSMTPServer;  
    private static String mailSMTPServerPort;
	private static String emailRemtente;
	private static String senhaRemetente;  
      
    EnviarEmail() { //Para o GMAIL   
        mailSMTPServer = "smtp.gmail.com";  
        mailSMTPServerPort = "465";  
    }  
    /* 
     * caso queira mudar o servidor e a porta, so enviar para o contrutor 
     * os valor como string 
     */  
    EnviarEmail(String mailSMTPServer, String mailSMTPServerPort) { //Para outro Servidor  
        this.mailSMTPServer = mailSMTPServer;  
        this.mailSMTPServerPort = mailSMTPServerPort;  
    }  
     
    /**
     * Metodo para enviar um email 
     * @param nomeDestinatario - nome do destinatário
     * @param emailDestinatario - email do destinatário
     * @param assunto - InfoCarona
     * @param mensagem - mensagem do email
     * @return
     */
    public static boolean sendMail(String nomeDestinatario, String emailDestinatario, String assunto, String mensagem) {  
        boolean retorno = true;
        mailSMTPServer = "smtp.gmail.com";  
        mailSMTPServerPort = "465";  
    	emailRemtente = "infocarona@gmail.com";
        senhaRemetente = "infocarona1.0";
        Properties props = new Properties();  
    
        props.put("mail.transport.protocol", "smtp"); //define protocolo de envio como SMTP  
        props.put("mail.smtp.starttls.enable","true");   
        props.put("mail.smtp.host", mailSMTPServer); //server SMTP do GMAIL  
        props.put("mail.smtp.auth", "true"); //ativa autenticacao  
        props.put("mail.smtp.user", nomeDestinatario); //usuario ou seja, a conta que esta enviando o email (tem que ser do GMAIL)  
        props.put("mail.debug", "true");  
        props.put("mail.smtp.port", mailSMTPServerPort); //porta  
        props.put("mail.smtp.socketFactory.port", mailSMTPServerPort); //mesma porta para o socket  
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");  
        props.put("mail.smtp.socketFactory.fallback", "false");  
            
        SimpleAuth auth = null;  
        auth = new SimpleAuth (emailRemtente,senhaRemetente);  
          
        Session session = Session.getDefaultInstance(props);  
        session.setDebug(true); //Habilita o LOG das ações executadas durante o envio do email  
  
          
        Message msg = new MimeMessage(session);  
  
        try {  
            //Setando o destinatário  
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailDestinatario));  
            //Setando a origem do email  
            msg.setFrom(new InternetAddress(nomeDestinatario));  
            //Setando o assunto  
            msg.setSubject(assunto);  
            //Setando o conteúdo/corpo do email  
            msg.setContent(mensagem,"text/plain");  
  
        } catch (Exception e) {  
            //System.out.println(">> Erro: Completar Mensagem");  
            //e.printStackTrace();
        	retorno = false;
        }  
          
        //Objeto encarregado de enviar os dados para o email  
        Transport tr;  
        try {  
            tr = session.getTransport("smtp"); //define smtp para transporte  
            /* 
             *  1 - define o servidor smtp 
             *  2 - seu nome de usuario do gmail 
             *  3 - sua senha do gmail 
             */  
            tr.connect(mailSMTPServer, emailRemtente, senhaRemetente);  
            msg.saveChanges(); // don't forget this  
            //envio da mensagem  
            tr.sendMessage(msg, msg.getAllRecipients());  
            tr.close();  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            //System.out.println(">> Erro: Envio Mensagem");  
            //e.printStackTrace();
        	retorno = false;
        }
		return retorno;  
  
    } 
}  
  
//clase que retorna uma autenticacao para ser enviada e verificada pelo servidor smtp  
class SimpleAuth extends Authenticator {  
    public String username = null;  
    public String password = null;  
  
  
    public SimpleAuth(String user, String pwd) {  
        username = user;  
        password = pwd;  
    }  
  
    protected PasswordAuthentication getPasswordAuthentication() {  
        return new PasswordAuthentication (username,password.toCharArray());  
    }
}

