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

public class VentanaEditarPromocion
{

	private JDialog frame;
	private JTextField txtNombre;	
	private JTextArea txtrDescripcion;
	private JTable tablaProducto;
	private DefaultTableModel modelProducto;
	private String[] nombreColumnasProducto = { "Codigo","Nombre", "Tipo"};
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnAceptar;
	
	public VentanaEditarPromocion() 
	{
		frame = new JDialog();
		frame.setTitle("Editar Promocion");
		frame.setSize(281, 360);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
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
		btnAceptar.setBounds(23, 287, 221, 23);
		frame.getContentPane().add(btnAceptar);
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
	
	public void show() 
	{
		frame.setVisible(true);
	}
	
	public void close()
	{
		frame.dispose();
	}
	
}