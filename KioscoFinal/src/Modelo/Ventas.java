package Modelo;

import java.time.LocalDate;
import java.util.Vector;

public abstract class Ventas {
	
	protected int numero;
	protected LocalDate fecha;
	protected float total;
	protected Vector<ItemVenta> items;
	protected boolean abierta;
	protected static int proximoNumero;
	
	public static int proximoNumero() {
		return ++proximoNumero;
	}

	public Ventas() {
		fecha = LocalDate.now();
		items = new Vector<ItemVenta>();
		numero = proximoNumero();
		abierta = true;
	}
	
	public boolean getEstado() {
		return abierta;
	}
	
	public float getTotal() {
		calcularTotal();
		return total;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public void agregarItems(int codProd, int cantidad) {
		for(ItemVenta iv: items) {
			if(Integer.parseInt(iv.getInfoProductos().elementAt(0)) == codProd) {
				iv.setCantidad(cantidad);
				return;
			}
		}
			ItemVenta iv = new ItemVenta(cantidad, codProd);
			iv.descontarStock();
			items.add(iv);			
		}
	
	public boolean modificarItems(long codProd, int cantidad) {
		for(ItemVenta iv: items) {
			if(Integer.parseInt(iv.getInfoProductos().elementAt(0)) == codProd) {
				iv.devolverStock();
				items.remove(iv);
				return true;
				}
			}
		return false;
	}
	
	public void calcularTotal() {
		float sum = 0;
		
		for(ItemVenta item : items) {
			sum += item.calcularSubTotal();
		}
		
		total = sum;
	}
	
	public boolean sosLaVenta(int codigo) {
		return(codigo == numero);
	}
	
	public void cerrarVenta() {
		abierta = false;
		calcularTotal();
		
	}
	
	public void cancelarVenta() {
		proximoNumero--;
		for(ItemVenta iv: items) {
			iv.devolverStock();		
			}
	}
	
	public Vector<Vector<String>> getDatosInfoItems() {
		Vector<Vector<String>> data = new Vector<Vector<String>>();
		for(ItemVenta iv: items) {
			Vector<String> renglon = iv.getInfoProductos();
			renglon.remove(4);
			renglon.remove(3);
			renglon.add(iv.getCantidad()+"");
			data.add(renglon);
		}
		return data;
	}
	
	public abstract Vector<String> getDatosVenta();
	
}
