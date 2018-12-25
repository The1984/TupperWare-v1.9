package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion 
{
	public static Conexion instancia;
	private Connection connection;
	private String user="root";
	private String pass="root";
	private String bd="TupperWare";
	
	private Conexion()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+this.getBd(), this.getUser(), this.getPass());
			System.out.println("Conexion exitosa");
		}
		catch(Exception e)
		{
			System.out.println("Conexion fallida "+ e);
		}
	}

	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return this.connection;
	}
	
	public void cerrarConexion()
	{
		try 
		{
			this.connection.close();
			System.out.println("Conexion cerrada");
		}
		catch (SQLException e) 
		{
			System.out.println("Error al cerrar la conexion! " + e);
		}
		instancia = null;
	}
	
	public String getUser() 
	{
		return user;
	}

	public String getPass() 
	{
		return pass;
	}

	public String getBd() 
	{
		return bd;
	}

}
