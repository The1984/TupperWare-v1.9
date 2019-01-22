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

public class PanelGestionCompras extends JPanel 
{

	private static final long serialVersionUID = -2645130709335183446L;

	private JTable tablaCompra;
	private DefaultTableModel modelCompra;
	private String[] nombreColumnasCompra = { "Cliente","Producto","Unidades","Precio Unitario","Debe","Estado" };
	private JTextField textFiltro;
	private JPanel panel;
	private JButton btnEditar;
	private JButton btnGenerarReporte;
	private JLabel lblCampaña;

	public PanelGestionCompras() 
	{
		this.setBackground(new Color(5,35,27));//181,30,20
		JScrollPane scrollPane_compra = new JScrollPane();
		scrollPane_compra.setBorder(new TitledBorder(null, "Compras", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
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
		
		lblCampaña = new JLabel();
		lblCampaña.setForeground(Color.ORANGE);
		lblCampaña.setHorizontalAlignment(SwingConstants.CENTER);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_compra, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblLupa)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textFiltro, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(lblCampaña, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textFiltro, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCampaña, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblLupa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_compra, GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		Border borderButton = BorderFactory.createLineBorder(Color.black, 3);
		
		btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.setBorder(borderButton);
		panel.add(btnGenerarReporte);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBorder(borderButton);
		panel.add(btnEditar);
		
		modelCompra = new DefaultTableModel(null, nombreColumnasCompra) 
			{
				private static final long serialVersionUID = 1L;	
				public boolean isCellEditable(int row, int column) 
				{
					return false;
				}
			};
		
		tablaCompra = new JTable(modelCompra);
		scrollPane_compra.setViewportView(tablaCompra);
		setLayout(groupLayout);	
	}
	
	public JLabel getLblCampaña() 
	{
		return lblCampaña;
	}
	
	public JTable getTablaCompra() 
	{
		return tablaCompra;
	}

	public DefaultTableModel getModelCompra() 
	{
		return modelCompra;
	}

	public String[] getNombreColumnasCompra() 
	{
		return nombreColumnasCompra;
	}

	public JTextField getTextFiltro() 
	{
		return textFiltro;
	}

	public JButton getBtnEditar() 
	{
		return btnEditar;
	}

	public JButton getBtnGenerarReporte() 
	{
		return btnGenerarReporte;
	}
	
}