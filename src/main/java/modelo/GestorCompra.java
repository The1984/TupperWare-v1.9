package modelo;

import java.util.List;

import dto.CompraDTO;
import dto.CompraPromocionDTO;
import dto.CompraPromocionProductoDTO;
import dto.ProductoDTO;
import persistencia.dao.mysql.CompraDAOMYSQL;
import persistencia.dao.mysql.CompraPromocionDAOMYSQL;
import persistencia.dao.mysql.CompraPromocionProductoDAOMYSQL;

public class GestorCompra 
{

	private static GestorCompra instance;
	private CompraDAOMYSQL compraDaoSQL;
	private CompraPromocionDAOMYSQL compraPromocionDaoSQL;
	private CompraPromocionProductoDAOMYSQL compraPromocionProductoDaoSQL;
	
	private GestorCompra() 
	{
		this.compraDaoSQL = CompraDAOMYSQL.getInstance();
		this.compraPromocionDaoSQL = CompraPromocionDAOMYSQL.getInstance();
		this.compraPromocionProductoDaoSQL = CompraPromocionProductoDAOMYSQL.getInstance();
	}

	public static GestorCompra getInstance() 
	{
		if ( instance == null )
			instance = new GestorCompra();
		return instance;
	}
	
	public void insert(CompraDTO compra) 
	{
		this.compraDaoSQL.insert(compra);
	}
	
	public void delete(CompraDTO compra)
	{
		this.compraDaoSQL.delete(compra);
	}
	
	public void update(CompraDTO compra)
	{
		this.compraDaoSQL.update(compra);
	}
	
	public List<CompraDTO> readAll()
	{
		return this.compraDaoSQL.readAll();
	}
	
	public CompraDTO readCompraForId(int idCompra)
	{
		return this.compraDaoSQL.readForId(idCompra);
	}
	
	public void insert(CompraPromocionDTO compraPromocion)
	{
		this.compraPromocionDaoSQL.insert(compraPromocion);
	}
	
	public void delete(CompraPromocionDTO compraPromocion)
	{
		this.compraPromocionDaoSQL.delete(compraPromocion);
	}
	
	public CompraPromocionDTO readCompraPromocionForId(int idCompraPromocion)
	{
		return this.compraPromocionDaoSQL.readForId(idCompraPromocion);
	}
	
	public int idUltimoInsert()
	{
		return this.compraPromocionDaoSQL.idUltimoInsert();
	}
	
	public void insert(CompraPromocionProductoDTO compraPromocionProducto)
	{
		this.compraPromocionProductoDaoSQL.insert(compraPromocionProducto);
	}
	
	public void delete(int idCompraPromocion, int idProducto)
	{
		this.compraPromocionProductoDaoSQL.delete(idCompraPromocion, idProducto);
	}
	
	public List<ProductoDTO> readProductosCompraPromocion(int idCompraPromocion)
	{
		return this.compraPromocionProductoDaoSQL.readProductos(idCompraPromocion);
	}
	
}