package presentacion.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

import dto.Campa�aDTO;
import modelo.GestorCampa�a;
import observer.Observador;
import presentacion.vista.PanelGestionCampa�as;
import presentacion.vista.PanelGestionCompras;
import presentacion.vista.VentanaPrincipal;

public class ControladorPanelGestionCampa�as implements MouseListener, Observador
{

	private List<Campa�aDTO> campa�as_en_tabla;
	private PanelGestionCampa�as panelCampa�a;
	private VentanaPrincipal ventana;
	
	public ControladorPanelGestionCampa�as(PanelGestionCampa�as panel, VentanaPrincipal ventana)
	{
		this.ventana = ventana;
		this.panelCampa�a = panel;
		this.campa�as_en_tabla = null;
		this.panelCampa�a.getBtnAgregar().addActionListener(e -> this.agregarCampa�a());
		this.panelCampa�a.getBtnEditar().addActionListener(e -> this.editarCampa�a());
		this.panelCampa�a.getBtnEliminar().addActionListener(e -> this.eliminarCampa�a());
		this.panelCampa�a.getBtnPromociones().addActionListener(e -> this.promocionesABM());
		this.panelCampa�a.getBtnPremios().addActionListener(e -> this.premiosABM());
		this.panelCampa�a.getTablaCampa�a().addMouseListener(this);
		this.panelCampa�a.getBtnEditar().setEnabled(false);
		this.panelCampa�a.getBtnEliminar().setEnabled(false);
		this.panelCampa�a.getBtnPremios().setEnabled(false);
		this.panelCampa�a.getBtnPromociones().setEnabled(false);
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.panelCampa�a.repaint();
	}
	
	private void llenarTabla()
	{
		reiniciarTabla();
		
		campa�as_en_tabla = GestorCampa�a.getInstance().readAll();
		
		for (Campa�aDTO campa�a : campa�as_en_tabla)
		{
			Object[] fila = {
								campa�a.getA�o(),
								campa�a.getNumero(),
								campa�a.getCierre(),
								"Aqui venta",
								"Aqui ganancia",
								"Aqui si gano premios",
								"Aqui si tiene algo pendiente"
							};
			this.panelCampa�a.getModelCampa�a().addRow(fila);
		}			
	}

	private void reiniciarTabla() 
	{
		this.panelCampa�a.getModelCampa�a().setRowCount(0); // Para vaciar la tabla
		this.panelCampa�a.getModelCampa�a().setColumnCount(0);
		this.panelCampa�a.getModelCampa�a().setColumnIdentifiers(this.panelCampa�a.getNombreColumnasCampa�a());
	}

	private void agregarCampa�a() 
	{
		for(Campa�aDTO campa�a : GestorCampa�a.getInstance().readAll())
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateCierre = sdf.format(campa�a.getCierre());
			String dateHoy = sdf.format(new Date());
			if(campa�a.getCierre().after(new Date()) || dateCierre.equals(dateHoy))
			{
				JOptionPane.showMessageDialog(null, "�Ya existe una campa�a vigente!");
				return;				
			}
		}
		ControladorVentanaAgregarCampa�a contro = new ControladorVentanaAgregarCampa�a(this);
		contro.initialize();
	}
	
	private void editarCampa�a() 
	{
		if(this.panelCampa�a.getTablaCampa�a().getSelectedRow()==-1)
		{
			JOptionPane.showMessageDialog(null, "�Seleccione una campa�a!");
			return;
		}
		Campa�aDTO campa�aSelect = this.campa�as_en_tabla.get(this.panelCampa�a.getTablaCampa�a().getSelectedRow());
		ControladorVentanaEditarCampa�a contro = new ControladorVentanaEditarCampa�a(this, campa�aSelect);
		contro.initialize();
	}
	
	public void eliminarCampa�a()
	{

	}
	
	public void promocionesABM()
	{
		Campa�aDTO campa�aSelect = this.campa�as_en_tabla.get(this.panelCampa�a.getTablaCampa�a().getSelectedRow());
		ControladorVentanaPromocionesABM contro = new ControladorVentanaPromocionesABM( campa�aSelect );
		contro.initialize();
	}
	
	public void premiosABM()
	{
		Campa�aDTO campa�aSelect = this.campa�as_en_tabla.get(this.panelCampa�a.getTablaCampa�a().getSelectedRow());
		ControladorVentanaPremiosABM contro = new ControladorVentanaPremiosABM( campa�aSelect );
		contro.initialize();
	}
	
	@Override
	public void update() 
	{
		this.llenarTabla();
		this.panelCampa�a.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getClickCount()==2)
		{
			this.ventana.limpiarPanelCentral();
			Campa�aDTO campa�aSelect = this.campa�as_en_tabla.get(this.panelCampa�a.getTablaCampa�a().getSelectedRow());
			PanelGestionCompras panelCompra = new PanelGestionCompras(); 
			this.ventana.setearPanelCentral(panelCompra);
			this.ventana.show();
			ControladorPanelGestionCompras contro = new ControladorPanelGestionCompras(panelCompra, campa�aSelect);
			contro.initialize();
		}
		
		if(this.panelCampa�a.getTablaCampa�a().getSelectedRow() != -1)
		{
			this.panelCampa�a.getBtnEditar().setEnabled(true);
			this.panelCampa�a.getBtnEliminar().setEnabled(true);
			this.panelCampa�a.getBtnPremios().setEnabled(true);
			this.panelCampa�a.getBtnPromociones().setEnabled(true);
		}
		else
		{
			this.panelCampa�a.getBtnEditar().setEnabled(false);
			this.panelCampa�a.getBtnEliminar().setEnabled(false);
			this.panelCampa�a.getBtnPremios().setEnabled(false);
			this.panelCampa�a.getBtnPromociones().setEnabled(false);
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

}