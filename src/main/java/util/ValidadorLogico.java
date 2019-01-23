package util;

import java.util.List;

import dto.Campa�aDTO;
import dto.ClienteDTO;
import dto.ProductoDTO;
import dto.PromocionDTO;

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
	
	public static boolean existeProductoNombreAgregar(String nombre, List<ProductoDTO> productos)
	{
		for(ProductoDTO producto : productos)
		{
			if(producto.getNombre().toLowerCase().equals(nombre.toLowerCase()))
				return true;
		}
		return false;
	}

	public static boolean existeProductoCodigoAgregar(String codigo, List<ProductoDTO> productos)
	{
		for(ProductoDTO producto : productos)
		{
			if(producto.getCodigo().toLowerCase().equals(codigo.toLowerCase()))
				return true;
		}
		return false;
	}
	
	public static boolean existeProductoNombreEditar(int idProducto, String nombre, List<ProductoDTO> productos)
	{
		for(ProductoDTO producto : productos)
		{
			if(producto.getNombre().equals(nombre) &&
			   producto.getIdProducto()!=idProducto)
				return true;
		}
		return false;
	}
	
	public static boolean existeProductoCodigoEditar(int idProducto, String codigo, List<ProductoDTO> productos)
	{
		for(ProductoDTO producto : productos)
		{
			if(producto.getCodigo().equals(codigo) &&
			   producto.getIdProducto()!=idProducto)
				return true;
		}
		return false;
	}
	
	public static boolean existeA�oNumeroCampa�aAgregar(String a�o, String numero, List<Campa�aDTO> campa�as)
	{
		for(Campa�aDTO campa�a : campa�as)
		{
			if(campa�a.getA�o().toLowerCase().equals(a�o.toLowerCase()) && 
			   campa�a.getNumero().toLowerCase().equals(numero.toLowerCase()))
				return true;
		}
		return false;
	}
	
	public static boolean existeA�oNumeroCampa�aEditar(int idCampa�a, String a�o, String numero, List<Campa�aDTO> campa�as)
	{
		for(Campa�aDTO campa�a : campa�as)
		{
			if(campa�a.getA�o().toLowerCase().equals(a�o.toLowerCase()) && 
			   campa�a.getNumero().toLowerCase().equals(numero.toLowerCase())&&
			   campa�a.getIdCampa�a()!=idCampa�a)
				return true;
		}
		return false;
	}
	
	public static boolean existePromocionNombreAgregar(String nombre, List<PromocionDTO> promociones)
	{
		for(PromocionDTO promocion : promociones)
		{
			if(promocion.getNombre().toLowerCase().equals(nombre.toLowerCase()))
				return true;
		}
		return false;
	}

	public static boolean existePromocionNombreEditar(int idPromocion, String nombre, List<PromocionDTO> promociones)
	{
		for(PromocionDTO promocion : promociones)
		{
			if(promocion.getNombre().toLowerCase().equals(nombre.toLowerCase())&&
			   promocion.getIdPromocion()!=idPromocion)
				return true;
		}
		return false;
	}
	
}