package Tags;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_resultado extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        try {
            // <editor-fold defaultstate="collapsed" desc="inicio session">
            if (pageContext.getRequest().getAttribute("expirar_session") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("expirar_session").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"su session ha expirado\","
                            + "text:\"Ha sobrepasado el tiempo de actividad en su session\","
                            + "type:\"warning\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("estadoInactivo") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("estadoInactivo").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"El usuario se encuentra inactivo\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("ingreso_sistema") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("ingreso_sistema").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"Los datos de ingreso son incorrectos, favor verificar\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("resultado_contraseña") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("resultado_contraseña").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"Se ha realizado el cambio de la contraseña\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("resultado_contraseñaR") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("resultado_contraseñaR").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"Se ha realizado el cambio de la contraseña al año en curso\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("cambio_contraseña") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("cambio_contraseña").toString());
                int id_usuario = Integer.parseInt(pageContext.getRequest().getAttribute("id_usa").toString());
                if (resultado) {
                    out.print("<div class='sweet-local' id='Control_pet' style='opacity: 1.03; display: flex; margin:auto;align-items: center;'>");
                    out.print("<fieldset class='popup_local' style='margin-left:25%;width:45%;'>");
//                    out.print("<a href='Sesion?opc=2' style='float:right'><img src='Interfaz/Contenido/Iconos/Delete.png' alt='edit' style='width:22px;height:22px;' title='Cerra modulo de registro' /></a>");
                    out.print("<center><b>Cambiar Contraseña</b></center>");
                    out.print("<p style='color:#03899C'>Recordar que la protección de datos, usuario y contraseña, ayuda a evitar fraudes o alteraciones en la Organización (Platitec S.A) y en este Aplicativo.</p>");
                    out.print("<form action='Login?opc=2' method='post'>");
                    out.print("<center>");
                    out.print("<input type='hidden' id='usuario'  name='id_usuario' value='" + id_usuario + "' />");
                    out.print("<input type='password' id='pass-input'  placeholder='Nueva Contraseña' style='border-bottom: solid 1px gray; border-left: none;border-right: none;border-top: none;position:relative;top:2px'>&nbsp;&nbsp;&nbsp;");
                    out.print("<script>");
                    out.print("var validatedObj = new LiveValidation('pass-input');");
                    out.print("validatedObj.add(Validate.Presence);");
                    out.print("validatedObj.add(Validate.Password);");
//                    out.print("validatedObj.add(Validate.Password_1);");
                    out.print("</script>");
                    out.print("<input type='password' id='confpass-input' name='txt_passw' placeholder='Confirmar Contraseña' style='border-bottom: solid 1px gray; border-left: none;border-right: none;border-top: none;position:relative;top:2px' >");
                    out.print("<script>");
                    out.print("var validatedObj = new LiveValidation('confpass-input');");
                    out.print("validatedObj.add(Validate.Password);");
//                    out.print("validatedObj.add(Validate.Password_1);");
                    out.print("validatedObj.add(Validate.Confirmation, { match: 'pass-input' });");
                    out.print("</script>");
//                    out.print("<input type='hidden' value='" + menu + "' name='id_usuario'>");
                    out.print("</center>");
                    out.print("<div style='float:right;'><img src='Interfaz/Contenido/images/spy.gif' alt='Logo' width='200' height='150' style='margin-right: 40px;' /></div>");
                    out.print("<div class='Ayuda'>");
                    out.print("<div class='label_info'><label style='color:#008063'>El cambio de Contraseña debe contener:<br />"
                            + "-Minimo 8 caracteres<br/>\n"
                            + "-Maximo 15 caracteres<br/>\n"
                            + "-Al menos una letra mayúscula<br/>\n"
                            + "-Al menos una letra minúscula<br/>\n"
                            + "-Al menos un dígito ( Numero )<br/>\n"
                            + "-No espacios en blanco<br/>\n"
                            + "-Al menos 1 caracter especial ( $@$!%*?&#- )</label></div>");
                    out.print("</div>");
                    out.print("<center>");
                    out.print("<br><input type='submit' value='Cambiar'>");
                    out.print("</center>");
                    out.print("</form>");
                    out.print("</fieldset>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal('Error','Los datos ingresados son incorrectos.','error');");
                    out.print("</script>");
                }
            }
            // </editor-fold>
            // <editor-fold defaultstate="collapsed" desc="alertas modulo usuarios">
            if (pageContext.getRequest().getAttribute("Registro_usuario") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Registro_usuario").toString());
                boolean pass = Boolean.valueOf(pageContext.getRequest().getAttribute("Pass").toString());
                if (pass) {
                    if (resultado) {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha registrado el usuario\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    } else {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Error\","
                                + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                                + "type:\"error\""
                                + "});");
                        out.print("</script>");
                    }
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"las contraseñas no coinciden. no se ha realizado el registro\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Modificar_usuario") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Modificar_usuario").toString());
                boolean pass = Boolean.valueOf(pageContext.getRequest().getAttribute("Pass").toString());
                if (pass) {
                    if (resultado) {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha modificado el usuario\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    } else {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Error\","
                                + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                                + "type:\"error\""
                                + "});");
                        out.print("</script>");
                    }
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"las contraseñas no coinciden. no se ha realizado la modificacion\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Estado_usuario") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Estado_usuario").toString());
                int estado = Integer.parseInt(pageContext.getRequest().getAttribute("estado").toString());
                if (resultado) {
                    if (estado == 0) {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha des-activado el usuario\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    } else {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha activado el usuario\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    }
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
// </editor-fold>
            //<editor-fold defaultstate="collapsed" desc="ALERTAS FICHA TECNICA">
            if (pageContext.getRequest().getAttribute("Registro_FichaT") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Registro_FichaT").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"Se ha registrado la ficha Tecnica.\","
                            + "type:\"success\","
                            + "});");
                    out.print("</script>");
                } else if (pageContext.getRequest().getAttribute("Conf_herramental") == "") {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"No se ha seleccionado ningun molde.\","
                            + "type: \"error\","
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Editar_ficha") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Editar_ficha").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"Se ha modificado la ficha tecnica.\","
                            + "type: \"success\","
                            + "});");
                    out.print("</script>");
                } else if (pageContext.getRequest().getAttribute("Conf_herramental") == "") {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"No se ha seleccionado ningun molde.\","
                            + "type: \"error\","
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Estado_Inactivo") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Estado_Inactivo").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Advertencia\","
                            + "text:\"Esta ficha contiene maquinas y moldes asociados.<br>"
                            + " ¿Seguro desea inactivar?."
                            + "<form action='Ficha_tecnica?opc=8' method='post'><button style='background: red;' type='submit'>Cancelar</button></form>\","
                            + "type: \"warning\","
                            + "confirmButtonText: \"Si, inactivar!\","
                            + "html: true"
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"Ha ocurrido un problema.\","
                            + "type: \"error\","
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("confimr_result") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("confimr_result").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"Se ha realizado correctamente el cambio.\","
                            + "type: \"success\","
                            + "});");
                    out.print("</script>");
                } else {

                }
            }
            //<editor-fold defaultstate="collapsed" desc="ALERTAS PENDIENTES">
            if (pageContext.getRequest().getAttribute("Registrar_Pndt") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Registrar_Pndt").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"Se ha registrado un pendiente.\","
                            + "type: \"success\","
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Atencion!\","
                            + "text:\"No ha completado los campos.\","
                            + "type: \"warnning\","
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Editar_Pndt") != null) {
                boolean result = Boolean.valueOf(pageContext.getRequest().getAttribute("Editar_Pndt").toString());
                if (result) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"Se ha modificado un pendiente.\","
                            + "type: \"success\","
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Atencion!\","
                            + "text:\"No ha diligenciado una fecha.\","
                            + "type: \"Warnning\","
                            + "});");
                    out.print("</script>");
                }
            }
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="ALERTAS CORREO">
            if (pageContext.getRequest().getAttribute("Mail_pendiente_Ficha") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Mail_pendiente_Ficha").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"Se ha enviado el pendiente a los usuarios seleccionados.\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"Ocurrio un error en el envio del correo por favor comunicarse con el administrador.\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            //</editor-fold>
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="alertas maquina">
            if (pageContext.getRequest().getAttribute("Registro_maquina") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Registro_maquina").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha registrado la maquina\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Modificar_maquina") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Modificar_maquina").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado la maquina\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("estado_maquina") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("estado_maquina").toString());
                int estado = Integer.parseInt(pageContext.getRequest().getAttribute("estado").toString());
                if (resultado) {
                    if (estado == 0) {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha des-activado la maquina\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    } else {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha activado la maquina\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    }
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="alertas herramental">
            if (pageContext.getRequest().getAttribute("registro_herramental") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("registro_herramental").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha registrado el herramental\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("modificar_herramental") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("modificar_herramental").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado el herramental\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("estado_herramental") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("estado_herramental").toString());
                int estado = Integer.parseInt(pageContext.getRequest().getAttribute("estado").toString());
                if (resultado) {
                    if (estado == 0) {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha des-activado el herramental\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    } else {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha activado el herramental \","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    }
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="alertas movimientos">
            if (pageContext.getRequest().getAttribute("registro_movimiento") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("registro_movimiento").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha registrado el movimiento\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("validar_movimiento") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("validar_movimiento").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Alerta\","
                            + "text:\"No se puede registrar un movimiento, porque se encuentra un molde Montado\","
                            + "type:\"info\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("modificar_movimiento") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("modificar_movimiento").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado el movimiento\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="alertas tipo maquina">
            if (pageContext.getRequest().getAttribute("registro_tipoM") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("registro_tipoM").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha registrado el tipo de la maquina\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Modificar_tipoM") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Modificar_tipoM").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado el tipo de la maquina\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("estado_tipoM") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("estado_tipoM").toString());
                int estado = Integer.parseInt(pageContext.getRequest().getAttribute("estado").toString());
                if (resultado) {
                    if (estado == 0) {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha des-activado el tipo de la maquina\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    } else {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha activado el tipo de la maquina\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    }
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="alertas pendientes herramental">
            if (pageContext.getRequest().getAttribute("registro_pendiente") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("registro_pendiente").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha registrado el pendiente en el herramental\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("modificar_pendiente") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("modificar_pendiente").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado el pendiente en el herramental\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("solucionar_pendiente_herramental") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("solucionar_pendiente_herramental").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha solucionado el pendiente del herramental\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="alertas pendientes maquina">
            if (pageContext.getRequest().getAttribute("registro_pendiente_maquina") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("registro_pendiente_maquina").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha registrado el pendiente en la maquina\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("modificar_pendiente_maquina") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("modificar_pendiente_maquina").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado el pendiente en la maquina\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("modificar_pendiente_maquina") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("modificar_pendiente_maquina").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado el pendiente en la maquina\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("solucionar_pendiente_maquina") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("solucionar_pendiente_maquina").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha solucionado el pendiente en la maquina\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("solucionar_pendienteM_maquina") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("solucionar_pendienteM_maquina").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado la solucion del pendiente en la maquina\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="alertas Mail">
            if (pageContext.getRequest().getAttribute("Email_movimiento_her") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Email_movimiento_her").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha enviado el movimiento a los usuarios selecionados\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el envio del correo por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Email_movimiento_maq") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Email_movimiento_maq").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha enviado el movimiento a los usuarios selecionados\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el envio del correo por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Mail_pendiente_her") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Mail_pendiente_her").toString());
                int estado = Integer.parseInt(pageContext.getRequest().getAttribute("estado").toString());
                if (resultado) {
                    if (estado != 1) {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha enviado la solucion del pendiente a los usuarios selecionados\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    } else {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha enviado el pendiente a los usuarios selecionados\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    }
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el envio del correo por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Mail_pendiente_maq") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Mail_pendiente_maq").toString());
                int estado = Integer.parseInt(pageContext.getRequest().getAttribute("estado").toString());
                if (resultado) {
                    if (estado != 1) {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha enviado la solucion del pendiente a los usuarios selecionados\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    } else {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha enviado el pendiente a los usuarios selecionados\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    }
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el envio del correo por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="registro programacion">
            if (pageContext.getRequest().getAttribute("Registro_programacion") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Registro_programacion").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha registrado la programacion\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Modificar_programacion") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Modificar_programacion").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado la programacion\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="alertas clasificacion defectos">
            if (pageContext.getRequest().getAttribute("Registro_ClasificacionD") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Registro_ClasificacionD").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha registrado la clasificacion\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Modificar_ClasificacionD") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Modificar_ClasificacionD").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado la clasificacion\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }

            if (pageContext.getRequest().getAttribute("Estado_ClasificacionD") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Estado_ClasificacionD").toString());
                int estado = Integer.parseInt(pageContext.getRequest().getAttribute("Estado").toString());
                if (resultado) {
                    if (estado == 0) {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha des-activado la clasificacion\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    } else {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha activado la clasificacion\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    }
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="alertas familia producto">
            if (pageContext.getRequest().getAttribute("Registro_familiaP") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Registro_familiaP").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha registrado la familia de producto\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("Modificar_familiaP") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Modificar_familiaP").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado la familia de producto\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }

            if (pageContext.getRequest().getAttribute("Estado_familiaP") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("Estado_familiaP").toString());
                int estado = Integer.parseInt(pageContext.getRequest().getAttribute("estado").toString());
                if (resultado) {
                    if (estado == 0) {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha des-activado la familia de producto\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    } else {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha activado la familia de producto\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    }
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="alertas defectos">
            if (pageContext.getRequest().getAttribute("registro_defecto") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("registro_defecto").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha registrado el defecto\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("modificar_defecto") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("modificar_defecto").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado el defecto\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("estado_defecto") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("estado_defecto").toString());
                int estado = Integer.parseInt(pageContext.getRequest().getAttribute("estado").toString());
                if (resultado) {
                    if (estado == 0) {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha des-activado el defecto\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    } else {
                        out.print("<script type='text/javascript'>");
                        out.print("swal({"
                                + "title:\"Correcto\","
                                + "text:\"se ha activado el defecto\","
                                + "type:\"success\""
                                + "});");
                        out.print("</script>");
                    }
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="alertas informes">
            if (pageContext.getRequest().getAttribute("registro_informe") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("registro_informe").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha registrado el informe\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("modificar_informe") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("modificar_informe").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado el informe\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="alertas novedad">
            if (pageContext.getRequest().getAttribute("registro_novedad") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("registro_novedad").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha registrado la novedad\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
            if (pageContext.getRequest().getAttribute("modificar_novedad") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("modificar_novedad").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha modificado la novedad\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }
//</editor-fold>
            if (pageContext.getRequest().getAttribute("registro_nota") != null) {
                boolean resultado = Boolean.valueOf(pageContext.getRequest().getAttribute("registro_nota").toString());
                if (resultado) {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Correcto\","
                            + "text:\"se ha registrado la nota\","
                            + "type:\"success\""
                            + "});");
                    out.print("</script>");
                } else {
                    out.print("<script type='text/javascript'>");
                    out.print("swal({"
                            + "title:\"Error\","
                            + "text:\"ocurrio un error en el registro por favor comunicarse con el administrador\","
                            + "type:\"error\""
                            + "});");
                    out.print("</script>");
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Tag_resultado.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();

    }

}
