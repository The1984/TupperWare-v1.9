package persistencia.dao.intefaz;

import dto.CompraPromocionDTO;

public interface CompraPromocionDAO 
{

	public boolean insert (CompraPromocionDTO compraPromocion);

	public boolean delete (CompraPromocionDTO compraPromocion);
	
	public CompraPromocionDTO readForId (int compraPromocion);
	
}
