package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProductoDTO;
import dto.PromocionProductoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.PromocionProductoDAO;

public class PromocionProductoDAOMYSQL implements PromocionProductoDAO
{

	private static PromocionProductoDAOMYSQL instance;
	private static final String insert = "INSERT INTO promocionProducto (idPromocion, idProducto) VALUES(?, ?)";
	private static final String delete = "DELETE FROM promocionProducto WHERE idPromocion=? and idProducto=?";
	private static final String readProductos = "SELECT * FROM producto p, promocionProducto pp WHERE p.idProducto=pp.idProducto and pp.idPromocion=?";
	
	public static PromocionProductoDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new PromocionProductoDAOMYSQL();
		return instance;
	}

	@Override
	public boolean insert(PromocionProductoDTO promocionProducto) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, promocionProducto.getIdPromocion());
			statement.setInt(2, promocionProducto.getIdProducto());
			if(statement.executeUpdate() > 0) { return true; }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(int idPromocion, int idProducto) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, idPromocion);
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
	public List<ProductoDTO> readProductos(int idPromocion) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		ArrayList<ProductoDTO> productos = new ArrayList<ProductoDTO>(); 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readProductos);
			statement.setInt(1, idPromocion);
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
