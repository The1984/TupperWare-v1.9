package util;

import javax.swing.JOptionPane;

import persistencia.conexion.Conexion;
import presentacion.controlador.ControladorLogin;
import presentacion.vista.VentanaExportBD;
import presentacion.vista.VentanaImportBD;
import presentacion.vista.VentanaPrincipal;

public class ImportExportBD {

	Conexion Connection = Conexion.getConexion();
	
	public void Exportar(String rutaExport, VentanaExportBD vista) 
	{
		String ruta = rutaExport;
		String nombre = "\\"+Connection.getBd()+".sql";
		String comando = "";
	
		if(ruta.trim().length()!=0) 
		{
			try 
			{
				comando = "C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysqldump --opt -u"+Connection.getUser()+" -p"+Connection.getPass()+" -B "+Connection.getBd()+" -r "+ruta+nombre;
				Runtime rt = Runtime.getRuntime();
				rt.exec(comando);
				vista.close();
				JOptionPane.showMessageDialog(null, "Exportacion Exitosa!!");
			}
			catch(Exception ex) 
			{
				JOptionPane.showMessageDialog(null, "No se realizo la Exportacion");
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Introduzca el destino de la BD");
		}
	}
	
	public void Importar(String rutaImport, VentanaImportBD vista, VentanaPrincipal view) 
	{
		String ruta = rutaImport;
		String comando = "";
		if(ruta.trim().length()!=0) 
		{
			try 
			{
				comando = "cmd /c \"C:\\Program Files\\MySQL\\MySQL Server 5.7\\bin\\mysql.exe\""+" -u"+Connection.getUser()+" -p"+Connection.getPass()+" "+Connection.getBd()+" < "+ruta;
				Runtime rt = Runtime.getRuntime();
				rt.exec(comando);
				vista.close();
				view.close();
				JOptionPane.showMessageDialog(null, "Importacion Exitosa!!");
				ControladorLogin controladoLogin=new ControladorLogin();
				controladoLogin.initialize();
			}
			catch(Exception ex) 
			{
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Falta seleccionar el archivo.sql");
		}
	}
	
}