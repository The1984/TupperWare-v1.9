package presentacion.controlador;

import presentacion.vista.VentanaConfiguracion;

public class ControladorPanelConfiguracion
{

	private VentanaConfiguracion ventanaConfiguracion;

	public ControladorPanelConfiguracion()
	{
		this.ventanaConfiguracion = new VentanaConfiguracion();
		this.ventanaConfiguracion.getBtnCuenta().addActionListener(e -> this.editarCuenta());
		this.ventanaConfiguracion.getBtnTipoDeProducto().addActionListener(e -> this.gestarTiposDeProductos());
	}
	
	public void initialize()
	{
		this.ventanaConfiguracion.show();
	}
	
	public void editarCuenta()
	{
		ControladorVentanaEditarCuenta contro = new ControladorVentanaEditarCuenta();
		contro.initialize();
	}

	public void gestarTiposDeProductos()
	{
		ControladorVentanaTipoDeProductoABM contro = new ControladorVentanaTipoDeProductoABM();
		contro.initialize();		
	}
	
}