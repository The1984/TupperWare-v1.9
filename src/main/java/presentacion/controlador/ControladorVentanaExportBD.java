package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import presentacion.vista.VentanaExportBD;
import util.ImportExportBD;

public class ControladorVentanaExportBD implements ActionListener
{

	private VentanaExportBD ventanaExpBD;
	private ImportExportBD importExportBD;
	
	public ControladorVentanaExportBD()
	{
		this.importExportBD = new ImportExportBD();	
		this.ventanaExpBD = new VentanaExportBD();
		this.ventanaExpBD.getBtnExport().addActionListener(this);
		this.ventanaExpBD.getBtnSelectFolder().addActionListener(this);
		this.initialize();
	}
	
	private void initialize()
	{
		this.ventanaExpBD.show();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		// Actions Export
		
		if (e.getSource() == this.ventanaExpBD.getBtnSelectFolder())
		{
			JFileChooser select = new JFileChooser();
			
			select.setAcceptAllFileFilterUsed(false);
			select.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int seGuarda = select.showSaveDialog(null);

			if(seGuarda == JFileChooser.APPROVE_OPTION)
			{
				String ruta = select.getSelectedFile().getPath();
				this.ventanaExpBD.getTextField().setText(ruta);;
			}
		}
		
		if (e.getSource() == this.ventanaExpBD.getBtnExport())
		{
			String rutaExport = this.ventanaExpBD.getTextField().getText();
			importExportBD.Exportar(rutaExport,this.ventanaExpBD);
		}
		
	}
		
}