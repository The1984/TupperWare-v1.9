package presentacion.controlador;

import observer.Observador;
import presentacion.vista.PanelCorreo;

public class ControladorPanelCorreo implements Observador
{

	private PanelCorreo panelCorreo;

	public ControladorPanelCorreo(PanelCorreo panel)
	{
		this.panelCorreo = panel;
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.panelCorreo.repaint();
	}
	
	private void llenarTabla()
	{

	}

	@Override
	public void update() 
	{

	}

}