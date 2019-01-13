package presentacion.controlador;

import presentacion.vista.VentanaComprarProducto;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JOptionPane;

import dto.CampañaDTO;
import dto.ClienteDTO;
import dto.CompraDTO;
import dto.ProductoDTO;
import modelo.GestorCampaña;
import modelo.GestorCompra;
import modelo.GestorEstadoDeCompra;

public class ControladorVentanaComprarProducto implements MouseListener
{

	private VentanaComprarProducto ventana;
	private ProductoDTO product;
	private ClienteDTO client;
	
	public ControladorVentanaComprarProducto(ProductoDTO producto)
	{
		this.ventana = new VentanaComprarProducto();
		this.product = producto;
		this.setearTextProducto(this.product);
		this.ventana.getBtnSelectCliente().addActionListener(e -> this.seleccionarCliente());
		this.ventana.getBtnAceptar().addActionListener(e -> this.comprarProducto());
		this.ventana.getChckbxPago().addMouseListener(this);
	}
	
	public void initialize()
	{
		ventana.show();
	}
	
	public void setearTextProducto(ProductoDTO producto)
	{
		this.ventana.getTxtNameProducto().setText(producto.getNombre());
	}
	
	public void seleccionarCliente()
	{
		ControladorVentanaSeleccionarClienteCompraProducto contro = new ControladorVentanaSeleccionarClienteCompraProducto(this);
		contro.initialize();
	}
	
	public void setearCliente(ClienteDTO cliente) 
	{
		this.client = cliente;
	}
	
	public void setearTextCliente()
	{
		this.ventana.getTxtNameCliente().setText(this.client.getNombre()+" "+this.client.getApellido());
	}
	
	public void comprarProducto()
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
		newCompra.setProducto(this.product);
		newCompra.setCliente(this.client);
		newCompra.setPagina(this.ventana.getTxtPagina().getText());
		newCompra.setPorcentajeDeGanancia(this.product.getTipoDeProducto().getPorcentajeDeGanancia());
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
		newCompra.setCompraPromocion(null);
		GestorCompra.getInstance().insert(newCompra);
		this.ventana.close();
		JOptionPane.showMessageDialog(null, "Compra realizada exitosamente!!");
	}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		if(this.ventana.getChckbxPago().isSelected())
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