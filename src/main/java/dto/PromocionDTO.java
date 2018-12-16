package dto;

import java.util.List;

public class PromocionDTO 
{

	private int idPromocion;
	private String titulo;
	private String descripcion;
	private List<ProductoDTO> productos;
	private CampañaDTO campaña;

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

	public CampañaDTO getCampaña() {
		return campaña;
	}

	public void setCampaña(CampañaDTO campaña) {
		this.campaña = campaña;
	}

}
