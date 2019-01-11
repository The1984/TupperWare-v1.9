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

public class VentanaComprarPromocion
{

	private JDialog frame;
	private JTextArea txtrDescripcion;
	private JTable tablaProducto;
	private DefaultTableModel modelProducto;
	private String[] nombreColumnasProducto = { "Codigo","Nombre", "Tipo"};
	private JTextField textFieldPagina;
	private JTextField textFieldPrecio;
	private JTextField textFieldPago;
	private JTextField textFieldCliente;	
	private JSpinner spinnerUnidades;
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
		
		textFieldPagina = new JTextField();
		textFieldPagina.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPagina.setColumns(10);
		textFieldPagina.setBounds(108, 285, 136, 20);
		frame.getContentPane().add(textFieldPagina);
		
		textFieldPrecio = new JTextField();
		textFieldPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPrecio.setColumns(10);
		textFieldPrecio.setBounds(108, 330, 136, 20);
		frame.getContentPane().add(textFieldPrecio);
		
		JLabel labelPrecio = new JLabel("Precio");
		labelPrecio.setBounds(23, 333, 84, 14);
		frame.getContentPane().add(labelPrecio);
		
		JLabel labelPago = new JLabel("Pag\u00F3");
		labelPago.setBounds(22, 378, 85, 14);
		frame.getContentPane().add(labelPago);

		textFieldPago = new JTextField();
		textFieldPago.setText("0");
		textFieldPago.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldPago.setColumns(10);
		textFieldPago.setBounds(108, 375, 136, 20);
		frame.getContentPane().add(textFieldPago);
		
		JLabel labelUnidades = new JLabel("Unidades");
		labelUnidades.setBounds(23, 423, 84, 14);
		frame.getContentPane().add(labelUnidades);
		
		spinnerUnidades = new JSpinner();
		spinnerUnidades.setModel(new SpinnerNumberModel(1, 1, 25, 1));
		spinnerUnidades.setBounds(108, 420, 136, 20);
		frame.getContentPane().add(spinnerUnidades);
		
		JLabel labelCliente = new JLabel("Cliente");
		labelCliente.setBounds(23, 243, 84, 14);
		frame.getContentPane().add(labelCliente);
		
		textFieldCliente = new JTextField();
		textFieldCliente.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCliente.setEditable(false);
		textFieldCliente.setColumns(10);
		textFieldCliente.setBounds(108, 240, 107, 20);
		frame.getContentPane().add(textFieldCliente);
		
		BtnSelectCliente = new JButton("...");
		BtnSelectCliente.setBounds(215, 240, 29, 20);
		frame.getContentPane().add(BtnSelectCliente);
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
	
	public JTextField getTextFieldPagina() 
	{
		return textFieldPagina;
	}

	public JTextField getTextFieldPrecio() 
	{
		return textFieldPrecio;
	}

	public JTextField getTextFieldPago() 
	{
		return textFieldPago;
	}

	public JTextField getTextFieldCliente() 
	{
		return textFieldCliente;
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