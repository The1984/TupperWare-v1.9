package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.GridLayout;

public class PanelGestionCampa�as extends JPanel 
{

	private static final long serialVersionUID = -2645130709335183446L;

	private JTable tablaCampa�a;
	private DefaultTableModel modelCampa�a;
	private String[] nombreColumnasCampa�a = { "A�o","Numero","Cierre","Venta ($)","Ganancia ($)","Premios Pendientes","Pagos/Entregas Pendientes"};
	private JPanel panel;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnPromociones;
	private JButton btnPremios;
	
	public PanelGestionCampa�as() 
	{
		this.setBackground(new Color(5,35,27));
		
		JScrollPane scrollPane_campa�a = new JScrollPane();
		scrollPane_campa�a.setBorder(new TitledBorder(null, "Campa�as", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		panel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_campa�a, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_campa�a, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		Border borderButton = BorderFactory.createLineBorder(Color.black, 3);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBorder(borderButton);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBorder(borderButton);
		panel.add(btnEditar);

		btnPromociones = new JButton("Promociones");
		btnPromociones.setBorder(borderButton);
		panel.add(btnPromociones);
		
		btnPremios = new JButton("Premios");
		btnPremios.setBorder(borderButton);
		panel.add(btnPremios);
		
		modelCampa�a = new DefaultTableModel(null, nombreColumnasCampa�a) 
			{
				private static final long serialVersionUID = 1L;	
				public boolean isCellEditable(int row, int column) 
				{
					return false;
				}
			};
		
		tablaCampa�a = new JTable(modelCampa�a);
		scrollPane_campa�a.setViewportView(tablaCampa�a);
		setLayout(groupLayout);	
	}
	
	public JTable getTablaCampa�a() 
	{
		return tablaCampa�a;
	}

	public DefaultTableModel getModelCampa�a() 
	{
		return modelCampa�a;
	}

	public String[] getNombreColumnasCampa�a() 
	{
		return nombreColumnasCampa�a;
	}
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}

	public JButton getBtnEditar() 
	{
		return btnEditar;
	}
	
	public JButton getBtnPromociones() 
	{
		return btnPromociones;
	}

	public JButton getBtnPremios() 
	{
		return btnPremios;
	}
    
}