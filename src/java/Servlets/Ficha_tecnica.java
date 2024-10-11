/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Mail.Email;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controladores.FichaTecnicaJpaController;
import controladores.UsuarioJpaController;
import java.util.List;

public class Ficha_tecnica extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession sesion = request.getSession();
            int opc = Integer.parseInt(request.getParameter("opc"));
            boolean result = false;

            String Rol_usuario = sesion.getAttribute("Rol").toString();
            String nombre_usuario = sesion.getAttribute("Nombre").toString();
            FichaTecnicaJpaController JpaFichaTecnica = new FichaTecnicaJpaController();
            UsuarioJpaController JpaUsuario = new UsuarioJpaController();
            List lst_usuario = null;
            List lst_fichaT = null;

            //<editor-fold defaultstate="collapsed" desc="VARIABLES">
            Email mail = new Email();
            String filtro = "";
            int id_fichaTec = 0, id_fichaLog = 0;
            int nmb_estado = 0, firma_insu = 0, firma_cali = 0, firma_pro = 0, valid_firmas = 0, id_pend = 0;
            int id_herra = 0, U_code = 0, U_cc = 0, val_estado = 0, est_estado = 0, fil_estado = 0, version = 0;
            String Ref_ficha = "", nombref = "", id_herr = "";
            String mensaje = "", filter = "", usa_firmas = "";

            //<editor-fold defaultstate="collapsed" desc="Variables de Pendientes">
            int ficha_p = 0, id_ficha = 0, ficha_t = 0, id_pdnt = 0, id_pdntC = 0;
            String desc = "", fecha_pdnt = "", cargos = "";
            //</editor-fold>

            //</editor-fold>
            switch (opc) {
                case 1:
                    //<editor-fold defaultstate="collapsed" desc="REGISTRAR FICHA">
                    Ref_ficha = request.getParameter("Txt_RefTec");
                    nombref = request.getParameter("Txt_Nombre");
                    version = Integer.parseInt(request.getParameter("Txt_version"));
                    try {
                        id_herr = request.getParameter("Txt_ids");
                    } catch (Exception e) {
                        id_herr = "";
                    }
                    if (!id_herr.equals("")) {
                        result = JpaFichaTecnica.Registrar_Ficha_Tecnica_v2(Ref_ficha, nombref, id_herr, Rol_usuario, version);
                    } else {
                        request.setAttribute("Conf_herramental", id_herr);
                    }
                    request.setAttribute("Registro_FichaT", result);
                    request.setAttribute("dd", result);
                    request.getRequestDispatcher("Ficha_tecnica?opc=9").forward(request, response);
                    break;
                //</editor-fold> 
                case 2:
                    //<editor-fold defaultstate="collapsed" desc="CONSULTAR HERRAMENAL">
                    try {
                        id_fichaTec = Integer.parseInt(request.getParameter("id_fichat"));
                    } catch (Exception e) {
                        id_fichaTec = 0;
                    }
                    request.setAttribute("Consultar_Herramental_id", id_fichaTec);
                    request.getRequestDispatcher("Ficha_tecnica?opc=9").forward(request, response);
                    break;
                //</editor-fold>
                case 3:
                    //<editor-fold defaultstate="collapsed" desc="CONSULTAR IDS">
                    try {
                        id_fichaTec = Integer.parseInt(request.getParameter("id_fichat"));
                    } catch (Exception e) {
                        id_fichaTec = 0;
                    }
                    try {
                        id_herra = Integer.parseInt(request.getParameter("id_herr"));
                    } catch (Exception e) {
                        id_herra = 0;
                    }
                    try {
                        filter = request.getParameter("Txt_filtro");
                    } catch (Exception e) {
                        filter = "";
                    }

                    try {
                        est_estado = Integer.parseInt(request.getParameter("est_estado"));
                    } catch (Exception e) {
                        est_estado = 0;
                    }

                    try {
                        fil_estado = Integer.parseInt(request.getParameter("fil_estado"));
                    } catch (Exception e) {
                        fil_estado = 0;
                    }
                    try {
                        firma_insu = Integer.parseInt(request.getParameter("firma_insu"));
                    } catch (Exception e) {
                        firma_insu = 0;
                    }
                    try {
                        firma_cali = Integer.parseInt(request.getParameter("firma_cali"));
                    } catch (Exception e) {
                        firma_cali = 0;
                    }

                    try {
                        firma_pro = Integer.parseInt(request.getParameter("firma_pro"));
                    } catch (Exception e) {
                        firma_pro = 0;
                    }

                    request.setAttribute("Consultar_Estado_Ficha", id_fichaTec);
                    request.setAttribute("Consultar_Id_Herramentla", id_herra);
                    request.setAttribute("Filtrar_datos", filter);
                    request.setAttribute("Consultar_PorEstado", est_estado);
                    request.setAttribute("Filtros_Estados", fil_estado);
                    request.setAttribute("Validar_firmas_insumos", firma_insu);
                    request.setAttribute("Validar_firmas_calidad", firma_cali);
                    request.setAttribute("Validar_firmas_proyectos", firma_pro);
                    request.setAttribute("Consultar_historial", id_fichaLog);
                    request.getRequestDispatcher("Ficha_tecnica?opc=9").forward(request, response);
                    break;
                //</editor-fold>
                case 4:
                    //<editor-fold defaultstate="collapsed" desc="EDITAR FICHA TECNICA">                    
                    id_fichaTec = Integer.parseInt(request.getParameter("id_fichat"));
                    Ref_ficha = request.getParameter("Txt_RefTec");
                    version = Integer.parseInt(request.getParameter("Txt_version"));
                    nombref = request.getParameter("Txt_Nombre");
                    nombref = nombref.replace("Ã", "Ó")
                            .replace("Ã\u009A", "Ú").replace("Ã\u0093", "Ó")
                            .replace("Ã\u008D", "Í").replace("Ã\u0089", "É")
                            .replace("Ã\u0081", "Á").replace("Ã³", "Ó");
                    try {
                        id_herr = request.getParameter("Txt_ids");
                    } catch (Exception e) {
                        id_herr = "";
                    }
                    if (!id_herr.equals("")) {
                        JpaFichaTecnica.Registrar_Ficha_Tecnica_Log(Rol_usuario, id_fichaTec);
                        result = JpaFichaTecnica.Editar_FichaTecnica(id_fichaTec, Ref_ficha, nombref, id_herr, version);
                    } else {
                        request.setAttribute("Conf_herramental", id_herr);
                    }

                    request.setAttribute("Editar_ficha", result);
                    request.getRequestDispatcher("Ficha_tecnica?opc=9").forward(request, response);
                    break;
                //</editor-fold>
                case 5:
                    //<editor-fold defaultstate="collapsed" desc="CONSULTAR Y TRAER FIRMAS">                    
                    id_fichaTec = Integer.parseInt(request.getParameter("id_fichat"));
                    val_estado = Integer.parseInt(request.getParameter("val_estado"));
                    U_code = Integer.parseInt(request.getParameter("Txt_code"));
                    U_cc = Integer.parseInt(request.getParameter("nmb_cc"));
                    lst_usuario = JpaUsuario.Firma(U_cc, U_code);

                    if (lst_usuario == null) {
                        mensaje = "El usuario no existe";
                    } else {
                        Object[] obj_firmas = (Object[]) lst_usuario.get(0);
                        JpaFichaTecnica.Registrar_firmas(id_fichaTec, obj_firmas[2].toString(), Integer.parseInt(obj_firmas[5].toString()));
                        mensaje = "";
                    }

                    request.setAttribute("Validar_code", U_code);
                    request.setAttribute("Validar_cc", U_cc);
                    request.setAttribute("Validar_est", val_estado);
                    request.setAttribute("Consultar_IdFicha", id_fichaTec);
                    request.setAttribute("Enviar_mensaje", mensaje);
                    request.getRequestDispatcher("Ficha_tecnica?opc=9").forward(request, response);
                    break;
                //</editor-fold>
                case 6:
                    //<editor-fold defaultstate="collapsed" desc="CONSULTAR Y REGISTRAR ESTADO FICHA">
                    id_fichaTec = Integer.parseInt(request.getParameter("id_ficha"));
                    nmb_estado = Integer.parseInt(request.getParameter("nmb_estado"));

                    if (nmb_estado == 3) {
                        boolean results = true;
                        request.setAttribute("Estado_Inactivo", results);
                    }
                    if (nmb_estado == 1) {
                        JpaFichaTecnica.Registrar_Ficha_Tecnica_Log(Rol_usuario, id_fichaTec);
                        result = JpaFichaTecnica.Editar_FichaTecnica_Estado(id_fichaTec, nmb_estado);
                    } else {
                        request.setAttribute("Consultar_IdFicha", id_fichaTec);
                        request.setAttribute("Validar_est", nmb_estado);
                    }
                    request.setAttribute("confimr_result", result);
                    request.getRequestDispatcher("Ficha_tecnica?opc=9").forward(request, response);
                    break;
                //</editor-fold>
                case 7:
                    //<editor-fold defaultstate="collapsed" desc="FIRMAS 2.0">
                    id_fichaTec = Integer.parseInt(request.getParameter("id_fichat"));
                    valid_firmas = Integer.parseInt(request.getParameter("txt_val"));
                    usa_firmas = request.getParameter("txt_resp");
                    val_estado = Integer.parseInt(request.getParameter("val_estado"));
                    if (valid_firmas == 3) {
//                        lst_usuario = JpaFichaTecnica.ConsultaFichaTecnica_id(id_fichaTec);
//                        JpaFichaTecnica.Registrar_FichaTecnica(Ref_ficha, nombref, mensaje, fil_estado, filter, filter, filter, U_code, usa_firmas)egistrar_Ficha_Tecnica_v2(id_fichaTec);
                        JpaFichaTecnica.Registrar_Ficha_Tecnica_Log(Rol_usuario, id_fichaTec);
                        result = JpaFichaTecnica.Editar_FichaTecnica_Estado(id_fichaTec, val_estado);
                    } else {
                        U_code = Integer.parseInt(request.getParameter("Txt_code"));
                        try {
                            U_cc = Integer.parseInt(request.getParameter("nmb_cc"));
                        } catch (Exception e) {
                            U_cc = 0;
                        }
                    }
                    request.setAttribute("txt_resp", usa_firmas);
                    request.setAttribute("Validar_code", U_code);
                    request.setAttribute("Validar_cc", U_cc);
                    request.setAttribute("Validar_est", ((valid_firmas == 3) ? 0 : val_estado));
                    request.setAttribute("Consultar_IdFicha", id_fichaTec);
                    request.setAttribute("txt_val", valid_firmas);
                    request.setAttribute("confimr_result", result);
                    request.getRequestDispatcher("Ficha_tecnica?opc=9").forward(request, response);
                    //</editor-fold>
                    break;
                case 8:
                    //<editor-fold defaultstate="collapsed" desc="REDIRECCIONARA FICHA TECNICA JSP">
                    try {
                        id_fichaLog = Integer.parseInt(request.getParameter("id_fichaLog"));
                    } catch (Exception e) {
                        id_fichaLog = 0;
                    }

                    request.setAttribute("Ficha_tecnica", "Consulta_Historial");
                    request.setAttribute("Consultar_historial", id_fichaLog);
                    request.getRequestDispatcher("Ficha_tecnica.jsp").forward(request, response);
                    //</editor-fold>
                    break;
                case 9:
                    request.setAttribute("Ficha_tecnica", "Consulta_Fichas");
                    request.getRequestDispatcher("Ficha_tecnica.jsp").forward(request, response);
                    break;
                case 10:
                    //<editor-fold defaultstate="collapsed" desc="IR A PENDIENTES">

                    try {
                        ficha_p = Integer.parseInt(request.getParameter("id_fichaLog"));
                    } catch (Exception e) {
                        ficha_p = 0;
                    }

                    try {
                        id_pdnt = Integer.parseInt(request.getParameter("id_pndte"));
                    } catch (Exception e) {
                        id_pdnt = 0;
                    }
                    
                    try {
                        id_pdntC = Integer.parseInt(request.getParameter("num_pend"));
                        request.setAttribute("id_pendt_cargos", id_pdntC);
                    } catch (Exception e) {
                        id_pdntC = 0;
                    }

                    request.setAttribute("Ficha_tecnica", "Gestionar_Pendientes");
                    request.setAttribute("Gestionar_Pendientes", ficha_p);
                    request.setAttribute("Id_pendiente", id_pdnt);
                    
                    request.getRequestDispatcher("Ficha_tecnica.jsp").forward(request, response);
                    //</editor-fold>
                    break;
                case 11:
                    //<editor-fold defaultstate="collapsed" desc="REGISTRAR PENDIENTES">
                    try {
                        id_ficha = Integer.parseInt(request.getParameter("id_ficha"));
                    } catch (Exception e) {
                        id_ficha = 0;
                    }
                    try {
                        fecha_pdnt = request.getParameter("txt_fecha");
                    } catch (Exception e) {
                        fecha_pdnt = "";
                    }
                    try {
                        desc = request.getParameter("Txt_instruccion_seguridad");
                    } catch (Exception e) {
                        desc = "";
                    }
                    result = JpaFichaTecnica.Registrar_Pendiente_fichaT(id_ficha, fecha_pdnt, desc, Rol_usuario);
                    if (result) {
                        request.setAttribute("Registrar_Pndt", result);
                    } else {
                        request.setAttribute("Registrar_Pndt", result);
                    }
                    request.getRequestDispatcher("Ficha_tecnica?opc=10&id_fichaLog=" + id_ficha + "").forward(request, response);
                    //</editor-fold>
                    break;
                case 12:
                    //<editor-fold defaultstate="collapsed" desc="EDITAR PENDIENTES">
                    try {
                        id_pdnt = Integer.parseInt(request.getParameter("id_pndt"));
                    } catch (Exception e) {
                        id_pdnt = 0;
                    }
                    try {
                        ficha_p = Integer.parseInt(request.getParameter("id_fichat"));
                    } catch (Exception e) {
                        ficha_p = 0;
                    }
                    try {
                        fecha_pdnt = request.getParameter("txt_fecha");
                    } catch (Exception e) {
                        fecha_pdnt = "";
                    }
                    try {
                        desc = request.getParameter("Txt_instruccion_seguridad");
                    } catch (Exception e) {
                        desc = "";
                    }

                    result = JpaFichaTecnica.Editar_Pendiente_Ficha_T(id_pdnt, fecha_pdnt, desc, Rol_usuario);
                    request.setAttribute("Editar_Pndt", result);
                    request.getRequestDispatcher("Ficha_tecnica?opc=10&id_fichaLog=" + ficha_p + "").forward(request, response);
                    //</editor-fold>
                    break;
                case 13:
                    //<editor-fold defaultstate="collapsed" desc="ENVIAR CORREO PENDIENTES">
                    try {
                        ficha_p = Integer.parseInt(request.getParameter("txt_idficha"));
                    } catch (Exception e) {
                        ficha_p = 0;
                    }
                    try {
                        cargos = request.getParameter("txt_cargs");
                    } catch (Exception e) {
                        cargos = "";
                    }
                    try {
                        id_pend = Integer.parseInt(request.getParameter("num_pendi"));
                    } catch (Exception e) {
                        id_pend = 0;
                    }
                    try {
                        lst_fichaT = JpaFichaTecnica.Consultar_Pendiente_FichaT_Id(ficha_p);
                    } catch (Exception e) {
                        lst_fichaT = null;
                    }
                    
                    result = mail.mail_Pendiente_Ficha_t(cargos, lst_fichaT);

                    if (result) {
                        int conf = 1;
                        request.setAttribute("Mail_pendiente_Ficha", result);
                        JpaFichaTecnica.Confirmar_Envio_Correo(id_pend, conf);
                    } else {
                        request.setAttribute("Mail_pendiente_Ficha", result);
                    }
                    request.getRequestDispatcher("Ficha_tecnica?opc=10&id_fichaLog=" + ficha_p + "").forward(request, response);
                    //</editor-fold>
                    break;
                case 14:
                    //<editor-fold defaultstate="collapsed" desc="SELECCION CORREO POR CARGOS">
                    try {
                        ficha_p = Integer.parseInt(request.getParameter("id_fichaLog"));
                    } catch (Exception e) {
                        ficha_p = 0;
                    }
                    try {
                        id_pdntC = Integer.parseInt(request.getParameter("id_pndt"));
                    } catch (Exception e) {
                        id_pdntC = 0;
                    }
                    request.setAttribute("id_pendt_cargos", id_pdntC);
                    request.getRequestDispatcher("Ficha_tecnica?opc=10&id_fichaLog=" + ficha_p + "&id_pndt="+ id_pdntC +"").forward(request, response);
                    //</editor-fold>
                    break;
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("Ficha_tecnica.jsp").forward(request, response);
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
