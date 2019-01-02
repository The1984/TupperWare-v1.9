package presentacion.controlador;

import presentacion.vista.PanelNegocio;
import persistencia.conexion.Conexion;
import presentacion.vista.PanelGestionCampa�as;
import presentacion.vista.PanelGestionClientes;
import presentacion.vista.VentanaPrincipal;

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
		this.ventanaPrincipal.getBtnGestionCampa�as().addActionListener(e -> this.mostrarPanelGestionCampa�as());
	}
	
	public void initialize() 
	{
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
		ventanaPrincipal.setearPanelCentral(panelCliente);		
		ControladorPanelGestionClientes contro = new ControladorPanelGestionClientes(panelCliente);
		contro.initialize();
	}
	
	private void mostrarPanelNegocio()
	{
		this.ventanaPrincipal.limpiarPanelCentral();
		PanelNegocio panelCompra = new PanelNegocio();
		ventanaPrincipal.setearPanelCentral(panelCompra);
		ControladorPanelNegocio contro = new ControladorPanelNegocio(panelCompra);
		contro.initialize();
	}
	
	private void mostrarPanelGestionCampa�as()
	{
		this.ventanaPrincipal.limpiarPanelCentral();
		PanelGestionCampa�as panelCampa�a = new PanelGestionCampa�as();
		ventanaPrincipal.setearPanelCentral(panelCampa�a);		
		ControladorPanelGestionCampa�as contro = new ControladorPanelGestionCampa�as(panelCampa�a);
		contro.initialize();
	}
	
}