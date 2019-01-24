package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

public class PanelNegocio extends JPanel 
{

	private static final long serialVersionUID = -2307018905071256486L;
	
	private JTable tablaProducto;
	private DefaultTableModel modelProducto;
	private String[] nombreColumnasProducto = { "Codigo","Nombre","Descripcion","Tipo"};
	private JTable tablaPromocion;
	private DefaultTableModel modelPromocion;
	private String[] nombreColumnasPromocion = { "Nombre","Descripcion","Productos","Pagina","Precio"};
	private JTextField textFiltro;
	private JButton btnAgregarProducto;
	private JButton btnEditarProducto;
	private JButton btnEliminarProducto;
	
	public PanelNegocio() 
	{
		this.setBackground(new Color(5,35,27));
		
		JScrollPane scrollPane_Producto = new JScrollPane();
		scrollPane_Producto.setBorder(new TitledBorder(null, "Productos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
	
		JScrollPane scrollPane_Promocion = new JScrollPane();
		scrollPane_Promocion.setBorder(new TitledBorder(null, "Promociones", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		JPanel panelProducto = new JPanel();
		
		textFiltro = new JTextField();
		Border border = BorderFactory.createLineBorder(new Color(0,135,191), 1);
		textFiltro.setBorder(border);
		textFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		textFiltro.setColumns(10);
		
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
						.addComponent(scrollPane_Producto, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(panelProducto, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(scrollPane_Promocion, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblLupa)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFiltro, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_Promocion, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textFiltro)
						.addComponent(lblLupa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(11)
					.addComponent(scrollPane_Producto, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelProducto, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelProducto.setLayout(new GridLayout(1, 0, 0, 0));
		
		Border borderButton = BorderFactory.createLineBorder(Color.black, 3);
		
		btnAgregarProducto = new JButton("Agregar");
		btnAgregarProducto.setBorder(borderButton);
		panelProducto.add(btnAgregarProducto);
		
		btnEditarProducto = new JButton("Editar");
		btnEditarProducto.setBorder(borderButton);
		panelProducto.add(btnEditarProducto);
		
		btnEliminarProducto = new JButton("Eliminar");
		btnEliminarProducto.setBorder(borderButton);
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

	public JTable getTablaProducto() 
	{
		return tablaProducto;
	}

	public DefaultTableModel getModelProducto() 
	{
		return modelProducto;
	}

	public String[] getNombreColumnasProducto() 
	{
		return nombreColumnasProducto;
	}

	public JTable getTablaPromocion() 
	{
		return tablaPromocion;
	}

	public DefaultTableModel getModelPromocion() 
	{
		return modelPromocion;
	}

	public String[] getNombreColumnasPromocion() 
	{
		return nombreColumnasPromocion;
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