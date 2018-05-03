package cl.controller;

import cl.model.Persona;
import cl.model.Producto;
import cl.model.ServicioLocal;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author roman
 */
@WebServlet(name = "Controlador", urlPatterns = {"/control.do"})
public class Controlador extends HttpServlet {

    @EJB
    private ServicioLocal servicio;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bt = request.getParameter("bt");
        switch (bt) {
            case "cambio":
                cambio(request, response);
                break;
            case "inicio":
                inicio(request, response);
                break;
            case "add":
                add(request, response);
                break;
            case "edit":
                edit(request, response);
                break;
            case "delete":
                delete(request, response);
                break;
            default:
                redirect(request, response);
        }

    }

    protected void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txt1 = request.getParameter("txt1");
        String txt2 = request.getParameter("txt2");
        int precio = 0;

        try {
            precio = Integer.parseInt(txt2);
            servicio.add(new Producto(servicio.getLista().size() + 1,
                    txt1, precio));

        } catch (Exception e) {

        }
        response.sendRedirect("index.jsp");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String txt1 = request.getParameter("txt1");
        int id = Integer.parseInt(txt1);
        servicio.eliminarProducto(id);
        response.sendRedirect("index.jsp");

    }

    protected void edit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String txt1 = request.getParameter("txt1");
        String txt3 = request.getParameter("txt3");
        int id = Integer.parseInt(txt1);
        int precio;
        try {
            precio = Integer.parseInt(txt3);
            servicio.editarPrecio(id, precio);
        } catch (Exception e) {
        }
        response.sendRedirect("index.jsp");

    }

    protected void redirect(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String bt = request.getParameter("bt");

        int id = Integer.parseInt(bt);
        Producto p = servicio.buscarProducto(id);

        request.setAttribute("producto", p);
        request.getRequestDispatcher("edit.jsp").forward(request, response);

    }

    protected void inicio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String rut = request.getParameter("x1");
        String clave = request.getParameter("x2");

        Persona p = servicio.iniciarSesion(rut, clave);

        if (p != null) {
            //CREAR UNA SESION
            request.getSession().setAttribute("person", p);
            //OPCIONAL:PERIODO DE INACTIVIDAD DE PERSON 5MIN
            request.getSession().setMaxInactiveInterval(5 * 60);
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("msg", "Rut no encontrado :(");
            request.getRequestDispatcher("inicio.jsp").forward(request, response);
        }

    }

    protected void cambio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String x1 = request.getParameter("x1");
        String x2 = request.getParameter("x2");
        String x3 = request.getParameter("x3");
        
        
        if (x2.equals(x3)) {
            //SACAR EL OBJETO DE LA SESION person
            Persona person=(Persona)request.getSession().getAttribute("person");
            if (person!=null) {
                String msg =servicio.cambiarClave(person.getRut(), x1, x3);
                request.setAttribute("msg", msg);
                request.getRequestDispatcher("cambio_clave.jsp").forward(request, response);
            }else{
                response.sendRedirect("inicio.jsp");
            }
        } else {
            request.setAttribute("msg", "las claves no coinciden");
            request.getRequestDispatcher("cambio_clave.jsp").forward(request, response);
        }
        
        
        
        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
