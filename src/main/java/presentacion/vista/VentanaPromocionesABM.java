package presentacion.vista;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;

public class VentanaPromocionesABM
{

	private JDialog frame;
	private DefaultTableModel modelPromocion;
	private String[] nombreColumnasPromocion = { "Nombre","Descripcion","Productos","Pagina","Precio"};
	private JTable tablaPromocion;
	private JButton btnAgregar;
	private JButton btnEditar;

	public VentanaPromocionesABM() 
	{
		frame = new JDialog();
		frame.setTitle("Gestion Promociones");
		frame.setBounds(100, 100, 600, 215);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(0,64,64));
		
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
		
		frame.setLocationRelativeTo(null);
	}

	public DefaultTableModel getModelPromocion() 
	{
		return modelPromocion;
	}

	public String[] getNombreColumnasPromocion()
	{
		return nombreColumnasPromocion;
	}

	public JTable getTablaPromocion()
	{
		return tablaPromocion;
	}

	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}

	public JButton getBtnEditar() 
	{
		return btnEditar;
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