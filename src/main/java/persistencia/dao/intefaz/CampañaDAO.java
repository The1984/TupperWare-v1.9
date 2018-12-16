package persistencia.dao.intefaz;

import java.util.List;

import dto.Campa�aDTO;

public interface Campa�aDAO 
{

	public boolean insert (Campa�aDTO campa�a);

	public boolean update (Campa�aDTO campa�a);
	
	public boolean delete (Campa�aDTO campa�a);
	
	public List<Campa�aDTO> readAll ();

	public Campa�aDTO readForId (int idCampa�a);
	
}
