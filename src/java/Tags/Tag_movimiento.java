package Tags;

import controladores.HerramentalJpaController;
import controladores.MaquinaJpaController;
import controladores.MovimientoInyectoraJpaController;
import controladores.UsuarioJpaController;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_movimiento extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession sesion = pageContext.getSession();
        String area = sesion.getAttribute("Area").toString();
        int usuarioId = Integer.parseInt(sesion.getAttribute("id").toString());
        HerramentalJpaController jpa_herramental = new HerramentalJpaController();
        MaquinaJpaController jpa_maquina = new MaquinaJpaController();
        MovimientoInyectoraJpaController jpa_movimiento = new MovimientoInyectoraJpaController();
        UsuarioJpaController jpa_usuario = new UsuarioJpaController();
        List lst_herramental = jpa_herramental.ConsultaHerramentales();
        List lst_TipoM = jpa_movimiento.ConsultaMovimientos();
        List lst_usuarios = jpa_usuario.ConsultaCargos();
        int id_maquina = Integer.parseInt(pageContext.getRequest().getAttribute("id_maquina").toString());
        int id_movimiento = Integer.parseInt(pageContext.getRequest().getAttribute("id_movimiento").toString());
        int solucion = Integer.parseInt(pageContext.getRequest().getAttribute("solucion").toString());
        int consecutivo = Integer.parseInt(pageContext.getRequest().getAttribute("consecutivo").toString());
        String filtro = (String) pageContext.getRequest().getAttribute("filtro");
        String codigo = (String) pageContext.getRequest().getAttribute("codigo");
        String usa_firmas = "";
        List lst_maquina = jpa_maquina.ConsultaMaquinaId(id_maquina);
        Object[] obj_maquina = (Object[]) lst_maquina.get(0);
        int id_herramental = 0, documento = 0, codUsa = 0, tipoMV = 0;
        int id_pendiente = Integer.parseInt(pageContext.getRequest().getAttribute("id_pendiente").toString());
        List lst_movimientos = jpa_movimiento.ConsultaMovimientosIdMaquina(id_maquina);
        List lst_pendientes = jpa_maquina.ConsultaPendientesMaquina(id_maquina);
        try {
            documento = Integer.parseInt(pageContext.getRequest().getAttribute("txt_doc").toString());
            codUsa = Integer.parseInt(pageContext.getRequest().getAttribute("txt_cod").toString());
            tipoMV = Integer.parseInt(pageContext.getRequest().getAttribute("idTMV").toString());
            usa_firmas = pageContext.getRequest().getAttribute("txt_resp").toString();
        } catch (Exception e) {
            documento = 0;
            codUsa = 0;
            tipoMV = 0;
            usa_firmas = "";
        }
        try {
            out.print("<div id='content_sin'>");
            out.print("<a href='Maquina?opc=1&idM=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a>");
            out.print("<h3 style='float:right'><b>Maquina: </b><b style='color:#292929'>" + obj_maquina[1] + "</b></h3>");
            out.print("<div id='tab-container' style='margin-top: 10px;'>");
            //<editor-fold defaultstate="collapsed" desc="enviar email movimientos">
            out.print("<div style='display:none;position:absolute;float:right;width:300px;font-size:14px;right:140px;background-color:#fff;' id=\"toggleM\">");
            out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:290px;padding: 20px 0 20px 10px;'>");
            out.print("<h3>Seleccionar Cargos</h3>");
            out.print("<form action='Movimiento?opc=6' method='post' name='formMailM' onsubmit='MailM();'>");
            out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
            out.print("<input type='hidden' name='idMV' id='idMV' value=''>");
            out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
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
            out.print("<input type='submit' value='Enviar' onclick='CorreoM();' id='botonMa'/>");
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
                if (!area.equals("CONSULTA")) {
                    if (id_movimiento == 0) {
                        //<editor-fold defaultstate="collapsed" desc="registrar movimiento">
                        out.print("<div class='tab-content'>");
                        out.print("<h1 class='tab' title='Historial'>Historial</h1>");
                        if (lst_movimientos != null) {
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
                        out.print("$(\"#toggle2\").toggle(\"slide\");");
                        out.print("});");
                        out.print("</script>");
                        if (codigo != null) {
                            out.print("<div style='display:block;' id=\"toggle2\">");
                        } else {
                            out.print("<div style='display:none;' id=\"toggle2\">");
                        }
                        out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                        out.print("<h3> <div style='position: absolute;right: 5%;'><a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img id='Menu_registro' style='float:left;position: absolute;'  src='Interfaz/Contenido/Iconos/Delete.png' width='20px' height='20px' alt='edit' title='Buscar' /></a></div>Registrar Movimiento</h3>");
                        out.print("<b>Codigo:</b><br />");
                        out.print("<form action='Movimiento?opc=4&idMV=" + 0 + "' method='post' onsubmit='checkSubmit();' style='margin-bottom: 0px;'>");
                        if (codigo != null) {
                            out.print("<input type='text' name='txt_codigo' value='" + codigo + "' id='codigo-id' placeholder='Codigo producto'>");
                        } else {
                            out.print("<input type='text' name='txt_codigo' id='codigo-id' placeholder='Codigo producto'>");
                        }
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('codigo-id');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("validation.add( Validate.Length, { minimum: 4, maximum: 4} );");
                        out.print("</script><br />");
                        out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                        out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                        out.print("</form>");
                        if (pageContext.getRequest().getAttribute("productos") != null) {
                            List ltapdt = (List) pageContext.getRequest().getAttribute("productos");
                            out.print("<form action='Movimiento?opc=2' method='post' name='formM' id='formM' onsubmit='checkSubmit();registroM();'>");
                            out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                            out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                            out.print("<input type='hidden' name='tipoM' value='1'>");
                            out.print("<table  style='width:100%;border:none;font-size: 11px;'>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Producto:</b><br />");
                            out.print("<select name='slt_producto' class='form-control' id='producto-id' >");
                            out.print("<option value='' style='display:none'>Seleccione un producto</option>");
                            for (int i = 0; i < ltapdt.size(); i++) {
                                out.print("<option value='" + ltapdt.get(i) + "'>" + ltapdt.get(i) + "</option>");
                            }
                            out.print("</select>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('producto-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("<b>Fecha:</b><br />");
                            out.print("<input type='text' name='txt_fecha' id='datepicker2' placeholder='fecha' autocomplete='off'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('datepicker2');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Lote:</b><br />");
                            out.print("<input type='text' name='txt_lote' id='lote-id' placeholder='lote' autocomplete='off'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('lote-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("<b>Total cavidades:</b><br />");
                            out.print("<input type='number' min='0' name='txt_cavidad' id='cavidad-id' placeholder='Cavidad'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('cavidad-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script><br />");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Cavidades des-habilitadas:</b><br />");
                            out.print("<input type='number' min='0' name='txt_cavidadD' id='cavidadDes-id' placeholder='Cavidad des-habilitadas'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('cavidadDes-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script><br />");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("<b>Cavidades:</b><br />");
                            out.print("<input type='text' name='txt_cavidades' id='cavidades-id' placeholder='Cavidades'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('cavidades-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script><br />");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Herramental</b><br />");
                            out.print("<select name='lst_herramental' id='select-id' onchange='Herramental(this.value)'>");
                            out.print("<option value='0' style='display:none;'>Seleccione herramental</option>");
                            out.print("<optgroup label='Sugeridos'>");
                            for (int i = 0; i < lst_herramental.size(); i++) {
                                Object[] obj_herramentales = (Object[]) lst_herramental.get(i);
                                if (Integer.parseInt(obj_herramentales[0].toString()) != 1) {
                                    if (obj_herramentales[3].toString().contains("[" + id_maquina + "]")) {
                                        if (Integer.parseInt(obj_herramentales[7].toString()) == 3) {
                                            out.print("<option value='" + obj_herramentales[0] + "-" + obj_herramentales[7] + "' disabled>" + obj_herramentales[4] + "-En Reparacion</option>");
                                        } else {
                                            out.print("<option value='" + obj_herramentales[0] + "-" + obj_herramentales[7] + "'>" + obj_herramentales[4] + "</option>");
                                        }
                                    }
                                }
                            }
                            out.print("</optgroup>");
                            out.print("<optgroup label='Todos'>");
                            for (int i = 0; i < lst_herramental.size(); i++) {
                                Object[] obj_herramentales = (Object[]) lst_herramental.get(i);
                                if ((Integer) obj_herramentales[0] != 1) {
                                    if (!obj_herramentales[3].toString().contains("[" + id_maquina + "]")) {
                                        if ((Integer) obj_herramentales[7] == 3) {
                                            out.print("<option value='" + obj_herramentales[0] + "-" + obj_herramentales[7] + "' disabled>" + obj_herramentales[4] + "-En Reparacion</option>");
                                        } else {
                                            out.print("<option value='" + obj_herramentales[0] + "-" + obj_herramentales[7] + "'>" + obj_herramentales[4] + "</option>");
                                        }
                                    }
                                }
                            }
                            out.print("</optgroup>");
                            out.print("</select>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('select-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("<b>Movimiento</b><br />");
                            out.print("<select name='lst_TipoM' id='selectTM-id' onchange='Firma(this)'>");
                            out.print("<option value='0' style='display:none;'>Seleccione Movimiento</option>");
                            for (int i = 0; i < lst_TipoM.size(); i++) {
                                Object[] obj_movimientos = (Object[]) lst_TipoM.get(i);
                                if ((Integer) obj_movimientos[0] == 4) {
                                    out.print("<option value='" + obj_movimientos[0] + "'>" + obj_movimientos[1] + "</option>");
                                }
                            }
                            out.print("</select>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('selectTM-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td colspan='2'><br />");
                            out.print("<b>Descripcion cavidad tapada:</b><br />");
                            out.print("<textarea id='descripcionC-id' name='txt_descripcionC' style='width: 445px; height: 40'></textarea></td>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('descripcionC-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</tr>");
                            //<editor-fold defaultstate="collapsed" desc="DESCRIPCION ANTIGUA">
                            out.print("<tr>");
                            out.print("<td colspan='2'>");
                            if (lst_movimientos != null) {
                                Object[] obj_movimientos = (Object[]) lst_movimientos.get(0);
                                if (Integer.parseInt(obj_movimientos[5].toString()) == 1) {
                                    out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 120'>");
                                    if (area.equals("PRODUCCION INSUMOS")) {
                                        out.print("<b>Descripción:</b><br/>");
                                        out.print("<div contenteditable='true'><p>*</p><p></p></div>");
                                        out.print("<hr /> ");
                                        if (usuarioId == 2) {
                                            out.print("<b>Programacion: </b>");
                                            out.print("<div contenteditable='true'><p>*</p><p></p></div>");
                                        } else {
                                            out.print("<b>Programacion: </b>");
                                            out.print("<div contenteditable='false'><p>*</p><p></p></div>");
                                        }
                                    } else if (area.equals("SISTEMAS")) {
                                        out.print("<b>Descripción::</b><br/>");
                                        out.print("<div contenteditable='true'><p>*</p><p></p></div>");
                                        out.print("<hr /> ");
                                        out.print("<b>Programacion: </b>");
                                        out.print("<div contenteditable='true'><p>*</p><p></p></div>");
                                    }
                                    out.print("</textarea></td>");
                                } else if (!obj_movimientos[16].equals("null")) {
                                    if (obj_movimientos[13].toString().contains("<hr />")) {
                                        String[] desc = obj_movimientos[13].toString().split("<hr />");

                                        if (area.equals("PRODUCCION INSUMOS")) {
                                            if (usuarioId == 2) {
//                                            String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[2].replace("<div>", "<div contenteditable='true'>");
                                                String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>");
                                                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + des + "</textarea>");
                                            } else {
//                                            String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='false'>") + "<hr />" + desc[2].replace("<div>", "<div contenteditable='true'>");
                                                String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='false'>");
                                                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + des + "</textarea>");
                                            }
                                        } else if (area.equals("SISTEMAS")) {
//                                            String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[2].replace("<div>", "<div contenteditable='true'>");
                                            String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>");
                                            out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + des + "</textarea>");
                                        }
                                    } else {
                                        out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>"
                                                + "<strong>Descripci&oacute;n:</strong><br /><div contenteditable='true'><p>*</p></div>"
                                                + "<hr /><strong>Programacion: </strong><div contenteditable='true'><p>*</p></div>"
                                                + "</textarea>");
                                    }
                                } else {
                                    out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 120'>");
                                    if (area.equals("PRODUCCION INSUMOS")) {
                                        out.print("<b>Descripción:</b><br/>");
                                        out.print("<div contenteditable='true'><p>*</p><p></p></div>");
                                        out.print("<hr /> ");
                                        if (usuarioId == 2) {
                                            out.print("<b>Programacion: </b>");
                                            out.print("<div contenteditable='true'><p>*</p><p></p></div>");
//                                        out.print("<hr />");
                                        } else {
                                            out.print("<b>Programacion: </b>");
                                            out.print("<div contenteditable='false'><p>*</p><p></p></div>");
//                                        out.print("<hr />");
                                        }
//                                    out.print("<b>Novedades personal: </b>");
//                                    out.print("<div contenteditable='true'><p>*</p><p></p></div>");
                                    } else if (area.equals("SISTEMAS")) {
                                        out.print("<b>Descripción:</b><br/>");
                                        out.print("<div contenteditable='true'><p>*</p><p></p></div>");
                                        out.print("<hr /> ");
                                        out.print("<b>Programacion: </b>");
                                        out.print("<div contenteditable='true'><p>*</p><p></p></div>");
//                                        out.print("<hr />");
//                                        out.print("<b>Novedades personal: </b>");
//                                        out.print("<div contenteditable='true'><p>*</p><p></p></div>");
                                    }
                                    out.print("</textarea></td>");
                                }
                            } else {
                                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>"
                                        + "<strong>Descripci&oacute;n:</strong><br /><div contenteditable='true'><p>*</p></div>"
                                        + "<hr /><strong>Programacion: </strong><div contenteditable='true'><p>*</p></div>"
                                        + "</textarea>");
                            }

//</editor-fold>
                        }
                        out.print("</tr>");
                        out.print("</table>");
                        out.print("<input type='submit' value='Guardar' onclick='Movimiento();registroM()' id='botonM' />");
                        out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntosM'>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "          <div></div>\n"
                                + "        </div>");
                        out.print("<input type='hidden' name='txt_resp' id='id_resp' value=''>");
                        out.print("</form>");
                        out.print("<div class='cleaner'></div></div>");
                        out.print("</div>");
                        out.print("<br/>");
                        out.print("<br/>");
//</editor-fold>
                    } else {
                        List lst_movimiento = jpa_movimiento.ConsultaMovimientosIdMovimiento(id_movimiento);
                        Object[] obj_movimiento = (Object[]) lst_movimiento.get(0);
                        if (consecutivo == 0) {
                            //<editor-fold defaultstate="collapsed" desc="modificar movimiento">
                            out.print("<div class='tab-content'>");
                            out.print("<h1 class='tab tab-active' title='Historial'>Historial</h1>");
                            out.print("<a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img id='Menu_registro' style='float:left;position: absolute;'  src='Interfaz/Contenido/Iconos/Delete.png' width='20px' height='20px' alt='edit' title='Buscar' /></a>");
                            out.print("<br/>");
                            out.print("<br/>");
                            out.print("<div style='display:block;' id=\"toggle2\">");
                            out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                            out.print("<h3>Modificar movimiento</h3>");
                            out.print("<table  style='width:100%;border:none;font-size: 11px;'>");
                            out.print("<tr>");
                            out.print("<td colspan='2'>");
                            out.print("<b>Codigo:</b><br />");
                            out.print("<form action='Movimiento?opc=4' method='post' style='margin-bottom: 0px;'>");
                            out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                            out.print("<input type='hidden' name='idMV' id='idMVC' value='" + id_movimiento + "'>");
                            out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                            if (codigo != null) {
                                out.print("<input type='text' name='txt_codigo' value='" + codigo + "' id='codigo-id' placeholder='Codigo producto'>");
                            } else {
                                out.print("<input type='text' name='txt_codigo' id='codigo-id' placeholder='Codigo producto'>");
                            }
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('codigo-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("validation.add( Validate.Length, { minimum: 4, maximum: 4} );");
                            out.print("</script>");
                            out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                            out.print("</form>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<form action='Movimiento?opc=3' method='post' name='formM' id='formM' onsubmit='checkSubmit();registroM()'>");
                            out.print("<tr>");
                            if (pageContext.getRequest().getAttribute("productos") != null) {
                                List ltapdt = (List) pageContext.getRequest().getAttribute("productos");
                                out.print("<td>");
                                out.print("<b>Producto:</b><br />");
                                out.print("<select name='slt_productoM' class='form-control' id='producto-id' >");
                                out.print("<option value='' style='display:none'>Seleccione un producto</option>");
                                for (int i = 0; i < ltapdt.size(); i++) {
                                    out.print("<option value='" + ltapdt.get(i) + "'>" + ltapdt.get(i) + "</option>");
                                }
                                out.print("</select>");
                                out.print("<script type='text/javascript'>");
                                out.print("var validation = new LiveValidation('producto-id');");
                                out.print("validation.add( Validate.Presence );");
                                out.print("</script>");
                                out.print("</td>");
                            } else {
                                out.print("<td>");
                                out.print("<b>Producto:</b><br />");
                                out.print("<select name='slt_productoM' class='form-control' id='producto-id' >");
                                out.print("<option value='" + obj_movimiento[8] + "' selected>" + obj_movimiento[8] + "</option>");
                                out.print("</select>");
                                out.print("<script type='text/javascript'>");
                                out.print("var validation = new LiveValidation('producto-id');");
                                out.print("validation.add( Validate.Presence );");
                                out.print("</script>");
                                out.print("</td>");
                            }
                            out.print("<td>");
                            out.print("<b>Fecha:</b><br />");
                            out.print("<input type='text' name='txt_fechaM' value='" + obj_movimiento[7] + "' id=\"datepicker\" placeholder='fecha' autocomplete='off'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('datepicker');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Lote:</b><br />");
                            out.print("<input type='text' name='txt_loteM' id='lote-id' value='" + obj_movimiento[9] + "' placeholder='lote' autocomplete='off'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('lote-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("<b>Total cavidades:</b><br />");
                            out.print("<input type='number' min='0' name='txt_cavidadM' value='" + obj_movimiento[10] + "' id='cavidad-id' placeholder='Cavidad'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('cavidad-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Cavidades des-habilitadas:</b><br />");
                            out.print("<input type='number' min='0' name='txt_cavidadDM' value='" + obj_movimiento[11] + "' id='cavidadDes-id' placeholder='Cavidad des-habilitadas'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('cavidadDes-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("<b>Cavidades:</b><br />");
                            out.print("<input type='text' name='txt_cavidadesM' value='" + obj_movimiento[12] + "' id='cavidades-id' placeholder='Cavidades'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('cavidades-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Herramental</b><br />");
                            out.print("<select name='lst_herramentalM' id='select-id'>");
                            out.print("<option value='" + obj_movimiento[3] + "' style='display:none;'>" + obj_movimiento[4] + "</option>");
                            out.print("<optgroup label='Sugeridos'>");
                            for (int i = 0; i < lst_herramental.size(); i++) {
                                Object[] obj_herramentales = (Object[]) lst_herramental.get(i);
                                if ((Integer) obj_herramentales[0] != 1) {
                                    if (obj_herramentales[3].toString().contains("[" + id_maquina + "]")) {
                                        out.print("<option value='" + obj_herramentales[0] + "'>" + obj_herramentales[4] + "</option>");
                                    }
                                }
                            }
                            out.print("</optgroup>");
                            out.print("<optgroup label='Todos'>");
                            for (int i = 0; i < lst_herramental.size(); i++) {
                                Object[] obj_herramentales = (Object[]) lst_herramental.get(i);
                                if ((Integer) obj_herramentales[0] != 1) {
                                    if (!obj_herramentales[3].toString().contains("[" + id_maquina + "]")) {
                                        out.print("<option value='" + obj_herramentales[0] + "'>" + obj_herramentales[4] + "</option>");
                                    }
                                }
                            }
                            out.print("</optgroup>");
                            out.print("</select>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('select-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("<b>Movimiento</b><br />");
                            out.print("<select name='lst_TipoMM' id='selectM-id' onchange='Firma(this)'>");
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
                            out.print("<td colspan='2'><br />");
                            out.print("<b>Descripcion cavidad tapada:</b><br />");
                            if (obj_movimiento[16] == null) {
                                out.print("<textarea id='descripcionC-id' name='txt_descripcionC' style='width: 445px; height: 40'>N/A</textarea></td>");
                            } else {
                                out.print("<textarea id='descripcionC-id' name='txt_descripcionC' style='width: 445px; height: 40'>" + obj_movimiento[16] + "</textarea></td>");
                            }
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('descripcionC-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td colspan='2'>");
                            out.print("<b>Descripcion:</b><br />");
                            if (obj_movimiento[13].toString().contains("<hr />")) {
                                String[] desc = obj_movimiento[13].toString().split("<hr />");
                                if (area.equals("PRODUCCION INSUMOS")) {
                                    if (usuarioId == 2) {
//                                    String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[2].replace("<div>", "<div contenteditable='true'>");
                                        String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>");
                                        out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + des + "</textarea>");
                                    } else {
//                                    String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='false'>") + "<hr />" + desc[2].replace("<div>", "<div contenteditable='true'>");
                                        String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='false'>");
                                        out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + des + "</textarea>");
                                    }
                                } else if (area.equals("SISTEMAS")) {
//                                    String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[2].replace("<div>", "<div contenteditable='true'>");
                                    String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>");
                                    out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + des + "</textarea>");
                                }
                            } else {
                                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>"
                                        + "<strong>Descripci&oacute;n:</strong><br /><div contenteditable='true'><p>*</p></div>"
                                        + "<hr /><strong>Programacion: </strong><div contenteditable='true'><p>*</p></div>"
                                        + "</textarea>");
                            }
                            out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                            out.print("<input type='hidden' name='idMV' value='" + id_movimiento + "'>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td><input type='submit' value='Guardar' onclick='MovimientoM();registroM()' id='botonM'/>");
                            out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntosM'>\n"
                                    + "          <div></div>\n"
                                    + "          <div></div>\n"
                                    + "          <div></div>\n"
                                    + "        </div>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<input type='hidden' name='txt_resp' id='id_resp' value='" + usa_firmas + "'>");
                            out.print("</form>");
                            out.print("</table>");
                            out.print("<div class='cleaner'></div></div>");
                            out.print("</div>");
                            //</editor-fold>
                        } else if (consecutivo == 1) {
                            //<editor-fold defaultstate="collapsed" desc="registrar movimiento consecutivo">
                            out.print("<div class='tab-content'>");
                            out.print("<h1 class='tab tab-active' title='Historial'>Historial</h1>");
                            out.print("<a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img id='Menu_registro' style='float:left;position: absolute;'  src='Interfaz/Contenido/Iconos/Delete.png' width='20px' height='20px' alt='edit' title='Buscar' /></a>");
                            out.print("<br/>");
                            out.print("<br/>");
                            out.print("<div style='display:block;' id=\"toggle2\">");
                            out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                            out.print("<h3>Actualizar movimiento</h3>");
                            out.print("<table  style='width:100%;border:none;font-size: 11px;'>");
                            out.print("<tr>");
                            out.print("<td colspan='2'>");
                            out.print("<b>Codigo:</b><br />");
                            out.print("<form action='Movimiento?opc=4' method='post' style='margin-bottom: 0px;'>");
                            out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                            out.print("<input type='hidden' name='idMV' id='idMVC' value='" + id_movimiento + "'>");
                            out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                            out.print("<input type='hidden' name='cons' value='" + consecutivo + "'>");
                            if (codigo != null) {
                                out.print("<input type='text' name='txt_codigo' value='" + codigo + "' id='codigo-id' placeholder='Codigo producto' required>");
                            } else {
                                out.print("<input type='text' name='txt_codigo' id='codigo-id' placeholder='Codigo producto' required>");
                            }
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('codigo-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("validation.add( Validate.Length, { minimum: 4, maximum: 4} );");
                            out.print("</script>");
                            out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                            out.print("</form>");
                            out.print("</td>");
                            out.print("</tr>");
                            if (pageContext.getRequest().getAttribute("productos") != null) {
                                List ltapdt = (List) pageContext.getRequest().getAttribute("productos");
                                out.print("<form action='Movimiento?opc=2' method='post' name='formM' id='formM' onsubmit='registroM();checkSubmit()'>");
                                out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                                out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                                out.print("<input type='hidden' name='slt_producto' value='" + obj_movimiento[8] + "'>");
                                out.print("<input type='hidden' name='lst_herramental' value='" + obj_movimiento[3] + "-" + obj_movimiento[5] + "'>");
                                out.print("<input type='hidden' name='txt_resp' id='id_resp' value='" + usa_firmas + "'>");
                                out.print("<input type='hidden' name='tipoM' value='2'>");
                                out.print("<tr>");
                                out.print("<tr>");
                                out.print("<td>");
                                out.print("<b>Producto:</b><br />");
                                out.print("<select name='slt_productoM' class='form-control' id='producto-id' >");
                                out.print("<option value='' style='display:none'>Seleccione un producto</option>");
                                for (int i = 0; i < ltapdt.size(); i++) {
                                    out.print("<option value='" + ltapdt.get(i) + "'>" + ltapdt.get(i) + "</option>");
                                }
                                out.print("</select>");
                                out.print("<script type='text/javascript'>");
                                out.print("var validation = new LiveValidation('producto-id');");
                                out.print("validation.add( Validate.Presence );");
                                out.print("</script>");
                                out.print("</td>");
                            } else {
                                out.print("<form action='Movimiento?opc=2' method='post' name='formM' id='formM' onsubmit='registroM();'>");
                                out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                                out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                                out.print("<input type='hidden' name='slt_producto' value='" + obj_movimiento[8] + "'>");
                                out.print("<input type='hidden' name='lst_herramental' value='" + obj_movimiento[3] + "-" + obj_movimiento[5] + "'>");
                                out.print("<input type='hidden' name='txt_resp' id='id_resp' value='" + usa_firmas + "'>");
                                out.print("<input type='hidden' name='tipoM' value='2'>");
                                out.print("<tr>");
                                out.print("<td>");
                                out.print("<b>Producto:</b><br />");
                                out.print("<select name='slt_producto' class='form-control' id='producto-id' disabled>");
                                out.print("<option value='" + obj_movimiento[8] + "' selected>" + obj_movimiento[8] + "</option>");
                                out.print("</select>");
                                out.print("<script type='text/javascript'>");
                                out.print("var validation = new LiveValidation('producto-id');");
                                out.print("validation.add( Validate.Presence );");
                                out.print("</script>");
                                out.print("</td>");
                            }
                            out.print("<td>");
                            out.print("<b>Fecha:</b><br />");
                            out.print("<input type='text' name='txt_fecha' value='" + obj_movimiento[7] + "' id=\"datepicker\" placeholder='fecha' autocomplete='off'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('datepicker');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Lote:</b><br />");
                            out.print("<input type='text' name='txt_lote' id='lote-id' value='" + obj_movimiento[9] + "' placeholder='lote' autocomplete='off'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('lote-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("<b>Total cavidades:</b><br />");
                            out.print("<input type='number' min='0' name='txt_cavidad' value='" + obj_movimiento[10] + "' id='cavidad-id' placeholder='Cavidad'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('cavidad-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Cavidades des-habilitadas:</b><br />");
                            out.print("<input type='number' min='0' name='txt_cavidadD' value='" + obj_movimiento[11] + "' id='cavidadDes-id' placeholder='Cavidad des-habilitadas'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('cavidadDes-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("<b>Cavidades:</b><br />");
                            out.print("<input type='text' name='txt_cavidades' value='" + obj_movimiento[12] + "' id='cavidades-id' placeholder='Cavidades'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('cavidades-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Herramental</b><br />");
                            out.print("<select name='lst_herramental' id='select-id' disabled>");
                            out.print("<option value='" + obj_movimiento[3] + "-" + obj_movimiento[5] + "' style='display:none;'>" + obj_movimiento[4] + "</option>");
                            out.print("<optgroup label='Sugeridos'>");
                            for (int i = 0; i < lst_herramental.size(); i++) {
                                Object[] obj_herramentales = (Object[]) lst_herramental.get(i);
                                if (obj_herramentales[3].toString().contains("[" + id_maquina + "]")) {
                                    out.print("<option value='" + obj_herramentales[0] + "-" + obj_herramentales[7] + "'>" + obj_herramentales[4] + "</option>");
                                }
                            }
                            out.print("</optgroup>");
                            out.print("<optgroup label='Todos'>");
                            for (int i = 0; i < lst_herramental.size(); i++) {
                                Object[] obj_herramentales = (Object[]) lst_herramental.get(i);
                                if (!obj_herramentales[3].toString().contains("[" + id_maquina + "]")) {
                                    out.print("<option value='" + obj_herramentales[0] + "-" + obj_herramentales[7] + "'>" + obj_herramentales[4] + "</option>");
                                }
                            }
                            out.print("</optgroup>");
                            out.print("</select>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('select-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("<b>Movimiento</b><br />");
                            out.print("<select name='lst_TipoM' id='selectM-id' onchange='Firma(this)'>");
                            if (tipoMV != 0) {
                                out.print("<option value='" + tipoMV + "' style='display:none;'>" + ((tipoMV == 2) ? "DES-MONTADO_STOCK" : "DES-MONTADO_REPARACION") + "</option>");
                            } else {
                                out.print("<option value='" + obj_movimiento[5] + "' style='display:none;'>" + obj_movimiento[6] + "</option>");
                            }
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
                            out.print("<td colspan='2'><br />");
                            out.print("<b>Descripcion cavidad tapada:</b><br />");
                            out.print("<textarea id='descripcionC-id' name='txt_descripcionC' style='width: 445px; height: 40'>" + ((obj_movimiento[16].equals("null") ? "N/A" : obj_movimiento[16])) + "</textarea></td>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('descripcionC-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</tr>");
                            //<editor-fold defaultstate="collapsed" desc="DESCRIPCION ANTIGUA">
                            out.print("<tr>");
                            out.print("<td colspan='2'><b>Descripcion:</b><br />");
                            if (obj_movimiento[13].toString().contains("<hr />")) {
                                String[] desc = obj_movimiento[13].toString().split("<hr />");
                                if (area.equals("PRODUCCION INSUMOS")) {
                                    if (usuarioId == 2) {
//                                        String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[2].replace("<div>", "<div contenteditable='true'>");
                                        String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>");
                                        out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + des + "</textarea>");
                                    } else {
//                                        String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='false'>") + "<hr />" + desc[2].replace("<div>", "<div contenteditable='true'>");
                                        String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='false'>");
                                        out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + des + "</textarea>");
                                    }
                                } else if (area.equals("SISTEMAS")) {
//                                        String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[2].replace("<div>", "<div contenteditable='true'>");
                                    String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>");
                                    out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + des + "</textarea>");
                                }
                            } else {
                                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>"
                                        + "<strong>Descripci&oacute;n:</strong><br /><div contenteditable='true'><p>*</p></div>"
                                        + "<hr /><strong>Programacion: </strong><div contenteditable='true'><p>*</p></div>"
                                        + "</textarea>");
                            }
                            out.print("</td>");
                            out.print("</tr>");
                            //</editor-fold>
//                            //<editor-fold defaultstate="collapsed" desc="DESCRIPCION NUEVA">
//                            if (obj_movimiento[13].toString().contains("][")) {
//                                String[] descripcion = obj_movimiento[13].toString().replace("][", "///").replace("[", "").replace("]", "").split("///");
//                                out.print("<tr><td><b>Descripcion:</b><br />");
//                                out.print("<textarea name='txt_descripcion' id='txt_descripcion' style='width:172%' value='" + descripcion[0] + "'>" + descripcion[0] + "</textarea></td></tr>");
//                                out.print("<tr><td><b>Programacion:</b><br />");
//                                out.print("<textarea name='txt_programacion' id='txt_programacion' style='width:172%' value='" + descripcion[1] + "'>" + descripcion[1] + "</textarea></td></tr>");
//                            } else {
//                                out.print("<tr><td><i onclick='VinetasTd()'>Viñetas</i></td></tr>");
//                                out.print("<tr><td contenteditable='true' id='Td_descripcion' onkeyup=\"javascript:document.getElementById('txt_descripcion').value = document.getElementById('Td_descripcion').innerHTML;\">" + obj_movimiento[13] + "</td></tr>");
//                                out.print("<div style='display:none'><textarea name='txt_descripcion' id='txt_descripcion'>" + obj_movimiento[13] + "</textarea>"
//                                        + "<textarea name='txt_programacion' id='txt_descripcion'> </textarea></div>");
//                            }
//                            //</editor-fold>
                            out.print("<tr>");
                            out.print("<td><input type='submit' value='Guardar' onclick='MovimientoC();registroM()' id='botonM'/>");
                            out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntosM'>\n"
                                    + "          <div></div>\n"
                                    + "          <div></div>\n"
                                    + "          <div></div>\n"
                                    + "        </div>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("</table>");
                            out.print("</form>");
                            out.print("<div class='cleaner'></div></div>");
                            out.print("</div>");

                            //</editor-fold>
                        } else {
                            //<editor-fold defaultstate="collapsed" desc="modificar producto movimiento">
                            out.print("<div class='tab-content'>");
                            out.print("<h1 class='tab tab-active' title='Historial'>Historial</h1>");
                            out.print("<a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img id='Menu_registro' style='float:left;position: absolute;'  src='Interfaz/Contenido/Iconos/Delete.png' width='20px' height='20px' alt='edit' title='Buscar' /></a>");
                            out.print("<br/>");
                            out.print("<br/>");
                            out.print("<div style='display:block;' id=\"toggle2\">");
                            out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                            out.print("<h3>Modificar movimiento</h3>");
                            out.print("<table  style='width:100%;border:none;font-size: 11px;'>");
                            out.print("<tr>");
                            out.print("<td colspan='2'>");
                            out.print("<b>Codigo:</b><br />");
                            out.print("<form action='Movimiento?opc=4' method='post' style='margin-bottom: 0px;'>");
                            out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                            out.print("<input type='hidden' name='idMV' id='idMVC' value='" + id_movimiento + "'>");
                            out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                            if (codigo != null) {
                                out.print("<input type='text' name='txt_codigo' value='" + codigo + "' id='codigo-id' placeholder='Codigo producto'>");
                            } else {
                                out.print("<input type='text' name='txt_codigo' id='codigo-id' placeholder='Codigo producto'>");
                            }
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('codigo-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("validation.add( Validate.Length, { minimum: 4, maximum: 4} );");
                            out.print("</script>");
                            out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                            out.print("</form>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<form action='Movimiento?opc=3' method='post' name='formM' id='formM' onsubmit='checkSubmit();registroM();'>");
                            out.print("<tr>");
                            if (pageContext.getRequest().getAttribute("productos") != null) {
                                List ltapdt = (List) pageContext.getRequest().getAttribute("productos");
                                out.print("<td>");
                                out.print("<b>Producto:</b><br />");
                                out.print("<select name='slt_productoM' class='form-control' id='producto-id' >");
                                out.print("<option value='' style='display:none'>Seleccione un producto</option>");
                                for (int i = 0; i < ltapdt.size(); i++) {
                                    out.print("<option value='" + ltapdt.get(i) + "'>" + ltapdt.get(i) + "</option>");
                                }
                                out.print("</select>");
                                out.print("<script type='text/javascript'>");
                                out.print("var validation = new LiveValidation('producto-id');");
                                out.print("validation.add( Validate.Presence );");
                                out.print("</script>");
                                out.print("</td>");
                            } else {
                                out.print("<td>");
                                out.print("<b>Producto:</b><br />");
                                out.print("<select name='slt_productoM' class='form-control' id='producto-id' >");
                                out.print("<option value='" + obj_movimiento[8] + "' selected>" + obj_movimiento[8] + "</option>");
                                out.print("</select>");
                                out.print("<script type='text/javascript'>");
                                out.print("var validation = new LiveValidation('producto-id');");
                                out.print("validation.add( Validate.Presence );");
                                out.print("</script>");
                                out.print("</td>");
                            }
                            out.print("<td>");
                            out.print("<b>Fecha:</b><br />");
                            out.print("<input type='text' name='txt_fechaM' value='" + obj_movimiento[7] + "' id=\"datepicker\" placeholder='fecha' autocomplete='off'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('datepicker');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Lote:</b><br />");
                            out.print("<input type='text' name='txt_loteM' id='lote-id' value='" + obj_movimiento[9] + "' placeholder='lote' autocomplete='off'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('lote-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("<b>Total cavidades:</b><br />");
                            out.print("<input type='number' min='0' name='txt_cavidadM' value='" + obj_movimiento[10] + "' id='cavidad-id' placeholder='Cavidad'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('cavidad-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Cavidades des-habilitadas:</b><br />");
                            out.print("<input type='number' min='0' name='txt_cavidadDM' value='" + obj_movimiento[11] + "' id='cavidadDes-id' placeholder='Cavidad des-habilitadas'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('cavidadDes-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("<b>Cavidades:</b><br />");
                            out.print("<input type='text' name='txt_cavidadesM' value='" + obj_movimiento[12] + "' id='cavidades-id' placeholder='Cavidades'>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('cavidades-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td>");
                            out.print("<b>Herramental</b><br />");
                            out.print("<select name='lst_herramentalM' id='select-id'>");
                            out.print("<option value='" + obj_movimiento[3] + "' style='display:none;'>" + obj_movimiento[4] + "</option>");
                            out.print("<optgroup label='Sugeridos'>");
                            for (int i = 0; i < lst_herramental.size(); i++) {
                                Object[] obj_herramentales = (Object[]) lst_herramental.get(i);
                                if ((Integer) obj_herramentales[0] != 1) {
                                    if (obj_herramentales[3].toString().contains("[" + id_maquina + "]")) {
                                        out.print("<option value='" + obj_herramentales[0] + "'>" + obj_herramentales[4] + "</option>");
                                    }
                                }
                            }
                            out.print("</optgroup>");
                            out.print("<optgroup label='Todos'>");
                            for (int i = 0; i < lst_herramental.size(); i++) {
                                Object[] obj_herramentales = (Object[]) lst_herramental.get(i);
                                if ((Integer) obj_herramentales[0] != 1) {
                                    if (!obj_herramentales[3].toString().contains("[" + id_maquina + "]")) {
                                        out.print("<option value='" + obj_herramentales[0] + "'>" + obj_herramentales[4] + "</option>");
                                    }
                                }
                            }
                            out.print("</optgroup>");
                            out.print("</select>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('select-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</td>");
                            out.print("<tr>");
                            out.print("<td colspan='2'><br />");
                            out.print("<b>Descripcion cavidad tapada:</b><br />");
                            if (obj_movimiento[16] == null) {
                                out.print("<textarea id='descripcionC-id' name='txt_descripcionC' style='width: 445px; height: 40'>N/A</textarea></td>");
                            } else {
                                out.print("<textarea id='descripcionC-id' name='txt_descripcionC' style='width: 445px; height: 40'>" + obj_movimiento[16] + "</textarea></td>");
                            }
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('descripcionC-id');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script>");
                            out.print("</tr>");
                            out.print("<td>");
                            out.print("<b>Movimiento</b><br />");
                            out.print("<select name='lst_TipoMM' id='selectM-id' onchange='Firma(this)'>");
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
                            if (obj_movimiento[13].toString().contains("<hr />")) {
                                String[] desc = obj_movimiento[13].toString().split("<hr />");
                                if (area.equals("PRODUCCION INSUMOS")) {
                                    if (usuarioId == 2) {
//                                        String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[2].replace("<div>", "<div contenteditable='true'>");
                                        String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>");
                                        out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + des + "</textarea>");
                                    } else {
//                                        String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='false'>") + "<hr />" + desc[2].replace("<div>", "<div contenteditable='true'>");
                                        String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='false'>");
                                        out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + des + "</textarea>");
                                    }
                                } else if (area.equals("SISTEMAS")) {
//                                        String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[2].replace("<div>", "<div contenteditable='true'>");
                                    String des = desc[0].replace("<div>", "<div contenteditable='true'>") + "<hr />" + desc[1].replace("<div>", "<div contenteditable='true'>");
                                    out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>" + des + "</textarea>");
                                }
                            } else {
                                out.print("<textarea id='editor' name='txt_descripcion' style='width: 500px; height: 400' placeholder='descripcion'>"
                                        + "<strong>Descripci&oacute;n:</strong><br /><div contenteditable='true'><p>*</p></div>"
                                        + "<hr /><strong>Programacion: </strong><div contenteditable='true'><p>*</p></div>"
                                        + "</textarea>");
                            }
                            out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                            out.print("<input type='hidden' name='idMV' value='" + id_movimiento + "'>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td><input type='submit' value='Guardar' onclick='MovimientoM();registroM()' id='botonM'/>");
                            out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntosM'>\n"
                                    + "          <div></div>\n"
                                    + "          <div></div>\n"
                                    + "          <div></div>\n"
                                    + "        </div>");
                            out.print("</td>");
                            out.print("</tr>");
                            out.print("<input type='hidden' name='txt_resp' id='id_resp' value='" + usa_firmas + "'>");
                            out.print("</form>");
                            out.print("</table>");
                            out.print("<div class='cleaner'></div></div>");
                            out.print("</div>");
                            //</editor-fold>
                        }
                    }
                } else {
                    out.print("<div class='tab-content'>");
                    out.print("<h1 class='tab' title='Historial'>Historial</h1>");
                }
                if (lst_movimientos != null) {
                    //<editor-fold defaultstate="collapsed" desc="historial movimientos">
                    if ((Integer) obj_maquina[3] == 3) {
                        out.print("<table class='table' style='width:100%'>");
                        for (int i = 0; i < lst_movimientos.size(); i++) {
                            int contO = 0;
                            Object[] obj_movimientos = (Object[]) lst_movimientos.get(i);
                            if (i != 0) {
                                Object[] obj_mov = (Object[]) lst_movimientos.get(i - 1);
                                if (!obj_movimientos[8].toString().equals(obj_mov[8].toString()) || (Integer) obj_movimientos[3] != (Integer) obj_mov[3]) {
                                    out.print("<tr>");
                                    if (obj_movimientos[6].toString().equals("N/A")) {
                                        out.print("<td align='center' style='width:10%;'>" + obj_movimientos[4] + "</td>");
                                    } else {
                                        out.print("<td align='center' style='width:10%;'><a href='Herramental?opc=5&idH=" + obj_movimientos[3] + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus='>" + obj_movimientos[4] + "</a></td>");
                                    }
                                    out.print("<td align='center' colspan='2'><b>Producto: </b>" + obj_movimientos[8] + "</td>");
                                    out.print("</tr>");
                                }
                            } else {
                                out.print("<tr>");
                                if (obj_movimientos[6].toString().equals("N/A")) {
                                    out.print("<td align='center' style='width:10%;'>" + obj_movimientos[4] + "</td>");
                                } else {
                                    out.print("<td align='center' style='width:10%;'><a href='Herramental?opc=5&idH=" + obj_movimientos[3] + "&idP=" + 0 + "&idMV=" + 0 + "&idS=" + 0 + "&txt_bus='>" + obj_movimientos[4] + "</a></td>");
                                }
                                out.print("<td align='center'><b>Producto: </b>" + obj_movimientos[8] + "</td>");
                                out.print("<td align='center'><a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + obj_movimientos[0] + "&idP=" + 0 + "&idS=" + 0 + "&cons=" + 1 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Update.png' width='22' height='22' title='Consecutivo'></a></td>");
                                out.print("</tr>");
                            }
                            out.print("<tr>");
                            out.print("<td colspan='4' valing='top'>");
                            if (Integer.parseInt(obj_movimientos[16].toString()) != 1) {
//                            if ((Integer) obj_movimientos[16] != 1) {
                                if (obj_movimientos[6].toString().equals("N/A")) {
                                    out.print("<button class='accordion'><div style='float:left;'><b style='color:black;'>" + obj_movimientos[7] + " //</b><b style='color:red'>Parada</b></div></button>");
                                } else {
                                    out.print("<button class='accordion'><div style='float:left;'><b style='color:black;'>" + obj_movimientos[7] + " // " + obj_movimientos[6] + "</b></div></button>");
                                }
                            } else if (obj_movimientos[6].toString().equals("N/A")) {
                                if (area.equals("PRODUCCION INSUMOS") || area.equals("SISTEMAS")) {
                                    out.print("<button class='accordion'><div style='float:left;'><b style='color:black;'>" + obj_movimientos[7] + " // </b><b style='color:red'>Parada</b></div>"
                                            + "<div style='float:right;'><a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + obj_movimientos[0] + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='20' height='20'></a>&nbsp;&nbsp;&nbsp;"
                                            + "<a href='#' onclick='idMV(" + obj_movimientos[0] + ")'><img id='Menu_mail' src='Interfaz/Contenido/Iconos/Mail.png' width='22px' height='15px'></a></div></button>");
                                } else {
                                    out.print("<button class='accordion'><div style='float:left;'><b style='color:black;'>" + obj_movimientos[7] + " // </b><b style='color:red'>Parada</b></div></button>");
                                }
                            } else if (area.equals("PRODUCCION INSUMOS") || area.equals("SISTEMAS")) {
                                out.print("<button class='accordion'><div style='float:left;'><b style='color:black;'>" + obj_movimientos[7] + " // " + obj_movimientos[6] + "</b></div>"
                                        + "<div style='float:right;'><a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + obj_movimientos[0] + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='20' height='20'></a>&nbsp;&nbsp;&nbsp;"
                                        + "<a href='#' onclick='idMV(" + obj_movimientos[0] + ")'><img id='Menu_mail' src='Interfaz/Contenido/Iconos/Mail.png' width='22px' height='15px'></a></div></button>");
                            } else {
                                out.print("<button class='accordion'><div style='float:left;'><b style='color:black;'>" + obj_movimientos[7] + " // </b><b style='color:red'>Parada</b></div></button>");
                            }
                            out.print("<div class='panel' style='overflow:scroll;'>");
                            out.print("<table class='table' style='width:100%;'>");
                            out.print("<tr>");
                            out.print("<td colspan='3' style='background-color:#979595;' align='center'><b style='color:white;'>COPIA NO CONTROLADA</b></td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td align='center' style='width:30%'><b><img src='Interfaz/Contenido/images/Logo.png' style='width:60%;'></b></td>");
                            out.print("<td align='center'><b class='negro'>Registro de actividades</b></td>");
                            out.print("<td align='center'><b>Codigo: </b><b class='negro'>R-PI-019</b><hr /><b>Version: </b><b class='negro'>3</b></td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td colspan='3'><b>Producto: </b>" + obj_movimientos[8] + " | <b>MOLDE :</b>" + obj_movimientos[4] + " " + obj_movimientos[17] + " | <b>MAQUINA:</b>" + obj_maquina[1] + "</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            out.print("<td align='center'><b>Total cavidades: </b>" + obj_movimientos[10] + "</td>");
                            out.print("<td align='center'><b>Cavidades deshabilitadas: </b>" + obj_movimientos[11] + "</td>");
                            out.print("<td align='center' style='width:20%'><b>Cavidades: </b>" + obj_movimientos[12] + "</td>");
                            out.print("</tr>");
                            out.print("<tr>");
                            if (obj_movimientos[18] == null) {
                                out.print("<td colspan='3'><b>Descripcion Cavidad(es) des-habilitadas: </b>N/A</td>");
                            } else {
                                out.print("<td colspan='3'><b>Descripcion Cavidad(es) des-habilitadas: </b>" + obj_movimientos[18] + "</td>");
                            }
                            out.print("</tr>");
                            out.print("<tr>");
                            if (obj_movimientos[6].toString().equals("N/A")) {
                                out.print("<td valign='top' colspan='2'><b>Descripcion: </b>" + obj_movimientos[13] + "</td>");
                            } else {
                                out.print("<td valign='top' colspan='2'>" + obj_movimientos[13] + "</td>");
                            }
                            out.print("<td valign='top'><b>Responsables: </b><br />" + obj_movimientos[14].toString().replace("[", "").replace("]", "<br />") + "");
                            out.print("" + ((obj_movimientos[19] != null) ? "<hr /><b>Confirman: </b><br />" + obj_movimientos[19].toString().replace("[", "").replace("]", "<br />") : "") + "</td>");
                            out.print("</tr>");
                            out.print("</table>");
                            out.print("</div>");
                            out.print("</td>");
                            out.print("</tr>");
                        }
                        out.print("<tr>");
                        out.print("<td colspan='3' style='background-color: #eee;'></td>");
                        out.print("</tr>");
                        out.print("</table>");
                    }
                    //</editor-fold>
                } else {
                    out.print("<h3><b style='color:#292929'>No se han encontrado resultados</b></h3>");
                }
                out.print("</div>");
            }
            if ((documento != 0 || codUsa != 0)) {
                out.print("<div class='overlay' tabindex='-1' id='bloq' style='opacity: 1.06; display: block;'>");
                out.print("<fieldset class='resalta' id='Confirmar_movimiento' style='visibility: visible;width:400px'>");
                int valF = Integer.parseInt(pageContext.getRequest().getAttribute("txt_val").toString());
                List lst_usa = jpa_usuario.Firma(documento, codUsa);
                if (lst_usa != null) {
                    Object[] obj_usa = (Object[]) lst_usa.get(0);
                    out.print("<div style='float:right;'><a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
                    out.print("<h3>Confirmar desmontado</h3>");
                    out.print("<form action='Movimiento?opc=7' method='post' id='formP'>");
                    out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                    out.print("<input type='hidden' name='idTMV' id='idTMV' value='" + tipoMV + "'>");
                    out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                    out.print("<input type='hidden' name='idMV' id='idMVF' value='" + id_movimiento + "'>");
                    if (!usa_firmas.contains("" + obj_usa[2] + " / " + obj_usa[7] + "")) {
                        if (Integer.parseInt(obj_usa[5].toString()) == 3) {
                            usa_firmas = usa_firmas.replace("1", "" + obj_usa[2] + " / " + obj_usa[7] + "");
                            valF++;
                        } else if (Integer.parseInt(obj_usa[5].toString()) == 6 || (Integer) obj_usa[5] == 6) {
                            usa_firmas = usa_firmas.replace("2", "" + obj_usa[2] + " / " + obj_usa[7] + "");
                            valF++;
                        } else if (Integer.parseInt(obj_usa[5].toString()) == 10) {
                            usa_firmas = usa_firmas.replace("3", "" + obj_usa[2] + " / " + obj_usa[7] + "");
                            valF++;
                        }
                    }
                    out.print("<input type='t' name='txt_resp' id='id_resp' value='" + usa_firmas + "'>");
                    out.print("<table style='width:100%;border:none;font-size: 11px;'>");
                    out.print("<tr>");
                    out.print("<td>");
                    out.print("<b>Cedula</b><br />");
                    out.print("<input type='text' name='txt_doc' id='id_doc' value='' " + ((valF == 3) ? "disabled" : "") + ">");
                    if (valF != 3) {
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('id_doc');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script>");
                    }
                    out.print("</td>");
                    out.print("<td>");
                    out.print("<b>Codigo</b><br />");
                    out.print("<input type='text' name='txt_cod' id='id_cod' value='' " + ((valF == 3) ? "disabled" : "") + "><br />");
                    if (valF != 3) {
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('id_cod');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script>");
                    }
                    out.print("</td>");
                    out.print("</tr>");
                    String[] firmas = null;
                    try {
                        firmas = usa_firmas.replace("][", "-").split("-");
                    } catch (Exception e) {
                        firmas = null;
                    }
                    out.print("<tr>");
                    out.print("<td colspan='2'><b>Calidad: </b>" + ((firmas != null) ? firmas[0].replace("[", "").replace("1", "") : "") + "</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td colspan='2'><b>Produccion: </b>" + ((firmas != null) ? firmas[1].replace("2", "") : "") + "</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td colspan='2'><b>Proyectos: </b>" + ((firmas != null) ? firmas[2].replace("]", "").replace("3", "") : "") + "</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td colspan='2' align='center'>");
                    out.print("<input type='hidden' name='txt_val' id='idVal' value='" + valF + "'>");
                    out.print("<input type='submit' id='btsubmit' value='" + ((valF == 3) ? "Guardar" : "Firmar") + "'>");
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
                } else {
                    out.print("<div style='float:right;'><a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
                    out.print("<h3>Confirmar desmontado</h3>");
                    out.print("<form action='Movimiento?opc=7' method='post' id='formP'>");
                    out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                    out.print("<input type='hidden' name='idTMV' id='idTMV' value='" + tipoMV + "'>");
                    out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                    out.print("<input type='hidden' name='idMV' id='idMVF' value='" + id_movimiento + "'>");
                    out.print("<input type='hidden' name='txt_resp' id='id_resp' value='" + usa_firmas + "'>");
                    out.print("<table style='width:100%;border:none;font-size: 11px;'>");
                    out.print("<tr>");
                    out.print("<td>");
                    out.print("<b>Cedula</b><br />");
                    out.print("<input type='text' name='txt_doc' id='id_doc' value='' " + ((valF == 3) ? "disabled" : "") + ">");
                    if (valF != 3) {
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('id_doc');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script>");
                    }
                    out.print("</td>");
                    out.print("<td>");
                    out.print("<b>Codigo</b><br />");
                    out.print("<input type='text' name='txt_cod' id='id_cod' value='' " + ((valF == 3) ? "disabled" : "") + "><br />");
                    if (valF != 3) {
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('id_cod');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script>");
                    }
                    out.print("</td>");
                    out.print("</tr>");
                    String[] firmas = null;
                    try {
                        firmas = usa_firmas.replace("][", "-").split("-");
                    } catch (Exception e) {
                        firmas = null;
                    }
                    out.print("<tr>");
                    out.print("<td colspan='2'><b>Calidad: </b>" + ((firmas != null) ? firmas[0].replace("[", "").replace("1", "") : "") + "</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td colspan='2'><b>Produccion: </b>" + ((firmas != null) ? firmas[1].replace("2", "") : "") + "</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td colspan='2'><b>Proyectos: </b>" + ((firmas != null) ? firmas[2].replace("]", "").replace("3", "") : "") + "</td>");
                    out.print("</tr>");
                    out.print("<tr>");
                    out.print("<td colspan='2' align='center'>");
                    out.print("<input type='hidden' name='txt_val' id='idVal' value='" + valF + "'>");
                    out.print("<input type='submit' id='btsubmit' value='" + ((valF == 3) ? "Guardar" : "Firmar") + "'>");
                    out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "          <div></div>\n"
                            + "        </div>");
                    out.print("</td>");
                    out.print("</tr>");
                    out.print("</table>");
                    out.print("<b style='color:#292929'>No se encontraron resultados</b>");
                    out.print("</form>");
                    out.print("</fieldset>");
                    out.print("</div>");
                }
            } else {
                out.print("<div class='overlay' tabindex='-1' id='bloq' style='opacity: 1.06; display: none;'>");
                out.print("<fieldset class='resalta' id='Confirmar_movimiento' style='visibility: hidden;width:400px'>");
                out.print("<div style='float:right;'><a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22' title='Cancelar'></a></div>");
                out.print("<h3>Confirmar desmontado</h3>");
                out.print("<form action='Movimiento?opc=7' method='post' id='formP'>");
                out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                out.print("<input type='hidden' name='idTMV' id='idTMV' value='" + tipoMV + "'>");
                out.print("<input type='hidden' name='idMV' id='idMVF' value='" + id_movimiento + "'>");
                out.print("<input type='hidden' name='txt_resp' id='id_resp' value='[1][2][3]'>");
                out.print("<table style='width:100%;border:none;font-size: 11px;'>");
                out.print("<tr>");
                out.print("<td>");
                out.print("<b>Cedula</b><br />");
                out.print("<input type='text' name='txt_doc' id='id_doc' value=''>");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('id_doc');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("</td>");
                out.print("<td>");
                out.print("<b>Codigo</b><br />");
                out.print("<input type='text' name='txt_cod' id='id_cod' value=''><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('id_cod');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("</td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td colspan='2'><b>Calidad: </b></td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td colspan='2'><b>Produccion: </b></td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td colspan='2'><b>Proyectos: </b></td>");
                out.print("</tr>");
                out.print("<tr>");
                out.print("<td colspan='2' align='center'>");
                out.print("<input type='hidden' name='txt_val' id='idVal' value='0'>");
                out.print("<input type='submit' id='btsubmit' value='Firmar'>");
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
            }
            out.print("<div class='tab-content'>");
            out.print("<h1 class='tab' title='Pendientes'>Pendientes</h1>");
            //<editor-fold defaultstate="collapsed" desc="enviar email Pendientes">
            out.print("<div style='display:none;position:absolute;float:right;width:300px;font-size:14px;right:140px;background-color:#fff;' id='toggleP'>");
            out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:290px;padding: 20px 0 20px 10px;'>");
            out.print("<h3>Seleccionar Cargos</h3>");
            out.print("<form action='Maquina?opc=9' method='post' name='formMailP' onsubmit='MailP();'>");
            out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
            out.print("<input type='hidden' name='idP' id='idP' value=''>");
            out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
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
            //</editor-fold>
            if (id_pendiente == 0) {
                //<editor-fold defaultstate="collapsed" desc="registrar pendiente">
//                out.print("<script src=\"Interfaz/EditorHtml/htmljquery-3.5.1.min.js\" type=\"text/javascript\"></script>");
//                out.print("<script src=\"Interfaz/EditorHtml/htmlpopper.min.js\" type=\"text/javascript\"></script>");
//                out.print("<link href=\"Interfaz/EditorHtml/htmlbootstrap2.min.css\" rel=\"stylesheet\" type=\"text/css\" />");
//                out.print("<script src=\"Interfaz/EditorHtml/htmlbootstrap.min.js\" type=\"text/javascript\"></script>");
//                out.print("<link href=\"Interfaz/EditorHtml/htmlsummernote-bs4.min.css\" rel=\"stylesheet\" type=\"text/css\" />");
//                out.print("<script src=\"Interfaz/EditorHtml/htmlsummernote-bs4.min.js\" type=\"text/javascript\"></script>");
                if (id_movimiento == 0) {
                    out.print("<img id='Menu_pendiente' src='Interfaz/Contenido/Iconos/Plus.png' width='20px' height='20px' alt='edit' title='Buscar' />");
                    out.print("<script>");
                    out.print("$(Menu_pendiente).click(function() {");
                    out.print("$(\"#toggle5\").toggle(\"slide\");");
                    out.print("});");
                    out.print("</script>");
                    out.print("<div style='display:none;' id=\"toggle5\">");
                    out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                    out.print("<h3>Nuevo Pendiente</h3>");
                    out.print("<form action='Maquina?opc=5' method='post' name='formP' onsubmit='registroP();'>");
                    out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                    out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                    out.print("<b>Fecha:</b><br />");
                    out.print("<input type='text' name='txt_fecha' id=\"datepicker\" placeholder='fecha'>");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('datepicker');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script><br />");
                    out.print("<b>Descripcion:</b><br />");
                    out.print("<textarea id='editor' name='txt_pendiente' style='width: 500px; height: 400px' placeholder='descripcion'><div contenteditable='true'><p>*</p></div></textarea>");
                    out.print("<script>"
                            + "    CKEDITOR.replace(\"editor\");    "
                            + "</script>");
                    out.print("<input type='submit' value='Guardar' onclick='Pendiente()' id='botonP' style='margin-top: 10px;'/>");
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
                List lst_pendiente = jpa_maquina.ConsultaPendienteMaquinaIdPendiente(id_pendiente);
                Object[] obj_pendiente = (Object[]) lst_pendiente.get(0);
                out.print("<a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + 0 + "&txt_bus=" + filtro + "&idS=" + 0 + "'><img id='Menu_registro' style='float:left;position: absolute;'  src='Interfaz/Contenido/Iconos/Delete.png' width='20px' height='20px' alt='edit' title='Buscar' /></a>");
                out.print("<br/>");
                out.print("<br/>");
                if (solucion == 1) {
                    out.print("<div style='display:block;margin-top:0px' id=\"toggle3\">");
                    out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                    out.print("<h3>Solucionar Pendiente</h3>");
                    out.print("<form action='Maquina?opc=7' method='post' name='formS' onsubmit='registroP();'>");
                    out.print("<input type='hidden' name='idP' value='" + id_pendiente + "'>");
                    out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
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
                    out.print("<form action='Maquina?opc=8' method='post' name='formSM' onsubmit='registroP();'>");
                    out.print("<input type='hidden' name='idP' value='" + id_pendiente + "'>");
                    out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                    out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                    out.print("<b>Fecha:</b><br />");
                    out.print("<input type='text' name='txt_fechaM' value='" + obj_pendiente[7] + "' id=\"datepicker\" placeholder='fecha'>");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('datepicker');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script><br />");
                    out.print("<b>Descripcion:</b><br />");
                    String sol = obj_pendiente[8].toString().replace("<div>", "<div contenteditable='true'>");
                    out.print("<textarea id='editor' name='txt_solucionM' style='width: 500px; height: 400' placeholder='descripcion'>" + sol + "</textarea>");
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
                    out.print("<div style='display:block;margin-top:0px' id=\"toggle3\">");
                    out.print("<div id='sidebar' style='border: 1px solid #CAA427;width:600px;padding: 20px 0 20px 10px;'>");
                    out.print("<h3>Modificar Pendiente</h3>");
                    out.print("<form action='Maquina?opc=6' method='post' name='formPM' onsubmit='registroP();'>");
                    out.print("<input type='hidden' name='idP' value='" + id_pendiente + "'>");
                    out.print("<input type='hidden' name='idM' value='" + id_maquina + "'>");
                    out.print("<input type='hidden' name='txt_bus' value='" + filtro + "'>");
                    out.print("<b>Fecha:</b><br />");
                    out.print("<input type='text' name='txt_fechaM' value='" + obj_pendiente[3] + "' id=\"datepicker\" placeholder='fecha'>");
                    out.print("<script type='text/javascript'>");
                    out.print("var validation = new LiveValidation('datepicker');");
                    out.print("validation.add( Validate.Presence );");
                    out.print("</script><br />");
                    out.print("<b>Descripcion:</b><br />");
                    String des = obj_pendiente[4].toString().replace("<div>", "<div contenteditable='true'>");
                    out.print("<textarea id='editor' name='txt_pendienteM' style='width: 500px; height: 400px' placeholder='descripcion'>" + des + "</textarea>");
                    out.print("<input type='submit' value='Guardar' onclick='PendienteM()' id='botonP'/>");
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
            if (lst_pendientes != null) {
                //<editor-fold defaultstate="collapsed" desc="pendientes">
                out.print("<table class='table' style='width:100%' id='resultados1'>");
                out.print("<tr>");
                out.print("<th style='width:10%'>Fecha</th>");
                out.print("<th style='width:35%'>Pendiente</th>");
                out.print("<th style='width:35%'>Solucion</th>");
                out.print("<th style='width:10%'>Modificar</th>");
                out.print("<th style='width:10%'>Mail</th>");
                out.print("</tr>");
                for (int i = 0; i < lst_pendientes.size(); i++) {
                    Object[] obj_pendientes = (Object[]) lst_pendientes.get(i);
                    out.print("<tr>");
                    out.print("<td align='center'>" + obj_pendientes[3] + "</td>");
                    out.print("<td valign='top'>" + obj_pendientes[4] + "</td>");
                    if (Integer.parseInt(obj_pendientes[6].toString()) == 0) {
                        out.print("<td align='center'>Pendiente enviar corrreo para solucionar</td>");
                        out.print("<td align='center'><a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + obj_pendientes[0] + "&idS=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                        out.print("<td align='center'><a href='#' onclick='idP(" + obj_pendientes[0] + "," + 1 + ")'><img id='Mail_pendiente' src='Interfaz/Contenido/Iconos/Mail.png' width='22px' height='15px'></a>");
                        out.print("</td>");
                    } else if (Integer.parseInt(obj_pendientes[6].toString()) == 1) {
                        if (obj_pendientes[8] != null) {
                            out.print("<td Valign='top'><b>Fecha: </b>" + obj_pendientes[7] + "<br />");
                            out.print("" + obj_pendientes[8] + "</td>");
                            out.print("<td align='center'><a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + obj_pendientes[0] + "&idS=" + 2 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                            out.print("<td align='center'><a href='#' onclick='idP(" + obj_pendientes[0] + "," + 2 + ")'><img src='Interfaz/Contenido/Iconos/chulo1.png' width='15' height='15'>&nbsp;&nbsp;");
                            out.print("<img id='Mail_pendiente' src='Interfaz/Contenido/Iconos/Mail.png' width='22px' height='15px'></a></td>");
                        } else {
                            out.print("<td align='center'><a href='Movimiento?opc=1&idM=" + id_maquina + "&idMV=" + 0 + "&idP=" + obj_pendientes[0] + "&idS=" + 1 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Plus.png' width='22' height='22'></a></td>");
                            out.print("<td align='center'><img src='Interfaz/Contenido/Iconos/Warning.png' width='22' height='22'></td>");
                            out.print("<td align='center'><img src='Interfaz/Contenido/Iconos/chulo1.png' width='15' height='15'>&nbsp;&nbsp;");
                            out.print("<img id='Mail_pendiente' src='Interfaz/Contenido/Iconos/Mail.png' width='22px' height='15px'></td>");
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
                //</editor-fold>
            } else {
                out.print("<h3><b style='color:#292929'>No se han encontrado resultados</b></h3>");
            }
            out.print("</div>");
            out.print("</div>");
            out.print("<script>");
            out.print("$(Menu_mail).click(function() {");
            out.print("$(\"#toggleM\").toggle(\"slide\");");
            out.print("});");
            out.print("</script>");
            out.print("<script>");
            out.print("$(Mail_pendiente).click(function() {");
            out.print("$(\"#toggleP\").toggle(\"slide\");");
            out.print("});");
            out.print("</script>");
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
