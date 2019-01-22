package presentacion.vista;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Color;

public class VentanaGenerarReporte 
{

	private JDialog frame;
	private JTextField textField;
	private JButton btnSelectFolder;
	private JButton btnGenerarReporteVendedor;
	private JButton btnGenerarReporteLider;

	public VentanaGenerarReporte()  
	{
		frame = new JDialog();
		frame.setTitle("Generar Reporte");
		frame.setResizable(false);
		frame.setSize(425, 190);
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
		
		btnGenerarReporteVendedor = new JButton("Reporte - Vendedor");
		btnGenerarReporteVendedor.setBounds(10, 79, 400, 31);
		btnGenerarReporteVendedor.setBackground(Color.darkGray);
		btnGenerarReporteVendedor.setForeground(Color.white);
		frame.getContentPane().add(btnGenerarReporteVendedor);
		
		btnGenerarReporteLider = new JButton("Reporte - Lider");
		btnGenerarReporteLider.setBounds(10, 119, 400, 31);
		btnGenerarReporteLider.setBackground(Color.darkGray);
		btnGenerarReporteLider.setForeground(Color.white);
		frame.getContentPane().add(btnGenerarReporteLider);
	}
	
	public JTextField getTextField() 
	{
		return textField;
	}

	public JButton getBtnSelectFolder() 
	{
		return btnSelectFolder;
	}

	public JButton getBtnGenerarReporteVendedor() 
	{
		return btnGenerarReporteVendedor;
	}
	
	public JButton getBtnGenerarReporteLider() 
	{
		return btnGenerarReporteLider;
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