package dto;

public class TipoDeProductoDTO 
{

	private int idTipoDeProducto;
	private String nombre;
	private int porcentajeDeGanancia;

	public int getIdTipoDeProducto() 
	{
		return idTipoDeProducto;
	}
	
	public void setIdTipoDeProducto(int idTipoDeProducto) 
	{
		this.idTipoDeProducto = idTipoDeProducto;
	}
	
	public String getNombre() 
	{
		return nombre;
	}
	
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	public int getPorcentajeDeGanancia() 
	{
		return porcentajeDeGanancia;
	}
	
	public void setPorcentajeDeGanancia(int porcentajeDeGanancia) 
	{
		this.porcentajeDeGanancia = porcentajeDeGanancia;
	}
	
}