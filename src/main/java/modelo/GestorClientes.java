package modelo;

import java.util.List;

import dto.ClienteDTO;
import persistencia.dao.mysql.ClienteDAOMYSQL;

public class GestorClientes 
{
	
	private static GestorClientes instance;
	private ClienteDAOMYSQL clienteDaoSQL;
	
	private GestorClientes() 
	{
		this.clienteDaoSQL = ClienteDAOMYSQL.getInstance();
	}

	public static GestorClientes getInstance() 
	{
		if ( instance == null )
			instance = new GestorClientes();
		return instance;
	}

	public void insert(ClienteDTO cliente) 
	{
		this.clienteDaoSQL.insert(cliente);
	}
	
	public void delete(ClienteDTO cliente)
	{
		this.clienteDaoSQL.delete(cliente);
	}
	
	public void update(ClienteDTO cliente)
	{
		this.clienteDaoSQL.update(cliente);
	}

	public List<ClienteDTO> readAll() 
	{
		return this.clienteDaoSQL.readAll();
	}
	
	public ClienteDTO readForId(int idCliente)
	{
		return this.clienteDaoSQL.readForId(idCliente);
	}
	
}