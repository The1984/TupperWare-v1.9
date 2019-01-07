package presentacion.controlador;

import presentacion.vista.PanelNegocio;
import persistencia.conexion.Conexion;
import presentacion.vista.PanelGestionCampañas;
import presentacion.vista.PanelGestionClientes;
import presentacion.vista.VentanaPrincipal;
import presentacion.vista.VistaPresentacion;

public class ControladorVentanaPrincipal 
{

	VentanaPrincipal ventanaPrincipal;
	Conexion conexion = Conexion.getConexion();

	public ControladorVentanaPrincipal() 
	{
		this.ventanaPrincipal = new VentanaPrincipal();
		this.ventanaPrincipal.getBtnImportarBD().addActionListener(e -> this.mostrarVentanaImportar());
		this.ventanaPrincipal.getBtnExportarBD().addActionListener(e -> this.mostrarVentanaExportar());
		this.ventanaPrincipal.getBtnGestionClientes().addActionListener(e -> this.mostrarPanelGestionClientes());
		this.ventanaPrincipal.getBtnNegocio().addActionListener(e -> this.mostrarPanelNegocio());
		this.ventanaPrincipal.getBtnGestionCampañas().addActionListener(e -> this.mostrarPanelGestionCampañas());
	}
	
	public void initialize() 
	{
		this.ventanaPrincipal.limpiarPanelCentral();
		this.ventanaPrincipal.setearPanelCentral(new VistaPresentacion());
		this.ventanaPrincipal.show();
	}

	private void mostrarVentanaImportar() 
	{
		new ControladorVentanaImportBD(this.ventanaPrincipal);
	}

	private void mostrarVentanaExportar()
	{
		new ControladorVentanaExportBD();
	}

	private void mostrarPanelGestionClientes() 
	{
		this.ventanaPrincipal.limpiarPanelCentral();
		PanelGestionClientes panelCliente = new PanelGestionClientes();
		this.ventanaPrincipal.setearPanelCentral(panelCliente);		
		this.ventanaPrincipal.show();
		ControladorPanelGestionClientes contro = new ControladorPanelGestionClientes(panelCliente);
		contro.initialize();
	}
	
	private void mostrarPanelNegocio()
	{
		this.ventanaPrincipal.limpiarPanelCentral();
		PanelNegocio panelCompra = new PanelNegocio();
		this.ventanaPrincipal.setearPanelCentral(panelCompra);
		this.ventanaPrincipal.show();
		ControladorPanelNegocio contro = new ControladorPanelNegocio(panelCompra);
		contro.initialize();
	}
	
	private void mostrarPanelGestionCampañas()
	{
		this.ventanaPrincipal.limpiarPanelCentral();
		PanelGestionCampañas panelCampaña = new PanelGestionCampañas();
		this.ventanaPrincipal.setearPanelCentral(panelCampaña);
		this.ventanaPrincipal.show();
		ControladorPanelGestionCampañas contro = new ControladorPanelGestionCampañas(panelCampaña, this.ventanaPrincipal);
		contro.initialize();
	}
	
}