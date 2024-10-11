package Servlets;

import controladores.DefectoJpaController;
import controladores.FamiliaProductoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Consulta_catologo extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        FamiliaProductoJpaController jpa_familiaP = new FamiliaProductoJpaController();
        DefectoJpaController jpa_defecto = new DefectoJpaController();
        try {
            int opc = Integer.parseInt(request.getParameter("opc"));
            int estado = 0, id_familiaP = 0, id_tipo = 0, id_defecto = 0, filtroND = 0;
            String filtro = "", filtro2 = "", nombre = "", fechaI = "", fechaF = "";
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
                    request.setAttribute("nuevos_defectos", jpa_defecto.ConsultaDefectosNuevosMes());
                    request.setAttribute("filtro", filtro);
                    request.setAttribute("id_familiaP", id_familiaP);
                    request.setAttribute("Consulta_Catalogo", "familias");
                    request.setAttribute("nuevos_defectos", filtroND);
                    request.getRequestDispatcher("Consulta_Catalogo.jsp").forward(request, response);
                    break;
                case 2:
                    filtro = request.getParameter("txt_bus");
                    id_defecto = Integer.parseInt(request.getParameter("idD"));
                    id_familiaP = Integer.parseInt(request.getParameter("idF"));
                    if (!filtro.equals("")) {
                        request.setAttribute("consulta_defectos", jpa_defecto.ConsultaDefectoFiltro(filtro, id_familiaP));
                    } else {
                        request.setAttribute("consulta_defectos", jpa_defecto.ConsultaDefectos(id_familiaP));
                    }
                    request.setAttribute("filtro", filtro);
                    request.setAttribute("id_defecto", id_defecto);
                    request.setAttribute("id_familiaP", id_familiaP);
                    request.setAttribute("Consulta_Catalogo", "defectos");
                    request.getRequestDispatcher("Consulta_Catalogo.jsp").forward(request, response);
                    break;
                case 3:
                    filtro = request.getParameter("txt_bus");
                    if (!filtro.equals("")) {
                        request.setAttribute("consulta_familiaP", jpa_familiaP.ConsultaFamiliaProductoFiltro(filtro));
                    } else {
                        request.setAttribute("consulta_familiaP", jpa_familiaP.ConsultaFamiliaProducto());
                    }
                    filtro2 = request.getParameter("txt_filtro");
                    try {
                        fechaI = request.getParameter("txt_fechaID");
                    } catch (Exception e) {
                        fechaI = "";
                    }
                    try {
                        fechaF = request.getParameter("txt_fechaFD");
                    } catch (Exception e) {
                        fechaF = "";
                    }
                    request.setAttribute("fecha_inicio", fechaI);
                    request.setAttribute("fecha_fin", fechaF);
                    request.setAttribute("id_familiaP", id_familiaP);
                    request.setAttribute("filtro", filtro);
                    request.setAttribute("filtro2", filtro2);
                    request.setAttribute("Consulta_Catalogo", "defectos_filtro");
                    request.getRequestDispatcher("Consulta_Catalogo.jsp").forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("salir.jsp").forward(request, response);
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
