package presentacion.controlador;

import presentacion.vista.PanelNegocio;
import presentacion.vista.PanelGestionCliente;
import presentacion.vista.VentanaPrincipal;

public class ControladorVentanaPrincipal 
{

	VentanaPrincipal ventanaPrincipal;

	public ControladorVentanaPrincipal() 
	{
		this.ventanaPrincipal = new VentanaPrincipal();
		this.ventanaPrincipal.getBtnImportarBD().addActionListener(e -> this.mostrarVentanaImportar());
		this.ventanaPrincipal.getBtnExportarBD().addActionListener(e -> this.mostrarVentanaExportar());
		this.ventanaPrincipal.getBtnGestionClientes().addActionListener(e -> this.mostrarPanelGestionCliente());
		this.ventanaPrincipal.getBtnNegocio().addActionListener(e -> this.mostrarPanelNegocio());
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

	private void mostrarPanelGestionCliente() 
	{
		this.ventanaPrincipal.limpiarPanelCentral();
		PanelGestionCliente panelCliente = new PanelGestionCliente();
		ventanaPrincipal.setearPanelCentral(panelCliente);		
		ControladorPanelGestionCliente contro = new ControladorPanelGestionCliente(panelCliente);
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
	
}