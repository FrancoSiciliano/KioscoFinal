package Controlador;

import Vista.cajaView;
import java.util.Vector;
import Modelo.Caja;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdministradorCaja {

    private Vector<Caja> cajas;
    private static AdministradorCaja instancia;

    private AdministradorCaja(){
        cajas = new Vector<Caja>();
    }

    public static AdministradorCaja getAdministradorCaja(){

        if (instancia == null){
            instancia = new AdministradorCaja();
        }

        return instancia;

    }
    
    public boolean cajaAbierta() {
    	return buscarCaja(LocalDate.now()) != null;
    }

    public boolean abrirCaja(float saldo){
    	
        Caja c = buscarCaja(LocalDate.now());
    	
        if (c == null) {
    		Caja caja = new Caja(saldo);
    		cajas.add(caja);    		
    		return true;
    	}
    	else {
    		return false;
    	}
    
    
    }

    private Caja buscarCaja(LocalDate fecha) {

        for (Caja c: cajas){
            if (c.sosLaCaja(fecha) && c.getAbierta())
                return c;
        }   

        return null;
    }

    public void cerrarCaja() throws NullPointerException {

        LocalDate fecha = LocalDate.now();
        
        Caja caja = buscarCaja(fecha);
        
        if (caja.getAbierta()) {
        	caja.cerrarCaja();
        	
        } else {
        	throw new NullPointerException();
        }
        

    }
    
    public void agregarVentaContado(int venta){
    	
    	LocalDate fecha = LocalDate.now();
    	
    	Caja caja = buscarCaja(fecha);

    	caja.agregarVentaContado(AdministradorVentas.getAdministradorVentas().buscarVentaContado(venta));
    	
    }
    
    public void agregarVentaCredito(int venta){
    	LocalDate fecha = LocalDate.now();
    	
    	Caja caja = buscarCaja(fecha);
    	
    	caja.agregarVentaCredito(AdministradorVentas.getAdministradorVentas().buscarVentaCredito(venta));
    }
    
    public void agregarVentaDebito(int venta){
    	LocalDate fecha = LocalDate.now();
    	
    	Caja caja = buscarCaja(fecha);
    	
    	caja.agregarVentaDebito(AdministradorVentas.getAdministradorVentas().buscarVentaDebito(venta));
    }

    public Vector<Vector<String>> generarListadoVentasDiariasContado(LocalDate fecha){

        Vector<Vector<String>> listado = new Vector<Vector<String>>();
        
        Caja c = buscarCaja(fecha);
        
        c.getDatosVentaContado(listado);
        
        if (!listado.isEmpty()) {
        	
        	float suma = 0;
        	for(Vector<String> s: listado) {
        		suma += Float.parseFloat(s.elementAt(2));
        	}
        
        	Vector<String> ultLinea = new Vector<String>();
        	ultLinea.add("Total");
        	ultLinea.add("");
        	ultLinea.add(suma + "");
        	
        	listado.add(ultLinea);
        }
        return listado;
    }
    
    public Vector<Vector<String>> generarListadoVentasDiariasDebito(LocalDate fecha){
    	 Vector<Vector<String>> listado = new Vector<Vector<String>>();
         Caja c = buscarCaja(fecha);
         
         c.getDatosVentaDebito(listado);
         
         if (!listado.isEmpty()) {
         	
         	float suma = 0;
         	for(Vector<String> s: listado) {
         		suma += Float.parseFloat(s.elementAt(4));
         	}
         
         	Vector<String> ultLinea = new Vector<String>();
         	ultLinea.add("Total");
         	ultLinea.add("");
         	ultLinea.add("");
         	ultLinea.add("");
         	ultLinea.add(suma + "");
         	
         	listado.add(ultLinea);
         }
        	 
        	 return listado;        	 
    }
    
    public Vector<Vector<String>> generarListadoVentasDiariasCredito(LocalDate fecha){
    	 Vector<Vector<String>> listado = new Vector<Vector<String>>();
         Caja c = buscarCaja(fecha);
         
         c.getDatosVentaCredito(listado);
         
         if (!listado.isEmpty()) {
         	
         	float suma = 0;
         	for(Vector<String> s: listado) {
         		suma += Float.parseFloat(s.elementAt(3));
         	}
         
         	Vector<String> ultLinea = new Vector<String>();
         	ultLinea.add("Total");
         	ultLinea.add("");
         	ultLinea.add("");
         	ultLinea.add(suma + "");
         	
         	listado.add(ultLinea);
         }
         
         return listado;
         
    }
    
    public Vector<Vector<String>> ventasTotales(LocalDate fechaInicio, LocalDate fechaFin) {
    	Vector<Vector<String>> listado = new Vector<Vector<String>>();
    	for(Caja c: cajas) {
    		if((c.getFecha().isAfter(fechaInicio) && c.getFecha().isBefore(fechaFin)) || c.getFecha().equals(fechaInicio) || c.getFecha().equals(fechaFin)){
    			Vector<String> renglon = new Vector<String>();
    			renglon.add(c.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    			renglon.add(""+c.cantidadVentas());
    			renglon.add(""+c.totalVentas());
    			listado.add(renglon);
    		}
    	}
    	return listado;
    }
    
    public cajaView buscarCajaView(LocalDate fecha) {
    	Caja c = buscarCaja(fecha);
    	return c.getView();
    	
    	
    }
}
