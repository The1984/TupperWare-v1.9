package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class VentanaAgregarPremio
{

	private JDialog frame;
	private JTextField txtNombre;
	private JTextArea textArea;
	private JSpinner spinnerUnidadesMinimas;
	private JButton btnAceptar;
	
	public VentanaAgregarPremio() 
	{
		frame = new JDialog();
		frame.setTitle("Agregar Premio");
		frame.setSize(281, 278);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		txtNombre = new JTextField();
		txtNombre.setHorizontalAlignment(SwingConstants.CENTER);
		txtNombre.setBounds(119, 27, 125, 20);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(23, 30, 91, 14);
		frame.getContentPane().add(lblNombre);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(23, 205, 221, 23);
		frame.getContentPane().add(btnAceptar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 68, 221, 75);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel label = new JLabel("Descripcion");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(label);

		JLabel lblUnidades = new JLabel("Unidades");
		lblUnidades.setBounds(23, 170, 91, 14);
		frame.getContentPane().add(lblUnidades);
		
		spinnerUnidadesMinimas = new JSpinner();
		spinnerUnidadesMinimas.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinnerUnidadesMinimas.setBounds(119, 167, 125, 20);
		frame.getContentPane().add(spinnerUnidadesMinimas);		
	}

	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextArea getTextArea() 
	{
		return textArea;
	}

	public JSpinner getSpinnerUnidadesMinimas() 
	{
		return spinnerUnidadesMinimas;
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