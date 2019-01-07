package presentacion.controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import dto.CampañaDTO;
import dto.CompraDTO;
import observer.Observador;
import presentacion.vista.PanelGestionCompras;

public class ControladorPanelGestionCompras implements KeyListener, Observador
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
		this.panelCompras.getBtnEditar().addActionListener(e -> this.editarCompra());
		this.panelCompras.getBtnEliminar().addActionListener(e -> this.eliminarCompra());
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.panelCompras.repaint();
	}
	
	private void llenarTabla()
	{
		reiniciarTabla();
		
		for (CompraDTO compra : this.campañaSelect.getCompras())
		{
			Object[] fila = {
								compra.getCliente().getNombre()+" "+compra.getCliente().getApellido(),
								compra.getProducto().getNombre(),
								compra.getUnidades(),
								compra.getPrecio(),
								(compra.getPrecio()*compra.getUnidades())-compra.getMontoPagado(),
								compra.getEstadoDeCompra().getNombre()
							};
			this.compras_filtradas.add(compra);
			this.panelCompras.getModelCompra().addRow(fila);
		}
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
							   compra.getProducto().getNombre().toUpperCase()+
							   compra.getEstadoDeCompra().getNombre().toUpperCase();
			if (getNombre.indexOf(cadena.toUpperCase()) != -1) 
			{
				this.compras_filtradas.add(compra);
				Object[] fila = 
					{ 
						compra.getCliente().getNombre()+" "+compra.getCliente().getApellido(),
						compra.getProducto().getNombre(),
						compra.getUnidades(),
						compra.getPrecio(),
						(compra.getPrecio()*compra.getUnidades())-compra.getMontoPagado(),
						compra.getEstadoDeCompra().getNombre()
					};
				this.panelCompras.getModelCompra().addRow(fila);
			}
		}
	}
	
	private void editarCompra() 
	{
		
	}
	
	public void eliminarCompra()
	{

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
	public void update() 
	{
		this.llenarTabla();
		this.panelCompras.getTextFiltro().setText("");
		this.panelCompras.repaint();
	}
	
}