package persistencia.dao.intefaz;

import java.util.List;

import dto.CampañaDTO;

public interface CampañaDAO 
{

	public boolean insert (CampañaDTO campaña);

	public boolean update (CampañaDTO campaña);
	
	public boolean delete (CampañaDTO campaña);
	
	public List<CampañaDTO> readAll ();

	public CampañaDTO readForId (int idCampaña);
	
}
