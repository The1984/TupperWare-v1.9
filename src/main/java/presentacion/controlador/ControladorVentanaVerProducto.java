package presentacion.controlador;

import presentacion.vista.VentanaVerProducto;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import dto.ImagenDeProductoDTO;
import dto.ProductoDTO;
import modelo.GestorProductos;

public class ControladorVentanaVerProducto
{
	
	private VentanaVerProducto ventana;
	private ProductoDTO product;
	
	public ControladorVentanaVerProducto(ProductoDTO producto)
	{
		this.product = producto;
		ventana = new VentanaVerProducto();
		
		this.ventana.getLblNombre().setText(this.product.getNombre());
		this.ventana.getTxtrDescripcion().setText(this.product.getDescripcion());
		ImagenDeProductoDTO imagen = GestorProductos.getInstance().readForIdProducto(this.product.getIdProducto());
	    Icon icono = new ImageIcon(imagen.getImagen().getScaledInstance(this.ventana.getLblImagen().getWidth(), this.ventana.getLblImagen().getHeight(), Image.SCALE_DEFAULT));
	    this.ventana.getLblImagen().setIcon(icono);

		ventana.getBtnComprar().addActionListener(e -> this.comprarProducto());
	}
	
	public void initialize()
	{
		ventana.show();
	}

	public void comprarProducto()
	{
		this.ventana.close();
		ControladorVentanaComprarProducto contro = new ControladorVentanaComprarProducto(this.product);
		contro.initialize();
	}
	
}