package persistencia.dao.intefaz;

import java.util.List;

import dto.EstadoDeCompraDTO;

public interface EstadoDeCompraDAO 
{

	public boolean insert (EstadoDeCompraDTO estadoDeCompra);

	public boolean update (EstadoDeCompraDTO estadoDeCompra);
	
	public boolean delete (EstadoDeCompraDTO estadoDeCompra);
	
	public List<EstadoDeCompraDTO> readAll ();

	public EstadoDeCompraDTO readForId (int idEstadoDeCompra);
	
}
