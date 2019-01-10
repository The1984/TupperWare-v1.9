package presentacion.controlador;

import presentacion.vista.VentanaComprarPromocion;

import java.util.ArrayList;
import java.util.List;

import dto.CampañaDTO;
import dto.ClienteDTO;
import dto.CompraDTO;
import dto.CompraPromocionDTO;
import dto.CompraPromocionProductoDTO;
import dto.ProductoDTO;
import dto.PromocionDTO;
import modelo.GestorCampaña;
import modelo.GestorCompra;
import modelo.GestorEstadoDeCompra;

public class ControladorVentanaComprarPromocion
{

	private VentanaComprarPromocion ventana;
	private PromocionDTO promocion;
	private List<ProductoDTO> listaDeProductos;
	private ClienteDTO client;
	
	public ControladorVentanaComprarPromocion(PromocionDTO promo)
	{
		this.ventana = new VentanaComprarPromocion();
		this.promocion = promo;
		this.ventana.getTxtNombre().setText(this.promocion.getNombre());
		this.ventana.getTxtrDescripcion().setText(this.promocion.getDescripcion());
		this.ventana.getBtnEliminar().addActionListener(e -> this.eliminarProducto());
		this.ventana.getBtnSelectCliente().addActionListener(e -> this.seleccionarCliente());
		this.ventana.getBtnComprar().addActionListener(e -> this.comprarPromocion());
		this.listaDeProductos = new ArrayList<ProductoDTO>();
		this.setearListaProductos();
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.ventana.show();
	}
	

	private void setearListaProductos()
	{
		for(ProductoDTO product : this.promocion.getProductos()) 
		{
			this.listaDeProductos.add(product);
		}
	}
	
	
	public void llenarTabla()
	{
		this.reiniciarTabla();
		
		for (ProductoDTO producto : this.listaDeProductos)
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
	
	public void eliminarProducto()
	{
		ProductoDTO productSelect = this.listaDeProductos.get(this.ventana.getTablaProducto().getSelectedRow());
		this.listaDeProductos.remove(productSelect);
		this.initialize();
	}
	
	public void seleccionarCliente()
	{
		ControladorVentanaSeleccionarClienteCompraPromocion contro = new ControladorVentanaSeleccionarClienteCompraPromocion(this);
		contro.initialize();
	}
	
	public void setearCliente(ClienteDTO cliente) 
	{
		this.client = cliente;
	}
	
	public void setearTextCliente()
	{
		this.ventana.getTextFieldCliente().setText(this.client.getNombre()+" "+this.client.getApellido());
	}
	
	public void comprarPromocion()
	{
		CampañaDTO campañaMasReciente;
		List<CampañaDTO> campañas = GestorCampaña.getInstance().readAll();
		campañaMasReciente = campañas.get(0);
		for (CampañaDTO campaña : campañas)
		{
			if(campañaMasReciente.getCierre().before(campaña.getCierre()))
				campañaMasReciente = campaña;
		}
		CompraDTO newCompra = new CompraDTO();
		newCompra.setCampaña(campañaMasReciente);
		newCompra.setCliente(this.client);
		newCompra.setPagina(this.ventana.getTextFieldPagina().getText());
		newCompra.setPorcentajeDeGanancia(this.listaDeProductos.get(0).getTipoDeProducto().getPorcentajeDeGanancia());
		newCompra.setPrecio(Integer.parseInt(this.ventana.getTextFieldPrecio().getText()));
		newCompra.setMontoPagado(Integer.parseInt(this.ventana.getTextFieldPago().getText()));
		newCompra.setUnidades(Integer.parseInt(this.ventana.getSpinnerUnidades().getValue().toString()));
		if(Integer.parseInt(this.ventana.getTextFieldPrecio().getText())*Integer.parseInt(this.ventana.getSpinnerUnidades().getValue().toString())
							==Integer.parseInt(this.ventana.getTextFieldPago().getText()))
		{
			newCompra.setEstadoDeCompra(GestorEstadoDeCompra.getInstance().readForId(1));
		}
		else 
		{
			newCompra.setEstadoDeCompra(GestorEstadoDeCompra.getInstance().readForId(2));			
		}

		CompraPromocionDTO newCompraPromocion = new CompraPromocionDTO();
		newCompraPromocion.setPromocion(this.promocion);
		GestorCompra.getInstance().insert(newCompraPromocion);
		int idCompraPromocion = GestorCompra.getInstance().idUltimoInsert();
		newCompraPromocion.setIdCompraPromocion(idCompraPromocion);
		newCompra.setCompraPromocion(newCompraPromocion);
		
		for(ProductoDTO product : this.listaDeProductos)
		{
			CompraPromocionProductoDTO newCPP = new CompraPromocionProductoDTO();
			newCPP.setIdProducto(product.getIdProducto());
			newCPP.setIdCompraPromocion(idCompraPromocion);
			GestorCompra.getInstance().insert(newCPP);
		}
		newCompra.setProducto(null);
		
		GestorCompra.getInstance().insert(newCompra);
		
		this.ventana.close();
	}
	
}