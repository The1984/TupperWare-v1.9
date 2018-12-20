package persistencia.dao.intefaz;

import java.util.List;

import dto.CompraDTO;

public interface CompraDAO 
{

	public boolean insert (CompraDTO compra);

	public boolean update (CompraDTO compra);
	
	public boolean delete (CompraDTO compra);
	
	public List<CompraDTO> readAll ();
	
	public CompraDTO readForId (int idCompra);
	
	public List<CompraDTO> readForIdCampaña (int idCampaña);
	
	public List<CompraDTO> readForIdCliente (int idCliente);
	
}