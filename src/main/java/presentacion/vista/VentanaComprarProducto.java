package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.Color;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class VentanaComprarProducto
{

	private JDialog frame;
	private JTextField txtPagina;
	private JTextField txtPrecio;
	private JTextField txtPago;
	private JCheckBox chckbxPago;
	private JLabel lblProducto;
	private JLabel lblCliente;
	private JTextField txtNameCliente;
	private JTextField txtNameProducto;
	private JSpinner spinnerUnidades;
	private JButton btnAceptar;
	private JButton btnSelectCliente;
	
	public VentanaComprarProducto() 
	{
		frame = new JDialog();
		frame.setTitle("Comprar Producto");
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 281, 366);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(23, 293, 221, 23);
		frame.getContentPane().add(btnAceptar);
		
		spinnerUnidades = new JSpinner();
		spinnerUnidades.setModel(new SpinnerNumberModel(1, 1, 25, 1));
		spinnerUnidades.setBounds(108, 246, 136, 20);
		frame.getContentPane().add(spinnerUnidades);
		
		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setBounds(23, 249, 84, 14);
		frame.getContentPane().add(lblUnidades);
		
		JLabel lblPagina = new JLabel("Pagina");
		lblPagina.setBounds(24, 116, 84, 14);
		frame.getContentPane().add(lblPagina);
		
		txtPagina = new JTextField();
		txtPagina.setHorizontalAlignment(SwingConstants.CENTER);
		txtPagina.setBounds(108, 113, 136, 20);
		frame.getContentPane().add(txtPagina);
		txtPagina.setColumns(10);
		
		txtPrecio = new JTextField();
		txtPrecio.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrecio.setBounds(108, 158, 136, 20);
		frame.getContentPane().add(txtPrecio);
		txtPrecio.setColumns(10);
		
		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setBounds(23, 161, 84, 14);
		frame.getContentPane().add(lblPrecio);
		
		JLabel lblPag = new JLabel("Pag\u00F3");
		lblPag.setBounds(23, 203, 85, 14);
		frame.getContentPane().add(lblPag);
		
		txtPago = new JTextField();
		txtPago.setText("0");
		txtPago.setHorizontalAlignment(SwingConstants.CENTER);
		txtPago.setBounds(134, 200, 110, 20);
		frame.getContentPane().add(txtPago);
		txtPago.setColumns(10);
		
		chckbxPago = new JCheckBox("");
		chckbxPago.setBounds(108, 200, 20, 20);
		frame.getContentPane().add(chckbxPago);
		
		lblProducto = new JLabel("Producto");
		lblProducto.setBounds(23, 25, 84, 14);
		frame.getContentPane().add(lblProducto);
		
		lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(23, 71, 84, 14);
		frame.getContentPane().add(lblCliente);
		
		txtNameCliente = new JTextField();
		txtNameCliente.setEditable(false);
		txtNameCliente.setHorizontalAlignment(SwingConstants.CENTER);
		txtNameCliente.setBounds(108, 68, 107, 20);
		frame.getContentPane().add(txtNameCliente);
		txtNameCliente.setColumns(10);
		
		txtNameProducto = new JTextField();
		txtNameProducto.setEditable(false);
		txtNameProducto.setHorizontalAlignment(SwingConstants.CENTER);
		txtNameProducto.setBounds(108, 22, 136, 20);
		frame.getContentPane().add(txtNameProducto);
		txtNameProducto.setColumns(10);
		
		btnSelectCliente = new JButton("...");
		btnSelectCliente.setBounds(215, 68, 29, 20);
		frame.getContentPane().add(btnSelectCliente);
	}

	public JTextField getTxtPagina() 
	{
		return txtPagina;
	}

	public JTextField getTxtPrecio() 
	{
		return txtPrecio;
	}

	public JTextField getTxtPago() 
	{
		return txtPago;
	}

	public JCheckBox getChckbxPago() 
	{
		return chckbxPago;
	}

	public JTextField getTxtNameProducto() 
	{
		return txtNameProducto;
	}

	public JButton getBtnSelectCliente() 
	{
		return btnSelectCliente;
	}
	
	public JTextField getTxtNameCliente() 
	{
		return txtNameCliente;
	}
	
	public JSpinner getSpinnerUnidades() 
	{
		return spinnerUnidades;
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