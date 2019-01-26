package presentacion.controlador;

import javax.swing.JOptionPane;

import presentacion.vista.VentanaConfiguracion;
import presentacion.vista.VentanaEditarCuenta;
import presentacion.vista.VentanaPrincipal;
import servicio.Correo;
import util.Encriptador;
import util.OwnerProperties;
import util.ValidadorCampos;

public class ControladorVentanaEditarCuenta
{
	
	private VentanaEditarCuenta ventana;
	private VentanaPrincipal ventanaPrincipal;
	private VentanaConfiguracion ventanaConfig;
	
	public ControladorVentanaEditarCuenta(VentanaPrincipal ventanaPrincipal, VentanaConfiguracion ventanaConfig)
	{
		this.ventanaPrincipal = ventanaPrincipal;
		this.ventanaConfig = ventanaConfig;
		this.ventana = new VentanaEditarCuenta();
		this.ventana.getTxtNombre().setText(OwnerProperties.getInstance().getNombre());
		this.ventana.getTxtApellido().setText(OwnerProperties.getInstance().getApellido());
		this.ventana.getTxtEmail().setText(OwnerProperties.getInstance().getEmail());
		this.ventana.getTxtContraseña().setText(Correo.getInstance().getPasswordEmisor());
		this.ventana.getTxtRepitaCont().setText(Correo.getInstance().getPasswordEmisor());
		this.ventana.getBtnAceptar().addActionListener(e -> this.editarCuenta());
	}
	
	public void initialize()
	{
		this.ventana.show();
	}

	public void editarCuenta()
	{
		if(ValidadorCampos.campoVacio(this.ventana.getTxtContraseña().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡El campo contraseña es obligatorio!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(!Encriptador.sha1(this.ventana.getTxtContraseña().getText()).equals(Encriptador.sha1(this.ventana.getTxtRepitaCont().getText())))
		{
			JOptionPane.showMessageDialog(null, "¡Contraseña Incorrecta!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(!OwnerProperties.getInstance().getNombre().equals(this.ventana.getTxtNombre().getText())||
		   !OwnerProperties.getInstance().getApellido().equals(this.ventana.getTxtApellido().getText())||
		   !OwnerProperties.getInstance().getEmail().equals(this.ventana.getTxtEmail().getText())||
		   !OwnerProperties.getInstance().getPass().equals(Encriptador.sha1(this.ventana.getTxtContraseña().getText())))
		{
			OwnerProperties.getInstance().setNombre(this.ventana.getTxtNombre().getText());
			OwnerProperties.getInstance().setApellido(this.ventana.getTxtApellido().getText());
			OwnerProperties.getInstance().setEmail(this.ventana.getTxtEmail().getText());
			OwnerProperties.getInstance().setPass(Encriptador.sha1(this.ventana.getTxtContraseña().getText()));
			this.ventana.close();
			this.ventanaConfig.close();
			this.ventanaPrincipal.close();
			ControladorVentanaLogin controladoLogin=new ControladorVentanaLogin();
			controladoLogin.initialize();			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "¡Introduzca algun cambio!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
	}
	
}