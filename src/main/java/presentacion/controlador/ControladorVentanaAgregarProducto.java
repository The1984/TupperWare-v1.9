package presentacion.controlador;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import dto.ProductoDTO;
import dto.TipoDeProductoDTO;
import modelo.GestorProductos;
import modelo.GestorTipoDeProducto;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaAgregarProducto;
import util.ValidadorCampos;
import util.ValidadorLogico;

public class ControladorVentanaAgregarProducto implements SujetoObservable, MouseListener
{

	private VentanaAgregarProducto ventana;
	private ArrayList<Observador> observadores;
	private ProductoDTO newProducto;
	private String rutaImagen;
	
	public ControladorVentanaAgregarProducto(ControladorPanelNegocio control)
	{
		ventana = new VentanaAgregarProducto();
		this.llenarComboBoxTipoDeProducto();
		ventana.getBtnAceptar().addActionListener(e -> this.registrarProducto());
		ventana.getLblImagen().addMouseListener(this);
		
	    observadores = new ArrayList<Observador>();
		observadores.add(control);
		newProducto = new ProductoDTO();
		this.rutaImagen = System.getProperty("user.dir") + "/src/main/resources/Imagenes/ImagenProductoDefault.png";
	}
	
	public void initialize()
	{
		ventana.show();
	}

	public void registrarProducto()
	{
		if(ValidadorCampos.campoVacio(this.ventana.getTxtCodigo().getText())||
		   ValidadorCampos.campoVacio(this.ventana.getTxtNombre().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡Los campos codigo y nombre son obligatorios!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(ValidadorLogico.existeProductoNombreAgregar(this.ventana.getTxtNombre().getText(), GestorProductos.getInstance().readAll())) 
		{
			JOptionPane.showMessageDialog(null, "¡Nombre de producto ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;	
		}
		if(ValidadorLogico.existeProductoCodigoAgregar(this.ventana.getTxtCodigo().getText(), GestorProductos.getInstance().readAll())) 
		{
			JOptionPane.showMessageDialog(null, "¡Codigo de producto ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;	
		}
		newProducto.setCodigo(this.ventana.getTxtCodigo().getText());
		newProducto.setNombre(this.ventana.getTxtNombre().getText());
		newProducto.setDescripcion(this.ventana.getTxtrDescripcion().getText());
		newProducto.setTipoDeProducto(this.ventana.getComboBoxTipoDeProducto().getItemAt(this.ventana.getComboBoxTipoDeProducto().getSelectedIndex()));
		GestorProductos.getInstance().insert(this.newProducto);
		
		int idNewProducto = GestorProductos.getInstance().idUltimoInsert();
		GestorProductos.getInstance().insert(this.rutaImagen, idNewProducto);
		
		this.ventana.close();
		this.notificar();
	}
	
	private void llenarComboBoxTipoDeProducto() 
	{
		List<TipoDeProductoDTO> tiposDeProductos = GestorTipoDeProducto.getInstance().readAll();
		for(TipoDeProductoDTO tipoDeProducto : tiposDeProductos) 
		{
			this.ventana.getComboBoxTipoDeProducto().addItem(tipoDeProducto);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{
		if(e.getClickCount()==2)
		{
			JFileChooser select = new JFileChooser();

			select.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG", "png","jpg");
			select.setFileFilter(filter);
			
			int seOpen = select.showOpenDialog(null);

			if(seOpen == JFileChooser.APPROVE_OPTION)
			{
				float longitud = new File(select.getSelectedFile().getPath()).length();
				if(longitud/1024 >= 64)
				{
					JOptionPane.showMessageDialog(null, "¡Tamaño de imagen mayor a 64kb!", "Error", JOptionPane.ERROR_MESSAGE);
					return;			
				}
				this.rutaImagen = select.getSelectedFile().getPath();
			    ImageIcon fot = new ImageIcon(this.rutaImagen);
			    Icon icono = new ImageIcon(fot.getImage().getScaledInstance(this.ventana.getLblImagen().getWidth(), this.ventana.getLblImagen().getHeight(), Image.SCALE_DEFAULT));
			    this.ventana.getLblImagen().setIcon(icono);
			    this.ventana.getLblImagen().repaint();
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
	
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		
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