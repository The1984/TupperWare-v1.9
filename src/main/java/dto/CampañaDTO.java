package dto;

import java.util.Date;
import java.util.List;

public class CampañaDTO 
{

	private int idCampaña;
	private String numero;
	private String año;
	private Date cierre;
	private List<CompraDTO> compras;
	
	public int getIdCampaña() 
	{
		return idCampaña;
	}
	
	public void setIdCampaña(int idCampaña) 
	{
		this.idCampaña = idCampaña;
	}
	
	public String getNumero() 
	{
		return numero;
	}
	
	public void setNumero(String numero) 
	{
		this.numero = numero;
	}
	
	public String getAño() 
	{
		return año;
	}
	
	public void setAño(String año) 
	{
		this.año = año;
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
