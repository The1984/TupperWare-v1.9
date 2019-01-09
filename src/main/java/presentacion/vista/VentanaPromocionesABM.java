package presentacion.vista;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;

public class VentanaPromocionesABM
{

	private JDialog frame;
	private DefaultTableModel modelPromocion;
	private String[] nombreColumnasPromocion = { "Nombre","Descripcion","Productos"};
	private JTable tablaPromocion;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	
	public VentanaPromocionesABM() 
	{
		frame = new JDialog();
		frame.setTitle("Gestion Promociones");
		frame.setBounds(100, 100, 600, 215);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane_premio = new JScrollPane();
		scrollPane_premio.setBounds(10, 11, 564, 118);
		frame.getContentPane().add(scrollPane_premio);
		
		modelPromocion = new DefaultTableModel(null, nombreColumnasPromocion) 
		{
			private static final long serialVersionUID = 1L;	
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
	
		tablaPromocion = new JTable(modelPromocion);
		scrollPane_premio.setViewportView(tablaPromocion);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 145, 564, 20);
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

	public JTable getTablaPromocion()
	{
		return tablaPromocion;
	}

	public void setTablaPromocion(JTable tablaPromocion) 
	{
		this.tablaPromocion = tablaPromocion;
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