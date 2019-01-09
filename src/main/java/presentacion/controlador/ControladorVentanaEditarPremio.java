package presentacion.controlador;

import java.util.ArrayList;

import dto.PremioDTO;
import modelo.GestorPremios;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaEditarPremio;

public class ControladorVentanaEditarPremio implements SujetoObservable
{

	private VentanaEditarPremio ventana;
	private ArrayList<Observador> observadores;
	private PremioDTO premio;
	
	public ControladorVentanaEditarPremio(ControladorVentanaPremiosABM control, PremioDTO premio)
	{
		this.ventana = new VentanaEditarPremio();
		this.premio = premio;
		this.ventana.getTxtNombre().setText(this.premio.getNombre());
		this.ventana.getTextArea().setText(this.premio.getDescripcion());
		this.ventana.getSpinnerUnidadesMinimas().setValue(this.premio.getUnidadesMinimas());
		this.ventana.getChckbxRecibido().setSelected(this.premio.getRecibido());
		//
		// Habilitar o no edicion de Recibido...
		//
		this.ventana.getBtnAceptar().addActionListener(e -> this.editarPremio());
		this.observadores = new ArrayList<Observador>();
		this.observadores.add(control);
	}
	
	public void initialize()
	{
		ventana.show();
	}

	private void editarPremio()
	{	
		this.premio.setNombre(this.ventana.getTxtNombre().getText());
		this.premio.setDescripcion(this.ventana.getTextArea().getText());
		this.premio.setUnidadesMinimas(Integer.parseInt(this.ventana.getSpinnerUnidadesMinimas().getValue().toString()));
		this.premio.setRecibido(this.ventana.getChckbxRecibido().isSelected());
		GestorPremios.getInstance().update(this.premio);
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