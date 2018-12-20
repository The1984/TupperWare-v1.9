package persistencia.dao.intefaz;

import java.util.List;

import dto.ContactoDTO;

public interface ContactoDAO 
{

	public boolean insert (ContactoDTO contacto);

	public boolean update (ContactoDTO contacto);
	
	public boolean delete (ContactoDTO contacto);
	
	public List<ContactoDTO> readAll ();

	public ContactoDTO readForId (int idContacto);
		
}