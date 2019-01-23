package presentacion.controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.Campa�aDTO;
import modelo.GestorCampa�a;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaEditarCampa�a;
import util.ValidadorCampos;
import util.ValidadorLogico;

public class ControladorVentanaEditarCampa�a implements SujetoObservable
{

	private VentanaEditarCampa�a ventana;
	private ArrayList<Observador> observadores;
	private Campa�aDTO campa�a_a_editar;
	
	public ControladorVentanaEditarCampa�a(ControladorPanelGestionCampa�as control, Campa�aDTO campa�a)
	{
		ventana = new VentanaEditarCampa�a();
		this.campa�a_a_editar = campa�a;
		ventana.getBtnAceptar().addActionListener(e -> this.editarCampa�a());
		observadores = new ArrayList<Observador>();
		observadores.add(control);
	}
	
	public void initialize()
	{
		this.ventana.getA�oChooser().setYear(Integer.parseInt(this.campa�a_a_editar.getA�o()));
		this.ventana.getNumeroSpinner().setValue(Integer.parseInt(this.campa�a_a_editar.getNumero()));
		this.ventana.getCierreChooser().setDate(this.campa�a_a_editar.getCierre());
		ventana.show();
	}

	public void editarCampa�a()
	{
		if(ValidadorCampos.campoVacio(Integer.toString(this.ventana.getA�oChooser().getValue()))||
		   ValidadorCampos.campoVacio(this.ventana.getNumeroSpinner().getValue().toString())||
		   this.ventana.getCierreChooser().getDate()==null)
		{
			JOptionPane.showMessageDialog(null, "�Faltan completar campos!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(ValidadorLogico.existeA�oNumeroCampa�aEditar(this.campa�a_a_editar.getIdCampa�a(), Integer.toString(this.ventana.getA�oChooser().getValue()), this.ventana.getNumeroSpinner().getValue().toString(), GestorCampa�a.getInstance().readAll())) 
		{
			JOptionPane.showMessageDialog(null, "�A�o y Numero de campa�a ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;									
		}
		this.campa�a_a_editar.setA�o(Integer.toString(this.ventana.getA�oChooser().getValue()));
		this.campa�a_a_editar.setNumero(this.ventana.getNumeroSpinner().getValue().toString());
		this.campa�a_a_editar.setCierre(this.ventana.getCierreChooser().getDate());
		GestorCampa�a.getInstance().update(this.campa�a_a_editar);
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