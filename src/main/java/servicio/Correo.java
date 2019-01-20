package servicio;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class Correo 
{

	private static Correo instance;
	private Session session;
	private Properties properties;
	private String nombreEmisor;
	private String mailEmisor;
	private String passwordEmisor;
	private String mailReceptor;
	private String asunto;
	private String mensaje;
	private String rutaArchivo;
	private Multipart multipart;
	private MimeBodyPart mimeBodyPartAdjunto;
	private MimeBodyPart mimeBodyPart;
	
	private Correo() 
	{		
		multipart = new MimeMultipart();
		mimeBodyPartAdjunto = new MimeBodyPart();
		mimeBodyPart = new MimeBodyPart();
	}

	public static Correo getInstance() 
	{
		if ( instance == null )
		{
			instance = new Correo();
		}
		return instance;
	}
	
	public void sendEmail()
	{
		this.setProperties(new Properties());
		// La configuración para enviar correo
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.user", this.getMailEmisor());
		properties.put("mail.password", this.getPasswordEmisor());

		// Obtener la sesion
		session = Session.getInstance(properties, null);
		
		try 
		{
			// Crear el cuerpo del mensaje
			MimeMessage mimeMessage = new MimeMessage(session);

//			mimeMessage.setContent(this.getMensaje(), "text/html; charset=utf-8");
			
			// Agregar quien envía el correo
			mimeMessage.setFrom(new InternetAddress(this.getMailEmisor(), this.getNombreEmisor()));

			// Los destinatarios
			InternetAddress[] internetAddresses = {
//					new InternetAddress("correo1@gmail.com"),
//					new InternetAddress("correo2@gmail.com"),
					new InternetAddress(this.getDestinatario()) };

			// Agregar los destinatarios al mensaje
			mimeMessage.setRecipients(Message.RecipientType.TO,
					internetAddresses);
			
			// Agregar el asunto al correo
			mimeMessage.setSubject(this.getAsunto());
			
			// Creo la parte del mensaje
//			MimeBodyPart mimeBodyPart = new MimeBodyPart();
//			mimeBodyPart.setText(this.getMensaje());
			mimeBodyPart.setContent( this.getMensaje(), "text/html; charset=utf-8" );

			// Crear el multipart para agregar la parte del mensaje anterior
			this.getMultipart().addBodyPart(mimeBodyPart);

			// Agregar el multipart al cuerpo del mensaje
			mimeMessage.setContent(multipart);

			// Enviar el mensaje
			Transport transport = session.getTransport("smtp");
			transport.connect(this.getMailEmisor(), this.getPasswordEmisor());
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			
			this.setearMultipart();
			JOptionPane.showMessageDialog(null, "Tu mensaje ha sido enviado.");
		} 
		catch (Exception ex) 
		{
			JOptionPane.showMessageDialog(null, "¡Falló el envío del mensaje!", "Error", JOptionPane.ERROR_MESSAGE);
			ex.printStackTrace();
		}	
	}

	public String getMailEmisor() 
	{
		return mailEmisor;
	}

	public void setMailEmisor(String mail) 
	{
		this.mailEmisor = mail;
	}

	public String getPasswordEmisor() 
	{
		return passwordEmisor;
	}

	public void setPasswordEmisor(String pass) 
	{
		this.passwordEmisor = pass;
	}

	public String getDestinatario() 
	{
		return mailReceptor;
	}

	public void setDestinatario(String destinatario) 
	{
		this.mailReceptor = destinatario;
	}
	
	public String getAsunto() 
	{
		return asunto;
	}

	public void setAsunto(String asunto) 
	{
		this.asunto = asunto;
	}

	public String getMensaje() 
	{
		return mensaje;
	}

	public void setMensaje(String mensaje) 
	{
		this.mensaje = mensaje;
	}

	public String getNombreEmisor() 
	{
		return nombreEmisor;
	}

	public void setNombreEmisor(String nombreEmisor) 
	{
		this.nombreEmisor = nombreEmisor;
	}
	
	public String getRutaArchivo() 
	{
		return rutaArchivo;
	}
	
	public Properties getProperties() 
	{
		return properties;
	}

	public void setProperties(Properties properties) 
	{
		this.properties = properties;
	}
	
	public Multipart getMultipart() 
	{
		return multipart;
	}

	public MimeBodyPart getMimeBodyPart() 
	{
		return mimeBodyPart;
	}
	
	public MimeBodyPart getMimeBodyPartAdjunto() 
	{
		return mimeBodyPartAdjunto;
	}

	public void setearMultipart()
	{
		try
		{
			this.getMultipart().removeBodyPart(this.getMimeBodyPartAdjunto());
			this.getMultipart().removeBodyPart(this.getMimeBodyPart());
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void adjuntarArchivo(String ruta)
	{
		// Adjunta un archivo al mensaje
		
		try 
		{
			mimeBodyPartAdjunto.attachFile(ruta);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
		try 
		{
			this.getMultipart().addBodyPart(mimeBodyPartAdjunto);
		} 
		catch (MessagingException e) 
		{
			e.printStackTrace();
		}
	}
	
}