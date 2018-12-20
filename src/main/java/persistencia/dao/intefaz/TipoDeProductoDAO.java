package persistencia.dao.intefaz;

import java.util.List;

import dto.TipoDeProductoDTO;

public interface TipoDeProductoDAO 
{

	public boolean insert (TipoDeProductoDTO tipoDeProducto);

	public boolean update (TipoDeProductoDTO tipoDeProducto);
	
	public boolean delete (TipoDeProductoDTO tipoDeProducto);
	
	public List<TipoDeProductoDTO> readAll ();

	public TipoDeProductoDTO readForId (int idTipoDeProducto);
	
}