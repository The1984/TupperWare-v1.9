package presentacion.vista;

import java.awt.Color;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JTable;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;

public class VentanaVerCompras
{

	private JDialog frame;
	private JTextField txtFiltro;
	private DefaultTableModel modelCompras;
	private String[] nombreColumnasCompras = {"Campaña", "Producto", "Estado"};
	private JTable tablaCompras;
	private JLabel lblLupa;
		
	public VentanaVerCompras() 
	{
		frame = new JDialog();
		frame.setTitle("Compras realizadas");
		frame.setBounds(100, 100, 353, 290);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(0,64,64));

		lblLupa = new JLabel("");
		lblLupa.setBounds(10, 11, 20, 20);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Imagenes/Lupa.png"));
		Icon img=new ImageIcon(icon.getImage().getScaledInstance(lblLupa.getWidth(), lblLupa.getHeight(), Image.SCALE_SMOOTH));
		lblLupa.setIcon(img);
		frame.getContentPane().add(lblLupa);
		
		txtFiltro = new JTextField();
		txtFiltro.setBounds(39, 11, 288, 20);
		Border border = BorderFactory.createLineBorder(new Color(0,135,191), 1);
		txtFiltro.setBorder(border);
		txtFiltro.setHorizontalAlignment(SwingConstants.CENTER);
		frame.getContentPane().add(txtFiltro);
		txtFiltro.setColumns(10);
		
		JScrollPane scrollPane_compras = new JScrollPane();
		scrollPane_compras.setBounds(10, 42, 317, 198);
		frame.getContentPane().add(scrollPane_compras);
		
		modelCompras = new DefaultTableModel(null, nombreColumnasCompras) 
		{
			private static final long serialVersionUID = 1L;	
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
	
		tablaCompras = new JTable(modelCompras);
		scrollPane_compras.setViewportView(tablaCompras);
		
		frame.setLocationRelativeTo(null);
	}

	public DefaultTableModel getModelCompras() 
	{
		return modelCompras;
	}

	public String[] getNombreColumnasCompras() 
	{
		return nombreColumnasCompras;
	}

	public JTable getTablaCompras() 
	{
		return tablaCompras;
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