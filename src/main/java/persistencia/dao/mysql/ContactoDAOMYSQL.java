package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.ContactoDAO;

public class ContactoDAOMYSQL implements ContactoDAO
{

	private static ContactoDAOMYSQL instance;
	private static final String insert = "INSERT INTO contacto (nombre, apellido, direccion, celular, email) VALUES(?, ?, ?, ?, ?)";
	private static final String update = "UPDATE contacto SET nombre=?, apellido=?, direccion=?, celular=?, email=? WHERE idContacto=?";
	private static final String delete = "DELETE FROM contacto WHERE idContacto=?";
	private static final String readAll = "SELECT * FROM contacto";
	private static final String readForId = "SELECT * FROM contacto WHERE idContacto=?";
	
	public static ContactoDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new ContactoDAOMYSQL();
		return instance;
	}

	@Override
	public boolean insert(ContactoDTO contacto) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, contacto.getNombre());
			statement.setString(2, contacto.getApellido());
			statement.setString(3, contacto.getDireccion());
			statement.setString(4, contacto.getCelular());
			statement.setString(5, contacto.getEmail());
			if(statement.executeUpdate() > 0) { return true; }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(ContactoDTO contacto) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, contacto.getNombre());
			statement.setString(2, contacto.getApellido());
			statement.setString(3, contacto.getDireccion());
			statement.setString(4, contacto.getCelular());
			statement.setString(5, contacto.getEmail());
			statement.setInt(6, contacto.getIdContacto());
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
	public boolean delete(ContactoDTO contacto) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, contacto.getIdContacto());
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
	public List<ContactoDTO> readAll() 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<ContactoDTO> contactos = new ArrayList<ContactoDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				ContactoDTO contacto = new ContactoDTO();
				contacto.setIdContacto(resultSet.getInt("idContacto"));				
				contacto.setNombre(resultSet.getString("nombre"));				
				contacto.setApellido(resultSet.getString("apellido"));		
				contacto.setDireccion(resultSet.getString("direccion"));	
				contacto.setCelular(resultSet.getString("celular"));	
				contacto.setEmail(resultSet.getString("email"));	
				contacto.setTipoDeContacto(resultSet.getString("tipoDeContacto"));
				contactos.add(contacto);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return contactos;
	}

	@Override
	public ContactoDTO readForId(int idContacto) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		ContactoDTO contacto = new ContactoDTO();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForId);
			statement.setInt(1, idContacto);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				contacto.setIdContacto(resultSet.getInt("idContacto"));				
				contacto.setNombre(resultSet.getString("nombre"));				
				contacto.setApellido(resultSet.getString("apellido"));		
				contacto.setDireccion(resultSet.getString("direccion"));	
				contacto.setCelular(resultSet.getString("celular"));	
				contacto.setEmail(resultSet.getString("email"));	
				contacto.setTipoDeContacto(resultSet.getString("tipoDeContacto"));
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return contacto;
	}	
	
}
