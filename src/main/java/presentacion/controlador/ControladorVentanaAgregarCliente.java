package presentacion.controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.ClienteDTO;
import modelo.GestorClientes;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaAgregarCliente;
import util.ValidadorCampos;
import util.ValidadorLogico;

public class ControladorVentanaAgregarCliente implements SujetoObservable
{

	private VentanaAgregarCliente ventana;
	private ArrayList<Observador> observadores;
	
	public ControladorVentanaAgregarCliente(ControladorPanelGestionCliente control)
	{
		ventana = new VentanaAgregarCliente();
		ventana.getBtnAceptar().addActionListener(e -> this.registrarCliente());
		observadores = new ArrayList<Observador>();
		observadores.add(control);
	}
	
	public void initialize()
	{
		ventana.show();
	}

	public void registrarCliente()
	{
		if(ValidadorCampos.campoVacio(this.ventana.getTxtNombre().getText())||
		   ValidadorCampos.campoVacio(this.ventana.getTxtApellido().getText())||
		   ValidadorCampos.campoVacio(this.ventana.getTxtDireccion().getText())||
		   ValidadorCampos.campoVacio(this.ventana.getTxtCelular().getText())||
		   ValidadorCampos.campoVacio(this.ventana.getTxtEmail().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡Falta completar campos!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(!ValidadorCampos.validarNombre(this.ventana.getTxtNombre().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡Formato de nombre invalido!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!ValidadorCampos.validarApellido(this.ventana.getTxtApellido().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡Formato de apellido invalido!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!ValidadorCampos.validarCelular(this.ventana.getTxtCelular().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡Formato de celular invalido!", "Error", JOptionPane.ERROR_MESSAGE);
			return;			
		}
		if(!ValidadorCampos.validarMail(this.ventana.getTxtEmail().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡Formato de e-mail invalido!", "Error", JOptionPane.ERROR_MESSAGE);
			return;						
		}
		if(ValidadorLogico.existeNombreApellidoAgregar(this.ventana.getTxtNombre().getText(), this.ventana.getTxtApellido().getText(), GestorClientes.getInstance().readAll())) 
		{
			JOptionPane.showMessageDialog(null, "¡Nombre y Apellido ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;									
		}
		if(ValidadorLogico.existeEmailAgregar(this.ventana.getTxtEmail().getText().toLowerCase(), GestorClientes.getInstance().readAll())) 
		{
			JOptionPane.showMessageDialog(null, "¡Email ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;									
		}
		ClienteDTO newCliente = new ClienteDTO();
		newCliente.setNombre(this.ventana.getTxtNombre().getText());
		newCliente.setApellido(this.ventana.getTxtApellido().getText());
		newCliente.setDireccion(this.ventana.getTxtDireccion().getText());
		newCliente.setCelular(this.ventana.getTxtCelular().getText());
		newCliente.setEmail(this.ventana.getTxtEmail().getText().toLowerCase());
		GestorClientes.getInstance().insert(newCliente);
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
