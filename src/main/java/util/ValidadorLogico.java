package util;

import java.util.List;

import dto.ClienteDTO;

public class ValidadorLogico 
{

	public static boolean existeNombreApellidoAgregar(String nombre, String apellido, List<ClienteDTO> clientes)
	{
		for(ClienteDTO cliente : clientes)
		{
			if(cliente.getNombre().toLowerCase().equals(nombre.toLowerCase()) && 
			   cliente.getApellido().toLowerCase().equals(apellido.toLowerCase()))
				return true;
		}
		return false;
	}
	
	public static boolean existeEmailAgregar(String email, List<ClienteDTO> clientes)
	{
		for(ClienteDTO cliente : clientes)
		{
			if(cliente.getEmail().equals(email))
				return true;
		}
		return false;
	}
	
	public static boolean existeNombreApellidoEditar(int idCliente, String nombre, String apellido, List<ClienteDTO> clientes)
	{
		for(ClienteDTO cliente : clientes)
		{
			if(cliente.getNombre().toLowerCase().equals(nombre.toLowerCase()) && 
			   cliente.getApellido().toLowerCase().equals(apellido.toLowerCase()) &&
			   cliente.getIdCliente()!=idCliente)
				return true;
		}
		return false;
	}
	
	public static boolean existeEmailEditar(int idCliente, String email, List<ClienteDTO> clientes)
	{
		for(ClienteDTO cliente : clientes)
		{
			if(cliente.getEmail().equals(email) &&
			   cliente.getIdCliente()!=idCliente)
				return true;
		}
		return false;
	}
	
}