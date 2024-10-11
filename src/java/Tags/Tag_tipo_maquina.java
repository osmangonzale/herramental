package Tags;

import Entidad.TipoMaquina;
import controladores.MaquinaJpaController;
import controladores.TipoMaquinaJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_tipo_maquina extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession sesion = pageContext.getSession();
        String area = sesion.getAttribute("Area").toString();
        TipoMaquinaJpaController jpa_tipoM = new TipoMaquinaJpaController();
        List lst_areas = jpa_tipoM.ConsultaAreas();
        List lst_tipoM = (List) pageContext.getRequest().getAttribute("consulta_TipoM");
        String filtro = (String) pageContext.getRequest().getAttribute("filtro");
        int id_tipoM = Integer.parseInt(pageContext.getRequest().getAttribute("id_tipoM").toString());
        try {
            out.print("<div id='sidebar'>");
            if (area.equals("SISTEMAS") || area.equals("GESTION DE CALIDAD")) {
                if (id_tipoM == 0) {
                    out.print("<h3>Nuevo tipo de maquina</h3>");
                    out.print("<form action='Tipo_maquina?opc=2' onsubmit='registroTP();' method='post' id='form1'>");
                    out.print("<b>Tipo Maquina</b><br />");
                    out.print("<input type='text' name='txt_tipoM' id ='tipoM-id' placeholder='Nombre' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('tipoM-id');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script>");
                    out.print("<b>Area</b><br />");
                    out.print("<select name='lsarea' id='selecta-id' required>");
                    out.print("<option value='0' style='display:none;'>Seleccione area</option>");
                    for (int i = 0; i < lst_areas.size(); i++) {
                        Object[] obj_areas = (Object[]) lst_areas.get(i);
                        out.print("<option value='" + obj_areas[0] + "'>" + obj_areas[1] + " / " + obj_areas[2] + "</option>");
                    }
                    out.print("</select><br /><br />");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('selecta-id'');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script>");
                    out.print("<input type='submit' id='btsubmit' value='Guardar' id='btsubmit'>");
                    out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "        </div>");
                    out.print("</form>");
                } else {
                    List lst_TipoMM = jpa_tipoM.ConsultaTipoMaquinasId(id_tipoM);
                    Object[] obj_tipoMM = (Object[]) lst_TipoMM.get(0);
                    out.print("<a href='Tipo_maquina?opc=1&idT=" + 0 + "&txt_bus=" + filtro + "' style='float:right;'><img src='Interfaz/Contenido/Iconos/Delete.png' alt='Logo' width='20' height='20' title='Cancelar' /></a>");
                    out.print("<h3>Modificar Tipo maquina</h3>");
                    out.print("<form action='Tipo_maquina?opc=3&idT=" + id_tipoM + "' onsubmit='registroTP();' method='post' id='form1'>");
                    out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                    out.print("<b>Maquina</b><br />");
                    out.print("<input type='text' name='txt_tipoMM' id ='tipoMM-id' value='" + obj_tipoMM[1] + "' placeholder='Nombre' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('tipoMM-id');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script>");
                    out.print("<b>Area</b><br />");
                    out.print("<select name='lsareaM' id='selecta-id'>");
                    out.print("<option value='" + obj_tipoMM[3] + "'>" + obj_tipoMM[4] + "</option>");
                    for (int i = 0; i < lst_areas.size(); i++) {
                        Object[] obj_areas = (Object[]) lst_areas.get(i);
                        out.print("<option value='" + obj_areas[0] + "'>" + obj_areas[1] + " / " + obj_areas[2] + "</option>");
                    }
                    out.print("</select><br /><br />");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('selecta-id'');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script>");
                    out.print("<input type='submit' value='Modificar' id='btsubmit'>");
                    out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "        </div>");
                    out.print("</form>");
                }
            } else {
                out.print("<h3>Nuevo tipo de maquina</h3>");
                out.print("<div align='center'><img src='Interfaz/Contenido/Iconos/Alert.png' alt='Logo' width='55' height='55.5' /><br /><b>No se permite <br />el registro</b></div>");
            }
            out.print("<div class='cleaner'></div></div>");
            out.print("<div id='content'>");
            out.print("<div style='float:right;'>");
            out.print("<form method='post' action='Tipo_maquina?opc=1&idT=" + 0 + "'>");
            out.print("<input name='txt_bus' type='text' class='input_field' placeholder='Buscar'><br/>");
            out.print("</form>");
            out.print("</div>");
            if (!filtro.equals("")) {
                out.print("<a href='Tipo_maquina?opc=1&idT=" + 0 + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a>");
            }
            out.print("<h3>Tipo Maquinas</h3>");
            if (!lst_tipoM.isEmpty()) {
                out.print("<div id='NavPosicion'></div>");
                out.print("<table class='table' id='resultados' style='width:100%;'>");
                out.print("<tr>");
                out.print("<th>Tipo maquina</th>");
                out.print("<th>Area</th>");
                out.print("<th>Modificar</th>");
                out.print("<th>Estado</th>");
                out.print("</tr>");
                for (int i = 0; i < lst_tipoM.size(); i++) {
                    Object[] obj_TipoM = (Object[]) lst_tipoM.get(i);
                    out.print("<tr>");
                    if ((Integer) obj_TipoM[2] == 1) {
                        out.print("<td align='center'>" + obj_TipoM[1] + "</td>");
                        out.print("<td align='center'>" + obj_TipoM[4] + "</td>");
                        if (area.equals("SISTEMAS") || area.equals("GESTION DE CALIDAD")) {
                            out.print("<td align='center'><a href='Tipo_maquina?opc=1&idT=" + obj_TipoM[0] + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                            out.print("<td align='center'><a href='Tipo_maquina?opc=4&idT=" + obj_TipoM[0] + "&est=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Check.png' width='22' height='22'></a></td>");
                        } else {
                            out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
                            out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
                        }
                    } else {
                        out.print("<td align='center'><b style='color:red'>" + obj_TipoM[1] + "</b></td>");
                        out.print("<td align='center'><b style='color:red'>" + obj_TipoM[4] + "</b></td>");
                        if (area.equals("SISTEMAS") || area.equals("GESTION DE CALIDAD")) {
                            out.print("<td align='center'><a href='Tipo_maquina?opc=1&idT=" + obj_TipoM[0] + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                            out.print("<td align='center'><a href='Tipo_maquina?opc=4&idT=" + obj_TipoM[0] + "&est=" + 1 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22'></a></td>");
                        } else {
                            out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
                            out.print("<td align='center'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></a></td>");
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
                out.print("<h3><b style='color:#292929'>No se han encontrado resultados</b></h3");
            }
            out.print("<div class='cleaner'></div></div>");
        } catch (IOException ex) {
            Logger.getLogger(Tag_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
