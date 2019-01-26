package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import presentacion.vista.VentanaImportBD;
import presentacion.vista.VentanaPrincipal;
import util.ImportExportBD;

public class ControladorVentanaImportBD implements ActionListener
{

	private VentanaImportBD ventanaImpBD;
	private ImportExportBD importExportBD;
	private VentanaPrincipal ventanaPrincipal;
	
	public ControladorVentanaImportBD(VentanaPrincipal ventanaPrincipal)
	{
		this.ventanaPrincipal = ventanaPrincipal;
		this.importExportBD = new ImportExportBD();
		this.ventanaImpBD = new VentanaImportBD();
		this.ventanaImpBD.getBtnImport().addActionListener(this);
		this.ventanaImpBD.getBtnSelectFile().addActionListener(this);	
		this.initialize();
	}
	
	private void initialize()
	{
		this.ventanaImpBD.show();
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		// Actions Import
		
		if (e.getSource() == this.ventanaImpBD.getBtnSelectFile())
		{
			JFileChooser select = new JFileChooser();
			
			select.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("SQL","sql");
			select.setFileFilter(filter);
			
			int seOpen = select.showOpenDialog(null);

			if(seOpen == JFileChooser.APPROVE_OPTION)
			{
				String ruta = select.getSelectedFile().getPath();
				this.ventanaImpBD.getTextField().setText(ruta);
			}
		}
		
		if (e.getSource() == this.ventanaImpBD.getBtnImport())
		{
			String rutaImport = this.ventanaImpBD.getTextField().getText();
			this.importExportBD.Importar(rutaImport,this.ventanaImpBD,this.ventanaPrincipal);
		}
		
	}
	
}