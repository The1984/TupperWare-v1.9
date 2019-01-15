package presentacion.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ProductoDTO;
import dto.TipoDeProductoDTO;
import modelo.GestorProductos;
import modelo.GestorTipoDeProducto;
import observer.Observador;
import presentacion.vista.VentanaTipoDeProductoABM;

public class ControladorVentanaTipoDeProductoABM implements MouseListener, Observador
{

	private VentanaTipoDeProductoABM ventana;
	
	public ControladorVentanaTipoDeProductoABM()
	{
		this.ventana = new VentanaTipoDeProductoABM();
		this.ventana.getBtnAgregar().addActionListener(e -> this.agregarTipoDeProducto());
		this.ventana.getBtnEditar().addActionListener(e -> this.editarTipoDeProducto());
		this.ventana.getBtnEliminar().addActionListener(e -> this.eliminarTipoDeProducto());
		this.ventana.getTablaTipoDeProducto().addMouseListener(this);
		this.ventana.getBtnEditar().setEnabled(false);
		this.ventana.getBtnEliminar().setEnabled(false);
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.ventana.show();
	}

	private void llenarTabla()
	{
		this.reiniciarTabla();
		
		for (TipoDeProductoDTO tipoDeProducto : GestorTipoDeProducto.getInstance().readAll())
		{
			Object[] fila = {
								tipoDeProducto.getNombre(),
								tipoDeProducto.getPorcentajeDeGanancia()
							};
			this.ventana.getModelTipoDeProducto().addRow(fila);
		}			
	}
		
	private void reiniciarTabla() 
	{
		this.ventana.getModelTipoDeProducto().setRowCount(0); // Para vaciar la tabla
		this.ventana.getModelTipoDeProducto().setColumnCount(0);
		this.ventana.getModelTipoDeProducto().setColumnIdentifiers(this.ventana.getNombreColumnasTipoDeProducto());
	}
	
	private void agregarTipoDeProducto()
	{
		ControladorVentanaAgregarTipoDeProducto contro = new ControladorVentanaAgregarTipoDeProducto(this);
		contro.initialize();
	}
	
	private void editarTipoDeProducto()
	{
		TipoDeProductoDTO tipoDeProductoSelect = GestorTipoDeProducto.getInstance().readAll().get(this.ventana.getTablaTipoDeProducto().getSelectedRow());
		ControladorVentanaEditarTipoDeProducto contro = new ControladorVentanaEditarTipoDeProducto(this, tipoDeProductoSelect);
		contro.initialize();
	}

	private void eliminarTipoDeProducto()
	{

		List<ProductoDTO> productos = GestorProductos.getInstance().readAll();
		TipoDeProductoDTO tipoDeProductoSelect = GestorTipoDeProducto.getInstance().readAll().get(this.ventana.getTablaTipoDeProducto().getSelectedRow());
		
		for (ProductoDTO producto : productos)
		{
			if(producto.getTipoDeProducto().getNombre().equals(tipoDeProductoSelect.getNombre()))
			{
				JOptionPane.showMessageDialog(null, "¡Tipo de Producto no puede eliminarse!", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		if(JOptionPane.showConfirmDialog(null,"<html>¿Est\u00E1 seguro que quiere eliminar el Tipo de Producto?</html>", "Eliminar Tipo de Producto",JOptionPane.YES_NO_OPTION)==0) 
		{
			GestorTipoDeProducto.getInstance().delete(tipoDeProductoSelect);
			this.update();
		}		
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(this.ventana.getTablaTipoDeProducto().getSelectedRow() != -1)
		{
			this.ventana.getBtnEditar().setEnabled(true);
			this.ventana.getBtnEliminar().setEnabled(true);
		}
		else
		{
			this.ventana.getBtnEditar().setEnabled(false);
			this.ventana.getBtnEliminar().setEnabled(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void update() 
	{
		this.initialize();
	}
	
}