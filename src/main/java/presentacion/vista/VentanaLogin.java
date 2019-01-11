package presentacion.vista;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class VentanaLogin 
{

	private JFrame frame;
	private JLabel WelcomeLabel;
	private JPasswordField password;
	
	public VentanaLogin() 
	{
		frame = new JFrame();
		frame.setTitle("TupperWare");
		frame.setSize(260, 280);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource("/Imagenes/iconVentana.png")));
		
		WelcomeLabel = new JLabel();
		WelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		WelcomeLabel.setBounds(10, 163, 234, 14);
		frame.getContentPane().add(WelcomeLabel);

		password = new JPasswordField();
		password.setForeground(Color.BLACK);
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(37, 205, 180, 20);
		frame.getContentPane().add(password);
		
		JLabel lblTupperware = new JLabel("TupperWare");
		lblTupperware.setForeground(Color.ORANGE);
		lblTupperware.setFont(new Font("Source Sans Pro Black", Font.PLAIN, 28));
		lblTupperware.setHorizontalAlignment(SwingConstants.CENTER);
		lblTupperware.setBounds(16, 46, 220, 32);
		frame.getContentPane().add(lblTupperware);
		
		JLabel lblSubrayado = new JLabel();
		lblSubrayado.setBounds(36,81,180,7);
		ImageIcon icon = new ImageIcon(this.getClass().getResource("/Imagenes/Subrayado.jpg"));
		Icon img=new ImageIcon(icon.getImage().getScaledInstance(lblSubrayado.getWidth(), lblSubrayado.getHeight(), Image.SCALE_SMOOTH));
		lblSubrayado.setIcon(img);
		frame.getContentPane().add(lblSubrayado);
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