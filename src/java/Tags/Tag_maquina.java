package Tags;

import controladores.MaquinaJpaController;
import controladores.MovimientoInyectoraJpaController;
import controladores.TipoMaquinaJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_maquina extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession sesion = pageContext.getSession();
        String area = sesion.getAttribute("Area").toString();
        MaquinaJpaController jpa_maquina = new MaquinaJpaController();
        TipoMaquinaJpaController jpa_tipoM = new TipoMaquinaJpaController();
        MovimientoInyectoraJpaController jpa_movimiento = new MovimientoInyectoraJpaController();
        List lst_maquinas = null;
        List lst_tipoM = jpa_tipoM.ConsultaTipoMaquinas();
        List lst_movimiento = null;
        String filtro = (String) pageContext.getRequest().getAttribute("filtro");
        int id_maquina = Integer.parseInt(pageContext.getRequest().getAttribute("id_maquina").toString());
        String UltMovimiento = "";
        String Producto = "";
        String Molde = "";
        String movimiento = "";
        String herramental = "";
        if (!filtro.equals("")) {
            lst_maquinas = jpa_maquina.ConsultaMaquinaFiltro(filtro);
        } else {
            lst_maquinas = jpa_maquina.ConsultaMaquinas();
        }
        try {
            out.print("<div id='content_sin'>");
            out.print("<div style='float:right;'>");
            out.print("<form method='post' action='Maquina?opc=1&idM=" + 0 + "'>");
            out.print("<input name='txt_bus' type='text' class='input_field' placeholder='Buscar'><br/>");
            out.print("</form>");
            out.print("</div>");
            if (!filtro.equals("")) {
                out.print("<a href='Maquina?opc=1&idM=" + 0 + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a>");
            }
            out.print("<img id='Menu_registro'  src='Interfaz/Contenido/Iconos/Menu.png' width='20px' height='20px' alt='edit' title='registro' />");

            out.print("<script>");
            out.print("$(Menu_registro).click(function() {");
            out.print("$(\"#toggle\").toggle(\"slide\");");
            out.print("});");
            out.print("</script>");
            if (area.equals("SISTEMAS") || area.equals("PRODUCCION INSUMOS") || area.equals("PRODUCCIÓN FARMACEUTICA")) {
                if (id_maquina == 0) {
                    out.print("<div style='display:none;' id=\"toggle\">");
                    out.print("<div id='sidebar' style='border: 1px solid #CAA427;'>");
                    //<editor-fold defaultstate="collapsed" desc="registro maquina">
                    out.print("<h3>Nueva maquina</h3>");
                    out.print("<form action='Maquina?opc=2' onsubmit='registroM();' method='post' id='form1'>");
                    out.print("<b>Maquina</b><br />");
                    out.print("<input type='text' name='txt_maquina' id ='maquina-id' placeholder='Nombre' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('maquina-id');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script>");
                    out.print("<b>Tipo</b><br />");
                    out.print("<select name='lst_tipoM' id='select-id'>");
                    out.print("<option value='0' style='display:none;'>Seleccione tipo</option>");
                    for (int i = 0; i < lst_tipoM.size(); i++) {
                        Object[] obj_tipoM = (Object[]) lst_tipoM.get(i);
                        if ((Integer) obj_tipoM[2] == 1) {
                            out.print("<option value='" + obj_tipoM[0] + "'>" + obj_tipoM[1] + "</option>");
                        } else {
                            out.print("<option value='" + obj_tipoM[0] + "' disabled>" + obj_tipoM[1] + "-Inactiva</option>");
                        }
                    }
                    out.print("</select><br />");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('select-id');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script><br />");
                    out.print("<input type='submit' id='btsubmit' value='Guardar'>");
                    out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "        </div>");
                    out.print("</form>");
//</editor-fold>
                    out.print("<div class='cleaner'></div></div>");
                    out.print("</div>");
                } else {
                    out.print("<div style='display:block;' id=\"toggle\">");
                    out.print("<div id='sidebar' style='border: 1px solid #CAA427;'>");
                    //<editor-fold defaultstate="collapsed" desc="modificar maquina">
                    List lst_maquina = jpa_maquina.ConsultaMaquinaId(id_maquina);
                    Object[] obj_maquina = (Object[]) lst_maquina.get(0);
                    out.print("<h3>Modificar maquina &nbsp;&nbsp;&nbsp;<a href='Maquina?opc=1&idM=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Delete.png' alt='Logo' width='20' height='20' title='Cancelar' /></a></h3>");
                    out.print("<form action='Maquina?opc=3&idM=" + id_maquina + "' onsubmit='registroM();' method='post' id='form1'>");
                    out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                    out.print("<b>Maquina</b><br />");
                    out.print("<input type='text' name='txt_maquinaM' id ='maquinaM-id' value='" + obj_maquina[1] + "' placeholder='Nombre' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('maquinaM-id');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script>");
                    out.print("<b>Tipo</b><br />");
                    out.print("<select name='lst_tipoMM' id='select-id'>");
                    out.print("<option value='" + obj_maquina[3] + "' style='display:none;'>" + obj_maquina[4] + "</option>");
                    for (int i = 0; i < lst_tipoM.size(); i++) {
                        Object[] obj_tipoM = (Object[]) lst_tipoM.get(i);
                        if ((Integer) obj_tipoM[2] == 1) {
                            out.print("<option value='" + obj_tipoM[0] + "'>" + obj_tipoM[1] + "</option>");
                        } else {
                            out.print("<option value='" + obj_tipoM[0] + "' disabled>" + obj_tipoM[1] + "-Inactiva</option>");
                        }
                    }
                    out.print("</select><br />");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('select-id');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script><br />");
                    out.print("<input type='submit' value='Modificar' id='btsubmit'>");
                    out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "        </div>");
                    out.print("</form>");
//</editor-fold>
                    out.print("<div class='cleaner'></div></div>");
                    out.print("</div>");
                }
            } else {
                out.print("<div style='display:none;' id=\"toggle\">");
                out.print("<div id='sidebar' style='border: 1px solid #CAA427;>");
                out.print("<h3>Nueva maquina</h3>");
                out.print("<div align='center'><img src='Interfaz/Contenido/Iconos/Alert.png' alt='Logo' width='55' height='55.5' /><br /><b>No se permite <br />el registro</b></div>");
                out.print("<div class='cleaner'></div></div>");
                out.print("</div>");
            }
            //<editor-fold defaultstate="collapsed" desc="consulta maquinas">
            if (!lst_maquinas.isEmpty()) {
                out.print("<h3>Maquinas</h3>");
                out.print("<div id='NavPosicion'></div>");
                out.print("<table class='table' id='resultados' style='width:100%;'>");
                out.print("<tr>");
                out.print("<th>Nombre</th>");
                out.print("<th>Tipo</th>");
                out.print("<th colspan='2'>Estado</th>");
                out.print("<th>Molde Actual</th>");
                out.print("<th>Produto Actual</th>");
                out.print("<th style='width:5%;'>Movimientos</th>");
                out.print("<th style='width:5%;'>Modificar</th>");
                out.print("</tr>");
                for (int i = 0; i < lst_maquinas.size(); i++) {
                    Object[] obj_maquinas = (Object[]) lst_maquinas.get(i);
                    out.print("<tr>");
                    if ((Integer) obj_maquinas[0] != 1) {
                        lst_movimiento = jpa_movimiento.ConsultaUltMovimientoIdMaquina((Integer) obj_maquinas[0]);
                        if (lst_movimiento != null) {
                            Object[] obj_mov = (Object[]) lst_movimiento.get(0);
                            UltMovimiento = obj_mov[3].toString();
                            Producto = obj_mov[3].toString();
                            Molde = obj_mov[1].toString();
                            movimiento = obj_mov[1].toString();
                            herramental = obj_mov[0].toString();
                        } else {
                            UltMovimiento = "No se han realizado movimientos";
                            Producto = "N/A";
                            movimiento = "N/A";
                            herramental = "N/A";
                        }
                        if ((Integer) obj_maquinas[2] != 0) {
                            //<editor-fold defaultstate="collapsed" desc="maquina activa">
                            out.print("<td align='center'>" + obj_maquinas[1] + "</td>");
                            out.print("<td align='center'>" + obj_maquinas[4] + "</td>");
                            if (area.equals("SISTEMAS") || area.equals("PRODUCCION INSUMOS") || area.equals("PRODUCCIÓN FARMACEUTICA")) {
                                if (area.equals(obj_maquinas[6].toString()) || area.equals("SISTEMAS")) {
                                    //<editor-fold defaultstate="collapsed" desc="administradores de las maquinas">
                                    if ((Integer) obj_maquinas[2] == 1) {
                                        out.print("<td align='center' style='width:10%'>");
                                        out.print("<input type='radio' class='inputBox_verde' value='3' name='checkB" + i + "' onclick='estado(this.value,\"" + obj_maquinas[0] + "\",\"" + filtro + "\")' checked>");
                                        out.print("</td><td align='center' style='width: 20%;'>");
                                        out.print("<b class='tooltip' style='color:green;'>En produccion");
                                        out.print("<span class='tooltiptext' style='border:2px solid #CAA427' valign='top'><b>Herramental =</b> " + herramental + "  <br /><b>Producto =</b> " + Producto + " <br /><b>Movimiento =</b> " + movimiento + " <br />" + UltMovimiento + "</span></b>");
                                        out.print("</td>");
                                    } else if ((Integer) obj_maquinas[2] == 2) {
                                        out.print("<td align='center' style='width:10%'>"
                                                + "<input type='radio' class='inputBox_naranja' value='3' name='checkB" + i + "' onclick='estado(this.value,\"" + obj_maquinas[0] + "\",\"" + filtro + "\")' checked>"
                                                + "</td><td align='center' style='width: 20%;'>"
                                                + "<b class='tooltip'>En produccion <br/ > Cavidad tapada"
                                                + "<span class='tooltiptext'  style='border:2px solid #CAA427'  valign='top'><b>Herramental =</b> " + herramental + "  <br /><b>Producto =</b> " + Producto + " <br /><b>Movimiento</b> = " + movimiento + " <br />" + UltMovimiento + "</span></b>"
                                                + "</td>");
                                    } else if ((Integer) obj_maquinas[2] == 3) {
                                        out.print("<td align='center' style='width:10%'>"
                                                + "<input type='radio' class='inputBox_rojo' value='3' name='checkB" + i + "' onclick='estado(this.value,\"" + obj_maquinas[0] + "\",\"" + filtro + "\")' checked>"
                                                + "</td><td align='center' style='width: 20%;'>"
                                                + "<b class='tooltip' style='color:red;'>Parada"
                                                + "<span class='tooltiptext' style='border:2px solid #CAA427'  valign='top'><b>Herramental =</b> " + herramental + "  <br /><b>Producto =</b> " + Producto + " <br /><b>Movimiento</b> = " + movimiento + " <br />" + UltMovimiento + "</span></b>"
                                                + "</td>");
                                    }
                                    out.print("<td align='center'>" + Molde + "</td>");
                                    out.print("<td>" + Producto + "</td>");
                                    out.print("<td align='center'><a href='Movimiento?opc=1&idM=" + obj_maquinas[0] + "&idMV=" + 0 + "&txt_bus=" + filtro + "&idP=" + 0 + "&idS=" + 0 + "' target=\"_self\"><img src='Interfaz/Contenido/Iconos/Document.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='Maquina?opc=1&idM=" + obj_maquinas[0] + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                                    //</editor-fold>
                                } else {
                                    //<editor-fold defaultstate="collapsed" desc="solo consulta de las maquinas">
                                    if ((Integer) obj_maquinas[2] == 1) {
                                        out.print("<td align='center' style='width:10%'>"
                                                + "<input type='radio' class='inputBox_verde' value='1' name='checkB" + i + "' disabled checked>"
                                                + "</td><td align='center' style='width: 20%;'>"
                                                + "<b class='tooltip' style='color:green;'>En produccion"
                                                + "<span class='tooltiptext' style='border:2px solid #CAA427'  valign='top'><b>Herramental =</b> " + herramental + "  <br /><b>Producto =</b> " + Producto + " <br /><b>Movimiento =</b> " + movimiento + " <br />" + UltMovimiento + "</span></b>"
                                                + "</td>");
                                    } else if ((Integer) obj_maquinas[2] == 2) {
                                        out.print("<td align='center' style='width:10%'>"
                                                + "<input type='radio' class='inputBox_naranja' value='2' name='checkB" + i + "' checked disabled>"
                                                + "</td><td align='center' style='width: 20%;'>"
                                                + "<b class='tooltip'>En produccion <br/ > Cavidad tapada"
                                                + "<span class='tooltiptext' style='border:2px solid #CAA427' valign='top'><b>Herramental =</b> " + herramental + "  <br /><b>Producto =</b> " + Producto + " <br /><b>Movimiento</b> = " + movimiento + " <br />" + UltMovimiento + "</span></b>"
                                                + "</td>");
                                    } else if ((Integer) obj_maquinas[2] == 3) {
                                        out.print("<td align='center' style='width:10%'>"
                                                + "<input type='radio' class='inputBox_rojo' value='3' name='checkB" + i + "' disabled checked>"
                                                + "</td><td align='center' style='width: 20%;'>"
                                                + "<b class='tooltip' style='color:red;'>Parada"
                                                + "<span class='tooltiptext' style='border:2px solid #CAA427'  valign='top'><b>Herramental =</b> " + herramental + "  <br /><b>Producto =</b> " + Producto + " <br /><b>Movimiento</b> = " + movimiento + " <br />" + UltMovimiento + "</span></b>"
                                                + "</td>");
                                    }
                                    out.print("<td align='center'>" + Molde + "</td>");
                                    out.print("<td>" + Producto + "</td>");
                                    out.print("<td align='center'><a href='Movimiento?opc=1&idM=" + obj_maquinas[0] + "&idMV=" + 0 + "&txt_bus=" + filtro + "&idP=" + 0 + "&idS=" + 0 + "' target=\"_self\"><img src='Interfaz/Contenido/Iconos/Document.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
                                    //</editor-fold>
                                }
                            } else {
                                //<editor-fold defaultstate="collapsed" desc="no pertenecientes a las areas de las maquinas solo consulta">
                                if ((Integer) obj_maquinas[2] == 1) {
                                    out.print("<td align='center' style='width:10%'>"
                                            + "<input type='radio' class='inputBox_verde' value='1' name='checkB" + i + "' disabled checked>"
                                            + "</td><td align='center' style='width: 20%;'>"
                                            + "<b class='tooltip' style='color:green;'>En produccion"
                                            + "<span class='tooltiptext' valign='top'><b>Herramental =</b> " + herramental + "  <br /><b>Producto =</b> " + Producto + " <br /><b>Movimiento =</b> " + movimiento + " <br />" + UltMovimiento + "</span></b>"
                                            + "</td>");
                                } else if ((Integer) obj_maquinas[2] == 2) {
                                    out.print("<td align='center' style='width:10%'>"
                                            + "<input type='radio' class='inputBox_naranja' value='2' name='checkB" + i + "' checked disabled>"
                                            + "</td><td align='center' style='width: 20%;'>"
                                            + "<b class='tooltip'>En produccion <br/ > Cavidad tapada"
                                            + "<span class='tooltiptext' valign='top'><b>Herramental =</b> " + herramental + "  <br /><b>Producto =</b> " + Producto + " <br /><b>Movimiento</b> = " + movimiento + " <br />" + UltMovimiento + "</span></b>"
                                            + "</td>");
                                } else if ((Integer) obj_maquinas[2] == 3) {
                                    out.print("<td align='center' style='width:10%'>"
                                            + "<input type='radio' class='inputBox_rojo' value='3' name='checkB" + i + "' disabled checked>"
                                            + "</td><td align='center' style='width: 20%;'>"
                                            + "<b class='tooltip' style='color:red;'>Parada"
                                            + "<span class='tooltiptext' valign='top'><b>Herramental =</b> " + herramental + "  <br /><b>Producto =</b> " + Producto + " <br /><b>Movimiento</b> = " + movimiento + " <br />" + UltMovimiento + "</span></b>"
                                            + "</td>");
                                }
                                out.print("<td align='center'>" + Molde + "</td>");
                                out.print("<td>" + Producto + "</td>");
                                out.print("<td align='center'><a href='Movimiento?opc=1&idM=" + obj_maquinas[0] + "&idMV=" + 0 + "&txt_bus=" + filtro + "&idP=" + 0 + "&idS=" + 0 + "' target=\"_self\"><img src='Interfaz/Contenido/Iconos/Document.png' width='22' height='22'></a></td>");
                                out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
                                //</editor-fold>
                            }
                            //</editor-fold>
                        } else {
                            //<editor-fold defaultstate="collapsed" desc="maquina in-activa">
                            out.print("<td align='center'>" + obj_maquinas[1] + "</td>");
                            out.print("<td align='center'>" + obj_maquinas[4] + "</td>");
                            if (area.equals("SISTEMAS") || area.equals("PRODUCCION INSUMOS") || area.equals("PRODUCCIÓN FARMACEUTICA")) {
                                if (area.equals(obj_maquinas[6].toString()) || area.equals("SISTEMAS")) {
                                    //<editor-fold defaultstate="collapsed" desc="administradores de las maquinas">
                                    out.print("<td align='center' style='width:10%'>");
                                    out.print("<input type='radio' class='inputBox_gris' value='3' name='checkB" + i + "' onclick='estado(this.value,\"" + obj_maquinas[0] + "\",\"" + filtro + "\")' checked>");
                                    out.print("</td><td align='center' style='width: 20%;'>");
                                    out.print("<b class='tooltip' style='color:#bbb;'>Disponible");
                                    out.print("<span class='tooltiptext' valign='top'><b>Herramental =</b> " + herramental + "  <br /><b>Producto =</b> " + Producto + " <br /><b>Movimiento</b> = " + movimiento + " <br />" + UltMovimiento + "</span></b>");
                                    out.print("</td>");
                                    out.print("<td align='center'>" + Molde + "</td>");
                                    out.print("<td>" + Producto + "</td>");
                                    out.print("<td align='center'><a href='Movimiento?opc=1&idM=" + obj_maquinas[0] + "&idMV=" + 0 + "&txt_bus=" + filtro + "&idP=" + 0 + "&idS=" + 0 + "' target=\"_self\"><img src='Interfaz/Contenido/Iconos/Document.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='Maquina?opc=1&idM=" + obj_maquinas[0] + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                                    //</editor-fold>
                                } else {
                                    //<editor-fold defaultstate="collapsed" desc="solo consulta de las maquinas">
                                    out.print("<td align='center' style='width:10%'>"
                                            + "<input type='radio' class='inputBox_gris' value='0' name='checkB" + i + "' onclick='estado(this.value,\"" + obj_maquinas[0] + "\",\"" + filtro + "\")' checked>"
                                            + "</td><td align='center' style='width: 20%;'>"
                                            + "<b class='tooltip' style='color:#bbb;'>Disponible"
                                            + "<span class='tooltiptext' valign='top'><b>Herramental =</b> " + herramental + "  <br /><b>Producto =</b> " + Producto + " <br /><b>Movimiento</b> = " + movimiento + " <br />" + UltMovimiento + "</span></b>"
                                            + "</td>");
                                    out.print("<td align='center'>" + Molde + "</td>");
                                    out.print("<td>" + Producto + "</td>");
                                    out.print("<td align='center'><a href='Movimiento?opc=1&idM=" + obj_maquinas[0] + "&idMV=" + 0 + "&txt_bus=" + filtro + "&idP=" + 0 + "&idS=" + 0 + "' target=\"_self\"><img src='Interfaz/Contenido/Iconos/Document.png' width='22' height='22'></a></td>");
                                    out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
                                    //</editor-fold>
                                }
                            } else {
                                //<editor-fold defaultstate="collapsed" desc="no pertenecientes a las areas de las maquinas solo consulta">
                                out.print("<td align='center' style='width:10%'>"
                                        + "<input type='radio' class='inputBox_gris' value='0' name='checkB" + i + "' onclick='estado(this.value,\"" + obj_maquinas[0] + "\",\"" + filtro + "\")' checked>"
                                        + "</td><td align='center' style='width: 20%;'>"
                                        + "<b class='tooltip' style='color:#bbb;'>Disponible"
                                        + "<span class='tooltiptext' valign='top'><b>Herramental =</b> " + herramental + "  <br /><b>Producto =</b> " + Producto + " <br /><b>Movimiento</b> = " + movimiento + " <br />" + UltMovimiento + "</span></b>"
                                        + "</td>");
                                out.print("<td align='center'>" + Molde + "</td>");
                                out.print("<td>" + Producto + "</td>");
                                out.print("<td align='center'><a href='Movimiento?opc=1&idM=" + obj_maquinas[0] + "&idMV=" + 0 + "&txt_bus=" + filtro + "&idP=" + 0 + "&idS=" + 0 + "' target=\"_self\"><img src='Interfaz/Contenido/Iconos/Document.png' width='22' height='22'></a></td>");
                                out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
                                //</editor-fold>
                            }
                            //</editor-fold>
                        }
                    }
                    out.print("</tr>");
                }
                out.print("</table>");
                out.print("<script type='text/javascript'>");
                out.print("var pager = new Pager('resultados',10);");
                out.print("pager.init();");
                out.print("pager.showPageNav('pager','NavPosicion');");
                out.print("pager.showPage(1);");
                out.print("</script>");
            } else {
                out.print("<h3><b style='color:#292929'>No se han encontrado resultados</b></h3>");
            }
            //</editor-fold>
            out.print("<div class='cleaner'></div></div>");
        } catch (IOException ex) {
            Logger.getLogger(Tag_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
