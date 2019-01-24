package presentacion.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import dto.CampañaDTO;
import dto.PremioDTO;
import dto.ProductoDTO;
import dto.PromocionDTO;
import modelo.GestorPremios;
import modelo.GestorPromociones;
import observer.Observador;
import presentacion.vista.VentanaPromocionesABM;
import presentacion.vista.VentanaVerPremio;

public class ControladorVentanaPromocionesABM implements MouseListener, Observador
{
	
	private VentanaPromocionesABM ventana;
	private CampañaDTO campaña;
	
	public ControladorVentanaPromocionesABM(CampañaDTO campaña)
	{
		this.ventana = new VentanaPromocionesABM();
		this.campaña = campaña;
		this.ventana.getTablaPromocion().addMouseListener(this);
		this.ventana.getBtnAgregar().addActionListener(e -> this.agregarPromocion());
		this.ventana.getBtnEditar().addActionListener(e -> this.editarPromocion());
		this.ventana.getBtnEditar().setEnabled(false);
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.ventana.show();
	}

	private void agregarPromocion()
	{
		ControladorVentanaAgregarPromocion contro = new ControladorVentanaAgregarPromocion(this, this.campaña);
		contro.initialize();
	}

	private void editarPromocion() 
	{
		PromocionDTO promocionSelect = GestorPromociones.getInstance().readForCampaña(this.campaña).get(this.ventana.getTablaPromocion().getSelectedRow());
		ControladorVentanaEditarPromocion contro = new ControladorVentanaEditarPromocion(this, promocionSelect, this.campaña);
		contro.initialize();			
	}
	
	private void llenarTabla()
	{
		this.reiniciarTabla();
		
		for (PromocionDTO promocion : GestorPromociones.getInstance().readForCampaña(this.campaña))
		{
			Object[] fila = {
								promocion.getNombre(),
								promocion.getDescripcion(),
								listarProductos(promocion.getProductos()),
								promocion.getPagina(),
								Integer.toString(promocion.getPrecio())
							};
			this.ventana.getModelPromocion().addRow(fila);
		}			
	}

	private String listarProductos(List<ProductoDTO> productos)
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
	
	private void reiniciarTabla() 
	{
		this.ventana.getModelPromocion().setRowCount(0); // Para vaciar la tabla
		this.ventana.getModelPromocion().setColumnCount(0);
		this.ventana.getModelPromocion().setColumnIdentifiers(this.ventana.getNombreColumnasPromocion());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getClickCount()==2)
		{
			PremioDTO premioSelect = GestorPremios.getInstance().readForIdCampaña(this.campaña.getIdCampaña()).get(this.ventana.getTablaPromocion().getSelectedRow());
			
			VentanaVerPremio ventana = new VentanaVerPremio();
			ventana.getFrame().setTitle(premioSelect.getNombre());
			ventana.getTextArea().setText(premioSelect.getDescripcion());
			ventana.getTextFieldUnidades().setText(Integer.toString(premioSelect.getUnidadesMinimas()));
			ventana.show();
		}
		
		if(this.ventana.getTablaPromocion().getSelectedRow() != -1)
		{
			this.ventana.getBtnEditar().setEnabled(true);
		}
		else
		{
			this.ventana.getBtnEditar().setEnabled(false);
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
	public void update() 
	{
		this.llenarTabla();
		this.ventana.getTablaPromocion().repaint();
	}
	
}