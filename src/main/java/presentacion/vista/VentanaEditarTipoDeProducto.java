package presentacion.vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class VentanaEditarTipoDeProducto
{

	private JDialog frame;
	private JTextField txtNombre;
	private JSpinner spinnerGanancia;
	private JButton btnAceptar;
	
	public VentanaEditarTipoDeProducto() 
	{
		frame = new JDialog();
		frame.setTitle("Editar - Tipo de Producto");
		frame.setSize(281, 180);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(0,64,64));
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setBounds(119, 27, 125, 20);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(23, 30, 91, 14);
		frame.getContentPane().add(lblNombre);
		
		JLabel lblGanancia = new JLabel("Ganancia");
		lblGanancia.setBounds(23, 72, 91, 14);
		frame.getContentPane().add(lblGanancia);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(23, 107, 221, 23);
		frame.getContentPane().add(btnAceptar);
		
		spinnerGanancia = new JSpinner();
		spinnerGanancia.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		spinnerGanancia.setBounds(119, 69, 125, 20);
		frame.getContentPane().add(spinnerGanancia);
	}

	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}
	
	public JSpinner getSpinnerGanancia() 
	{
		return spinnerGanancia;
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