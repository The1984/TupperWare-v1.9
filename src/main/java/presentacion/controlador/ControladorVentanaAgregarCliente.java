package presentacion.controlador;

import java.util.ArrayList;

import dto.ClienteDTO;
import modelo.GestorClientes;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaAgregarCliente;

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
		ClienteDTO newCliente = new ClienteDTO();
		newCliente.setNombre(this.ventana.getTxtNombre().getText());
		newCliente.setApellido(this.ventana.getTxtApellido().getText());
		newCliente.setDireccion(this.ventana.getTxtDireccion().getText());
		newCliente.setCelular(this.ventana.getTxtCelular().getText());
		newCliente.setEmail(this.ventana.getTxtEmail().getText().toLowerCase());
		newCliente.setClienteAlDia(false);
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
