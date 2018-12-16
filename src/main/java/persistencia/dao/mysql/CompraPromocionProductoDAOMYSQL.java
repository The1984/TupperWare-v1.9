package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CompraPromocionProductoDTO;
import dto.ProductoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.CompraPromocionProductoDAO;

public class CompraPromocionProductoDAOMYSQL implements CompraPromocionProductoDAO
{

	private static CompraPromocionProductoDAOMYSQL instance;
	private static final String insert = "INSERT INTO compraPromocionProducto (idCompraPromocion, idProducto) VALUES(?, ?)";
	private static final String delete = "DELETE FROM compraPromocionProducto WHERE idCompraPromocion=? and idProducto=?";
	private static final String readProductos = "SELECT * FROM producto p, compraPromocionProducto pp WHERE p.idProducto=pp.idProducto and pp.idCompraPromocion=?";
	
	public static CompraPromocionProductoDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new CompraPromocionProductoDAOMYSQL();
		return instance;
	}

	@Override
	public boolean insert(CompraPromocionProductoDTO compraPromocionProducto) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, compraPromocionProducto.getIdCompraPromocionProducto());
			statement.setInt(2, compraPromocionProducto.getIdProducto());
			if(statement.executeUpdate() > 0) { return true; }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int idCompraPromocion, int idProducto) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, idCompraPromocion);
			statement.setInt(2, idProducto);
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) 
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<ProductoDTO> readProductos(int idCompraPromocion) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		ArrayList<ProductoDTO> productos = new ArrayList<ProductoDTO>(); 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readProductos);
			statement.setInt(1, idCompraPromocion);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				ProductoDTO producto = new ProductoDTO();
				producto.setIdProducto(resultSet.getInt("idProducto"));
				producto.setCodigo(resultSet.getString("codigo"));				
				producto.setNombre(resultSet.getString("nombre"));
				producto.setDescripcion(resultSet.getString("descripcion"));
				producto.setTipoDeProducto(TipoDeProductoDAOMYSQL.getInstance().readForId(resultSet.getInt("idTipoDeProducto")));
				productos.add(producto);
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return productos;
	}

}
