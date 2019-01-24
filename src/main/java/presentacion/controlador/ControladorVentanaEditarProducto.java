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

import dto.ImagenDeProductoDTO;
import dto.ProductoDTO;
import dto.TipoDeProductoDTO;
import modelo.GestorProductos;
import modelo.GestorTipoDeProducto;
import observer.Observador;
import observer.SujetoObservable;
import presentacion.vista.VentanaEditarProducto;
import util.ValidadorCampos;
import util.ValidadorLogico;

public class ControladorVentanaEditarProducto implements SujetoObservable, MouseListener
{

	private VentanaEditarProducto ventana;
	private ArrayList<Observador> observadores;
	private ProductoDTO producto_a_editar;
	private String rutaImagen;
	
	public ControladorVentanaEditarProducto(ControladorPanelNegocio control, ProductoDTO producto)
	{
		ventana = new VentanaEditarProducto();
		this.producto_a_editar = producto;
		
		this.ventana.getTxtCodigo().setText(producto_a_editar.getCodigo());
		this.ventana.getTxtNombre().setText(producto_a_editar.getNombre());
		this.ventana.getTxtrDescripcion().setText(producto_a_editar.getDescripcion());
		this.llenarComboBoxTipoDeProducto();
		this.ventana.getComboBoxTipoDeProducto().setSelectedIndex(producto_a_editar.getTipoDeProducto().getIdTipoDeProducto()-1);

		ImagenDeProductoDTO imagen = GestorProductos.getInstance().readForIdProducto(this.producto_a_editar.getIdProducto());
	    Icon icono = new ImageIcon(imagen.getImagen().getScaledInstance(this.ventana.getLblImagen().getWidth(), this.ventana.getLblImagen().getHeight(), Image.SCALE_DEFAULT));
	    this.ventana.getLblImagen().setIcon(icono);
		
		ventana.getBtnAceptar().addActionListener(e -> this.editarProducto());
		ventana.getLblImagen().addMouseListener(this);
		
	    observadores = new ArrayList<Observador>();
		observadores.add(control);
		this.rutaImagen = "";
	}
	
	public void initialize()
	{
		ventana.show();
	}

	public void editarProducto()
	{
		if(ValidadorCampos.campoVacio(this.ventana.getTxtCodigo().getText())||
	       ValidadorCampos.campoVacio(this.ventana.getTxtNombre().getText()))
		{
			JOptionPane.showMessageDialog(null, "¡Los campos Codigo y Nombre son obligatorios!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(ValidadorLogico.existeProductoNombreEditar(producto_a_editar.getIdProducto(), this.ventana.getTxtNombre().getText(), GestorProductos.getInstance().readAll())) 
		{
			JOptionPane.showMessageDialog(null, "¡Nombre de producto ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;									
		}
		if(ValidadorLogico.existeProductoCodigoEditar(producto_a_editar.getIdProducto(), this.ventana.getTxtCodigo().getText(), GestorProductos.getInstance().readAll())) 
		{
			JOptionPane.showMessageDialog(null, "¡Codigo de producto ya existente!", "Warning", JOptionPane.WARNING_MESSAGE);
			return;									
		}
		producto_a_editar.setCodigo(this.ventana.getTxtCodigo().getText());
		producto_a_editar.setNombre(this.ventana.getTxtNombre().getText());
		producto_a_editar.setDescripcion(this.ventana.getTxtrDescripcion().getText());
		producto_a_editar.setTipoDeProducto(this.ventana.getComboBoxTipoDeProducto().getItemAt(this.ventana.getComboBoxTipoDeProducto().getSelectedIndex()));
		GestorProductos.getInstance().update(this.producto_a_editar);
		
		if(this.rutaImagen != "")
		{
			GestorProductos.getInstance().update(this.rutaImagen, producto_a_editar.getIdProducto());			
		}

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