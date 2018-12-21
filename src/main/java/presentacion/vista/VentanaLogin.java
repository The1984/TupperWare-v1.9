package presentacion.vista;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JLabel;

public class VentanaLogin 
{

	private JFrame frame;
	private JLabel WelcomeLabel;
	private JPasswordField password;
	
	public VentanaLogin() 
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setTitle("TupperWare");
		frame.setSize(276, 300);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource("/Imagenes/iconVentana.png")));
		
		WelcomeLabel = new JLabel("");
		WelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		WelcomeLabel.setBounds(10, 163, 250, 14);
		frame.getContentPane().add(WelcomeLabel);

		password = new JPasswordField();
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(10, 204, 250, 20);
		frame.getContentPane().add(password);
	}

	public JLabel getWelcomeLabel() 
	{
		return WelcomeLabel;
	}

	public JTextField getPassword() 
	{
		return password;
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