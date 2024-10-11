package Tags;

import controladores.ClasificacionDefectoJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_ClasificacionDefecto extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession sesion = pageContext.getSession();
        String area = sesion.getAttribute("Area").toString();
        ClasificacionDefectoJpaController jps_clasificacionD = new ClasificacionDefectoJpaController();
        List lst_clasificaciones = (List) pageContext.getRequest().getAttribute("consulta_clasificacion");
        String filtro = (String) pageContext.getRequest().getAttribute("filtro");
        int id_clasificacionD = Integer.parseInt(pageContext.getRequest().getAttribute("id_Cdefecto").toString());
        List lst_clasificacion = null;
        try {
            out.print("<div id='content_sin'>");
            out.print("<script language='Javascript'>"
                    + "function mostrar() {"
                    + "var panel ;var pagina =''; panel = document.getElementById('NuevaClasificacion');"
                    + "var bloq = document.getElementById('bloq');"
                    + "if(panel.style.visibility == 'hidden') {"
                    + "panel.style.visibility = 'visible';"
                    + "bloq.style.display = 'block';"
                    + "}else {"
                    + "panel.style.visibility = 'hidden';"
                    + "bloq.style.display = 'none';"
                    + "}}</script>");
            if (id_clasificacionD == 0) {
                out.print("<div class='overlay' tabindex='-1' id='bloq' style='opacity: 1.06; display: none;'>");
                out.print("<fieldset class='resalta' id='NuevaClasificacion' style='visibility: hidden;'>");
                out.print("<div style='float:right;'><a href='Clasificacion_defecto?opc=1&idCD=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
                out.print("<h3>Nueva clasificacion de defecto</h3>");
                out.print("<form action='Clasificacion_defecto?opc=2'  method='post' onsubmit='registroC();' name='formC'>");
                out.print("<div style='width: 100%; display: flex; justify-content: space-evenly;'>");
                out.print("<div>");
                out.print("<b>Clasificacion</b><br />");
                out.print("<input type='text' name='txt_clasificacion' id ='clasificacion-id' placeholder='Clasificacion' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('clasificacion-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("</div>");
                
                out.print("<div>");
                out.print("<b>Convencion</b><br />");
                out.print("<input type='text' name='txt_convencion' id ='convencion-id' placeholder='Convencion' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('convencion-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("</div>");
                out.print("</div>");
                out.print("<b>Descripcion</b><br />");
                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 120'><div contenteditable='true'><p>*</p><p></p></div></textarea>");
                out.print("<input type='submit' id='btsubmit' onclick='Clasificacion()' value='Guardar'>");
                out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "        </div>");
                out.print("</form>");
                out.print("</fieldset>");
                out.print("</div>");
            } else {
                lst_clasificacion = jps_clasificacionD.ConsultaClasificacionId(id_clasificacionD);
                Object[] obj_clasificacion = (Object[]) lst_clasificacion.get(0);
                out.print("<div class='overlay' tabindex='-1' id='bloq' style='opacity: 1.06; display: block;'>");
                out.print("<fieldset class='resalta' id='ModificarClasificacion' style='visibility: visible;'>");
                out.print("<div style='float:right;'><a href='Clasificacion_defecto?opc=1&idCD=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
                out.print("<h3>Modificar clasificacion de defecto</h3>");
                out.print("<form action='Clasificacion_defecto?opc=3'  method='post' onsubmit='registroC();' name='formC'>");
                out.print("<input type='hidden' name='idCD' value='" + id_clasificacionD + "'>");
                out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                
                out.print("<div style='width: 100%; display: flex; justify-content: space-evenly;'>");
                out.print("<div>");
                out.print("<b>Clasificacion</b><br />");
                out.print("<input type='text' name='txt_clasificacionM' value='" + obj_clasificacion[1] + "' id ='clasificacion-id' placeholder='Clasificacion' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('clasificacion-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("</div>");
                
                out.print("<div>");
                out.print("<b>Convencion</b><br />");
                out.print("<input type='text' name='txt_convencionM' value='" + obj_clasificacion[3] + "' id ='convencion-id' placeholder='Convencion' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('convencion-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("</div>");
                out.print("</div>");
                
                out.print("<b>Descripcion</b><br />");
                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 120'>" + obj_clasificacion[2].toString().replace("<div>", "<div contenteditable='true'>") + "</textarea>");
                out.print("<input type='submit' id='btsubmit' onclick='Clasificacion()' value='Guardar'>");
                out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "        </div>");
                out.print("</form>");
                out.print("</fieldset>");
                out.print("</div>");
            }
            out.print("<div style='float:right;'>");
            out.print("<form method='post' action='Clasificacion_defecto?opc=1&idCD=" + 0 + "'>");
            out.print("<input name='txt_bus' type='text' class='input_field' placeholder='Buscar'><br/>");
            out.print("</form>");
            out.print("</div>");
            if (area.equals("GESTION DE CALIDAD") || area.equals("SISTEMAS")) {
                out.print("<a href='javascript:mostrar();'><img src='Interfaz/Contenido/Iconos/Plus.png' width='22' height='22'></a>");
            }
            if (!filtro.equals("")) {
                out.print("<a href='Clasificacion_defecto?opc=1&idCD=" + 0 + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a>");
            }
            out.print("<br /><br />");
            if (!lst_clasificaciones.isEmpty()) {
                out.print("<div id='NavPosicion'></div>");
                out.print("<table class='table' id='resultados' style='width:100%;'>");
                out.print("<tr>");
                out.print("<th>Convencion</th>");
                out.print("<th>Defecto</th>");
                out.print("<th>Descripcion</th>");
                out.print("<th>Modificar</th>");
                out.print("<th>Estado</th>");
                out.print("</tr>");
                for (int i = 0; i < lst_clasificaciones.size(); i++) {
                    Object[] obj_clasificacion = (Object[]) lst_clasificaciones.get(i);
                    out.print("<tr>");
                    if (area.equals("GESTION DE CALIDAD") || area.equals("SISTEMAS")) {
                        if ((Integer) obj_clasificacion[4] != 0) {
                            out.print("<td align='center' style='width:5%;'><b style='color:#292929;'>" + obj_clasificacion[3] + "</b></td>");
                            out.print("<td align='center' style='width:25%;'><b style='color:#292929;'>" + obj_clasificacion[1] + "</b></td>");
                            out.print("<td valign='top'>" + obj_clasificacion[2] + "</td>");
                            out.print("<td align='center' style='width:5%;'><a href='Clasificacion_defecto?opc=1&idCD=" + obj_clasificacion[0] + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                            out.print("<td align='center' style='width:5%;'><a href='Clasificacion_defecto?opc=4&idCD=" + obj_clasificacion[0] + "&txt_bus=" + filtro + "&est=" + 0 + "'><img src='Interfaz/Contenido/Iconos/Check.png' width='22' height='22'></a></td>");
                        } else {
                            out.print("<td align='center' style='width:5%;'><b style='color:red;'>" + obj_clasificacion[3] + "</b></td>");
                            out.print("<td align='center' style='width:25%;'><b style='color:red;'>" + obj_clasificacion[1] + "</b></td>");
                            out.print("<td valign='top'>" + obj_clasificacion[2] + "</td>");
                            out.print("<td align='center' style='width:5%;'><a href='Clasificacion_defecto?opc=1&idCD=" + obj_clasificacion[0] + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                            out.print("<td align='center' style='width:5%;'><a href='Clasificacion_defecto?opc=4&idCD=" + obj_clasificacion[0] + "&txt_bus=" + filtro + "&est=" + 1 + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22'></a></td>");
                        }
                    } else {
                        out.print("<td align='center' style='width:5%;'>" + obj_clasificacion[3] + "</td>");
                        out.print("<td align='center' style='width:25%;'>" + obj_clasificacion[1] + "</td>");
                        out.print("<td valign='top'>" + obj_clasificacion[2] + "</td>");
                        out.print("<td align='center' style='width:5%;'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22' title='se necesitan permisos'></a></td>");
                        out.print("<td align='center' style='width:5%;'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22' title='se necesitan permisos'></a></td>");
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
            out.print("<script>\n"
                    + "     CKEDITOR.replace(\"editor\");\n"
                    + "</script>");
        } catch (IOException ex) {
            Logger.getLogger(Tag_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
