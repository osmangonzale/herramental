package Servlets;

import controladores.BitacoraJpaController;
import controladores.MaquinaJpaController;
import controladores.TipoMaquinaJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Bitacora extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
            int opc = Integer.parseInt(request.getParameter("opc"));
            Boolean resultado = false;
            BitacoraJpaController jpa_bitacora = new BitacoraJpaController();
            TipoMaquinaJpaController jpa_tipoM = new TipoMaquinaJpaController();
            MaquinaJpaController jpa_maquina = new MaquinaJpaController();
            List lst_tipoM = jpa_tipoM.ConsultaTipoMaquinasPi();
            List lst_maquinas = null;
            List lst_bitacoras = null;
            int turno = 0, id_maquina = 0;
            switch (opc) {
                case 1:
                    for (int i = 0; i < 3; i++) {
                        Object[] obj_tipoM = (Object[]) lst_tipoM.get(i);
                        lst_maquinas = jpa_maquina.ConsultaMaquinasIdTipo(Integer.parseInt(obj_tipoM[0].toString()));
                        for (int j = 0; j < lst_maquinas.size(); j++) {
                            Object[] obj_maquinas = (Object[]) lst_maquinas.get(j);
                            id_maquina = Integer.parseInt(obj_maquinas[0].toString());
                            lst_bitacoras = jpa_bitacora.ConsultaBitacoraIdMaquina(id_maquina);
                            if (lst_bitacoras != null) {
                                Object[] obj_bitacora = (Object[]) lst_bitacoras.get(0);
                                jpa_bitacora.RegistroBitacorasMaquinas(Integer.parseInt(obj_maquinas[0].toString()), obj_maquinas[7].toString(), ((obj_bitacora[3] != null) ? obj_bitacora[3].toString() : ""));
                            } else {
                                jpa_bitacora.RegistroBitacorasMaquinas(Integer.parseInt(obj_maquinas[0].toString()), obj_maquinas[7].toString(), "");
                            }

                        }
                    }
                    request.getRequestDispatcher("Bitacora?opc=3").forward(request, response);
                    break;
                case 2:
                    turno = Integer.parseInt(request.getParameter("turno"));
                    jpa_bitacora.EstadoBitacoraTurno(turno);
                    request.getRequestDispatcher("Bitacora?opc=3").forward(request, response);
                    break;
                case 3:
                    response.sendRedirect("http://172.16.2.117:8084/Aplicativos_Plastitec/Automatic_servlets.jsp");
                    break;
            }
        } catch (NumberFormatException ex) {
            response.sendRedirect("http://172.16.2.117:8084/Aplicativos_Plastitec/Automatic_servlets.jsp");
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
