package presentacion.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import dto.CompraDTO;
import dto.EstadoDeCompraDTO;
import modelo.GestorCompra;
import modelo.GestorEstadoDeCompra;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaEditarCompra;

public class ControladorVentanaEditarCompra implements SujetoObservable, MouseListener
{

	private VentanaEditarCompra ventana;
	private ArrayList<Observador> observadores;
	private CompraDTO compra_a_editar;
	
	public ControladorVentanaEditarCompra(ControladorPanelGestionCompras control, CompraDTO compra)
	{
		this.ventana = new VentanaEditarCompra();
		this.llenarComboBoxEstadoDeCompra();
		this.compra_a_editar = compra;
		this.ventana.getTxtMontoTotal().setText(Integer.toString(compra.getPrecio()*compra.getUnidades()));
		this.ventana.getTxtMontoTotal().setEnabled(false);
		this.ventana.getTxtPago().setText(Integer.toString(this.compra_a_editar.getMontoPagado()));
		if(this.ventana.getTxtMontoTotal().getText().equals(this.ventana.getTxtPago().getText()))
		{
			this.ventana.getChckbxPago().setSelected(true);
		}
		this.ventana.getComboBoxEstadoDeCompra().setSelectedIndex(compra_a_editar.getEstadoDeCompra().getIdEstadoDeCompra()-1);
		
		ventana.getBtnAceptar().addActionListener(e -> this.editarCompra());
		this.ventana.getChckbxPago().addMouseListener(this);
		
	    observadores = new ArrayList<Observador>();
		observadores.add(control);
	}
	
	public void initialize()
	{
		ventana.show();
	}

	public void editarCompra()
	{
		this.compra_a_editar.setMontoPagado(Integer.parseInt(this.ventana.getTxtPago().getText()));
		this.compra_a_editar.setEstadoDeCompra(this.ventana.getComboBoxEstadoDeCompra().getItemAt(this.ventana.getComboBoxEstadoDeCompra().getSelectedIndex()));
		GestorCompra.getInstance().update(compra_a_editar);
		this.ventana.close();
		this.notificar();
	}
	
	private void llenarComboBoxEstadoDeCompra() 
	{
		List<EstadoDeCompraDTO> estados = GestorEstadoDeCompra.getInstance().readAll();
		for(EstadoDeCompraDTO estado: estados) 
		{
			this.ventana.getComboBoxEstadoDeCompra().addItem(estado);
		}
	}

	@Override
	public void notificar() 
	{
		for(Observador o : observadores) 
		{
			o.update();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		if(this.ventana.getChckbxPago().isSelected())
		{
			this.ventana.getTxtPago().setText(this.ventana.getTxtMontoTotal().getText());
			this.ventana.getTxtPago().setEnabled(false);
			this.ventana.getComboBoxEstadoDeCompra().setSelectedIndex(0);
			this.ventana.getComboBoxEstadoDeCompra().setEnabled(false);
		}
		else
		{
			this.ventana.getTxtPago().setText(Integer.toString(this.compra_a_editar.getMontoPagado()));
			this.ventana.getComboBoxEstadoDeCompra().setSelectedIndex(1);
			this.ventana.getTxtPago().setEnabled(true);
			this.ventana.getComboBoxEstadoDeCompra().setEnabled(true);
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