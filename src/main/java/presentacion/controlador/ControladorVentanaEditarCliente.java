package presentacion.controlador;

import java.util.ArrayList;

import dto.ClienteDTO;
import modelo.GestorClientes;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaEditarCliente;

public class ControladorVentanaEditarCliente implements SujetoObservable
{

	private VentanaEditarCliente ventana;
	private ArrayList<Observador> observadores;
	private ClienteDTO cliente_a_editar;
	
	public ControladorVentanaEditarCliente(ControladorPanelGestionCliente control, ClienteDTO cliente)
	{
		ventana = new VentanaEditarCliente();
		ventana.getBtnAceptar().addActionListener(e -> this.EditarCliente());
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

	public void EditarCliente()
	{
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
