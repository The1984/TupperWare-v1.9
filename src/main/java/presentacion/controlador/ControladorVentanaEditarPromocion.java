package presentacion.controlador;

import java.util.ArrayList;
import java.util.List;

import dto.ProductoDTO;
import dto.PromocionDTO;
import dto.PromocionProductoDTO;
import modelo.GestorPromociones;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaEditarPromocion;

public class ControladorVentanaEditarPromocion implements SujetoObservable
{

	private VentanaEditarPromocion ventana;
	private ArrayList<Observador> observadores;
	private PromocionDTO promocion;
	private List<ProductoDTO> listProductAntes;
	private List<ProductoDTO> listProductDespues;
	
	public ControladorVentanaEditarPromocion(ControladorVentanaPromocionesABM control, PromocionDTO promocion)
	{
		this.ventana = new VentanaEditarPromocion();
		this.promocion = promocion;
		this.ventana.getTxtNombre().setText(this.promocion.getNombre());
		this.ventana.getTxtrDescripcion().setText(this.promocion.getDescripcion());
		this.listProductAntes = new ArrayList<ProductoDTO>();
		this.listProductDespues = new ArrayList<ProductoDTO>();
		this.setearListaProductos();
		this.ventana.getBtnAgregar().addActionListener(e -> this.agregarProducto());
		this.ventana.getBtnEliminar().addActionListener(e -> this.eliminarProducto());
		this.ventana.getBtnAceptar().addActionListener(e -> this.actualizarPromocion());
		this.observadores = new ArrayList<Observador>();
		this.observadores.add(control);
	}
	
	public void initialize()
	{
		this.llenarTabla();
		ventana.show();
	}

	private void setearListaProductos()
	{
		for(ProductoDTO product : this.promocion.getProductos()) 
		{
			this.listProductAntes.add(product);
			this.listProductDespues.add(product);
		}
	}
	
	public void llenarTabla()
	{
		this.reiniciarTabla();
		
		for (ProductoDTO producto : this.listProductDespues)
		{
			Object[] fila = 
					{ 
						producto.getCodigo(),
						producto.getNombre(),
						producto.getTipoDeProducto().getNombre()
					};
			this.ventana.getModelProducto().addRow(fila);
		}			
	}

	private void reiniciarTabla() 
	{
		this.ventana.getModelProducto().setRowCount(0); // Para vaciar la tabla
		this.ventana.getModelProducto().setColumnCount(0);
		this.ventana.getModelProducto().setColumnIdentifiers(this.ventana.getNombreColumnasProducto());
	}
	
	private void agregarProducto()
	{
		ControladorVentanaSeleccionarProductoEditar contro = new ControladorVentanaSeleccionarProductoEditar(this);
		contro.initialize();
	}
	
	private void eliminarProducto()
	{
		ProductoDTO productSelect = this.listProductDespues.get(this.ventana.getTablaProducto().getSelectedRow());
		this.listProductDespues.remove(productSelect);
		this.initialize();
	}
	
	public void sumarProducto(ProductoDTO producto) 
	{
		this.listProductDespues.add(producto);
	}
	
	private void actualizarPromocion()
	{	
		this.promocion.setNombre(this.ventana.getTxtNombre().getText());
		this.promocion.setDescripcion(this.ventana.getTxtrDescripcion().getText());
		GestorPromociones.getInstance().update(promocion);
		
		for (ProductoDTO product : this.listProductAntes)
		{
			if(!this.listProductDespues.contains(product))
			{
				GestorPromociones.getInstance().delete(this.promocion.getIdPromocion(), product.getIdProducto());
			}
		}
		
		for (ProductoDTO product : this.listProductDespues)
		{
			if(!this.listProductAntes.contains(product))
			{
				PromocionProductoDTO newPromocionProducto = new PromocionProductoDTO();
				newPromocionProducto.setIdPromocion(this.promocion.getIdPromocion());
				newPromocionProducto.setIdProducto(product.getIdProducto());
				GestorPromociones.getInstance().insert(newPromocionProducto);
			}
		}
		
		this.ventana.close();
		this.notificar();
	}

	@Override
	public void notificar() 
	{
		for(Observador o : observadores) 
		{
			o.update();
		}
	}
	
}