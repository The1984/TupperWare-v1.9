package presentacion.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import dto.ProductoDTO;
import modelo.GestorProductos;
import presentacion.vista.VentanaSeleccionarProducto;

public class ControladorVentanaSeleccionarProductoEditar implements KeyListener, MouseListener
{
	
	private ControladorVentanaEditarPromocion controlador;
	private VentanaSeleccionarProducto ventana;
	private List<ProductoDTO> productos_filtrados;
	
	public ControladorVentanaSeleccionarProductoEditar(ControladorVentanaEditarPromocion control)
	{
		this.ventana = new VentanaSeleccionarProducto();
		this.controlador = control;
		this.productos_filtrados = new ArrayList<ProductoDTO>();
		this.ventana.getTxtFiltro().addKeyListener(this);
		this.ventana.getTablaProducto().addMouseListener(this);
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.ventana.show();
	}

	private void llenarTabla()
	{
		this.reiniciarTabla();
		
		for (ProductoDTO producto : GestorProductos.getInstance().readAll())
		{
			Object[] fila = 
					{ 
						producto.getCodigo(),
						producto.getNombre(),
						producto.getTipoDeProducto().getNombre()
					};
			this.productos_filtrados.add(producto);
			this.ventana.getModelProducto().addRow(fila);
		}			
	}

	private void reiniciarTabla() 
	{
		this.ventana.getModelProducto().setRowCount(0); // Para vaciar la tabla
		this.ventana.getModelProducto().setColumnCount(0);
		this.ventana.getModelProducto().setColumnIdentifiers(this.ventana.getNombreColumnasProducto());
	}
	
	public void filtrarTabla(String cadena) 
	{
		this.reiniciarTabla();
		
		this.productos_filtrados.clear();
		
		for (ProductoDTO producto : GestorProductos.getInstance().readAll())
		{
			String getNombre = producto.getCodigo().toUpperCase()+
							   producto.getNombre().toUpperCase()+
							   producto.getTipoDeProducto().getNombre().toUpperCase();
			if (getNombre.indexOf(cadena.toUpperCase()) != -1) 
			{
				this.productos_filtrados.add(producto);
				Object[] fila = 
					{ 
						producto.getCodigo(),
						producto.getNombre(),
						producto.getTipoDeProducto().getNombre()
					};
				this.ventana.getModelProducto().addRow(fila);
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getClickCount()==2)
		{
			ProductoDTO productSelect = this.productos_filtrados.get(this.ventana.getTablaProducto().getSelectedRow());
			this.controlador.sumarProducto(productSelect);
			this.controlador.initialize();
			this.ventana.close();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		String cadena = this.ventana.getTxtFiltro().getText();
		this.filtrarTabla(cadena);		
	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}
	
}