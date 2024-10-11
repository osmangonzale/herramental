package Tags;

import controladores.FamiliaProductoJpaController;
import controladores.RolJpaController;
import controladores.TipoMaquinaJpaController;
import controladores.UsuarioJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_FamiliaProducto extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession sesion = pageContext.getSession();
        String area = sesion.getAttribute("Area").toString();
        FamiliaProductoJpaController jpa_familiaP = new FamiliaProductoJpaController();
        TipoMaquinaJpaController jpa_tipoM = new TipoMaquinaJpaController();
        List lst_familiasP = (List) pageContext.getRequest().getAttribute("consulta_familiaP");
        String filtro = (String) pageContext.getRequest().getAttribute("filtro");
        int filtroND = Integer.parseInt(pageContext.getRequest().getAttribute("nuevos_defectos").toString());
        int id_familiaP = Integer.parseInt(pageContext.getRequest().getAttribute("id_familiaP").toString());
        List lst_familiaP = null;
        List lst_tipoM = jpa_tipoM.ConsultaTipoMaquinas();
        try {
            //<editor-fold defaultstate="collapsed" desc="modulo familia producto">
            out.print("<div id='sidebar'>");
            if (area.equals("GESTION DE CALIDAD") || area.equals("SISTEMAS")) {
                if (id_familiaP == 0) {
                    out.print("<h3>Nueva Familia de producto</h3>");
                    out.print("<form action='Familia_producto?opc=2'  method='post' onsubmit='registroF();' name='formF'>");
                    out.print("<b>Nombre</b><br />");
                    out.print("<input type='text' name='txt_nombre' id ='nombre-id' placeholder='Nombre' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('nombre-id');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script><br />");
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
                } else {
                    lst_familiaP = jpa_familiaP.ConsultaFamiliaProductoId(id_familiaP);
                    Object[] obj_familiaP = (Object[]) lst_familiaP.get(0);
                    out.print("<h3>Modificar Familia de producto</h3>");
                    out.print("<form action='Familia_producto?opc=3'  method='post' onsubmit='registroF();' name='formF'>");
                    out.print("<input type='hidden' name='idF' value='" + id_familiaP + "'>");
                    out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                    out.print("<b>Nombre</b><br />");
                    out.print("<input type='text' name='txt_nombreM' value='" + obj_familiaP[1] + "' id ='nombre-id' placeholder='Nombre' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('nombre-id');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script>");
                    out.print("<b>Tipo</b><br />");
                    out.print("<select name='lst_tipoM' id='select-id'>");
                    for (int i = 0; i < lst_tipoM.size(); i++) {
                        Object[] obj_tipoM = (Object[]) lst_tipoM.get(i);
                        if (Integer.parseInt(obj_tipoM[0].toString()) == (Integer) obj_familiaP[3]) {
                            out.print("<option value='" + obj_tipoM[0] + "' style='display:none;' selected>" + obj_tipoM[1] + "</option>");
                        }
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
                }
            } else {
                out.print("<h3>Nueva maquina</h3>");
                out.print("<div align='center'><img src='Interfaz/Contenido/Iconos/Alert.png' alt='Logo' width='55' height='55.5' /><br /><b>No se permite <br />el registro</b></div>");
            }
            out.print("<div class='cleaner'></div></div>");
            out.print("<div id='content'>");
            out.print("<div style='float:right;'>");
            out.print("<form method='post' action='Familia_producto?opc=1&idF=" + 0 + "'>");
            out.print("<input name='txt_bus' type='text' class='input_field' placeholder='Buscar'><br/>");
            out.print("</form>");
            out.print("</div>");
            out.print("<div style='float:right;'>");
            out.print("<a href='Familia_producto?opc=1&idF=" + 0 + "&txt_bus=&filtroND=1'><img src='Interfaz/Contenido/Iconos/Search.png' width='21' height='21' title='filtro' style='margin-right: 10px;'></a>");
            out.print("</div>");
            if (!filtro.equals("")) {
                out.print("<a href='Familia_producto?opc=1&idF=" + 0 + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a>");
            }
            if (filtroND == 1) {
                out.print("<div class='overlay' tabindex='-1' id='bloqn' style='opacity: 1.06; display: block;'>");
                out.print("<fieldset class='resalta' id='NuevaNovedad' style='visibility: visible;width:100px;margin-left: -150px;margin-top: -90px;'>");
                out.print("<div style='float:right;'><a href='Familia_producto?opc=1&idF=" + 0 + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
                out.print("<h3>Filtro Nuevos defectos</h3>");
                out.print("<form action='Consulta_catologo?opc=3&txt_bus=' method='post' name='formFil' target='_blank'>");
                out.print("<b>Fecha Inicio:</b><br />");
                out.print("<input type='text' name='txt_fechaID' id=\"datepicker\" value=''  placeholder='Fecha inicio' style='margin-bottom: 0px;'>");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('datepicker');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script><br /><br/>");
                out.print("<b>Fecha Fin:</b><br />");
                out.print("<input type='text' name='txt_fechaFD' id=\"datepicker2\" value=''  placeholder='Fecha fin' style='margin-bottom: 0px;'>");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('datepicker2');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script><br/><br/>");
                out.print("<input type='submit' id='btsubmit' value='Consultar'>");
                out.print("</form>");
                out.print("</fieldset>");
                out.print("</div>");
            }
            out.print("<h3>Familias de productos</h3>");
            if (!lst_familiasP.isEmpty()) {
                out.print("<div id='NavPosicion'></div>");
                out.print("<table class='table' id='resultados' style='width:100%;'>");
                out.print("<tr>");
                out.print("<th>Nombre</th>");
                out.print("<th>Defectos</th>");
                out.print("<th>Tipo</th>");
                out.print("<th>Modificar</th>");
                out.print("<th>Estado</th>");
                out.print("</tr>");
                for (int i = 0; i < lst_familiasP.size(); i++) {
                    Object[] obj_familiaP = (Object[]) lst_familiasP.get(i);
                    out.print("<tr>");
                    if (area.equals("GESTION DE CALIDAD") || area.equals("SISTEMAS")) {
                        if ((Integer) obj_familiaP[2] == 0) {
                            out.print("<td align='center'><b style='color:red;'>" + obj_familiaP[1] + "</b></td>");
                            out.print("<td align='center'><b style='color:red;'>" + obj_familiaP[4] + "</b></td>");
                            out.print("<td align='center' style='width:10%;'><a href='Defecto?opc=1&idD=" + 0 + "&idF=" + obj_familiaP[0] + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Document.png' alt='Logo' width='22' height='22.5' /></a></td>");
                            out.print("<td align='center' style='width:10%;'><a href='Familia_producto?opc=1&idF=" + obj_familiaP[0] + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' alt='Logo' width='22' height='22.5' /></a></td>");
                            out.print("<td align='center' style='width:10%;'><a href='Familia_producto?opc=4&idF=" + obj_familiaP[0] + "&txt_bus=" + filtro + "&est=" + 1 + "'><img src='Interfaz/Contenido/Iconos/Delete.png' alt='Logo' width='22' height='22.5' /></a></td>");
                        } else {
                            out.print("<td align='center'>" + obj_familiaP[1] + "</td>");
                            out.print("<td align='center'>" + obj_familiaP[4] + "</td>");
                            out.print("<td align='center' style='width:10%;'><a href='Defecto?opc=1&idD=" + 0 + "&idF=" + obj_familiaP[0] + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Document.png' alt='Logo' width='22' height='22.5' /></a></td>");
                            out.print("<td align='center' style='width:10%;'><a href='Familia_producto?opc=1&idF=" + obj_familiaP[0] + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' alt='Logo' width='22' height='22.5' /></a></td>");
                            out.print("<td align='center' style='width:10%;'><a href='Familia_producto?opc=4&idF=" + obj_familiaP[0] + "&txt_bus=" + filtro + "&est=" + 0 + "'><img src='Interfaz/Contenido/Iconos/Check.png' alt='Logo' width='22' height='22.5' /></a></td>");
                        }
                    } else {
                        out.print("<td align='center'>" + obj_familiaP[1] + "</td>");
                        out.print("<td align='center'>" + obj_familiaP[4] + "</td>");
                        out.print("<td align='center' style='width:10%;'><a href='Defecto?opc=1&idD=" + 0 + "&idF=" + obj_familiaP[0] + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Document.png' alt='Logo' width='22' height='22.5' /></a></td>");
                        out.print("<td align='center' style='width:10%;'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' alt='Logo' width='22' height='22.5' title='Se necesitan permisos' /></a></td>");
                        out.print("<td align='center' style='width:10%;'><a href='#'><img src='Interfaz/Contenido/Iconos/Warning.png' alt='Logo' width='22' height='22.5' title='Se necesitan permisos' /></a></td>");
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
            //</editor-fold>
        } catch (IOException ex) {
            Logger.getLogger(Tag_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
