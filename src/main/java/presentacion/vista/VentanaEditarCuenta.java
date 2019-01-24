package presentacion.vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class VentanaEditarCuenta
{

	private JDialog frame;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtEmail;
	private JPasswordField pwdContraseña;
	private JPasswordField pwdRepitaCont;
	private JButton btnAceptar;
	
	public VentanaEditarCuenta() 
	{
		frame = new JDialog();
		frame.setTitle("Cuenta");
		frame.setSize(281, 317);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(new Color(0,64,64));
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
		
		txtEmail = new JTextField();
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setBounds(119, 111, 125, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(23, 30, 91, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(23, 72, 91, 14);
		frame.getContentPane().add(lblApellido);
		
		JLabel lblContraseña = new JLabel("Contrase\u00F1a");
		lblContraseña.setBounds(23, 156, 91, 14);
		frame.getContentPane().add(lblContraseña);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(23, 114, 91, 14);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblRepita = new JLabel("Repita Cont.");
		lblRepita.setBounds(23, 198, 91, 14);
		frame.getContentPane().add(lblRepita);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(23, 244, 221, 23);
		frame.getContentPane().add(btnAceptar);
		
		pwdContraseña = new JPasswordField();
		pwdContraseña.setHorizontalAlignment(SwingConstants.CENTER);
		pwdContraseña.setBounds(119, 153, 125, 20);
		frame.getContentPane().add(pwdContraseña);
		
		pwdRepitaCont = new JPasswordField();
		pwdRepitaCont.setHorizontalAlignment(SwingConstants.CENTER);
		pwdRepitaCont.setBounds(119, 195, 125, 20);
		frame.getContentPane().add(pwdRepitaCont);
	}

	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtApellido() 
	{
		return txtApellido;
	}
	
	public JTextField getTxtContraseña() 
	{
		return pwdContraseña;
	}

	public JTextField getTxtRepitaCont() 
	{
		return pwdRepitaCont;
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