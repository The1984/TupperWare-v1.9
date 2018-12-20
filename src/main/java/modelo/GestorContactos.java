package modelo;

import java.util.List;

import dto.ContactoDTO;
import persistencia.dao.mysql.ContactoDAOMYSQL;

public class GestorContactos 
{
	
	private static GestorContactos instance;
	private ContactoDAOMYSQL contactoDaoSQL;
	
	private GestorContactos() 
	{
		this.contactoDaoSQL = ContactoDAOMYSQL.getInstance();
	}

	public static GestorContactos getInstance() 
	{
		if ( instance == null )
			instance = new GestorContactos();
		return instance;
	}

	public void insert(ContactoDTO contacto) 
	{
		this.contactoDaoSQL.insert(contacto);
	}
	
	public void delete(ContactoDTO contacto)
	{
		this.contactoDaoSQL.delete(contacto);
	}
	
	public void update(ContactoDTO contacto)
	{
		this.contactoDaoSQL.update(contacto);
	}

	public List<ContactoDTO> readAll() 
	{
		return this.contactoDaoSQL.readAll();
	}
	
	public void readForId(int idContacto)
	{
		this.contactoDaoSQL.readForId(idContacto);
	}
	
}