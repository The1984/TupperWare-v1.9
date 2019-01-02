package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextField;

public class PanelGestionClientes extends JPanel 
{

	private static final long serialVersionUID = -2645130709335183446L;

	private JTable tablaCliente;
	private DefaultTableModel modelCliente;
	private String[] nombreColumnasCliente = { "Nombre","Apellido","Dirección","Celular","E-mail"};
	private JTextField textFiltro;
	private JPanel panel;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	
	public PanelGestionClientes() 
	{
		setBackground(Color.ORANGE);
		
		JScrollPane scrollPane_cliente = new JScrollPane();
		scrollPane_cliente.setBorder(new TitledBorder(null, "Clientes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		textFiltro = new JTextField();
		textFiltro.setColumns(10);
		
		panel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_cliente, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(52)
							.addComponent(textFiltro, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(20)
					.addComponent(textFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_cliente, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnAgregar = new JButton("Agregar");
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		panel.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		panel.add(btnEliminar);
		
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
		setLayout(groupLayout);	
	}
	
	public JTable getTablaCliente() 
	{
		return tablaCliente;
	}

	public void setTablaCliente(JTable tablaCliente) 
	{
		this.tablaCliente = tablaCliente;
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
	
	public JTextField getTextFiltro() 
	{
		return textFiltro;
	}

	public void setTextFiltro(JTextField textFiltro) 
	{
		this.textFiltro = textFiltro;
	}

	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}

	public JButton getBtnEditar() 
	{
		return btnEditar;
	}

	public JButton getBtnEliminar() 
	{
		return btnEliminar;
	}
	
}