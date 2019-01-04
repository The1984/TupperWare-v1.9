package dto;

public class ClienteDTO 
{

	private int idCliente;
	private String nombre;
	private String apellido;
	private String direccion;
	private String celular;	
	private String email;
	
	public int getIdCliente() 
	{
		return idCliente;
	}

	public void setIdCliente(int idCliente) 
	{
		this.idCliente = idCliente;
	}

	public String getNombre() 
	{
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getApellido() 
	{
		return apellido;
	}

	public void setApellido(String apellido) 
	{
		this.apellido = apellido;
	}

	public String getDireccion() 
	{
		return direccion;
	}

	public void setDireccion(String direccion) 
	{
		this.direccion = direccion;
	}

	public String getCelular() 
	{
		return celular;
	}

	public void setCelular(String celular) 
	{
		this.celular = celular;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}
	
}