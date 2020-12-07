package Vista;

import java.time.LocalDate;

public class cajaView {
	
	private float saldo;
	private LocalDate fecha;
	
	public cajaView(float saldo, LocalDate fecha) {
		this.saldo = saldo;
		this.fecha = fecha;
	}
	
	public float getSaldo() {
		return saldo;
	}
	
	public LocalDate getFecha() {
		return fecha;
	}
}
