package persistencia.dao.intefaz;

import java.util.List;

import dto.ProductoDTO;
import dto.PromocionProductoDTO;

public interface PromocionProductoDAO 
{

	public boolean insert (PromocionProductoDTO promocionProducto);
	
	public boolean delete ( int idPromocion, int idProducto );
	
	public List<ProductoDTO> readProductos (int idPromocion);
	
}