package dto;

public class ContactoDTO 
{

	private int idContacto;
	private String nombre;
	private String apellido;
	private String direccion;
	private String celular;
	private String email;
	private String tipoDeContacto;

	public int getIdContacto() 
	{
		return idContacto;
	}
	
	public void setIdContacto(int idContacto) 
	{
		this.idContacto = idContacto;
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
	
	public String getTipoDeContacto() 
	{
		return tipoDeContacto;
	}
	
	public void setTipoDeContacto(String tipoDeContacto) 
	{
		this.tipoDeContacto = tipoDeContacto;
	}
	
}