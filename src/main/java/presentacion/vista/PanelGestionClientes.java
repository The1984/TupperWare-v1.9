package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

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
		this.setBackground(new Color(5,35,27));
		JScrollPane scrollPane_cliente = new JScrollPane();
		scrollPane_cliente.setBorder(new TitledBorder(null, "Clientes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		textFiltro = new JTextField();
		Border border = BorderFactory.createLineBorder(new Color(0,135,191), 1);
		textFiltro.setBorder(border);
		textFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		textFiltro.setColumns(10);
		
		panel = new JPanel();
		
		JLabel lblLupa = new JLabel();
		lblLupa.setSize(25,25);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Imagenes/Lupa.png"));
		Icon img=new ImageIcon(icon.getImage().getScaledInstance(lblLupa.getWidth(), lblLupa.getHeight(), Image.SCALE_SMOOTH));
		lblLupa.setIcon(img);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_cliente, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblLupa)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFiltro, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFiltro)
						.addComponent(lblLupa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_cliente, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		Border borderButton = BorderFactory.createLineBorder(Color.black, 3);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBorder(borderButton);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBorder(borderButton);
		panel.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBorder(borderButton);
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

	public DefaultTableModel getModelCliente() 
	{
		return modelCliente;
	}

	public String[] getNombreColumnasCliente() 
	{
		return nombreColumnasCliente;
	}
	
	public JTextField getTextFiltro() 
	{
		return textFiltro;
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