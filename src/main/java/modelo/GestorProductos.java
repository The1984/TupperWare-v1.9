package modelo;

import java.util.List;

import dto.ImagenDeProductoDTO;
import dto.ProductoDTO;
import persistencia.dao.mysql.ImagenDeProductoDAOMYSQL;
import persistencia.dao.mysql.ProductoDAOMYSQL;

public class GestorProductos 
{
	
	private static GestorProductos instance;
	private ProductoDAOMYSQL productoDaoSQL;
	private ImagenDeProductoDAOMYSQL imagenDeProductoDaoSQL;
	
	public static GestorProductos getInstance() 
	{
		if ( instance == null )
			instance = new GestorProductos();
		return instance;
	}
	
	private GestorProductos() 
	{
		this.productoDaoSQL = ProductoDAOMYSQL.getInstance();
		this.imagenDeProductoDaoSQL = ImagenDeProductoDAOMYSQL.getInstance(); 
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
	
	public int idUltimoInsert()
	{
		return this.productoDaoSQL.idUltimoInsert();
	}
	
	public boolean insert (String rutaImagen, int idProducto)
	{
		return this.imagenDeProductoDaoSQL.insert(rutaImagen, idProducto);
	}

	public boolean update (String rutaImagen, int idProducto) 
	{
		return this.imagenDeProductoDaoSQL.update(rutaImagen, idProducto);
	}
	
	public boolean delete (int idProducto)
	{
		return this.imagenDeProductoDaoSQL.delete(idProducto);
	}
	
	public ImagenDeProductoDTO readForIdProducto (int idProducto)
	{
		return this.imagenDeProductoDaoSQL.readForIdProducto(idProducto);
	}
	
}