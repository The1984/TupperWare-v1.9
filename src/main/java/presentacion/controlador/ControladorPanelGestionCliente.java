package presentacion.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ClienteDTO;
import modelo.GestorClientes;
import observer.Observador;
import presentacion.vista.PanelGestionCliente;

public class ControladorPanelGestionCliente implements KeyListener, Observador
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
		this.panelCliente.getBtnAgregar().addActionListener(e -> this.agregarCliente());
		this.panelCliente.getBtnEditar().addActionListener(e -> this.editarCliente());
		this.panelCliente.getBtnEliminar().addActionListener(e -> this.eliminarCliente());
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
	
	public void filtrarTablaTipo(String cadena) 
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

	private void agregarCliente() 
	{
		ControladorVentanaAgregarCliente contro = new ControladorVentanaAgregarCliente(this);
		contro.initialize();
	}
	
	private void editarCliente() 
	{
		ClienteDTO clientSelect = this.clientes_en_tabla.get(this.panelCliente.getTablaClientes().getSelectedRow());
		ControladorVentanaEditarCliente contro = new ControladorVentanaEditarCliente(this, clientSelect);
		contro.initialize();
	}
	
	public void eliminarCliente()
	{
		ClienteDTO clientSelect = this.clientes_en_tabla.get(this.panelCliente.getTablaClientes().getSelectedRow());
		if(clientSelect.getCompras().size()==0)
		{
			if(JOptionPane.showConfirmDialog(null,"<html>¿Est\u00E1 seguro que quiere eliminar al cliente?</html>", "Eliminar Cliente",JOptionPane.YES_NO_OPTION)==0) 
			{
				GestorClientes.getInstance().delete(clientSelect);
				this.update();
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Este cliente posee comprar no puede eliminarse");
			return;			
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
		this.filtrarTablaTipo(cadena);	
	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void update() 
	{
		this.llenarTabla();
		this.panelCliente.repaint();	
	}

}