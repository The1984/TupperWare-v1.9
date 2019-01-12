package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;

public class VentanaComprarPromocion
{

	private JDialog frame;
	private JTextArea txtrDescripcion;
	private JTable tablaProducto;
	private DefaultTableModel modelProducto;
	private String[] nombreColumnasProducto = { "Codigo","Nombre", "Tipo"};
	private JTextField txtPagina;
	private JTextField txtPrecio;
	private JTextField txtPago;
	private JTextField txtCliente;	
	private JSpinner spinnerUnidades;
	private JCheckBox checkbxPago;
	private JButton btnEliminar;
	private JButton btnComprar;
	private JButton BtnSelectCliente;
	
	public VentanaComprarPromocion() 
	{
		frame = new JDialog();
		frame.setSize(281, 532);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JScrollPane scrollPaneDescripcion = new JScrollPane();
	    scrollPaneDescripcion.setBounds(23, 11, 221, 65);
	    frame.getContentPane().add(scrollPaneDescripcion);
	    
	    JLabel lblDescripcion = new JLabel("Descripcion");
	    lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
	    scrollPaneDescripcion.setColumnHeaderView(lblDescripcion);
	    
	    txtrDescripcion = new JTextArea();
	    txtrDescripcion.setEditable(false);
	    scrollPaneDescripcion.setViewportView(txtrDescripcion);
	    
	    JScrollPane scrollPaneProductos = new JScrollPane();
	    scrollPaneProductos.setBounds(23, 87, 221, 107);
	    scrollPaneProductos.setBorder(new TitledBorder(null, "Productos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
	    frame.getContentPane().add(scrollPaneProductos);
	    
		modelProducto = new DefaultTableModel(null, nombreColumnasProducto) 
			{
				private static final long serialVersionUID = 1L;	
				public boolean isCellEditable(int row, int column) 
				{
					return false;
				}
			};
	    
	    tablaProducto = new JTable(modelProducto);
	    scrollPaneProductos.setViewportView(tablaProducto);
	    
	    btnEliminar = new JButton("Eliminar");
	    btnEliminar.setBounds(23, 192, 221, 23);
	    frame.getContentPane().add(btnEliminar);

		btnComprar = new JButton("Comprar");
		btnComprar.setBounds(23, 459, 221, 23);
		frame.getContentPane().add(btnComprar);
		
		JLabel label = new JLabel("Pagina");
		label.setBounds(23, 288, 84, 14);
		frame.getContentPane().add(label);
		
		txtPagina = new JTextField();
		txtPagina.setHorizontalAlignment(SwingConstants.CENTER);
		txtPagina.setColumns(10);
		txtPagina.setBounds(108, 285, 136, 20);
		frame.getContentPane().add(txtPagina);
		
		txtPrecio = new JTextField();
		txtPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(108, 330, 136, 20);
		frame.getContentPane().add(txtPrecio);
		
		JLabel labelPrecio = new JLabel("Precio");
		labelPrecio.setBounds(23, 333, 84, 14);
		frame.getContentPane().add(labelPrecio);
		
		JLabel labelPago = new JLabel("Pag\u00F3");
		labelPago.setBounds(22, 423, 85, 14);
		frame.getContentPane().add(labelPago);

		txtPago = new JTextField();
		txtPago.setText("0");
		txtPago.setHorizontalAlignment(SwingConstants.CENTER);
		txtPago.setColumns(10);
		txtPago.setBounds(134, 420, 110, 20);
		frame.getContentPane().add(txtPago);
		
		JLabel labelUnidades = new JLabel("Unidades");
		labelUnidades.setBounds(23, 378, 84, 14);
		frame.getContentPane().add(labelUnidades);
		
		spinnerUnidades = new JSpinner();
		spinnerUnidades.setModel(new SpinnerNumberModel(1, 1, 25, 1));
		spinnerUnidades.setBounds(108, 375, 136, 20);
		frame.getContentPane().add(spinnerUnidades);
		
		JLabel labelCliente = new JLabel("Cliente");
		labelCliente.setBounds(23, 243, 84, 14);
		frame.getContentPane().add(labelCliente);
		
		txtCliente = new JTextField();
		txtCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtCliente.setEditable(false);
		txtCliente.setColumns(10);
		txtCliente.setBounds(108, 240, 107, 20);
		frame.getContentPane().add(txtCliente);
		
		BtnSelectCliente = new JButton("...");
		BtnSelectCliente.setBounds(215, 240, 29, 20);
		frame.getContentPane().add(BtnSelectCliente);
		
		checkbxPago = new JCheckBox();
		checkbxPago.setBounds(108, 420, 20, 20);
		frame.getContentPane().add(checkbxPago);
	}

	public JTextArea getTxtrDescripcion() 
	{
		return txtrDescripcion;
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
	
	public JTextField getTxtPagina() 
	{
		return txtPagina;
	}

	public JTextField getTxtPrecio() 
	{
		return txtPrecio;
	}

	public JTextField getTxtPago() 
	{
		return txtPago;
	}

	public JCheckBox getCheckbxPago() 
	{
		return checkbxPago;
	}

	public JTextField getTxtCliente() 
	{
		return txtCliente;
	}

	public JSpinner getSpinnerUnidades() 
	{
		return spinnerUnidades;
	}
	
	public JButton getBtnEliminar() 
	{
		return btnEliminar;
	}

	public JButton getBtnSelectCliente() 
	{
		return BtnSelectCliente;
	}

	public JButton getBtnComprar() 
	{
		return btnComprar;
	}

	public JDialog getFrame() 
	{
		return frame;
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