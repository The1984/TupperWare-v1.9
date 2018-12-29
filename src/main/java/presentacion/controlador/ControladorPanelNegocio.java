package presentacion.controlador;

import java.util.ArrayList;
import java.util.List;

import dto.ProductoDTO;
import modelo.GestorProductos;
import presentacion.vista.PanelNegocio;

public class ControladorPanelNegocio 
{

	private List<ProductoDTO> productos_en_tabla;
	private List<ProductoDTO> productos_filtrados;
	private PanelNegocio panelCompra;
	
	public ControladorPanelNegocio(PanelNegocio panel)
	{
		this.productos_en_tabla = null;
		this.productos_filtrados = new ArrayList<ProductoDTO>();
		this.productos_filtrados = GestorProductos.getInstance().readAll();
		panelCompra = panel;
		
	}
	
	public void initialize()
	{
		this.llenarTablaProducto();
		this.llenarTablaPromocion();
		this.panelCompra.repaint();
	}
	
	private void llenarTablaProducto()
	{
		this.panelCompra.getModelProducto().setRowCount(0); //Para vaciar la tabla
		this.panelCompra.getModelProducto().setColumnCount(0);
		this.panelCompra.getModelProducto().setColumnIdentifiers(this.panelCompra.getNombreColumnasProducto());
		
		this.productos_en_tabla = GestorProductos.getInstance().readAll();
		for (ProductoDTO producto : productos_en_tabla)
		{
			Object[] fila = {
								producto.getCodigo(),
								producto.getNombre(),
								producto.getDescripcion(),
								producto.getTipoDeProducto().getNombre()
							};
			this.panelCompra.getModelProducto().addRow(fila);
		}			
	}

	private void llenarTablaPromocion()
	{

	}
	
}