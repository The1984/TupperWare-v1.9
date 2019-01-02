package presentacion.controlador;

import java.util.ArrayList;

import dto.Campa�aDTO;
import modelo.GestorCampa�a;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaAgregarCampa�a;

public class ControladorVentanaAgregarCampa�a implements SujetoObservable
{

	private VentanaAgregarCampa�a ventana;
	private ArrayList<Observador> observadores;
	
	public ControladorVentanaAgregarCampa�a(ControladorPanelGestionCampa�as control)
	{
		ventana = new VentanaAgregarCampa�a();
		ventana.getBtnAceptar().addActionListener(e -> this.registrarCampa�a());
		observadores = new ArrayList<Observador>();
		observadores.add(control);
	}
	
	public void initialize()
	{
		ventana.show();
	}

	public void registrarCampa�a()
	{
		Campa�aDTO newCampa�a = new Campa�aDTO();
		newCampa�a.setA�o(Integer.toString(this.ventana.getA�oChooser().getValue()));
		newCampa�a.setNumero(this.ventana.getNumeroSpinner().getValue().toString());
		newCampa�a.setCierre(this.ventana.getCierreChooser().getDate());
		GestorCampa�a.getInstance().insert(newCampa�a);
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