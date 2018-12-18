package persistencia.dao.mysql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CampañaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.CampañaDAO;

public class CampañaDAOMYSQL implements CampañaDAO
{

	private static CampañaDAOMYSQL instance;
	private static final String insert = "INSERT INTO campaña (numero ,año, cierre) VALUES(?, ?, ?)";
	private static final String update = "UPDATE campaña SET numero=?, año=?, cierre=? WHERE idCampaña=?";
	private static final String delete = "DELETE FROM campaña WHERE idCampaña=?";
	private static final String readAll = "SELECT * FROM campaña";
	private static final String readForId = "SELECT * FROM campaña WHERE idCampaña=?";
	
	public static CampañaDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new CampañaDAOMYSQL();
		return instance;
	}

	@Override
	public boolean insert(CampañaDTO campaña) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, campaña.getNumero());
			statement.setString(2, campaña.getAño());
			Date FechaCierreSqlDate = new Date(campaña.getCierre().getTime());
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
	public boolean update(CampañaDTO campaña) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, campaña.getNumero());
			statement.setString(2, campaña.getAño());
			Date FechaCierreSqlDate = new Date(campaña.getCierre().getTime());
			statement.setDate(3, FechaCierreSqlDate);
			statement.setInt(4, campaña.getIdCampaña());
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
	public boolean delete(CampañaDTO campaña) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, campaña.getIdCampaña());
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
	public List<CampañaDTO> readAll() 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<CampañaDTO> campañas = new ArrayList<CampañaDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				CampañaDTO campaña = new CampañaDTO();
				campaña.setIdCampaña(resultSet.getInt("idCampaña"));
				campaña.setNumero(resultSet.getString("numero"));
				campaña.setAño(resultSet.getString("año"));
				campaña.setCierre(resultSet.getDate("cierre"));
				campaña.setCompras(CompraDAOMYSQL.getInstance().readForIdCampaña(resultSet.getInt("idCampaña")));
				campaña.setPremios(PremioDAOMYSQL.getInstance().readForIdCampaña(resultSet.getInt("idCampaña")));
				campañas.add(campaña);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return campañas;
	}

	@Override
	public CampañaDTO readForId(int idCampaña) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		CampañaDTO campaña = new CampañaDTO();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForId);
			statement.setInt(1, idCampaña);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				campaña.setIdCampaña(resultSet.getInt("idCampaña"));
				campaña.setNumero(resultSet.getString("numero"));
				campaña.setAño(resultSet.getString("año"));
				campaña.setCierre(resultSet.getDate("cierre"));
				campaña.setCompras(CompraDAOMYSQL.getInstance().readForIdCampaña(resultSet.getInt("idCampaña")));
				campaña.setPremios(PremioDAOMYSQL.getInstance().readForIdCampaña(resultSet.getInt("idCampaña")));
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return campaña;
	}		
	
}