package presentacion.controlador;

import java.util.ArrayList;

import dto.Campa�aDTO;
import dto.PremioDTO;
import modelo.GestorPremios;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaAgregarPremio;

public class ControladorVentanaAgregarPremio implements SujetoObservable
{

	private VentanaAgregarPremio ventana;
	private ArrayList<Observador> observadores;
	private Campa�aDTO campa�a;
	private PremioDTO newPremio;
	
	public ControladorVentanaAgregarPremio(ControladorVentanaPremiosABM control, Campa�aDTO campa�a)
	{
		this.ventana = new VentanaAgregarPremio();
		this.campa�a = campa�a;
		this.ventana.getBtnAceptar().addActionListener(e -> this.registrarPremio());
		this.observadores = new ArrayList<Observador>();
		this.observadores.add(control);
		this.newPremio = new PremioDTO();
	}
	
	public void initialize()
	{
		ventana.show();
	}

	private void registrarPremio()
	{	
		this.newPremio.setNombre(this.ventana.getTxtNombre().getText());
		this.newPremio.setDescripcion(this.ventana.getTextArea().getText());
		this.newPremio.setUnidadesMinimas(Integer.parseInt(this.ventana.getSpinnerUnidadesMinimas().getValue().toString()));
		this.newPremio.setRecibido(false);
		this.newPremio.setIdCampa�a(this.campa�a.getIdCampa�a());
		GestorPremios.getInstance().insert(newPremio);
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