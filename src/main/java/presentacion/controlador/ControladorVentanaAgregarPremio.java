package presentacion.controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.CampañaDTO;
import dto.PremioDTO;
import modelo.GestorPremios;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaAgregarPremio;
import util.ValidadorCampos;
import util.ValidadorLogico;

public class ControladorVentanaAgregarPremio implements SujetoObservable
{

	private VentanaAgregarPremio ventana;
	private ArrayList<Observador> observadores;
	private CampañaDTO campaña;
	private PremioDTO newPremio;
	
	public ControladorVentanaAgregarPremio(ControladorVentanaPremiosABM control, CampañaDTO campaña)
	{
		this.ventana = new VentanaAgregarPremio();
		this.campaña = campaña;
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
		if(ValidadorLogico.existePremioNombreAgregar(this.ventana.getTxtNombre().getText(), this.campaña.getPremios())) 
		{
			JOptionPane.showMessageDialog(null, "¡Nombre de premio ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;			
		}
		this.newPremio.setNombre(this.ventana.getTxtNombre().getText());
		this.newPremio.setDescripcion(this.ventana.getTextArea().getText());
		this.newPremio.setUnidadesMinimas(Integer.parseInt(this.ventana.getSpinnerUnidadesMinimas().getValue().toString()));
		this.newPremio.setRecibido(false);
		this.newPremio.setIdCampaña(this.campaña.getIdCampaña());
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