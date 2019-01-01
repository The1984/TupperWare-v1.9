package dto;

import java.awt.Image;

public class ImagenDeProductoDTO 
{
	private int idImagenDeProducto;
	private Image imagen;
	private int idProducto;
	
	public int getIdImagenDeProducto() 
	{
		return idImagenDeProducto;
	}
	
	public void setIdImagenDeProducto(int idImagenDeProducto) 
	{
		this.idImagenDeProducto = idImagenDeProducto;
	}
	
	public Image getImagen() 
	{
		return imagen;
	}
	
	public void setImagen(Image imagen) 
	{
		this.imagen = imagen;
	}
	
	public int getIdProducto() 
	{
		return idProducto;
	}
	
	public void setIdProducto(int idProducto) 
	{
		this.idProducto = idProducto;
	}

}
