package modelo;

import java.util.List;

import dto.EstadoDeCompraDTO;
import persistencia.dao.mysql.EstadoDeCompraDAOMYSQL;

public class GestorEstadoDeCompra 
{

	private static GestorEstadoDeCompra instance;
	private EstadoDeCompraDAOMYSQL estadoDeCompraDaoSQL;
	
	private GestorEstadoDeCompra() 
	{
		this.estadoDeCompraDaoSQL = EstadoDeCompraDAOMYSQL.getInstance();
	}

	public static GestorEstadoDeCompra getInstance() 
	{
		if ( instance == null )
			instance = new GestorEstadoDeCompra();
		return instance;
	}

	public void insert(EstadoDeCompraDTO estadoDeCompra) 
	{
		this.estadoDeCompraDaoSQL.insert(estadoDeCompra);
	}
	
	public void delete(EstadoDeCompraDTO estadoDeCompra)
	{
		this.estadoDeCompraDaoSQL.delete(estadoDeCompra);
	}
	
	public void update(EstadoDeCompraDTO estadoDeCompra)
	{
		this.estadoDeCompraDaoSQL.update(estadoDeCompra);
	}

	public List<EstadoDeCompraDTO> readAll() 
	{
		return this.estadoDeCompraDaoSQL.readAll();
	}
	
	public void readForId(int idEstadoDeCompra)
	{
		this.estadoDeCompraDaoSQL.readForId(idEstadoDeCompra);
	}
	
}
