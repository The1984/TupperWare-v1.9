package dto;

public class PremioDTO 
{

	private int idPremio;
	private String nombre;
	private String descripcion;
	private int unidadesMinimas;
	private boolean recibido;
	private int idCampaña;
	
	public int getIdPremio() 
	{
		return idPremio;
	}
	
	public void setIdPremio(int idPremio) 
	{
		this.idPremio = idPremio;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public String getDescripcion() 
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
	}
	
	public int getUnidadesMinimas() 
	{
		return unidadesMinimas;
	}
	
	public void setUnidadesMinimas(int unidadesMinimas) 
	{
		this.unidadesMinimas = unidadesMinimas;
	}
	
	public boolean getRecibido() 
	{
		return recibido;
	}
	
	public void setRecibido(boolean recibido) 
	{
		this.recibido = recibido;
	}

	public int getIdCampaña() 
	{
		return idCampaña;
	}

	public void setIdCampaña(int idCampaña) 
	{
		this.idCampaña = idCampaña;
	}
	
}