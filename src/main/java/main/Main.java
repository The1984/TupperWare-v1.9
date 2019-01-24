package main;

import java.util.Properties;
import javax.swing.UIManager;
import com.jtattoo.plaf.hifi.HiFiLookAndFeel;
import presentacion.controlador.ControladorVentanaLogin;

public class Main 
{

	public static void main(String[] args) 
	{
		
		try 
		{
			Properties props = new Properties();
			props.put("logoString", "");
            props.put("buttonColorDark", "0 30 60");
            props.put("windowTitleColorLight", "0 0 0");
			HiFiLookAndFeel.setCurrentTheme(props);
			UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

		ControladorVentanaLogin controladorLogin = new ControladorVentanaLogin();
		controladorLogin.initialize();
		
	}

}