package presentacion.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import dto.CampañaDTO;
import dto.ProductoDTO;
import dto.PromocionDTO;
import modelo.GestorCampaña;
import modelo.GestorProductos;
import observer.Observador;
import presentacion.vista.PanelNegocio;

public class ControladorPanelNegocio implements KeyListener, MouseListener, Observador
{

	private List<ProductoDTO> productos_filtrados;
	private List<PromocionDTO> promociones;
	private PanelNegocio panelNegocio;
	
	public ControladorPanelNegocio(PanelNegocio panel)
	{
		this.panelNegocio = panel;
		this.productos_filtrados = new ArrayList<ProductoDTO>();
		this.promociones = new ArrayList<PromocionDTO>();
		this.panelNegocio.getTextFiltro().addKeyListener(this);
		this.panelNegocio.getBtnAgregarProducto().addActionListener(e -> this.agregarProducto());
		this.panelNegocio.getBtnEditarProducto().addActionListener(e -> this.editarProducto());
		this.panelNegocio.getTablaProducto().addMouseListener(this);
		this.panelNegocio.getTablaPromocion().addMouseListener(this);
		this.panelNegocio.getBtnEditarProducto().setEnabled(false);
		this.panelNegocio.getBtnEliminarProducto().setEnabled(false);
	}

	public void initialize()
	{
		this.llenarTablaProducto();
		this.llenarTablaPromocion();
		this.panelNegocio.repaint();
	}
	
	private void llenarTablaProducto()
	{
		this.reiniciarTablaProducto();
		
		for (ProductoDTO producto : GestorProductos.getInstance().readAll())
		{
			Object[] fila = {
								producto.getCodigo(),
								producto.getNombre(),
								producto.getDescripcion(),
								producto.getTipoDeProducto().getNombre()
							};
			this.productos_filtrados.add(producto);
			this.panelNegocio.getModelProducto().addRow(fila);
		}			
	}

	private void llenarTablaPromocion()
	{
		this.reiniciarTablaPromocion(); 
		
		CampañaDTO campañaMasReciente;
		List<CampañaDTO> campañas = GestorCampaña.getInstance().readAll();
		if(campañas.size()!=0)
		{
			campañaMasReciente = campañas.get(0);
			for (CampañaDTO campaña : campañas)
			{
				if(campañaMasReciente.getCierre().before(campaña.getCierre()))
					campañaMasReciente = campaña;
			}
		
			for (PromocionDTO promocion : campañaMasReciente.getPromociones())
			{
				Object[] fila = {
									promocion.getNombre(),
									promocion.getDescripcion(),
									this.listarProductos(promocion.getProductos()),
									promocion.getPagina(),
									Integer.toString(promocion.getPrecio())
								};
				this.promociones.add(promocion);
				this.panelNegocio.getModelPromocion().addRow(fila);
			}	
		}
	}
	
	public String listarProductos(List<ProductoDTO> productos)
	{
		String list = "";
		for (ProductoDTO producto : productos)
		{
			list = list + producto.getNombre();
			if (!productos.get(productos.size()-1).equals(producto))
			{
				list = list + " / ";
			}
		}
		return list;
	}
	
	private void reiniciarTablaProducto() 
	{
		this.panelNegocio.getModelProducto().setRowCount(0); // Para vaciar la tabla
		this.panelNegocio.getModelProducto().setColumnCount(0);
		this.panelNegocio.getModelProducto().setColumnIdentifiers(this.panelNegocio.getNombreColumnasProducto());
	}
	
	private void reiniciarTablaPromocion() 
	{
		this.panelNegocio.getModelPromocion().setRowCount(0); // Para vaciar la tabla
		this.panelNegocio.getModelPromocion().setColumnCount(0);
		this.panelNegocio.getModelPromocion().setColumnIdentifiers(this.panelNegocio.getNombreColumnasPromocion());
	}
	
	public void filtrarTabla(String cadena) 
	{
		reiniciarTablaProducto();
		
		productos_filtrados.clear();
		
		for (ProductoDTO producto : GestorProductos.getInstance().readAll())
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
		if(e.getClickCount()==2 && e.getSource().equals(this.panelNegocio.getTablaProducto()))
		{
			ProductoDTO productSelect = this.productos_filtrados.get(this.panelNegocio.getTablaProducto().getSelectedRow());
			ControladorVentanaVerProducto contro = new ControladorVentanaVerProducto(productSelect);
			contro.initialize();
		}
		if(e.getClickCount()==2 && e.getSource().equals(this.panelNegocio.getTablaPromocion()))
		{
			PromocionDTO promoSelect = this.promociones.get(this.panelNegocio.getTablaPromocion().getSelectedRow());
			ControladorVentanaComprarPromocion contro = new ControladorVentanaComprarPromocion(promoSelect);
			contro.initialize();
		}
		
		if(this.panelNegocio.getTablaProducto().getSelectedRow() != -1)
		{
			this.panelNegocio.getBtnEditarProducto().setEnabled(true);
			this.panelNegocio.getBtnEliminarProducto().setEnabled(true);
		}
		else
		{
			this.panelNegocio.getBtnEditarProducto().setEnabled(false);
			this.panelNegocio.getBtnEliminarProducto().setEnabled(false);
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