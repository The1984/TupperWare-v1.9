package dto;

import java.util.Date;
import java.util.List;

public class Campa�aDTO 
{

	private int idCampa�a;
	private String numero;
	private String a�o;
	private Date cierre;
	private List<CompraDTO> compras;
	
	public int getIdCampa�a() 
	{
		return idCampa�a;
	}
	
	public void setIdCampa�a(int idCampa�a) 
	{
		this.idCampa�a = idCampa�a;
	}
	
	public String getNumero() 
	{
		return numero;
	}
	
	public void setNumero(String numero) 
	{
		this.numero = numero;
	}
	
	public String getA�o() 
	{
		return a�o;
	}
	
	public void setA�o(String a�o) 
	{
		this.a�o = a�o;
	}
	
	public Date getCierre() 
	{
		return cierre;
	}
	
	public void setCierre(Date cierre) 
	{
		this.cierre = cierre;
	}
	
	public List<CompraDTO> getCompras() 
	{
		return compras;
	}

	public void setCompras(List<CompraDTO> compras) 
	{
		this.compras = compras;
	}

}
