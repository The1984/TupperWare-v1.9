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
		this.ventana = new VentanaEditarCliente();
		this.ventana.getBtnAceptar().addActionListener(e -> this.editarCliente());
		this.observadores = new ArrayList<Observador>();
		this.observadores.add(control);
		this.cliente_a_editar = cliente;
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
		   ValidadorCampos.campoVacio(this.ventana.getTxtApellido().getText()))
		{
			JOptionPane.showMessageDialog(null, "�Los campos nombre y apellido son obligatorios!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(!ValidadorCampos.validarNombre(this.ventana.getTxtNombre().getText()))
		{
			JOptionPane.showMessageDialog(null, "�Formato de nombre invalido!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!ValidadorCampos.validarApellido(this.ventana.getTxtApellido().getText()))
		{
			JOptionPane.showMessageDialog(null, "�Formato de apellido invalido!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(ValidadorLogico.existeNombreApellidoEditar(this.cliente_a_editar.getIdCliente(), this.ventana.getTxtNombre().getText(), this.ventana.getTxtApellido().getText(), GestorClientes.getInstance().readAll())) 
		{
			JOptionPane.showMessageDialog(null, "�Nombre y Apellido ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;									
		}
		if(!ValidadorCampos.campoVacio(this.ventana.getTxtCelular().getText()))
		{
			if(!ValidadorCampos.validarCelular(this.ventana.getTxtCelular().getText()))
			{
				JOptionPane.showMessageDialog(null, "�Formato de celular invalido!", "Error", JOptionPane.ERROR_MESSAGE);
				return;			
			}			
		}
		if(!ValidadorCampos.campoVacio(this.ventana.getTxtEmail().getText()))
		{
			if(!ValidadorCampos.validarMail(this.ventana.getTxtEmail().getText()))
			{
				JOptionPane.showMessageDialog(null, "�Formato de e-mail invalido!", "Error", JOptionPane.ERROR_MESSAGE);
				return;						
			}
			if(ValidadorLogico.existeEmailEditar(this.cliente_a_editar.getIdCliente(), this.ventana.getTxtEmail().getText().toLowerCase(), GestorClientes.getInstance().readAll())) 
			{
				JOptionPane.showMessageDialog(null, "�Email ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
				return;									
			}
		}
		
		this.cliente_a_editar.setNombre(this.ventana.getTxtNombre().getText());
		this.cliente_a_editar.setApellido(this.ventana.getTxtApellido().getText());
		this.cliente_a_editar.setDireccion(this.ventana.getTxtDireccion().getText());
		this.cliente_a_editar.setCelular(this.ventana.getTxtCelular().getText());
		this.cliente_a_editar.setEmail(this.ventana.getTxtEmail().getText().toLowerCase());
		GestorClientes.getInstance().update(this.cliente_a_editar);
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
