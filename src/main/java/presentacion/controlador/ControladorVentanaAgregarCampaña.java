package presentacion.controlador;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import dto.Campa�aDTO;
import modelo.GestorCampa�a;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaAgregarCampa�a;
import util.ValidadorCampos;
import util.ValidadorLogico;

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
		if(ValidadorCampos.campoVacio(Integer.toString(this.ventana.getA�oChooser().getValue()))||
		   ValidadorCampos.campoVacio(this.ventana.getNumeroSpinner().getValue().toString())||
		   this.ventana.getCierreChooser().getDate()==null)
		{
			JOptionPane.showMessageDialog(null, "�Faltan completar campos!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(ValidadorLogico.existeA�oNumeroCampa�aAgregar(Integer.toString(this.ventana.getA�oChooser().getValue()), this.ventana.getNumeroSpinner().getValue().toString(), GestorCampa�a.getInstance().readAll())) 
		{
			JOptionPane.showMessageDialog(null, "�A�o y Numero de campa�a ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;									
		}
		if(this.ventana.getCierreChooser().getDate().before(new Date())) 
		{
			JOptionPane.showMessageDialog(null, "�Fecha de cierre debe ser posterior a la fecha actual!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;			
		}
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