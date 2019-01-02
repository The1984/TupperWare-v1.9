package presentacion.controlador;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CampañaDTO;
import modelo.GestorCampaña;
import observer.Observador;
import presentacion.vista.PanelGestionCampañas;

public class ControladorPanelGestionCampañas implements Observador
{

	private List<CampañaDTO> campañas_en_tabla;
	private PanelGestionCampañas panelCampaña;

	public ControladorPanelGestionCampañas(PanelGestionCampañas panel)
	{
		this.panelCampaña = panel;
		this.campañas_en_tabla = null;
		this.panelCampaña.getBtnAgregar().addActionListener(e -> this.agregarCampaña());
		this.panelCampaña.getBtnEditar().addActionListener(e -> this.editarCampaña());
		this.panelCampaña.getBtnEliminar().addActionListener(e -> this.eliminarCampaña());
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
	
	@Override
	public void update() 
	{
		this.llenarTabla();
		this.panelCampaña.repaint();
	}

}