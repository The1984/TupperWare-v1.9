package dto;

public class ProductoDTO 
{

	private int idProducto;
	private String codigo;
	private String nombre;
	private String descripcion;
	private TipoDeProductoDTO tipoDeProducto;
	
	public int getIdProducto() 
	{
		return idProducto;
	}
	
	public void setIdProducto(int idProducto) 
	{
		this.idProducto = idProducto;
	}
	
	public String getCodigo() 
	{
		return codigo;
	}
	
	public void setCodigo(String codigo) 
	{
		this.codigo = codigo;
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

	public TipoDeProductoDTO getTipoDeProducto() 
	{
		return tipoDeProducto;
	}

	public void setTipoDeProducto(TipoDeProductoDTO tipoDeProducto) 
	{
		this.tipoDeProducto = tipoDeProducto;
	}
	
}