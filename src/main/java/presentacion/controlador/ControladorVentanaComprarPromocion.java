package presentacion.controlador;

import presentacion.vista.VentanaComprarPromocion;
import util.ValidadorCampos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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

public class ControladorVentanaComprarPromocion implements MouseListener
{

	private VentanaComprarPromocion ventana;
	private PromocionDTO promocion;
	private List<ProductoDTO> listaDeProductos;
	private ClienteDTO client;
	
	public ControladorVentanaComprarPromocion(PromocionDTO promo)
	{
		this.ventana = new VentanaComprarPromocion();
		this.promocion = promo;
		this.ventana.getFrame().setTitle(this.promocion.getNombre());
		this.ventana.getTxtrDescripcion().setText(this.promocion.getDescripcion());
		this.ventana.getTxtPagina().setText(this.promocion.getPagina());
		this.ventana.getTxtPrecio().setText(Integer.toString(this.promocion.getPrecio()));
		this.ventana.getBtnEliminar().addActionListener(e -> this.eliminarProducto());
		this.ventana.getBtnSelectCliente().addActionListener(e -> this.seleccionarCliente());
		this.ventana.getBtnComprar().addActionListener(e -> this.comprarPromocion());
		this.ventana.getCheckbxPago().addMouseListener(this);
		this.listaDeProductos = new ArrayList<ProductoDTO>();
		this.ventana.getTxtPagina().setEnabled(false);
		this.ventana.getTxtPrecio().setEnabled(false);
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
		this.ventana.getTxtCliente().setText(this.client.getNombre()+" "+this.client.getApellido());
	}
	
	public void comprarPromocion()
	{
		if(ValidadorCampos.campoVacio(this.ventana.getTxtCliente().getText())||
		   ValidadorCampos.campoVacio(this.ventana.getTxtPagina().getText())||
		   ValidadorCampos.campoVacio(this.ventana.getTxtPrecio().getText())||
		   ValidadorCampos.campoVacio(this.ventana.getTxtPago().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡Los campos cliente, pagina, precio y monto pagado son obligatorios!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(!ValidadorCampos.isNumeric(this.ventana.getTxtPrecio().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡Precio no es un numero!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(!ValidadorCampos.isNumeric(this.ventana.getTxtPago().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡El monto pagado no es un numero!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(Integer.parseInt(this.ventana.getTxtPrecio().getText())*Integer.parseInt(this.ventana.getSpinnerUnidades().getValue().toString())
				<Integer.parseInt(this.ventana.getTxtPago().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡El monto pagado es mayor al monto total!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
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
		newCompra.setPagina(this.ventana.getTxtPagina().getText());
		newCompra.setPorcentajeDeGanancia(this.listaDeProductos.get(0).getTipoDeProducto().getPorcentajeDeGanancia());
		newCompra.setPrecio(Integer.parseInt(this.ventana.getTxtPrecio().getText()));
		newCompra.setMontoPagado(Integer.parseInt(this.ventana.getTxtPago().getText()));
		newCompra.setUnidades(Integer.parseInt(this.ventana.getSpinnerUnidades().getValue().toString()));
		if(Integer.parseInt(this.ventana.getTxtPrecio().getText())*Integer.parseInt(this.ventana.getSpinnerUnidades().getValue().toString())
							==Integer.parseInt(this.ventana.getTxtPago().getText()))
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
		
		JOptionPane.showMessageDialog(null, "Compra realizada exitosamente!!");
	}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		if(this.ventana.getCheckbxPago().isSelected())
		{
			int montoTotal = Integer.parseInt(this.ventana.getTxtPrecio().getText()) * Integer.parseInt(this.ventana.getSpinnerUnidades().getValue().toString());
			this.ventana.getTxtPago().setText(String.valueOf(montoTotal));
			this.ventana.getTxtPago().setEnabled(false);
		}
		else
		{
			this.ventana.getTxtPago().setText("0");
			this.ventana.getTxtPago().setEnabled(true);
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
	
}