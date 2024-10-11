package Tags;

import controladores.RolJpaController;
import controladores.UsuarioJpaController;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_usuario extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        RolJpaController jpa_rol = new RolJpaController();
        UsuarioJpaController jpa_usuario = new UsuarioJpaController();
        List rol = jpa_rol.ConsultaRoles();
        Date fechaA = new Date();
        List lst_areas = jpa_usuario.ConsultaAreas();
        List lst_usuarios = (List) pageContext.getRequest().getAttribute("consulta_usuarios");
        String filtro = (String) pageContext.getRequest().getAttribute("filtro");
        int id_usuario = Integer.parseInt(pageContext.getRequest().getAttribute("id_usuario").toString());
        try {
            out.print("<div id='sidebar'>");
            if (id_usuario == 0) {
                // <editor-fold defaultstate="collapsed" desc="registrar usuario">
                out.print("<h3>Nuevo usuario</h3>");
                out.print("<form action='Usuario?opc=2'  method='post' onsubmit='registroU();' name='formU'>");
                out.print("<b>Nombre</b><br />");
                out.print("<input type='text' name='txt_nombre' id ='nombre-id' placeholder='Nombre' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('nombre-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>Apellido</b><br />");
                out.print("<input type='text' name='txt_apellido' id ='apellido-id' placeholder='Apellido' onchange='javascript:this.value=this.value.toUpperCase();' <br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('apellido-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>Documento</b><br />");
                out.print("<input type='text' name='txt_doc' id ='doc-id' placeholder='Documento' onchange='javascript:this.value=this.value.toUpperCase();' <br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('doc-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>Codigo</b><br />");
                out.print("<input type='text' name='txt_cod' id ='cod-id' placeholder='Documento' onchange='javascript:this.value=this.value.toUpperCase();' <br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('cod-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>rol</b><br />");
                out.print("<select name='lsrol' id='select-id'>");
                out.print("<option value='0' style='display:none;'>Seleccione rol</option>");
                for (int i = 0; i < rol.size(); i++) {
                    Object[] obj_rol = (Object[]) rol.get(i);
                    out.print("<option value='" + obj_rol[0] + "'>" + obj_rol[1] + "</option>");
                }
                out.print("</select><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('select-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>Area</b><br />");
                out.print("<select name='lsarea' id='selecta-id'>");
                out.print("<option value='0' style='display:none;'>Seleccione area</option>");
                for (int i = 0; i < lst_areas.size(); i++) {
                    Object[] obj_areas = (Object[]) lst_areas.get(i);
                    out.print("<option value='" + obj_areas[0] + "'>" + obj_areas[1] + " / " + obj_areas[2] + "</option>");
                }
                out.print("</select><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('selecta-id'');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>Cargo</b><br />");
                out.print("<select name='lstcargo' id='selectC-id'>");
                out.print("<option value='0' style='display:none;'>Seleccione Cargo</option>");
                out.print("<option>ADMINISTRADOR</option>");
                out.print("<option>DIRECTORA CALIDAD</option>");
                out.print("<option>DIRECTOR PRODUCCION</option>");
                out.print("<option>DIRECTOR TECNICO</option>");
                out.print("<option>DIRECTOR COMERCIAL</option>");
                out.print("<option>JEFE PRODUCCION INSUMOS</option>");
                out.print("<option>JEFE PROYECTOS</option>");
                out.print("<option>COORDINADOR INYECCION</option>");
                out.print("<option>COORDINADOR EXTRUSION</option>");
                out.print("<option>COORDINADOR PELETIZADO</option>");
                out.print("<option>COORDINADOR CALIDAD</option>");
                out.print("<option>INSPECTORA CALIDAD EXTRUSION</option>");
                out.print("<option>INSPECTORA CALIDAD INYECCION</option>");
                out.print("<option>COORDINADOR SGC</option>");
                out.print("<option>COORDINADOR PROYECTOS</option>");
                out.print("<option>AJUSTADOR INYECCION</option>");
                out.print("<option>ASISTENTES PRODUCCION INSUMOS</option>");
                out.print("</select><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('selectC-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>User</b><br />");
                out.print("<input type='text' name='txt_user' id='user-id' placeholder='User' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('user-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>Correo</b><br />");
                out.print("<textarea cols='30' rows='4' name='txt_correo' id='correo-id' size='34' placeholder='Example@hotmail.com' onchange='javascript:this.value=this.value.toUpperCase();'></textarea><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('correo-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<input type='hidden' name='txt_contra' id='pass-id value='" + (fechaA.getYear() + 1900) + "'>");
                out.print("<input type='submit' id='btsubmit' value='Guardar'>");
                out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "        </div>");
                out.print("</form>");
// </editor-fold>
            } else {
                // <editor-fold defaultstate="collapsed" desc="modificar usuario">
                List lst_usuario = jpa_usuario.ConsultaUsuarioId(id_usuario);
                Object[] obj_usuario = (Object[]) lst_usuario.get(0);
                out.print("<h3>Modificar usuario &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href='Usuario?opc=1&idU=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Delete.png' alt='Logo' width='20' height='20' title='Cancelar' /></a></h3>");
                out.print("<form action='Usuario?opc=3&txt_bus=" + filtro + "&idU=" + obj_usuario[0] + "' onsubmit='registroU();' method='post'>");
                out.print("<b>Nombre</b><br />");
                out.print("<input type='text' name='txt_nombreM' id ='nombre-id' placeholder='Nombre' value='" + obj_usuario[1] + "' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('nombre-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>Apellido</b><br />");
                out.print("<input type='text' name='txt_apellidoM' id ='apellido-id' placeholder='Apellido' value='" + obj_usuario[2] + "' onchange='javascript:this.value=this.value.toUpperCase();' <br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('apellido-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>Documento</b><br />");
                out.print("<input type='number' name='txt_docM' id ='doc-id' placeholder='Documento' value='" + obj_usuario[3] + "' onchange='javascript:this.value=this.value.toUpperCase();' <br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('doc-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>Codigo</b><br />");
                out.print("<input type='number' name='txt_codM' id ='cod-id' placeholder='Documento' value='" + obj_usuario[4] + "' onchange='javascript:this.value=this.value.toUpperCase();' <br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('cod-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>rol</b><br />");
                out.print("<select name='lsrolM' id='select-id'>");
                out.print("<option value='" + obj_usuario[8] + "' style='display:none;'>" + obj_usuario[9] + "</option>");
                for (int i = 0; i < rol.size(); i++) {
                    Object[] obj_rol = (Object[]) rol.get(i);
                    out.print("<option value='" + obj_rol[0] + "'>" + obj_rol[1] + "</option>");
                }
                out.print("</select><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('select-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>Area</b><br />");
                out.print("<select name='lsareaM' id='selecta-id'>");
                out.print("<option value='" + obj_usuario[11] + "' style='display:none;'>" + obj_usuario[12] + "</option>");
                for (int i = 0; i < lst_areas.size(); i++) {
                    Object[] obj_areas = (Object[]) lst_areas.get(i);
                    out.print("<option value='" + obj_areas[0] + "'>" + obj_areas[1] + " / " + obj_areas[2] + "</option>");
                }
                out.print("</select><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('selecta-id'');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>Cargo</b><br />");
                out.print("<select name='lstcargoM' id='selectC-id'>");
                out.print("<option value='" + obj_usuario[13] + "' style='display:none;'>" + obj_usuario[13] + "</option>");
                out.print("<option>ADMINISTRADOR</option>");
                out.print("<option>DIRECTORA CALIDAD</option>");
                out.print("<option>DIRECTOR PRODUCCION</option>");
                out.print("<option>DIRECTOR TECNICO</option>");
                out.print("<option>DIRECTOR COMERCIAL</option>");
                out.print("<option>JEFE PRODUCCION INSUMOS</option>");
                out.print("<option>JEFE PROYECTOS</option>");
                out.print("<option>COORDINADOR INYECCION</option>");
                out.print("<option>COORDINADOR EXTRUSION</option>");
                out.print("<option>COORDINADOR PELETIZADO</option>");
                out.print("<option>COORDINADOR CALIDAD</option>");
                out.print("<option>INSPECTORA CALIDAD EXTRUSION</option>");
                out.print("<option>INSPECTORA CALIDAD INYECCION</option>");
                out.print("<option>COORDINADOR SGC</option>");
                out.print("<option>COORDINADOR PROYECTOS</option>");
                out.print("<option>AJUSTADOR INYECCION</option>");
                out.print("<option>ASISTENTES PRODUCCION INSUMOS</option>");
                out.print("</select><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('selectC-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>User</b><br />");
                out.print("<input type='text' name='txt_userM' id='user-id' placeholder='User' value='" + obj_usuario[5] + "' onchange='javascript:this.value=this.value.toUpperCase();'><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('user-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<b>Correo</b><br />");
                out.print("<textarea cols='30' rows='4' name='txt_correoM' id='correo-id' size='34' placeholder='Example@hotmail.com' onchange='javascript:this.value=this.value.toUpperCase();'>" + obj_usuario[7] + "</textarea><br />");
                out.print("<script type='text/javascript'>");
                out.print("var validation = new LiveValidation('correo-id');");
                out.print("validation.add( Validate.Presence );");
                out.print("</script>");
                out.print("<input type='hidden' name='txt_contraM' id='pass-id' value='" + obj_usuario[6] + "'>");
                out.print("<input type='submit' id='btsubmit' value='Modificar'>");
                out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "          <div></div>\n"
                        + "        </div>");
                out.print("</form>");
                out.print("<br /><center><a href='#' onclick='contrasenaM()'><b class='naranja'>Restablecer contraseña</b></a></center>");
// </editor-fold>
            }
            out.print("<div class='cleaner'></div></div>");
            out.print("<div id='content'>");
            // <editor-fold defaultstate="collapsed" desc="consulta usuario">
            out.print("<div style='float:right;'>");
            out.print("<form method='post' action='Usuario?opc=1&idU=" + 0 + "'>");
            out.print("<input name='txt_bus' type='text' class='input_field' placeholder='Buscar'><br/>");
            out.print("</form>");
            out.print("</div>");
            if (!filtro.equals("")) {
                out.print("<a href='Usuario?opc=1&idU=" + 0 + "&txt_bus='><img src='Interfaz/Contenido/Iconos/Volver.png' alt='Logo' width='25' height='25.5' /></a>");
            }
            out.print("<h3>Usuarios</h3>");
            if (!lst_usuarios.isEmpty()) {
                out.print("<div id='NavPosicion'></div>");
                out.print("<table class='table' id='resultados' style='width:100%;'>");
                out.print("<tr>");
                out.print("<th>Nombre</th>");
                out.print("<th>Documento</th>");
                out.print("<th>Usuario</th>");
                out.print("<th>Rol</th>");
                out.print("<th>Area</th>");
                out.print("<th>Cargo</th>");
                out.print("<th>Correo</th>");
                out.print("<th>Modificar</th>");
                out.print("<th>Estado</th>");
                out.print("</tr>");
                for (int i = 0; i < lst_usuarios.size(); i++) {
                    Object[] obj_usuarios = (Object[]) lst_usuarios.get(i);
                    out.print("<tr>");
                    out.print("<td>" + obj_usuarios[1] + "</td>");
                    out.print("<td align='center'>" + obj_usuarios[2] + "</td>");
                    out.print("<td align='center'>" + obj_usuarios[4] + "</td>");
                    out.print("<td align='center'>" + obj_usuarios[7] + "</td>");
                    out.print("<td align='center'>" + obj_usuarios[10] + "</td>");
                    out.print("<td align='center'>" + obj_usuarios[11] + "</td>");
                    out.print("<td align='center'>" + obj_usuarios[6] + "</td>");
                    out.print("<td align='center'><a href='Usuario?opc=1&idU=" + obj_usuarios[0] + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Edit.png' width='22' height='22'></a></td>");
                    if ((Integer) obj_usuarios[8] == 1) {
                        out.print("<td align='center'><a href='Usuario?opc=4&idU=" + obj_usuarios[0] + "&est=" + 0 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Check.png' width='22' height='22'></a></td>");
                    } else {
                        out.print("<td align='center'><a href='Usuario?opc=4&idU=" + obj_usuarios[0] + "&est=" + 1 + "&txt_bus=" + filtro + "'><img src='Interfaz/Contenido/Iconos/Delete.png' width='22' height='22'></a></td>");
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
// </editor-fold>
            out.print("<div class='cleaner'></div></div>");
        } catch (IOException ex) {
            Logger.getLogger(Tag_usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
