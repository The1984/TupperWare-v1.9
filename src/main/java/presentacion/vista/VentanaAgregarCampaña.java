package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import com.toedter.calendar.JYearChooser;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAgregarCampaña
{

	private JDialog frame;
	private JYearChooser añoChooser;
	private JSpinner numeroSpinner;
	private JDateChooser cierreChooser;
	private JButton btnAceptar;
	
	public VentanaAgregarCampaña() 
	{
		frame = new JDialog();
		frame.setTitle("Agregar Campaña");
		frame.setBounds(100, 100, 281, 188);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(0,64,64));
		
		JLabel lblAño = new JLabel("A\u00F1o");
		lblAño.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAño.setBounds(23, 30, 91, 14);
		frame.getContentPane().add(lblAño);
		
		JLabel lblNumero = new JLabel("Numero");
		lblNumero.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNumero.setBounds(150, 30, 59, 14);
		frame.getContentPane().add(lblNumero);
		
		JLabel lblFechaDeCierre = new JLabel("Fecha de cierre");
		lblFechaDeCierre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFechaDeCierre.setBounds(23, 69, 106, 20);
		frame.getContentPane().add(lblFechaDeCierre);
		
		añoChooser = new JYearChooser();
		añoChooser.setBounds(58, 27, 59, 20);
		frame.getContentPane().add(añoChooser);

		numeroSpinner = new JSpinner();
		numeroSpinner.setFont(new Font("Tahoma", Font.BOLD, 11));
		numeroSpinner.setModel(new SpinnerNumberModel(1, 1, 25, 1));
		numeroSpinner.setBounds(205, 27, 39, 20);
		frame.getContentPane().add(numeroSpinner);
		
		cierreChooser = new JDateChooser();
		cierreChooser.setBounds(139, 69, 105, 20);
		frame.getContentPane().add(cierreChooser);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnAceptar.setBounds(23, 115, 221, 23);
		frame.getContentPane().add(btnAceptar);
	}

	public JYearChooser getAñoChooser() 
	{
		return añoChooser;
	}

	public JSpinner getNumeroSpinner() 
	{
		return numeroSpinner;
	}

	public JDateChooser getCierreChooser() 
	{
		return cierreChooser;
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