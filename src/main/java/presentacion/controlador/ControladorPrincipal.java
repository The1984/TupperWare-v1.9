package presentacion.controlador;

import java.awt.event.ActionEvent;

import presentacion.vista.VentanaPrincipal;

public class ControladorPrincipal 
{

	VentanaPrincipal ventanaPrincipal;

	public ControladorPrincipal() 
	{
		this.ventanaPrincipal = new VentanaPrincipal();
		this.ventanaPrincipal.getBtnImportarBD().addActionListener(this::mostrarVistaImportar);
		this.ventanaPrincipal.getBtnExportarBD().addActionListener(this::mostrarVistaExportar);
	}
	
	public void initialize() 
	{
		this.ventanaPrincipal.show();
	}

	private void mostrarVistaImportar(ActionEvent e) {
		new ControladorImportBD(this.ventanaPrincipal);
	}

	private void mostrarVistaExportar(ActionEvent e) {
		new ControladorExportBD();
	}

	
}
