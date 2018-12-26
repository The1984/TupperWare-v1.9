package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.GridLayout;

public class PanelGestionCliente extends JPanel 
{

	private static final long serialVersionUID = -2645130709335183446L;

	private JTable tablaClientes;
	private DefaultTableModel modelClientes;
	private String[] nombreColumnas = { "Nombre","Apellido","Dirección","Teléfono","E-Mail"};
	
	public PanelGestionCliente() 
	{
		setBackground(Color.ORANGE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(63)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		
		JButton btnAgregar = new JButton("Agregar");
		panel.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		panel.add(btnEditar);
		
		JButton btnEliminar = new JButton("Eliminar");
		panel.add(btnEliminar);
		
		modelClientes = new DefaultTableModel(null, nombreColumnas) 
			{
				private static final long serialVersionUID = 1L;	
				public boolean isCellEditable(int row, int column) 
				{
					return false;
				}
			};
		
		tablaClientes = new JTable(modelClientes);
		scrollPane.setViewportView(tablaClientes);
		setLayout(groupLayout);
		
	}
	
	public JTable getTablaClientes() 
	{
		return tablaClientes;
	}

	public void setTablaClientes(JTable tablaClientes) 
	{
		this.tablaClientes = tablaClientes;
	}

	public DefaultTableModel getModelClientes() 
	{
		return modelClientes;
	}

	public void setModelClientes(DefaultTableModel modelClientes) 
	{
		this.modelClientes = modelClientes;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}

	public void setNombreColumnas(String[] nombreColumnas) 
	{
		this.nombreColumnas = nombreColumnas;
	}
	
}