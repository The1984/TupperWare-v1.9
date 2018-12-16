package persistencia.dao.intefaz;

import java.util.List;

import dto.ClienteDTO;

public interface ClienteDAO 
{

	public boolean insert (ClienteDTO cliente);

	public boolean update (ClienteDTO cliente);
	
	public boolean delete (ClienteDTO cliente);
	
	public List<ClienteDTO> readAll ();
	
	public ClienteDTO readForId (int idCliente);

}
