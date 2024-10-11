package Servlets;

import controladores.InformeJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Informe extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            HttpSession sesion = request.getSession();
            int opc = Integer.parseInt(request.getParameter("opc"));
            Boolean resultado = false;
            String Rol_Usuario = sesion.getAttribute("Rol").toString();
            String nombre_Usuario = sesion.getAttribute("Nombre").toString();
            InformeJpaController jpa_informe = new InformeJpaController();
            int id_informe = 0, id_herramental = 0, prototipo = 0, cantidad = 0, numero = 0, duracion = 0, consecutivo = 0;
            String descripcion = "", dimesiones = "", material = "", fecha_inicio = "", fecha_final = "";
            int dias = 0;
            switch (opc) {
                case 1:
                    id_informe = Integer.parseInt(request.getParameter("idI"));
                    consecutivo = Integer.parseInt(request.getParameter("Cons"));
                    id_herramental = Integer.parseInt(request.getParameter("idH"));
                    request.setAttribute("id_herramental", id_herramental);
                    request.setAttribute("id_informe", id_informe);
                    request.setAttribute("consecutivo", consecutivo);
                    request.getRequestDispatcher("informe.jsp").forward(request, response);
                    break;
                case 2:
                    id_herramental = Integer.parseInt(request.getParameter("idH"));
                    id_informe = Integer.parseInt(request.getParameter("idI"));
                    try {
                        prototipo = Integer.parseInt(request.getParameter("check_prot"));
                    } catch (Exception e) {
                        prototipo = 0;
                    }
                    descripcion = request.getParameter("txt_descripcion");
                    descripcion = descripcion.trim();
                    cantidad = Integer.parseInt(request.getParameter("nmb_cantidad"));
                    numero = Integer.parseInt(request.getParameter("nmb_numero"));
                    dimesiones = request.getParameter("txt_dimensiones");
                    material = request.getParameter("txt_material");
                    fecha_inicio = request.getParameter("txt_fchIni");
                    fecha_final = request.getParameter("txt_fchFin");
                    Date fechI = dateFormat.parse(fecha_inicio);
                    Date fechF = dateFormat.parse(fecha_final);
                    dias = (int) ((fechF.getTime() - fechI.getTime()) / 86400000);
                    duracion = dias;
                    if (id_informe == 0) {
                        resultado = jpa_informe.RegistroInforme(id_herramental, prototipo, descripcion, cantidad, numero, dimesiones, material, fecha_inicio, fecha_final, duracion, nombre_Usuario);
                    } else {
                        resultado = jpa_informe.RegistroInformeFase(id_herramental, id_informe, prototipo, descripcion, cantidad, numero, dimesiones, material, fecha_inicio, fecha_final, duracion, nombre_Usuario);
                    }
                    if (resultado) {
                        request.setAttribute("registro_informe", resultado);
                    } else {
                        request.setAttribute("registro_informe", resultado);
                    }
                    request.getRequestDispatcher("Informe?opc=1&idH=" + id_herramental + "&idI=0&Cons=0").forward(request, response);
                    break;
                case 3:
                    id_herramental = Integer.parseInt(request.getParameter("idH"));
                    id_informe = Integer.parseInt(request.getParameter("idI"));
                    try {
                        prototipo = Integer.parseInt(request.getParameter("check_prot"));
                    } catch (Exception e) {
                        prototipo = 0;
                    }
                    descripcion = request.getParameter("txt_descripcion");
                    descripcion = descripcion.trim();
                    cantidad = Integer.parseInt(request.getParameter("nmb_cantidad"));
                    numero = Integer.parseInt(request.getParameter("nmb_numero"));
                    dimesiones = request.getParameter("txt_dimensiones");
                    material = request.getParameter("txt_material");
                    fecha_inicio = request.getParameter("txt_fchIni");
                    fecha_final = request.getParameter("txt_fchFin");
                    Date fechII = dateFormat.parse(fecha_inicio);
                    Date fechFF = dateFormat.parse(fecha_final);
                    dias = (int) ((fechFF.getTime() - fechII.getTime()) / 86400000);
                    duracion = dias;
                    resultado = jpa_informe.ModificarInforme(id_informe, prototipo, descripcion, cantidad, numero, dimesiones, material, fecha_inicio, fecha_final, duracion);
                    if (resultado) {
                        request.setAttribute("modificar_informe", resultado);
                    } else {
                        request.setAttribute("modificar_informe", resultado);
                    }
                    request.getRequestDispatcher("Informe?opc=1&idH=" + id_herramental + "&idI=0&Cons=0").forward(request, response);
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
