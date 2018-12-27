package presentacion.controlador;

import presentacion.vista.PanelCompra;
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
		this.ventanaPrincipal.getBtnCompra().addActionListener(e -> this.mostrarPanelCompra());
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
	
	private void mostrarPanelCompra()
	{
		this.ventanaPrincipal.limpiarPanelCentral();
		PanelCompra panelCompra = new PanelCompra();
		ventanaPrincipal.setearPanelCentral(panelCompra);
		ControladorPanelCompra contro = new ControladorPanelCompra(panelCompra);
		contro.initialize();
	}
	
}