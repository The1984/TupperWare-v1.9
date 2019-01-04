package presentacion.vista;

import javax.swing.AbstractButton;
import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.GridLayout;

public class VentanaPrincipal 
{

	private JFrame frame;
	private JMenu mnBD;
	private JMenuItem btnExportarBD;
	private JMenuItem btnImportarBD;
	private JPanel panelCentral;
	private JPanel panelNavegacion;
	private JButton btnGestionClientes;
	private JButton btnNegocio;
	private JButton btnGestionCampañas;
	private JButton btnCorreo;
	private JButton btnConfiguraciones;
	
	public VentanaPrincipal() 
	{
		frame = new JFrame();
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/Imagenes/iconVentana.png")));
		frame.setTitle("TupperWare");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnBD = new JMenu("Base de Datos");
		menuBar.add(mnBD);
		btnImportarBD = new JMenuItem("Importar");
		btnExportarBD = new JMenuItem("Exportar");
		mnBD.add(btnImportarBD);
		mnBD.add(btnExportarBD);
		mnBD.setVisible(true);
		
		panelCentral = new JPanel();
		panelCentral.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(panelCentral, BorderLayout.CENTER);
		
		GroupLayout gl_panelCentral = new GroupLayout(panelCentral);
		gl_panelCentral.setHorizontalGroup(
			gl_panelCentral.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 434, Short.MAX_VALUE)
		);
		gl_panelCentral.setVerticalGroup(
			gl_panelCentral.createParallelGroup(Alignment.LEADING)
				.addGap(0, 207, Short.MAX_VALUE)
		);
		panelCentral.setLayout(gl_panelCentral);
		
		panelNavegacion = new JPanel();
		frame.getContentPane().add(panelNavegacion, BorderLayout.NORTH);
		panelNavegacion.setLayout(new GridLayout(0, 5, 0, 0));
		
		btnGestionClientes = new JButton("Clientes");
		panelNavegacion.add(btnGestionClientes);
		
		btnNegocio = new JButton("Negocio");
		panelNavegacion.add(btnNegocio);
		
		btnGestionCampañas = new JButton("Campa\u00F1as");
		panelNavegacion.add(btnGestionCampañas);
		
		btnCorreo = new JButton("Correo");
		panelNavegacion.add(btnCorreo);
		
		btnConfiguraciones = new JButton("Configuraciones");
		panelNavegacion.add(btnConfiguraciones);	
	}

	public AbstractButton getBtnExportarBD() 
	{
		return btnExportarBD;
	}

	public AbstractButton getBtnImportarBD() 
	{
		return btnImportarBD;
	}

	public JPanel getPanelCentral() 
	{
		return panelCentral;
	}

	public JPanel getPanelNavegacion() 
	{
		return panelNavegacion;
	}

	public JButton getBtnGestionClientes() 
	{
		return btnGestionClientes;
	}

	public JButton getBtnNegocio() 
	{
		return btnNegocio;
	}
	
	public JButton getBtnGestionCampañas() 
	{
		return btnGestionCampañas;
	}

	public JButton getBtnCorreo() 
	{
		return btnCorreo;
	}

	public JButton getBtnConfiguraciones() 
	{
		return btnConfiguraciones;
	}
	
	public void limpiarPanelCentral()
	{
		this.panelCentral.removeAll();
		this.panelCentral.setLayout(new BorderLayout(0, 0));
		this.panelCentral.repaint();
	}
	
	public void setearPanelCentral(JPanel panel)
	{
		this.panelCentral.add(panel);
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