package presentacion.controlador;

import java.util.ArrayList;

import dto.CampañaDTO;
import modelo.GestorCampaña;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaAgregarCampaña;

public class ControladorVentanaAgregarCampaña implements SujetoObservable
{

	private VentanaAgregarCampaña ventana;
	private ArrayList<Observador> observadores;
	
	public ControladorVentanaAgregarCampaña(ControladorPanelGestionCampañas control)
	{
		ventana = new VentanaAgregarCampaña();
		ventana.getBtnAceptar().addActionListener(e -> this.registrarCampaña());
		observadores = new ArrayList<Observador>();
		observadores.add(control);
	}
	
	public void initialize()
	{
		ventana.show();
	}

	public void registrarCampaña()
	{
		CampañaDTO newCampaña = new CampañaDTO();
		newCampaña.setAño(Integer.toString(this.ventana.getAñoChooser().getValue()));
		newCampaña.setNumero(this.ventana.getNumeroSpinner().getValue().toString());
		newCampaña.setCierre(this.ventana.getCierreChooser().getDate());
		GestorCampaña.getInstance().insert(newCampaña);
		this.ventana.close();
		this.notificar();
	}
	
	@Override
	public void notificar() 
	{
		for(Observador o : observadores) 
		{
			o.update();
		}
	}
	
}