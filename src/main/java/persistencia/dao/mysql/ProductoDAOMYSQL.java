package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ProductoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.ProductoDAO;

public class ProductoDAOMYSQL implements ProductoDAO
{

	private static ProductoDAOMYSQL instance;
	private static final String insert = "INSERT INTO producto (codigo, nombre, descripcion, idTipoDeProducto) VALUES(?, ?, ?, ?)";
	private static final String update = "UPDATE producto SET codigo=?, nombre=?, descripcion=?, idTipoDeProducto=? WHERE idProducto=?";
	private static final String delete = "DELETE FROM producto WHERE idProducto=?";
	private static final String readAll = "SELECT * FROM producto";
	private static final String readForId = "SELECT * FROM producto WHERE idProducto=?";
	private static final String idUltimoInsert = "SELECT @@IDENTITY as idUltimo";
	
	public static ProductoDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new ProductoDAOMYSQL();
		return instance;
	}

	@Override
	public boolean insert(ProductoDTO producto) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, producto.getCodigo());
			statement.setString(2, producto.getNombre());
			statement.setString(3, producto.getDescripcion());
			statement.setInt(4, producto.getTipoDeProducto().getIdTipoDeProducto());
			if(statement.executeUpdate() > 0) { return true; }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(ProductoDTO producto) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, producto.getCodigo());
			statement.setString(2, producto.getNombre());
			statement.setString(3, producto.getDescripcion());
			statement.setInt(4, producto.getTipoDeProducto().getIdTipoDeProducto());
			statement.setInt(5, producto.getIdProducto());
			if(statement.executeUpdate() > 0)
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(ProductoDTO producto) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, producto.getIdProducto());
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
	public List<ProductoDTO> readAll() 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ProductoDTO> productos = new ArrayList<ProductoDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				ProductoDTO newProducto = new ProductoDTO();
				newProducto.setIdProducto(resultSet.getInt("idProducto"));				
				newProducto.setCodigo(resultSet.getString("codigo"));				
				newProducto.setNombre(resultSet.getString("nombre"));
				newProducto.setDescripcion(resultSet.getString("descripcion"));
				newProducto.setTipoDeProducto(TipoDeProductoDAOMYSQL.getInstance().readForId(resultSet.getInt("idTipoDeProducto")));
				productos.add(newProducto);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return productos;
	}

	@Override
	public ProductoDTO readForId(int idProducto) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		ProductoDTO producto = new ProductoDTO();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForId);
			statement.setInt(1, idProducto);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				producto.setIdProducto(resultSet.getInt("idProducto"));
				producto.setCodigo(resultSet.getString("codigo"));
				producto.setNombre(resultSet.getString("nombre"));
				producto.setDescripcion(resultSet.getString("descripcion"));
				producto.setTipoDeProducto(TipoDeProductoDAOMYSQL.getInstance().readForId(resultSet.getInt("idTipoDeProducto")));
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return producto;
	}

	@Override
	public int idUltimoInsert() 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(idUltimoInsert);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
			    return resultSet.getInt("idUltimo");
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return 0;
	}	
	
}