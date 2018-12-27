package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import presentacion.vista.VentanaImportBD;
import presentacion.vista.VentanaPrincipal;
import util.ImportExportBD;

public class ControladorVentanaImportBD implements ActionListener{

	private VentanaImportBD ventanaImpBD;
	private ImportExportBD importExportBD;
	private VentanaPrincipal view;
	
	public ControladorVentanaImportBD(VentanaPrincipal vista){

		view = vista;
		importExportBD = new ImportExportBD();

		ventanaImpBD = new VentanaImportBD();
		this.ventanaImpBD.getBtnImport().addActionListener(this);
		this.ventanaImpBD.getBtnSelectFile().addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Actions Import
		
		if (e.getSource() == this.ventanaImpBD.getBtnSelectFile()){
			JFileChooser select = new JFileChooser();

			FileNameExtensionFilter filter = new FileNameExtensionFilter("SQL","sql");
			select.setFileFilter(filter);
			
			int seOpen = select.showOpenDialog(null);

			if(seOpen == JFileChooser.APPROVE_OPTION){
				String ruta = select.getSelectedFile().getPath();
				this.ventanaImpBD.getTextField().setText(ruta);;
			}
		}
		
		if (e.getSource() == this.ventanaImpBD.getBtnImport()){
			String rutaImport = this.ventanaImpBD.getTextField().getText();
			importExportBD.Importar(rutaImport,this.ventanaImpBD,this.view);
		}
		
	}
	
}
