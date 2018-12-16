package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.CompraPromocionDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.CompraPromocionDAO;

public class CompraPromocionDAOMYSQL implements CompraPromocionDAO
{

	private static CompraPromocionDAOMYSQL instance;
	private static final String insert = "INSERT INTO compraPromocion (idPromocion) VALUES(?)";
	private static final String delete = "DELETE FROM compraPromocion WHERE idCompraPromocion=?";
	private static final String readForId = "Select * FROM compraPromocion WHERE idCompraPromocion=?";

	public static CompraPromocionDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new CompraPromocionDAOMYSQL();
		return instance;
	}

	@Override
	public boolean insert(CompraPromocionDTO compraPromocion) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, compraPromocion.getPromocion().getIdPromocion());
			if(statement.executeUpdate() > 0) { return true; }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(CompraPromocionDTO compraPromocion) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, compraPromocion.getIdCompraPromocion());
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
	public CompraPromocionDTO readForId(int idCompraPromocion) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		CompraPromocionDTO compraPromocion = new CompraPromocionDTO(); 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForId);
			statement.setInt(1, idCompraPromocion);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				compraPromocion.setIdCompraPromocion(resultSet.getInt("idCompraPromocion"));
				compraPromocion.setPromocion(PromocionDAOMYSQL.getInstance().readForId(resultSet.getInt("idPromocion")));
				compraPromocion.setProductos(CompraPromocionProductoDAOMYSQL.getInstance().readProductos(resultSet.getInt("idCompraPromocion")));
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return compraPromocion;
	}	
	
}
