package persistencia.dao.intefaz;

import dto.ImagenDeProductoDTO;

public interface ImagenDeProductoDAO 
{

	public boolean insert (String rutaImagen, int idProducto);

	public boolean update (String rutaImagen, int idProducto);
	
	public boolean delete (int idProducto);
	
	public ImagenDeProductoDTO readForIdProducto (int idProducto);

}