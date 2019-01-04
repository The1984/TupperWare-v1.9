package presentacion.vista;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.border.Border;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaVerProducto
{

	private JDialog frame;
	private JLabel lblNombre;
	private JButton btnComprar;
	private JLabel lblImagen;
	private JScrollPane scrollPane;
	private JTextArea txtrDescripcion;
	
	public VentanaVerProducto() 
	{
		frame = new JDialog();
		frame.setTitle("Ver Producto");
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 281, 454);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		lblNombre = new JLabel();
		lblNombre.setBounds(23, 21, 221, 14);
		frame.getContentPane().add(lblNombre);
		
		btnComprar = new JButton("Comprar");
		btnComprar.setBounds(23, 381, 221, 23);
		frame.getContentPane().add(btnComprar);
		
		lblImagen = new JLabel("");
		Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
		lblImagen.setBorder(border);
		lblImagen.setBounds(23, 138, 221, 221);
		frame.getContentPane().add(lblImagen);
		
	    scrollPane = new JScrollPane();
	    scrollPane.setBounds(23, 52, 221, 63);
	    frame.getContentPane().add(scrollPane);
	    
	    txtrDescripcion = new JTextArea();
	    scrollPane.setViewportView(txtrDescripcion);
	    txtrDescripcion.setEditable(false);
	    this.getLblImagen().repaint();
	}
	
	public JLabel getLblNombre() 
	{
		return lblNombre;
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