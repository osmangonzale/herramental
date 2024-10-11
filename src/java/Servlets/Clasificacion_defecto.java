/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import controladores.ClasificacionDefectoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Clasificacion_defecto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
            HttpSession sesion = request.getSession();
            int opc = Integer.parseInt(request.getParameter("opc"));
            Boolean resultado = false;
            String Rol_Usuario = sesion.getAttribute("Rol").toString();
            String nombre_Usuario = sesion.getAttribute("Nombre").toString();
            ClasificacionDefectoJpaController jpa_clasificacionD = new ClasificacionDefectoJpaController();
            String filtro = "", clasificacion = "", descripcion = "", convecion = "";
            int id_defecto = 0, estado = 0;
            switch (opc) {
                case 1:
                    filtro = request.getParameter("txt_bus");
                    id_defecto = Integer.parseInt(request.getParameter("idCD"));
                    if (!filtro.equals("")) {
                        request.setAttribute("consulta_clasificacion", jpa_clasificacionD.ConsultaClasificacionFiltro(filtro));
                    } else {
                        request.setAttribute("consulta_clasificacion", jpa_clasificacionD.ConsultaClasificacion());
                    }
                    request.setAttribute("filtro", filtro);
                    request.setAttribute("id_Cdefecto", id_defecto);
                    request.getRequestDispatcher("Clasificacion_defecto.jsp").forward(request, response);
                    break;
                case 2:
                    clasificacion = request.getParameter("txt_clasificacion");
                    descripcion = request.getParameter("txt_descripcion");
                    convecion = request.getParameter("txt_convencion");
                    resultado = jpa_clasificacionD.RegistroClasificacion(clasificacion, descripcion, convecion, nombre_Usuario);
                    if (resultado) {
                        request.setAttribute("Registro_ClasificacionD", resultado);
                    } else {
                        request.setAttribute("Registro_ClasificacionD", resultado);
                    }
                    request.getRequestDispatcher("Clasificacion_defecto?opc=1&idCD=" + 0 + "&txt_bus=").forward(request, response);
                    break;
                case 3:
                    id_defecto = Integer.parseInt(request.getParameter("idCD"));
                    clasificacion = request.getParameter("txt_clasificacionM");
                    convecion = request.getParameter("txt_convencionM");
                    descripcion = request.getParameter("txt_descripcion");
                    filtro = request.getParameter("txt_bus");
                    resultado = jpa_clasificacionD.ModificarClasificacion(id_defecto, clasificacion, convecion, descripcion);
                    if (resultado) {
                        request.setAttribute("Modificar_ClasificacionD", resultado);
                    } else {
                        request.setAttribute("Modificar_ClasificacionD", resultado);
                    }
                    request.getRequestDispatcher("Clasificacion_defecto?opc=1&idCD=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 4:
                    id_defecto = Integer.parseInt(request.getParameter("idCD"));
                    estado = Integer.parseInt(request.getParameter("est"));
                    filtro = request.getParameter("txt_bus");
                    resultado = jpa_clasificacionD.ModificarEstadoClasificacion(id_defecto, estado);
                    if (resultado) {
                        request.setAttribute("Estado_ClasificacionD", resultado);
                    } else {
                        request.setAttribute("Estado_ClasificacionD", resultado);
                    }
                    request.setAttribute("Estado", estado);
                    request.getRequestDispatcher("Clasificacion_defecto?opc=1&idCD=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("menu.jsp").forward(request, response);
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
