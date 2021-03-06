package presentacion.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import presentacion.vista.VentanaLogin;
import servicio.Correo;
import util.Encriptador;
import util.OwnerProperties;

public class ControladorVentanaLogin implements KeyListener 
{

	VentanaLogin ventanaLogin;

	public ControladorVentanaLogin() 
	{
		this.ventanaLogin = new VentanaLogin();
		this.ventanaLogin.getPassword().addKeyListener(this);
		this.ventanaLogin.getPassword().setFocusable(true);
		String bienvenida = "� Bienvenid@ "+OwnerProperties.getInstance().getApellido()+" "+OwnerProperties.getInstance().getNombre()+" !";
		this.ventanaLogin.getWelcomeLabel().setText(bienvenida);
	}
	
	public void initialize() 
	{
		this.ventanaLogin.show();
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		if(e.getKeyCode()==KeyEvent.VK_ENTER)
		{
			String passIntro = Encriptador.sha1(this.ventanaLogin.getPassword().getText());
			String passOriginal = OwnerProperties.getInstance().getPass();
			if (passIntro.equals(passOriginal)) 
			{
				this.ventanaLogin.close();
				Correo.getInstance().setNombreEmisor(OwnerProperties.getInstance().getApellido()+" "+OwnerProperties.getInstance().getNombre());
				Correo.getInstance().setMailEmisor(OwnerProperties.getInstance().getEmail());
				Correo.getInstance().setPasswordEmisor(this.ventanaLogin.getPassword().getText());
				ControladorVentanaPrincipal controladorPrincipal = new ControladorVentanaPrincipal();
				controladorPrincipal.initialize();
			}
			else
			{
				this.ventanaLogin.getPassword().setText("");
			}			
		}	
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub
		
	}
	
}