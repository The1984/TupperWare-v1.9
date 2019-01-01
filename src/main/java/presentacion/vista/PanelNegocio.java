package presentacion.vista;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JTextField;

public class PanelNegocio extends JPanel 
{

	private static final long serialVersionUID = -2307018905071256486L;
	
	private JTable tablaProducto;
	private DefaultTableModel modelProducto;
	private String[] nombreColumnasProducto = { "Codigo","Nombre","Descripcion","Tipo"};
	private JTable tablaPromocion;
	private DefaultTableModel modelPromocion;
	private String[] nombreColumnasPromocion = { "Nombre","Descripcion","Productos"};
	private JTextField textFiltro;
	
	private JButton btnAgregarProducto;
	private JButton btnEditarProducto;
	private JButton btnEliminarProducto;
	
	public PanelNegocio() 
	{
		setBackground(Color.ORANGE);
		
		JScrollPane scrollPane_Producto = new JScrollPane();
		scrollPane_Producto.setBorder(new TitledBorder(null, "Productos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
	
		JScrollPane scrollPane_Promocion = new JScrollPane();
		scrollPane_Promocion.setBorder(new TitledBorder(null, "Promociones", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JPanel panelProducto = new JPanel();
		
		textFiltro = new JTextField();
		textFiltro.setColumns(10);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_Producto, GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
						.addComponent(panelProducto, GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
						.addComponent(scrollPane_Promocion, GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(51)
							.addComponent(textFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(textFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_Producto, GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelProducto, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_Promocion, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelProducto.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnAgregarProducto = new JButton("Agregar");
		panelProducto.add(btnAgregarProducto);
		
		btnEditarProducto = new JButton("Editar");
		panelProducto.add(btnEditarProducto);
		
		btnEliminarProducto = new JButton("Eliminar");
		panelProducto.add(btnEliminarProducto);
		
		modelProducto = new DefaultTableModel(null, nombreColumnasProducto) 
		{
			private static final long serialVersionUID = 1L;	
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
		tablaProducto = new JTable(modelProducto);
		scrollPane_Producto.setViewportView(tablaProducto);

		modelPromocion = new DefaultTableModel(null, nombreColumnasPromocion) 
		{
			private static final long serialVersionUID = 1L;	
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
		tablaPromocion = new JTable(modelPromocion);
		scrollPane_Promocion.setViewportView(tablaPromocion);
		
		setLayout(groupLayout);
	}
	
	public JTextField getTextFiltro() 
	{
		return textFiltro;
	}

	public JTable getTableProducto() 
	{
		return tablaProducto;
	}

	public void setTableProducto(JTable tableProducto) 
	{
		this.tablaProducto = tableProducto;
	}

	public DefaultTableModel getModelProducto() 
	{
		return modelProducto;
	}

	public void setModelProducto(DefaultTableModel modelProducto) 
	{
		this.modelProducto = modelProducto;
	}

	public String[] getNombreColumnasProducto() 
	{
		return nombreColumnasProducto;
	}

	public void setNombreColumnasProducto(String[] nombreColumnasProducto) 
	{
		this.nombreColumnasProducto = nombreColumnasProducto;
	}

	public JTable getTablaPromocion() 
	{
		return tablaPromocion;
	}

	public void setTablaPromocion(JTable tablaPromocion) 
	{
		this.tablaPromocion = tablaPromocion;
	}

	public DefaultTableModel getModelPromocion() 
	{
		return modelPromocion;
	}

	public void setModelPromocion(DefaultTableModel modelPromocion) 
	{
		this.modelPromocion = modelPromocion;
	}

	public String[] getNombreColumnasPromocion() 
	{
		return nombreColumnasPromocion;
	}

	public void setNombreColumnasPromocion(String[] nombreColumnasPromocion) 
	{
		this.nombreColumnasPromocion = nombreColumnasPromocion;
	}
	
	public JButton getBtnAgregarProducto() 
	{
		return btnAgregarProducto;
	}
	
	public JButton getBtnEditarProducto() 
	{
		return btnEditarProducto;
	}
	
	public JButton getBtnEliminarProducto() 
	{
		return btnEliminarProducto;
	}

}