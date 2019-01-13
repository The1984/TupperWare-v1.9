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
//            props.put("selectionBackgroundColor", "180 240 197"); 
//            props.put("menuSelectionBackgroundColor", "180 240 197"); 
//
//            props.put("controlColor", "218 254 230");
//            props.put("controlColorLight", "218 254 230");
//            props.put("controlColorDark", "116 116 116mejo"); 
//
//            props.put("buttonColor", "218 230 254");
//            props.put("buttonColorLight", "255 255 255");
            props.put("buttonColorDark", "2 99 104");
//
//            props.put("rolloverColor", "218 254 230"); 
//            props.put("rolloverColorLight", "218 254 230"); 
//            props.put("rolloverColorDark", "180 240 197"); 
//
//            props.put("windowTitleForegroundColor", "0 0 0");
//            props.put("windowTitleBackgroundColor", "180 240 197"); 
//            props.put("windowTitleColorLight", "218 254 230"); 
//            props.put("windowTitleColorDark", "180 240 197"); 
//            props.put("windowBorderColor", "218 254 230");
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