package Vista;

public class ProductoView {
	
	private long CodigoBarra;
	private String descripcion;
	private float precio;
	private int stock;
	private int stockMinimo;
	private boolean activo;

	public ProductoView(long CodigoBarra, String descripcion, float precio, int stock, int stockMinimo, boolean activo) 
	{
		this.CodigoBarra = CodigoBarra;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.stockMinimo = stockMinimo;
		activo = activo;
	}
	
	public long getCodigoBarra() {
		return CodigoBarra;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public float getPrecio() {
		return precio;
	}

	public boolean getEstado(){
		return activo;
	}
	
	public int getStock() {
		return stock;
	}
	
	public int getStockMinimo() {
		return stockMinimo;
	}
	
	

}
