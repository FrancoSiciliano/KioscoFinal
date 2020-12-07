package Controlador;

import java.util.Vector;
import Modelo.Producto;
import Vista.ProductoView;

public class AdministradorProducto {
    
    private Vector<Producto> productos;
    private static AdministradorProducto instancia;

    private AdministradorProducto(){
        
        productos = new Vector<Producto>();

    }

    public static AdministradorProducto getAdministradorProducto(){
        
        if (instancia == null){
            instancia = new AdministradorProducto();
        }
        return instancia;

    }
    
    public Vector<String> getCodigos(){
    	Vector<String> codigos = new Vector<String>();
    	
    	for(Producto prod: productos) {
    		if(prod.getEstado()) {
    			String cod = Long.toString(prod.getCodigoBarra());
    			codigos.add(cod);
    		}
    	}
    	return codigos;
    }

    public boolean crearProducto(long CodigoBarra, String desc, float precio, int stock, int stockMinimo){
    	
    	Producto p = buscarProducto(CodigoBarra);
    	
        if (p == null) {
        	Producto prod = new Producto(CodigoBarra, desc, precio, stock, stockMinimo);

            productos.add(prod);

            return true;
        }
        else {
        	return false;
        }

    }

    public Producto buscarProducto(long codigoBarra){
        for(Producto p: productos){
            if (p.sosElProducto(codigoBarra) && p.getEstado()){
                return p;
            }
        }
        return null;
    }

    public boolean modificarProducto(long codigoBarra,String descripcion, float precio, int stock, int stockMinimo){

        Producto prod = buscarProducto(codigoBarra);

        if(prod != null){
            prod.setPrecio(precio);
            prod.setStock(stock);
            prod.setStockMinimo(stockMinimo);
            prod.setDescripcion(descripcion);

            return true;
        }
        
        return false;

    }

    public boolean eliminarProducto(long codigoBarra){
        
        Producto prod = buscarProducto(codigoBarra);

        if(prod != null){
            prod.eliminarProducto();
            return true;
        }
        
        return false;
    }

    public Vector<Vector<String>> getDatosListadoStockMinimo(){
    	
    	Vector<Vector<String>> datos = new Vector<Vector<String>>();
    	
    	for(Producto p: productos) {
    		if (p.tenesStockMin() && p.getEstado()) {
    			datos.add(p.getDatosProducto());
    		}
    	}
    
        return datos;
    }
    
    public ProductoView buscarProductoView(long codigoBarra) {
    	Producto p = buscarProducto(codigoBarra);
    	return p.getView();
    }

    public Vector<Vector<String>> datosInventario(){
    	Vector<Vector<String>> datos = new Vector<Vector<String>>();
    	for(Producto p: productos) {
    		if(p.getEstado()) {
    			datos.add(p.getDatosProducto());    			
    		}
    	}
    	
    	return datos;
    }
}
