package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.PremioDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.PremioDAO;

public class PremioDAOMYSQL implements PremioDAO 
{

	private static PremioDAOMYSQL instance;
	private static final String insert = "INSERT INTO premio (nombre, descripcion, unidadesMinimas, recibido, idCampaña) VALUES(?, ?, ?, ?, ?)";
	private static final String update = "UPDATE premio SET nombre=?, descripcion=?, unidadesMinimas=?, recibido=? WHERE idPremio=?";
	private static final String delete = "DELETE FROM premio WHERE idPremio=?";
	private static final String readForIdCampaña = "SELECT * FROM premio WHERE idCampaña=?";
	private static final String readForId = "SELECT * FROM premio WHERE idPremio=?";
	
	public static PremioDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new PremioDAOMYSQL();
		return instance;
	}

	@Override
	public boolean insert(PremioDTO premio) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, premio.getNombre());
			statement.setString(2, premio.getDescripcion());
			statement.setInt(3, premio.getUnidadesMinimas());
			statement.setBoolean(4, premio.getRecibido());
			statement.setInt(5, premio.getIdCampaña());
			if(statement.executeUpdate() > 0) { return true; }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(PremioDTO premio) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, premio.getNombre());
			statement.setString(2, premio.getDescripcion());
			statement.setInt(3, premio.getUnidadesMinimas());
			statement.setBoolean(4, premio.getRecibido());
			statement.setInt(5, premio.getIdPremio());
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
	public boolean delete(PremioDTO premio) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, premio.getIdPremio());
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
	public List<PremioDTO> readForIdCampaña(int idCampaña) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PremioDTO> premios = new ArrayList<PremioDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForIdCampaña);
			statement.setInt(1, idCampaña);
			resultSet = statement.executeQuery();			
			while(resultSet.next())
			{
				PremioDTO newPremio = new PremioDTO();
				newPremio.setIdPremio(resultSet.getInt("idPremio"));
				newPremio.setNombre(resultSet.getString("nombre"));
				newPremio.setDescripcion(resultSet.getString("descripcion"));
				newPremio.setUnidadesMinimas(resultSet.getInt("unidadesMinimas"));
				newPremio.setRecibido(resultSet.getBoolean("recibido"));
				newPremio.setIdCampaña(resultSet.getInt("idCampaña"));
				premios.add(newPremio);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return premios;
	}

	@Override
	public PremioDTO readForId(int idPremio) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		PremioDTO premio = new PremioDTO(); 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForId);
			statement.setInt(1, idPremio);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				premio.setIdPremio(resultSet.getInt("idPremio"));
				premio.setNombre(resultSet.getString("nombre"));
				premio.setDescripcion(resultSet.getString("descripcion"));
				premio.setUnidadesMinimas(resultSet.getInt("unidadesMinimas"));
				premio.setRecibido(resultSet.getBoolean("recibido"));
				premio.setIdCampaña(resultSet.getInt("idCampaña"));
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return premio;
	}
	
}