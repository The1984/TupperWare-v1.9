package presentacion.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import dto.ProductoDTO;
import modelo.GestorProductos;
import observer.Observador;
import presentacion.vista.PanelNegocio;

public class ControladorPanelNegocio implements KeyListener, MouseListener, Observador
{

	private List<ProductoDTO> productos_en_tabla;
	private List<ProductoDTO> productos_filtrados;
	private PanelNegocio panelNegocio;
	
	public ControladorPanelNegocio(PanelNegocio panel)
	{
		this.panelNegocio = panel;
		this.productos_en_tabla = null;
		this.productos_filtrados = new ArrayList<ProductoDTO>();
		this.productos_filtrados = GestorProductos.getInstance().readAll();

		this.panelNegocio.getTextFiltro().addKeyListener(this);
		this.panelNegocio.getBtnAgregarProducto().addActionListener(e -> this.agregarProducto());
		this.panelNegocio.getBtnEditarProducto().addActionListener(e -> this.editarProducto());
		this.panelNegocio.getTablaProducto().addMouseListener(this);
		
	}
	
	public void initialize()
	{
		this.llenarTablaProducto();
		this.llenarTablaPromocion();
		this.panelNegocio.repaint();
	}
	
	private void llenarTablaProducto()
	{
		this.reiniciarTabla();
		
		this.productos_en_tabla = GestorProductos.getInstance().readAll();
		for (ProductoDTO producto : productos_en_tabla)
		{
			Object[] fila = {
								producto.getCodigo(),
								producto.getNombre(),
								producto.getDescripcion(),
								producto.getTipoDeProducto().getNombre()
							};
			this.panelNegocio.getModelProducto().addRow(fila);
		}			
	}

	private void reiniciarTabla() 
	{
		this.panelNegocio.getModelProducto().setRowCount(0); // Para vaciar la tabla
		this.panelNegocio.getModelProducto().setColumnCount(0);
		this.panelNegocio.getModelProducto().setColumnIdentifiers(this.panelNegocio.getNombreColumnasProducto());
	}
	
	public void filtrarTabla(String cadena) 
	{
		reiniciarTabla();
		
		productos_filtrados.clear();
		
		for (ProductoDTO producto : productos_en_tabla) 
		{
			String getNombre = producto.getCodigo().toUpperCase()+
							   producto.getNombre().toUpperCase()+
							   producto.getTipoDeProducto().getNombre().toUpperCase();
			if (getNombre.indexOf(cadena.toUpperCase()) != -1) 
			{
				productos_filtrados.add(producto);
				Object[] fila = 
					{ 
						producto.getCodigo(),
						producto.getNombre(),
						producto.getDescripcion(),
						producto.getTipoDeProducto().getNombre()
					};
				this.panelNegocio.getModelProducto().addRow(fila);
			}
		}
	}

	private void llenarTablaPromocion()
	{

	}
	
	private void agregarProducto()
	{
		ControladorVentanaAgregarProducto contro = new ControladorVentanaAgregarProducto(this);
		contro.initialize();
	}

	private void editarProducto()
	{
		ProductoDTO productSelect = this.productos_filtrados.get(this.panelNegocio.getTablaProducto().getSelectedRow());
		ControladorVentanaEditarProducto contro = new ControladorVentanaEditarProducto(this, productSelect);
		contro.initialize();
	}

	@Override
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		String cadena = this.panelNegocio.getTextFiltro().getText();
		this.filtrarTabla(cadena);		
	}

	@Override
	public void keyTyped(KeyEvent e) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getClickCount()==2)
		{
			ProductoDTO productSelect = this.productos_filtrados.get(this.panelNegocio.getTablaProducto().getSelectedRow());
			ControladorVentanaVerProducto contro = new ControladorVentanaVerProducto(productSelect);
			contro.initialize();
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
		this.llenarTablaProducto();
		this.productos_filtrados = GestorProductos.getInstance().readAll();
		this.panelNegocio.getTextFiltro().setText("");
		this.panelNegocio.repaint();	
	}
	
}