
package cl.model;

import javax.ejb.Local;
import java.util.*;
/**
 *
 * @author roman
 */
@Local
public interface ServicioLocal {
    void add(Producto p);
    List<Producto> getLista();
    
    Producto buscarProducto(int id);
    void eliminarProducto(int id);
    void editarPrecio(int id, int precio);
    
    Persona iniciarSesion(String rut, String clave);
    String cambiarClave(String rut, String actual, String nueva);
}
