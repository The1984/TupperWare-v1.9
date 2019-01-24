package presentacion.vista;

import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;

public class VentanaPremiosABM
{

	private JDialog frame;
	private DefaultTableModel modelPremio;
	private String[] nombreColumnasPremio = { "Nombre","Descripcion","Unidades","Ganado","Recibido"};
	private JTable tablaPremio;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnEliminar;
	
	public VentanaPremiosABM() 
	{
		frame = new JDialog();
		frame.setTitle("Gestion Premios");
		frame.setBounds(100, 100, 600, 215);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(0,64,64));
		
		JScrollPane scrollPane_premio = new JScrollPane();
		scrollPane_premio.setBounds(10, 11, 564, 118);
		frame.getContentPane().add(scrollPane_premio);
		
		modelPremio = new DefaultTableModel(null, nombreColumnasPremio) 
		{
			private static final long serialVersionUID = 1L;	
			public boolean isCellEditable(int row, int column) 
			{
				return false;
			}
		};
	
		tablaPremio = new JTable(modelPremio);
		scrollPane_premio.setViewportView(tablaPremio);
		
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

	public DefaultTableModel getModelPremio() 
	{
		return modelPremio;
	}

	public String[] getNombreColumnasPremio() 
	{
		return nombreColumnasPremio;
	}

	public JTable getTablaPremio() 
	{
		return tablaPremio;
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