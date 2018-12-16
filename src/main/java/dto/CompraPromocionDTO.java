package dto;

import java.util.List;

public class CompraPromocionDTO 
{

	private int idCompraPromocion;
	private PromocionDTO promocion;
	private List<ProductoDTO> productos;	

	public int getIdCompraPromocion() 
	{
		return idCompraPromocion;
	}
	
	public void setIdCompraPromocion(int idCompraPromocion) 
	{
		this.idCompraPromocion = idCompraPromocion;
	}

	public PromocionDTO getPromocion() 
	{
		return promocion;
	}

	public void setPromocion(PromocionDTO promocion) 
	{
		this.promocion = promocion;
	}
	
	public List<ProductoDTO> getProductos() 
	{
		return productos;
	}
	
	public void setProductos(List<ProductoDTO> productos) 
	{
		this.productos = productos;
	}

}
