package presentacion.vista;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;

public class VentanaExportBD 
{

	private JDialog frame;
	private JTextField textField;
	private JButton btnSelectFolder;
	private JButton btnExport;

	public VentanaExportBD()  
	{
		frame = new JDialog();
		frame.setTitle("Exportar BD");
		frame.setResizable(false);
		frame.setSize(425, 160);
		frame.getContentPane().setBackground(Color.lightGray);
		frame.setModal(true);
	    frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 30, 230, 22);
		textField.setEditable(false);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		btnSelectFolder = new JButton("Seleccionar destino");
		btnSelectFolder.setBounds(250, 30, 160, 23);
		frame.getContentPane().add(btnSelectFolder);
		
		btnExport = new JButton("Exportar");
		btnExport.setBounds(10, 79, 400, 31);
		btnExport.setBackground(Color.darkGray);
		btnExport.setForeground(Color.white);
		
		frame.getContentPane().add(btnExport);
	}
	
	public JTextField getTextField() 
	{
		return textField;
	}

	public void setTextField(JTextField textField) 
	{
		this.textField = textField;
	}

	public JButton getBtnSelectFolder() 
	{
		return btnSelectFolder;
	}

	public void setBtnSelectFolder(JButton btnSelectFolder) 
	{
		this.btnSelectFolder = btnSelectFolder;
	}

	public JButton getBtnExport() 
	{
		return btnExport;
	}

	public void setBtnExport(JButton btnExport) 
	{
		this.btnExport = btnExport;
	}
	
	public void show()
	{
		this.frame.setVisible(true);
	}
	
	public void close() 
	{
		this.frame.dispose();
	}
	
}