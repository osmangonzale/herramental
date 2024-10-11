package Servlets;

import Mail.Email;
import controladores.DefectoJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Defecto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
            HttpSession sesion = request.getSession();
            int opc = Integer.parseInt(request.getParameter("opc"));
            Boolean resultado = false;
            Email mail = new Email();
            String Rol_Usuario = sesion.getAttribute("Rol").toString();
            String nombre_Usuario = sesion.getAttribute("Nombre").toString();
            DefectoJpaController jpa_defecto = new DefectoJpaController();
            String filtro = "", defecto = "", descripcion = "", consecutivo = "";
            int id_defecto = 0, id_familiaP = 0, id_clasificacion = 0, estado = 0;
            List lst_defecto = null;
            switch (opc) {
                case 1:
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
                    request.getRequestDispatcher("defecto.jsp").forward(request, response);
                    break;
                case 2:
                    id_familiaP = Integer.parseInt(request.getParameter("idF"));
                    id_clasificacion = Integer.parseInt(request.getParameter("slc_clasificacion"));
                    defecto = request.getParameter("txt_nombre");
                    descripcion = request.getParameter("txt_descripcion");
                    consecutivo = request.getParameter("txt_consecutivo");
                    resultado = jpa_defecto.RegistroDefecto(id_clasificacion, id_familiaP, defecto, descripcion, consecutivo, nombre_Usuario);
                    if (resultado) {
                        lst_defecto = jpa_defecto.ConsultaDefectos(id_familiaP);
                        mail.mail_notificacion_defecto(lst_defecto);
                        request.setAttribute("registro_defecto", resultado);
                    } else {
                        request.setAttribute("registro_defecto", resultado);
                    }
                    request.getRequestDispatcher("Defecto?opc=1&idD=" + 0 + "&idF=" + id_familiaP + "&txt_bus=").forward(request, response);
                    break;
                case 3:
                    filtro = request.getParameter("txt_bus");
                    id_defecto = Integer.parseInt(request.getParameter("idD"));
                    id_familiaP = Integer.parseInt(request.getParameter("idF"));
                    id_clasificacion = Integer.parseInt(request.getParameter("slc_clasificacionM"));
                    defecto = request.getParameter("txt_nombreM");
                    descripcion = request.getParameter("txt_descripcion");
                    consecutivo = request.getParameter("txt_consecutivoM");
                    resultado = jpa_defecto.ModificarDefecto(id_defecto, id_clasificacion, defecto, descripcion, consecutivo);
                    if (resultado) {
                        lst_defecto = jpa_defecto.ConsultaDefectoId(id_defecto);
                        mail.mail_notificacion_defecto(lst_defecto);
                        request.setAttribute("modificar_defecto", resultado);
                    } else {
                        request.setAttribute("modificar_defecto", resultado);
                    }
                    request.getRequestDispatcher("Defecto?opc=1&idD=" + 0 + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 4:
                    filtro = request.getParameter("txt_bus");
                    id_defecto = Integer.parseInt(request.getParameter("idD"));
                    id_familiaP = Integer.parseInt(request.getParameter("idF"));
                    estado = Integer.parseInt(request.getParameter("est"));
                    resultado = jpa_defecto.ModificarDefectoEstado(id_defecto, estado);
                    if (resultado) {
                        request.setAttribute("estado_defecto", resultado);
                    } else {
                        request.setAttribute("estado_defecto", resultado);
                    }
                    request.setAttribute("estado", estado);
                    request.getRequestDispatcher("Defecto?opc=1&idD=" + 0 + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "").forward(request, response);
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
