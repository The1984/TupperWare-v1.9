package modelo;

import java.util.List;

import dto.CampañaDTO;
import persistencia.dao.mysql.CampañaDAOMYSQL;

public class GestorCampaña 
{
	
	private static GestorCampaña instance;
	private CampañaDAOMYSQL campañaDaoSQL;
	
	private GestorCampaña() 
	{
		this.campañaDaoSQL = CampañaDAOMYSQL.getInstance();
	}

	public static GestorCampaña getInstance() 
	{
		if ( instance == null )
			instance = new GestorCampaña();
		return instance;
	}
	
	public void insert(CampañaDTO campaña) 
	{
		this.campañaDaoSQL.insert(campaña);
	}
	
	public void delete(CampañaDTO campaña)
	{
		this.campañaDaoSQL.delete(campaña);
	}
	
	public void update(CampañaDTO campaña)
	{
		this.campañaDaoSQL.update(campaña);
	}
	
	public List<CampañaDTO> readAll()
	{
		return this.campañaDaoSQL.readAll();
	}
	
	public CampañaDTO readForId(int idCampaña)
	{
		return this.campañaDaoSQL.readForId(idCampaña);
	}
	
}