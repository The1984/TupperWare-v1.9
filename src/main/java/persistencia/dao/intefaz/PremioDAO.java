package persistencia.dao.intefaz;

import java.util.List;

import dto.PremioDTO;

public interface PremioDAO 
{

	public boolean insert (PremioDTO premio);

	public boolean update (PremioDTO premio);
	
	public boolean delete (PremioDTO premio);

	public List<PremioDTO> readForIdCampa�a (int idCampa�a);
	
	public PremioDTO readForId (int idPremio);

}