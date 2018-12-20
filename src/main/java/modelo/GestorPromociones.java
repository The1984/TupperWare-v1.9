package modelo;

import java.util.List;

import dto.CampañaDTO;
import dto.ProductoDTO;
import dto.PromocionDTO;
import dto.PromocionProductoDTO;
import persistencia.dao.mysql.PromocionDAOMYSQL;
import persistencia.dao.mysql.PromocionProductoDAOMYSQL;

public class GestorPromociones 
{
	
	private static GestorPromociones instance;
	private PromocionDAOMYSQL promocionDaoSQL;
	private PromocionProductoDAOMYSQL promocioncionProductoDaoSQL;
	
	private GestorPromociones() 
	{
		this.promocionDaoSQL = PromocionDAOMYSQL.getInstance();
		this.promocioncionProductoDaoSQL = PromocionProductoDAOMYSQL.getInstance();
	}

	public static GestorPromociones getInstance() 
	{
		if ( instance == null )
			instance = new GestorPromociones();
		return instance;
	}

	public void insert(PromocionDTO promocion) 
	{
		this.promocionDaoSQL.insert(promocion);
	}
	
	public void delete(PromocionDTO promocion)
	{
		this.promocionDaoSQL.delete(promocion);
	}
	
	public void update(PromocionDTO promocion)
	{
		this.promocionDaoSQL.update(promocion);
	}

	public List<PromocionDTO> readForCampaña(CampañaDTO campaña) 
	{
		return this.promocionDaoSQL.readForCampaña(campaña);
	}
	
	public PromocionDTO readForId(int idPromocion)
	{
		return this.promocionDaoSQL.readForId(idPromocion);
	}
	
	public PromocionDTO readForCampañaTitulo(int idCampaña, String titulo)
	{
		return this.promocionDaoSQL.readForCampañaTitulo(idCampaña, titulo);
	}
	
	public void insert(PromocionProductoDTO promocionProducto)
	{
		this.promocioncionProductoDaoSQL.insert(promocionProducto);
	}
	
	public void delete(int promocion, int producto)
	{
		this.promocioncionProductoDaoSQL.delete(promocion, producto);
	}
	
	public List<ProductoDTO> readProductos(int idPromocion)
	{
		return this.promocioncionProductoDaoSQL.readProductos(idPromocion);
	}
	
}