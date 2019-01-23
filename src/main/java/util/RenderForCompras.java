package util;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

@SuppressWarnings("serial")
public class RenderForCompras extends DefaultTableCellRenderer
{
 
    public RenderForCompras()
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
      if(value.equals("Mora"))
      {
         this.setBackground(new Color(215,0,0));
         this.setForeground(Color.white);
      }
      else 
      {
    	  if(value.equals("Pagado"))
          {
             this.setBackground(new Color(206,206,0));
             this.setForeground(Color.black);
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