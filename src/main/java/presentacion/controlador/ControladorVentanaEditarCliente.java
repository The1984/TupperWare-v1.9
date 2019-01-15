package presentacion.controlador;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import dto.ClienteDTO;
import modelo.GestorClientes;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaEditarCliente;
import util.ValidadorCampos;
import util.ValidadorLogico;

public class ControladorVentanaEditarCliente implements SujetoObservable
{

	private VentanaEditarCliente ventana;
	private ArrayList<Observador> observadores;
	private ClienteDTO cliente_a_editar;
	
	public ControladorVentanaEditarCliente(ControladorPanelGestionClientes control, ClienteDTO cliente)
	{
		ventana = new VentanaEditarCliente();
		ventana.getBtnAceptar().addActionListener(e -> this.editarCliente());
		observadores = new ArrayList<Observador>();
		observadores.add(control);
		cliente_a_editar = cliente;
	}
	
	public void initialize()
	{
		ventana.getTxtNombre().setText(this.cliente_a_editar.getNombre());
		ventana.getTxtApellido().setText(this.cliente_a_editar.getApellido());
		ventana.getTxtDireccion().setText(this.cliente_a_editar.getDireccion());
		ventana.getTxtCelular().setText(this.cliente_a_editar.getCelular());
		ventana.getTxtEmail().setText(this.cliente_a_editar.getEmail());
		ventana.show();
	}

	public void editarCliente()
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
		if(ValidadorLogico.existeNombreApellidoEditar(cliente_a_editar.getIdCliente(), this.ventana.getTxtNombre().getText(), this.ventana.getTxtApellido().getText(), GestorClientes.getInstance().readAll())) 
		{
			JOptionPane.showMessageDialog(null, "¡Nombre y Apellido ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;									
		}
		if(ValidadorLogico.existeEmailEditar(cliente_a_editar.getIdCliente(), this.ventana.getTxtEmail().getText().toLowerCase(), GestorClientes.getInstance().readAll())) 
		{
			JOptionPane.showMessageDialog(null, "¡Email ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;									
		}
		cliente_a_editar.setNombre(this.ventana.getTxtNombre().getText());
		cliente_a_editar.setApellido(this.ventana.getTxtApellido().getText());
		cliente_a_editar.setDireccion(this.ventana.getTxtDireccion().getText());
		cliente_a_editar.setCelular(this.ventana.getTxtCelular().getText());
		cliente_a_editar.setEmail(this.ventana.getTxtEmail().getText().toLowerCase());
		GestorClientes.getInstance().update(cliente_a_editar);
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
