package presentacion.controlador;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CampañaDTO;
import dto.ProductoDTO;
import dto.PromocionDTO;
import dto.PromocionProductoDTO;
import modelo.GestorPromociones;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaAgregarPromocion;
import util.ValidadorCampos;
import util.ValidadorLogico;

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
		if(ValidadorCampos.campoVacio(this.ventana.getTxtNombre().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡El campo nombre es obligatorios!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(ValidadorCampos.campoVacio(this.ventana.getTxtPagina().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡El campo pagina es obligatorio!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(ValidadorCampos.campoVacio(this.ventana.getTxtPrecio().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡El campo precio es obligatorio!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(this.listProduct.size()==0)
		{
			JOptionPane.showMessageDialog(null, "¡Introduzca productos en la promocion!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;			
		}
		if(ValidadorLogico.existePromocionNombreAgregar(this.ventana.getTxtNombre().getText(), this.campaña.getPromociones()))
		{
			JOptionPane.showMessageDialog(null, "¡Nombre de promocion ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;		
		}
		if(!ValidadorCampos.isNumeric(this.ventana.getTxtPrecio().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡Precio no es un numero!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		this.newPromocion.setNombre(this.ventana.getTxtNombre().getText());
		this.newPromocion.setDescripcion(this.ventana.getTxtrDescripcion().getText());
		this.newPromocion.setPagina(this.ventana.getTxtPagina().getText());
		this.newPromocion.setPrecio(Integer.parseInt(this.ventana.getTxtPrecio().getText()));
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