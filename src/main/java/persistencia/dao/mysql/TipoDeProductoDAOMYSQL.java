package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.TipoDeProductoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.TipoDeProductoDAO;

public class TipoDeProductoDAOMYSQL implements TipoDeProductoDAO
{
	private static TipoDeProductoDAOMYSQL instance;
	private static final String insert = "INSERT INTO tipoDeProducto (nombre, porcentajeDeGanancia) VALUES(?, ?)";
	private static final String update = "UPDATE tipoDeProducto SET nombre=?, porcentajeDeGanancia=? WHERE idTipoDeProducto=?";
	private static final String delete = "DELETE FROM tipoDeProducto WHERE idTipoDeProducto=?";
	private static final String readAll = "SELECT * FROM tipoDeProducto";
	private static final String readForId = "SELECT * FROM tipoDeProducto WHERE idTipoDeProducto=?";

	public static TipoDeProductoDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new TipoDeProductoDAOMYSQL();
		return instance;
	}
	
	@Override
	public boolean insert(TipoDeProductoDTO tipoDeProducto) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, tipoDeProducto.getNombre());
			statement.setInt(2, tipoDeProducto.getPorcentajeDeGanancia());
			if(statement.executeUpdate() > 0) { return true; }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(TipoDeProductoDTO tipoDeProducto) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, tipoDeProducto.getNombre());
			statement.setInt(2, tipoDeProducto.getPorcentajeDeGanancia());
			statement.setInt(3, tipoDeProducto.getIdTipoDeProducto());
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
	public boolean delete(TipoDeProductoDTO tipoDeProducto) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, tipoDeProducto.getIdTipoDeProducto());
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
	public List<TipoDeProductoDTO> readAll() 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<TipoDeProductoDTO> tiposDeProductos = new ArrayList<TipoDeProductoDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				TipoDeProductoDTO newTipoDeProducto = new TipoDeProductoDTO();
				newTipoDeProducto.setIdTipoDeProducto(resultSet.getInt("idTipoDeProducto"));				
				newTipoDeProducto.setNombre(resultSet.getString("nombre"));
				newTipoDeProducto.setPorcentajeDeGanancia(resultSet.getInt("porcentajeDeGanancia"));
				tiposDeProductos.add(newTipoDeProducto);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tiposDeProductos;
	}

	@Override
	public TipoDeProductoDTO readForId(int idTipoDeProducto) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		TipoDeProductoDTO tipoDeProducto = new TipoDeProductoDTO(); 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForId);
			statement.setInt(1, idTipoDeProducto);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				tipoDeProducto.setIdTipoDeProducto(resultSet.getInt("idTipoDeProducto"));
				tipoDeProducto.setNombre(resultSet.getString("nombre"));
				tipoDeProducto.setPorcentajeDeGanancia(resultSet.getInt("porcentajeDeGanancia"));
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return tipoDeProducto;
	}

}
