package presentacion.controlador;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import dto.CampañaDTO;
import modelo.GestorCampaña;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaAgregarCampaña;
import util.ValidadorCampos;
import util.ValidadorLogico;

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
		if(ValidadorCampos.campoVacio(Integer.toString(this.ventana.getAñoChooser().getValue()))||
		   ValidadorCampos.campoVacio(this.ventana.getNumeroSpinner().getValue().toString())||
		   this.ventana.getCierreChooser().getDate()==null)
		{
			JOptionPane.showMessageDialog(null, "¡Faltan completar campos!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(ValidadorLogico.existeAñoNumeroCampañaAgregar(Integer.toString(this.ventana.getAñoChooser().getValue()), this.ventana.getNumeroSpinner().getValue().toString(), GestorCampaña.getInstance().readAll())) 
		{
			JOptionPane.showMessageDialog(null, "¡Año y Numero de campaña ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;									
		}
		if(this.ventana.getCierreChooser().getDate().before(new Date())) 
		{
			JOptionPane.showMessageDialog(null, "¡Fecha de cierre debe ser posterior a la fecha actual!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;			
		}
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