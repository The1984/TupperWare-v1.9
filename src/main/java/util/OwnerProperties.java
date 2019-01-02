package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class OwnerProperties
{
	
	private Properties props;
	private static OwnerProperties instance;
	private static final String FILE_PATH = System.getProperty("user.dir") + "/config/Owner.properties";

	public static OwnerProperties getInstance()
	{
		if(instance==null)
			instance = new OwnerProperties();
		return instance;
	}
	
	public OwnerProperties() 
	{
		props = new Properties();
	}

	public String getNombre() 
	{
		try 
		{
			this.props.load(new FileInputStream(new File(FILE_PATH)));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return this.props.getProperty("Nombre");
	}

	public void setNombre(String nombre)
	{
		try 
		{
			this.props.load(new FileInputStream(new File(FILE_PATH)));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		this.props.put("Nombre", nombre);
		try 
		{
			props.store(new FileOutputStream(new File(FILE_PATH)), "");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public String getApellido()
	{
		try 
		{
			this.props.load(new FileInputStream(new File(FILE_PATH)));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return this.props.getProperty("Apellido");
	}

	public void setApellido(String apellido)
	{
		try 
		{
			this.props.load(new FileInputStream(new File(FILE_PATH)));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		this.props.put("Apellido", apellido);
		try 
		{
			props.store(new FileOutputStream(new File(FILE_PATH)), "");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public String getEmail()
	{
		try 
		{
			this.props.load(new FileInputStream(new File(FILE_PATH)));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return this.props.getProperty("Email");
	}

	public void setEmail(String email)
	{
		try 
		{
			this.props.load(new FileInputStream(new File(FILE_PATH)));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		this.props.put("Email", email);
		try 
		{
			props.store(new FileOutputStream(new File(FILE_PATH)), "");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	public String getPass()
	{
		try 
		{
			this.props.load(new FileInputStream(new File(FILE_PATH)));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		return this.props.getProperty("Pass");
	}

	public void setPass(String pass)
	{
		try 
		{
			this.props.load(new FileInputStream(new File(FILE_PATH)));
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		this.props.put("Pass", pass);
		try 
		{
			props.store(new FileOutputStream(new File(FILE_PATH)), "");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
}