package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.EstadoDeCompraDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.EstadoDeCompraDAO;

public class EstadoDeCompraDAOMYSQL implements EstadoDeCompraDAO
{

	private static EstadoDeCompraDAOMYSQL instance;
	private static final String insert = "INSERT INTO estadoDeCompra (nombre) VALUES(?, ?)";
	private static final String update = "UPDATE estadoDeCompra SET nombre=? WHERE idEstadoDeCompra=?";
	private static final String delete = "DELETE FROM estadoDeCompra WHERE idEstadoDeCompra=?";
	private static final String readAll = "SELECT * FROM estadoDeCompra";
	private static final String readForId = "SELECT * FROM estadoDeCompra WHERE idEstadoDeCompra=?";

	
	public static EstadoDeCompraDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new EstadoDeCompraDAOMYSQL();
		return instance;
	}

	@Override
	public boolean insert(EstadoDeCompraDTO estadoDeCompra) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, estadoDeCompra.getNombre());
			if(statement.executeUpdate() > 0) { return true; }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(EstadoDeCompraDTO estadoDeCompra) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, estadoDeCompra.getNombre());
			statement.setInt(2, estadoDeCompra.getIdEstadoDeCompra());
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
	public boolean delete(EstadoDeCompraDTO estadoDeCompra) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, estadoDeCompra.getIdEstadoDeCompra());
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
	public List<EstadoDeCompraDTO> readAll() 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<EstadoDeCompraDTO> estadoDeCompra = new ArrayList<EstadoDeCompraDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				EstadoDeCompraDTO newEstadoDeCompra = new EstadoDeCompraDTO();
				newEstadoDeCompra.setIdEstadoDeCompra(resultSet.getInt("idEstadoDeCompra"));				
				newEstadoDeCompra.setNombre(resultSet.getString("nombre"));
				estadoDeCompra.add(newEstadoDeCompra);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return estadoDeCompra;
	}

	@Override
	public EstadoDeCompraDTO readForId(int idEstadoDeCompra) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		EstadoDeCompraDTO estadoDeCompra = new EstadoDeCompraDTO(); 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForId);
			statement.setInt(1, idEstadoDeCompra);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				estadoDeCompra.setIdEstadoDeCompra(resultSet.getInt("idEstadoDeCompra"));
				estadoDeCompra.setNombre(resultSet.getString("nombre"));
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return estadoDeCompra;
	}	
	
}
