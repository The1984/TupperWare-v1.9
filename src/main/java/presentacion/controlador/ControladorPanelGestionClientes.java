package presentacion.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.ClienteDTO;
import modelo.GestorClientes;
import observer.Observador;
import presentacion.vista.PanelGestionClientes;

public class ControladorPanelGestionClientes implements KeyListener, Observador
{

	private List<ClienteDTO> clientes_en_tabla;
	private List<ClienteDTO> clientes_filtrados;
	private PanelGestionClientes panelCliente;

	public ControladorPanelGestionClientes(PanelGestionClientes panel)
	{
		this.panelCliente = panel;
		this.clientes_en_tabla = null;
		this.clientes_filtrados = new ArrayList<ClienteDTO>();
		this.clientes_filtrados = GestorClientes.getInstance().readAll();
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
		this.reiniciarTabla();
		
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
			this.panelCliente.getModelCliente().addRow(fila);
		}			
	}

	private void reiniciarTabla() 
	{
		this.panelCliente.getModelCliente().setRowCount(0); // Para vaciar la tabla
		this.panelCliente.getModelCliente().setColumnCount(0);
		this.panelCliente.getModelCliente().setColumnIdentifiers(this.panelCliente.getNombreColumnasCliente());
	}
	
	public void filtrarTabla(String cadena) 
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
				this.panelCliente.getModelCliente().addRow(fila);
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
		if(this.panelCliente.getTablaCliente().getSelectedRow()==-1)
		{
			JOptionPane.showMessageDialog(null, "¡Seleccione un cliente!");
			return;
		}
		ClienteDTO clientSelect = this.clientes_filtrados.get(this.panelCliente.getTablaCliente().getSelectedRow());
		ControladorVentanaEditarCliente contro = new ControladorVentanaEditarCliente(this, clientSelect);
		contro.initialize();
	}
	
	public void eliminarCliente()
	{
		if(this.panelCliente.getTablaCliente().getSelectedRow()==-1)
		{
			JOptionPane.showMessageDialog(null, "¡Seleccione un cliente!");
			return;
		}
//		ClienteDTO clientSelect = this.clientes_filtrados.get(this.panelCliente.getTablaCliente().getSelectedRow());
//		if(clientSelect.getCompras().size()==0)
//		{
//			if(JOptionPane.showConfirmDialog(null,"<html>¿Est\u00E1 seguro que quiere eliminar al cliente?</html>", "Eliminar Cliente",JOptionPane.YES_NO_OPTION)==0) 
//			{
//				GestorClientes.getInstance().delete(clientSelect);
//				this.update();
//			}
//		}
//		else
//		{
//			JOptionPane.showMessageDialog(null, "Este cliente posee comprar no puede eliminarse");
//			return;			
//		}
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
		this.filtrarTabla(cadena);	
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
		this.clientes_filtrados = GestorClientes.getInstance().readAll();
		this.panelCliente.getTextFiltro().setText("");
		this.panelCliente.repaint();	
	}

}