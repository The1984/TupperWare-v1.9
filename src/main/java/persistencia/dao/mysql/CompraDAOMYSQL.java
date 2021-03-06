package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.CompraDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.CompraDAO;

public class CompraDAOMYSQL implements CompraDAO
{

	private static CompraDAOMYSQL instance;
	private static final String insert = "INSERT INTO compra (pagina, precio, unidades, montoPagado, porcentajeDeGanancia, idCliente, idProducto, idCompraPromocion, idCampa�a, idEstadoDeCompra) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String update = "UPDATE compra SET pagina=?, precio=?, unidades=?, montoPagado=?, porcentajeDeGanancia=?, idEstadoDeCompra=? WHERE idCompra=?";
	private static final String delete = "DELETE FROM compra WHERE idCompra=?";
	private static final String readAll = "SELECT * FROM compra";
	private static final String readForId = "SELECT * FROM compra WHERE idCompra=?";
	private static final String readForIdCampa�a = "SELECT * FROM compra WHERE idCampa�a=?";
	private static final String readForIdCliente = "SELECT * FROM compra WHERE idCliente=?";
	
	public static CompraDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new CompraDAOMYSQL();
		return instance;
	}	
	
	@Override
	public boolean insert(CompraDTO compra) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, compra.getPagina());
			statement.setInt(2, compra.getPrecio());
			statement.setInt(3, compra.getUnidades());
			statement.setInt(4, compra.getMontoPagado());
			statement.setInt(5, compra.getPorcentajeDeGanancia());
			statement.setInt(6, compra.getCliente().getIdCliente());
			if(compra.getProducto() != null) 
			{ 
				statement.setInt(7, compra.getProducto().getIdProducto());
			}
			else 
			{ 
				statement.setObject(7, null);
			}
			if(compra.getCompraPromocion() != null) 
			{ 
				statement.setInt(8, compra.getCompraPromocion().getIdCompraPromocion()); 
			}
			else 
			{ 
				statement.setObject(8, null);
			}
			statement.setInt(9, compra.getCampa�a().getIdCampa�a());
			statement.setInt(10, compra.getEstadoDeCompra().getIdEstadoDeCompra());
			if(statement.executeUpdate() > 0) { return true; }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(CompraDTO compra) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, compra.getPagina());
			statement.setInt(2, compra.getPrecio());
			statement.setInt(3, compra.getUnidades());
			statement.setInt(4, compra.getMontoPagado());
			statement.setInt(5, compra.getPorcentajeDeGanancia());
			statement.setInt(6, compra.getEstadoDeCompra().getIdEstadoDeCompra());
			statement.setInt(7, compra.getIdCompra());
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
	public boolean delete(CompraDTO compra) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, compra.getIdCompra());
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
	public List<CompraDTO> readAll() 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<CompraDTO> compras = new ArrayList<CompraDTO>();
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readAll);
			resultSet = statement.executeQuery();			
			while(resultSet.next())
			{
				CompraDTO compra = new CompraDTO();
				compra.setIdCompra(resultSet.getInt("idCompra"));
				compra.setPagina(resultSet.getString("pagina"));
				compra.setPrecio(resultSet.getInt("precio"));
				compra.setUnidades(resultSet.getInt("unidades"));
				compra.setMontoPagado(resultSet.getInt("montoPagado"));
				compra.setPorcentajeDeGanancia(resultSet.getInt("porcentajeDeGanancia"));
				compra.setCliente(ClienteDAOMYSQL.getInstance().readForId(resultSet.getInt("idCliente")));
				compra.setCampa�a(Campa�aDAOMYSQL.getInstance().readForId(resultSet.getInt("idCampa�a")));
				compra.setEstadoDeCompra(EstadoDeCompraDAOMYSQL.getInstance().readForId(resultSet.getInt("idEstadoDeCompra")));
				compra.setProducto(ProductoDAOMYSQL.getInstance().readForId(resultSet.getInt("idProducto")));
				compra.setCompraPromocion(CompraPromocionDAOMYSQL.getInstance().readForId(resultSet.getInt("idCompraPromocion")));
				compras.add(compra);
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return compras;
	}


	@Override
	public CompraDTO readForId(int idCompra) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		CompraDTO compra = new CompraDTO(); 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForId);
			statement.setInt(1, idCompra);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				compra.setIdCompra(resultSet.getInt("idCompra"));
				compra.setPagina(resultSet.getString("pagina"));
				compra.setPrecio(resultSet.getInt("precio"));
				compra.setUnidades(resultSet.getInt("unidades"));
				compra.setMontoPagado(resultSet.getInt("montoPagado"));
				compra.setPorcentajeDeGanancia(resultSet.getInt("porcentajeDeGanancia"));
				compra.setCliente(ClienteDAOMYSQL.getInstance().readForId(resultSet.getInt("idCliente")));
				compra.setCampa�a(Campa�aDAOMYSQL.getInstance().readForId(resultSet.getInt("idCampa�a")));
				compra.setEstadoDeCompra(EstadoDeCompraDAOMYSQL.getInstance().readForId(resultSet.getInt("idEstadoDeCompra")));
				compra.setProducto(ProductoDAOMYSQL.getInstance().readForId(resultSet.getInt("idProducto")));
				compra.setCompraPromocion(CompraPromocionDAOMYSQL.getInstance().readForId(resultSet.getInt("idCompraPromocion")));
				return compra;
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return compra;
	}
	
	@Override
	public List<CompraDTO> readForIdCampa�a(int idCampa�a) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		ArrayList<CompraDTO> compraDeCampa�a = new ArrayList<CompraDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForIdCampa�a);
			statement.setInt(1, idCampa�a);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				CompraDTO compra = new CompraDTO();
				compra.setIdCompra(resultSet.getInt("idCompra"));
				compra.setPagina(resultSet.getString("pagina"));
				compra.setPrecio(resultSet.getInt("precio"));
				compra.setUnidades(resultSet.getInt("unidades"));
				compra.setMontoPagado(resultSet.getInt("montoPagado"));
				compra.setPorcentajeDeGanancia(resultSet.getInt("porcentajeDeGanancia"));
				compra.setCliente(ClienteDAOMYSQL.getInstance().readForId(resultSet.getInt("idCliente")));
				compra.setCampa�a(Campa�aDAOMYSQL.getInstance().readForId(resultSet.getInt("idCampa�a")));
				compra.setEstadoDeCompra(EstadoDeCompraDAOMYSQL.getInstance().readForId(resultSet.getInt("idEstadoDeCompra")));
				compra.setProducto(ProductoDAOMYSQL.getInstance().readForId(resultSet.getInt("idProducto")));
				compra.setCompraPromocion(CompraPromocionDAOMYSQL.getInstance().readForId(resultSet.getInt("idCompraPromocion")));
				compraDeCampa�a.add(compra);
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return compraDeCampa�a;
	}

	@Override
	public List<CompraDTO> readForIdCliente(int idCliente) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		ArrayList<CompraDTO> compraDeCliente = new ArrayList<CompraDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForIdCliente);
			statement.setInt(1, idCliente);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				CompraDTO compra = new CompraDTO();
				compra.setIdCompra(resultSet.getInt("idCompra"));
				compra.setPagina(resultSet.getString("pagina"));
				compra.setPrecio(resultSet.getInt("precio"));
				compra.setUnidades(resultSet.getInt("unidades"));
				compra.setMontoPagado(resultSet.getInt("montoPagado"));
				compra.setPorcentajeDeGanancia(resultSet.getInt("porcentajeDeGanancia"));
				compra.setCliente(ClienteDAOMYSQL.getInstance().readForId(resultSet.getInt("idCliente")));
				compra.setCampa�a(Campa�aDAOMYSQL.getInstance().readForId(resultSet.getInt("idCampa�a")));
				compra.setEstadoDeCompra(EstadoDeCompraDAOMYSQL.getInstance().readForId(resultSet.getInt("idEstadoDeCompra")));
				compra.setProducto(ProductoDAOMYSQL.getInstance().readForId(resultSet.getInt("idProducto")));
				compra.setCompraPromocion(CompraPromocionDAOMYSQL.getInstance().readForId(resultSet.getInt("idCompraPromocion")));
				compraDeCliente.add(compra);
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return compraDeCliente;
	}
	
}