package Servlets;

import Mail.Email;
import controladores.MaquinaJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Maquina extends HttpServlet {

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
            MaquinaJpaController jpa_maquina = new MaquinaJpaController();
            Email mail = new Email();
            String filtro = "";
            int id_maquina = 0, estado = 0, idTipoM = 0, id_pendiente = 0;
            String maquina = "", fecha = "", descripcion = "", solucion = "", id_usuarios = "";
            switch (opc) {
                case 1:
                    filtro = request.getParameter("txt_bus");
                    id_maquina = Integer.parseInt(request.getParameter("idM"));
                    request.setAttribute("filtro", filtro);
                    request.setAttribute("id_maquina", id_maquina);
                    request.getRequestDispatcher("maquina.jsp").forward(request, response);
                    break;
                case 2:
                    maquina = request.getParameter("txt_maquina");
                    idTipoM = Integer.parseInt(request.getParameter("lst_tipoM").toString());
                    resultado = jpa_maquina.RegistroMaquina(maquina, idTipoM, nombre_Usuario);
                    if (resultado) {
                        request.setAttribute("Registro_maquina", resultado);
                    } else {
                        request.setAttribute("Registro_maquina", resultado);
                    }
                    request.getRequestDispatcher("Maquina?opc=1&idM=" + 0 + "&txt_bus=").forward(request, response);
                    break;
                case 3:
                    id_maquina = Integer.parseInt(request.getParameter("idM").toString());
                    maquina = request.getParameter("txt_maquinaM");
                    idTipoM = Integer.parseInt(request.getParameter("lst_tipoMM").toString());
                    filtro = request.getParameter("txt_bus");
                    resultado = jpa_maquina.ModificarMaquina(id_maquina, maquina, idTipoM);
                    if (resultado) {
                        request.setAttribute("Modificar_maquina", resultado);
                    } else {
                        request.setAttribute("Modificar_maquina", resultado);
                    }
                    request.getRequestDispatcher("Maquina?opc=1&idM=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 4:
                    id_maquina = Integer.parseInt(request.getParameter("idM").toString());
                    filtro = request.getParameter("txt_bus");
                    estado = Integer.parseInt(request.getParameter("est").toString());
                    resultado = jpa_maquina.ModificarEstadoMaquina(id_maquina, estado);
                    if (resultado) {
                        request.setAttribute("estado_maquina", resultado);
                    } else {
                        request.setAttribute("estado_maquina", resultado);
                    }
                    request.setAttribute("estado", estado);
                    request.getRequestDispatcher("Maquina?opc=1&idM=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 5:
                    id_maquina = Integer.parseInt(request.getParameter("idM").toString());
                    filtro = request.getParameter("txt_bus");
                    fecha = request.getParameter("txt_fecha");
                    descripcion = request.getParameter("txt_pendiente");
                    resultado = jpa_maquina.RegistroPendienteMaquina(id_maquina, fecha, descripcion, nombre_Usuario);
                    if (resultado) {
                        request.setAttribute("registro_pendiente_maquina", resultado);
                    } else {
                        request.setAttribute("registro_pendiente_maquina", resultado);
                    }
                    request.getRequestDispatcher("Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 6:
                    id_pendiente = Integer.parseInt(request.getParameter("idP").toString());
                    id_maquina = Integer.parseInt(request.getParameter("idM").toString());
                    filtro = request.getParameter("txt_bus");
                    fecha = request.getParameter("txt_fechaM");
                    descripcion = request.getParameter("txt_pendienteM");
                    resultado = jpa_maquina.ModificarPendienteMaquina(id_pendiente, fecha, descripcion);
                    if (resultado) {
                        request.setAttribute("modificar_pendiente_maquina", resultado);
                    } else {
                        request.setAttribute("modificar_pendiente_maquina", resultado);
                    }
                    request.getRequestDispatcher("Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 7:
                    id_pendiente = Integer.parseInt(request.getParameter("idP").toString());
                    id_maquina = Integer.parseInt(request.getParameter("idM").toString());
                    filtro = request.getParameter("txt_bus");
                    fecha = request.getParameter("txt_fecha");
                    solucion = request.getParameter("txt_solucion");
                    resultado = jpa_maquina.SolucionarPendienteMaquina(id_pendiente, fecha, solucion);
                    if (resultado) {
                        request.setAttribute("solucionar_pendiente_maquina", resultado);
                    } else {
                        request.setAttribute("solucionar_pendiente_maquina", resultado);
                    }
                    request.getRequestDispatcher("Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&txt_bus=" + filtro + "&idS=" + 0 + "").forward(request, response);
                    break;
                case 8:
                    id_pendiente = Integer.parseInt(request.getParameter("idP").toString());
                    id_maquina = Integer.parseInt(request.getParameter("idM").toString());
                    filtro = request.getParameter("txt_bus");
                    fecha = request.getParameter("txt_fechaM");
                    solucion = request.getParameter("txt_solucionM");
                    resultado = jpa_maquina.SolucionarPendienteMaquina(id_pendiente, fecha, solucion);
                    if (resultado) {
                        request.setAttribute("solucionar_pendienteM_maquina", resultado);
                    } else {
                        request.setAttribute("solucionar_pendienteM_maquina", resultado);
                    }
                    request.getRequestDispatcher("Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&txt_bus=" + filtro + "&idS=" + 0 + "").forward(request, response);
                    break;
                case 9:
                    id_maquina = Integer.parseInt(request.getParameter("idM").toString());
                    id_pendiente = Integer.parseInt(request.getParameter("idP").toString());
                    id_usuarios = request.getParameter("txt_usuarios");
                    filtro = request.getParameter("txt_bus");
                    estado = Integer.parseInt(request.getParameter("est").toString());
                    
                    jpa_maquina.EstadoPendienteMaquina(id_pendiente, estado);
                    List lst_pendiente = jpa_maquina.ConsultaPendienteMaquinaIdPendiente(id_pendiente);
                    
                    resultado = mail.mail_Pendiente_maquina(id_usuarios, lst_pendiente);
                    if (resultado) {
                        request.setAttribute("Mail_pendiente_maq", resultado);
                    } else {
                        request.setAttribute("Mail_pendiente_maq", resultado);
                    }
                    request.setAttribute("estado", estado);
                    request.getRequestDispatcher("Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&txt_bus=" + filtro + "&idS=" + 0 + "").forward(request, response);
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
