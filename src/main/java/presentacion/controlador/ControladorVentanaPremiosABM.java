package presentacion.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import dto.Campa�aDTO;
import dto.CompraDTO;
import dto.PremioDTO;
import modelo.GestorPremios;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaPremiosABM;
import presentacion.vista.VentanaVerPremio;

public class ControladorVentanaPremiosABM implements MouseListener, Observador, SujetoObservable
{
	private ArrayList<Observador> observadores;
	private VentanaPremiosABM ventana;
	private Campa�aDTO campa�a;
	
	public ControladorVentanaPremiosABM(Campa�aDTO campa�a, ControladorPanelGestionCampa�as control)
	{
		this.ventana = new VentanaPremiosABM();
		this.campa�a = campa�a;
		this.observadores = new ArrayList<Observador>();
		this.observadores.add(control);
		this.ventana.getTablaPremio().addMouseListener(this);
		this.ventana.getBtnAgregar().addActionListener(e -> this.agregarPremio());
		this.ventana.getBtnEditar().addActionListener(e -> this.editarPremio());
		this.ventana.getBtnEliminar().addActionListener(e -> this.eliminarPremio());
		this.ventana.getBtnEditar().setEnabled(false);
		this.ventana.getBtnEliminar().setEnabled(false);
	}
	
	public void initialize()
	{
		this.llenarTabla();
		this.ventana.show();
	}

	private void agregarPremio()
	{
		ControladorVentanaAgregarPremio contro = new ControladorVentanaAgregarPremio(this, this.campa�a);
		contro.initialize();
	}
	
	private void editarPremio()
	{
		PremioDTO premioSelect = GestorPremios.getInstance().readForIdCampa�a(this.campa�a.getIdCampa�a()).get(this.ventana.getTablaPremio().getSelectedRow());
		
		ControladorVentanaEditarPremio contro = new ControladorVentanaEditarPremio(this, premioSelect);
		contro.initialize();
	}

	private void eliminarPremio()
	{
		PremioDTO premioSelect = GestorPremios.getInstance().readForIdCampa�a(this.campa�a.getIdCampa�a()).get(this.ventana.getTablaPremio().getSelectedRow());
		
		if(JOptionPane.showConfirmDialog(null,"<html>�Est\u00E1 seguro que quiere eliminar el premio?</html>", "Eliminar Premio",JOptionPane.YES_NO_OPTION)==0) 
		{
			GestorPremios.getInstance().delete(premioSelect);
			this.update();
		}		
	}
	
	private void llenarTabla()
	{
		this.reiniciarTabla();
		
		for (PremioDTO premio : GestorPremios.getInstance().readForIdCampa�a(this.campa�a.getIdCampa�a()))
		{
			Object[] fila = {
								premio.getNombre(),
								premio.getDescripcion(),
								premio.getUnidadesMinimas(),
								this.ganoPremio(this.campa�a.getCompras(), premio),
								this.recibioPremio(premio)
							};
			this.ventana.getModelPremio().addRow(fila);
		}			
	}
	
	private String ganoPremio(List<CompraDTO> compras, PremioDTO premio)
	{
		int contadorDeCompras = 0;
		for (CompraDTO compra : compras)
		{
			if(compra.getProducto().getNombre()!=null&&!compra.getEstadoDeCompra().getNombre().equals("Cancelado"))
			{
				contadorDeCompras = contadorDeCompras + compra.getUnidades();				
			}
		}
		if(contadorDeCompras >= premio.getUnidadesMinimas())
		{
			return "Si";
		}
		return "No";
	}

	private String recibioPremio(PremioDTO premio)
	{
		if(premio.getRecibido()) 
		{ 
			return "Si";
		}
		return "No";
	}
	
	private void reiniciarTabla() 
	{
		this.ventana.getModelPremio().setRowCount(0); // Para vaciar la tabla
		this.ventana.getModelPremio().setColumnCount(0);
		this.ventana.getModelPremio().setColumnIdentifiers(this.ventana.getNombreColumnasPremio());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getClickCount()==2)
		{
			PremioDTO premioSelect = GestorPremios.getInstance().readForIdCampa�a(this.campa�a.getIdCampa�a()).get(this.ventana.getTablaPremio().getSelectedRow());
			
			VentanaVerPremio ventana = new VentanaVerPremio();
			ventana.getFrame().setTitle(premioSelect.getNombre());
			ventana.getTextArea().setText(premioSelect.getDescripcion());
			ventana.getTextFieldUnidades().setText(Integer.toString(premioSelect.getUnidadesMinimas()));
			ventana.show();
		}
		
		if(this.ventana.getTablaPremio().getSelectedRow() != -1)
		{
			this.ventana.getBtnEditar().setEnabled(true);
			this.ventana.getBtnEliminar().setEnabled(true);
		}
		else
		{
			this.ventana.getBtnEditar().setEnabled(false);
			this.ventana.getBtnEliminar().setEnabled(false);
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

	@Override
	public void update() 
	{
		this.llenarTabla();
		this.ventana.getTablaPremio().repaint();
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