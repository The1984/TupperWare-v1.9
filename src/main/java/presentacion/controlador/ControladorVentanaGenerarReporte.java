package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import dto.CampañaDTO;
import presentacion.vista.VentanaGenerarReporte;
import util.GeneradorDeReporte;
import util.OwnerProperties;
import util.ValidadorCampos;

public class ControladorVentanaGenerarReporte implements ActionListener
{

	private VentanaGenerarReporte ventanaGenerarReporte;
	private CampañaDTO campaña;
	
	public ControladorVentanaGenerarReporte(CampañaDTO campaña)
	{	
		this.ventanaGenerarReporte = new VentanaGenerarReporte();
		this.campaña = campaña;
		this.ventanaGenerarReporte.getBtnSelectFolder().addActionListener(this);
		this.ventanaGenerarReporte.getBtnGenerarReporteVendedor().addActionListener(this);
		this.ventanaGenerarReporte.getBtnGenerarReporteLider().addActionListener(this);
	}
	
	public void initialize()
	{
		this.ventanaGenerarReporte.show();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		if (e.getSource() == this.ventanaGenerarReporte.getBtnSelectFolder())
		{
			JFileChooser select = new JFileChooser();
			
			select.setAcceptAllFileFilterUsed(false);
			select.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int seGuarda = select.showSaveDialog(null);

			if(seGuarda == JFileChooser.APPROVE_OPTION)
			{
				String ruta = select.getSelectedFile().getPath();
				this.ventanaGenerarReporte.getTextField().setText(ruta);
			}
		}
		if (e.getSource() == this.ventanaGenerarReporte.getBtnGenerarReporteVendedor())
		{
			if(ValidadorCampos.campoVacio(this.ventanaGenerarReporte.getTextField().getText())) 
			{
				JOptionPane.showMessageDialog(null, "¡Falta introducir la ruta del archivo!", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String ruta = this.ventanaGenerarReporte.getTextField().getText()+"\\Reporte - Campaña - "+this.campaña.getAño()+" - N°"+this.campaña.getNumero()+".pdf";
			GeneradorDeReporte gen = new GeneradorDeReporte(ruta);
			gen.openDocument();
			gen.ReporteVendedor(this.campaña);
			gen.closeDocument();
		}
		
		if (e.getSource() == this.ventanaGenerarReporte.getBtnGenerarReporteLider())
		{
			if(ValidadorCampos.campoVacio(this.ventanaGenerarReporte.getTextField().getText())) 
			{
				JOptionPane.showMessageDialog(null, "¡Falta introducir la ruta del archivo!", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String ruta = this.ventanaGenerarReporte.getTextField().getText()+"\\Pedido - "+OwnerProperties.getInstance().getApellido()+" "+OwnerProperties.getInstance().getNombre()+" - Campaña - "+this.campaña.getAño()+" - N°"+this.campaña.getNumero()+".pdf";
			GeneradorDeReporte gen = new GeneradorDeReporte(ruta);
			gen.openDocument();
			gen.ReporteLider(this.campaña);
			gen.closeDocument();
		}		
		
	}
		
}