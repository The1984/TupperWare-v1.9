package presentacion.controlador;

import presentacion.vista.VentanaConfiguracion;
import presentacion.vista.VentanaPrincipal;

public class ControladorPanelConfiguracion
{

	private VentanaConfiguracion ventanaConfiguracion;
	private VentanaPrincipal ventanaPrincipal;

	public ControladorPanelConfiguracion(VentanaPrincipal vista)
	{
		this.ventanaPrincipal = vista;
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
		ControladorVentanaEditarCuenta contro = new ControladorVentanaEditarCuenta(this.ventanaPrincipal, this.ventanaConfiguracion);
		contro.initialize();
	}

	public void gestarTiposDeProductos()
	{
		ControladorVentanaTipoDeProductoABM contro = new ControladorVentanaTipoDeProductoABM();
		contro.initialize();		
	}
	
}