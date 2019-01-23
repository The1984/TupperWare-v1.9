package util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class RenderForPremios extends DefaultTableCellRenderer
{
 
    public RenderForPremios()
    {
    	
    }

	public Component getTableCellRendererComponent(JTable table,
      Object value,
      boolean isSelected,
      boolean hasFocus,
      int row,
      int column)
   {
      super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);
      
	  if(value.equals("Si"))
      {
         this.setBackground(new Color(181,230,29));
         this.setForeground(Color.black);
      }
      else
      {
          if(value.equals("No"))
          {
             this.setBackground(new Color(215,0,0));
             this.setForeground(Color.white);
          }
          else
          {
      		 this.setBackground(Color.darkGray);
         	 this.setForeground(Color.white);            	  
          }
      }
      return this;
   }

}