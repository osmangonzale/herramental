package Servlets;

import Mail.Email;
import controladores.HerramentalJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Herramental extends HttpServlet {

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
            HerramentalJpaController jpa_herramental = new HerramentalJpaController();
            Email mail = new Email();
            String filtro = "";
            int id_herramental = 0, estado = 0, id_tipoH = 0, id_pendiente = 0, id_movimiento = 0, solucion = 0;
            String herramental = "", idmaquinas = "", fecha = "", descripcion = "", id_usuarios = "";
            switch (opc) {
                case 1:
                    filtro = request.getParameter("txt_bus");
                    id_herramental = Integer.parseInt(request.getParameter("idH"));
                    if (!filtro.equals("")) {
                        request.setAttribute("consulta_herramentales", jpa_herramental.ConsultaHerramentalFiltro(filtro));
                    } else {
                        request.setAttribute("consulta_herramentales", jpa_herramental.ConsultaHerramentales());
                    }
                    request.setAttribute("filtro", filtro);
                    request.setAttribute("Modulo_pendientes", 0);
                    request.setAttribute("id_herramental", id_herramental);
                    request.getRequestDispatcher("herramental.jsp").forward(request, response);
                    break;
                case 2:
                    herramental = request.getParameter("txt_herramental");
                    idmaquinas = request.getParameter("txt_idM");
                    id_tipoH = Integer.parseInt(request.getParameter("lst_tipoH"));
                    descripcion = request.getParameter("txt_descripcion");
                    resultado = jpa_herramental.RegistroHerramental(id_tipoH, herramental, idmaquinas, nombre_Usuario,descripcion);
                    if (resultado) {
                        request.setAttribute("registro_herramental", resultado);
                    } else {
                        request.setAttribute("registro_herramental", resultado);
                    }
                    request.getRequestDispatcher("Herramental?opc=1&idH=" + 0 + "&txt_bus=").forward(request, response);
                    break;
                case 3:
                    id_herramental = Integer.parseInt(request.getParameter("idH"));
                    herramental = request.getParameter("txt_herramentalM");
                    idmaquinas = request.getParameter("txt_idMM");
                    id_tipoH = Integer.parseInt(request.getParameter("lst_tipoHM"));
                    descripcion = request.getParameter("txt_descripcion");
                    filtro = request.getParameter("txt_bus");
                    resultado = jpa_herramental.ModificarHerramental(id_herramental, herramental, id_tipoH, idmaquinas,descripcion);
                    if (resultado) {
                        request.setAttribute("modificar_herramental", resultado);
                    } else {
                        request.setAttribute("modificar_herramental", resultado);
                    }
                    request.getRequestDispatcher("Herramental?opc=1&idH=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 4:
                    id_herramental = Integer.parseInt(request.getParameter("idH"));
                    estado = Integer.parseInt(request.getParameter("est"));
                    filtro = request.getParameter("txt_bus");
                    resultado = jpa_herramental.EstadoHerramental(id_herramental, estado);
                    if (resultado) {
                        request.setAttribute("estado_herramental", resultado);
                    } else {
                        request.setAttribute("estado_herramental", resultado);
                    }
                    request.setAttribute("estado", estado);
                    request.getRequestDispatcher("Herramental?opc=1&idH=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 5:
                    filtro = request.getParameter("txt_bus");
                    id_herramental = Integer.parseInt(request.getParameter("idH"));
                    id_pendiente = Integer.parseInt(request.getParameter("idP"));
                    id_movimiento = Integer.parseInt(request.getParameter("idMV"));
                    solucion = Integer.parseInt(request.getParameter("idS"));
                    request.setAttribute("Modulo_pendientes", 1);
                    request.setAttribute("filtro", filtro);
                    request.setAttribute("id_pendiente", id_pendiente);
                    request.setAttribute("id_herramental", id_herramental);
                    request.setAttribute("id_movimiento", id_movimiento);
                    request.setAttribute("solucion", solucion);
                    request.getRequestDispatcher("herramental.jsp").forward(request, response);
                    break;
                case 6:
                    filtro = request.getParameter("txt_bus");
                    id_herramental = Integer.parseInt(request.getParameter("idH"));
                    fecha = request.getParameter("txt_fecha");
                    descripcion = request.getParameter("txt_descripcionM");
                    resultado = jpa_herramental.RegistroPendiente(id_herramental, fecha, descripcion, nombre_Usuario);
                    if (resultado) {
                        request.setAttribute("registro_pendiente", resultado);
                    } else {
                        request.setAttribute("registro_pendiente", resultado);
                    }
                    request.getRequestDispatcher("Herramental?opc=5&idH=" + id_herramental + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 7:
                    filtro = request.getParameter("txt_bus");
                    id_herramental = Integer.parseInt(request.getParameter("idH"));
                    id_pendiente = Integer.parseInt(request.getParameter("idP"));
                    fecha = request.getParameter("txt_fechaM");
                    descripcion = request.getParameter("txt_descripcionM");
                    resultado = jpa_herramental.ModificarPendiente(id_pendiente, fecha, descripcion);
                    if (resultado) {
                        request.setAttribute("modificar_pendiente", resultado);
                    } else {
                        request.setAttribute("modificar_pendiente", resultado);
                    }
                    request.getRequestDispatcher("Herramental?opc=5&idH=" + id_herramental + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 8:
                    filtro = request.getParameter("txt_bus");
                    id_herramental = Integer.parseInt(request.getParameter("idH"));
                    id_pendiente = Integer.parseInt(request.getParameter("idP"));
                    id_usuarios = request.getParameter("txt_usuarios");
                    estado = Integer.parseInt(request.getParameter("est"));
                    List lst_pendiente = jpa_herramental.ConsultaPendientesId(id_pendiente);
                    jpa_herramental.EstadoPendienteHerramental(id_pendiente, estado);
                    resultado = mail.mail_Pendiente_herramental(id_usuarios, lst_pendiente, nombre_Usuario);
                    if (resultado) {
                        request.setAttribute("Mail_pendiente_her", resultado);
                    } else {
                        request.setAttribute("Mail_pendiente_her", resultado);
                    }
                    request.setAttribute("estado", estado);
                    request.getRequestDispatcher("Herramental?opc=5&idH=" + id_herramental + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 9:
                    filtro = request.getParameter("txt_bus");
                    id_herramental = Integer.parseInt(request.getParameter("idH"));
                    id_pendiente = Integer.parseInt(request.getParameter("idP"));
                    fecha = request.getParameter("txt_fecha");
                    descripcion = request.getParameter("txt_solucion");
                    resultado = jpa_herramental.SolucionPendienteHerramental(id_pendiente, fecha, descripcion);
                    if (resultado) {
                        request.setAttribute("solucionar_pendiente_herramental", resultado);
                    } else {
                        request.setAttribute("solucionar_pendiente_herramental", resultado);
                    }
                    request.getRequestDispatcher("Herramental?opc=5&idH=" + id_herramental + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
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
