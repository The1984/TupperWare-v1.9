package presentacion.vista;

import java.awt.Color;

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

public class VentanaAgregarPromocion
{

	private JDialog frame;
	private JTextField txtNombre;	
	private JTextArea txtrDescripcion;
	private JTable tablaProducto;
	private DefaultTableModel modelProducto;
	private String[] nombreColumnasProducto = { "Codigo","Nombre", "Tipo"};
	private JTextField txtPagina;
	private JTextField txtPrecio;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnAceptar;
	
	public VentanaAgregarPromocion() 
	{
		frame = new JDialog();
		frame.setTitle("Agregar Promocion");
		frame.setSize(281, 431);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(0,64,64));
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setBounds(119, 27, 125, 20);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(23, 30, 91, 14);
		frame.getContentPane().add(lblNombre);
		
		JScrollPane scrollPaneDescripcion = new JScrollPane();
	    scrollPaneDescripcion.setBounds(23, 69, 221, 65);
	    frame.getContentPane().add(scrollPaneDescripcion);
	    
	    JLabel lblDescripcion = new JLabel("Descripcion");
	    lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
	    scrollPaneDescripcion.setColumnHeaderView(lblDescripcion);
	    
	    txtrDescripcion = new JTextArea();
	    scrollPaneDescripcion.setViewportView(txtrDescripcion);
	    
	    JScrollPane scrollPaneProductos = new JScrollPane();
	    scrollPaneProductos.setBounds(23, 145, 221, 107);
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
	    
	    btnAgregar = new JButton("Agregar");
	    btnAgregar.setBounds(23, 250, 110, 23);
	    frame.getContentPane().add(btnAgregar);
	    
	    btnEliminar = new JButton("Eliminar");
	    btnEliminar.setBounds(134, 250, 110, 23);
	    frame.getContentPane().add(btnEliminar);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(23, 358, 221, 23);
		frame.getContentPane().add(btnAceptar);
		
		JLabel labelPagina = new JLabel("Pagina");
		labelPagina.setBounds(23, 290, 84, 14);
		frame.getContentPane().add(labelPagina);
		
		txtPagina = new JTextField();
		txtPagina.setHorizontalAlignment(SwingConstants.CENTER);
		txtPagina.setColumns(10);
		txtPagina.setBounds(108, 287, 136, 20);
		frame.getContentPane().add(txtPagina);
		
		JLabel labelPrecio = new JLabel("Precio");
		labelPrecio.setBounds(23, 321, 84, 14);
		frame.getContentPane().add(labelPrecio);
		
		txtPrecio = new JTextField();
		txtPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(108, 318, 136, 20);
		frame.getContentPane().add(txtPrecio);
	}

	public JTextField getTxtNombre() 
	{
		return txtNombre;
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
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}

	public JButton getBtnEliminar() 
	{
		return btnEliminar;
	}
	
	public JButton getBtnAceptar() 
	{
		return btnAceptar;
	}

	public JTextField getTxtPagina() 
	{
		return txtPagina;
	}

	public JTextField getTxtPrecio() 
	{
		return txtPrecio;
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