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
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class PanelGestionCliente extends JPanel 
{

	private static final long serialVersionUID = -2645130709335183446L;

	private JTable tablaClientes;
	private DefaultTableModel modelClientes;
	private String[] nombreColumnas = { "Nombre","Apellido","Dirección","Celular","E-mail"};
	private JTextField textFiltro;
	private JButton btnAgregar;
	private JButton btnEditar ;
	private JButton btnEliminar;
	
	public PanelGestionCliente() 
	{
		setBackground(Color.ORANGE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel PanelBotonera = new JPanel();
		
		textFiltro = new JTextField();
		textFiltro.setColumns(10);
		
		JLabel lblFiltro = new JLabel("Filtro:");
		lblFiltro.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(PanelBotonera, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFiltro, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFiltro, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(25)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFiltro))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(PanelBotonera, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		PanelBotonera.setLayout(new GridLayout(0, 4, 0, 0));
		
		btnAgregar = new JButton("Agregar");
		PanelBotonera.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		PanelBotonera.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		PanelBotonera.add(btnEliminar);
		
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