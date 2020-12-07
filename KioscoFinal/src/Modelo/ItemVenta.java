package Modelo;

import java.util.Vector;

import Controlador.AdministradorProducto;

public class ItemVenta {
	
	private int cantidad;
	private Producto producto;

	public ItemVenta(int cantidad, int codigoProd) {
		this.producto = AdministradorProducto.getAdministradorProducto().buscarProducto(codigoProd);
		this.cantidad = cantidad;
	}
	
	public float calcularSubTotal() {
		return cantidad * producto.getPrecio();
	}
	
	public void descontarStock() {
		producto.descontarStock(cantidad);
	}
	
	public void devolverStock() {
		producto.devolverStock(cantidad);
	}
	
	public Producto getProducto() {
		return producto;
	}
	
	public Vector<String> getInfoProductos(){
		return producto.getDatosProducto();
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public void setCantidad(int cantidad) {
		producto.descontarStock(cantidad);
		this.cantidad += cantidad;
	}
	
	

}
