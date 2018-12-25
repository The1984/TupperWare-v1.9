package presentacion.vista;

import javax.swing.AbstractButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class VentanaPrincipal 
{

	private JFrame frame;

	private JMenu mnBD;
	private JMenuItem btnExportarBD;
	private JMenuItem btnImportarBD;
	
	
	public VentanaPrincipal() 
	{
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setTitle("TupperWare");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(null);
		
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/Imagenes/iconVentana.png")));

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnBD = new JMenu("Base de Datos");
		menuBar.add(mnBD);
		btnImportarBD = new JMenuItem("Importar");
		btnExportarBD = new JMenuItem("Exportar");
		mnBD.add(btnImportarBD);
		mnBD.add(btnExportarBD);
		mnBD.setVisible(true);
		
	}

	public AbstractButton getBtnExportarBD() 
	{
		return btnExportarBD;
	}

	public AbstractButton getBtnImportarBD() 
	{
		return btnImportarBD;
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