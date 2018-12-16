package dto;

public class CompraDTO 
{

	private int idCompra;
	private String pagina;
	private int precio;
	private int unidades;
	private int montoPagado;
	private int porcentajeDeGanancia;
	private ClienteDTO cliente;
	private ProductoDTO producto;
	private CompraPromocionDTO compraPromocion;
	private CampañaDTO campaña;
	private EstadoDeCompraDTO estadoDeCompra;

	public int getIdCompra() 
	{
		return idCompra;
	}

	public void setIdCompra(int idCompra) 
	{
		this.idCompra = idCompra;
	}
	
	public String getPagina() 
	{
		return pagina;
	}

	public void setPagina(String pagina) 
	{
		this.pagina = pagina;
	}

	public int getPrecio() 
	{
		return precio;
	}

	public void setPrecio(int precio) 
	{
		this.precio = precio;
	}

	public int getUnidades() 
	{
		return unidades;
	}

	public void setUnidades(int unidades) 
	{
		this.unidades = unidades;
	}

	public int getPorcentajeDeGanancia() 
	{
		return porcentajeDeGanancia;
	}

	public void setPorcentajeDeGanancia(int porcentajeDeGanancia) 
	{
		this.porcentajeDeGanancia = porcentajeDeGanancia;
	}
	
	public ClienteDTO getCliente() 
	{
		return cliente;
	}
	
	public void setCliente(ClienteDTO cliente) 
	{
		this.cliente = cliente;
	}

	public int getMontoPagado() 
	{
		return montoPagado;
	}

	public void setMontoPagado(int montoPagado) 
	{
		this.montoPagado = montoPagado;
	}

	public ProductoDTO getProducto() 
	{
		return producto;
	}

	public void setProducto(ProductoDTO producto) 
	{
		this.producto = producto;
	}

	public CompraPromocionDTO getCompraPromocion() 
	{
		return compraPromocion;
	}

	public void setCompraPromocion(CompraPromocionDTO compraPromocion) 
	{
		this.compraPromocion = compraPromocion;
	}

	public CampañaDTO getCampaña() 
	{
		return campaña;
	}

	public void setCampaña(CampañaDTO campaña) 
	{
		this.campaña = campaña;
	}

	public EstadoDeCompraDTO getEstadoDeCompra() 
	{
		return estadoDeCompra;
	}

	public void setEstadoDeCompra(EstadoDeCompraDTO estadoDeCompra) 
	{
		this.estadoDeCompra = estadoDeCompra;
	}
	
}