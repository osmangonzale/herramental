package Tags;

import controladores.ClasificacionDefectoJpaController;
import controladores.FamiliaProductoJpaController;
import controladores.TipoMaquinaJpaController;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Consulta_catalogo extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        TipoMaquinaJpaController jpa_tipoM = new TipoMaquinaJpaController();
        FamiliaProductoJpaController jpa_familia = new FamiliaProductoJpaController();
        ClasificacionDefectoJpaController jpa_clasificacionD = new ClasificacionDefectoJpaController();
        String filtro = (String) pageContext.getRequest().getAttribute("filtro");
        int id_familiaP = Integer.parseInt(pageContext.getRequest().getAttribute("id_familiaP").toString());
        try {
            if (pageContext.getRequest().getAttribute("Consulta_Catalogo").toString().equals("familias")) {
                int filtroND = Integer.parseInt(pageContext.getRequest().getAttribute("nuevos_defectos").toString());
                List lst_familiasP = (List) pageContext.getRequest().getAttribute("consulta_familiaP");
                out.print("<div id='content_sin'>");
                out.print("<div style='float:right;'>");
                out.print("<form method='post' action='Consulta_catologo?opc=1&idF=" + 0 + "'>");
                out.print("<input name='txt_bus' type='text' class='input_field' placeholder='Buscar'><br/>");
                out.print("</form>");
                out.print("</div>");
                out.print("<div style='float:right;'>");
                out.print("<a href='Consulta_catologo?opc=1&idF=" + 0 + "&txt_bus=&filtroND=1'><img src='Interfaz/Contenido/Iconos/Search.png' width='21' height='21' title='filtro' style='margin-right: 10px;'></a>");
                out.print("</div>");
                if (!filtro.equals("")) {
                    out.print("<a href='Consulta_catologo?opc=1&idF=" + 0 + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a>");
                }
                out.print("<h3>Familias de productos</h3>");
                if (!lst_familiasP.isEmpty()) {
                    if (filtroND == 1) {
                        out.print("<div class='overlay' tabindex='-1' id='bloqn' style='opacity: 1.06; display: block;'>");
                        out.print("<fieldset class='resalta' id='NuevaNovedad' style='visibility: visible;width:100px;margin-left: -150px;margin-top: -90px;'>");
                        out.print("<div style='float:right;'><a href='Consulta_catologo?opc=1&idF=0&txt_bus='><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
                        out.print("<h3>Filtro Nuevos defectos</h3>");
                        out.print("<form action='Consulta_catologo?opc=3&txt_bus=' method='post' name='formFil'>");
                        out.print("<b>Fecha Inicio:</b><br />");
                        out.print("<input type='text' name='txt_fechaID' id=\"datepicker\" value=''  placeholder='Fecha inicio' style='margin-bottom: 0px;'>");
                        out.print("<br /><br/>");
                        out.print("<b>Fecha Fin:</b><br />");
                        out.print("<input type='text' name='txt_fechaFD' id=\"datepicker2\" value=''  placeholder='Fecha fin' style='margin-bottom: 0px;'>");
                        out.print("<br/><br/>");
                        out.print("<b>Filtro:</b><br />");
                        out.print("<input type='text' name='txt_filtro' id='filtro-id' value=''  placeholder='Buscar' style='margin-bottom: 0px;'>");
                        out.print("<br/><br/>");
                        out.print("<input type='submit' id='btsubmit' value='Consultar'>");
                        out.print("</form>");
                        out.print("</fieldset>");
                        out.print("</div>");
                    }
                    out.print("<div id='NavPosicion'></div>");
                    out.print("<table class='table' id='resultados' style='width:100%;'>");
                    out.print("<tr>");
                    out.print("<th>Nombre</th>");
                    out.print("<th>Defectos</th>");
                    out.print("<th>Critico</th>");
                    out.print("<th>Mayor</th>");
                    out.print("<th>Menor</th>");
                    out.print("<th>Leve</th>");
                    out.print("<th>Total defectos</th>");
                    out.print("<th>Tipo</th>");
                    out.print("</tr>");
                    for (int i = 0; i < lst_familiasP.size(); i++) {
                        Object[] obj_familiaP = (Object[]) lst_familiasP.get(i);
                        String clas = new String((byte[]) obj_familiaP[7], Charset.defaultCharset());
                        String[] clasificaciones = clas.split("-");
//                        String[] clasificaciones = obj_familiaP[7].toString().split("-");
                        out.print("<tr>");
                        if ((Integer) obj_familiaP[2] == 0) {
                            if (Integer.parseInt(obj_familiaP[5].toString()) != 0) {
                                out.print("<td><div class='contenedor'><span class='burbuja3'>Nuevo : " + obj_familiaP[5] + "</span></div><b style='color:red;'>" + obj_familiaP[1] + "</b></td>");
                            } else {
                                out.print("<td><b style='color:red;'>" + obj_familiaP[1] + "</b></td>");
                            }
                            out.print("<td align='center'><b style='color:red;'>" + obj_familiaP[4] + "</b></td>");
                            for (int j = 0; j < clasificaciones.length; j++) {
                                if (j == 0) {
                                    out.print("<td align='center' style='background:#FFE0E0'><b style='color:red;'>" + clasificaciones[j] + "</b></td>");
                                } else if (j == 1) {
                                    out.print("<td align='center' style='background:#FFE3CB'><b style='color:red;'>" + clasificaciones[j] + "</b></td>");
                                } else if (j == 2) {
                                    out.print("<td align='center' style='background:#FFFEE0'><b style='color:red;'>" + clasificaciones[j] + "</b></td>");
                                } else if (j == 3) {
                                    out.print("<td align='center' style='background:#E6FECB'><b style='color:red;'>" + clasificaciones[j] + "</b></td>");
                                }
                            }
                            out.print("<td align='center'><b style='color:red;'>" + obj_familiaP[6] + "</b></td>");
                            out.print("<td align='center' style='width:10%;'><a href='Consulta_catologo?opc=2&idD=" + 0 + "&idF=" + obj_familiaP[0] + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Document.png' alt='Logo' width='22' height='22.5' /></a></td>");
                        } else {
                            if (Integer.parseInt(obj_familiaP[5].toString()) != 0) {
                                out.print("<td><div class='contenedor'><span class='burbuja3'>Nuevo: " + obj_familiaP[5] + "</span></div>" + obj_familiaP[1] + "</td>");
                            } else {
                                out.print("<td><div class='contenedor'>" + obj_familiaP[1] + "</td>");
                            }
                            out.print("<td>" + obj_familiaP[4] + "</td>");
                            for (int j = 0; j < clasificaciones.length; j++) {
                                if (j == 0) {
                                    out.print("<td align='center' style='background:#FFE0E0'>" + clasificaciones[j] + "</td>");
                                } else if (j == 1) {
                                    out.print("<td align='center' style='background:#FFE3CB'>" + clasificaciones[j] + "</td>");
                                } else if (j == 2) {
                                    out.print("<td align='center' style='background:#FFFEE0'>" + clasificaciones[j] + "</td>");
                                } else if (j == 3) {
                                    out.print("<td align='center' style='background:#E6FECB'>" + clasificaciones[j] + "</td>");
                                }
                            }
                            out.print("<td align='center'>" + obj_familiaP[6] + "</td>");
                            out.print("<td align='center' style='width:10%;'><a href='Consulta_catologo?opc=2&idD=" + 0 + "&idF=" + obj_familiaP[0] + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Document.png' alt='Logo' width='22' height='22.5' /></a></td>");
                        }
                        out.print("</tr>");
                    }
                    out.print("</table>");
                    out.print("<script type='text/javascript'>");
                    out.print("var pager = new Pager('resultados',15);");
                    out.print("pager.init();");
                    out.print("pager.showPageNav('pager','NavPosicion');");
                    out.print("pager.showPage(1);");
                    out.print("</script>");
                    out.print("<div style='float:right;'>");
                    SimpleDateFormat formato = new SimpleDateFormat("MMMM", new Locale("es"));
                    Date fechaDate = new Date();
                    String fecha = formato.format(fechaDate);
                    out.print("<b style='color:#292929'>Las familias con <span style='background: none repeat scroll 0 0 #f44336;color: #FFFFFF;'> Nuevo : # </span> tiene  nuevos defectos registrados del mes de " + fecha + "</b>");
                    out.print("</div>");
                } else {
                    out.print("<h3><b style='color:#292929'>No se han encontrado resultados</b></h3");
                }
                out.print("<br />");
                out.print("<br />");
                out.print("<h3>Clasificacion Defectos</h3>");
                List lst_clasificaciones = jpa_clasificacionD.ConsultaClasificacion();
                if (!lst_clasificaciones.isEmpty()) {
                    out.print("<div id='NavPosicion'></div>");
                    out.print("<table class='table' id='resultados' style='width:100%;'>");
                    out.print("<tr>");
                    out.print("<th>Convencion</th>");
                    out.print("<th>Defecto</th>");
                    out.print("<th>Descripcion</th>");
                    out.print("</tr>");
                    for (int i = 0; i < lst_clasificaciones.size(); i++) {
                        Object[] obj_clasificacion = (Object[]) lst_clasificaciones.get(i);
                        out.print("<tr>");
                        out.print("<td align='center' style='width:5%;'>" + obj_clasificacion[3] + "</td>");
                        out.print("<td align='center' style='width:25%;'>" + obj_clasificacion[1] + "</td>");
                        out.print("<td valign='top'>" + obj_clasificacion[2] + "</td>");
                        out.print("</tr>");
                    }
                    out.print("</table>");
                }
                out.print("<div class='cleaner'></div></div>");
            }

            if (pageContext.getRequest().getAttribute("Consulta_Catalogo").toString().equals("defectos")) {
                List lst_defectos = (List) pageContext.getRequest().getAttribute("consulta_defectos");
                List lst_familia = jpa_familia.ConsultaFamiliaProductoId(id_familiaP);
                Object[] obj_familia = (Object[]) lst_familia.get(0);
                out.print("<div id='content_sin'>");
                out.print("<div style='display:flex;justify-content:space-between;'>");
                if (!filtro.equals("")) {
                    out.print("<div><a href='Consulta_catologo?opc=2&idD=" + 0 + "&idF=" + id_familiaP + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a></div>");
                } else {
                    out.print("<div><a href='Consulta_catologo?opc=1&idF=" + 0 + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a></div>");
                }
                out.print("<div style='display:flex;align-items:baseline;'><div><form method='post' action='Consulta_catologo?opc=2&idD=" + 0 + "&idF=" + id_familiaP + "'>");
                out.print("<input name='txt_bus' type='text' class='input_field' placeholder='Buscar'><br/>");
                out.print("</form></div><div style='margin-left:3px;'><i onclick=\"printSection('ImprimirTabla')\" class='fas fa-print' style='font-size:21px;'></i></div></div>");
                out.print("</div>");
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
                //                    //<editor-fold defaultstate="collapsed" desc="VISTA ANTERIOR">
//                    out.print("<div id='NavPosicion'></div>");
//                    out.print("<table class='table' id='resultados' style='width:100%;'>");
//                    for (int i = 0; i < lst_defectos.size(); i++) {
//                        Object[] obj_defectos = (Object[]) lst_defectos.get(i);
//                        String[] arg_descripcion = obj_defectos[6].toString().replace("<hr />", "<hr/>").split("<hr/>");
//                        out.print("<tr>");
//                        out.print("<td colspan='4'></td>");
//                        out.print("</tr>");
//                        if ((Integer) obj_defectos[1] == 1) {
//                            //<editor-fold defaultstate="collapsed" desc="critico">
//                            out.print("<tr>");
//                            out.print("<td align='center' rowspan='2' style='background:#FFE0E0'><b>" + obj_defectos[7] + "</b></td>");
//                            out.print("<td align='center' style='background:#FFE0E0'><b>Defecto: </b>" + obj_defectos[5] + "</td>");
//                            out.print("<td align='center' colspan='2' style='background:#FFE0E0'><b>Clasificacion: </b>" + obj_defectos[2] + "</td>");
//                            out.print("</tr>");
//                            out.print("<tr>");
//                            out.print("<td align='center' style='width:50%;background:#FFE0E0'>" + arg_descripcion[0] + "</td>");
//                            out.print("<td valign='top' colspan='2' style='background:#FFE0E0'>" + arg_descripcion[1] + "</td>");
//                            out.print("</tr>");
//                            //</editor-fold>
//                        } else if ((Integer) obj_defectos[1] == 2) {
//                            //<editor-fold defaultstate="collapsed" desc="mayor">
//                            out.print("<tr>");
//                            out.print("<td align='center' rowspan='2' style='background:#FFE3CB'><b>" + obj_defectos[7] + "</b></td>");
//                            out.print("<td align='center' style='background:#FFE3CB'><b>Defecto: </b>" + obj_defectos[5] + "</td>");
//                            out.print("<td align='center' colspan='2' style='background:#FFE3CB'><b>Clasificacion: </b>" + obj_defectos[2] + "</td>");
//                            out.print("</tr>");
//                            out.print("<tr>");
//                            out.print("<td align='center' style='width:50%;background:#FFE3CB'>" + arg_descripcion[0] + "</td>");
//                            out.print("<td valign='top' colspan='2' style='background:#FFE3CB'>" + arg_descripcion[1] + "</td>");
//                            out.print("</tr>");
//                            //</editor-fold>
//                        } else if ((Integer) obj_defectos[1] == 3) {
//                            //<editor-fold defaultstate="collapsed" desc="menor">
//                            out.print("<tr>");
//                            out.print("<td align='center' rowspan='2' style='background:#FFFEE0'><b>" + obj_defectos[7] + "</b></td>");
//                            out.print("<td align='center' style='background:#FFFEE0'><b>Defecto: </b>" + obj_defectos[5] + "</td>");
//                            out.print("<td align='center' colspan='2' style='background:#FFFEE0'><b>Clasificacion: </b>" + obj_defectos[2] + "</td>");
//                            out.print("</tr>");
//                            out.print("<tr>");
//                            out.print("<td align='center' style='width:50%;background:#FFFEE0'>" + arg_descripcion[0] + "</td>");
//                            out.print("<td valign='top' colspan='2' style='background:#FFFEE0'>" + arg_descripcion[1] + "</td>");
//                            out.print("</tr>");
//                            //</editor-fold>
//                        } else if ((Integer) obj_defectos[1] == 4) {
//                            //<editor-fold defaultstate="collapsed" desc="leve">
//                            out.print("<tr>");
//                            out.print("<td align='center' rowspan='2' style='background:#E6FECB'><b>" + obj_defectos[7] + "</b></td>");
//                            out.print("<td align='center' style='background:#E6FECB'><b>Defecto: </b>" + obj_defectos[5] + "</td>");
//                            out.print("<td align='center' colspan='2' style='background:#E6FECB'><b>Clasificacion: </b>" + obj_defectos[2] + "</td>");
//                            out.print("</tr>");
//                            out.print("<tr>");
//                            out.print("<td align='center' style='width:50%;background:#E6FECB'>" + arg_descripcion[0] + "</td>");
//                            out.print("<td valign='top' colspan='2' style='background:#E6FECB'>" + arg_descripcion[1] + "</td>");
//                            out.print("</tr>");
//                            //</editor-fold>
//                        }
//                    }
//                    out.print("</table>");
//                    out.print("<script type='text/javascript'>");
//                    out.print("var pager = new Pager('resultados',12);");
//                    out.print("pager.init();");
//                    out.print("pager.showPageNav('pager','NavPosicion');");
//                    out.print("pager.showPage(1);");
//                    out.print("</script>");
//                    //</editor-fold>
                out.print("<div class='cleaner'></div></div>");
            }
            if (pageContext.getRequest().getAttribute("Consulta_Catalogo").toString().equals("defectos_filtro")) {
                List lst_familiasP = (List) pageContext.getRequest().getAttribute("consulta_familiaP");
                List lst_defectos = null;
                String filtro2 = (String) pageContext.getRequest().getAttribute("filtro2");
                String fechaI = (String) pageContext.getRequest().getAttribute("fecha_inicio");
                String fechaF = (String) pageContext.getRequest().getAttribute("fecha_fin");
                out.print("<div id='content_sin'>");
                out.print("<div style='float:right'><b>Nuevos defectos de: </b>" + fechaI + "<b> a </b>" + fechaF + "</div>");
                out.print("<a href='Consulta_catologo?opc=1&idF=0&txt_bus='><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a>");
                for (int i = 0; i < lst_familiasP.size(); i++) {
                    Object[] obj_familias = (Object[]) lst_familiasP.get(i);
                    lst_defectos = jpa_familia.ConsultaDefectosFiltroFechas(Integer.parseInt(obj_familias[0].toString()), fechaI, fechaF, filtro2);
                    if (lst_defectos != null) {
                        out.print("<button class='accordion'><div class='contenedor' style='float:right'><span class='burbuja' style='font: bold 0.7em Tahoma,Arial,Helvetica;height:11px;'>" + lst_defectos.size() + "</span></div><b style='color:black;float:right;margin-right: 5px;'>" + obj_familias[1] + "</b></button>");
                        out.print("<div class='panel' style='overflow:scroll;'>");
                        out.print("<table class='table' style='width:100%'>");
                        for (int j = 0; j < lst_defectos.size(); j++) {
                            Object[] obj_defectos = (Object[]) lst_defectos.get(j);
                            String[] arg_descripcion = obj_defectos[6].toString().replace("<hr />", "<hr/>").split("<hr/>");
                            out.print("<tr>");
                            out.print("<td colspan='4'></td>");
                            out.print("</tr>");
                            if ((Integer) obj_defectos[1] == 1) {
                                //<editor-fold defaultstate="collapsed" desc="critico">
                                out.print("<tr>");
                                out.print("<td align='center' rowspan='2' style='background:#FFE0E0'><b>" + obj_defectos[7] + "</b></td>");
                                out.print("<td align='center' style='background:#FFE0E0'><b>Defecto: </b>" + obj_defectos[5] + "</td>");
                                out.print("<td align='center' colspan='2' style='background:#FFE0E0'><b>Clasificacion: </b>" + obj_defectos[2] + "</td>");
                                out.print("</tr>");
                                out.print("<tr>");
                                out.print("<td align='center' style='width:50%;background:#FFE0E0'>" + arg_descripcion[0] + "</td>");
                                out.print("<td valign='top' colspan='2' style='background:#FFE0E0'>" + arg_descripcion[1] + "</td>");
                                out.print("</tr>");
                                //</editor-fold>
                            } else if ((Integer) obj_defectos[1] == 2) {
                                //<editor-fold defaultstate="collapsed" desc="mayor">
                                out.print("<tr>");
                                out.print("<td align='center' rowspan='2' style='background:#FFE3CB'><b>" + obj_defectos[7] + "</b></td>");
                                out.print("<td align='center' style='background:#FFE3CB'><b>Defecto: </b>" + obj_defectos[5] + "</td>");
                                out.print("<td align='center' colspan='2' style='background:#FFE3CB'><b>Clasificacion: </b>" + obj_defectos[2] + "</td>");
                                out.print("</tr>");
                                out.print("<tr>");
                                out.print("<td align='center' style='width:50%;background:#FFE3CB'>" + arg_descripcion[0] + "</td>");
                                out.print("<td valign='top' colspan='2' style='background:#FFE3CB'>" + arg_descripcion[1] + "</td>");
                                out.print("</tr>");
                                //</editor-fold>
                            } else if ((Integer) obj_defectos[1] == 3) {
                                //<editor-fold defaultstate="collapsed" desc="menor">
                                out.print("<tr>");
                                out.print("<td align='center' rowspan='2' style='background:#FFFEE0'><b>" + obj_defectos[7] + "</b></td>");
                                out.print("<td align='center' style='background:#FFFEE0'><b>Defecto: </b>" + obj_defectos[5] + "</td>");
                                out.print("<td align='center' colspan='2' style='background:#FFFEE0'><b>Clasificacion: </b>" + obj_defectos[2] + "</td>");
                                out.print("</tr>");
                                out.print("<tr>");
                                out.print("<td align='center' style='width:50%;background:#FFFEE0'>" + arg_descripcion[0] + "</td>");
                                out.print("<td valign='top' colspan='2' style='background:#FFFEE0'>" + arg_descripcion[1] + "</td>");
                                out.print("</tr>");
                                //</editor-fold>
                            } else if ((Integer) obj_defectos[1] == 4) {
                                //<editor-fold defaultstate="collapsed" desc="leve">
                                out.print("<tr>");
                                out.print("<td align='center' rowspan='2' style='background:#E6FECB'><b>" + obj_defectos[7] + "</b></td>");
                                out.print("<td align='center' style='background:#E6FECB'><b>Defecto: </b>" + obj_defectos[5] + "</td>");
                                out.print("<td align='center' colspan='2' style='background:#E6FECB'><b>Clasificacion: </b>" + obj_defectos[2] + "</td>");
                                out.print("</tr>");
                                out.print("<tr>");
                                out.print("<td align='center' style='width:50%;background:#E6FECB'>" + arg_descripcion[0] + "</td>");
                                out.print("<td valign='top' colspan='2' style='background:#E6FECB'>" + arg_descripcion[1] + "</td>");
                                out.print("</tr>");
                                //</editor-fold>
                            }
                        }
                        out.print("</table>");
                        out.print("</div>");
                    }
                }
                out.print("<div class='cleaner'></div></div>");
            }
        } catch (IOException ex) {
            Logger.getLogger(Tag_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return super.doStartTag();
    }
}
