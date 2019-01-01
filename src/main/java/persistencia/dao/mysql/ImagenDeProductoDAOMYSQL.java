
package persistencia.dao.mysql;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;

import dto.ImagenDeProductoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.intefaz.ImagenDeProductoDAO;

public class ImagenDeProductoDAOMYSQL implements ImagenDeProductoDAO
{
	
	private static ImagenDeProductoDAOMYSQL instance;
	private static final String insert = "INSERT INTO imagenDeProducto  (imagen, idProducto) VALUES(?, ?)";
	private static final String update = "UPDATE imagenDeProducto SET imagen=? WHERE idProducto=?";
	private static final String delete = "DELETE FROM imagenDeProducto WHERE idProducto =?";
	private static final String readForIdProducto = "SELECT * FROM imagenDeProducto WHERE idProducto=?";
	
	public static ImagenDeProductoDAOMYSQL getInstance()
	{
		if (instance == null)
			instance = new ImagenDeProductoDAOMYSQL();
		return instance;
	}

	@Override
	public boolean insert(String rutaImagen, int idProducto) 
	{
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{	
			statement = conexion.getSQLConexion().prepareStatement(insert);
			File file = new File(rutaImagen);
			FileInputStream fis = null;
			try 
			{
				fis = new FileInputStream(file);
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			statement.setBinaryStream(1, fis,(int)file.length());
			statement.setInt(2, idProducto);
			if(statement.executeUpdate() > 0) { return true; }
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(String rutaImagen, int idProducto) 
	{
		System.out.println("rutaImagen: "+rutaImagen);
		System.out.println("idProducto: "+idProducto);
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(update);
			File file = new File(rutaImagen);
			FileInputStream fis = null;
			try 
			{
				fis = new FileInputStream(file);
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
			}
			statement.setBinaryStream(1, fis,(int)file.length());
			statement.setInt(2, idProducto);
			if(statement.executeUpdate() > 0)
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		System.out.println("algo fallo!!!");
		return false;
	}

	@Override
	public boolean delete(int idProducto) 
	{
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, idProducto);
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
	public ImagenDeProductoDTO readForIdProducto(int idProducto) 
	{
		PreparedStatement statement;
		ResultSet resultSet;
		Conexion conexion = Conexion.getConexion();
		ImagenDeProductoDTO imagenDeProducto = new ImagenDeProductoDTO(); 
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readForIdProducto);
			statement.setInt(1, idProducto);
			resultSet = statement.executeQuery();
			while(resultSet.next())
			{
				Blob blob = resultSet.getBlob("imagen");
				byte[] data = blob.getBytes(1, (int)blob.length());
				BufferedImage img = null;
				try {
					img = ImageIO.read(new ByteArrayInputStream(data));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				imagenDeProducto.setImagen(img);
				imagenDeProducto.setIdProducto(resultSet.getInt("idProducto"));
				imagenDeProducto.setIdImagenDeProducto(resultSet.getInt("idImagenDeProducto"));
			}	
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return imagenDeProducto;
	}

}