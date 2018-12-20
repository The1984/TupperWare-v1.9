package persistencia.dao.intefaz;

import java.util.List;

import dto.CompraPromocionProductoDTO;
import dto.ProductoDTO;

public interface CompraPromocionProductoDAO 
{

	public boolean insert (CompraPromocionProductoDTO compraProductoPromocion);
	
	public boolean delete ( int idCompraPromocion, int idProducto );
	
	public List<ProductoDTO> readProductos (int idCompraPromocion);

}