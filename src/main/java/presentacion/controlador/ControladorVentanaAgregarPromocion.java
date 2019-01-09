package presentacion.controlador;

import java.util.ArrayList;
import java.util.List;

import dto.CampañaDTO;
import dto.ProductoDTO;
import dto.PromocionDTO;
import dto.PromocionProductoDTO;
import modelo.GestorPromociones;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaAgregarPromocion;

public class ControladorVentanaAgregarPromocion implements SujetoObservable
{

	private VentanaAgregarPromocion ventana;
	private ArrayList<Observador> observadores;
	private CampañaDTO campaña;
	private PromocionDTO newPromocion;
	private List<ProductoDTO> listProduct;
	
	public ControladorVentanaAgregarPromocion(ControladorVentanaPromocionesABM control, CampañaDTO campaña)
	{
		this.ventana = new VentanaAgregarPromocion();
		this.campaña = campaña;
		this.listProduct = new ArrayList<ProductoDTO>();
		this.ventana.getBtnAgregar().addActionListener(e -> this.agregarProducto());
		this.ventana.getBtnEliminar().addActionListener(e -> this.eliminarProducto());
		this.ventana.getBtnAceptar().addActionListener(e -> this.registrarPromocion());
		this.observadores = new ArrayList<Observador>();
		this.observadores.add(control);
		this.newPromocion = new PromocionDTO();
	}
	
	public void initialize()
	{
		this.llenarTabla();
		ventana.show();
	}

	public void llenarTabla()
	{
		this.reiniciarTabla();
		
		for (ProductoDTO producto : this.listProduct)
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
		ControladorVentanaSeleccionarProductoAgregar contro = new ControladorVentanaSeleccionarProductoAgregar(this);
		contro.initialize();
	}
	
	private void eliminarProducto()
	{
		ProductoDTO productSelect = this.listProduct.get(this.ventana.getTablaProducto().getSelectedRow());
		this.listProduct.remove(productSelect);
		this.initialize();
	}
	
	public void sumarProducto(ProductoDTO producto) 
	{
		this.listProduct.add(producto);
	}
	
	private void registrarPromocion()
	{	
		this.newPromocion.setNombre(this.ventana.getTxtNombre().getText());
		this.newPromocion.setDescripcion(this.ventana.getTxtrDescripcion().getText());
		this.newPromocion.setCampaña(this.campaña);
		GestorPromociones.getInstance().insert(newPromocion);

		int idPromocion = GestorPromociones.getInstance().idUltimoInsert();
		for (ProductoDTO product : this.listProduct)
		{
			PromocionProductoDTO newPP = new PromocionProductoDTO();
			newPP.setIdProducto(product.getIdProducto());
			newPP.setIdPromocion(idPromocion);
			GestorPromociones.getInstance().insert(newPP);			
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