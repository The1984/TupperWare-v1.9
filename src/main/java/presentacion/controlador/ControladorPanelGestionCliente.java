package presentacion.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import dto.ClienteDTO;
import modelo.GestorClientes;
import presentacion.vista.PanelGestionCliente;

public class ControladorPanelGestionCliente implements KeyListener
{

	private List<ClienteDTO> clientes_en_tabla;
	private List<ClienteDTO> clientes_filtrados;
	private PanelGestionCliente panelCliente;

	public ControladorPanelGestionCliente(PanelGestionCliente panel)
	{

		this.panelCliente = panel;
		this.clientes_en_tabla = null;
		this.clientes_filtrados = new ArrayList<ClienteDTO>();
		this.panelCliente.getTextFiltro().addKeyListener(this);
		
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.panelCliente.repaint();
	}
	
	private void llenarTabla()
	{
		this.panelCliente.getModelClientes().setRowCount(0); //Para vaciar la tabla
		this.panelCliente.getModelClientes().setColumnCount(0);
		this.panelCliente.getModelClientes().setColumnIdentifiers(this.panelCliente.getNombreColumnas());
		
		this.clientes_en_tabla = GestorClientes.getInstance().readAll();
		for (ClienteDTO cliente : clientes_en_tabla)
		{
			Object[] fila = {
								cliente.getNombre(),
								cliente.getApellido(),
								cliente.getDireccion(),
								cliente.getCelular(),
								cliente.getEmail()
							};
			this.panelCliente.getModelClientes().addRow(fila);
		}			
	}

	private void reiniciarTabla() 
	{
		this.panelCliente.getModelClientes().setRowCount(0); // Para vaciar la tabla
		this.panelCliente.getModelClientes().setColumnCount(0);
		this.panelCliente.getModelClientes().setColumnIdentifiers(this.panelCliente.getNombreColumnas());
	}
	
	public void filtrarTablaTipo(String cadena, String tipo) 
	{
		reiniciarTabla();
		
		clientes_filtrados.clear();
		
		for (ClienteDTO cliente : clientes_en_tabla) 
		{
			String getNombre = cliente.getNombre().toUpperCase()+
							   cliente.getApellido().toUpperCase()+
							   cliente.getDireccion().toUpperCase()+
							   cliente.getCelular().toUpperCase()+
							   cliente.getEmail().toUpperCase();
			if (getNombre.indexOf(cadena.toUpperCase()) != -1) 
			{
					clientes_filtrados.add(cliente);
					Object[] fila = 
						{ 
							cliente.getNombre(),
							cliente.getApellido(),
							cliente.getDireccion(),
							cliente.getCelular(),
							cliente.getEmail()
						};
					this.panelCliente.getModelClientes().addRow(fila);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		String cadena = this.panelCliente.getTextFiltro().getText();
		String tipo = "nombre";
		this.filtrarTablaTipo(cadena, tipo);	
	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

}