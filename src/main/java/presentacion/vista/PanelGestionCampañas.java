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

public class PanelGestionCampaņas extends JPanel 
{

	private static final long serialVersionUID = -2645130709335183446L;

	private JTable tablaCampaņa;
	private DefaultTableModel modelCampaņa;
	private String[] nombreColumnasCampaņa = { "Aņo","Numero","Cierre","Venta ($)","Ganancia ($)","Premios Pendientes","Pagos/Entregas Pendientes"};
	private JPanel panel;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnPromociones;
	private JButton btnPremios;
	
	public PanelGestionCampaņas() 
	{
		this.setBackground(new Color(5,35,27));
		
		JScrollPane scrollPane_campaņa = new JScrollPane();
		scrollPane_campaņa.setBorder(new TitledBorder(null, "Campaņas", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		
		panel = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane_campaņa, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_campaņa, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
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
		
		modelCampaņa = new DefaultTableModel(null, nombreColumnasCampaņa) 
			{
				private static final long serialVersionUID = 1L;	
				public boolean isCellEditable(int row, int column) 
				{
					return false;
				}
			};
		
		tablaCampaņa = new JTable(modelCampaņa);
		scrollPane_campaņa.setViewportView(tablaCampaņa);
		setLayout(groupLayout);	
	}
	
	public JTable getTablaCampaņa() 
	{
		return tablaCampaņa;
	}

	public DefaultTableModel getModelCampaņa() 
	{
		return modelCampaņa;
	}

	public String[] getNombreColumnasCampaņa() 
	{
		return nombreColumnasCampaņa;
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