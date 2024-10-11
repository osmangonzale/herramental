package Tags;

import controladores.HerramentalJpaController;
import controladores.MaquinaJpaController;
import controladores.MovimientoInyectoraJpaController;
import controladores.TipoHerramentalJpaController;
import controladores.UsuarioJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_herramental extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession sesion = pageContext.getSession();
        String area = sesion.getAttribute("Area").toString();
        HerramentalJpaController jpa_herramental = new HerramentalJpaController();
        MaquinaJpaController jpa_maquina = new MaquinaJpaController();
        UsuarioJpaController jpa_usuario = new UsuarioJpaController();
        List lst_maquina = jpa_maquina.ConsultaMaquinas();
        TipoHerramentalJpaController jpa_tipoH = new TipoHerramentalJpaController();
        MovimientoInyectoraJpaController jpa_movimiento = new MovimientoInyectoraJpaController();
        List lst_TipoM = jpa_movimiento.ConsultaMovimientos();
        List lst_herramentales = null;
        List lst_movimientos = null;
        List lst_herramental = null;
        List lst_pendientes = null;
        List lst_usuarios = null;
        List lst_TipoH = jpa_tipoH.ConsultaTipoHerramental();
        String filtro = (String) pageContext.getRequest().getAttribute("filtro");
        int id_herramental = Integer.parseInt(pageContext.getRequest().getAttribute("id_herramental").toString());
        try {
            if (Integer.parseInt(pageContext.getRequest().getAttribute("Modulo_pendientes").toString()) != 1) {
                lst_herramentales = (List) pageContext.getRequest().getAttribute("consulta_herramentales");
                out.print("<div id='sidebar'>");
                if (area.equals("SISTEMAS") || area.equals("PROYECTOS")) {
                    if (id_herramental == 0) {
                        //<editor-fold defaultstate="collapsed" desc="registrar herramental">
                        out.print("<h3>Nuevo herramental</h3>");
                        out.print("<form action='Herramental?opc=2' onsubmit='registroH();' method='post' id='form1'>");
                        out.print("<b>Cod.Herramental</b><br />");
                        out.print("<input type='text' name='txt_herramental' id ='herramental-id' placeholder='Codigo' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('herramental-id');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script>");
                        out.print("<b>Nombre Herramental</b><br />");
                        out.print("<textarea name='txt_descripcion' id ='txt_descripcion-id' placeholder='Nombre' onchange='javascript:this.value=this.value.toUpperCase();'></textarea><br />");
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('txt_descripcion-id');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script>");
                        out.print("<b>Tipo</b><br />");
                        out.print("<select name='lst_tipoH' id='select-id'>");
                        out.print("<option value='0' style='display:none;'>Seleccione tipo</option>");
                        for (int i = 0; i < lst_TipoH.size(); i++) {
                            Object[] obj_tipoH = (Object[]) lst_TipoH.get(i);
                            out.print("<option value='" + obj_tipoH[0] + "'>" + obj_tipoH[1] + "</option>");
                        }
                        out.print("</select><br />");
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('select-id');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script><br />");
                        out.print("<b>Maquina</b><br />");
                        out.print("<div id='NavPos'></div>");
                        out.print("<br />");
                        out.print("<table class='table' id='result' style='width:100%;'>");
                        out.print("<tr><td colspan='2'></td></tr>");
                        for (int i = 0; i < lst_maquina.size(); i++) {
                            Object[] obj_maquina = (Object[]) lst_maquina.get(i);
                            if ((Integer) obj_maquina[0] != 1) {
                                if (Integer.parseInt(obj_maquina[2].toString()) == 1) {
                                    out.print("<tr>");
                                    out.print("<td align='center'><input type='checkbox' id='selectM" + obj_maquina[0] + "' value='" + obj_maquina[0] + "' onchange='agregar(this,this.value)'></td>");
                                    out.print("<td align='center'><b style='color:black'>" + obj_maquina[1] + "</b></td>");
                                    out.print("</tr>");
                                } else {
                                    out.print("<tr>");
                                    out.print("<td align='center'><input type='checkbox' id='selectM" + obj_maquina[0] + "' value='" + obj_maquina[0] + "' disabled></td>");
                                    out.print("<td align='center'><b style='color:red'>" + obj_maquina[1] + "</b></td>");
                                    out.print("</tr>");
                                }
                            }
                        }
                        out.print("</table>");
                        out.print("<input type='hidden' name='txt_idM' id='id_maquinas'>");
                        out.print("<br />");
                        out.print("<input type='submit' id='btsubmit' value='Guardar'>");
                        out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "        </div>");
                        out.print("</form>");
                        out.print("<script type='text/javascript'>");
                        out.print("var pager2 = new Pager2('result',5);");
                        out.print("pager2.init();");
                        out.print("pager2.showPageNav('pager2','NavPos');");
                        out.print("pager2.showPage(1);");
                        out.print("</script>");
                        //</editor-fold>
                    } else {
                        //<editor-fold defaultstate="collapsed" desc="modificar herramental">
                        lst_herramental = jpa_herramental.ConsultaHerramentalId(id_herramental);
                        Object[] obj_herramental = (Object[]) lst_herramental.get(0);
                        String[] arg_maquina = obj_herramental[3].toString().replace("[", "").split("]");
                        out.print("<h3>Modificar herramental<a href='Herramental?opc=1&idH=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Delete.png' alt='Logo' width='18' height='18' title='Cancelar' /></a></h3>");
                        out.print("<form action='Herramental?opc=3&idH=" + id_herramental + "' onsubmit='registroH();' method='post' id='form1'>");
                        out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                        out.print("<b>Cod.Herramental</b><br />");
                        out.print("<input type='text' name='txt_herramentalM' id ='herramental-id' placeholder='Codigo' value='" + obj_herramental[4] + "' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('herramental-id');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script>");
                        out.print("<b>Nombre Herramental</b><br />");
                        out.print("<textarea name='txt_descripcion' id ='txt_descripcion-id' placeholder='Nombre' value='" + obj_herramental[9] + "' onchange='javascript:this.value=this.value.toUpperCase();'>" + obj_herramental[9] + "</textarea><br />");
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('txt_descripcion-id');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script>");
                        out.print("<b>Tipo</b><br />");
                        out.print("<select name='lst_tipoHM' id='select-id'>");
                        out.print("<option value='" + obj_herramental[1] + "' style='display:none;'>" + obj_herramental[2] + "</option>");
                        for (int i = 0; i < lst_TipoH.size(); i++) {
                            Object[] obj_tipoH = (Object[]) lst_TipoH.get(i);
                            out.print("<option value='" + obj_tipoH[0] + "'>" + obj_tipoH[1] + "</option>");
                        }
                        out.print("</select><br />");
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('select-id');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script><br /><br />");
                        out.print("<b>Maquina</b><br />");
                        out.print("<div id='NavPos'></div>");
                        out.print("<br />");
                        out.print("<table class='table' id='result' style='width:100%;'>");
                        out.print("<tr><td colspan='2'></td></tr>");
                        for (int i = 0; i < lst_maquina.size(); i++) {
                            Object[] obj_maquina = (Object[]) lst_maquina.get(i);
                            out.print("<tr>");
                            if ((Integer) obj_maquina[0] != 1) {
                                if (Integer.parseInt(obj_maquina[2].toString()) == 1) {
                                    for (int k = 0; k < arg_maquina.length; k++) {
                                        if (!arg_maquina[k].toString().equals("") && Integer.parseInt(arg_maquina[k].toString()) == (Integer) obj_maquina[0]) {
                                            out.print("<td align='center'><input type='checkbox' id='selectM" + obj_maquina[0] + "' value='" + obj_maquina[0] + "' onchange='agregar(this,this.value)'  checked></td>");
                                            k = arg_maquina.length;
                                        } else if (k == (arg_maquina.length - 1)) {
                                            out.print("<td align='center'><input type='checkbox' id='selectM" + obj_maquina[0] + "' value='" + obj_maquina[0] + "' onchange='agregar(this,this.value)'></td>");
                                        }
                                    }
                                    out.print("<td align='center'><b style='color:black'>" + obj_maquina[1] + "</b></td>");
                                } else {
                                    for (int k = 0; k < arg_maquina.length; k++) {
                                        if (!arg_maquina[k].toString().equals("") && Integer.parseInt(arg_maquina[k].toString()) == (Integer) obj_maquina[0]) {
                                            out.print("<td align='center'><input type='checkbox' id='selectM" + obj_maquina[0] + "' value='" + obj_maquina[0] + "' checked></td>");
                                        } else if (k == (arg_maquina.length - 1)) {
                                            out.print("<td align='center'><input type='checkbox' id='selectM" + obj_maquina[0] + "' value='" + obj_maquina[0] + "' onchange='agregar(this,this.value)'></td>");
                                        }
                                    }
                                    out.print("<td align='center'><b style='color:red'>" + obj_maquina[1] + "</b></td>");
                                }
                            }
                            out.print("</tr>");
                        }
                        out.print("</table>");
                        out.print("<script type='text/javascript'>");
                        out.print("var Pager2 = new Pager2('result',4);");
                        out.print("Pager2.init();");
                        out.print("Pager2.showPageNav('Pager2','NavPos');");
                        out.print("Pager2.showPage(1);");
                        out.print("</script>");
                        out.print("<input type='hidden' name='txt_idMM' value='" + obj_herramental[3] + "' id='id_maquinas'>");
                        out.print("<br />");
                        out.print("<input type='submit' id='btsubmit' value='Guardar'>");
                        out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "        </div>");
                        out.print("</form>");
                        //</editor-fold>
                    }
                } else {
                    out.print("<h3>Nuevo herramental</h3>");
                    out.print("<div align='center'><img src='Interfaz/Contenido/Iconos/Alert.png' alt='Logo' width='55' height='55.5' /><br /><b>No se permite <br />el registro</b></div>");
                }
                out.print("<div class='cleaner'></div></div>");
                out.print("<div id='content'>");
                //<editor-fold defaultstate="collapsed" desc="consulta herramental">
                out.print("<div style='float:right;'>");
                out.print("<form method='post' action='Herramental?opc=1&idH=" + 0 + "'>");
                out.print("<input name='txt_bus' type='text' class='input_field' placeholder='Buscar'><br/>");
                out.print("</form>");
                out.print("</div>");
                if (!filtro.equals("")) {
                    out.print("<a href='Herramental?opc=1&idH=" + 0 + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a>");
                }
                out.print("<h3>Herramental</h3>");
                if (!lst_herramentales.isEmpty()) {
                    out.print("<div id='NavPosicion'></div>");
                    out.print("<table class='table' id='resultados' style='width:100%;'>");
                    for (int i = 0; i < lst_herramentales.size(); i++) {
                        Object[] obj_herramentales = (Object[]) lst_herramentales.get(i);
                        if ((Integer) obj_herramentales[0] != 1) {
                            out.print("<tr>");
                            out.print("<td colspan='9'></td>");
                            out.print("</tr>");
                            if ((Integer) obj_herramentales[5] == 1) {
                                out.print("<tr>");
                                out.print("<td align='center'><b>" + obj_herramentales[4] + "</b></td>");
                                out.print("<td align='center'>" + obj_herramentales[9] + "</td>");
                                out.print("<td align='center'><b>Tipo: </b>" + obj_herramentales[2] + "</td>");
                                out.print("<td align='center'><b>Maquina: </b><br />");
                                for (int j = 0; j < lst_maquina.size(); j++) {
                                    Object[] obj_maquina = (Object[]) lst_maquina.get(j);
                                    if ((Integer) obj_maquina[0] != 1) {
                                        if (obj_herramentales[3].toString().contains("[" + obj_maquina[0] + "]")) {
                                            out.print("" + obj_maquina[1] + "<br/>");
                                        }
                                    }
                                }
                                out.print("</td>");
                                out.print("<td align='center'><b>Ultimo movimiento: </b>" + obj_herramentales[8] + "</td>");
                                if (area.equals("SISTEMAS") || area.equals("PROYECTOS")) {
                                    out.print("<td align='center'><a href='Herramental?opc=1&idH=" + obj_herramentales[0] + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='Herramental?opc=5&idH=" + obj_herramentales[0] + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/History.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='Informe?opc=1&idH=" + obj_herramentales[0] + "&idI=0&Cons=0'><img src='Interfaz/Contenido/Iconos/Doc.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='Herramental?opc=4&idH=" + obj_herramentales[0] + "&est=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Check.png' width='22' height='22'></a></td>");
                                } else {
                                    out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='Herramental?opc=5&idH=" + obj_herramentales[0] + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/History.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='Informe?opc=1&idH=" + obj_herramentales[0] + "&idI=0&Cons=0'><img src='Interfaz/Contenido/Iconos/Doc.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
                                }
                                out.print("</tr>");
                            } else {
                                out.print("<tr>");
                                out.print("<td align='center'><b style='color:red'>" + obj_herramentales[4] + "</b></td>");
                                out.print("<td align='center'>" + obj_herramentales[9] + "</td>");
                                out.print("<td align='center'><b>Tipo: </b><b style='color:red'>" + obj_herramentales[2] + "</b></td>");
                                out.print("<td align='center'><b>Maquina: </b><br />");
                                for (int j = 0; j < lst_maquina.size(); j++) {
                                    Object[] obj_maquina = (Object[]) lst_maquina.get(j);
                                    if (obj_herramentales[3].toString().contains("[" + obj_maquina[0] + "]")) {
                                        out.print("<b style='color:red'>" + obj_maquina[1] + "</b><br/>");
                                    }
                                }
                                out.print("</td>");
                                out.print("<td align='center'><b>Ultimo movimiento: </b>" + obj_herramentales[8] + "</td>");
                                if (area.equals("COORDINADOR PRODUCCION INSUMOS") || area.equals("INSPECTOR CALIDAD") || area.equals("CONSULTA")) {
                                    out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='Herramental?opc=5&idH=" + obj_herramentales[0] + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/History.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
                                } else {
                                    out.print("<td align='center'><a href='Herramental?opc=1&idH=" + obj_herramentales[0] + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='Herramental?opc=5&idH=" + obj_herramentales[0] + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/History.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='Herramental?opc=4&idH=" + obj_herramentales[0] + "&est=" + 1 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22'></a></td>");
                                }
                                out.print("</tr>");
                            }
                        }
                    }
                    out.print("</table>");
                    out.print("<script type='text/javascript'>");
                    out.print("var pager = new Pager('resultados',30);");
                    out.print("pager.init();");
                    out.print("pager.showPageNav('pager','NavPosicion');");
                    out.print("pager.showPage(1);");
                    out.print("</script>");
                } else {
                    out.print("<h3><b style='color:#292929'>No se han encontrado resultados</b></h3>");
                }
                //</editor-fold>
                out.print("<div class='cleaner'></div></div>");
            }
            if (Integer.parseInt(pageContext.getRequest().getAttribute("Modulo_pendientes").toString()) == 1) {
                lst_movimientos = jpa_herramental.ConsultaMovimientosIdHerramental(id_herramental);
                int id_pendiente = Integer.parseInt(pageContext.getRequest().getAttribute("id_pendiente").toString());
                int id_movimiento = Integer.parseInt(pageContext.getRequest().getAttribute("id_movimiento").toString());
                int solucion = Integer.parseInt(pageContext.getRequest().getAttribute("solucion").toString());
                lst_pendientes = jpa_herramental.ConsultaPendientesIdHerramental(id_herramental);
                lst_herramental = jpa_herramental.ConsultaHerramentalId(id_herramental);
                Object[] obj_herramental = (Object[]) lst_herramental.get(0);
                lst_usuarios = jpa_usuario.ConsultaCargos();
                out.print("<div id='content_sin'>");
                out.print("<a href='Herramental?opc=1&idH=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a>");
                out.print("<h3 style='float:right'><b>Herramental: </b><b style='color:#292929'>" + obj_herramental[4] + " / " + obj_herramental[9] + "</b></h3><br />");
                out.print("<div id='tab-container' style='margin-top: 10px;'>");
                //<editor-fold defaultstate="collapsed" desc="enviar email movimientos">
                out.print("<div style='display:none;position:absolute;float:right;width:300px;font-size:14px;right:140px;background-color:#fff;' id=\"toggleM\">");
                out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:290px;padding: 20px 0 20px 10px;'>");
                out.print("<h3>Seleccionar Cargos</h3>");
                out.print("<form action='Movimiento?opc=6' method='post' name='formMailM' onsubmit='MailM();'>");
                out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                out.print("<input type='hidden' name='idMV' id='idMV' value=''>");
                out.print("<input type='hidden' name='idH' value='" + obj_herramental[0] + "'>");
                out.print("<input type='hidden' name='idM' value='" + 1 + "'>");
                out.print("<input type='hidden' value='' id='usuario-id' name='txt_usuarios'>");
                out.print("<table class='table' id='resultados3' style='width:100%'>");
                for (int j = 0; j < lst_usuarios.size(); j++) {
                    Object[] obj_usuarios = (Object[]) lst_usuarios.get(j);
                    out.print("<tr>");
                    out.print("<td><b style='color:#292929;'>" + obj_usuarios[1] + "</b></td>");
                    out.print("<td align='center'><input type='checkbox' value='" + obj_usuarios[0] + "' onclick='agregarUM(this, this.value)'></td>");
                    out.print("</tr>");
                }
                out.print("</table><br />");
                out.print("<input type='submit' value='Enviar' onclick='CorreoM()' id='botonMa'/>");
                out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntosMa'>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "        </div>");
                out.print("</form>");
                out.print("<div class='cleaner'></div></div>");
                out.print("</div>");
                //</editor-fold>
                if (id_pendiente == 0) {
                    if (area.equals("SISTEMAS") || area.equals("PROYECTOS")) {
                        if (id_movimiento == 0) {
                            //<editor-fold defaultstate="collapsed" desc="registrar movimiento">
                            out.print("<div class='tab-content'>");
                            out.print("<h1 class='tab' title='Historial'>Historial</h1>");
                            if (!lst_movimientos.isEmpty()) {
                                Object[] obj_movimientos = (Object[]) lst_movimientos.get(0);
                                if (obj_movimientos[13] != null) {
                                    out.print("<img id='Menu_registro' style='float:left;position: absolute;'  src='Interfaz/Contenido/Iconos/Menu.png' width='20px' height='20px' alt='edit' title='Buscar' />");
                                } else {
                                    out.print("<img id='Menu_block' style='float:left;position: absolute;'  src='Interfaz/Contenido/Iconos/Menu.png' width='20px' height='20px' alt='edit' title='Buscar' />");
                                }
                            } else {
                                out.print("<img id='Menu_registro' style='float:left;position: absolute;'  src='Interfaz/Contenido/Iconos/Menu.png' width='20px' height='20px' alt='edit' title='Buscar' />");
                            }
                            out.print("<script>");
                            out.print("$(Menu_registro).click(function() {");
                            out.print("$(\"#toggle\").toggle(\"slide\");");
                            out.print("});");
                            out.print("</script>");
                            out.print("<div style='display:none;' id=\"toggle\">");
                            out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:229px;padding: 20px 0 20px 10px;'>");
                            out.print("<h3>Registrar Movimiento</h3>");
                            out.print("<form action='Movimiento?opc=2' method='post' onsubmit='registroM();' name='formM'>");
                            out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                            out.print("<input type='hidden' name='lst_herramental' value='" + id_herramental + "'>");
                            out.print("<b>Fecha:</b><br />");
                            out.print("<input type='text' name='txt_fecha' id='datepicker' placeholder='fecha' autocomplete='off'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('datepicker');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script><br />");
                            out.print("<b>Movimiento</b><br />");
                            out.print("<select name='lst_TipoM' id='selectTM-id'>");
                            out.print("<option value='0' style='display:none;'>Seleccione Movimiento</option>");
                            for (int i = 0; i < lst_TipoM.size(); i++) {
                                Object[] obj_movimientos = (Object[]) lst_TipoM.get(i);
                                if ((Integer) obj_movimientos[0] != 1) {
                                    out.print("<option value='" + obj_movimientos[0] + "'>" + obj_movimientos[1] + "</option>");
                                }
                            }
                            out.print("</select><br /><br />");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('selectTM-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</table>");
                            out.print("<input type='submit' value='Guardar' id='botonM' />");
                            out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntosM'>\n"
                                    + "          <div></div>\n"
                                    + "          <div></div>\n"
                                    + "          <div></div>\n"
                                    + "        </div>");
                            out.print("</form>");
                            out.print("<div class='cleaner'></div></div>");
                            out.print("</div>");
                            out.print("<br/>");
                            out.print("<br/>");
//</editor-fold>
                        } else {
                            //<editor-fold defaultstate="collapsed" desc="modificar movimiento">
                            List lst_movimiento = jpa_movimiento.ConsultaMovimientosIdMovimiento(id_movimiento);
                            Object[] obj_movimiento = (Object[]) lst_movimiento.get(0);
                            out.print("<div class='tab-content'>");
                            out.print("<h1 class='tab tab-active' title='Historial'>Historial</h1>");
                            out.print("<a href='Herramental?opc=5&idH=" + obj_movimiento[3] + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img id='Menu_registro' style='float:left;position: absolute;'  src='Interfaz/Contenido/Iconos/Delete.png' width='20px' height='20px' alt='edit' title='Buscar' /></a>");
                            out.print("<br/>");
                            out.print("<br/>");
                            out.print("<div style='display:block;' id=\"toggle2\">");
                            out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                            out.print("<h3>Modificar movimiento</h3>");
                            out.print("<table  style='width:100%;border:none;font-size: 11px;'>");
                            out.print("<form action='Movimiento?opc=3' method='post' name='formM' onsubmit='registroM();'>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Fecha:</b><br />");
                            out.print("<input type='text' name='txt_fechaM' value='" + obj_movimiento[7] + "' id=\"datepicker\" placeholder='fecha'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('datepicker');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("<b>Movimiento</b><br />");
                            out.print("<select name='lst_TipoMM' id='selectM-id'>");
                            out.print("<option value='" + obj_movimiento[5] + "' style='display:none;'>" + obj_movimiento[6] + "</option>");
                            for (int i = 0; i < lst_TipoM.size(); i++) {
                                Object[] obj_movimientos = (Object[]) lst_TipoM.get(i);
                                out.print("<option value='" + obj_movimientos[0] + "'>" + obj_movimientos[1] + "</option>");
                            }
                            out.print("</select>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('selectM-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td colspan='2'>");
                            out.print("<b>Descripcion:</b><br />");
                            String des = obj_movimiento[13].toString().replace("<div>", "<div contenteditable='true'>");
                            out.print("<textarea id='editor' name='txt_descripcionM' style='width: 500px; height: 400px' placeholder='descripcion'>" + des + "</textarea>");
//                            out.print("<textarea id='editor' name='txt_descripcionM' style='width: 500px; height: 400px' placeholder='descripcion'>" + des + "</textarea>");
                            out.print("<input type='hidden' name='idM' value='" + obj_movimiento[1] + "'>");
                            out.print("<input type='hidden' name='idMV' value='" + id_movimiento + "'>");
                            out.print("<input type='hidden' name='idH' value='" + obj_movimiento[3] + "'>");
                            out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td><input type='submit' value='Guardar' onclick='MovimientoM()' id='botonM' />");
                            out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntosM'>\n"
                                    + "          <div></div>\n"
                                    + "          <div></div>\n"
                                    + "          <div></div>\n"
                                    + "        </div>");
                            out.print("</td></tr>");
                            out.print("</form>");
                            out.print("</table>");
                            out.print("<div class='cleaner'></div></div>");
                            out.print("</div>");
                            //</editor-fold>
                        }
                    } else {
                        out.print("<div class='tab-content'>");
                        out.print("<h1 class='tab' title='Historial'>Historial</h1>");
                    }
                    if (!lst_movimientos.isEmpty()) {
                        //<editor-fold defaultstate="collapsed" desc="hostrial movimientos">
                        out.print("<div id='NavPosicion'></div>");
                        out.print("<table class='table' style='width:100%' id='resultados'>");
                        out.print("<tr>");
                        out.print("<th style='width: 7%;'>Fecha / Maquina</th>");
                        out.print("<th style='width: 16%;'>Producto / Lote</th>");
                        out.print("<th style='width: 12%;'>Cavidades/<br />Deshabilitadas</th>");
                        out.print("<th>Descripcion</th>");
                        out.print("<th style='width: 12%;'>Estado</th>");
                        out.print("<th style='width: 9%;'>Responsable</th>");
                        for (int i = 0; i < lst_movimientos.size(); i++) {
                            Object[] obj_movimientos = (Object[]) lst_movimientos.get(i);
                            if (i == 0) {
                                if (area.equals("SISTEMAS") || area.equals("PROYECTOS")) {
                                    if (obj_movimientos[13] != null) {
                                        if ((Integer) obj_movimientos[1] == 1) {
                                            out.print("<td align='center'><a href='Movimiento?opc=1&idM=" + obj_movimientos[1] + "&idMV=" + obj_movimientos[0] + "&idH=" + obj_movimientos[3] + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                                            out.print("<td align='center'><a href='#' onclick='idMV(" + obj_movimientos[0] + ")'><img id='mail_movimientos' src='Interfaz/Contenido/Iconos/Mail.png' width='22px' height='15px'></a></td>");
                                        } else {
                                            out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22' title='Editar'></a></td>");
                                            out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22' title='Enviar'></a></td>");
                                        }
                                    } else {
                                        out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22' title='Editar'></a></td>");
                                        out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22' title='Enviar'></a></td>");
                                    }
                                } else {
                                    out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22' title='Editar'></a></td>");
                                    out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22' title='Enviar'></a></td>");
                                }
                                out.print("</tr>");
                            }
                            out.print("<tr>");
                            out.print("<td valign='top'>" + obj_movimientos[7] + "<hr/>");
                            out.print("" + obj_movimientos[2] + "</td>");
                            out.print("<td valign='top'>" + obj_movimientos[8] + "<hr/>");
                            out.print("" + obj_movimientos[9] + "</td>");
                            out.print("<td valign='top'><b title='Numero cavidades'>#: </b>" + obj_movimientos[10] + "<hr/>");
                            out.print("<b title='Numero cavidades deshabilitadas'>#: </b>" + obj_movimientos[11] + "<hr/>");
                            out.print("<p title='cavidades'>" + obj_movimientos[12] + "</p>");
                            if (obj_movimientos[16] == null) {
                                out.print("<b>descripcion des-habilitadas</b><p title='cavidades'>N/A</p></td>");
                            } else {
                                out.print("<b>descripcion des-habilitadas</b><p title='cavidades'>" + obj_movimientos[16] + "</p></td>");
                            }
                            if (area.equals("SISTEMAS") || area.equals("PROYECTOS")) {
                                if (obj_movimientos[13] != null) {
                                    out.print("<td valign='top' style='text-transform: none;'>" + obj_movimientos[13] + "</td>");
                                } else if ((Integer) obj_movimientos[1] != 1) {
                                    out.print("<td valign='top' style='text-transform: none;'><b style='color:#292929'>No se ha registrado la descripcion</b></td>");
                                } else {
//                                    out.print("<td valign='top'><a href='#' onclick='Prueba()'><img src='Interfaz/Contenido/Iconos/Save.png' width='15' height='15'></a>");
                                    out.print("<td valign='top'><a href='#' onclick='Movimiento()'><img src='Interfaz/Contenido/Iconos/Save.png' width='15' height='15'></a>");
                                    out.print("<form action='Movimiento?opc=5'  method='post' id='formDes' name='formDes'>");
                                    out.print("<input type='hidden' name='idH' value='" + obj_movimientos[3] + "'>");
                                    out.print("<input type='hidden' name='idMV' value='" + obj_movimientos[0] + "'>");
                                    out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                                    out.print("<input type='hidden' name='idM' value='" + obj_movimientos[1] + "'>");
                                    out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 120'><div contenteditable='true'><p>*</p></div></textarea></td>");
//                                    out.print("<textarea id='descripcion-id' name='txt_descripcion' style='width: 500px; height: 400px' placeholder='descripcion'>"
//                                            + "<strong>Descripci&oacute;n:</strong><br /><div contenteditable='true'><p>*</p></div>"
//                                            + "<hr /><strong>Programacion: </strong><div contenteditable='true'><p>*</p></div></textarea></td>");
                                    out.print("</form>");
                                }
                            } else if (obj_movimientos[13] != null) {
                                out.print("<td valign='top' style='text-transform: none;'>" + obj_movimientos[13] + "</td>");
                            } else if ((Integer) obj_movimientos[1] != 1) {
                                out.print("<td valign='top' style='text-transform: none;'><b style='color:#292929'>No se ha registrado la descripcion</b></td>");
                            }

                            out.print("<td valign='top'>" + obj_movimientos[6] + "</td>");
                            out.print("<td valign='top' colspan='3'>" + obj_movimientos[14] + "</td>");
                            out.print("</tr>");
                        }
                        out.print("</table>");
                        out.print("<script type='text/javascript'>");
                        out.print("var pager = new Pager('resultados',12);");
                        out.print("pager.init();");
                        out.print("pager.showPageNav('pager','NavPosicion');");
                        out.print("pager.showPage(1);");
                        out.print("</script>");
                        //</editor-fold>
                    } else {
                        out.print("<h3><b style='color:#292929'>No se han encontrado resultados</b></h3>");
                    }
                    out.print("</div>");
                }
                //<editor-fold defaultstate="collapsed" desc="enviar email Pendientes">
                out.print("<div style='display:none;position:absolute;float:right;width:300px;font-size:14px;right:140px;background-color:#fff;' id='toggleP'>");
                out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:290px;padding: 20px 0 20px 10px;'>");
                out.print("<h3>Seleccionar Cargos</h3>");
                out.print("<form action='Herramental?opc=8' method='post' name='formMailP' onsubmit='MailP();'>");
                out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                out.print("<input type='hidden' name='idP' id='idP' value=''>");
                out.print("<input type='hidden' name='idH' value='" + id_herramental + "'>");
                out.print("<input type='hidden' name='est' id='estP' value=''>");
                out.print("<input type='hidden' value='' id='usuario-idP' name='txt_usuarios'>");
                out.print("<table class='table' style='width:100%'>");
                for (int j = 0; j < lst_usuarios.size(); j++) {
                    Object[] obj_usuarios = (Object[]) lst_usuarios.get(j);
                    out.print("<tr>");
                    out.print("<td><b style='color:#292929;'>" + obj_usuarios[1] + "</b></td>");
                    out.print("<td align='center'><input type='checkbox' value='" + obj_usuarios[0] + "' onclick='agregarUP(this, this.value)'></td>");
                    out.print("</tr>");
                }
                out.print("</table><br />");
                out.print("<input type='submit' value='Enviar' onclick='CorreoP();' id='botonPe'/>");
                out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntosPe'>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "        </div>");
                out.print("</form>");
                out.print("<div class='cleaner'></div></div>");
                out.print("</div>");
//                //</editor-fold>
                out.print("<div class='tab-content'>");
                out.print("<h1 class='tab' title='Pendientes'>Pendientes</h1>");
                if (id_pendiente == 0) {
                    //<editor-fold defaultstate="collapsed" desc="registrar pendiente">
                    if (id_movimiento == 0) {
//                            Object[] obj_movimientos = (Object[]) lst_movimientos.get(0);
//                            if (obj_movimientos[13] != null) {
                                out.print("<img id='Menu_registro' style='float:left;position: absolute;'  src='Interfaz/Contenido/Iconos/Plus.png' width='20px' height='20px' alt='edit' title='Buscar' />");
//                            } else {
//                                out.print("<img style='float:left;position: absolute;opacity:0.5;'  src='Interfaz/Contenido/Iconos/Plus.png' width='20px' height='20px' alt='edit' title='No permitir registrar hasta que se finalice el historial' />");
//
//                            }
                        out.print("<br/>");
                        out.print("<br/>");
                        out.print("<script>");
                        out.print("$(Menu_registro).click(function() {");
                        out.print("$(\"#toggle3\").toggle(\"slide\");");
                        out.print("});");
                        out.print("</script>");
                        out.print("<div style='display:none;' id=\"toggle3\">");
                        out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                        out.print("<h3>Nuevo Pendiente</h3>");
                        out.print("<form action='Herramental?opc=6' method='post' name='formM' onsubmit='registroP();'>");
                        out.print("<input type='hidden' name='idH' value='" + id_herramental + "'>");
                        out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                        out.print("<b>Fecha:</b><br />");
                        out.print("<input type='text' name='txt_fecha' id=\"datepicker\" placeholder='fecha'>");
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('datepicker');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script><br />");
                        out.print("<b>Descripcion:</b><br />");
//                        out.print("<textarea id='descripcion-id' name='txt_descripcionM' style='width: 500px; height: 400px' placeholder='descripcion'>");
                        out.print("<textarea id='editor' name='txt_descripcionM' style='width: 500px; height: 400px;' placeholder='descripcion'>");
                        out.print("<b>Cavidades: </b>");
                        out.print("<div contenteditable='true'><p>*</p></div>");
                        out.print("<b>Cavidades des-habilitadas: </b>");
                        out.print("<div contenteditable='true'><p>*</p></div>");
                        out.print("<hr/><b>Causas: </b><br />");
                        out.print("<div contenteditable='true'><p>*</p></div>");
                        out.print("<hr/><b>Sugerencias: </b><br />");
                        out.print("<div contenteditable='true'><p>*</p></div>");
                        out.print("</textarea>");
                        out.print("<script>"
                                + "     CKEDITOR.replace(\"editor\");"
                                + "</script>");
                        out.print("<input type='submit' value='Guardar' onclick='Movimiento()' id='botonP'/>");
                        out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntosP'>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "        </div>");
                        out.print("</form>");
                        out.print("<div class='cleaner'></div></div>");
                        out.print("</div>");
                    }
                    //</editor-fold>
                } else {
                    //<editor-fold defaultstate="collapsed" desc="modificar pendiente">
                    List lst_pendiente = jpa_herramental.ConsultaPendientesId(id_pendiente);
                    Object[] obj_pendiente = (Object[]) lst_pendiente.get(0);
                    out.print("<a href='Herramental?opc=5&idH=" + id_herramental + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img id='Menu_registro' style='float:left;position: absolute;'  src='Interfaz/Contenido/Iconos/Delete.png' width='20px' height='20px' alt='edit' title='Buscar' /></a>");
                    out.print("<br/>");
                    out.print("<br/>");
                    if (solucion == 1) {
                        out.print("<div style='display:block;margin-top:0px' id=\"toggle3\">");
                        out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                        out.print("<h3>Solucionar Pendiente</h3>");
                        out.print("<form action='Herramental?opc=9' method='post' name='formS' onsubmit='registroP();'>");
                        out.print("<input type='hidden' name='idP' value='" + id_pendiente + "'>");
                        out.print("<input type='hidden' name='idH' value='" + id_herramental + "'>");
                        out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                        out.print("<b>Fecha:</b><br />");
                        out.print("<input type='text' name='txt_fecha' id=\"datepicker\" placeholder='fecha'>");
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('datepicker');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script><br />");
                        out.print("<b>Descripcion:</b><br />");
                        out.print("<textarea id='editor' name='txt_solucion' style='width: 500px; height: 400px' placeholder='descripcion'><div contenteditable='true'><p>*</p></div></textarea>");
                        out.print("<input type='submit' value='Guardar' onclick='SolucionP()' id='botonP'/>");
                        out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntosP'>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "        </div>");
                        out.print("</form>");
                        out.print("<div class='cleaner'></div></div>");
                        out.print("</div>");
                    } else if (solucion == 2) {
                        out.print("<div style='display:block;margin-top:0px' id=\"toggle3\">");
                        out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                        out.print("<h3>Modificar solucion</h3>");
                        out.print("<form action='Herramental?opc=9' method='post' name='formSM' onsubmit='registroP();'>");
                        out.print("<input type='hidden' name='idP' value='" + id_pendiente + "'>");
                        out.print("<input type='hidden' name='idH' value='" + id_herramental + "'>");
                        out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                        out.print("<b>Fecha:</b><br />");
                        out.print("<input type='text' name='txt_fecha' value='" + obj_pendiente[7] + "' id=\"datepicker\" placeholder='fecha'>");
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('datepicker');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script><br />");
                        out.print("<b>Descripcion:</b><br />");
                        String sol = obj_pendiente[8].toString().replace("<div>", "<div contenteditable='true'>");
                        out.print("<textarea id='editor' name='txt_solucion' style='width: 500px; height: 400px' placeholder='descripcion'>" + sol + "</textarea>");
                        out.print("<input type='submit' value='Guardar' onclick='SolucionPM()' id='botonP'/>");
                        out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntosP'>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "        </div>");
                        out.print("</form>");
                        out.print("<div class='cleaner'></div></div>");
                        out.print("</div>");
                    } else {
                        out.print("<div style='display:block;' id=\"toggle3\">");
                        out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                        out.print("<h3>Modificar Pendiente</h3>");
                        out.print("<form action='Herramental?opc=7' method='post' name='formM' onsubmit='registroP();'>");
                        out.print("<input type='hidden' name='idP' value='" + id_pendiente + "'>");
                        out.print("<input type='hidden' name='idH' value='" + id_herramental + "'>");
                        out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                        out.print("<b>Fecha:</b><br />");
                        out.print("<input type='text' name='txt_fechaM' value='" + obj_pendiente[3] + "' id=\"datepicker\" placeholder='fecha'>");
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('datepicker');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script><br />");
                        out.print("<b>Descripcion:</b><br />");
                        String des = obj_pendiente[4].toString().replace("<div>", "<div contenteditable='true'>");
                        out.print("<textarea id='editor' name='txt_descripcionM' style='width: 500px; height: 400px' placeholder='descripcion'>" + des + "</textarea>");
                        out.print("<input type='submit' value='Guardar' onclick='Movimiento()' id='botonP'/>");
                        out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntosP'>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "        </div>");
                        out.print("</form>");
                        out.print("<div class='cleaner'></div></div>");
                        out.print("</div>");
                    }
                    //</editor-fold>
                }
                if (!lst_pendientes.isEmpty()) {
                    //<editor-fold defaultstate="collapsed" desc="pendientes hetrramental">
                    out.print("<div id='NavPosicion1'></div>");
                    out.print("<table class='table' style='width:100%' id='resultados1'>");
                    out.print("<tr>");
                    out.print("<th style='width:10%'>Fecha</th>");
                    out.print("<th style='width:20%'>Cavidades</th>");
                    out.print("<th style='width:20%'>Causas</th>");
                    out.print("<th style='width:20%'>Sugerencias</th>");
                    out.print("<th style='width:20%'>Solucion</th>");
                    out.print("<th style='width:10%'>Modificar</th>");
                    out.print("<th>Mail</th>");
                    out.print("</tr>");
                    for (int i = 0; i < lst_pendientes.size(); i++) {
                        Object[] obj_pendientes = (Object[]) lst_pendientes.get(i);
                        out.print("<tr>");
                        out.print("<td align='center'>" + obj_pendientes[3] + "</td>");
                        String[] arg_descripcion = obj_pendientes[4].toString().replace("<hr />", "<hr/>").split("<hr/>");
                        for (int j = 0; j < arg_descripcion.length; j++) {
                            out.print("<td valign='top'>" + arg_descripcion[j] + "</td>");
                        }
                        if ((Integer) obj_pendientes[6] == 0) {
                            out.print("<td align='center'>Pendiente enviar corrreo para solucionar</td>");
                            out.print("<td align='center'><a href='Herramental?opc=5&idH=" + obj_herramental[0] + "&idP=" + obj_pendientes[0] + "&idS=" + 0 + "&idMV=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                            out.print("<td align='center'><a href='#' onclick='idP(" + obj_pendientes[0] + "," + 1 + ")'><img id='Mail_pendiente' src='Interfaz/Contenido/Iconos/Mail.png' width='22px' height='15px'></a>");
                            out.print("</td>");
                        } else if ((Integer) obj_pendientes[6] == 1) {
                            if (obj_pendientes[8] != null) {
                                out.print("<td Valign='top'><b>Fecha: </b>" + obj_pendientes[7] + "<br />");
                                out.print("" + obj_pendientes[8] + "</td>");
//                                out.print("<td align='center'><a href='Herramental?opc=5&idH=" + id_herramental + "&idP=" + obj_pendientes[0] + "&idMV=" + 0 + "&idS=" + 2 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                                out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
                                out.print("<td align='center'><a href='#' onclick='idP(" + obj_pendientes[0] + "," + 2 + ")'><img src='Interfaz/Contenido/Iconos/chulo1.png' width='15' height='15'>&nbsp;&nbsp;</td>");
//                                out.print("<img id='Mail_pendiente' src='Interfaz/Contenido/Iconos/Mail.png' width='22px' height='15px'></a></td>");
                            } else {
//                                out.print("<td align='center'><a href='Herramental?opc=5&idH=" + id_herramental + "&idP=" + obj_pendientes[0] + "&idMV=" + 0 + "&idS=" + 1 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Plus.png' width='22' height='22'></a></td>");
                                out.print("<td align='center'>Esperando solucion</td>");
                                out.print("<td align='center'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></td>");
                                out.print("<td align='center'><img src='Interfaz/Contenido/Iconos/chulo1.png' width='15' height='15'>&nbsp;&nbsp;</td>");
//                                out.print("<img id='Mail_pendiente' src='Interfaz/Contenido/Iconos/Mail.png' width='22px' height='15px'></td>");
                            }
                        } else {
                            out.print("<td Valign='top'><b>Fecha: </b>" + obj_pendientes[7] + "<br />");
                            out.print("" + obj_pendientes[8] + "</td>");
                            out.print("<td align='center'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></td>");
                            out.print("<td align='center'><img src='Interfaz/Contenido/Iconos/chulo.png' width='30' height='20'></td>");
                        }
                        out.print("</tr>");
                    }
                    out.print("</table>");
                    out.print("<script type='text/javascript'>");
                    out.print("var pager3 = new Pager3('resultados1',12);");
                    out.print("pager3.init();");
                    out.print("pager3.showPageNav('pager','NavPosicion1');");
                    out.print("pager3.showPage(1);");
                    out.print("</script>");
                    //</editor-fold>
                } else {
                    out.print("<h3><b style='color:#292929'>No se han encontrado resultados</b></h3>");
                }
                out.print("</div>");
                out.print("</div>");
                out.print("<script>");
                out.print("$(Mail_pendiente).click(function() {");
                out.print("$(\"#toggleP\").toggle(\"slide\");");
                out.print("});");
                out.print("</script>");
                out.print("<script>");
                out.print("$(mail_movimientos).click(function() {");
                out.print("$(\"#toggleM\").toggle(\"slide\");");
                out.print("});");
                out.print("</script>");
                out.print("<div class='cleaner'></div></div>");
            }
            out.print("<script>"
                    + "     CKEDITOR.replace(\"editor\");"
                    + "</script>");
        } catch (IOException ex) {
            Logger.getLogger(Tag_herramental.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
