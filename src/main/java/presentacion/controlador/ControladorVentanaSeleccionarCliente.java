package presentacion.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import dto.ClienteDTO;
import modelo.GestorClientes;
import presentacion.vista.VentanaSeleccionarCliente;

public class ControladorVentanaSeleccionarCliente implements KeyListener, MouseListener
{
	
	private ControladorVentanaComprarProducto controlador;
	private VentanaSeleccionarCliente ventana;
	private List<ClienteDTO> clientes_filtrados;
	
	public ControladorVentanaSeleccionarCliente(ControladorVentanaComprarProducto control)
	{
		this.ventana = new VentanaSeleccionarCliente();
		this.controlador = control;
		this.clientes_filtrados = new ArrayList<ClienteDTO>();
		this.ventana.getTxtFiltro().addKeyListener(this);
		this.ventana.getTablaCliente().addMouseListener(this);
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.ventana.show();
	}

	
	private void llenarTabla()
	{
		this.reiniciarTabla();
		
		for (ClienteDTO cliente : GestorClientes.getInstance().readAll())
		{
			Object[] fila = {
								cliente.getNombre(),
								cliente.getApellido(),
								cliente.getCelular(),
								cliente.getEmail()
							};
			this.clientes_filtrados.add(cliente);
			this.ventana.getModelCliente().addRow(fila);
		}			
	}

	private void reiniciarTabla() 
	{
		this.ventana.getModelCliente().setRowCount(0); // Para vaciar la tabla
		this.ventana.getModelCliente().setColumnCount(0);
		this.ventana.getModelCliente().setColumnIdentifiers(this.ventana.getNombreColumnasCliente());
	}
	
	public void filtrarTabla(String cadena) 
	{
		reiniciarTabla();
		
		clientes_filtrados.clear();
		
		for (ClienteDTO cliente : GestorClientes.getInstance().readAll())
		{
			String getNombre = cliente.getNombre().toUpperCase()+
							   cliente.getApellido().toUpperCase()+
							   cliente.getCelular().toUpperCase()+
							   cliente.getEmail().toUpperCase();
			if (getNombre.indexOf(cadena.toUpperCase()) != -1) 
			{
				clientes_filtrados.add(cliente);
				Object[] fila = 
					{ 
						cliente.getNombre(),
						cliente.getApellido(),
						cliente.getCelular(),
						cliente.getEmail()
					};
				this.ventana.getModelCliente().addRow(fila);
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getClickCount()==2)
		{
			ClienteDTO clientSelect = this.clientes_filtrados.get(this.ventana.getTablaCliente().getSelectedRow());
			this.controlador.setearCliente(clientSelect);
			this.controlador.setearTextCliente();
			this.ventana.close();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		String cadena = this.ventana.getTxtFiltro().getText();
		this.filtrarTabla(cadena);		
	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}
	
}