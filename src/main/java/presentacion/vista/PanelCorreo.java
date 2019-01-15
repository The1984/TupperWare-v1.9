package presentacion.vista;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Color;

public class PanelCorreo extends JPanel 
{

	private static final long serialVersionUID = -2645130709335183446L;
	
	public PanelCorreo() 
	{
		this.setBackground(new Color(167,43,1));
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 450, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 300, Short.MAX_VALUE)
		);

		setLayout(groupLayout);	
	}
	
}