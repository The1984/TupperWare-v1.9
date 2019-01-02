package presentacion.controlador;

import java.util.ArrayList;

import dto.CampañaDTO;
import modelo.GestorCampaña;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaEditarCampaña;

public class ControladorVentanaEditarCampaña implements SujetoObservable
{

	private VentanaEditarCampaña ventana;
	private ArrayList<Observador> observadores;
	private CampañaDTO campaña_a_editar;
	
	public ControladorVentanaEditarCampaña(ControladorPanelGestionCampañas control, CampañaDTO campaña)
	{
		ventana = new VentanaEditarCampaña();
		this.campaña_a_editar = campaña;
		ventana.getBtnAceptar().addActionListener(e -> this.editarCampaña());
		observadores = new ArrayList<Observador>();
		observadores.add(control);
	}
	
	public void initialize()
	{
		this.ventana.getAñoChooser().setYear(Integer.parseInt(this.campaña_a_editar.getAño()));
		this.ventana.getNumeroSpinner().setValue(Integer.parseInt(this.campaña_a_editar.getNumero()));
		this.ventana.getCierreChooser().setDate(this.campaña_a_editar.getCierre());
		ventana.show();
	}

	public void editarCampaña()
	{
		this.campaña_a_editar.setAño(Integer.toString(this.ventana.getAñoChooser().getValue()));
		this.campaña_a_editar.setNumero(this.ventana.getNumeroSpinner().getValue().toString());
		this.campaña_a_editar.setCierre(this.ventana.getCierreChooser().getDate());
		GestorCampaña.getInstance().update(this.campaña_a_editar);
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