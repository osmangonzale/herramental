package Tags;

import controladores.HerramentalJpaController;
import controladores.InformeJpaController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_informe extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession sesion = pageContext.getSession();
        InformeJpaController jpa_informe = new InformeJpaController();
        HerramentalJpaController jpa_herramental = new HerramentalJpaController();
        String area = sesion.getAttribute("Area").toString();
        int id_herramental = Integer.parseInt(pageContext.getRequest().getAttribute("id_herramental").toString());
        int id_informe = Integer.parseInt(pageContext.getRequest().getAttribute("id_informe").toString());
        int consecutivo = Integer.parseInt(pageContext.getRequest().getAttribute("consecutivo").toString());
        List lst_informesP = jpa_informe.ConsultaInformeIdHerramentalPrototipo(id_herramental);
        List lst_informes = jpa_informe.ConsultaInformeIdHerramental(id_herramental);
        List lst_informeC = jpa_informe.ConsultaInformeCompleto(id_herramental);
        List lst_informe = null;
        List lst_informeF = jpa_informe.ConsultaInformeFechas(id_herramental);
        Object[] obj_informeF = (Object[]) lst_informeF.get(0);
        List lst_herramental = jpa_herramental.ConsultaHerramentalId(id_herramental);
        Object[] obj_herramental = (Object[]) lst_herramental.get(0);
        String ganttptp = "";
        String ganttM = "";
        List colores = new ArrayList();
        colores.add("ganttRed");
        colores.add("ganttGreen");
        colores.add("ganttOrange");
        colores.add("ganttYellow");
        colores.add("ganttAqua");
        colores.add("ganttFuchsia");
        colores.add("ganttBeyond");
        colores.add("ganttPurple");
        colores.add("ganttNavajo");
        colores.add("ganttSilver");
        colores.add("ganttSkyBlue");
        List coloresB = new ArrayList();
        coloresB.add("F9C4E1");
        coloresB.add("D8EDA3");
        coloresB.add("FCD29A");
        coloresB.add("FFFF88");
        coloresB.add("88FFFF");
        coloresB.add("FFAAFF");
        coloresB.add("7BCAE1");
        coloresB.add("C4ABFE");
        coloresB.add("FFDEAD");
        coloresB.add("C0C0C0");
        coloresB.add("87CEEB");
        try {
            out.print("<div id='content_sin'>");
            out.print("<img id='Menu_registro' style='float:left;position: absolute;'  src='Interfaz/Contenido/Iconos/Menu.png' width='20px' height='20px' alt='edit' title='Registrar' />");
            out.print("<div style='float:right'><a onclick='Imprimir();'><img src='Interfaz/Contenido/Iconos/Printer.png' style='width: 25px;height: 25px' alt='' title='Imprimir' /></a> Imprimir o PDF</div>");
            out.print("<script>");
            out.print("$(Menu_registro).click(function() {");
            out.print("$(\"#toggle6\").toggle(\"slide\");");
            out.print("});");
            out.print("</script>");
            if (id_informe != 0) {
                lst_informe = jpa_informe.ConsultaInformeId(id_herramental, id_informe);
                Object[] obj_informe = (Object[]) lst_informe.get(0);
                if (consecutivo == 1) {
                    //<editor-fold defaultstate="collapsed" desc="informe consecutivo">
                    out.print("<div style='display:block;' id=\"toggle6\">");
                    out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                    out.print("<form action='Informe?opc=2' method='post' name='formI' onsubmit='registroI();'>");
                    out.print("<input type='hidden' name='idH' value='" + id_herramental + "'>");
                    out.print("<input type='hidden' name='idI' value='" + id_informe + "'>");
                    out.print("<h3>Registro informe</h3>");
                    out.print("<table class='table' style='width:100%'>");
                    out.print("<tr>");
                    out.print("<td align='center'>");
                    out.print("<b>Prototipo?: </b>");
                    if ((Integer) obj_informe[3] == 1) {
                        out.print("<input type='checkbox' name='check_protD' value='1' id='id-protD' disabled checked>");
                        out.print("<input type='hidden' name='check_prot' value='1' id='id-prot'>");
                    } else {
                        out.print("<input type='checkbox' name='check_prot' value='1' id='id-prot' disabled>");
                    }
                    out.print("</td>");
                    out.print("<td align='center'>");
                    out.print("<b>Cantidad: </b>");
                    out.print("<input type='number' name='nmb_cantidad' min='0' value='0' id='id-cantidad' style='width:60px;'>&nbsp;&nbsp;&nbsp;");
                    out.print("<b>#: </b>");
                    out.print("<input type='number' name='nmb_numero' min='0' value='0' id='id-numero' style='width:60px;'>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td colspan='2'>");
                    out.print("<b>Descripcion: </b>");
                    out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 120'><div contenteditable='true'><p>*</p></div></textarea></td>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td>");
                    out.print("<b>Dimensiones: </b>");
                    out.print("<input type='text' name='txt_dimensiones' id='id-dimensiones' placeholder='Dimesiones'>");
                    out.print("</td>");
                    out.print("<td>");
                    out.print("<b>Material: </b>");
                    out.print("<input type='text' name='txt_material' id='id-material' placeholder='Material'>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td>");
                    out.print("<b>fecha inicio: </b>");
                    out.print("<input type='text' name='txt_fchIni' id='start' placeholder='fecha'>");
                    out.print("</td>");
                    out.print("<td>");
                    out.print("<b>fecha fin: </b>");
                    out.print("<input type='text' name='txt_fchFin' id='end' placeholder='fecha'>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("<td align='center' colspan='2'>");
                    out.print("<input type='submit' id='btsubmit' value='Guardar' onclick='Informe()'>");
                    out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "        </div>");
                    out.print("</td>");
                    out.print("<tr>");
                    out.print("</tr>");
                    out.print("</table>");
                    out.print("</form>");
                    out.print("<div class='cleaner'></div></div>");
                    out.print("</div>");
                    //</editor-fold>
                } else {
                    //<editor-fold defaultstate="collapsed" desc="modificar informe">
                    out.print("<div style='display:block;' id=\"toggle6\">");
                    out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                    out.print("<form action='Informe?opc=3' method='post' name='formI' onsubmit='registroI();'>");
                    out.print("<input type='hidden' name='idH' value='" + id_herramental + "'>");
                    out.print("<input type='hidden' name='idI' value='" + id_informe + "'>");
                    out.print("<h3>Registro informe</h3>");
                    out.print("<table class='table' style='width:100%'>");
                    out.print("<tr>");
                    out.print("<td align='center'>");
                    out.print("<b>Prototipo?: </b>");
                    if ((Integer) obj_informe[3] == 1) {
                        out.print("<input type='checkbox' name='check_prot' value='1' id='id-protD' checked>");
                    } else {
                        out.print("<input type='checkbox' name='check_prot' value='1' id='id-prot'>");
                    }
                    out.print("</td>");
                    out.print("<td align='center'>");
                    out.print("<b>Cantidad: </b>");
                    out.print("<input type='number' name='nmb_cantidad' min='0' value='" + obj_informe[5] + "' id='id-cantidad' style='width:60px;'>&nbsp;&nbsp;&nbsp;");
                    out.print("<b>#: </b>");
                    out.print("<input type='number' name='nmb_numero' min='0' value='" + obj_informe[6] + "' id='id-numero' style='width:60px;'>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td colspan='2'>");
                    out.print("<b>Descripcion: </b>");
                    out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 120'>" + obj_informe[4].toString().replace("<div>", "<div contenteditable='true'>") + "</textarea></td>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td>");
                    out.print("<b>Dimensiones: </b>");
                    out.print("<input type='text' name='txt_dimensiones' id='id-dimensiones' value='" + obj_informe[7] + "' placeholder='Dimesiones'>");
                    out.print("</td>");
                    out.print("<td>");
                    out.print("<b>Material: </b>");
                    out.print("<input type='text' name='txt_material' id='id-material' value='" + obj_informe[7] + "' placeholder='Material'>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td>");
                    out.print("<b>fecha inicio: </b>");
                    out.print("<input type='text' name='txt_fchIni' id='datepicker' value='" + obj_informe[9] + "' placeholder='Fecha Inicio'>");
                    out.print("</td>");
                    out.print("<td>");
                    out.print("<b>fecha fin: </b>");
                    out.print("<input type='text' name='txt_fchFin' id='datepicker2' value='" + obj_informe[10] + "' placeholder='Fecha Fin'>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("<td align='center' colspan='2'>");
                    out.print("<input type='submit' id='btsubmit' value='Guardar' onclick='Informe()'>");
                    out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "        </div>");
                    out.print("</td>");
                    out.print("<tr>");
                    out.print("</tr>");
                    out.print("</table>");
                    out.print("</form>");
                    out.print("<div class='cleaner'></div></div>");
                    out.print("</div>");
                    //</editor-fold>
                }
            } else {
                //<editor-fold defaultstate="collapsed" desc="registrar informe">
                out.print("<div style='display:none;' id=\"toggle6\">");
                out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                out.print("<form action='Informe?opc=2' method='post' name='formI' onsubmit='registroI();'>");
                out.print("<input type='hidden' name='idH' value='" + id_herramental + "'>");
                out.print("<input type='hidden' name='idI' value='" + 0 + "'>");
                out.print("<h3>Registro informe</h3>");
                out.print("<table class='table' style='width:100%'>");
                out.print("<tr>");
                out.print("<td align='center'>");
                out.print("<b>Prototipo?: </b>");
                out.print("<input type='checkbox' name='check_prot' value='1' id='id-prot'>");
                out.print("</td>");
                out.print("<td align='center'>");
                out.print("<b>Cantidad: </b>");
                out.print("<input type='number' name='nmb_cantidad' min='0' value='0' id='id-cantidad' style='width:60px;'>&nbsp;&nbsp;&nbsp;");
                out.print("<b>#: </b>");
                out.print("<input type='number' name='nmb_numero' min='0' value='0' id='id-numero' style='width:60px;'>");
                out.print("</td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td colspan='2'>");
                out.print("<b>Descripcion: </b>");
                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 120'><div contenteditable='true'><p>*</p></div></textarea></td>");
                out.print("</td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td>");
                out.print("<b>Dimensiones: </b>");
                out.print("<input type='text' name='txt_dimensiones' id='id-dimensiones' placeholder='Dimesiones'>");
                out.print("</td>");
                out.print("<td>");
                out.print("<b>Material: </b>");
                out.print("<input type='text' name='txt_material' id='id-material' placeholder='Material'>");
                out.print("</td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td>");
                out.print("<b>fecha inicio: </b>");
                out.print("<input type='text' name='txt_fchIni' id='start' placeholder='fecha'>");
                out.print("</td>");
                out.print("<td>");
                out.print("<b>fecha fin: </b>");
                out.print("<input type='text' name='txt_fchFin' id='end' placeholder='fecha'>");
                out.print("</td>");
                out.print("</tr>");
                out.print("<td align='center' colspan='2'>");
                out.print("<input type='submit' id='btsubmit' value='Guardar' onclick='Informe()'>");
                out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "        </div>");
                out.print("</td>");
                out.print("<tr>");
                out.print("</tr>");
                out.print("</table>");
                out.print("</form>");
                out.print("<div class='cleaner'></div></div>");
                out.print("</div>");
                //</editor-fold>
            }
            out.print("<br />");
            out.print("<div id='tab-container' style='margin-top: 10px;'>");
            //<editor-fold defaultstate="collapsed" desc="Prototipo">
            out.print("<div class='tab-content'>");
            out.print("<h1 class='tab' title='Prototipo'>Prototipo</h1>");
            if (!lst_informesP.isEmpty()) {
                out.print("<table class='table' style='width:100%'>");
                out.print("<tr>");
                out.print("<th colspan='2'>Cantidad</th>");
                out.print("<th>#</th>");
                out.print("<th>Descripcion</th>");
                out.print("<th>Dimensiones</th>");
                out.print("<th>Material</th>");
                out.print("<th style='width:20%'>Fecha</th>");
                out.print("<th>Duracion</th>");
                out.print("<th>Modificar</th>");
                out.print("</tr>");
                for (int i = 0; i < lst_informesP.size(); i++) {
                    out.print("<tr>");
                    Object[] obj_informes = (Object[]) lst_informesP.get(i);
                    if (obj_informes[2] == null) {
                        out.print("<td align='center'><a href='Informe?opc=1&idH=" + id_herramental + "&idI=" + obj_informes[0] + "&Cons=1'><img src='Interfaz/Contenido/Iconos/Plus.png' alt='Logo' width='20' height='20' /></a></td>");
                        out.print("<td align='center'>" + obj_informes[5] + "</td>");
                    } else {
                        out.print("<td align='center'>&raquo;</td>");
                        out.print("<td align='center'>N/A</td>");
                    }
                    out.print("<td align='center'>" + obj_informes[6] + "</td>");
                    out.print("<td valign='top'>" + obj_informes[4] + "</td>");
                    out.print("<td align='center'>" + obj_informes[7] + "</td>");
                    out.print("<td align='center'>" + obj_informes[8] + "</td>");
                    out.print("<td align='center'><b>Inicio: </b>" + obj_informes[9] + "&nbsp;&nbsp;<b>Fin: </b>" + obj_informes[10] + "</td>");
                    out.print("<td align='center'>" + obj_informes[11] + "</td>");
                    out.print("<td align='center'><a href='Informe?opc=1&idH=" + id_herramental + "&idI=" + obj_informes[0] + "&Cons=0'><img src='Interfaz/Contenido/Iconos/Edit.png' alt='Logo' width='20' height='20' /></a></td>");
                    out.print("</tr>");
                }
                out.print("</table>");
            } else {
                out.print("<b>No se encontraron resultados</b>");
            }
            out.print("</div>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Molde">
            out.print("<div class='tab-content'>");
            out.print("<h1 class='tab' title='Molde'>Molde</h1>");
            if (!lst_informes.isEmpty()) {
                out.print("<table class='table' style='width:100%'>");
                out.print("<tr>");
                out.print("<th colspan='2'>Cantidad</th>");
                out.print("<th>#</th>");
                out.print("<th>Descripcion</th>");
                out.print("<th>Dimensiones</th>");
                out.print("<th>Material</th>");
                out.print("<th style='width:20%'>Fecha</th>");
                out.print("<th>Duracion</th>");
                out.print("<th>Modificar</th>");
                out.print("</tr>");
                for (int i = 0; i < lst_informes.size(); i++) {
                    out.print("<tr>");
                    Object[] obj_informes = (Object[]) lst_informes.get(i);
                    if (obj_informes[2] == null) {
                        out.print("<td align='center'><a href='Informe?opc=1&idH=" + id_herramental + "&idI=" + obj_informes[0] + "&Cons=1'><img src='Interfaz/Contenido/Iconos/Plus.png' alt='Logo' width='20' height='20' /></a></td>");
                        out.print("<td align='center'>" + obj_informes[5] + "</td>");
                    } else {
                        out.print("<td align='center'>&raquo;</td>");
                        out.print("<td align='center'>N/A</td>");
                    }
                    out.print("<td align='center'>" + obj_informes[6] + "</td>");
                    out.print("<td valign='top'>" + obj_informes[4] + "</td>");
                    out.print("<td align='center'>" + obj_informes[7] + "</td>");
                    out.print("<td align='center'>" + obj_informes[8] + "</td>");
                    out.print("<td align='center'><b>Inicio: </b>" + obj_informes[9] + "&nbsp;&nbsp;<b>Fin: </b>" + obj_informes[10] + "</td>");
                    out.print("<td align='center'>" + obj_informes[11] + "</td>");
                    out.print("<td align='center'><a href='Informe?opc=1&idH=" + id_herramental + "&idI=" + obj_informes[0] + "&Cons=0'><img src='Interfaz/Contenido/Iconos/Edit.png' alt='Logo' width='20' height='20' /></a></td>");
                    out.print("</tr>");
                }
                out.print("</table>");
            } else {
                out.print("<b>No se encontraron resultados</b>");
            }
            out.print("</div>");
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Gannt">
            out.print("<div class='tab-content'>");
            out.print("<h1 class='tab' title='Gantt'>Gantt</h1>");
            if (!lst_informeC.isEmpty()) {
                ganttptp = ganttptp + "{";
                ganttptp = ganttptp + "name: \"Prototipo: " + obj_herramental[4] + "\",";
                ganttptp = ganttptp + "values: [{";
                ganttptp = ganttptp + "from: \"/Date(" + obj_informeF[0] + ")/\",";
                ganttptp = ganttptp + "to: \"/Date(" + obj_informeF[1] + ")/\",";
                ganttptp = ganttptp + "label: \"Prototipo: " + obj_herramental[4] + "\",";
                ganttptp = ganttptp + "customClass: \"ganttAqua\"";
                ganttptp = ganttptp + "}]";
                ganttptp = ganttptp + "}";

                ganttM = ganttM + ",{";
                ganttM = ganttM + "name: \"" + obj_herramental[4] + "\",";
                ganttM = ganttM + "values: [{";
                ganttM = ganttM + "from: \"/Date(" + obj_informeF[2] + ")/\",";
                ganttM = ganttM + "to: \"/Date(" + obj_informeF[3] + ")/\",";
                ganttM = ganttM + "label: \"" + obj_herramental[4] + "\",";
                ganttM = ganttM + "customClass: \"ganttAqua\"";
                ganttM = ganttM + "}]";
                ganttM = ganttM + "}";
                out.print("<script>");
                out.print("$(function () {");
                out.print("$(\".gantt\").gantt({");
                out.print("source: [");
                out.print("" + ganttptp + "");
                for (int i = 0; i < lst_informeC.size(); i++) {
                    int rand = (int) (Math.random() * colores.size());
                    Object[] obj_informeC = (Object[]) lst_informeC.get(i);
                    if (i != 0) {
                        Object[] obj_informeCA = (Object[]) lst_informeC.get(i - 1);
                        if ((Integer) obj_informeC[3] == 0 && (Integer) obj_informeCA[3] == 1) {
                            out.print("" + ganttM + "");
                        }
                    }
                    out.print(",{");
                    String des = obj_informeC[4].toString().replace("<div>", "");
                    des = des.toString().replace("</div>", "");
                    des = des.toString().replace("<p>", "");
                    des = des.toString().replace("</p>", "");
//                    String desc = obj_informeC[4].toString().replace("<p>", "<p class='tooltip2' style='margin-top: 0px;margin-bottom: 0px;'><span class='tooltiptext' style='border:2px solid #" + coloresB.get(rand) + ";'  valign='top'>" + des + "</span>");
//                    desc = desc.toString().replace("<div>", "");
//                    desc = desc.toString().replace("</div>", "");
                    String desc = "<p class='tooltip2' style='margin-top: 0px;margin-bottom: 0px;'><span class='tooltiptext' style='border:2px solid #" + coloresB.get(rand) + ";'  valign='top'>" + des.replace("\"", "'") + "</span>" + des.replace("\"", "'") + "</p>";
                    if (obj_informeC[2] == null) {
                        out.print("name: \"" + desc + "\",");
                    } else {
                        out.print("desc: \"" + desc + "\",");
                    }
                    out.print("values: [{");
                    out.print("from: \"/Date(" + obj_informeC[13] + ")/\",");
                    out.print("to: \"/Date(" + obj_informeC[14] + ")/\",");
                    out.print("label: \"De: " + obj_informeC[9] + " a: " + obj_informeC[10] + "\",");
                    out.print("customClass: \"" + colores.get(rand) + "\"");
                    out.print("}]");
                    out.print("}");

                }
                out.print("],");
                out.print("navigate: \"scroll\",");
                out.print("scale: \"days\",");
                out.print("maxScale: \"months\",");
                out.print("minScale: \"hours\",");
                out.print("months: [\"Enero\", \"Febrero\", \"Marzo\", \"Abril\", \"Mayo\", \"Junio\", \"Julio\", \"Agosto\", \"Septiembre\", \"Octubre\", \"Noviembre\", \"Diciembre\"],");
                out.print("dow: [\"D\", \"L\", \"M\", \"M\", \"J\", \"V\", \"S\"],");
                out.print("itemsPerPage: 100,");
                out.print("useCookie: false");
                out.print("});");
                out.print("});");
                out.print("</script>");
                out.print("<div id='ganttPrint' class=\"gantt\"></div>");
            } else {
                out.print("<b>No se encontraron resultados</b>");
            }
            out.print("</div>");
//</editor-fold>
            out.print("</div>");
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
