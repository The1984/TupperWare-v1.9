package presentacion.vista;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.Border;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class VentanaVerProducto
{

	private JDialog frame;
	private JButton btnComprar;
	private JLabel lblImagen;
	private JScrollPane scrollPane;
	private JTextArea txtrDescripcion;
	
	public VentanaVerProducto() 
	{
		frame = new JDialog();
		frame.setBounds(100, 100, 281, 421);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		btnComprar = new JButton("Comprar");
		btnComprar.setBounds(23, 348, 221, 23);
		frame.getContentPane().add(btnComprar);
		
		lblImagen = new JLabel("");
		Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
		lblImagen.setBorder(border);
		lblImagen.setBounds(23, 109, 221, 221);
		frame.getContentPane().add(lblImagen);
		
	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(23, 23, 221, 63);
	    frame.getContentPane().add(scrollPane);
	    
	    txtrDescripcion = new JTextArea();
	    txtrDescripcion.setBackground(Color.DARK_GRAY);
	    txtrDescripcion.setEditable(false);
	    scrollPane.setViewportView(txtrDescripcion);
	    
	    JLabel lblDescripcion = new JLabel("Descripcion");
	    lblDescripcion.setHorizontalAlignment(SwingConstants.CENTER);
	    scrollPane.setColumnHeaderView(lblDescripcion);
	    this.getLblImagen().repaint();
	}
	
	public JDialog getFrame() 
	{
		return frame;
	}

	public JTextArea getTxtrDescripcion() 
	{
		return txtrDescripcion;
	}

	public JLabel getLblImagen() 
	{
		return lblImagen;
	}
	
	public JButton getBtnComprar() 
	{
		return btnComprar;
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