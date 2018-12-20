package persistencia.dao.mysql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Campa�aDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.Campa�aDAO;

public class Campa�aDAOMYSQL implements Campa�aDAO
{

	private static Campa�aDAOMYSQL instance;
	private static final String insert = "INSERT INTO campa�a (numero ,a�o, cierre) VALUES(?, ?, ?)";
	private static final String update = "UPDATE campa�a SET numero=?, a�o=?, cierre=? WHERE idCampa�a=?";
	private static final String delete = "DELETE FROM campa�a WHERE idCampa�a=?";
	private static final String readAll = "SELECT * FROM campa�a";
	private static final String readForId = "SELECT * FROM campa�a WHERE idCampa�a=?";
	
	public static Campa�aDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new Campa�aDAOMYSQL();
		return instance;
	}

	@Override
	public boolean insert(Campa�aDTO campa�a) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, campa�a.getNumero());
			statement.setString(2, campa�a.getA�o());
			Date FechaCierreSqlDate = new Date(campa�a.getCierre().getTime());
			statement.setDate(3, FechaCierreSqlDate);
			if(statement.executeUpdate() > 0) { return true; }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(Campa�aDTO campa�a) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, campa�a.getNumero());
			statement.setString(2, campa�a.getA�o());
			Date FechaCierreSqlDate = new Date(campa�a.getCierre().getTime());
			statement.setDate(3, FechaCierreSqlDate);
			statement.setInt(4, campa�a.getIdCampa�a());
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
	public boolean delete(Campa�aDTO campa�a) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, campa�a.getIdCampa�a());
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
	public List<Campa�aDTO> readAll() 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<Campa�aDTO> campa�as = new ArrayList<Campa�aDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Campa�aDTO campa�a = new Campa�aDTO();
				campa�a.setIdCampa�a(resultSet.getInt("idCampa�a"));
				campa�a.setNumero(resultSet.getString("numero"));
				campa�a.setA�o(resultSet.getString("a�o"));
				campa�a.setCierre(resultSet.getDate("cierre"));
				campa�a.setCompras(CompraDAOMYSQL.getInstance().readForIdCampa�a(resultSet.getInt("idCampa�a")));
				campa�a.setPremios(PremioDAOMYSQL.getInstance().readForIdCampa�a(resultSet.getInt("idCampa�a")));
				campa�as.add(campa�a);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return campa�as;
	}

	@Override
	public Campa�aDTO readForId(int idCampa�a) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		Campa�aDTO campa�a = new Campa�aDTO();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForId);
			statement.setInt(1, idCampa�a);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				campa�a.setIdCampa�a(resultSet.getInt("idCampa�a"));
				campa�a.setNumero(resultSet.getString("numero"));
				campa�a.setA�o(resultSet.getString("a�o"));
				campa�a.setCierre(resultSet.getDate("cierre"));
				campa�a.setCompras(CompraDAOMYSQL.getInstance().readForIdCampa�a(resultSet.getInt("idCampa�a")));
				campa�a.setPremios(PremioDAOMYSQL.getInstance().readForIdCampa�a(resultSet.getInt("idCampa�a")));
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return campa�a;
	}		
	
}