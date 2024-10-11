package Tags;

import Entidad.Rol;
import controladores.BitacoraJpaController;
import controladores.MaquinaJpaController;
import controladores.NovedadPersonalJpaController;
import controladores.ProgramacionJpaController;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_programacion extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession sesion = pageContext.getSession();
        String area = sesion.getAttribute("Area").toString();
        String id_rol = sesion.getAttribute("id_rol").toString();
        String nombre_Usuario = sesion.getAttribute("Nombre").toString();
        MaquinaJpaController jpa_maquina = new MaquinaJpaController();
        ProgramacionJpaController jpa_programacion = new ProgramacionJpaController();
        NovedadPersonalJpaController jpa_novedadP = new NovedadPersonalJpaController();
        BitacoraJpaController jpa_bitacora = new BitacoraJpaController();
        List lst_bitacora = null;
        List lst_nov = null;
        List lst_btc = null;
        int turno = Integer.parseInt(pageContext.getRequest().getAttribute("turno").toString());;
        int formulario = Integer.parseInt(pageContext.getRequest().getAttribute("formulario").toString());;
//        int id_programacion = Integer.parseInt(pageContext.getRequest().getAttribute("id_programacion").toString());;
        int id_bitacora = Integer.parseInt(pageContext.getRequest().getAttribute("id_bitacora").toString());;
        int id_tipoM = Integer.parseInt(pageContext.getRequest().getAttribute("id_tipoMaq").toString());;
        int id_novedadP = Integer.parseInt(pageContext.getRequest().getAttribute("id_novedadP").toString());;
        List lst_maquina = jpa_maquina.ConsultaMaquinasIdTipo(id_tipoM);
        List lst_programacion = jpa_programacion.ConsultaProgramacion(id_tipoM);
        List lst_bitacoras = jpa_bitacora.ConsultaBitacorasIdTipo(id_tipoM);
        int year = 0;
        LocalDateTime DateTime = LocalDateTime.now();
        int anioActual = DateTime.getYear();
        try {
            year = Integer.parseInt(pageContext.getRequest().getAttribute("anio").toString());
        } catch (Exception e) {
            year = anioActual;
        }
        List lst_novedadP = jpa_novedadP.ConsultaNovedades(id_tipoM, year);
        try {
            out.print("<div id='content_sin'>");
            //<editor-fold defaultstate="collapsed" desc="script programacion">
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
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="script novedad">
            out.print("<script language='Javascript'>"
                    + "function mostrarNP() {"
                    + "var panel ;var pagina =''; panel = document.getElementById('NuevaNovedad');"
                    + "var bloq = document.getElementById('bloqn');"
                    + "if(panel.style.visibility == 'hidden') {"
                    + "panel.style.visibility = 'visible';"
                    + "bloq.style.display = 'block';"
                    + "}else {"
                    + "panel.style.visibility = 'hidden';"
                    + "bloq.style.display = 'none';"
                    + "}}</script>");
//</editor-fold>
            if (formulario == 1) {
                //<editor-fold defaultstate="collapsed" desc="formulario novedad">
                //<editor-fold defaultstate="collapsed" desc="Registro novedad">
                if (id_novedadP == 0) {
                    out.print("<div class='overlay' tabindex='-1' id='bloqn' style='opacity: 1.06; display: block;'>");
                    out.print("<fieldset class='resalta' id='NuevaNovedad' style='visibility: visible;'>");
                    out.print("<div style='float:right;'><a href='Programacion?opc=1&idP=" + 0 + "&idTM=" + id_tipoM + "&idNP=0'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
                    out.print("<h3>Nueva Novedad de personal</h3>");
                    out.print("<form action='Programacion?opc=4' onsubmit='registroNP();' method='post' id='formNP'>");
                    out.print("<input type='hidden' name='idTM' value='" + id_tipoM + "'>");
                    out.print("<table class='table' style='width:100%'>");
                    out.print("<tr>");
                    out.print("<td>");
                    out.print("<b>Fecha:</b><br />");
//                out.print("<input type='text' name='txt_fecha' id=\"datepicker\" value='" + obj_prog[1] + "'  placeholder='fecha'>");
                    out.print("<input type='text' name='txt_fechaNP' id=\"datepicker\" value='' autocomplete='off'  placeholder='fecha' style='margin-bottom: 0px;'>");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('datepicker');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td valign='top'>");
//                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + obj_prog[2].toString().replace("<div>", "<div contenteditable='true'>") + "");
                    out.print("<textarea id='editor' name='txt_descripcionNP' style='width: 500px; height: 400' placeholder='descripcion'>");
                    out.print("<b>Turno 1</b>");
                    out.print("<div contenteditable='true'>");
                    out.print("<p>*</p>");
                    out.print("</div>");
                    out.print("<b>Turno 2</b>");
                    out.print("<div contenteditable='true'>");
                    out.print("<p>*</p>");
                    out.print("</div>");
                    out.print("<b>Turno 3</b>");
                    out.print("<div contenteditable='true'>");
                    out.print("<p>*</p>");
                    out.print("</div>");
                    out.print("</textarea>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("</table>");
                    out.print("<input type='submit' id='btsubmit' value='Guardar'>");
                    out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "        </div>");
                    out.print("</form>");
                    out.print("</fieldset>");
                    out.print("</div>");
                    //</editor-fold>
                } else {
                    //<editor-fold defaultstate="collapsed" desc="Modificar novedad">
                    lst_nov = jpa_novedadP.ConsultaNovedadId(id_novedadP);
                    Object[] obj_nov = (Object[]) lst_nov.get(0);
                    out.print("<div class='overlay' tabindex='-1' id='bloqn' style='opacity: 1.06; display: block;'>");
                    out.print("<fieldset class='resalta' id='NuevaNovedad' style='visibility: visible;'>");
                    out.print("<div style='float:right;'><a href='Programacion?opc=1&idP=" + 0 + "&idTM=" + id_tipoM + "&idNP=0'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
                    out.print("<h3>Modificar Novedad de personal</h3>");
                    out.print("<form action='Programacion?opc=5' onsubmit='registroNP();' method='post' id='formNP'>");
                    out.print("<input type='hidden' name='idTM' value='" + id_tipoM + "'>");
                    out.print("<input type='hidden' name='idNP' value='" + obj_nov[0] + "'>");
                    out.print("<table class='table' style='width:100%'>");
                    out.print("<tr>");
                    out.print("<td>");
                    out.print("<b>Fecha:</b><br />");
                    out.print("<input type='text' name='txt_fechaNP' id=\"datepicker\" value='" + obj_nov[1] + "'  placeholder='fecha' style='margin-bottom: 0px;'>");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('datepicker');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td valign='top'>");
                    out.print("<textarea id='editor' name='txt_descripcionNP' style='width: 500px; height: 400' placeholder='descripcion'><div contenteditable='true'><p>" + obj_nov[2] + "</p></div>");
                    out.print("</textarea>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("</table>");
                    out.print("<input type='submit' id='btsubmit' value='Guardar'>");
                    out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "        </div>");
                    out.print("</form>");
                    out.print("</fieldset>");
                    out.print("</div>");
                    //</editor-fold>
                }
//</editor-fold>
            } else if (formulario == 2) {
                //<editor-fold defaultstate="collapsed" desc="formulario programacion comentariado">
//                if (id_programacion == 0) {
////                Object[] obj_prog = (Object[]) lst_programacion.get(0);
//                    out.print("<div class='overlay' tabindex='-1' id='bloq' style='opacity: 1.06; display: block;'>");
//                    out.print("<fieldset class='resalta' id='NuevaProgramacion' style='visibility: visible;'>");
//                    out.print("<div style='float:right;'><a href='Programacion?opc=1&idP=" + 0 + "&idTM=" + id_tipoM + "&idNP=0'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
//                    out.print("<h3>Nueva programacion</h3>");
//                    out.print("<form action='Programacion?opc=2' onsubmit='registroP();' method='post' id='formP'>");
//                    out.print("<input type='hidden' name='idTM' value='" + id_tipoM + "'>");
//                    out.print("<table class='table' style='width:100%'>");
//                    out.print("<tr>");
//                    out.print("<td>");
//                    out.print("<b>Fecha:</b><br />");
////                out.print("<input type='text' name='txt_fecha' id=\"datepicker\" value='" + obj_prog[1] + "'  placeholder='fecha'>");
//                    out.print("<input type='text' name='txt_fecha' id=\"datepicker\" value=''  placeholder='fecha' style='margin-bottom: 0px;'>");
//                    out.print("<script type='text/javascript'>");
//                    out.print("var validation = new LiveValidation('datepicker');");
//                    out.print("validation.add( Validate.Presence );");
//                    out.print("</script>");
//                    out.print("</td>");
//                    out.print("<td>");
//                    out.print("<b>Turno:</b><br/>");
//                    out.print("<select name='slt_turno' id='turno-id'>");
//                    out.print("<option value='' style='display:none;'>SELECCIONE TURNO</option>");
//                    out.print("<option value='1'>Turno 1</option>");
//                    out.print("<option value='2'>Turno 2</option>");
//                    out.print("<option value='3'>Turno 3</option>");
//                    out.print("<option value='1/12'>Turno 1 12hr</option>");
//                    out.print("<option value='2/12'>Turno 2 12hr</option>");
//                    out.print("</select>");
//                    out.print("<script type='text/javascript'>");
//                    out.print("var validation = new LiveValidation('turno-id');");
//                    out.print("validation.add( Validate.Presence );");
//                    out.print("</script>");
//                    out.print("</td>");
//                    out.print("</tr>");
//                    out.print("<tr>");
//                    out.print("<td colspan='2' valign='top'>");
////                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + obj_prog[2].toString().replace("<div>", "<div contenteditable='true'>") + "");
//                    out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>");
//                    for (int i = 0; i < lst_maquina.size(); i++) {
//                        Object[] obj_maquina = (Object[]) lst_maquina.get(i);
//                        out.print("<b>" + obj_maquina[1] + "</b>");
//                        out.print("<div contenteditable='true'>");
//                        out.print("<p>*</p>");
//                        out.print("</div>");
//                    }
//                    out.print("</textarea>");
//                    out.print("</td>");
//                    out.print("</tr>");
//                    out.print("</table>");
//                    out.print("<input type='submit' id='btsubmit' value='Guardar'>");
//                    out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
//                            + "          <div></div>\n"
//                            + "          <div></div>\n"
//                            + "          <div></div>\n"
//                            + "        </div>");
//                    out.print("</form>");
//                    out.print("</fieldset>");
//                    out.print("</div>");
//                } else {
//                    lst_prog = jpa_programacion.ConsultaProgramacionId(id_programacion);
//                    Object[] obj_prog = (Object[]) lst_prog.get(0);
//                    out.print("<div class='overlay' tabindex='-1' id='bloq' style='opacity: 1.06; display: block;'>");
//                    out.print("<fieldset class='resalta' id='ModificarProgramacion' style='visibility: visible;'>");
//                    out.print("<div style='float:right;'><a href='Programacion?opc=1&idP=" + 0 + "&idTM=" + id_tipoM + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
//                    out.print("<h3>Modificar programacion</h3>");
//                    out.print("<form action='Programacion?opc=3' onsubmit='registroP();' method='post' id='formP'>");
//                    out.print("<input type='hidden' name='idP' value='" + id_programacion + "'>");
//                    out.print("<input type='hidden' name='idTM' value='" + id_tipoM + "'>");
//                    out.print("<table class='table' style='width:100%'>");
//                    out.print("<tr>");
//                    out.print("<td>");
//                    out.print("<b>Fecha:</b><br />");
//                    out.print("<input type='text' name='txt_fechaM' value=" + obj_prog[1] + " id=\"datepicker\" placeholder='fecha' style='margin-bottom: 0px;'>");
//                    out.print("<script type='text/javascript'>");
//                    out.print("var validation = new LiveValidation('datepicker');");
//                    out.print("validation.add( Validate.Presence );");
//                    out.print("</script>");
//                    out.print("</td>");
//                    out.print("<td>");
//                    out.print("<b>Turno:</b><br/>");
//                    out.print("<select name='slt_turnoM' id='turno-id'>");
//                    out.print("<option value='" + obj_prog[6] + "' style='display:none;'>TURNO " + obj_prog[6] + "</option>");
//                    out.print("<option value='1'>Turno 1</option>");
//                    out.print("<option value='2'>Turno 2</option>");
//                    out.print("<option value='3'>Turno 3</option>");
//                    out.print("<option value='1/12'>Turno 1 12hr</option>");
//                    out.print("<option value='2/12'>Turno 2 12hr</option>");
//                    out.print("</select>");
//                    out.print("<script type='text/javascript'>");
//                    out.print("var validation = new LiveValidation('turno-id');");
//                    out.print("validation.add( Validate.Presence );");
//                    out.print("</script>");
//                    out.print("</td>");
//                    out.print("</tr>");
//                    out.print("<tr>");
//                    out.print("<td colspan='2' valign='top'>");
//                    out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + obj_prog[2].toString().replace("<div>", "<div contenteditable='true'>") + "");
//                    out.print("</textarea>");
//                    out.print("</td>");
//                    out.print("</tr>");
//                    out.print("</table>");
//                    out.print("<input type='submit' id='btsubmit' value='Guardar'>");
//                    out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
//                            + "          <div></div>\n"
//                            + "          <div></div>\n"
//                            + "          <div></div>\n"
//                            + "        </div>");
//                    out.print("</form>");
//                    out.print("</fieldset>");
//                    out.print("</div>");
//                }
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="registro formulario programacion">
                if (id_bitacora != 0) {
                    lst_bitacora = jpa_bitacora.ConsultaBitacoraId(id_bitacora);
                    Object[] obj_bitacora = (Object[]) lst_bitacora.get(0);
                    out.print("<div class='overlay' tabindex='-1' id='bloq' style='opacity: 1.06; display: block;'>");
                    out.print("<fieldset class='resalta' id='NuevaProgramacion' style='visibility: visible;'>");
                    out.print("<div style='float:right;'><a href='Programacion?opc=1&idP=" + 0 + "&idTM=" + id_tipoM + "&idNP=0'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
                    out.print("<h3>Nueva Bitacora</h3>");
                    out.print("<form action='Programacion?opc=2' onsubmit='registroP();' method='post' id='formP'>");
                    out.print("<input type='hidden' name='UsuR' value='" + ((turno == 1) ? ((obj_bitacora[4] != null) ? ((!obj_bitacora[4].toString().contains("[" + nombre_Usuario + "]")) ? obj_bitacora[4] + "[" + nombre_Usuario + "]" : obj_bitacora[4]) : "[" + nombre_Usuario + "]") : ((turno == 2) ? ((obj_bitacora[7] != null) ? ((!obj_bitacora[7].toString().contains("[" + nombre_Usuario + "]")) ? obj_bitacora[7] + "[" + nombre_Usuario + "]" : obj_bitacora[7]) : "[" + nombre_Usuario + "]") : ((obj_bitacora[10] != null) ? ((!obj_bitacora[10].toString().contains("[" + nombre_Usuario + "]")) ? obj_bitacora[10] + "[" + nombre_Usuario + "]" : obj_bitacora[10]) : "[" + nombre_Usuario + "]"))) + "'>");
                    out.print("<input type='hidden' name='idBt' value='" + id_bitacora + "'>");
                    out.print("<input type='hidden' name='idTM' value='" + id_tipoM + "'>");
                    out.print("<input type='hidden' name='turno' value='" + turno + "'>");
                    out.print("<table class='table' style='width:100%'>");
                    out.print("<tr>");
                    out.print("<td colspan='2' valign='top'>");
//                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + obj_prog[2].toString().replace("<div>", "<div contenteditable='true'>") + "");
                    out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>");
                    if (turno == 1) {
                        if (obj_bitacora[3] == null) {
                            out.print("<div contenteditable='true'><p>*</p></div>");
                        } else if (obj_bitacora[3].toString().contains("<div>")) {
                            out.print("" + ((obj_bitacora[3] != null) ? obj_bitacora[3].toString().replace("<div", "<div contenteditable='true'") : "<div contenteditable='true'><p>*</p></div>") + "");
                        } else {
                            out.print("<div contenteditable='true'>" + obj_bitacora[3] + "</div>");
                        }
                    } else if (turno == 2) {
                        if (obj_bitacora[6] == null) {
                            out.print("<div contenteditable='true'><p>*</p></div>");
                        } else if (obj_bitacora[6].toString().contains("<div>")) {
                            out.print("" + ((obj_bitacora[6] != null) ? obj_bitacora[6].toString().replace("<div", "<div contenteditable='true'") : "<div contenteditable='true'><p>*</p></div>") + "");
                        } else {
                            out.print("<div contenteditable='true'>" + obj_bitacora[6] + "</div>");
                        }
                    } else if (turno == 3) {
                        if (obj_bitacora[9] == null) {
                            out.print("<div contenteditable='true'><p>*</p></div>");
                        } else if (obj_bitacora[9].toString().contains("<div>")) {
                            out.print("" + ((obj_bitacora[9] != null) ? obj_bitacora[9].toString().replace("<div", "<div contenteditable='true'") : "<div contenteditable='true'><p>*</p></div>") + "");
                        } else {
                            out.print("<div contenteditable='true'>" + obj_bitacora[9] + "</div>");
                        }
                    } else if (obj_bitacora[12].toString().contains("<div>")) {
                        out.print("" + ((obj_bitacora[12] != null) ? obj_bitacora[12].toString().replace("<div", "<div contenteditable='true'") : "<div contenteditable='true'><p>*</p></div>") + "");
                    } else {
                        out.print("<div contenteditable='true'>" + obj_bitacora[12] + "</div>");
                    }
//                    out.print("" + ((turno == 1) ? ((obj_bitacora[3] != null) ? obj_bitacora[3].toString().replace("<div", "<div contenteditable='true'") : "<div contenteditable='true'><p>*</p></div>")
//                            : ((turno == 2) ? ((obj_bitacora[6] != null) ? obj_bitacora[6].toString().replace("<div", "<div contenteditable='true'") : "<div contenteditable='true'><p>*</p></div>")
//                                    : ((turno == 3) ? ((obj_bitacora[9] != null) ? obj_bitacora[9].toString().replace("<div", "<div contenteditable='true'")
//                                                    : "<div contenteditable='true'><p>*</p></div>") : ((obj_bitacora[12] != null) ? ((!obj_bitacora[12].toString().equals("")) ? obj_bitacora[12].toString().replace("<div", "<div contenteditable='true'")
//                                                    : "<div contenteditable='true'><p>*</p></div>") : "<div contenteditable='true'><p>*</p></div>")))) + "");
                    out.print("</textarea>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("</table>");
                    out.print("<input type='submit' id='btsubmit' value='Guardar'>");
                    out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "        </div>");
                    out.print("</form>");
                    out.print("</fieldset>");
                    out.print("</div>");
                }
//</editor-fold>
            }
            out.print("<div style='float:right;'>");
            out.print("<h3>");
            if (id_tipoM == 3) {
                out.print("<b>Inyeccion</b>");
            } else if (id_tipoM == 2) {
                out.print("<b>Extrusion PVC</b>");
            } else if (id_tipoM == 9) {
                out.print("<b>Extrusion PP</b>");
            } else if (id_tipoM == 10) {
                out.print("<b>Peletizado</b>");
            }
            out.print("</h3>");
            out.print("</div>");
            if (area.equals("SISTEMAS") || area.equals("PRODUCCION INSUMOS")) {
                out.print("<a href='Programacion?opc=1&idP=" + 0 + "&idTM=" + id_tipoM + "&idNP=0&form=1'><img src='Interfaz/Contenido/Iconos/Person.png' width='24' height='21' title='Nueva novedad de personal'></a><br /><br />");
            }
            out.print("<div style='width: 100%;text-align: right;'>");
            out.print("<form action='Programacion?opc=1&idP=" + 0 + "&idTM=" + id_tipoM + "&idNP=0' method='post' id='form_anio'>");
            out.print("<select class='select-form' onchange='ejectForm()' placeholder='filtro por anio' style='width: 60px;text-align: center;' name='anio'>");
            out.print("<option>" + year + "</option>");
            lst_nov = jpa_novedadP.ConsultarAniosTotales();
            for (int i = 0; i < lst_nov.size(); i++) {
                Object[] obj_anio = (Object[]) lst_nov.get(i);
                if (year == Integer.parseInt(obj_anio[1].toString())) {
                    out.print("<option style='display: none;'>-</option>");
                } else {
                    out.print("<option>" + obj_anio[1] + "</option>");
                }
            }
            out.print("</select>");
            out.print("</form>");
            out.print("</div>");

            out.print("<div id='tab-container'>");
            if (id_tipoM != 3) {
                out.print("<div class='tab-content'>");
                //<editor-fold defaultstate="collapsed" desc="consulta programacion">
                out.print("<h1 class='tab' title='Bitacoras'>Bitacoras</h1>");
                if (lst_bitacoras != null) {
                    for (int i = 0; i < lst_bitacoras.size(); i++) {
                        Object[] obj_bitacora = (Object[]) lst_bitacoras.get(i);
                        out.print("<div id='NavPosicion'></div>");
                        out.print("<table id='resultados' style='width:100%;'>");
                        out.print("<tr>");
                        out.print("<td></td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print("<td>");
                        out.print("<button class='accordion'><div style='float:right;'><b style='color:black;'>" + obj_bitacora[1] + "</b></div></button>");
                        out.print("<div class='panel' style='overflow:scroll;'>");
                        out.print("<table class='table' style='width:100%;'>");
                        out.print("<tr>");
                        out.print("<td colspan='4' style='background-color:#979595;' align='center'><b style='color:white;'>COPIA NO CONTROLADA</b></td>");
                        out.print("</tr>");
                        out.print("<tr>");
                        out.print("<td align='center' colspan='2' style='width:30%'><b><img src='Interfaz/Contenido/images/Logo.png' style='width:60%;'></b></td>");
                        out.print("<td align='center'><b class='negro'>Registro de actividades</b><hr/><b>Fecha: </b><b class='negro'>" + obj_bitacora[1] + "</b></td>");
                        if (id_tipoM == 3) {
                            out.print("<td align='center'><b>Codigo: </b><b class='negro'>R-PI-019</b><hr /><b>Version: </b><b class='negro'>3</b></td>");
                        } else if (id_tipoM == 2) {
                            out.print("<td align='center'><b>Codigo: </b><b class='negro'>R-PI-017</b><hr /><b>Version: </b><b class='negro'>3</b></td>");
                        } else if (id_tipoM == 9) {
                            out.print("<td align='center'><b>Codigo: </b><b class='negro'>R-PI-017</b><hr /><b>Version: </b><b class='negro'>3</b></td>");
                        } else if (id_tipoM == 10) {
                            out.print("<td align='center'><b>Codigo: </b><b class='negro'>R-PI-010</b><hr /><b>Version: </b><b class='negro'>3</b></td>");
                        }
                        out.print("</tr>");
                        lst_btc = jpa_bitacora.ConsultaBitacorasMaquinas(id_tipoM, obj_bitacora[1].toString());
                        for (int j = 0; j < lst_btc.size(); j++) {
                            Object[] obj_btc = (Object[]) lst_btc.get(j);
                            out.print("<tr>");
                            out.print("<td rowspan='3' align='center'>" + obj_btc[13] + "</td>");
                            if (area.equals("SISTEMAS") || area.equals("PRODUCCION INSUMOS")) {
                                if ((Integer) obj_btc[5] == 1) {
                                    out.print("<td align='center'><b class='negro'>Turno 1</b><div style='float:right'><a href='Programacion?opc=1&idTM=" + id_tipoM + "&idNP=0&form=2&idB=" + obj_btc[0] + "&turno=1'><img src='Interfaz/Contenido/Iconos/Edit.png' width='18' height='18'></a></div></td>");
                                } else {
                                    out.print("<td align='center'><b class='negro'>Turno 1</b></td>");
                                }
                            } else {
                                out.print("<td align='center'><b class='negro'>Turno 1</b></td>");
                            }
                            out.print("<td colspan='2'><b class='negro'>Responsables: </b>" + ((obj_btc[4] != null) ? obj_btc[4].toString().replace("][", ",").replace("[", "").replace("]", "") : "") + "<hr/>" + ((obj_btc[3] != null) ? obj_btc[3].toString() : "") + "</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            if (area.equals("SISTEMAS") || area.equals("PRODUCCION INSUMOS")) {
                                if ((Integer) obj_btc[8] == 1) {
                                    out.print("<td align='center'><b class='negro'>Turno 2</b><div style='float:right'><a href='Programacion?opc=1&idTM=" + id_tipoM + "&idNP=0&form=2&idB=" + obj_btc[0] + "&turno=2'><img src='Interfaz/Contenido/Iconos/Edit.png' width='18' height='18'></a></div></td>");
                                } else {
                                    out.print("<td align='center'><b class='negro'>Turno 2</b></td>");
                                }
                            } else {
                                out.print("<td align='center'><b class='negro'>Turno 2</b></td>");
                            }
                            out.print("<td colspan='2'><b class='negro'>Responsables: </b>" + ((obj_btc[7] != null) ? obj_btc[7].toString().replace("][", ",").replace("[", "").replace("]", "") : "") + "<hr/>" + ((obj_btc[6] != null) ? obj_btc[6].toString() : "") + "</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            if (area.equals("SISTEMAS") || area.equals("PRODUCCION INSUMOS")) {
                                if ((Integer) obj_btc[11] == 1) {
                                    out.print("<td align='center'><b class='negro'>Turno 3</b><div style='float:right'><a href='Programacion?opc=1&idTM=" + id_tipoM + "&idNP=0&form=2&idB=" + obj_btc[0] + "&turno=3'><img src='Interfaz/Contenido/Iconos/Edit.png' width='18' height='18'></a></div></td>");
                                } else {
                                    out.print("<td align='center'><b class='negro'>Turno 3</b></td>");
                                }
                            } else {
                                out.print("<td align='center'><b class='negro'>Turno 3</b></td>");
                            }
                            out.print("<td colspan='2'><b class='negro'>Responsables: </b>" + ((obj_btc[10] != null) ? obj_btc[10].toString().replace("][", ",").replace("[", "").replace("]", "") : "") + "<hr/>" + ((obj_btc[9] != null) ? obj_btc[9].toString() : "") + "</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            if (area.equals("SISTEMAS") || area.equals("PRODUCCION INSUMOS")) {
                                if (Integer.parseInt(id_rol) == 1 || Integer.parseInt(id_rol) == 3) {
                                    out.print("<td colspan='4' valign='top'><div style='float:right'><a href='Programacion?opc=1&idTM=" + id_tipoM + "&idNP=0&form=2&idB=" + obj_btc[0] + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='18' height='18'></a></div><b class='negro'>Programacion: </b>" + ((obj_btc[12] != null) ? obj_btc[12].toString() : "") + "</td>");
                                } else {
                                    out.print("<td colspan='4' valign='top'><b class='negro'>Programacion: </b>" + ((obj_btc[12] != null) ? obj_btc[12].toString() : "") + "</td>");
                                }
                            } else {
                                out.print("<td colspan='4' valign='top'><b class='negro'>Programacion: </b>" + ((obj_btc[12] != null) ? obj_btc[12].toString() : "") + "</td>");
                            }
                            out.print("</tr>");
                        }
                        out.print("</table>");
                        out.print("</div>");
                        out.print("</td>");
                        out.print("</tr>");
                        out.print("</table>");
                        out.print("<script type='text/javascript'>");
                        out.print("var pager = new Pager('resultados',10);");
                        out.print("pager.init();");
                        out.print("pager.showPageNav('pager','NavPosicion');");
                        out.print("pager.showPage(1);");
                        out.print("</script>");
                    }
                } else {
                    out.print("<h3><b style='color:#292929'>No se han encontrado resultados</b></h3>");
                }
//</editor-fold>
                out.print("</div>");
            }

            out.print("<div class='tab-content'>");
            //<editor-fold defaultstate="collapsed" desc="consulta novedades">
            out.print("<h1 class='tab' title='Novedades'>Novedad Personal</h1>");
            if (lst_novedadP != null) {
                for (int i = 0; i < lst_novedadP.size(); i++) {
                    Object[] obj_novedadP = (Object[]) lst_novedadP.get(i);
                    out.print("<div id='NavPosicionN'></div>");
                    out.print("<table class='table' id='resultadosN' style='width:100%;'>");
                    out.print("<tr>");
                    out.print("<td></td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td>");
                    if (area.equals("SISTEMAS") || area.equals("PRODUCCION INSUMOS")) {
                        if (Integer.parseInt(obj_novedadP[4].toString()) == 1) {
//                            out.print("<button class='accordion'><div style='float:left;'><b>Responsable: </b><b class='negro'>" + obj_novedadP[5] + "</b></div><div style='float:right;'><a href='Programacion?opc=1&idP=0&idTM=" + id_tipoM + "&idNP=" + obj_novedadP[0] + "&form=1'><img src='Interfaz/Contenido/Iconos/Edit.png' width='18' height='18'></a></div></button>");
                            out.print("<button class='accordion'><div style='float:left;'><b style='color:black;'>" + obj_novedadP[1] + "</b></div><div style='float:right;'><a href='Programacion?opc=1&idP=0&idTM=" + id_tipoM + "&idNP=" + obj_novedadP[0] + "&form=1'><img src='Interfaz/Contenido/Iconos/Edit.png' width='18' height='18'></a></div></button>");
                        } else {
//                            out.print("<button class='accordion'><div style='float:left;'><b>Responsable: </b><b class='negro'>" + obj_novedadP[5] + "</b></div><div style='float:right;'><b style='color:black;'> " + obj_novedadP[1] + "</b></div></button>");
                            out.print("<button class='accordion'><div style='float:right;'><b style='color:black;'> " + obj_novedadP[1] + "</b></div></button>");
                        }
                    } else {
                        out.print("<button class='accordion'><div style='float:left;'><b style='color:black;'>" + obj_novedadP[1] + "</b></div></button>");
                    }
                    out.print("<div class='panel' style='overflow:scroll;'>");
                    out.print("<table class='table' style='width:100%;'>");
                    out.print("<tr>");
                    out.print("<td valing='top' colspan='3'>" + obj_novedadP[2] + "</td>");
                    out.print("</tr>");
                    out.print("</table>");
                    out.print("</div>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("</table>");
                    out.print("<script type='text/javascript'>");
                    out.print("var pagern = new Pager0('resultadosN',10);");
                    out.print("pagern.init();");
                    out.print("pagern.showPageNav('pagern','NavPosicionN');");
                    out.print("pagern.showPage(1);");
                    out.print("</script>");
                }
            } else {
                out.print("<h3><b style='color:#292929'>No se han encontrado resultados</b></h3");
            }
            out.print("</div>");
//</editor-fold>
            if (id_tipoM == 3) {
                out.print("<div class='tab-content'>");
                //<editor-fold defaultstate="collapsed" desc="historial inyeccion">
                out.print("<h1 class='tab' title='Historial'>Historial</h1>");
                if (lst_programacion != null) {
                    for (int i = 0; i < lst_programacion.size(); i++) {
                        Object[] obj_programacion = (Object[]) lst_programacion.get(i);
                        if (Integer.parseInt(obj_programacion[0].toString()) <= 240) {
                            out.print("<div id='NavPosicion'></div>");
                            out.print("<table class='table' id='resultados' style='width:100%;'>");
                            out.print("<tr>");
                            out.print("<td></td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<button class='accordion'><div style='float:left;'><b>Responsable: </b><b class='negro'>" + obj_programacion[3] + "</b><b style='color:black;'>  " + obj_programacion[1] + "</b></div><div style='float:right;'><b style='color:black;'>" + obj_programacion[1] + "</b></div></button>");
                            out.print("<div class='panel' style='overflow:scroll;'>");
                            out.print("<table class='table' style='width:100%;'>");
                            out.print("<tr>");
                            out.print("<td valing='top' colspan='3'>" + obj_programacion[2] + "</td>");
                            out.print("</tr>");
                            out.print("</table>");
                            out.print("</div>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("</table>");
                            out.print("<script type='text/javascript'>");
                            out.print("var pager = new Pager('resultados',10);");
                            out.print("pager.init();");
                            out.print("pager.showPageNav('pager','NavPosicion');");
                            out.print("pager.showPage(1);");
                            out.print("</script>");
                        }
                    }
                } else {
                    out.print("<h3><b style='color:#292929'>No se han encontrado resultados</b></h3");
                }
                out.print("</div>");
                //</editor-fold>
            }
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
