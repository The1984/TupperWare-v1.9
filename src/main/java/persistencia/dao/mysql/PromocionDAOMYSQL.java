package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CampañaDTO;
import dto.PromocionDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.PromocionDAO;

public class PromocionDAOMYSQL implements PromocionDAO
{
	
	private static PromocionDAOMYSQL instance;
	private static final String insert = "INSERT INTO promocion (titulo, descripcion, idCampaña) VALUES(?, ?, ?)";
	private static final String update = "UPDATE promocion SET titulo=?, descripcion=?, idCampaña=? WHERE idPromocion=?";
	private static final String delete = "DELETE FROM promocion WHERE idPromocion=?";
	private static final String readAll = "SELECT * FROM promocion WHERE idCampaña=?";
	private static final String readForId = "SELECT * FROM promocion WHERE idPromocion=?";
	private static final String readForCampañaTitulo = "SELECT * FROM promocion WHERE idCampaña=? and titulo=?";
	
	public static PromocionDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new PromocionDAOMYSQL();
		return instance;
	}

	@Override
	public boolean insert(PromocionDTO promocion) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, promocion.getTitulo());
			statement.setString(2, promocion.getDescripcion());
			statement.setInt(3, promocion.getCampaña().getIdCampaña());
			if(statement.executeUpdate() > 0) { return true; }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(PromocionDTO promocion) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, promocion.getTitulo());
			statement.setString(2, promocion.getDescripcion());
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
	public boolean delete(PromocionDTO promocion) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, promocion.getIdPromocion());
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
	public List<PromocionDTO> readForCampaña(CampañaDTO campaña) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PromocionDTO> promociones = new ArrayList<PromocionDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			statement.setInt(1, campaña.getIdCampaña());
			resultSet = statement.executeQuery();			
			while(resultSet.next())
			{
				PromocionDTO promocion = new PromocionDTO();
				promocion.setIdPromocion(resultSet.getInt("idPromocion"));
				promocion.setTitulo(resultSet.getString("titulo"));
				promocion.setDescripcion(resultSet.getString("descripcion"));
				promocion.setCampaña(CampañaDAOMYSQL.getInstance().readForId(resultSet.getInt("idCampaña")));
				promocion.setProductos(PromocionProductoDAOMYSQL.getInstance().readProductos(resultSet.getInt("idPromocion")));
				promociones.add(promocion);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return promociones;
	}

	@Override
	public PromocionDTO readForId(int idPromocion) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		PromocionDTO promocion = new PromocionDTO(); 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForId);
			statement.setInt(1, idPromocion);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				promocion.setIdPromocion(resultSet.getInt("idPromocion"));
				promocion.setTitulo(resultSet.getString("titulo"));
				promocion.setDescripcion(resultSet.getString("descripcion"));
				promocion.setCampaña(CampañaDAOMYSQL.getInstance().readForId(resultSet.getInt("idCampaña")));
				promocion.setProductos(PromocionProductoDAOMYSQL.getInstance().readProductos(resultSet.getInt("idPromocion")));
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return promocion;
	}

	@Override
	public PromocionDTO readForCampañaTitulo(int idCampaña, String titulo) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		PromocionDTO promocion = new PromocionDTO(); 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForCampañaTitulo);
			statement.setInt(1, idCampaña);
			statement.setString(2, titulo);			
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				promocion.setIdPromocion(resultSet.getInt("idPromocion"));
				promocion.setTitulo(resultSet.getString("titulo"));
				promocion.setDescripcion(resultSet.getString("descripcion"));
				promocion.setCampaña(CampañaDAOMYSQL.getInstance().readForId(resultSet.getInt("idCampaña")));
				promocion.setProductos(PromocionProductoDAOMYSQL.getInstance().readProductos(resultSet.getInt("idPromocion")));
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return promocion;
	}
	
}
