package dto;

import java.util.List;

public class PromocionDTO 
{

	private int idPromocion;
	private String titulo;
	private String descripcion;
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
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getDescripcion() 
	{
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) 
	{
		this.descripcion = descripcion;
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

	public void setCampa�a(Campa�aDTO campa�a) {
		this.campa�a = campa�a;
	}

}
