package persistencia.dao.intefaz;

import java.util.List;

import dto.CampañaDTO;
import dto.PromocionDTO;

public interface PromocionDAO 
{

	public boolean insert (PromocionDTO promocion);

	public boolean update (PromocionDTO promocion);
	
	public boolean delete (PromocionDTO promocion);

	public List<PromocionDTO> readAll ();
	
	public List<PromocionDTO> readForCampaña (CampañaDTO campaña);
	
	public PromocionDTO readForId (int idPromocion);

	public PromocionDTO readForCampañaNombre (int idCampaña, String titulo);
	
	public int idUltimoInsert();
	
}