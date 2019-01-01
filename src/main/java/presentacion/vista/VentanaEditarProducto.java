package presentacion.vista;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import dto.TipoDeProductoDTO;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaEditarProducto
{

	private JDialog frame;
	private JTextField txtNombre;
	private JTextField txtCodigo;	
	private JButton btnAceptar;
	private JComboBox<TipoDeProductoDTO> comboBoxTipoDeProducto;
	private JLabel lblImagen;
	private JTextArea txtrDescripcion;
	
	public VentanaEditarProducto() 
	{
		frame = new JDialog();
		frame.setTitle("Editar Producto");
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 281, 541);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setBounds(119, 69, 125, 20);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCodigo = new JTextField();
		txtCodigo.setHorizontalAlignment(SwingConstants.CENTER);
		txtCodigo.setBounds(119, 27, 125, 20);
		frame.getContentPane().add(txtCodigo);
		txtCodigo.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(23, 72, 91, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(23, 30, 91, 14);
		frame.getContentPane().add(lblCodigo);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(23, 468, 221, 23);
		frame.getContentPane().add(btnAceptar);
		
		comboBoxTipoDeProducto = new JComboBox<TipoDeProductoDTO>();
		comboBoxTipoDeProducto.setBounds(119, 205, 125, 20);
		frame.getContentPane().add(comboBoxTipoDeProducto);
		
		JLabel lblTipodeproducto = new JLabel("Tipo de Producto");
		lblTipodeproducto.setBounds(23, 208, 86, 14);
		frame.getContentPane().add(lblTipodeproducto);
		
		lblImagen = new JLabel("");
		Border border = BorderFactory.createLineBorder(Color.BLUE, 3);
		lblImagen.setBorder(border);
		lblImagen.setBounds(23, 236, 221, 221);
		frame.getContentPane().add(lblImagen);
		
		ImageIcon fot = new ImageIcon(getClass().getResource("/Imagenes/ImagenProductoDefault.png"));
	    Icon icono = new ImageIcon(fot.getImage().getScaledInstance(this.getLblImagen().getWidth(), this.getLblImagen().getHeight(), Image.SCALE_DEFAULT));
	    this.getLblImagen().setIcon(icono);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(23, 115, 221, 79);
	    frame.getContentPane().add(scrollPane);
	    
	    txtrDescripcion = new JTextArea();
	    scrollPane.setViewportView(txtrDescripcion);
	    this.getLblImagen().repaint();

	}

	public JTextField getTxtCodigo() 
	{
		return txtCodigo;
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextArea getTxtrDescripcion() 
	{
		return txtrDescripcion;
	}
	
	public JComboBox<TipoDeProductoDTO> getComboBoxTipoDeProducto() {
		return comboBoxTipoDeProducto;
	}

	public JLabel getLblImagen() 
	{
		return lblImagen;
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