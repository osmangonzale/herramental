/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import controladores.FamiliaProductoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Familia_producto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
            HttpSession sesion = request.getSession();
            FamiliaProductoJpaController jpa_familiaP = new FamiliaProductoJpaController();
            int opc = Integer.parseInt(request.getParameter("opc"));
            String nombre_Usuario = sesion.getAttribute("Nombre").toString();
            Boolean resultado = false;
            int estado = 0, id_familiaP = 0, id_tipo = 0, filtroND = 0;
            String filtro = "", nombre = "";
            switch (opc) {
                case 1:
                    filtro = request.getParameter("txt_bus");
                    id_familiaP = Integer.parseInt(request.getParameter("idF"));
                    try {
                        filtroND = Integer.parseInt(request.getParameter("filtroND"));
                    } catch (Exception e) {
                        filtroND = 0;
                    }
                    if (!filtro.equals("")) {
                        request.setAttribute("consulta_familiaP", jpa_familiaP.ConsultaFamiliaProductoFiltro(filtro));
                    } else {
                        request.setAttribute("consulta_familiaP", jpa_familiaP.ConsultaFamiliaProducto());
                    }
                    request.setAttribute("filtro", filtro);
                    request.setAttribute("nuevos_defectos", filtroND);
                    request.setAttribute("id_familiaP", id_familiaP);
                    request.getRequestDispatcher("familia_producto.jsp").forward(request, response);
                    break;
                case 2:
                    nombre = request.getParameter("txt_nombre");
                    id_tipo = Integer.parseInt(request.getParameter("lst_tipoM"));
                    resultado = jpa_familiaP.RegistroFamiliaProducto(nombre, id_tipo, nombre_Usuario);
                    if (resultado) {
                        request.setAttribute("Registro_familiaP", resultado);
                    } else {
                        request.setAttribute("Registro_familiaP", resultado);
                    }
                    request.getRequestDispatcher("Familia_producto?opc=1&idF=" + 0 + "&txt_bus=").forward(request, response);
                    break;
                case 3:
                    nombre = request.getParameter("txt_nombreM");
                    id_familiaP = Integer.parseInt(request.getParameter("idF"));
                    id_tipo = Integer.parseInt(request.getParameter("lst_tipoM"));
                    filtro = request.getParameter("txt_bus");
                    resultado = jpa_familiaP.ModificarFamiliaProducto(id_familiaP, nombre, id_tipo);
                    if (resultado) {
                        request.setAttribute("Modificar_familiaP", resultado);
                    } else {
                        request.setAttribute("Modificar_familiaP", resultado);
                    }
                    request.getRequestDispatcher("Familia_producto?opc=1&idF=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 4:
                    id_familiaP = Integer.parseInt(request.getParameter("idF"));
                    filtro = request.getParameter("txt_bus");
                    estado = Integer.parseInt(request.getParameter("est"));
                    resultado = jpa_familiaP.ModificarFamiliaProductoEstado(id_familiaP, estado);
                    if (resultado) {
                        request.setAttribute("Estado_familiaP", resultado);
                    } else {
                        request.setAttribute("Estado_familiaP", resultado);
                    }
                    request.setAttribute("estado", estado);
                    request.getRequestDispatcher("Familia_producto?opc=1&idF=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
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
