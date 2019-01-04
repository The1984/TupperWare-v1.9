package presentacion.vista;

import javax.swing.JDialog;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class VentanaSeleccionarCliente
{

	private JDialog frame;
	private JTextField txtFiltro;
	
	private DefaultTableModel modelCliente;
	private String[] nombreColumnasCliente = { "Nombre","Apellido","Celular","E-mail"};
	private JTable tablaCliente;
		
	public VentanaSeleccionarCliente() 
	{
		frame = new JDialog();
		frame.setTitle("Seleccionar Cliente");
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 353, 324);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(39, 11, 86, 20);
		frame.getContentPane().add(txtFiltro);
		txtFiltro.setColumns(10);
		
		JScrollPane scrollPane_cliente = new JScrollPane();
		scrollPane_cliente.setBounds(10, 52, 317, 222);
		frame.getContentPane().add(scrollPane_cliente);
		
		modelCliente = new DefaultTableModel(null, nombreColumnasCliente) 
		{
			private static final long serialVersionUID = 1L;	
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
	
		tablaCliente = new JTable(modelCliente);
		scrollPane_cliente.setViewportView(tablaCliente);
		
		frame.setLocationRelativeTo(null);
	}

	public DefaultTableModel getModelCliente() 
	{
		return modelCliente;
	}

	public void setModelCliente(DefaultTableModel modelCliente) 
	{
		this.modelCliente = modelCliente;
	}

	public String[] getNombreColumnasCliente() 
	{
		return nombreColumnasCliente;
	}

	public void setNombreColumnasCliente(String[] nombreColumnasCliente) 
	{
		this.nombreColumnasCliente = nombreColumnasCliente;
	}

	public JTable getTablaCliente() 
	{
		return tablaCliente;
	}

	public void setTablaCliente(JTable tablaCliente) 
	{
		this.tablaCliente = tablaCliente;
	}

	public JTextField getTxtFiltro() 
	{
		return txtFiltro;
	}
	
	public void show() 
	{
		frame.setVisible(true);
	}
	
	public void close()
	{
		frame.dispose();
	}
}