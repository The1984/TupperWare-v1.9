package presentacion.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

import dto.CampañaDTO;
import modelo.GestorCampaña;
import observer.Observador;
import presentacion.vista.PanelGestionCampañas;
import presentacion.vista.PanelGestionCompras;
import presentacion.vista.VentanaPrincipal;

public class ControladorPanelGestionCampañas implements MouseListener, Observador
{

	private List<CampañaDTO> campañas_en_tabla;
	private PanelGestionCampañas panelCampaña;
	private VentanaPrincipal ventana;
	
	public ControladorPanelGestionCampañas(PanelGestionCampañas panel, VentanaPrincipal ventana)
	{
		this.ventana = ventana;
		this.panelCampaña = panel;
		this.campañas_en_tabla = null;
		this.panelCampaña.getBtnAgregar().addActionListener(e -> this.agregarCampaña());
		this.panelCampaña.getBtnEditar().addActionListener(e -> this.editarCampaña());
		this.panelCampaña.getBtnEliminar().addActionListener(e -> this.eliminarCampaña());
		this.panelCampaña.getBtnPromociones().addActionListener(e -> this.promocionesABM());
		this.panelCampaña.getBtnPremios().addActionListener(e -> this.premiosABM());
		this.panelCampaña.getTablaCampaña().addMouseListener(this);
		this.panelCampaña.getBtnEditar().setEnabled(false);
		this.panelCampaña.getBtnEliminar().setEnabled(false);
		this.panelCampaña.getBtnPremios().setEnabled(false);
		this.panelCampaña.getBtnPromociones().setEnabled(false);
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.panelCampaña.repaint();
	}
	
	private void llenarTabla()
	{
		reiniciarTabla();
		
		campañas_en_tabla = GestorCampaña.getInstance().readAll();
		
		for (CampañaDTO campaña : campañas_en_tabla)
		{
			Object[] fila = {
								campaña.getAño(),
								campaña.getNumero(),
								campaña.getCierre(),
								"Aqui venta",
								"Aqui ganancia",
								"Aqui si gano premios",
								"Aqui si tiene algo pendiente"
							};
			this.panelCampaña.getModelCampaña().addRow(fila);
		}			
	}

	private void reiniciarTabla() 
	{
		this.panelCampaña.getModelCampaña().setRowCount(0); // Para vaciar la tabla
		this.panelCampaña.getModelCampaña().setColumnCount(0);
		this.panelCampaña.getModelCampaña().setColumnIdentifiers(this.panelCampaña.getNombreColumnasCampaña());
	}

	private void agregarCampaña() 
	{
		for(CampañaDTO campaña : GestorCampaña.getInstance().readAll())
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String dateCierre = sdf.format(campaña.getCierre());
			String dateHoy = sdf.format(new Date());
			if(campaña.getCierre().after(new Date()) || dateCierre.equals(dateHoy))
			{
				JOptionPane.showMessageDialog(null, "¡Ya existe una campaña vigente!");
				return;				
			}
		}
		ControladorVentanaAgregarCampaña contro = new ControladorVentanaAgregarCampaña(this);
		contro.initialize();
	}
	
	private void editarCampaña() 
	{
		if(this.panelCampaña.getTablaCampaña().getSelectedRow()==-1)
		{
			JOptionPane.showMessageDialog(null, "¡Seleccione una campaña!");
			return;
		}
		CampañaDTO campañaSelect = this.campañas_en_tabla.get(this.panelCampaña.getTablaCampaña().getSelectedRow());
		ControladorVentanaEditarCampaña contro = new ControladorVentanaEditarCampaña(this, campañaSelect);
		contro.initialize();
	}
	
	public void eliminarCampaña()
	{

	}
	
	public void promocionesABM()
	{
		CampañaDTO campañaSelect = this.campañas_en_tabla.get(this.panelCampaña.getTablaCampaña().getSelectedRow());
		ControladorVentanaPromocionesABM contro = new ControladorVentanaPromocionesABM( campañaSelect );
		contro.initialize();
	}
	
	public void premiosABM()
	{
		CampañaDTO campañaSelect = this.campañas_en_tabla.get(this.panelCampaña.getTablaCampaña().getSelectedRow());
		ControladorVentanaPremiosABM contro = new ControladorVentanaPremiosABM( campañaSelect );
		contro.initialize();
	}
	
	@Override
	public void update() 
	{
		this.llenarTabla();
		this.panelCampaña.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getClickCount()==2)
		{
			this.ventana.limpiarPanelCentral();
			CampañaDTO campañaSelect = this.campañas_en_tabla.get(this.panelCampaña.getTablaCampaña().getSelectedRow());
			PanelGestionCompras panelCompra = new PanelGestionCompras(); 
			this.ventana.setearPanelCentral(panelCompra);
			this.ventana.show();
			ControladorPanelGestionCompras contro = new ControladorPanelGestionCompras(panelCompra, campañaSelect);
			contro.initialize();
		}
		
		if(this.panelCampaña.getTablaCampaña().getSelectedRow() != -1)
		{
			this.panelCampaña.getBtnEditar().setEnabled(true);
			this.panelCampaña.getBtnEliminar().setEnabled(true);
			this.panelCampaña.getBtnPremios().setEnabled(true);
			this.panelCampaña.getBtnPromociones().setEnabled(true);
		}
		else
		{
			this.panelCampaña.getBtnEditar().setEnabled(false);
			this.panelCampaña.getBtnEliminar().setEnabled(false);
			this.panelCampaña.getBtnPremios().setEnabled(false);
			this.panelCampaña.getBtnPromociones().setEnabled(false);
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