package presentacion.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import dto.CompraDTO;
import dto.ProductoDTO;
import presentacion.vista.VentanaVerCompras;

public class ControladorVentanaVerCompras implements KeyListener
{
	
	private VentanaVerCompras ventana;
	private List<CompraDTO> compra_en_tabla;
	private List<CompraDTO> compras_filtradas;
	
	public ControladorVentanaVerCompras(List<CompraDTO> compras)
	{
		this.ventana = new VentanaVerCompras();
		this.compras_filtradas = new ArrayList<CompraDTO>();
		this.compra_en_tabla = compras;
		this.ventana.getTxtFiltro().addKeyListener(this);
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.ventana.show();
	}

	private void llenarTabla()
	{
		this.reiniciarTabla();
		
		for (CompraDTO compra : this.compra_en_tabla)
		{
			Object[] fila = 
					{ 
						compra.getCampaña().getAño()+" - N° "+compra.getCampaña().getNumero(),
						this.listarProductos(compra),
						compra.getEstadoDeCompra().getNombre()
					};
			this.compras_filtradas.add(compra);
			this.ventana.getModelCompras().addRow(fila);
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
		this.ventana.getModelCompras().setRowCount(0); // Para vaciar la tabla
		this.ventana.getModelCompras().setColumnCount(0);
		this.ventana.getModelCompras().setColumnIdentifiers(this.ventana.getNombreColumnasCompras());
	}
	
	public void filtrarTabla(String cadena) 
	{
		reiniciarTabla();
		
		compras_filtradas.clear();
		
		for (CompraDTO compra : this.compra_en_tabla)
		{
			String getNombre = compra.getCampaña().getAño().toUpperCase()+" - "+compra.getCampaña().getNumero().toUpperCase()+" "+
							   this.listarProductos(compra).toUpperCase()+" "+
							   compra.getEstadoDeCompra().getNombre().toUpperCase();
			if (getNombre.indexOf(cadena.toUpperCase()) != -1) 
			{
				compras_filtradas.add(compra);
				Object[] fila = 
					{ 
						compra.getCampaña().getAño()+" - "+compra.getCampaña().getNumero(),
						this.listarProductos(compra),
						compra.getEstadoDeCompra().getNombre()
					};
				this.ventana.getModelCompras().addRow(fila);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}

	@Override
	public void keyReleased(KeyEvent arg0) 
	{
		String cadena = this.ventana.getTxtFiltro().getText();
		this.filtrarTabla(cadena);		
	}

	@Override
	public void keyTyped(KeyEvent arg0) 
	{
		// TODO Auto-generated method stub	
	}
	
}