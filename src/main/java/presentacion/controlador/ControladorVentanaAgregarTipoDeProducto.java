package presentacion.controlador;

import java.util.ArrayList;
import dto.TipoDeProductoDTO;
import modelo.GestorTipoDeProducto;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaAgregarTipoDeProducto;

public class ControladorVentanaAgregarTipoDeProducto implements SujetoObservable
{

	private VentanaAgregarTipoDeProducto ventana;
	private ArrayList<Observador> observadores;
	
	public ControladorVentanaAgregarTipoDeProducto(ControladorVentanaTipoDeProductoABM control)
	{
		ventana = new VentanaAgregarTipoDeProducto();
		ventana.getBtnAceptar().addActionListener(e -> this.registrarCliente());
		observadores = new ArrayList<Observador>();
		observadores.add(control);
	}
	
	public void initialize()
	{
		ventana.show();
	}

	public void registrarCliente()
	{
		TipoDeProductoDTO newTDP = new TipoDeProductoDTO();
		newTDP.setNombre(this.ventana.getTxtNombre().getText());
		newTDP.setPorcentajeDeGanancia(Integer.parseInt(this.ventana.getSpinnerGanancia().getValue().toString()));
		GestorTipoDeProducto.getInstance().insert(newTDP);
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