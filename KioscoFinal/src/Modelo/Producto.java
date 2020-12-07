package Modelo;

import java.util.Vector;

import Vista.ProductoView;

public class Producto {
	private long CodigoBarra;
	private String descripcion;
	private float precio;
	private int stock;
	private int stockMinimo;
	private boolean activo;

	public Producto(long CodigoBarra, String descripcion, float precio, int stock, int stockMinimo) {
		this.CodigoBarra = CodigoBarra;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.stockMinimo = stockMinimo;
		activo = true;
	}
	
	public boolean sosElProducto (long codigo) {
		return (codigo == CodigoBarra);
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
	
	public void setPrecio (float prec) {
		this.precio = prec;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void setStockMinimo(int stockMinimo) {
		this.stockMinimo = stockMinimo;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public boolean tenesStockMin() {
		return (stock <= stockMinimo);
	}
	
	public void eliminarProducto() {
		activo = false;
	}
	
	public void descontarStock(int cantidad){
		if (stock - cantidad >= 0) {
			stock -= cantidad;			
		}else {
			throw new NullPointerException();
		}
	}
	
	public void devolverStock(int cantidad) {
		this.stock += cantidad;
	}
	
	public ProductoView getView()
	{
		return new ProductoView(CodigoBarra,descripcion,precio,stock,stockMinimo,activo);
	}
	
	public Vector<String> getDatosProducto(){
		Vector<String> datos = new Vector<String>();
		datos.add(CodigoBarra + "");
		datos.add(descripcion);
		datos.add(precio+"");
		datos.add(stock+"");
		datos.add(stockMinimo + "");
		
		return datos;
	}

}
