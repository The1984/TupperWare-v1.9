package dto;

import java.util.List;

public class PromocionDTO 
{

	private int idPromocion;
	private String nombre;
	private String descripcion;
	private String pagina;
	private int precio;
	private List<ProductoDTO> productos;
	private Campa�aDTO campa�a;

	public int getIdPromocion() 
	{
		return idPromocion;
	}
	
	public void setIdPromocion(int idPromocion) 
	{
		this.idPromocion = idPromocion;
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
	
	public String getPagina() 
	{
		return pagina;
	}
	
	public void setPagina(String pagina) 
	{
		this.pagina = pagina;
	}
	
	public int getPrecio() 
	{
		return precio;
	}

	public void setPrecio(int precio) 
	{
		this.precio = precio;
	}
	
	public List<ProductoDTO> getProductos() 
	{
		return productos;
	}
	
	public void setProductos(List<ProductoDTO> productos) 
	{
		this.productos = productos;
	}

	public Campa�aDTO getCampa�a() {
		return campa�a;
	}

	public void setCampa�a(Campa�aDTO campa�a) 
	{
		this.campa�a = campa�a;
	}

}