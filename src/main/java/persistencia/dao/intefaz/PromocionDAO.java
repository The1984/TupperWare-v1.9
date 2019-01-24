package persistencia.dao.intefaz;

import java.util.List;

import dto.Campa�aDTO;
import dto.PromocionDTO;

public interface PromocionDAO 
{

	public boolean insert (PromocionDTO promocion);

	public boolean update (PromocionDTO promocion);
	
	public boolean delete (PromocionDTO promocion);

	public List<PromocionDTO> readAll ();
	
	public List<PromocionDTO> readForCampa�a (Campa�aDTO campa�a);
	
	public PromocionDTO readForId (int idPromocion);

	public PromocionDTO readForCampa�aNombre (int idCampa�a, String titulo);
	
	public int idUltimoInsert();
	
}