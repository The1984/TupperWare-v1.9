package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JDialog;

public class VentanaConfiguracion
{

	private JDialog frame;
	private JButton btnCuenta;
	private JButton btnTipoDeProducto;
	
	public VentanaConfiguracion() 
	{
		frame = new JDialog();
		frame.setTitle("Configuracion");
		frame.setSize(253, 122);
		frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		frame.setModal(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		btnCuenta = new JButton("Cuenta");
		btnCuenta.setBounds(10, 11, 220, 28);
		frame.getContentPane().add(btnCuenta);
		
		btnTipoDeProducto = new JButton("Tipo de producto");
		btnTipoDeProducto.setBounds(10, 50, 220, 28);
		frame.getContentPane().add(btnTipoDeProducto);
	}

	public JButton getBtnCuenta() 
	{
		return btnCuenta;
	}

	public JButton getBtnTipoDeProducto() 
	{
		return btnTipoDeProducto;
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