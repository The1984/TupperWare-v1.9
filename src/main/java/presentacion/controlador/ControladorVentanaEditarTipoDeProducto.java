package presentacion.controlador;

import java.util.ArrayList;

import dto.TipoDeProductoDTO;
import modelo.GestorTipoDeProducto;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaAgregarTipoDeProducto;

public class ControladorVentanaEditarTipoDeProducto implements SujetoObservable
{

	private VentanaAgregarTipoDeProducto ventana;
	private ArrayList<Observador> observadores;
	private TipoDeProductoDTO tipoDeProducto;
	
	public ControladorVentanaEditarTipoDeProducto(ControladorVentanaTipoDeProductoABM control, TipoDeProductoDTO tipoDeProducto)
	{
		this.ventana = new VentanaAgregarTipoDeProducto();
		this.tipoDeProducto = tipoDeProducto;
		this.ventana.getTxtNombre().setText(this.tipoDeProducto.getNombre());
		this.ventana.getSpinnerGanancia().setValue(this.tipoDeProducto.getPorcentajeDeGanancia());
		this.ventana.getBtnAceptar().addActionListener(e -> this.editarTipoDeProducto());
		this.observadores = new ArrayList<Observador>();
		this.observadores.add(control);
	}
	
	public void initialize()
	{
		this.ventana.show();
	}

	public void editarTipoDeProducto()
	{
		this.tipoDeProducto.setNombre(this.ventana.getTxtNombre().getText());
		this.tipoDeProducto.setPorcentajeDeGanancia(Integer.parseInt(this.ventana.getSpinnerGanancia().getValue().toString()));
		GestorTipoDeProducto.getInstance().update(this.tipoDeProducto);
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