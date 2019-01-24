package presentacion.vista;

import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaVerPremio
{

	private JDialog frame;
	private JTextArea textArea;
	private JTextField textFieldUnidades;

	public VentanaVerPremio() 
	{
		frame = new JDialog();
		frame.setSize(281, 221);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(0,64,64));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 11, 221, 132);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JLabel label = new JLabel("Descripcion");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(label);

		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setBounds(23, 157, 91, 14);
		frame.getContentPane().add(lblUnidades);
		
		textFieldUnidades = new JTextField();
		textFieldUnidades.setEditable(false);
		textFieldUnidades.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldUnidades.setColumns(10);
		textFieldUnidades.setBounds(119, 154, 125, 20);
		frame.getContentPane().add(textFieldUnidades);
	}

	public JDialog getFrame() 
	{
		return frame;
	}
	
	public JTextArea getTextArea() 
	{
		return textArea;
	}
	
	public JTextField getTextFieldUnidades() 
	{
		return textFieldUnidades;
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