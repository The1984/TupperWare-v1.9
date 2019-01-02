package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VentanaEditarCliente
{

	private JDialog frame;
	private JTextField txtNombre;
	private JTextField txtApellido;	
	private JTextField txtCelular;
	private JTextField txtDireccion;
	private JTextField txtEmail;
	private JButton btnAceptar;
	
	public VentanaEditarCliente() 
	{
		frame = new JDialog();
		frame.setTitle("Editar Cliente");
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setBounds(100, 100, 281, 318);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setBounds(119, 27, 125, 20);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		txtApellido = new JTextField();
		txtApellido.setHorizontalAlignment(SwingConstants.CENTER);
		txtApellido.setBounds(119, 69, 125, 20);
		frame.getContentPane().add(txtApellido);
		txtApellido.setColumns(10);
		
		txtCelular = new JTextField();
		txtCelular.setHorizontalAlignment(SwingConstants.CENTER);
		txtCelular.setBounds(119, 159, 125, 20);
		frame.getContentPane().add(txtCelular);
		txtCelular.setColumns(10);
		
		txtDireccion = new JTextField();
		txtDireccion.setHorizontalAlignment(SwingConstants.CENTER);
		txtDireccion.setBounds(119, 113, 125, 20);
		frame.getContentPane().add(txtDireccion);
		txtDireccion.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setBounds(119, 203, 125, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(23, 30, 91, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(23, 72, 91, 14);
		frame.getContentPane().add(lblApellido);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(23, 116, 91, 14);
		frame.getContentPane().add(lblDireccion);
		
		JLabel lblCelular = new JLabel("Celular");
		lblCelular.setBounds(23, 162, 91, 14);
		frame.getContentPane().add(lblCelular);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(23, 206, 91, 14);
		frame.getContentPane().add(lblEmail);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(23, 245, 221, 23);
		frame.getContentPane().add(btnAceptar);
	}

	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtApellido() 
	{
		return txtApellido;
	}

	public JTextField getTxtCelular() 
	{
		return txtCelular;
	}

	public JTextField getTxtDireccion() 
	{
		return txtDireccion;
	}

	public JTextField getTxtEmail() 
	{
		return txtEmail;
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