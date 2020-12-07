package Vista;

import java.time.LocalDate;
import java.util.Vector;

public abstract class VentaView {
	
	protected int numero;
	protected LocalDate fecha;
	protected float total;
	protected boolean abierta;
	protected Vector<Vector<String>> infoItemsVentas;
	
	public VentaView(LocalDate fecha, float total, int numero, boolean abierta,  Vector<Vector<String>> infoItems) {
		this.fecha = fecha;
		this.total = total;
		this.numero = numero;
		this.abierta = abierta;
		this.infoItemsVentas = infoItems;
		
	}
	
	public boolean getEstado() {
		return abierta;
	}
	
	public float getTotal() {
		return total;
	}
	
	public int getNumero() {
		return numero;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public abstract Vector<Vector<String>> getInfoItemsVenta();
	
	
	
	
}
