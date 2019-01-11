package presentacion.vista;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;

public class VentanaImportBD 
{

	private JDialog frame;
	private JTextField textField;
	private JButton btnSelectFile;
	private JButton btnImport;

	public VentanaImportBD()  
	{
		frame = new JDialog();	
		frame.setTitle("Importar BD");
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
		
		btnSelectFile = new JButton("Seleccionar archivo");
		btnSelectFile.setBounds(250, 30, 160, 23);
		frame.getContentPane().add(btnSelectFile);
		
		btnImport = new JButton("Importar");
		btnImport.setBounds(10, 79, 400, 31);
		btnImport.setBackground(Color.darkGray);
		btnImport.setForeground(Color.white);
		frame.getContentPane().add(btnImport);
	}

	public JTextField getTextField() 
	{
		return textField;
	}

	public void setTextField(JTextField textField) 
	{
		this.textField = textField;
	}

	public JButton getBtnSelectFile() 
	{
		return btnSelectFile;
	}

	public void setBtnSelectFile(JButton btnSelectFile) 
	{
		this.btnSelectFile = btnSelectFile;
	}

	public JButton getBtnImport() 
	{
		return btnImport;
	}

	public void setBtnImport(JButton btnImport) 
	{
		this.btnImport = btnImport;
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