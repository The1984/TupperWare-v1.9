package presentacion.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CampañaDTO;
import dto.CompraDTO;
import dto.ProductoDTO;
import observer.Observador;
import presentacion.vista.PanelGestionCompras;
import util.RenderForCompras;

public class ControladorPanelGestionCompras implements KeyListener, MouseListener, Observador
{

	private List<CompraDTO> compras_filtradas;
	private CampañaDTO campañaSelect;
	private PanelGestionCompras panelCompras;

	public ControladorPanelGestionCompras(PanelGestionCompras panel, CampañaDTO campaña)
	{
		this.campañaSelect = campaña;
		this.panelCompras = panel;
		this.panelCompras.getLblCampaña().setText("Campaña:  "+this.campañaSelect.getAño()+" - N°"+this.campañaSelect.getNumero());
		this.compras_filtradas = new ArrayList<CompraDTO>();
		this.panelCompras.getTextFiltro().addKeyListener(this);
		this.panelCompras.getBtnGenerarReporte().addActionListener(e -> this.generarReporte());
		this.panelCompras.getBtnEditar().addActionListener(e -> this.editarCompra());
		this.panelCompras.getTablaCompra().addMouseListener(this);
		this.panelCompras.getBtnEditar().setEnabled(false);
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.panelCompras.getTablaCompra().setDefaultRenderer(Object.class, new RenderForCompras());
		this.panelCompras.repaint();
	}
	
	private void llenarTabla()
	{
		reiniciarTabla();
		
		for (CompraDTO compra : this.campañaSelect.getCompras())
		{
			Object[] fila = {
								compra.getCliente().getNombre()+" "+compra.getCliente().getApellido(),
								this.listarProductos(compra),
								compra.getUnidades(),
								compra.getPrecio(),
								(compra.getPrecio()*compra.getUnidades())-compra.getMontoPagado(),
								compra.getEstadoDeCompra().getNombre()
							};
			this.compras_filtradas.add(compra);
			this.panelCompras.getModelCompra().addRow(fila);
		}
	}

	private String listarProductos(CompraDTO compra)
	{
		String list = "";
		if(compra.getProducto().getNombre()!=null)
		{
			list = list + compra.getProducto().getNombre();
		}
		if(compra.getCompraPromocion().getProductos()!=null)
		{
			for(ProductoDTO producto : compra.getCompraPromocion().getProductos())
			{
				list = list + producto.getNombre();
				if(!producto.equals(compra.getCompraPromocion().getProductos().get(compra.getCompraPromocion().getProductos().size()-1)))
				{
					list = list + " / ";
				}
				else
				{
					list = list + "  - (♦)";
				}
			}
		}
		return list;
	}
	
	private void reiniciarTabla() 
	{
		this.panelCompras.getModelCompra().setRowCount(0); // Para vaciar la tabla
		this.panelCompras.getModelCompra().setColumnCount(0);
		this.panelCompras.getModelCompra().setColumnIdentifiers(this.panelCompras.getNombreColumnasCompra());
	}
	
	public void filtrarTabla(String cadena) 
	{
		reiniciarTabla();
		
		this.compras_filtradas.clear();
		
		for (CompraDTO compra : this.campañaSelect.getCompras())
		{
			String getNombre = compra.getCliente().getNombre().toUpperCase()+" "+compra.getCliente().getApellido().toUpperCase()+
							   listarProductos(compra).toUpperCase()+
							   compra.getEstadoDeCompra().getNombre().toUpperCase();
			if (getNombre.indexOf(cadena.toUpperCase()) != -1) 
			{
				this.compras_filtradas.add(compra);
				Object[] fila = 
					{ 
						compra.getCliente().getNombre()+" "+compra.getCliente().getApellido(),
						this.listarProductos(compra),
						compra.getUnidades(),
						compra.getPrecio(),
						(compra.getPrecio()*compra.getUnidades())-compra.getMontoPagado(),
						compra.getEstadoDeCompra().getNombre()
					};
				this.panelCompras.getModelCompra().addRow(fila);
			}
		}
	}
	
	private void generarReporte()
	{
		if(this.campañaSelect.getCompras().size()==0)
		{
			JOptionPane.showMessageDialog(null, "¡No posee compras en esta campaña!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		ControladorVentanaGenerarReporte contro = new ControladorVentanaGenerarReporte(this.campañaSelect);
		contro.initialize();
	}
	
	private void editarCompra() 
	{
		CompraDTO compraSelect = this.compras_filtradas.get(this.panelCompras.getTablaCompra().getSelectedRow());
		ControladorVentanaEditarCompra contro = new ControladorVentanaEditarCompra(this, compraSelect);
		contro.initialize();
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		String cadena = this.panelCompras.getTextFiltro().getText();
		this.filtrarTabla(cadena);		
	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		if(this.panelCompras.getTablaCompra().getSelectedRow() != -1)
		{
			this.panelCompras.getBtnEditar().setEnabled(true);
		}
		else
		{
			this.panelCompras.getBtnEditar().setEnabled(false);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}
	
	@Override
	public void update() 
	{
		this.llenarTabla();
		this.panelCompras.getTextFiltro().setText("");
		this.panelCompras.repaint();
	}

}