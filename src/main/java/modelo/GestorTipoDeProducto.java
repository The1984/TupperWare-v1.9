package modelo;

import java.util.List;

import dto.TipoDeProductoDTO;
import persistencia.dao.mysql.TipoDeProductoDAOMYSQL;

public class GestorTipoDeProducto 
{

	private static GestorTipoDeProducto instance;
	private TipoDeProductoDAOMYSQL tipoDeProductoDaoSQL;
	
	public static GestorTipoDeProducto getInstance() 
	{
		if ( instance == null )
			instance = new GestorTipoDeProducto();
		return instance;
	}
	
	private GestorTipoDeProducto() 
	{
		this.tipoDeProductoDaoSQL = TipoDeProductoDAOMYSQL.getInstance();
	}

	public void insert(TipoDeProductoDTO tipoDeProducto)
	{
		this.tipoDeProductoDaoSQL.insert(tipoDeProducto);
	}
	
	public void delete(TipoDeProductoDTO tipoDeProducto) 
	{
		this.tipoDeProductoDaoSQL.delete(tipoDeProducto);
	}
	
	public void update(TipoDeProductoDTO tipoDeProducto) 
	{
		this.tipoDeProductoDaoSQL.update(tipoDeProducto);
	}
	
	public List<TipoDeProductoDTO> readAll()
	{
		return this.tipoDeProductoDaoSQL.readAll();
	}
	
	public TipoDeProductoDTO readForId(int idTipoDeProducto)
	{
		return this.tipoDeProductoDaoSQL.readForId(idTipoDeProducto);
	}
	
}