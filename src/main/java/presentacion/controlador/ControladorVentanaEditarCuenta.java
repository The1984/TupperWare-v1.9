package presentacion.controlador;

import javax.swing.JOptionPane;

import presentacion.vista.VentanaEditarCuenta;
import util.Encriptador;
import util.OwnerProperties;

public class ControladorVentanaEditarCuenta
{
	
	private VentanaEditarCuenta ventana;
	
	public ControladorVentanaEditarCuenta()
	{
		ventana = new VentanaEditarCuenta();
		ventana.getTxtNombre().setText(OwnerProperties.getInstance().getNombre());
		ventana.getTxtApellido().setText(OwnerProperties.getInstance().getApellido());
		ventana.getTxtEmail().setText(OwnerProperties.getInstance().getEmail());
		ventana.getBtnAceptar().addActionListener(e -> this.editarCuenta());
	}
	
	public void initialize()
	{
		ventana.show();
	}

	public void editarCuenta()
	{
		if(!Encriptador.sha1(this.ventana.getTxtContraseña().getText()).equals(Encriptador.sha1(this.ventana.getTxtRepitaCont().getText())))
		{
			JOptionPane.showMessageDialog(null, "¡Contraseña Incorrecta!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		OwnerProperties.getInstance().setNombre(this.ventana.getTxtNombre().getText());
		OwnerProperties.getInstance().setApellido(this.ventana.getTxtApellido().getText());
		OwnerProperties.getInstance().setEmail(this.ventana.getTxtEmail().getText());
		OwnerProperties.getInstance().setPass(Encriptador.sha1(this.ventana.getTxtContraseña().getText()));
		this.ventana.close();
	}
	
}