/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.model;

import java.util.List;
import javax.ejb.Singleton;
import java.util.*;

/**
 *
 * @author roman
 */
@Singleton
public class Servicio implements ServicioLocal {

    private List<Persona> listap;
    private List<Producto> lista;

    public Servicio() {
        lista = new ArrayList();
        lista.add(new Producto(1, "Sprite 1L", 1000));
        lista.add(new Producto(2, "Fanta 1L", 1000));
        listap = new ArrayList();
        listap.add(new Persona("1-1", "Roman", "123"));
        listap.add(new Persona("1-2", "Tomas", "123"));
        listap.add(new Persona("1-3", "Francisca", "123"));
        listap.add(new Persona("1-4", "Fany", "123"));
    }

    @Override
    public void add(Producto p) {
        lista.add(p);
    }

    @Override
    public List<Producto> getLista() {
        return lista;
    }

    @Override
    public Producto buscarProducto(int id) {
        for (Producto p : lista) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void eliminarProducto(int id) {
        Producto p = buscarProducto(id);
        if (p != null) {
            lista.remove(p);
        }
    }

    @Override
    public void editarPrecio(int id, int precio) {
        Producto p = buscarProducto(id);
        if (p != null) {
            p.setPrecio(precio);
        }
    }

    @Override
    public Persona iniciarSesion(String rut, String clave) {
        for (Persona p : listap) {
            if (rut.equals(p.getRut()) && clave.equals(p.getClave())) {
                return p;
            }
        }
        return null;
    }

    @Override
    public String cambiarClave(String rut, String actual, String nueva) {
        for (Persona p : listap) {
            if (p.getRut().equals(rut)) {
                if (p.getClave().equals(actual)) {
                    p.setClave(nueva);
                    return "Clave actualizada";
                }else{
                    return "clave incorrecta";
                }
            }
        }
        return "";
    }

}
