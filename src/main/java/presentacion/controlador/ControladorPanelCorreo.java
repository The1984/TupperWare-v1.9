package presentacion.controlador;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import dto.ClienteDTO;
import presentacion.vista.PanelCorreo;
import servicio.Correo;
import util.ValidadorCampos;

public class ControladorPanelCorreo
{

	private PanelCorreo panelCorreo;
	private ClienteDTO client;

	public ControladorPanelCorreo(PanelCorreo panel)
	{
		this.panelCorreo = panel;
		this.panelCorreo.getTxtReceptor().setEditable(false);
		Date fechaActual = new Date();
		String fechaActualString = new SimpleDateFormat("dd-MM-yyyy").format(fechaActual);
		this.panelCorreo.getTxtFecha().setText(fechaActualString);
		this.panelCorreo.getTxtFecha().setEditable(false);

		this.panelCorreo.getBtnCrearMensaje().addActionListener ( e -> this.enviarMensaje() );
		this.panelCorreo.getBtnAdjuntarArchivo().addActionListener ( e -> this.adjuntarArchivo() );
		this.panelCorreo.getBtnAsignarReceptor().addActionListener( e -> this.asignarReceptor() );
		
		Correo.getInstance().setearMultipart();
	}

	public void initialize()
	{
		this.panelCorreo.show();
	}
		
	public void setearCliente(ClienteDTO cliente) 
	{
		this.client = cliente;
	}

	public void setearTextCliente()
	{
		this.panelCorreo.getTxtReceptor().setText(this.client.getNombre()+" "+this.client.getApellido());
	}
	
	private void asignarReceptor() 
	{
		ControladorVentanaSeleccionarClienteCrearMensaje contro = new ControladorVentanaSeleccionarClienteCrearMensaje(this);
		contro.initialize();
	}
	
	private void adjuntarArchivo() 
	{
		JFileChooser select = new JFileChooser();
		
		select.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF","pdf");
		select.setFileFilter(filter);
		
		int seOpen = select.showOpenDialog(null);

		if(seOpen == JFileChooser.APPROVE_OPTION)
		{
			Correo.getInstance().adjuntarArchivo(select.getSelectedFile().getPath());
			this.panelCorreo.getBtnAdjuntarArchivo().setBackground(new Color(34,177,76));
			this.panelCorreo.getBtnAdjuntarArchivo().setForeground(Color.black);
			this.panelCorreo.repaint();
		}
	}

	private void enviarMensaje()
	{
		if(ValidadorCampos.campoVacio(this.panelCorreo.getTxtReceptor().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡Falta completar el receptor!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(ValidadorCampos.campoVacio(this.panelCorreo.getTxtAsunto().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡Introduzca asunto al mensaje!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		Correo.getInstance().setDestinatario(this.client.getEmail());
		Correo.getInstance().setAsunto(this.panelCorreo.getTxtAsunto().getText());
		Correo.getInstance().setMensaje(this.panelCorreo.getTextAreaMensaje().getText());

		Correo.getInstance().sendEmail();
	}

}