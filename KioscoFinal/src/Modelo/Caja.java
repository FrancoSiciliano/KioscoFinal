package Modelo;

import java.time.LocalDate;
import java.util.Vector;

import Vista.cajaView;

public class Caja {

	private LocalDate fecha;
	private float saldo;
	private boolean abierta;
	private Vector<VentaContado> ventasContado;
	private Vector<VentaTarjetaCredito> ventasTarjetaCredito;
	private Vector<VentaTarjetaDebito> ventasTarjetaDebito;
	
	public Caja(float saldo) {
		ventasContado = new Vector<VentaContado>();
		ventasTarjetaCredito = new Vector<VentaTarjetaCredito>();
		ventasTarjetaDebito = new Vector <VentaTarjetaDebito>();
		fecha = LocalDate.now();
		this.saldo = saldo;
		abierta = true;
	}
	
	public void agregarSaldoCaja(float total) {
		this.saldo += total;
	}
	
	public boolean getAbierta() {
		return abierta;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
	
	public boolean sosLaCaja(LocalDate fecha) {
		
		return this.fecha.equals(fecha);
		
	}

	
	public void agregarVentaContado (VentaContado venta) {
		agregarSaldoCaja(venta.getTotal());
		ventasContado.add(venta);
	}
	
	public void agregarVentaCredito(VentaTarjetaCredito venta) {
		ventasTarjetaCredito.add(venta);
	}
	
	public void agregarVentaDebito(VentaTarjetaDebito venta) {
		ventasTarjetaDebito.add(venta);
	}
	
	public void getDatosVentaContado(Vector<Vector<String>> data) {
		for(VentaContado v: ventasContado){
        	data.add(v.getDatosVenta());
        }
	}
	
	public void getDatosVentaCredito(Vector<Vector<String>> data){
		for(VentaTarjetaCredito v: ventasTarjetaCredito){
			data.add(v.getDatosVenta());
		}
	}
	
	public void getDatosVentaDebito(Vector<Vector<String>> data){
		for(VentaTarjetaDebito v: ventasTarjetaDebito){       		
         	data.add(v.getDatosVenta());
         }
	}
	
	public void cerrarCaja() {
		abierta = false;
	}
	
	public int cantidadVentas() {
		return ventasContado.size() + ventasTarjetaCredito.size() + ventasTarjetaDebito.size();
	}
	
	public float totalVentas() {
		float suma = 0;
		for(VentaContado v: ventasContado) {
			suma += v.getTotal();
		}
		for(VentaTarjetaCredito v: ventasTarjetaCredito) {
			suma += v.getTotal();
		}
		for(VentaTarjetaDebito v: ventasTarjetaDebito) {
			suma += v.getTotal();
		}
		
		return suma;
	}
	
	public cajaView getView() {
		return new cajaView(saldo, fecha);
	}
	
	

}
