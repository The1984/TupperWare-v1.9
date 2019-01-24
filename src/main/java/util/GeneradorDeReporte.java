package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dto.Campa�aDTO;
import dto.CompraDTO;
import dto.PremioDTO;
import dto.ProductoDTO;

public class GeneradorDeReporte 
{
	
	private Document document;
	
	public GeneradorDeReporte(String ruta)
	{
		this.document = new Document();
        try 
        {
			PdfWriter.getInstance(document, new FileOutputStream(new File(ruta)));
		} 
        catch (FileNotFoundException e) 
        {
			e.printStackTrace();
		} 
        catch (DocumentException e) 
        {
			e.printStackTrace();
		}
	}
	
	public void ReporteVendedor( Campa�aDTO campa�a ) 
	{
		try 
		{
			Image imagen = Image.getInstance("src/main/resources/Imagenes/HeaderReporte.png");
			imagen.setBorder(Image.BOX);
			imagen.setAlignment(Element.ALIGN_CENTER);
			imagen.scalePercent(40);
			document.add(imagen);
	
	        Date fechaActual = new Date();
			String fechaActualString = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual);
			String horaActualString = new SimpleDateFormat("HH:mm").format(fechaActual);
	        Font fuenteFecha = new Font();
	        fuenteFecha.setStyle(Font.BOLD);
	        fuenteFecha.setSize(10);
	        Paragraph p4 = new Paragraph(fechaActualString+" - "+horaActualString, fuenteFecha);
	        p4.setAlignment(Element.ALIGN_RIGHT);
	        document.add(p4);
			
	        document.add(new Paragraph(" "));

	        Font fuenteDatos = new Font();
	        fuenteDatos.setStyle(Font.BOLD);
	        fuenteDatos.setSize(11);
	        
	        Paragraph p2 = new Paragraph();
	        p2.setFont(fuenteDatos);
	        p2.add("Vendedor@: "+OwnerProperties.getInstance().getApellido()+" "+OwnerProperties.getInstance().getNombre()); //no alignment
	        document.add(p2);
	
	        Paragraph p3 = new Paragraph();
	        p3.setFont(fuenteDatos);
	        p3.add("Campa�a: "+campa�a.getA�o()+" - N�"+campa�a.getNumero()); //no alignment
	        document.add(p3);
	
	        document.add(new Paragraph(" "));
	        document.add(new Paragraph(" "));
			
	        //----------------------------------------------------------------
	        //CREAR UNA TABLA
	        //----------------------------------------------------------------
	        
	        PdfPTable table = new PdfPTable(7);
	
	        PdfPCell c1 = new PdfPCell(new Phrase("N�"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(new BaseColor(255,206,36));
	        table.addCell(c1);
	        
	        c1 = new PdfPCell(new Phrase("Cliente"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(new BaseColor(255,206,36));
	        table.addCell(c1);
	
	        c1 = new PdfPCell(new Phrase("Producto"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(new BaseColor(255,206,36));
	        table.addCell(c1);

	        c1 = new PdfPCell(new Phrase("Precio Unit."));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(new BaseColor(255,206,36));
	        table.addCell(c1);
	    	
	        c1 = new PdfPCell(new Phrase("Unidades"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(new BaseColor(255,206,36));
	        table.addCell(c1);

	        c1 = new PdfPCell(new Phrase("Debe"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(new BaseColor(255,206,36));
	        table.addCell(c1);

	        c1 = new PdfPCell(new Phrase("Estado"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(new BaseColor(255,206,36));
	        table.addCell(c1);
	        table.setHeaderRows(1);
	        

	        float[] medidaCeldas = {0.45f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f, 1.25f};
	        table.setWidths(medidaCeldas);
	        
	        int contador = 1;
	        
	        List<CompraDTO> listOrdenada = this.ordenarPorEstado(campa�a.getCompras());
	        
	        for (CompraDTO compra : listOrdenada)
	        {
		        c1 = new PdfPCell(new Phrase(Integer.toString(contador)));
	 	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	        c1.setBackgroundColor(new BaseColor(255,206,36));
	        	table.addCell(c1);
	        	table.addCell(compra.getCliente().getApellido()+" "+compra.getCliente().getNombre());
	        	table.addCell(this.listarProductosNombre(compra));
	        	table.addCell(Integer.toString(compra.getPrecio()));
	        	table.addCell(Integer.toString(compra.getUnidades())+this.esPromocion(compra));
	        	table.addCell(Integer.toString((compra.getUnidades()*compra.getPrecio())-compra.getMontoPagado()));
	        	table.addCell(compra.getEstadoDeCompra().getNombre());
	        	contador++;
	        }
	        
	        document.add(table);

	        document.add(new Paragraph(" "));

	        PdfPTable tableResul = new PdfPTable(2);
	
	        c1 = new PdfPCell(new Phrase("Venta"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(new BaseColor(0,162,232));
	        tableResul.addCell(c1);
	        
	        c1 = new PdfPCell(new Phrase("Ganancia"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(new BaseColor(0,162,232));
	        tableResul.addCell(c1);
	
	        tableResul.addCell("$ "+this.montoDeVenta(campa�a.getCompras()));
	        tableResul.addCell("$ "+this.gananciaDeVenta(campa�a.getCompras()));
	        document.add(tableResul);
	        
	        if(this.premioGanados(campa�a).size()!=0)
	        {
		        document.add(new Paragraph(" "));
		        Paragraph p5 = new Paragraph();
		        fuenteDatos.setColor(new BaseColor(128,0,0));
		        p5.setFont(fuenteDatos);
		        p5.add("*** Premios Ganados ***");
		        document.add(p5);
		        document.add(new Paragraph(" "));		        
		        for(String premio : this.premioGanados(campa�a))
		        {
			        Paragraph p6 = new Paragraph();
			        fuenteDatos.setColor(new BaseColor(0,0,0));
			        fuenteDatos.setSize(10);
			        p6.setFont(fuenteDatos);
			        p6.add(premio);
			        document.add(p6);		        	
		        }
	        }
	        
	        JOptionPane.showMessageDialog(null, "Reporte generado exitosamente.");    
		} 
		catch (DocumentException | IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<CompraDTO> ordenarPorEstado(List<CompraDTO> compras)
	{
		List<CompraDTO> listCompras = new ArrayList<>();
		for(CompraDTO compra : compras)
		{
			if(compra.getEstadoDeCompra().getNombre().equals("Entregado/Mora"))
			{
				listCompras.add(compra);
			}
		}
		for(CompraDTO compra : compras)
		{
			if(compra.getEstadoDeCompra().getNombre().equals("Mora"))
			{
				listCompras.add(compra);
			}
		}
		for(CompraDTO compra : compras)
		{
			if(compra.getEstadoDeCompra().getNombre().equals("Pagado"))
			{
				listCompras.add(compra);
			}
		}
		for(CompraDTO compra : compras)
		{
			if(compra.getEstadoDeCompra().getNombre().equals("Entregado"))
			{
				listCompras.add(compra);
			}
		}
		return listCompras;
	}
	
	private String listarProductosNombre(CompraDTO compra) 
	{
		String list = "";
		if(compra.getProducto().getNombre() != null)
		{
			list = list + compra.getProducto().getNombre();
		}
		if(compra.getCompraPromocion().getProductos() != null) 
		{
			for(ProductoDTO producto : compra.getCompraPromocion().getProductos())
			{
				list = list + producto.getNombre();

				if(!compra.getCompraPromocion().getProductos().get(compra.getCompraPromocion().getProductos().size()-1).equals(producto)) 
				{
					list = list + " / ";	
				}
			}
		}
		return list;
	}

	private String esPromocion(CompraDTO compra) 
	{
		if(compra.getProducto().getNombre()==null)
		{
			return " (Set)";
		}
		return "";
	}

	private int montoDeVenta(List<CompraDTO> compras)
	{
		int sumatoria = 0;
		for (CompraDTO compra : compras)
		{
			if(!compra.getEstadoDeCompra().getNombre().equals("Cancelado"))
				sumatoria = sumatoria + (compra.getPrecio()*compra.getUnidades());
		}
		return sumatoria;
	}
	
	private int gananciaDeVenta(List<CompraDTO> compras)
	{
		int ganancia = 0;
		for (CompraDTO compra : compras)
		{
			if(!compra.getEstadoDeCompra().getNombre().equals("Cancelado"))
				ganancia = ganancia + ((compra.getPrecio()*compra.getUnidades())*compra.getPorcentajeDeGanancia())/100;
		}
		return ganancia;
	}
	
	private List<String> premioGanados(Campa�aDTO campa�a)
	{
		int contadorDeCompras = 0;
		List<String> listString = new ArrayList<>();
		for (CompraDTO compra : campa�a.getCompras())
		{
			if(compra.getProducto().getNombre()!=null&&!compra.getEstadoDeCompra().getNombre().equals("Cancelado"))
			{
				contadorDeCompras = contadorDeCompras + compra.getUnidades();				
			}
		}
		for (PremioDTO premio : campa�a.getPremios())
		{
			if(contadorDeCompras >= premio.getUnidadesMinimas() && premio.getRecibido()==false)
				listString.add("* Premio: "+premio.getNombre()+" - Descripcion: "+premio.getDescripcion()+" - Unidades: "+premio.getUnidadesMinimas());
		}
		return listString;
	}
	
	public void ReporteLider( Campa�aDTO campa�a ) 
	{
		try 
		{
    		Image imagen = Image.getInstance("src/main/resources/Imagenes/HeaderReporte.png");
			imagen.setBorder(Image.BOX);
			imagen.setAlignment(Element.ALIGN_CENTER);
			imagen.scalePercent(40);
			document.add(imagen);
	
	        Date fechaActual = new Date();
			String fechaActualString = new SimpleDateFormat("dd/MM/yyyy").format(fechaActual);
			String horaActualString = new SimpleDateFormat("HH:mm").format(fechaActual);
	        Font fuenteFecha = new Font();
	        fuenteFecha.setStyle(Font.BOLD);
	        fuenteFecha.setSize(10);
	        Paragraph p4 = new Paragraph(fechaActualString+" - "+horaActualString, fuenteFecha);
	        p4.setAlignment(Element.ALIGN_RIGHT);
	        document.add(p4);
			
	        document.add(new Paragraph(" "));

	        Font fuenteDatos = new Font();
	        fuenteDatos.setStyle(Font.BOLD);
	        fuenteDatos.setSize(11);
	        
	        Paragraph p2 = new Paragraph();
	        p2.setFont(fuenteDatos);
	        p2.add("Vendedor@: "+OwnerProperties.getInstance().getApellido()+" "+OwnerProperties.getInstance().getNombre()); //no alignment
	        document.add(p2);
	
	        Paragraph p3 = new Paragraph();
	        p3.setFont(fuenteDatos);
	        p3.add("Campa�a: "+campa�a.getA�o()+" - N�"+campa�a.getNumero()); //no alignment
	        document.add(p3);
	
	        document.add(new Paragraph(" "));
	        document.add(new Paragraph(" "));
			
	        //----------------------------------------------------------------
	        //CREAR UNA TABLA
	        //----------------------------------------------------------------
	        
	        PdfPTable table = new PdfPTable(4);

	        PdfPCell c1 = new PdfPCell(new Phrase("N�"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(new BaseColor(181,230,29));
	        table.addCell(c1);
	        
	        c1 = new PdfPCell(new Phrase("Pagina"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(new BaseColor(181,230,29));
	        table.addCell(c1);
	
	        c1 = new PdfPCell(new Phrase("Codigo"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(new BaseColor(181,230,29));
	        table.addCell(c1);
	
	        c1 = new PdfPCell(new Phrase("Unidades"));
	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c1.setBackgroundColor(new BaseColor(181,230,29));
	        table.addCell(c1);
	        table.setHeaderRows(1);
	        
	        float[] medidaCeldas = {0.30f, 1.25f, 1.25f, 1.25f};
	        table.setWidths(medidaCeldas);
	        
	        List<CompraDTO> comprasNoCanceladas = this.comprasNoCanceladas(campa�a.getCompras());
	        List<CompraParaLider> compraAgrupada = this.agruparCompras(comprasNoCanceladas);
	        List<CompraParaLider> compraOrdenanda = this.ordenarCompras(compraAgrupada);
	        int contador = 1;
	        for (CompraParaLider compra : compraOrdenanda)
	        {
	        	c1 = new PdfPCell(new Phrase(Integer.toString(contador)));
	 	        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	 	        c1.setBackgroundColor(new BaseColor(181,230,29));
	        	table.addCell(c1);
	        	table.addCell(compra.getPagina());
	        	table.addCell(compra.getCodigo());
	        	table.addCell(Integer.toString(compra.getUnidades())+this.esPromocion(compra));
	        	contador++;
	        }
	        
	        document.add(table);
	        JOptionPane.showMessageDialog(null, "Reporte generado exitosamente.");
		} 
		catch (DocumentException | IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String listarProductosCodigo(CompraDTO compra) 
	{
		String list = "";
		if(compra.getProducto().getCodigo() != null)
		{
			list = list + compra.getProducto().getCodigo();
		}
		if(compra.getCompraPromocion().getProductos() != null) 
		{
			for(ProductoDTO producto : compra.getCompraPromocion().getProductos())
			{
				list = list + producto.getCodigo();

				if(!compra.getCompraPromocion().getProductos().get(compra.getCompraPromocion().getProductos().size()-1).equals(producto)) 
				{
					list = list + " / ";	
				}
			}
		}
		return list;
	}
	
	private String esPromocion(CompraParaLider compra) 
	{
		if(compra.getCodigo().contains(" / "))
		{
			return " (Set)";
		}
		return "";
	}
	
	private List<CompraDTO> comprasNoCanceladas(List<CompraDTO> compras)
	{
		List<CompraDTO> comprasFiltradas = new ArrayList<>();
		for(CompraDTO compra : compras)
		{
			if(!compra.getEstadoDeCompra().getNombre().equals("Cancelado"))
			{
				comprasFiltradas.add(compra);
			}	
		}
		return comprasFiltradas;
	}
	
	private List<CompraParaLider> agruparCompras(List<CompraDTO> compras)
	{
		List<CompraParaLider> compraLider = new ArrayList<>();
		for(CompraDTO compra1 : compras) 
		{
			if(!estaEnCompraParaLider(compra1, compraLider))
			{
				CompraParaLider newCom = new CompraParaLider();
				newCom.setCodigo(this.listarProductosCodigo(compra1));
				newCom.setPagina(compra1.getPagina());
				newCom.setUnidades(0);
				for(CompraDTO compra2 : compras) 
				{
					if(this.listarProductosCodigo(compra2).equals(newCom.getCodigo()))
					{
						newCom.setUnidades(newCom.getUnidades()+compra2.getUnidades());
					}
				}
				compraLider.add(newCom);
			}
		}
		return compraLider;
	}
	
	private boolean estaEnCompraParaLider(CompraDTO com, List<CompraParaLider> comLids)
	{
		for(CompraParaLider comLid : comLids)
		{
			if(comLid.getCodigo().equals(this.listarProductosCodigo(com)))
			{
				return true;
			}
		}
		return false;
	}
	
	private List<CompraParaLider> ordenarCompras(List<CompraParaLider> compras)
	{
		List<CompraParaLider> compraReturn = new ArrayList<>();
		List<CompraParaLider> compraNumero = new ArrayList<>();
		List<CompraParaLider> compraNOnumero = new ArrayList<>();
		
		for(CompraParaLider compra: compras)
		{
			if(this.isNumeric(compra.getPagina()))
			{
				compraNumero.add(compra);
			}
			else 
			{
				compraNOnumero.add(compra);
			}
		}
		
		while(compraNumero.size()!=0)
		{
			CompraParaLider newCom = compraNumero.get(0);
			for(CompraParaLider compra : compraNumero)
			{
				if(Integer.parseInt(compra.getPagina())<Integer.parseInt(newCom.getPagina()))
				{
					newCom = compra;
				}
			}
			compraReturn.add(newCom);
			compraNumero.remove(newCom);
		}
		
		for(CompraParaLider compra : compraNOnumero) 
		{
			compraReturn.add(compra);
		}
		
		return compraReturn;
	}
	
	private boolean isNumeric(String cadena) 
	{
		boolean resultado;
	    try 
	    {
	    	Integer.parseInt(cadena);
	        resultado = true;
	    }
	    catch (NumberFormatException excepcion) 
	    {
	    	resultado = false;
	    }
	    return resultado;
	}
	
	public void openDocument()
	{
		this.document.open();
	}
	
	public void closeDocument()
	{
		this.document.close();
	}
	
}