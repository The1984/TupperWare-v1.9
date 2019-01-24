package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Campa�aDTO;
import dto.PromocionDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.PromocionDAO;

public class PromocionDAOMYSQL implements PromocionDAO
{
	
	private static PromocionDAOMYSQL instance;
	private static final String insert = "INSERT INTO promocion (nombre, descripcion, pagina, precio, idCampa�a) VALUES(?, ?, ?, ?, ?)";
	private static final String update = "UPDATE promocion SET nombre=?, descripcion=?, pagina=?, precio=? WHERE idPromocion=?";
	private static final String delete = "DELETE FROM promocion WHERE idPromocion=?";
	private static final String readAll = "SELECT * FROM promocion";
	private static final String readForCampa�a = "SELECT * FROM promocion WHERE idCampa�a=?";
	private static final String readForId = "SELECT * FROM promocion WHERE idPromocion=?";
	private static final String readForCampa�aTitulo = "SELECT * FROM promocion WHERE idCampa�a=? and nombre=?";
	private static final String idUltimoInsert = "SELECT @@IDENTITY as idUltimo";
	
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
			statement.setString(1, promocion.getNombre());
			statement.setString(2, promocion.getDescripcion());
			statement.setString(3, promocion.getPagina());
			statement.setInt(4, promocion.getPrecio());
			statement.setInt(5, promocion.getCampa�a().getIdCampa�a());
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
			statement.setString(1, promocion.getNombre());
			statement.setString(2, promocion.getDescripcion());
			statement.setString(3, promocion.getPagina());
			statement.setInt(4, promocion.getPrecio());
			statement.setInt(5, promocion.getIdPromocion());
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
	public List<PromocionDTO> readAll() 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PromocionDTO> promociones = new ArrayList<PromocionDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();			
			while(resultSet.next())
			{
				PromocionDTO promocion = new PromocionDTO();
				promocion.setIdPromocion(resultSet.getInt("idPromocion"));
				promocion.setNombre(resultSet.getString("nombre"));
				promocion.setDescripcion(resultSet.getString("descripcion"));
				promocion.setPagina(resultSet.getString("pagina"));
				promocion.setPrecio(resultSet.getInt("precio"));
				promocion.setCampa�a(null);
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
	public List<PromocionDTO> readForCampa�a(Campa�aDTO campa�a) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PromocionDTO> promociones = new ArrayList<PromocionDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForCampa�a);
			statement.setInt(1, campa�a.getIdCampa�a());
			resultSet = statement.executeQuery();			
			while(resultSet.next())
			{
				PromocionDTO promocion = new PromocionDTO();
				promocion.setIdPromocion(resultSet.getInt("idPromocion"));
				promocion.setNombre(resultSet.getString("nombre"));
				promocion.setDescripcion(resultSet.getString("descripcion"));
				promocion.setPagina(resultSet.getString("pagina"));
				promocion.setPrecio(resultSet.getInt("precio"));
				promocion.setCampa�a(null);
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
				promocion.setNombre(resultSet.getString("nombre"));
				promocion.setDescripcion(resultSet.getString("descripcion"));
				promocion.setPagina(resultSet.getString("pagina"));
				promocion.setPrecio(resultSet.getInt("precio"));
				promocion.setCampa�a(Campa�aDAOMYSQL.getInstance().readForId(resultSet.getInt("idCampa�a")));
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
	public PromocionDTO readForCampa�aNombre(int idCampa�a, String nombre) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		PromocionDTO promocion = new PromocionDTO(); 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForCampa�aTitulo);
			statement.setInt(1, idCampa�a);
			statement.setString(2, nombre);			
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				promocion.setIdPromocion(resultSet.getInt("idPromocion"));
				promocion.setNombre(resultSet.getString("nombre"));
				promocion.setDescripcion(resultSet.getString("descripcion"));
				promocion.setPagina(resultSet.getString("pagina"));
				promocion.setPrecio(resultSet.getInt("precio"));
				promocion.setCampa�a(Campa�aDAOMYSQL.getInstance().readForId(resultSet.getInt("idCampa�a")));
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