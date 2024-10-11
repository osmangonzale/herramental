package Tags;

import controladores.HerramentalJpaController;
import controladores.MaquinaJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import controladores.FichaTecnicaJpaController;

public class Tag_menu extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession sesion = pageContext.getSession();
        String area = sesion.getAttribute("Area").toString();
        String id_rol = sesion.getAttribute("id_rol").toString();
        HerramentalJpaController jpa_herramental = new HerramentalJpaController();
        MaquinaJpaController jpa_maquina = new MaquinaJpaController();
        FichaTecnicaJpaController jpa_fichasTecnicas = new FichaTecnicaJpaController();
        List lst_Pherramentales = jpa_herramental.ConsultaPendientesHerramentales();
        List lst_Pmaquinas = jpa_maquina.ConsultaPendientesMaquinas();
        List lst_fichas = null;
        lst_fichas = jpa_fichasTecnicas.Contadores_pendientesFichas();
        int pend = 0;
        if (lst_fichas != null) {
            Object[] ob_fc = (Object[]) lst_fichas.get(0);
            pend = Integer.parseInt(ob_fc[1].toString());
        } else {
            pend = 0;
        }
        int cont = (lst_Pherramentales.size() + lst_Pmaquinas.size() + pend);
        int menu = 0;
        try {
            if (sesion.getAttribute("Nombre") != null || sesion.getAttribute("Rol") != null) {
                String nombre = sesion.getAttribute("Nombre").toString();
                String rol = sesion.getAttribute("Rol").toString();
                int id_usuario = Integer.parseInt(sesion.getAttribute("id").toString());
                // <editor-fold defaultstate="collapsed"  desc="cerrar session.">
                out.print("<div id='templatemo_header'>");
//                out.print("<div id='site_title'><div style='float: ;'><h1><a href='#' onclick='CerrarSesion();style='text-decoration: none;' ><b>" + rol + "/</b><b class='negro'>" + nombre.toUpperCase() + "</b></a></h1></div></div>");
                out.print("<div style='float:right; margin-top: 2%;'>"
                        + "<a class='btn-salir' onclick='CerrarSesion();'>"
                        + "<i class=\"fas fa-running\"></i>"
                        + "</a>"
                        + "</div>");

                out.print("<div id='' style='display: flex; height: 100%; align-items: center; '>");
                out.print("<div>");
//                out.print("<div style='float:left'><img src='Interfaz/Contenido/images/LogoTest4.fw.png' alt='logo' width='80px' height='80' style='margin-right: 15px;' /></div>");
                out.print("</div>");
                out.print("<div>");
                out.print("<img src='Interfaz/Contenido/images/logoT2.fw.png' alt='logo' style='margin-bottom: 15px;'/>");
                out.print("<h1 style='margin-left: 50px;'><a href='#' onclick='CerrarSesion();' style='text-decoration: none;' ><b>" + rol + "/</b><b class='negro'>" + nombre.toUpperCase() + "</b></a></h1>");
                out.print("</div>");
                out.print("</div>");

                out.print("</div>");
                // </editor-fold>
                out.print("<div id='templatemo_menu' class='ddsmoothmenu'>");
                // <editor-fold defaultstate="collapsed"  desc="Menu Admin">
                out.print("<div style='float:right;margin-top: 14px;margin-right: 10px;'>");
                out.print("<form action='Usuario?opc=5&idU=" + id_usuario + "' method='post' name='formRC' onsubmit='checkSubmit();'>");
                out.print("<input type='hidden' name='txt_pass'  id='pass-id' value=''>");
                out.print("<center><a href='#' onclick='contrasena()'><b style='color:#fff'>Cambiar contraseña</b></a></center>");
                out.print("</form>");
                out.print("</div>");;
                if (rol.equals("ADMINISTRADOR")) {
                    out.print("<ul>");
                    out.print("<li><a href='#'>Complementos</a>");
                    out.print("<ul>");
                    out.print("<li><a href='Usuario?opc=1&idU=" + 0 + "&txt_bus='>Usuarios</a></li>");
                    out.print("<li><a href='Herramental?opc=1&idH=" + 0 + "&txt_bus='>Herramental</a></li>");
                    out.print("<li><a href='Tipo_maquina?opc=1&idT=" + 0 + "&txt_bus='>Tipo maquina</a></li>");
                    out.print("<li><a href='Clasificacion_defecto?opc=1&idCD=" + 0 + "&txt_bus='>Clasificacion defecto</a></li>");
                    out.print("<li style='margin-bottom: 10px;'><a href='Ficha_tecnica?opc=9'>Fichas tecnicas de proceso</a></li>");
                    out.print("</ul>");
                    out.print("</li>");
                    out.print("<li><a href='Maquina?opc=1&idM=0&txt_bus='>Maquinas</a></li>");
                    out.print("<li><a href='#'>Programacion</a>");
                    out.print("<ul>");
                    out.print("<li><a href='Programacion?opc=1&idP=" + 0 + "&idTM=3&idNP=0'>Inyeccion</a></li>");
                    out.print("<li><a href='Programacion?opc=1&idP=" + 0 + "&idTM=2&idNP=0'>Extrusion PVC</a></li>");
                    out.print("<li><a href='Programacion?opc=1&idP=" + 0 + "&idTM=9&idNP=0'>Extrusion PP</a></li>");
                    out.print("<li><a href='Programacion?opc=1&idP=" + 0 + "&idTM=10&idNP=0'>Peletizado</a></li>");
                    out.print("<li><a href='Nota?opc=1'>Notas</a></li>");
                    out.print("</ul>");
                    out.print("</li>");
                    out.print("<li><a href='Familia_producto?opc=1&idF=" + 0 + "&txt_bus='>Catologo defectos</a></li>");
                    out.print("<div class='contenedor'>");
                    out.print("<li><a href='menu.jsp?menu=" + 1 + "'><span class='burbuja'>" + cont + "</span>Pendientes</a></li>");
                    out.print("</div>");
                    out.print("</ul>");
                    // </editor-fold>
                } else if (rol.equals("JEFE")) {
                    out.print("<ul>");
                    out.print("<li><a href='#'>Complementos</a>");
                    out.print("<ul>");
                    out.print("<li><a href='Herramental?opc=1&idH=" + 0 + "&txt_bus='>Herramental</a></li>");
                    out.print("<li><a href='Tipo_maquina?opc=1&idT=" + 0 + "&txt_bus='>Tipo maquina</a></li>");
                    out.print("<li><a href='Clasificacion_defecto?opc=1&idCD=" + 0 + "&txt_bus='>Clasificacion defecto</a></li>");
                    out.print("<li style='margin-bottom: 10px;'><a href='Ficha_tecnica?opc=9'>Fichas tecnicas de proceso</a></li>");
                    out.print("</ul>");
                    out.print("</li>");
                    out.print("<li><a href='Maquina?opc=1&idM=0&txt_bus='>Maquinas</a></li>");
                    out.print("<li><a href='#'>Programacion</a>");
                    out.print("<ul>");
                    out.print("<li><a href='Programacion?opc=1&idP=" + 0 + "&idTM=3&idNP=0'>Inyeccion</a></li>");
                    out.print("<li><a href='Programacion?opc=1&idP=" + 0 + "&idTM=2&idNP=0'>Extrusion PVC</a></li>");
                    out.print("<li><a href='Programacion?opc=1&idP=" + 0 + "&idTM=9&idNP=0'>Extrusion PP</a></li>");
                    out.print("<li><a href='Programacion?opc=1&idP=" + 0 + "&idTM=10&idNP=0'>Peletizado</a></li>");
                    if (area.equals("PRODUCCION INSUMOS")) {
                        if (Integer.parseInt(id_rol) == 1 || Integer.parseInt(id_rol) == 3) {
                            out.print("<li><a href='Nota?opc=1'>Notas</a></li>");
                        }
                    }
                    out.print("</ul>");
                    out.print("</li>");
                    out.print("<li><a href='Familia_producto?opc=1&idF=" + 0 + "&txt_bus='>Catologo defectos</a></li>");
                    out.print("<div class='contenedor'>");
                    out.print("<li><a href='menu.jsp?menu=" + 1 + "'><span class='burbuja'>" + cont + "</span>Pendientes</a></li>");
                    out.print("</div>");
                    out.print("</ul>");
                } else if (rol.equals("COLABORADOR") || rol.equals("CONSULTA")) {
                    out.print("<ul>");
                    out.print("<li><a href='#'>Complementos</a>");
                    out.print("<ul>");
                    out.print("<li><a href='Herramental?opc=1&idH=" + 0 + "&txt_bus='>Herramental</a></li>");
                    out.print("<li><a href='Tipo_maquina?opc=1&idT=" + 0 + "&txt_bus='>Tipo maquina</a></li>");
                    out.print("<li><a href='Clasificacion_defecto?opc=1&idCD=" + 0 + "&txt_bus='>Clasificacion defecto</a></li>");
                    out.print("<li style='margin-bottom: 10px;'><a href='Ficha_tecnica?opc=9'>Fichas tecnicas de proceso</a></li>");
                    out.print("</ul>");
                    out.print("</li>");
                    out.print("<li><a href='Maquina?opc=1&idM=0&txt_bus='>Maquinas</a></li>");
                    out.print("<li><a href='#'>Programacion</a>");
                    out.print("<ul>");
                    out.print("<li><a href='Programacion?opc=1&idP=" + 0 + "&idTM=3&idNP=0'>Inyeccion</a></li>");
                    out.print("<li><a href='Programacion?opc=1&idP=" + 0 + "&idTM=2&idNP=0'>Extrusion PVC</a></li>");
                    out.print("<li><a href='Programacion?opc=1&idP=" + 0 + "&idTM=9&idNP=0'>Extrusion PP</a></li>");
                    out.print("<li><a href='Programacion?opc=1&idP=" + 0 + "&idTM=10&idNP=0'>Peletizado</a></li>");
                    out.print("</ul>");
                    out.print("</li>");
                    out.print("<li><a href='Familia_producto?opc=1&idF=" + 0 + "&txt_bus='>Catologo defectos</a></li>");
                    out.print("<div class='contenedor'>");
                    out.print("<li><a href='menu.jsp?menu=" + 1 + "'><span class='burbuja'>" + cont + "</span>Pendientes</a></li>");
                    out.print("</div>");
                    out.print("</ul>");
                }
                //<editor-fold defaultstate="collapsed" desc="pendientes">
                out.print("<br style='clear: left'/>");
                out.print("</div>");
                out.print("<script type='text/javascript'>");
                out.print("function AlertPendiete() {");
                out.print("swal({");
                out.print("title:\"Pendientes\",");
                out.print("text:\"");
                out.print("<table class='table' style='width:100%;'>");
                out.print("<tr>");
                out.print("<td align='center'><b style='color:black;'>Herramentales</b></td>");
                out.print("<td align='center'><b style='color:black;'>" + lst_Pherramentales.size() + "</b></td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td align='center'><b style='color:black;'>Maquinas</b></td>");
                out.print("<td align='center'><b style='color:black;'>" + lst_Pmaquinas.size() + "</b></td>");
                out.print("</tr>");
                out.print("<tr>");
                lst_fichas = jpa_fichasTecnicas.Contadores_pendientesFichas();
                if (lst_fichas != null) {
                    Object[] obj_fichas = (Object[]) lst_fichas.get(0);
                    out.print("<td align='center'><b style='color:black;'>Fichas Tecnicas de Proceso</b></td>");
                    out.print("<td align='center'><b style='color:black;'>" + obj_fichas[1] + "</b></td>");
                } else {
                    out.print("<td align='center'><b style='color:black;'>Fichas Tecnicas de Proceso</b></td>");
                    out.print("<td align='center'><b style='color:black;'> 0 </b></td>");
                }
                out.print("</tr>");
                out.print("</table>");
                out.print("\",");
                out.print("html:\"true\",");
                out.print("type:\"warning\"");
                out.print("});");
                out.print("}");
                out.print("</script>");
                try {
                    menu = Integer.parseInt(pageContext.getRequest().getParameter("menu").toString());
//                    menu = Integer.parseInt(pageContext.getRequest().getAttribute("menu").toString());
                } catch (Exception ex) {
                    try {
                        menu = Integer.parseInt(pageContext.getRequest().getAttribute("menu").toString());
//                    menu = Integer.parseInt(pageContext.getRequest().getAttribute("menu").toString());
                    } catch (Exception e) {
                        menu = 0;
                    }
                }
                if (menu == 1) {
                    out.print("<div id='content_sin'>");
                    out.print("<br />");
                    out.print("<br />");
                    out.print("<button class='accordion'><b style='color:black;'>Herramentales</b><b style='color:black;float:right'>" + lst_Pherramentales.size() + "</b></button>");
                    out.print("<div class='panel' style='overflow:scroll;'>");
                    if (!lst_Pherramentales.isEmpty()) {
                        out.print("<table class='table' id='resultadosH' style='width:100%;'>");
                        for (int i = 0; i < lst_Pherramentales.size(); i++) {
                            Object[] obj_Pherramentales = (Object[]) lst_Pherramentales.get(i);
                            out.print("<tr>");
                            out.print("<td>" + obj_Pherramentales[3] + "</td>");
                            out.print("<td>" + obj_Pherramentales[2] + "</td>");
                            out.print("<td>" + obj_Pherramentales[5] + "</td>");
                            out.print("<td align='center'><a href='Herramental?opc=5&idH=" + obj_Pherramentales[1] + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus='><img src='Interfaz/Contenido/Iconos/History.png' width='22' height='22'></a></td>");
                            out.print("</tr>");
                        }
                        out.print("</table>");
                    } else {
                        out.print("<h3><b style='color:#292929'>No hay pendientes por herramental</b></h3>");
                    }
                    out.print("</div>");
                    out.print("<button class='accordion'><b style='color:black;'>Maquinas</b><b style='color:black;float:right'>" + lst_Pmaquinas.size() + "</b></button>");
                    out.print("<div class='panel' style='overflow:scroll;'>");
                    if (!lst_Pmaquinas.isEmpty()) {
                        out.print("<table class='table' id='resultadosH' style='width:100%;'>");
                        for (int i = 0; i < lst_Pmaquinas.size(); i++) {
                            Object[] obj_Pmaquinas = (Object[]) lst_Pmaquinas.get(i);
                            out.print("<tr>");
                            out.print("<td>" + obj_Pmaquinas[3] + "</td>");
                            out.print("<td>" + obj_Pmaquinas[2] + "</td>");
                            out.print("<td>" + obj_Pmaquinas[5] + "</td>");
                            out.print("<td align='center'><a href='Movimiento?opc=1&idM=" + obj_Pmaquinas[1] + "&idMV=" + 0 + "&txt_bus=&idP=" + 0 + "&idS=" + 0 + "'><img src='Interfaz/Contenido/Iconos/History.png' width='22' height='22'></a></td>");
                            out.print("</tr>");
                        }
                        out.print("</table>");
                    } else {
                        out.print("<h3><b style='color:#292929'>No hay pendientes por maquinas</b></h3>");
                    }
                    out.print("</div>");
                    lst_fichas = jpa_fichasTecnicas.Contadores_pendientesFichas();
                    if (lst_fichas != null) {
                        Object[] obj_fichas = (Object[]) lst_fichas.get(0);
                        out.print("<button class='accordion'><b style='color:black;'>Fichas Tecnicas </b><b style='color: black; float: right;'>" + obj_fichas[1] + "</b></button>");
                        out.print("<div class='panel' style='overflow: scroll;height: 46px;text-align: center;padding-top: 10px;'>");
                        out.print("<a href='Ficha_tecnica?opc=9'>Ingresar a pendientes de fichas tecnicas de proceso</a>");
                        out.print("</div>");
                    } else {
                        out.print("<button class='accordion'><b style='color:black;'>Fichas Tecnicas </b><b style='color: black; float: right;'> 0 </b></button>");
                        out.print("<div class='panel' style='overflow: scroll;height: 46px;text-align: center;padding-top: 10px;'>");
                        out.print("<a>No se han registrado pendientes</a>");
                        out.print("</div>");
                    }
                    out.print("<div class='cleaner'></div></div>");
                }
                //</editor-fold>
            } else {
                try {
                    pageContext.getRequest().getRequestDispatcher("index.jsp").forward(pageContext.getRequest(), pageContext.getResponse());
                } catch (ServletException ex) {
                    Logger.getLogger(Tag_menu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Tag_menu.class.getName()).log(Level.SEVERE, null, ex);
            try {
                pageContext.getRequest().getRequestDispatcher("index.jsp").forward(pageContext.getRequest(), pageContext.getResponse());
            } catch (ServletException ex1) {
                Logger.getLogger(Tag_menu.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (IOException ex1) {
                Logger.getLogger(Tag_menu.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return super.doStartTag();
    }
}
