package modelo;

import java.util.List;

import dto.PremioDTO;
import persistencia.dao.mysql.PremioDAOMYSQL;

public class GestorPremios 
{

	private static GestorPremios instance;
	private PremioDAOMYSQL premioDaoSQL;
	
	private GestorPremios() 
	{
		this.premioDaoSQL = PremioDAOMYSQL.getInstance();
	}

	public static GestorPremios getInstance() 
	{
		if ( instance == null )
			instance = new GestorPremios();
		return instance;
	}

	public void insert(PremioDTO premio) 
	{
		this.premioDaoSQL.insert(premio);
	}
	
	public void delete(PremioDTO premio)
	{
		this.premioDaoSQL.delete(premio);
	}
	
	public void update(PremioDTO premio)
	{
		this.premioDaoSQL.update(premio);
	}

	public List<PremioDTO> readForIdCampaña(int idCampaña) 
	{
		return this.premioDaoSQL.readForIdCampaña(idCampaña);
	}
	
	public void readForId(int idPremio)
	{
		this.premioDaoSQL.readForId(idPremio);
	}
	
}