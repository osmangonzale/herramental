package Servlets;

import Factory.Productos;
import Mail.Email;
import controladores.HerramentalJpaController;
import controladores.MaquinaJpaController;
import controladores.MovimientoInyectoraJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Movimiento extends HttpServlet {

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
            String codigo_Usu = sesion.getAttribute("Codigo").toString();
            String area = sesion.getAttribute("Area").toString();
            MovimientoInyectoraJpaController jpa_movimiento = new MovimientoInyectoraJpaController();
            HerramentalJpaController jpa_herramental = new HerramentalJpaController();
            MaquinaJpaController jpa_maquina = new MaquinaJpaController();
            Email mail = new Email();
            Productos prod = new Productos();
            List lst_movimiento = null;
            List lst_Ultmovimiento = null;
            String filtro = "";
            String descripcion_herramental = "", usa_firmas = "";
            int id_maquina = 0, id_Herramental = 0, id_movimiento = 0, id_tipoM = 0, cavidad = 0, cavidadDes = 0, id_pendiente = 0, solucion = 0, consecutivo = 0, documento = 0, codUsa = 0, valF = 0, tipoR = 0;
            String fecha = "", descripcion = "", ids_herramental = "", codigo = "", producto = "", lote = "", cavidades = "", herramental = "", id_usuarios = "", descripcion_cavidadesT = "", programacion = "";
            switch (opc) {
                case 1:
                    filtro = request.getParameter("txt_bus");
                    id_maquina = Integer.parseInt(request.getParameter("idM"));
                    id_movimiento = Integer.parseInt(request.getParameter("idMV"));
                    id_pendiente = Integer.parseInt(request.getParameter("idP"));
                    try {
                        consecutivo = Integer.parseInt(request.getParameter("cons"));
                    } catch (Exception ex) {
                        consecutivo = 0;
                    }
                    solucion = Integer.parseInt(request.getParameter("idS"));
                    request.setAttribute("filtro", filtro);
                    request.setAttribute("id_maquina", id_maquina);
                    request.setAttribute("id_movimiento", id_movimiento);
                    request.setAttribute("id_pendiente", id_pendiente);
                    request.setAttribute("consecutivo", consecutivo);
                    request.setAttribute("solucion", solucion);
                    if (id_maquina == 1) {
                        id_Herramental = Integer.parseInt(request.getParameter("idH"));
                        request.getRequestDispatcher("Herramental?opc=5&idH=" + id_Herramental + "&idP=" + 0 + "&idMV=" + id_movimiento + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    } else {
                        request.getRequestDispatcher("movimiento.jsp").forward(request, response);
                    }
                    break;
                case 2:
                    filtro = request.getParameter("txt_bus");
                    try {
                        id_maquina = Integer.parseInt(request.getParameter("idM"));
                    } catch (Exception ex) {
                        id_maquina = 1;
                    }
                    ids_herramental = request.getParameter("lst_herramental");
                    if (ids_herramental == null) {
                        ids_herramental = "1-N/A";
                    }
                    String[] idsH = ids_herramental.split("-");
                    id_Herramental = Integer.parseInt(idsH[0]);
                    try {
                        id_tipoM = Integer.parseInt(request.getParameter("lst_TipoM"));
                    } catch (Exception ex) {
                        id_tipoM = 1;
                    }
                    fecha = request.getParameter("txt_fecha");
                    producto = request.getParameter("slt_producto");
                    if (producto == null) {
                        producto = "N/A";
                    }
                    lote = request.getParameter("txt_lote");
                    if (lote == null) {
                        lote = "N/A";
                    }
                    try {
                        cavidad = Integer.parseInt(request.getParameter("txt_cavidad"));
                    } catch (Exception ex) {
                        cavidad = 0;
                    }
                    try {
                        tipoR = Integer.parseInt(request.getParameter("tipoM"));
                    } catch (Exception ex) {
                        tipoR = 2;
                    }
                    try {
                        cavidadDes = Integer.parseInt(request.getParameter("txt_cavidadD"));
                    } catch (Exception ex) {
                        cavidadDes = 0;
                    }
                    cavidades = request.getParameter("txt_cavidades");
                    if (cavidades == null) {
                        cavidades = "N/A";
                    }
                    descripcion_cavidadesT = request.getParameter("txt_descripcionC");
                    if (descripcion_cavidadesT == null) {
                        cavidades = "N/A";
                    }
                    descripcion = request.getParameter("txt_descripcion");
                    usa_firmas = request.getParameter("txt_resp");
                    List lst_herramental = jpa_herramental.ConsultaHerramentalId(id_Herramental);
                    Object[] obj_herramental = (Object[]) lst_herramental.get(0);
                    List lst_tipoM = jpa_movimiento.ConsultaMovimientosId(id_tipoM);
                    Object[] obj_TipoM = (Object[]) lst_tipoM.get(0);
                    if (!obj_herramental[3].toString().contains("[" + id_maquina + "]")) {
                        jpa_herramental.ModificarHerramental(id_Herramental, obj_herramental[4].toString(), (Integer) obj_herramental[1], obj_herramental[3].toString() + "[" + id_maquina + "]", descripcion_herramental);
                    }
                    jpa_herramental.ModificarHerramentalEstado(id_Herramental, (Integer) obj_TipoM[3], id_tipoM);

                    lst_Ultmovimiento = jpa_movimiento.ConsultaUltMovimientoIdMaquina(id_maquina);
                    if (tipoR == 2) {
                        if (id_maquina == 1) {
                            resultado = jpa_movimiento.RegistroMovimientoHerramental(id_maquina, id_Herramental, id_tipoM, fecha, producto, lote, cavidad, cavidadDes, cavidades, "[" + nombre_Usuario + "/" + area + "]", descripcion_cavidadesT);
                        } else {
                            resultado = jpa_movimiento.RegistroMovimiento(id_maquina, id_Herramental, id_tipoM, fecha, producto, lote, cavidad, cavidadDes, cavidades, descripcion, "[" + nombre_Usuario + "/" + area + "]", descripcion_cavidadesT, usa_firmas);
                        } //                        resultado = jpa_movimiento.RegistroMovimiento(id_maquina, id_Herramental, id_tipoM, fecha, producto, lote, cavidad, cavidadDes, cavidades, descripcion_bd, "[" + nombre_Usuario + "/" + area + "]", descripcion_cavidadesT, usa_firmas);
                        request.setAttribute("registro_movimiento", resultado);
                    } else if (tipoR == 1) {
                        if (lst_Ultmovimiento != null) {
                            Object[] obj_ultM = (Object[]) lst_Ultmovimiento.get(0);
                            if (obj_ultM[2].equals("MONTADO")) {
                                resultado = true;
                                request.setAttribute("validar_movimiento", resultado);
                            } else if (id_maquina == 1) {
                                resultado = jpa_movimiento.RegistroMovimientoHerramental(id_maquina, id_Herramental, id_tipoM, fecha, producto, lote, cavidad, cavidadDes, cavidades, "[" + nombre_Usuario + "/" + area + "]", descripcion_cavidadesT);
                                request.setAttribute("registro_movimiento", resultado);
                            } else {
                                resultado = jpa_movimiento.RegistroMovimiento(id_maquina, id_Herramental, id_tipoM, fecha, producto, lote, cavidad, cavidadDes, cavidades, descripcion, "[" + nombre_Usuario + "/" + area + "]", descripcion_cavidadesT, usa_firmas);
                                request.setAttribute("registro_movimiento", resultado);
                            }
                        } else if (id_maquina == 1) {
                            resultado = jpa_movimiento.RegistroMovimientoHerramental(id_maquina, id_Herramental, id_tipoM, fecha, producto, lote, cavidad, cavidadDes, cavidades, "[" + nombre_Usuario + "/" + area + "]", descripcion_cavidadesT);
                        } else {
                            resultado = jpa_movimiento.RegistroMovimiento(id_maquina, id_Herramental, id_tipoM, fecha, producto, lote, cavidad, cavidadDes, cavidades, descripcion, "[" + nombre_Usuario + "/" + area + "]", descripcion_cavidadesT, usa_firmas);
                        } //
                    } else {
                        resultado = jpa_movimiento.RegistroMovimiento(id_maquina, id_Herramental, id_tipoM, fecha, producto, lote, cavidad, cavidadDes, cavidades, descripcion, "[" + nombre_Usuario + "/" + area + "]", descripcion_cavidadesT, usa_firmas);
                        request.setAttribute("registro_movimiento", resultado);
                    }
                    if (id_tipoM == 2 || id_tipoM == 3) {
                        lst_movimiento = jpa_movimiento.ConsultaMovimientosIdMaquina(id_maquina);
                        mail.mail_Firma_Desmontado(lst_movimiento);
                    }
                    if (id_maquina == 1) {
                        request.getRequestDispatcher("Herramental?opc=5&idH=" + id_Herramental + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    } else {
                        if (id_tipoM == 2 || id_tipoM == 3) {
                            jpa_maquina.ModificarEstadoMaquina(id_maquina, 0);
                        } else if (id_tipoM == 4) {
                            if (cavidadDes > 0) {
                                jpa_maquina.ModificarEstadoMaquina(id_maquina, 2);
                            } else {
                                jpa_maquina.ModificarEstadoMaquina(id_maquina, 1);
                            }
                        }
                        if (id_tipoM == 1) {
                            jpa_maquina.ModificarEstadoMaquina(id_maquina, 3);
                            request.getRequestDispatcher("Maquina?opc=1&idM=0&txt_bus=" + filtro + "").forward(request, response);
                        } else {
                            request.getRequestDispatcher("Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                        }
                    }
                    break;
                case 3:
                    filtro = request.getParameter("txt_bus");
                    id_maquina = Integer.parseInt(request.getParameter("idM"));
                    id_movimiento = Integer.parseInt(request.getParameter("idMV"));
                    descripcion = request.getParameter("txt_descripcion");
                    fecha = request.getParameter("txt_fechaM");
                    if (id_maquina != 1) {
                        ids_herramental = request.getParameter("lst_herramentalM");
                        String[] idsHM = ids_herramental.split("-");
                        id_Herramental = Integer.parseInt(idsHM[0].toString());
                    } else {
                        id_Herramental = Integer.parseInt(request.getParameter("idH"));
                    }
                    id_tipoM = Integer.parseInt(request.getParameter("lst_TipoMM"));
                    producto = request.getParameter("slt_productoM");
                    if (producto == null) {
                        producto = "N/A";
                    }
                    lote = request.getParameter("txt_loteM");
                    if (lote == null) {
                        lote = "N/A";
                    }
                    try {
                        cavidad = Integer.parseInt(request.getParameter("txt_cavidadM"));
                    } catch (Exception ex) {
                        cavidad = 0;
                    }
                    try {
                        cavidadDes = Integer.parseInt(request.getParameter("txt_cavidadDM"));
                    } catch (Exception ex) {
                        cavidadDes = 0;
                    }
                    cavidades = request.getParameter("txt_cavidadesM");
                    if (cavidades == null) {
                        cavidades = "N/A";
                    }
                    descripcion_cavidadesT = request.getParameter("txt_descripcionC");
                    if (descripcion_cavidadesT == null) {
                        cavidades = "N/A";
                    }
                    lst_movimiento = jpa_movimiento.ConsultaMovimientosIdMovimiento(id_movimiento);
                    Object[] obj_mov = (Object[]) lst_movimiento.get(0);
                    if (obj_mov[14].toString().contains("[" + nombre_Usuario + "/" + area + "]")) {
                        resultado = jpa_movimiento.ModificarMovimiento(id_movimiento, id_Herramental, id_tipoM, fecha, producto, lote, cavidad, cavidadDes, cavidades, descripcion, obj_mov[14].toString(), descripcion_cavidadesT);
                    } else {
                        resultado = jpa_movimiento.ModificarMovimiento(id_movimiento, id_Herramental, id_tipoM, fecha, producto, lote, cavidad, cavidadDes, cavidades, descripcion, obj_mov[14].toString() + "[" + nombre_Usuario + "/" + area + "]", descripcion_cavidadesT);
                    }
                    if (resultado) {
                        request.setAttribute("modificar_movimiento", resultado);
                    } else {
                        request.setAttribute("modificar_movimiento", resultado);
                    }
                    if (id_maquina == 1) {
                        request.getRequestDispatcher("Herramental?opc=5&idH=" + id_Herramental + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    } else {
                        request.getRequestDispatcher("Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    }
                    break;
                case 4:
                    filtro = request.getParameter("txt_bus");
                    id_maquina = Integer.parseInt(request.getParameter("idM"));
                    id_movimiento = Integer.parseInt(request.getParameter("idMV"));
                    codigo = request.getParameter("txt_codigo").toString().trim();
                    try {
                        consecutivo = Integer.parseInt(request.getParameter("cons"));
                    } catch (Exception ex) {
                        consecutivo = 0;
                    }
                    request.setAttribute("productos", prod.Productos(codigo));
                    request.setAttribute("codigo", codigo);
                    request.getRequestDispatcher("Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + id_movimiento + "&idP=" + 0 + "&idS=" + 0 + "&cons=" + consecutivo + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 5:
                    filtro = request.getParameter("txt_bus");
                    id_maquina = Integer.parseInt(request.getParameter("idM"));
                    id_movimiento = Integer.parseInt(request.getParameter("idMV"));
                    descripcion = request.getParameter("txt_descripcion");
                    resultado = jpa_movimiento.ModificarMovimientoDescripcion(id_movimiento, descripcion);
                    if (resultado) {
                        request.setAttribute("modificar_movimiento_des", resultado);
                    } else {
                        request.setAttribute("modificar_movimiento_des", resultado);
                    }
                    if (id_maquina == 1) {
                        id_Herramental = Integer.parseInt(request.getParameter("idH"));
                        request.getRequestDispatcher("Herramental?opc=5&idH=" + id_Herramental + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    } else {
                        request.getRequestDispatcher("Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    }
                    break;
                case 6:
                    filtro = request.getParameter("txt_bus");
                    id_maquina = Integer.parseInt(request.getParameter("idM"));
                    id_movimiento = Integer.parseInt(request.getParameter("idMV"));
                    id_usuarios = request.getParameter("txt_usuarios");
                    lst_movimiento = jpa_movimiento.ConsultaMovimientosIdMovimiento(id_movimiento);
                    if (id_maquina == 1) {
                        resultado = mail.mail_Movimiento_Herramental(id_usuarios, lst_movimiento, nombre_Usuario);
                        if (resultado) {
                            request.setAttribute("Email_movimiento_her", resultado);
                        } else {
                            request.setAttribute("Email_movimiento_her", resultado);
                        }
                        id_Herramental = Integer.parseInt(request.getParameter("idH"));
                        request.getRequestDispatcher("Herramental?opc=5&idH=" + id_Herramental + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    } else {
                        resultado = mail.mail_Movimiento_maquina(id_usuarios, lst_movimiento, nombre_Usuario);
                        if (resultado) {
                            request.setAttribute("Email_movimiento_maq", resultado);
                        } else {
                            request.setAttribute("Email_movimiento_maq", resultado);
                        }
                        request.getRequestDispatcher("Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    }
                    break;
                case 7:
                    usa_firmas = request.getParameter("txt_resp");
                    try {
                        documento = Integer.parseInt(request.getParameter("txt_doc"));
                    } catch (Exception e) {
                        documento = 0;
                    }
                    try {
                        codUsa = Integer.parseInt(request.getParameter("txt_cod"));
                    } catch (Exception e) {
                        codUsa = 0;
                    }
                    id_maquina = Integer.parseInt(request.getParameter("idM"));
                    id_movimiento = Integer.parseInt(request.getParameter("idMV"));
                    id_tipoM = Integer.parseInt(request.getParameter("idTMV"));
                    valF = Integer.parseInt(request.getParameter("txt_val"));
                    filtro = request.getParameter("txt_bus");
                    request.setAttribute("txt_resp", usa_firmas);
                    request.setAttribute("txt_doc", documento);
                    request.setAttribute("txt_cod", codUsa);
                    request.setAttribute("idM", id_maquina);
                    request.setAttribute("idMV", id_movimiento);
                    request.setAttribute("idTMV", id_tipoM);
                    request.setAttribute("txt_bus", filtro);
                    request.setAttribute("txt_val", valF);
                    request.getRequestDispatcher("Movimiento?opc=1&idP=0&idS=0&cons=1").forward(request, response);
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
