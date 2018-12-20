package modelo;

import java.util.List;

import dto.Campa�aDTO;
import persistencia.dao.mysql.Campa�aDAOMYSQL;

public class GestorCampa�a 
{
	
	private static GestorCampa�a instance;
	private Campa�aDAOMYSQL campa�aDaoSQL;
	
	private GestorCampa�a() 
	{
		this.campa�aDaoSQL = Campa�aDAOMYSQL.getInstance();
	}

	public static GestorCampa�a getInstance() 
	{
		if ( instance == null )
			instance = new GestorCampa�a();
		return instance;
	}
	
	public void insert(Campa�aDTO campa�a) 
	{
		this.campa�aDaoSQL.insert(campa�a);
	}
	
	public void delete(Campa�aDTO campa�a)
	{
		this.campa�aDaoSQL.delete(campa�a);
	}
	
	public void update(Campa�aDTO campa�a)
	{
		this.campa�aDaoSQL.update(campa�a);
	}
	
	public List<Campa�aDTO> readAll()
	{
		return this.campa�aDaoSQL.readAll();
	}
	
	public Campa�aDTO readForId(int idCampa�a)
	{
		return this.campa�aDaoSQL.readForId(idCampa�a);
	}
	
}