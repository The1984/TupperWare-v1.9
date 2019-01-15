package presentacion.vista;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;

public class VentanaTipoDeProductoABM
{

	private JDialog frame;
	private DefaultTableModel modelTipoDeProducto;
	private String[] nombreTipoDeProducto = { "Nombre","Ganancia (%)"};
	private JTable tablaTipoDeProducto;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	
	public VentanaTipoDeProductoABM() 
	{
		frame = new JDialog();
		frame.setTitle("Gestion - Tipo de producto");
		frame.setBounds(100, 100, 420, 215);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_tipoDeProducto = new JScrollPane();
		scrollPane_tipoDeProducto.setBounds(10, 11, 384, 118);
		frame.getContentPane().add(scrollPane_tipoDeProducto);
		
		modelTipoDeProducto = new DefaultTableModel(null, nombreTipoDeProducto) 
		{
			private static final long serialVersionUID = 1L;	
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
	
		tablaTipoDeProducto = new JTable(modelTipoDeProducto);
		scrollPane_tipoDeProducto.setViewportView(tablaTipoDeProducto);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 145, 384, 20);
		frame.getContentPane().add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnAgregar = new JButton("Agregar");
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		panel.add(btnEditar);
		
		btnEliminar = new JButton("Eliminar");
		panel.add(btnEliminar);
		
		frame.setLocationRelativeTo(null);
	}

	public DefaultTableModel getModelTipoDeProducto() 
	{
		return modelTipoDeProducto;
	}

	public String[] getNombreColumnasTipoDeProducto() 
	{
		return nombreTipoDeProducto;
	}

	public JTable getTablaTipoDeProducto() 
	{
		return tablaTipoDeProducto;
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
	
	public void show() 
	{
		frame.setVisible(true);
	}
	
	public void close()
	{
		frame.dispose();
	}
	
}