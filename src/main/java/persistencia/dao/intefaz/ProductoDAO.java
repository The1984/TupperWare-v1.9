package persistencia.dao.intefaz;

import java.util.List;

import dto.ProductoDTO;

public interface ProductoDAO 
{

	public boolean insert (ProductoDTO producto);

	public boolean update (ProductoDTO producto);
	
	public boolean delete (ProductoDTO producto);
	
	public List<ProductoDTO> readAll ();

	public ProductoDTO readForId (int idProducto);
	
}