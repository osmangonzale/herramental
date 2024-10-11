package Tags;

import controladores.ClasificacionDefectoJpaController;
import controladores.DefectoJpaController;
import controladores.FamiliaProductoJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_defecto extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession sesion = pageContext.getSession();
        String area = sesion.getAttribute("Area").toString();
        ClasificacionDefectoJpaController jpa_clasificacionD = new ClasificacionDefectoJpaController();
        FamiliaProductoJpaController jpa_familia = new FamiliaProductoJpaController();
        DefectoJpaController jpa_defecto = new DefectoJpaController();
        List lst_clasificacionD = jpa_clasificacionD.ConsultaClasificacion();
        List lst_defectos = (List) pageContext.getRequest().getAttribute("consulta_defectos");
        String filtro = (String) pageContext.getRequest().getAttribute("filtro");
        int id_defecto = Integer.parseInt(pageContext.getRequest().getAttribute("id_defecto").toString());
        int id_familiaP = Integer.parseInt(pageContext.getRequest().getAttribute("id_familiaP").toString());
        List lst_familia = jpa_familia.ConsultaFamiliaProductoId(id_familiaP);
        Object[] obj_familia = (Object[]) lst_familia.get(0);
        List lst_defecto = null;
        try {
            out.print("<div id='content_sin'>");
            out.print("<script language='Javascript'>"
                    + "function mostrar() {"
                    + "var panel ;var pagina =''; panel = document.getElementById('NuevaProgramacion');"
                    + "var bloq = document.getElementById('bloq');"
                    + "if(panel.style.visibility == 'hidden') {"
                    + "panel.style.visibility = 'visible';"
                    + "bloq.style.display = 'block';"
                    + "}else {"
                    + "panel.style.visibility = 'hidden';"
                    + "bloq.style.display = 'none';"
                    + "}}</script>");
            if (id_defecto == 0) {
                //<editor-fold defaultstate="collapsed" desc="NUEVA PROGRAMACION">
                out.print("<div class='overlay' tabindex='-1' id='bloq' style='opacity: 1.06; display: none;'>");
                out.print("<fieldset class='resalta' id='NuevaProgramacion' style='visibility: hidden;height:500px;top:45%;'>");
                out.print("<div style='float:right;'><a href='Defecto?opc=1&idD=" + 0 + "&idF=" + id_familiaP + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
                out.print("<h3>Nuevo defecto</h3>");
                out.print("<form action='Defecto?opc=2' onsubmit='registroP();' method='post' id='formP'>");
                out.print("<input type='hidden' name='idF' value='" + id_familiaP + "'>");
                out.print("<table class='table' style='width:100%'>");
                out.print("<tr>");
                out.print("<td colspan='2'>");
                out.print("<b>Consecutivo:</b><br />");
                out.print("<input type='text' name='txt_consecutivo' id='consecutivo_id' placeholder='Consecutivo'>");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('consecutivo_id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("</td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td>");
                out.print("<b>Nombre:</b><br />");
                out.print("<input type='text' name='txt_nombre' id='Nombre_id' placeholder='Nombre'>");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('Nombre_id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("</td>");
                out.print("<td>");
                out.print("<b>Clasificacion:</b><br />");
                out.print("<select id='id_clasificacion' name='slc_clasificacion'>");
                out.print("<option style='display:none;' value='0'>Seleccione una clasificacion</option>");
                for (int i = 0; i < lst_clasificacionD.size(); i++) {
                    Object[] obj_clasificacionD = (Object[]) lst_clasificacionD.get(i);
                    out.print("<option value='" + obj_clasificacionD[0] + "'>" + obj_clasificacionD[1] + "</option>");
                }
                out.print("</select>");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('id_clasificacion');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("</td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td colspan='2'>");
                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>");
                out.print("<b>Imagen</b>");
                out.print("<div contenteditable='true'><p>*</p></div>");
                out.print("<hr />");
                out.print("<b>Tratamiento</b>");
                out.print("<div contenteditable='true'><p>*</p></div>");
                out.print("</textarea>");
                out.print("<input type='submit' id='btsubmit' value='Guardar'>");
                out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "        </div>");
                out.print("</td>");
                out.print("</tr>");
                out.print("</table>");
                out.print("</form>");
                out.print("</fieldset>");
                out.print("</div>");
                //</editor-fold>
            } else {
                //<editor-fold defaultstate="collapsed" desc="MODIFICAR PROGRAMACION">
                lst_defecto = jpa_defecto.ConsultaDefectoId(id_defecto);
                Object[] obj_defecto = (Object[]) lst_defecto.get(0);
                out.print("<div class='overlay' tabindex='-1' id='bloq' style='opacity: 1.06; display: block;'>");
                out.print("<fieldset class='resalta' id='ModificarProgramacion' style='visibility: visible;height:500px;top:45%;'>");
                out.print("<div style='float:right;'><a href='Defecto?opc=1&idD=" + 0 + "&idF=" + id_familiaP + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
                out.print("<h3>Modificar defecto</h3>");
                out.print("<form action='Defecto?opc=3' onsubmit='registroP();' method='post' id='formP'>");
                out.print("<input type='hidden' name='idD' value='" + obj_defecto[0] + "'>");
                out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                out.print("<input type='hidden' name='idF' value='" + id_familiaP + "'>");
                out.print("<table class='table' style='width:100%'>");
                out.print("<tr>");
                out.print("<td colspan='2'>");
                out.print("<b>Consecutivo:</b><br />");
                out.print("<input type='text' name='txt_consecutivoM' value='" + obj_defecto[7] + "' id='consecutivo_id' placeholder='Consecutivo'>");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('consecutivo_id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("</td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td>");
                out.print("<b>Nombre:</b><br />");
                out.print("<input type='text' name='txt_nombreM' value='" + obj_defecto[5] + "' id='Nombre_id' placeholder='Nombre'>");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('Nombre_id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("</td>");
                out.print("<td>");
                out.print("<b>Clasificacion:</b><br />");
                out.print("<select id='id_clasificacion' name='slc_clasificacionM'>");
                out.print("<option style='display:none;' value='" + obj_defecto[1] + "'>" + obj_defecto[2] + "</option>");
                for (int i = 0; i < lst_clasificacionD.size(); i++) {
                    Object[] obj_clasificacionD = (Object[]) lst_clasificacionD.get(i);
                    out.print("<option value='" + obj_clasificacionD[0] + "'>" + obj_clasificacionD[1] + "</option>");
                }
                out.print("</select>");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('id_clasificacion');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("</td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td colspan='2'>");
                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + obj_defecto[6].toString().replace("<div>", "<div contenteditable='true'>") + "</textarea>");
                out.print("<input type='submit' id='btsubmit' value='Guardar'>");
                out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "        </div>");
                out.print("</td>");
                out.print("</tr>");
                out.print("</table>");
                out.print("</form>");
                out.print("</fieldset>");
                out.print("</div>");
                //</editor-fold>
            }

            out.print("<div style='display:flex;justify-content:space-between;'>");
            if (!filtro.equals("")) {
                out.print("<div><a href='Defecto?opc=1&idD=" + 0 + "&idF=" + id_familiaP + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a></div>");
            } else {
                out.print("<div><a href='Familia_producto?opc=1&idF=" + 0 + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a></div>");
            }
            out.print("<div style='display:flex;align-items:baseline;'><div><form method='post' action='Defecto?opc=1&idD=" + 0 + "&idF=" + id_familiaP + "'>");
            out.print("<input name='txt_bus' type='text' class='input_field' placeholder='Buscar'><br/>");
            out.print("</form></div><div style='margin-left:3px;'><i onclick=\"printSection('ImprimirTabla')\" class='fas fa-print' style='font-size:21px;'></i></div></div>");
            out.print("</div>");
            if (area.equals("GESTION DE CALIDAD") || area.equals("SISTEMAS")) {
                out.print("<div><a href='javascript:mostrar();'><img src='Interfaz/Contenido/Iconos/Plus.png' width='22' height='22'></a></div>");
            }
            out.print("<div style='display:flex;justify-content:space-evenly;padding:10px;'>");
            out.print("<div><b>FAMILIA: </b><b style='color:#292929'>" + obj_familia[4] + "</b></div><div><b>PRODUCTO: </b><b style='color:#292929'>" + obj_familia[1] + "</b></div>");
            out.print("</div>");

            if (!lst_defectos.isEmpty()) {
                //<editor-fold defaultstate="collapsed" desc="VISTA NUEVA">
                out.print("<style>");
                out.print(".estatico { position: sticky; top: 0; }");
                out.print(".estatico3 { position: sticky; top: 81px; }"); // Ajusta este valor según la altura del primer bloque
                out.print("</style>");
                out.print("<script>");
                out.print("document.addEventListener('DOMContentLoaded', function() {");
                out.print("  var alturaBloque2 = document.querySelector('.estatico').offsetHeight;");
                out.print("  document.querySelector('.estatico3').style.top = alturaBloque2 + 'px';");
                out.print("});");
                out.print("</script>");
                out.print("<div id='ImprimirTabla'>");
                out.print("<table style='width:100%;'>");
                out.print("<tr>");
                out.print("<td></td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td>");
                out.print("<table class='table'  id='resultados' style='width:100%;border-top: 1px solid #d1d1d1;border-left: 1px solid #d1d1d1;border-right: 1px solid #d1d1d1;'>");
                out.print("<tr>");
                out.print("<td colspan='5' style='background-color:#979595;' align='center'><b style='color:white;'>COPIA NO CONTROLADA</b></td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td class='estatico' align='center' colspan='2' style='width:30%'><b><img src='Interfaz/Contenido/images/Logo.png' style='width:60%;'></b></td>");
                out.print("<td class='estatico' align='center' colspan='2'><b class='negro'>REGISTRO</b><hr/><b class='negro'>CATÁLOGO DE DEFECTOS</b></td>");
                out.print("<td class='estatico' align='center'><b>Codigo: </b><b class='negro'>R-GC-211</b><hr /><b>Version: </b><b class='negro'>000</b></td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td class='estatico3' colspan='5' style='background: #CAA427;'></td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td class='estatico3' align='center' style='width:16%;font-weight: bold;'>CÓDIGO DEFECTO</td>");
                out.print("<td class='estatico3' align='center' style='width:16%;font-weight: bold;'>NOMBRE DEL DEFECTO</td>");
                out.print("<td class='estatico3' align='center' style='width:30%;font-weight: bold;'>IMAGEN</td>");
                out.print("<td class='estatico3' align='center' style='width:6%;font-weight: bold;'>CLASIFICACIÓN</td>");
                out.print("<td class='estatico3' align='center' style='width:30%;font-weight: bold;'>TRATAMIENTO</td>");
                out.print("</tr>");
                for (int i = 0; i < lst_defectos.size(); i++) {
                    Object[] obj_defectos = (Object[]) lst_defectos.get(i);
                    String[] arg_descripcion = obj_defectos[6].toString().replace("<hr />", "<hr/>").split("<hr/>");
                    if ((Integer) obj_defectos[1] == 1) {
                        //<editor-fold defaultstate="collapsed" desc="CRITICO">
                        out.print("<tr>");
                        out.print("<td align='center' style='background:#FFE0E0'><b>" + obj_defectos[7] + "</b></td>");
                        out.print("<td align='center' style='background:#FFE0E0'>" + obj_defectos[5] + "</td>");
                        out.print("<td align='center' style='background:#FFE0E0'>" + arg_descripcion[0].replace("<strong>Imagen</strong>", "") + "</td>");
                        out.print("<td align='center' style='background:#FFE0E0'>" + obj_defectos[2] + "</td>");
                        out.print("<td valign='top' style='background:#FFE0E0'>" + arg_descripcion[1].replace("<strong>Tratamiento</strong>", "") + "</td>");
                        out.print("</tr>");
                        //</editor-fold>
                    } else if ((Integer) obj_defectos[1] == 2) {
                        //<editor-fold defaultstate="collapsed" desc="MAYOR">
                        out.print("<tr>");
                        out.print("<td align='center' style='background:#FFE3CB'><b>" + obj_defectos[7] + "</b></td>");
                        out.print("<td align='center' style='background:#FFE3CB'>" + obj_defectos[5] + "</td>");
                        out.print("<td align='center' style='background:#FFE3CB'>" + arg_descripcion[0].replace("<strong>Imagen</strong>", "") + "</td>");
                        out.print("<td align='center' style='background:#FFE3CB'>" + obj_defectos[2] + "</td>");
                        out.print("<td valign='top' style='background:#FFE3CB'>" + arg_descripcion[1].replace("<strong>Tratamiento</strong>", "") + "</td>");
                        out.print("</tr>");
                        //</editor-fold>
                    } else if ((Integer) obj_defectos[1] == 3) {
                        //<editor-fold defaultstate="collapsed" desc="MENOR">
                        out.print("<tr>");
                        out.print("<td align='center' style='background:#FFFEE0'><b>" + obj_defectos[7] + "</b></td>");
                        out.print("<td align='center' style='background:#FFFEE0'>" + obj_defectos[5] + "</td>");
                        out.print("<td align='center' style='background:#FFFEE0'>" + arg_descripcion[0].replace("<strong>Imagen</strong>", "") + "</td>");
                        out.print("<td align='center' style='background:#FFFEE0'>" + obj_defectos[2] + "</td>");
                        out.print("<td valign='top' style='background:#FFFEE0'>" + arg_descripcion[1].replace("<strong>Tratamiento</strong>", "") + "</td>");
                        out.print("</tr>");
                        //</editor-fold>
                    } else if ((Integer) obj_defectos[1] == 4) {
                        //<editor-fold defaultstate="collapsed" desc="LEVE">
                        out.print("<tr>");
                        out.print("<td align='center' style='background:#E6FECB'><b>" + obj_defectos[7] + "</b></td>");
                        out.print("<td align='center' style='background:#E6FECB'>" + obj_defectos[5] + "</td>");
                        out.print("<td align='center' style='background:#E6FECB'>" + arg_descripcion[0].replace("<strong>Imagen</strong>", "") + "</td>");
                        out.print("<td align='center' style='background:#E6FECB'>" + obj_defectos[2] + "</td>");
                        out.print("<td valign='top' style='background:#E6FECB'>" + arg_descripcion[1].replace("<strong>Tratamiento</strong>", "") + "</td>");
                        out.print("</tr>");
                        //</editor-fold>
                    }
                }
                out.print("</table>");
                out.print("</div>");
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
//            //<editor-fold defaultstate="collapsed" desc="VISTA ANTERIOR">
//            if (!lst_defectos.isEmpty()) {
//                out.print("<div id='NavPosicion'></div>");
//                out.print("<table class='table' id='resultados' style='width:100%;'>");
//                for (int i = 0; i < lst_defectos.size(); i++) {
//                    Object[] obj_defectos = (Object[]) lst_defectos.get(i);
//                    String[] arg_descripcion = obj_defectos[6].toString().replace("<hr />", "<hr/>").split("<hr/>");
//                    out.print("<tr>");
//                    out.print("<td colspan='4'></td>");
//                    out.print("</tr>");
//                    if ((Integer) obj_defectos[1] == 1) {
//                        //<editor-fold defaultstate="collapsed" desc="critico">
//                        out.print("<tr>");
//                        out.print("<td align='center' rowspan='2' style='background:#FFE0E0'><b>" + obj_defectos[7] + "</b></td>");
//                        out.print("<td align='center' style='background:#FFE0E0'><b>Defecto: </b>" + obj_defectos[5] + "</td>");
//                        out.print("<td align='center' colspan='2' style='background:#FFE0E0'><b>Clasificacion: </b>" + obj_defectos[2] + "</td>");
//                        out.print("</tr>");
//                        out.print("<tr>");
//                        out.print("<td align='center' style='width:50%;background:#FFE0E0'>" + arg_descripcion[0] + "</td>");
//                        if (area.equals("GESTION DE CALIDAD") || area.equals("SISTEMAS")) {
//                            out.print("<td valign='top' style='background:#FFE0E0'>" + arg_descripcion[1] + "</td>");
//                            out.print("<td align='center' style='background:#FFE0E0'><a href='Defecto?opc=1&idD=" + obj_defectos[0] + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a><hr />");
//                            if ((Integer) obj_defectos[8] != 0) {
//                                out.print("<a href='Defecto?opc=4&idD=" + obj_defectos[0] + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "&est=" + 0 + "'><img src='Interfaz/Contenido/Iconos/Check.png' width='22' height='22'></a></td>");
//                            } else {
//                                out.print("<a href='Defecto?opc=4&idD=" + obj_defectos[0] + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "&est=" + 1 + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22'></a></td>");
//                            }
//                        } else {
//                            out.print("<td valign='top' colspan='2' style='background:#FFE0E0'>" + arg_descripcion[1] + "</td>");
//                        }
//                        out.print("</tr>");
//                        //</editor-fold>
//                    } else if ((Integer) obj_defectos[1] == 2) {
//                        //<editor-fold defaultstate="collapsed" desc="mayor">
//                        out.print("<tr>");
//                        out.print("<td align='center' rowspan='2' style='background:#FFE3CB'><b>" + obj_defectos[7] + "</b></td>");
//                        out.print("<td align='center' style='background:#FFE3CB'><b>Defecto: </b>" + obj_defectos[5] + "</td>");
//                        out.print("<td align='center' colspan='2' style='background:#FFE3CB'><b>Clasificacion: </b>" + obj_defectos[2] + "</td>");
//                        out.print("</tr>");
//                        out.print("<tr>");
//                        out.print("<td align='center' style='width:50%;background:#FFE3CB'>" + arg_descripcion[0] + "</td>");
//                        if (area.equals("GESTION DE CALIDAD") || area.equals("SISTEMAS")) {
//                            out.print("<td valign='top' style='background:#FFE3CB'>" + arg_descripcion[1] + "</td>");
//                            out.print("<td align='center' style='background:#FFE3CB'><a href='Defecto?opc=1&idD=" + obj_defectos[0] + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a><hr />");
//                            if ((Integer) obj_defectos[8] != 0) {
//                                out.print("<a href='Defecto?opc=4&idD=" + obj_defectos[0] + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "&est=" + 0 + "'><img src='Interfaz/Contenido/Iconos/Check.png' width='22' height='22'></a></td>");
//                            } else {
//                                out.print("<a href='Defecto?opc=4&idD=" + obj_defectos[0] + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "&est=" + 1 + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22'></a></td>");
//                            }
//                        } else {
//                            out.print("<td valign='top' colspan='2' style='background:#FFE3CB'>" + arg_descripcion[1] + "</td>");
//                        }
//                        out.print("</tr>");
//                        //</editor-fold>
//                    } else if ((Integer) obj_defectos[1] == 3) {
//                        //<editor-fold defaultstate="collapsed" desc="menor">
//                        out.print("<tr>");
//                        out.print("<td align='center' rowspan='2' style='background:#FFFEE0'><b>" + obj_defectos[7] + "</b></td>");
//                        out.print("<td align='center' style='background:#FFFEE0'><b>Defecto: </b>" + obj_defectos[5] + "</td>");
//                        out.print("<td align='center' colspan='2' style='background:#FFFEE0'><b>Clasificacion: </b>" + obj_defectos[2] + "</td>");
//                        out.print("</tr>");
//                        out.print("<tr>");
//                        out.print("<td align='center' style='width:50%;background:#FFFEE0'>" + arg_descripcion[0] + "</td>");
//                        if (area.equals("GESTION DE CALIDAD") || area.equals("SISTEMAS")) {
//                            out.print("<td valign='top' style='background:#FFFEE0'>" + arg_descripcion[1] + "</td>");
//                            out.print("<td align='center' style='background:#FFFEE0'><a href='Defecto?opc=1&idD=" + obj_defectos[0] + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a><hr />");
//                            if ((Integer) obj_defectos[8] != 0) {
//                                out.print("<a href='Defecto?opc=4&idD=" + obj_defectos[0] + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "&est=" + 0 + "'><img src='Interfaz/Contenido/Iconos/Check.png' width='22' height='22'></a></td>");
//                            } else {
//                                out.print("<a href='Defecto?opc=4&idD=" + obj_defectos[0] + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "&est=" + 1 + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22'></a></td>");
//                            }
//                        } else {
//                            out.print("<td valign='top' colspan='2' style='background:#FFFEE0'>" + arg_descripcion[1] + "</td>");
//                        }
//                        out.print("</tr>");
//                        //</editor-fold>
//                    } else if ((Integer) obj_defectos[1] == 4) {
//                        //<editor-fold defaultstate="collapsed" desc="leve">
//                        out.print("<tr>");
//                        out.print("<td align='center' rowspan='2' style='background:#E6FECB'><b>" + obj_defectos[7] + "</b></td>");
//                        out.print("<td align='center' style='background:#E6FECB'><b>Defecto: </b>" + obj_defectos[5] + "</td>");
//                        out.print("<td align='center' colspan='2' style='background:#E6FECB'><b>Clasificacion: </b>" + obj_defectos[2] + "</td>");
//                        out.print("</tr>");
//                        out.print("<tr>");
//                        out.print("<td align='center' style='width:50%;background:#E6FECB'>" + arg_descripcion[0] + "</td>");
//                        if (area.equals("GESTION DE CALIDAD") || area.equals("SISTEMAS")) {
//                            out.print("<td valign='top' style='background:#E6FECB'>" + arg_descripcion[1] + "</td>");
//                            out.print("<td align='center' style='background:#E6FECB'><a href='Defecto?opc=1&idD=" + obj_defectos[0] + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a><hr />");
//                            if ((Integer) obj_defectos[8] != 0) {
//                                out.print("<a href='Defecto?opc=4&idD=" + obj_defectos[0] + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "&est=" + 0 + "'><img src='Interfaz/Contenido/Iconos/Check.png' width='22' height='22'></a></td>");
//                            } else {
//                                out.print("<a href='Defecto?opc=4&idD=" + obj_defectos[0] + "&idF=" + id_familiaP + "&txt_bus=" + filtro + "&est=" + 1 + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22'></a></td>");
//                            }
//                        } else {
//                            out.print("<td valign='top' colspan='2' style='background:#E6FECB'>" + arg_descripcion[1] + "</td>");
//                        }
//                        out.print("</tr>");
//                        //</editor-fold>
//                    }
//                }
//                out.print("</table>");
//                out.print("<script type='text/javascript'>");
//                out.print("var pager = new Pager('resultados',12);");
//                out.print("pager.init();");
//                out.print("pager.showPageNav('pager','NavPosicion');");
//                out.print("pager.showPage(1);");
//                out.print("</script>");
//            } else {
//                out.print("<h3><b style='color:#292929'>No se han encontrado resultados</b></h3>");
//            }
//             //</editor-fold>
            out.print("<div class='cleaner'></div></div>");
            out.print("<script>"
                    + "    CKEDITOR.replace(\"editor\");    "
                    + "</script>");
        } catch (IOException ex) {
            Logger.getLogger(Tag_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
