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

public class VentanaSeleccionarCliente
{

	private JDialog frame;
	private JTextField txtFiltro;
	
	private DefaultTableModel modelCliente;
	private String[] nombreColumnasCliente = { "Nombre","Apellido","Celular","E-mail"};
	private JTable tablaCliente;
	private JLabel lblLupa;
		
	public VentanaSeleccionarCliente() 
	{
		frame = new JDialog();
		frame.setTitle("Seleccionar Cliente");
		frame.setBounds(100, 100, 353, 290);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);

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
		
		JScrollPane scrollPane_cliente = new JScrollPane();
		scrollPane_cliente.setBounds(10, 42, 317, 198);
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