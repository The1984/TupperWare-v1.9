package presentacion.controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.CampañaDTO;
import dto.PremioDTO;
import modelo.GestorPremios;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaEditarPremio;
import util.ValidadorCampos;
import util.ValidadorLogico;

public class ControladorVentanaEditarPremio implements SujetoObservable
{

	private VentanaEditarPremio ventana;
	private ArrayList<Observador> observadores;
	private CampañaDTO campaña;
	private PremioDTO premio;
	
	public ControladorVentanaEditarPremio(ControladorVentanaPremiosABM control, PremioDTO premio, CampañaDTO campaña, Boolean habilitarRecibido)
	{
		this.ventana = new VentanaEditarPremio();
		this.campaña = campaña;
		this.premio = premio;
		this.ventana.getTxtNombre().setText(this.premio.getNombre());
		this.ventana.getTextArea().setText(this.premio.getDescripcion());
		this.ventana.getSpinnerUnidadesMinimas().setValue(this.premio.getUnidadesMinimas());
		this.ventana.getChckbxRecibido().setSelected(this.premio.getRecibido());
		this.ventana.getChckbxRecibido().setEnabled(habilitarRecibido);
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
		if(ValidadorCampos.campoVacio(this.ventana.getTxtNombre().getText())||
		   ValidadorCampos.campoVacio(this.ventana.getTextArea().getText())||
		   ValidadorCampos.campoVacio(this.ventana.getSpinnerUnidadesMinimas().getValue().toString()))
		{
			JOptionPane.showMessageDialog(null, "¡Los campos nombre, descripcion y unidades son obligatorios!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(!ValidadorCampos.isNumeric(this.ventana.getSpinnerUnidadesMinimas().getValue().toString()))
		{
			JOptionPane.showMessageDialog(null, "¡Unidades min. no es un numero!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(ValidadorLogico.existePremioNombreEditar(this.premio.getIdPremio() ,this.ventana.getTxtNombre().getText(), this.campaña.getPremios())) 
		{
			JOptionPane.showMessageDialog(null, "¡Nombre de premio ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;			
		}
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