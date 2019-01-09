package presentacion.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import dto.Campa�aDTO;
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
	private Campa�aDTO campa�a;
	
	public ControladorVentanaPromocionesABM(Campa�aDTO campa�a)
	{
		this.ventana = new VentanaPromocionesABM();
		this.campa�a = campa�a;
		this.ventana.getTablaPromocion().addMouseListener(this);
		this.ventana.getBtnAgregar().addActionListener(e -> this.agregarPromocion());
		this.ventana.getBtnEditar().addActionListener(e -> this.editarPromocion());
		this.ventana.getBtnEliminar().addActionListener(e -> this.eliminarPromocion());
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.ventana.show();
	}

	private void agregarPromocion()
	{
		ControladorVentanaAgregarPromocion contro = new ControladorVentanaAgregarPromocion(this, this.campa�a);
		contro.initialize();
	}

	private void editarPromocion() 
	{
		PromocionDTO promocionSelect = GestorPromociones.getInstance().readForCampa�a(this.campa�a).get(this.ventana.getTablaPromocion().getSelectedRow());
		ControladorVentanaEditarPromocion contro = new ControladorVentanaEditarPromocion(this, promocionSelect);
		contro.initialize();			
	}
	
	private void eliminarPromocion()
	{
		PromocionDTO promocionSelect = GestorPromociones.getInstance().readForCampa�a(this.campa�a).get(this.ventana.getTablaPromocion().getSelectedRow());
		
		if(JOptionPane.showConfirmDialog(null,"<html>�Est\u00E1 seguro que quiere eliminar la promocion?</html>", "Eliminar Promocion",JOptionPane.YES_NO_OPTION)==0) 
		{
			for(ProductoDTO product : promocionSelect.getProductos()) 
			{
				GestorPromociones.getInstance().delete(promocionSelect.getIdPromocion(), product.getIdProducto());
			}
			GestorPromociones.getInstance().delete(promocionSelect);
			this.update();
		}		
	}
	
	private void llenarTabla()
	{
		this.reiniciarTabla();
		
		for (PromocionDTO promocion : GestorPromociones.getInstance().readForCampa�a(this.campa�a))
		{
			Object[] fila = {
								promocion.getNombre(),
								promocion.getDescripcion(),
								listarProductos(promocion.getProductos())
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
			PremioDTO premioSelect = GestorPremios.getInstance().readForIdCampa�a(this.campa�a.getIdCampa�a()).get(this.ventana.getTablaPromocion().getSelectedRow());
			
			VentanaVerPremio ventana = new VentanaVerPremio();
			ventana.getFrame().setTitle(premioSelect.getNombre());
			ventana.getTextArea().setText(premioSelect.getDescripcion());
			ventana.getTextFieldUnidades().setText(Integer.toString(premioSelect.getUnidadesMinimas()));
			ventana.show();
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