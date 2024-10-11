package Servlets;

import controladores.TipoMaquinaJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Tipo_maquina extends HttpServlet {

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
            TipoMaquinaJpaController jpa_tipoM = new TipoMaquinaJpaController();
            String filtro = "";
            String tipo = "";
            int id_TipoM = 0, estado = 0, id_area = 0;
            switch (opc) {
                case 1:
                    filtro = request.getParameter("txt_bus");
                    id_TipoM = Integer.parseInt(request.getParameter("idT"));
                    if (!filtro.equals("")) {
                        request.setAttribute("consulta_TipoM", jpa_tipoM.ConsultaTipoMaquinasFiltro(filtro));
                    } else {
                        request.setAttribute("consulta_TipoM", jpa_tipoM.ConsultaTipoMaquinas());
                    }
                    request.setAttribute("filtro", filtro);
                    request.setAttribute("id_tipoM", id_TipoM);
                    request.getRequestDispatcher("tipo_maquina.jsp").forward(request, response);
                    break;
                case 2:
                    tipo = request.getParameter("txt_tipoM");
                    id_area = Integer.parseInt(request.getParameter("lsarea").toString());
                    resultado = jpa_tipoM.RegistroTipoMaquina(tipo, id_area, nombre_Usuario);
                    if (resultado) {
                        request.setAttribute("registro_tipoM", resultado);
                    } else {
                        request.setAttribute("registro_tipoM", resultado);
                    }
                    request.getRequestDispatcher("Tipo_maquina?opc=1&idT=" + 0 + "&txt_bus=").forward(request, response);
                    break;
                case 3:
                    id_TipoM = Integer.parseInt(request.getParameter("idT"));
                    filtro = request.getParameter("txt_bus");
                    tipo = request.getParameter("txt_tipoMM");
                    id_area = Integer.parseInt(request.getParameter("lsareaM").toString());
                    resultado = jpa_tipoM.ModificarTipoMaquina(id_TipoM, tipo, id_area);
                    if (resultado) {
                        request.setAttribute("Modificar_tipoM", resultado);
                    } else {
                        request.setAttribute("Modificar_tipoM", resultado);
                    }
                    request.getRequestDispatcher("Tipo_maquina?opc=1&idT=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 4:
                    id_TipoM = Integer.parseInt(request.getParameter("idT"));
                    filtro = request.getParameter("txt_bus");
                    estado = Integer.parseInt(request.getParameter("est"));
                    resultado = jpa_tipoM.ModificarEstadoTipoMaquina(id_TipoM, estado);
                    if (resultado) {
                        request.setAttribute("estado_tipoM", resultado);
                    } else {
                        request.setAttribute("estado_tipoM", resultado);
                    }
                    request.setAttribute("estado", estado);
                    request.getRequestDispatcher("Tipo_maquina?opc=1&idT=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
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
