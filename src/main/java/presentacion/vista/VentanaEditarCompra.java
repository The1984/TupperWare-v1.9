package presentacion.vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import dto.EstadoDeCompraDTO;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class VentanaEditarCompra
{

	private JDialog frame;
	private JCheckBox chckbxPago;
	private JTextField txtMontoTotal;
	private JTextField txtPago;
	private JButton btnAceptar;
	private JComboBox<EstadoDeCompraDTO> comboBoxEstadoDeCompra;

	public VentanaEditarCompra() 
	{
		frame = new JDialog();
		frame.setTitle("Editar Compra");
		frame.setSize(281, 221);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(0,64,64));
		
		comboBoxEstadoDeCompra = new JComboBox<EstadoDeCompraDTO>();
		comboBoxEstadoDeCompra.setBounds(108, 101, 136, 20);
		frame.getContentPane().add(comboBoxEstadoDeCompra);
		
		JLabel lblEstadoDeCompra = new JLabel("Estado");
		lblEstadoDeCompra.setBounds(23, 104, 84, 14);
		frame.getContentPane().add(lblEstadoDeCompra);
		
		JLabel lblMontoTotal = new JLabel("Monto total");
		lblMontoTotal.setBounds(23, 30, 84, 14);
		frame.getContentPane().add(lblMontoTotal);
		
		txtMontoTotal = new JTextField();
		txtMontoTotal.setHorizontalAlignment(SwingConstants.CENTER);
		txtMontoTotal.setColumns(10);
		txtMontoTotal.setBounds(108, 27, 136, 20);
		frame.getContentPane().add(txtMontoTotal);
		
		JLabel lblPago = new JLabel("Pag\u00F3");
		lblPago.setBounds(23, 67, 84, 14);
		frame.getContentPane().add(lblPago);
		
		chckbxPago = new JCheckBox();
		chckbxPago.setBounds(109, 64, 20, 20);
		frame.getContentPane().add(chckbxPago);
		
		txtPago = new JTextField();
		txtPago.setText("0");
		txtPago.setHorizontalAlignment(SwingConstants.CENTER);
		txtPago.setColumns(10);
		txtPago.setBounds(134, 64, 110, 20);
		frame.getContentPane().add(txtPago);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(23, 148, 221, 23);
		frame.getContentPane().add(btnAceptar);
	}

	public JTextField getTxtMontoTotal() 
	{
		return txtMontoTotal;
	}

	public JTextField getTxtPago() 
	{
		return txtPago;
	}
	
	public JCheckBox getChckbxPago() 
	{
		return chckbxPago;
	}

	public JComboBox<EstadoDeCompraDTO> getComboBoxEstadoDeCompra() 
	{
		return comboBoxEstadoDeCompra;
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