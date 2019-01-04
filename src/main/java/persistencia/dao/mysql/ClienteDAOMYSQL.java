package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ClienteDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.ClienteDAO;

public class ClienteDAOMYSQL implements ClienteDAO
{
	
	private static ClienteDAOMYSQL instance;
	private static final String insert = "INSERT INTO cliente (nombre, apellido, direccion, celular, email) VALUES(?, ?, ?, ?, ?)";
	private static final String update = "UPDATE cliente SET nombre=?, apellido=?, direccion=?, celular=?, email=? WHERE idCliente=?";
	private static final String delete = "DELETE FROM cliente WHERE idCliente=?";
	private static final String readAll = "SELECT * FROM cliente";
	private static final String readForId = "SELECT * FROM cliente WHERE idCliente=?";
	
	public static ClienteDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new ClienteDAOMYSQL();
		return instance;
	}
	
	@Override
	public boolean insert(ClienteDTO cliente) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, cliente.getNombre());
			statement.setString(2, cliente.getApellido());
			statement.setString(3, cliente.getDireccion());
			statement.setString(4, cliente.getCelular());
			statement.setString(5, cliente.getEmail());
			if(statement.executeUpdate() > 0) { return true; }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(ClienteDTO cliente) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, cliente.getNombre());
			statement.setString(2, cliente.getApellido());
			statement.setString(3, cliente.getDireccion());
			statement.setString(4, cliente.getCelular());
			statement.setString(5, cliente.getEmail());
			statement.setInt(6, cliente.getIdCliente());
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
	public boolean delete(ClienteDTO cliente) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, cliente.getIdCliente());
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
	public List<ClienteDTO> readAll() 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ClienteDTO> clientes = new ArrayList<ClienteDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();			
			while(resultSet.next())
			{
				ClienteDTO newCliente = new ClienteDTO();
				newCliente.setIdCliente(resultSet.getInt("idCliente"));				
				newCliente.setNombre(resultSet.getString("nombre"));
				newCliente.setApellido(resultSet.getString("apellido"));
				newCliente.setDireccion(resultSet.getString("direccion"));
				newCliente.setCelular(resultSet.getString("celular"));
				newCliente.setEmail(resultSet.getString("email"));
				clientes.add(newCliente);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return clientes;
	}

	@Override
	public ClienteDTO readForId(int idCliente) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		ClienteDTO cliente = new ClienteDTO(); 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForId);
			statement.setInt(1, idCliente);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				cliente.setIdCliente(resultSet.getInt("idCliente"));				
				cliente.setNombre(resultSet.getString("nombre"));
				cliente.setApellido(resultSet.getString("apellido"));
				cliente.setDireccion(resultSet.getString("direccion"));
				cliente.setCelular(resultSet.getString("celular"));
				cliente.setEmail(resultSet.getString("email"));
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return cliente;
	}

}
