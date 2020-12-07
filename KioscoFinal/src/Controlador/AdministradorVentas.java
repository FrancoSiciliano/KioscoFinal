package Controlador;

import java.util.Vector;


import Modelo.VentaContado;
import Modelo.VentaTarjetaCredito;
import Modelo.VentaTarjetaDebito;

import Vista.VentaContadoView;
import Vista.VentaCreditoView;
import Vista.VentaDebitoView;

import java.time.LocalDate;

public class AdministradorVentas {
    
    private Vector<VentaContado> ventasContado;
    private Vector<VentaTarjetaCredito> ventasCredito;
    private Vector<VentaTarjetaDebito> ventasDebito;    
    private static AdministradorVentas instancia;

    private AdministradorVentas(){
        
        ventasContado = new Vector<VentaContado>();
        ventasCredito = new Vector<VentaTarjetaCredito>();
        ventasDebito = new Vector<VentaTarjetaDebito>();

    }
    
    public static AdministradorVentas getAdministradorVentas(){

        if (instancia == null) {
            instancia = new AdministradorVentas();
        }

        return instancia;
    }
    
    
    public int iniciarVentaContado(){
        VentaContado ventacont = new VentaContado();
        
        ventasContado.add(ventacont);

        return ventacont.getNumero();
    }

    public int iniciarVentaTarjetaDebito(String banco, String codigoAutorizacion){
        VentaTarjetaDebito ventadeb = new VentaTarjetaDebito(codigoAutorizacion, banco);
        
        ventasDebito.add(ventadeb);
        
        return ventadeb.getNumero();
    }

    public int iniciarVentaTarjetaCredito(int codSeguridad, String codAutorizacion){
        VentaTarjetaCredito ventacred = new VentaTarjetaCredito(codAutorizacion, codSeguridad);
        
        ventasCredito.add(ventacred);

        return ventacred.getNumero();
    }

    public void cerrarVentaContado(int numVenta){
    	
        VentaContado venta = buscarVentaContado(numVenta);
        
        AdministradorCaja caja = AdministradorCaja.getAdministradorCaja();
        
        venta.cerrarVenta();
        
        caja.agregarVentaContado(numVenta);

    }

    public void cerrarVentaCredito(int numVenta, String codAut, int codSeg){

        VentaTarjetaCredito venta = buscarVentaCredito(numVenta);
        
        venta.setCodigoAutorizacion(codAut);
        venta.setCodigoSeguridad(codSeg);
            
        AdministradorCaja caja = AdministradorCaja.getAdministradorCaja();
        
        venta.cerrarVenta();

        caja.agregarVentaCredito(numVenta);


    }

    public void cerrarVentaDebito(int numVenta, String codAut, String banco){
        
        VentaTarjetaDebito venta = buscarVentaDebito(numVenta);
        
        venta.setCodigoAutorizacion(codAut);
        venta.setBanco(banco);
        
        AdministradorCaja caja = AdministradorCaja.getAdministradorCaja();
        
        venta.cerrarVenta();

        caja.agregarVentaDebito(numVenta);
    }

    public VentaContado buscarVentaContado(int codVenta){
        
        for (VentaContado vc: ventasContado){
            if (vc.sosLaVenta(codVenta)){
                return vc;
            }
        }

        return null;
    }
    
    public VentaTarjetaDebito buscarVentaDebito(int codVenta){
       
        for (VentaTarjetaDebito vd: ventasDebito){
            if (vd.sosLaVenta(codVenta)){
                return vd;
            }
        }

        return null;
    }

    public VentaTarjetaCredito buscarVentaCredito(int codVenta){
        
        for (VentaTarjetaCredito vcred: ventasCredito){
            if (vcred.sosLaVenta(codVenta)){
                return vcred;
            }
        }

        return null;
    }

    public boolean agregarProductoVenta(int codVenta, int codBarra, int cant){
        
    	VentaTarjetaCredito ventacred = null;
       
        VentaTarjetaDebito ventadeb = null;

        VentaContado ventacont = buscarVentaContado(codVenta);
        
        if (ventacont == null){
            ventacred = buscarVentaCredito(codVenta);
            
            if (ventacred == null){
                ventadeb = buscarVentaDebito(codVenta);
            }
        }

        
        	if (ventacont != null){
        		ventacont.agregarItems(codBarra, cant);
        	}
        	else if (ventacred != null){
        		ventacred.agregarItems(codBarra,cant);
        	}
        	else if (ventadeb != null){
        		ventadeb.agregarItems(codBarra,cant);
        	}
        	
       return true;


    }
    
    public boolean quitarProducto(int codVenta, long codBarra, int cant){
    	
    	VentaTarjetaCredito ventacred = null;
        
        VentaTarjetaDebito ventadeb = null;

        VentaContado ventacont = buscarVentaContado(codVenta);
        
        if (ventacont == null){
            ventacred = buscarVentaCredito(codVenta);
            
            if (ventacred == null){
                ventadeb = buscarVentaDebito(codVenta);
            }
        }
        
        
        if (ventacont != null){
    		return ventacont.modificarItems(codBarra, cant);
    	}
    	else if (ventacred != null){
    		return ventacred.modificarItems(codBarra,cant);
    	}
    	else if (ventadeb != null){
    		
    		return ventadeb.modificarItems(codBarra,cant);
    	}
        
        return false;

    }

    public Vector<Vector<String>> generarListadoVentasMensualesTotalizado(LocalDate fechaInicio, LocalDate fechaFin){
    	Vector<Vector<String>> datos = AdministradorCaja.getAdministradorCaja().ventasTotales(fechaInicio, fechaFin);
    	return datos;
    }
    
    public Vector<Vector<String>> generarListadoVentasMensualesContado(LocalDate fechaInicio, LocalDate fechaFin){

        Vector<Vector<String>> listado = new Vector<Vector<String>>();
        
        for(VentaContado v: ventasContado){
        	if ((v.getFecha().isAfter(fechaInicio) && v.getFecha().isBefore(fechaFin)) || v.getFecha().equals(fechaInicio) || v.getFecha().equals(fechaFin)) {        		
        		listado.add(v.getDatosVenta());
        	}
        }
        
        
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
    
    public Vector<Vector<String>> generarListadoVentasMensualesDebito(LocalDate fechaInicio, LocalDate fechaFin){
    	 Vector<Vector<String>> listado = new Vector<Vector<String>>();
         
         for(VentaTarjetaDebito v: ventasDebito){
        	 if((v.getFecha().isAfter(fechaInicio) && v.getFecha().isBefore(fechaFin)) || v.getFecha().equals(fechaInicio) || v.getFecha().equals(fechaFin)) {        		 
        		 listado.add(v.getDatosVenta());
        	 }
         }
         
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
    
    public Vector<Vector<String>> generarListadoVentasMensualesCredito(LocalDate fechaInicio, LocalDate fechaFin){
    	 
    	Vector<Vector<String>> listado = new Vector<Vector<String>>();
      
         
         for(VentaTarjetaCredito v: ventasCredito){
        	 if((v.getFecha().isAfter(fechaInicio) && v.getFecha().isBefore(fechaFin)) || v.getFecha().equals(fechaInicio) || v.getFecha().equals(fechaFin)) {        		 
        		 listado.add(v.getDatosVenta());
        	 }
         }
         
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
    
    public void cancelarVentaContado() {
    	VentaContado v = ventasContado.elementAt(ventasContado.size()-1);
    	v.cancelarVenta();
    	ventasContado.remove(ventasContado.size()-1);
    }
    
    public void cancelarVentaDebito() {
    	VentaTarjetaDebito v = ventasDebito.elementAt(ventasDebito.size()-1);
    	v.cancelarVenta();
    	ventasDebito.remove(ventasDebito.size()-1);
    	
    }
    
    public void cancelarVentaCredito() {
    	VentaTarjetaCredito v = ventasCredito.elementAt(ventasCredito.size()-1);
    	v.cancelarVenta();    	
    	ventasCredito.remove(ventasCredito.size()-1);
    }
    
    public VentaContadoView buscarVentaContadoView(int codigo) {
    	VentaContado v = buscarVentaContado(codigo);
    	return v.getView(); 
    }
    
    public VentaDebitoView buscarVentaDebitoView(int codigo) {
    	VentaTarjetaDebito v = buscarVentaDebito(codigo);
    	return v.getView();
    }
    
    public VentaCreditoView buscarVentaCreditoView(int codigo) {
    	VentaTarjetaCredito v = buscarVentaCredito(codigo);
    	return v.getView();
    }
    
}