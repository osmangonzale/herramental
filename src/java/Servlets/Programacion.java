package Servlets;

import controladores.BitacoraJpaController;
import controladores.NovedadPersonalJpaController;
import controladores.ProgramacionJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Programacion extends HttpServlet {

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
            ProgramacionJpaController jpa_programacion = new ProgramacionJpaController();
            NovedadPersonalJpaController jpa_novedadP = new NovedadPersonalJpaController();
            BitacoraJpaController jpa_bitacora = new BitacoraJpaController();
            String fecha = "", programacion = "", filtro = "", descripcion = "", responsables = "";
            int id_programacion = 0, id_tipo_maquina, turno = 0, id_novedadP = 0, formulario = 0, id_bitacora = 0, year = 0;
            LocalDateTime DateTime = LocalDateTime.now();
            int anioActual = DateTime.getYear();
            switch (opc) {
                case 1:
                    try {
                        id_bitacora = Integer.parseInt(request.getParameter("idB"));
                    } catch (Exception e) {
                        id_bitacora = 0;
                    }
                    try {
                        turno = Integer.parseInt(request.getParameter("turno"));
                    } catch (Exception e) {
                        turno = 0;
                    }
                    try {
                        year = Integer.parseInt(request.getParameter("anio"));
                    } catch (Exception e) {
                        year = anioActual;
                    }
                    id_tipo_maquina = Integer.parseInt(request.getParameter("idTM"));
                    try {
                        formulario = Integer.parseInt(request.getParameter("form"));
                    } catch (Exception e) {
                        formulario = 0;
                    }
                    id_novedadP = Integer.parseInt(request.getParameter("idNP"));
                    request.setAttribute("id_tipoMaq", id_tipo_maquina);
                    request.setAttribute("id_novedadP", id_novedadP);
                    request.setAttribute("id_bitacora", id_bitacora);
                    request.setAttribute("turno", turno);
                    request.setAttribute("formulario", formulario);
                    request.setAttribute("anio", year);
                    request.getRequestDispatcher("programacion.jsp").forward(request, response);
                    break;
                case 2:
                    //<editor-fold defaultstate="collapsed" desc="Reg_programacion anterior">
//                    fecha = request.getParameter("txt_fecha");
//                    programacion = request.getParameter("txt_descripcion");
//                    id_tipo_maquina = Integer.parseInt(request.getParameter("idTM").toString());
//                    turno = Integer.parseInt(request.getParameter("slt_turno").toString());
//                    resultado = jpa_programacion.RegistrarProgramacion(fecha, programacion, id_tipo_maquina, turno, nombre_Usuario);
//                    if (resultado) {
//                        request.setAttribute("Registro_programacion", resultado);
//                    } else {
//                        request.setAttribute("Registro_programacion", resultado);
//                    }
                    //</editor-fold>
                    programacion = request.getParameter("txt_descripcion");
                    responsables = request.getParameter("UsuR");
                    id_bitacora = Integer.parseInt(request.getParameter("idBt"));
                    id_tipo_maquina = Integer.parseInt(request.getParameter("idTM"));
                    turno = Integer.parseInt(request.getParameter("turno"));
                    resultado = jpa_bitacora.RegistrarBitacora(id_bitacora, programacion, responsables, turno);
                    if (resultado) {
                        request.setAttribute("Registro_programacion", resultado);
                    } else {
                        request.setAttribute("Registro_programacion", resultado);
                    }
                    request.getRequestDispatcher("Programacion?opc=1&idTM=" + id_tipo_maquina + "&idNP=0").forward(request, response);
                    break;
                case 3:
                    id_programacion = Integer.parseInt(request.getParameter("idP").toString());
                    fecha = request.getParameter("txt_fechaM");
                    programacion = request.getParameter("txt_descripcion");
                    turno = Integer.parseInt(request.getParameter("slt_turnoM").toString());
                    id_tipo_maquina = Integer.parseInt(request.getParameter("idTM").toString());
//                    resultado = jpa_programacion.ModificarProgramacion(id_programacion, fecha, programacion, turno);
                    if (resultado) {
                        request.setAttribute("Modificar_programacion", resultado);
                    } else {
                        request.setAttribute("Modificar_programacion", resultado);
                    }
                    request.getRequestDispatcher("Programacion?opc=1&idP=" + 0 + "&idTM=" + id_tipo_maquina + "&idNP=0").forward(request, response);
                    break;
                case 4:
                    fecha = request.getParameter("txt_fechaNP");
                    descripcion = request.getParameter("txt_descripcionNP");
                    id_tipo_maquina = Integer.parseInt(request.getParameter("idTM").toString());
                    resultado = jpa_novedadP.RegistrarNovedad(fecha, descripcion, id_tipo_maquina, nombre_Usuario);
                    if (resultado) {
                        request.setAttribute("registro_novedad", resultado);
                    } else {
                        request.setAttribute("registro_novedad", resultado);
                    }
                    request.getRequestDispatcher("Programacion?opc=1&idP=" + 0 + "&idTM=" + id_tipo_maquina + "&idNP=0").forward(request, response);
                    break;
                case 5:
                    id_novedadP = Integer.parseInt(request.getParameter("idNP").toString());
                    fecha = request.getParameter("txt_fechaNP");
                    descripcion = request.getParameter("txt_descripcionNP");
                    id_tipo_maquina = Integer.parseInt(request.getParameter("idTM").toString());
                    resultado = jpa_novedadP.ModificarNovedad(id_novedadP, fecha, descripcion);
                    if (resultado) {
                        request.setAttribute("modificar_novedad", resultado);
                    } else {
                        request.setAttribute("modificar_novedad", resultado);
                    }
                    request.getRequestDispatcher("Programacion?opc=1&idP=" + 0 + "&idTM=" + id_tipo_maquina + "&idNP=0").forward(request, response);
                    break;
                case 6:
                    request.getRequestDispatcher("programacion.jsp").forward(request, response);
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
