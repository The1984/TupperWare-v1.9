package modelo;

import java.util.List;

import dto.ProductoDTO;
import persistencia.dao.mysql.ProductoDAOMYSQL;

public class GestorProductos 
{
	
	private static GestorProductos instance;
	private ProductoDAOMYSQL productoDaoSQL;
	
	public static GestorProductos getInstance() 
	{
		if ( instance == null )
			instance = new GestorProductos();
		return instance;
	}
	
	private GestorProductos() 
	{
		this.productoDaoSQL = ProductoDAOMYSQL.getInstance();
	}

	public void insert(ProductoDTO producto)
	{
		this.productoDaoSQL.insert(producto);
	}
	
	public void delete(ProductoDTO producto) 
	{
		this.productoDaoSQL.delete(producto);
	}
	
	public void update(ProductoDTO producto) 
	{
		this.productoDaoSQL.update(producto);
	}
	
	public List<ProductoDTO> readAll()
	{
		return this.productoDaoSQL.readAll();
	}
	
	public ProductoDTO readForId(int idProducto)
	{
		return this.productoDaoSQL.readForId(idProducto);
	}
	
}
