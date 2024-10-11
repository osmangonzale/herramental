/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tags;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;

import controladores.FichaTecnicaJpaController;
import controladores.HerramentalJpaController;
import controladores.MaquinaJpaController;
import controladores.UsuarioJpaController;

/**
 *
 * @author tecnicos.sistemas2
 */
public class Tag_ficha_tecnica extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
//        HttpSession sesion = pageContext.getSession();
//        String area = sesion.getAttribute("Area").toString();
        FichaTecnicaJpaController fichaTecnicaJpa = new FichaTecnicaJpaController();
        HerramentalJpaController HerramentalJpa = new HerramentalJpaController();
        MaquinaJpaController MaquinaJpa = new MaquinaJpaController();
        UsuarioJpaController UsuarioJpa = new UsuarioJpaController();
        List lst_fichaTec = null;
        List lst_Herramental = null;
        List lst_maquina = null;
        List lst_usuario = null;

        int id_fichat = 0, id_fichaLog = 0;
        int id_est = 0;
        int id_herra = 0;
        int con_usuario = 0;
        int code = 0;
        int cc = 0;
        int validar_est = 0, firma_insu = 0, firma_cali = 0, firma_pro = 0;
        int id_Ficha_firma = 0, est_estado = 0, fil_estado = 0;
        String mensaje = "", filtro = "", usa_firmas = "";
        boolean conf_result;

        //<editor-fold defaultstate="collapsed" desc="CAPTURAR DATOS">
        try {
            id_fichat = Integer.parseInt(pageContext.getRequest().getAttribute("Consultar_Herramental_id").toString());
        } catch (Exception e) {
            id_fichat = 0;
        }

        try {
            id_est = Integer.parseInt(pageContext.getRequest().getAttribute("Consultar_Estado_Ficha").toString());
        } catch (Exception e) {
            id_est = 0;
        }
        try {
            id_herra = Integer.parseInt(pageContext.getRequest().getAttribute("Consultar_Id_Herramentla").toString());
        } catch (Exception e) {
            id_herra = 0;
        }

        try {
            code = Integer.parseInt(pageContext.getRequest().getAttribute("Validar_code").toString());
        } catch (Exception e) {
            code = 0;
        }
        try {
            cc = Integer.parseInt(pageContext.getRequest().getAttribute("Validar_cc").toString());
            usa_firmas = pageContext.getRequest().getAttribute("txt_resp").toString();
        } catch (Exception e) {
            cc = 0;
            usa_firmas = "";
        }
        try {
            validar_est = Integer.parseInt(pageContext.getRequest().getAttribute("Validar_est").toString());
        } catch (Exception e) {
            validar_est = 0;
        }

        try {
            id_Ficha_firma = Integer.parseInt(pageContext.getRequest().getAttribute("Consultar_IdFicha").toString());
        } catch (Exception e) {
            id_Ficha_firma = 0;
        }

        try {
            mensaje = pageContext.getRequest().getAttribute("Enviar_mensaje").toString();
        } catch (Exception e) {
            mensaje = "";
        }
        try {
            filtro = pageContext.getRequest().getAttribute("Filtrar_datos").toString();
        } catch (Exception e) {
            filtro = "";
        }
        try {
            est_estado = Integer.parseInt(pageContext.getRequest().getAttribute("Consultar_PorEstado").toString());
        } catch (Exception e) {
            est_estado = 0;
        }
        try {
            fil_estado = Integer.parseInt(pageContext.getRequest().getAttribute("Filtros_Estados").toString());
        } catch (Exception e) {
            fil_estado = 0;
        }

        try {
            id_fichaLog = Integer.parseInt(pageContext.getRequest().getAttribute("Consultar_historial").toString());
        } catch (Exception e) {
            id_fichaLog = 0;
        }

        //</editor-fold>
        try {
            if (pageContext.getRequest().getAttribute("Ficha_tecnica").toString().equals("Consulta_Fichas")) {
                //<editor-fold defaultstate="collapsed" desc="FICHA TECNICA">
                if (id_fichat > 0) {
                    //<editor-fold defaultstate="collapsed" desc="EDITAR FICHA TECNICA">
                    lst_fichaTec = fichaTecnicaJpa.ConsultaFichaTecnica_id(id_fichat);

                    for (int f = 0; f < lst_fichaTec.size(); f++) {
                        Object[] Obj_EditFicha = (Object[]) lst_fichaTec.get(f);

                        out.print("<div id='f_registro' class='ft_registro' style='display: block;'>");
                        out.print("<div class='' style='display: flex; justify-content: space-between; font-size: 1.2em;'>");
                        out.print("<h4> Editar Ficha Tecnica </h4>");
                        out.print("<div>");
                        out.print("<a class='btn-cls' href='Ficha_tecnica?opc=9' title='Cerrar' style='cursor: pointer;' onclick='EsconderRR()'><i class=\"fas fa-window-close\"></i></a>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("<form action='Ficha_tecnica?opc=4' method='post'>");
                        out.print("<input type='hidden' name='id_fichat' value='" + Obj_EditFicha[0] + "'>");
                        out.print("<input type='text' class='form-control' value='" + Obj_EditFicha[1] + "' name='Txt_RefTec' required><br>");
                        out.print("<input type='text' class='form-control' value='" + Obj_EditFicha[8] + "' name='Txt_version' required><br>");
                        out.print("<input type='text' class='form-control' value='" + Obj_EditFicha[3] + "' name='Txt_Nombre' required><br>");
                        lst_Herramental = HerramentalJpa.ConsultaHerramentales();
                        out.print("<input type='hidden' id='Txt_ids' name='Txt_ids' value='" + Obj_EditFicha[2] + "'>");

                        out.print("<p style='margin-bottom: 0px;'>Molde/s Actuales: </p><br>");
                        int contr = 0;
                        String[] mold = Obj_EditFicha[2].toString().replace("][", "-").replace("[", "").replace("]", "").split("-");
                        for (int i = 0; i < lst_Herramental.size(); i++) {
                            Object[] obj_mold = (Object[]) lst_Herramental.get(i);
                            for (int j = 0; j < mold.length; j++) {
                                int molds = Integer.parseInt(mold[j].toString());
                                int herr = Integer.parseInt(obj_mold[0].toString());
                                if (molds == herr) {
//                                    out.print("<input type='button' onclick='Masivo(this.value)' value=" + obj_mold[0] + " ><span class='fas fa-times' name='txt_molde'>" + obj_mold[4] + " </span> | ");
                                    out.print("<input type='checkbox' checked name='Txt_ids' class='btnMoldes' id='btnMoldes' value='" + obj_mold[0] + "' onclick='Masivorr(this.value)'>" + obj_mold[4] + "</button>");
                                    //                                   out.print("<tr><td colspan='2'>"
//                                            + "<br><input type='text' name='Txt_filtro_avanzado' id='Txt_filtro_avanzado' autocomplete='off' onkeypress='FiltroAvanzado(event);' placeholder='Buscar'/>"
//                                            + "<br /><b>Valores a filtrar</b><div id='Buscar_valores'></div>"
                                    //                                           + "<input type='text' name='fto'  id='Txt_valores_filtro' oninput=\"javascript:this.value+=document.getElementById('Buscar_valores').innerHTML\"/></td></tr>");
                                    contr++;
                                }
                                if (contr > 3) {
                                    out.print("<br>");
                                    contr = 0;
                                }
                            }
                        }

                        out.print("<p>Seleccionar molde/s:</p>");
                        out.print("<div class='scrollbar'>");
                        int cont = 0;
                        for (int i = 0; i < lst_Herramental.size(); i++) {
                            Object[] Obj_herrtal = (Object[]) lst_Herramental.get(i);

                            String comparar = Obj_EditFicha[2].toString();
                            String comp = Obj_herrtal[0].toString();
                            out.print("<input type='checkbox' onclick='Masivorr(this.value)' class='' name='id_herramental' value='" + Obj_herrtal[0] + "' " + ((comparar.contains("[" + comp + "]")) ? "disabled" : "") + ">"
                                    //                                El espacio en la condicion ternaria es por si se define con los encargados que el molde se vea inactivo o si queda deshabilitado
                                    + "<span style='" + (((Integer) Obj_herrtal[5] == 0) ? "<!-- color: red; -->" : "") + ((comparar.contains("[" + comp + "]")) ? "color:#c1c1c1;" : "") + "' >" + Obj_herrtal[4] + "</span>");
                            cont++;
                            if (cont > 2) {
                                out.print("<br>");
                                cont = 0;
                            }
                        }
//                        int cont = 0;
//                        String[] maquinas = Obj_EditFicha[2].toString().replace("][", "-").replace("[", "").replace("]", "").split("-");
//                        for (int i = 0; i < lst_Herramental.size(); i++) {
//                            Object[] Obj_herrtal = (Object[]) lst_Herramental.get(i);
//
//                            int maq = 0;
//                            int herr = Integer.parseInt(Obj_herrtal[0].toString());
//                            for (int j = 0; j < maquinas.length; j++) {
//                                maq = Integer.parseInt(maquinas[j].toString());
//                                if (maq == herr) {
//                                    out.print("<input type='checkbox' checked onclick='Masivo(this.value)' class='' name='id_herramental' value='" + Obj_herrtal[0] + "'>" + Obj_herrtal[4] + "");
//                                } else {
//                                    out.print("<input type='checkbox' onclick='Masivo(this.value)' class='' name='id_herramental' value='" + Obj_herrtal[0] + "'>" + Obj_herrtal[4] + "");
//                                }
//                            }
//                            cont++;
//                            if (cont > 2) {
//                                out.print("<br>");
//                                cont = 0;
//                            }
//                        }
                        out.print("</div>");
                        out.print("<button type='reset' id='btsubmit' class='btnft' style='margin-bottom: 10px;'> Limpiar </button><br>");
                        out.print("<button type='submit' id='btsubmit' class='btnft'> Editar </button>");
                        out.print("</form>");
                        out.print("</div>");
                    }

                    //</editor-fold> 
                } else {
                    //<editor-fold defaultstate="collapsed" desc="REGISTRO FICHA TECNICA">
                    out.print("<div id='toggle7' class='ft_registro' style='display: none;'>");
                    out.print("<div class='' style='display: flex; justify-content: space-between; font-size: 1.2em;'>");
                    out.print("<h4> Registrar Ficha Tecnica </h4>");
                    out.print("<div>");
                    out.print("<a class='btn-cls' title='Cerrar' style='cursor: pointer;' onclick='Esconder_reg_ft()'><i class=\"fas fa-window-close\"></i></a>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("<form action='Ficha_tecnica?opc=1' method='post'>");
                    out.print("<input type='text' class='form-control' placeholder='Referencia Ficha' name='Txt_RefTec' required autocomplete='off' ><br>");
                    out.print("<input type='text' class='form-control' placeholder='Version' name='Txt_version' required autocomplete='off' ><br>");
                    out.print("<input type='text' class='form-control' placeholder='Nombre' name='Txt_Nombre' required autocomplete='off' ><br>");
                    lst_Herramental = HerramentalJpa.ConsultaHerramentales();
                    out.print("<input type='hidden' class='' placeholder='' id='Txt_ids' name='Txt_ids'>");
                    out.print("<p>Seleccionar molde/s:</p>");
                    out.print("<div class='scrollbar'>");
                    int cont = 0;
                    for (int i = 0; i < lst_Herramental.size(); i++) {
                        Object[] Obj_herrtal = (Object[]) lst_Herramental.get(i);
                        out.print("<input type='checkbox' onclick='Masivorr(this.value)' class='' name='id_herramental' value='" + Obj_herrtal[0] + "'>"
                                //                                El espacio en la condicion ternaria es por si se define con los encargados que el molde se vea inactivo o si queda deshabilitado
                                + "<span style='" + (((Integer) Obj_herrtal[5] == 0) ? "<!-- color: red; -->" : "") + "'>" + Obj_herrtal[4] + "</span>");
                        cont++;
                        if (cont > 2) {
                            out.print("<br>");
                            cont = 0;
                        }
                    }
                    out.print("</div>");
                    out.print("<button type='reset' id='btsubmit' class='btnft' style='margin-bottom: 10px;'> Limpiar </button><br>");
                    out.print("<button type='submit' id='btsubmit' class='btnft'> Registrar </button>");
                    out.print("</form>");
                    out.print("</div>");
                    //</editor-fold>
                }
                if (id_est > 0) {
                    //<editor-fold defaultstate="collapsed" desc="CAMBIO DE ESTADO">
                    lst_fichaTec = fichaTecnicaJpa.ConsultaFichaTecnica_id(id_est);
                    for (int n = 0; n < lst_fichaTec.size(); n++) {
                        Object[] Obj_fichest = (Object[]) lst_fichaTec.get(n);
                        out.print("<div id='form_estad' class='cambEstado' style='position: absolute; background: white; padding: 20px;'>");
                        out.print("<form action='Ficha_tecnica?opc=6' method='post'>");
                        out.print("<div style='display: flex; justify-content: space-between;'>");
                        out.print("<p>Cambiar estado</p>");
                        out.print("<a class='btn-cls' title='Cerrar' style='cursor: pointer;' onclick='Esconder_estado()'><i class=\"fas fa-window-close\"></i></a>");
                        out.print("</div>");
                        out.print("<input type='hidden' class='' value='" + Obj_fichest[0] + "' name='id_ficha'>");
                        out.print("<select class='form-select' name='nmb_estado' style='height: 30px; margin-bottom: 10px;'>");
                        if ((Integer) Obj_fichest[4] == 1) {
                            out.print("<option value='1'>Vigente</option>");
                        } else if ((Integer) Obj_fichest[4] == 2) {
                            out.print("<option value='2'>Proceso</option>");
                        } else if ((Integer) Obj_fichest[4] == 3) {
                            out.print("<option value='3'>Inactivo</option>");
                        } else if ((Integer) Obj_fichest[4] == 4) {
                            out.print("<option value='4'>Obsoleto</option>");
                        }
                        out.print("<option value='1'>Vigente</option>");
                        out.print("<option value='2'>Proceso</option>");
                        out.print("<option value='3'>Inactivo</option>");
                        out.print("<option value='4'>Obsoleto</option>");
                        out.print("</select><br>");
                        out.print("<button class='btnft'>Cambiar estado</button>");
                        out.print("</form>");
                        out.print("</div>");
                    }
                    //</editor-fold>
                }
                if (validar_est > 1) {
                    //<editor-fold defaultstate="collapsed" desc="CONSULTAR FIRMAS">
                    if ((cc != 0 || code != 0)) {
                        out.print("<div id='form_estad' class='cont_firmas' style='position: absolute; background: white;'>");
                        String erro = "";

                        int valid_firmas = Integer.parseInt(pageContext.getRequest().getAttribute("txt_val").toString());
                        lst_usuario = UsuarioJpa.Consultar_firma(cc, code);
                        if (lst_usuario != null) {
                            Object[] obj_usu = (Object[]) lst_usuario.get(0);

                            out.print("<div style='display: flex; justify-content: space-between;'>");
                            out.print("<h4>Confirmar cambio</h4>");
                            out.print("<a class='btn-cls' title='Cerrar' style='cursor: pointer;' onclick='Esconder_estado()'><i class=\"fas fa-window-close\"></i></a>");
                            out.print("</div>");

                            out.print("<form action='Ficha_tecnica?opc=7' method='post' style='margin-top:-23px'>");
                            out.print("<input type='hidden' name='val_estado' value='" + validar_est + "'>");
                            out.print("<input type='hidden' name='id_fichat' value='" + id_Ficha_firma + "'>");
                            if (!usa_firmas.contains("" + obj_usu[2] + "")) {
                                int varg = Integer.parseInt(obj_usu[5].toString());
                                if (Integer.parseInt(obj_usu[5].toString()) == 3 && usa_firmas.contains("[1]")) {
                                    usa_firmas = usa_firmas.replace("1", "" + obj_usu[2] + "");
                                    valid_firmas++;
                                } else if (Integer.parseInt(obj_usu[5].toString()) == 6 && usa_firmas.contains("[2]")) {
                                    usa_firmas = usa_firmas.replace("2", "" + obj_usu[2] + "");
                                    valid_firmas++;
                                } else if (Integer.parseInt(obj_usu[5].toString()) == 10 && usa_firmas.contains("[3]")) {
                                    usa_firmas = usa_firmas.replace("3", "" + obj_usu[2] + "");
                                    valid_firmas++;
                                } else if (varg != 6 && varg != 10 && varg != 3) {
//                                } else if ( varg != 6 || Integer.parseInt(obj_usu[5].toString()) != 10 || Integer.parseInt(obj_usu[5].toString()) != 3) {
                                    erro = "El usuario <b style='color: black;'>" + obj_usu[2] + "</b><br> no pertenece a ninguna de las areas solicitadas";
                                } else {
                                    erro = "Ya hay un usuario agregado en esta area";
                                }
                            }
                            out.print("<input type='hidden' class='form-control' name='txt_resp' id='id_resp' value='" + usa_firmas + "'><br>");
                            out.print("<input type='hidden' class='form-control' name='' id='id_resp' value='" + valid_firmas + "'><br>");

                            out.print("<input type='number' class='form-control' placeholder='Codigo' id='id_cod' name='Txt_code' style='width: 70px; " + ((valid_firmas == 3) ? "background: #bfbdbd" : "") + "' " + ((valid_firmas == 3) ? "disabled" : "")
                                    + " min='1' max='9999' maxlength='4' oninput=\"maxLengthCheck(this)\" required>");
                            if (valid_firmas != 3) {
                                out.print("<script type='text/javascript'>");
                                out.print("var validation = new LiveValidation('id_cod');");
                                out.print("validation.add( Validate.Presence );");
                                out.print("</script>");
                            }

                            out.print("<input type='number' class='form-control' placeholder='Cedula' id='id_cc' name='nmb_cc' style='" + ((valid_firmas == 3) ? "background: #bfbdbd" : "") + "' " + ((valid_firmas == 3) ? "disabled" : "")
                                    + " min='1' max='99999999999' maxlength='11' oninput=\"maxLengthCheck(this)\" required>");

                            if (valid_firmas != 3) {
                                out.print("<script type='text/javascript'>");
                                out.print("var validation = new LiveValidation('id_cc');");
                                out.print("validation.add( Validate.Presence );");
                                out.print("</script>");
                            }
                            String[] firmas = null;
                            try {
                                firmas = usa_firmas.replace("][", "-").split("-");
                            } catch (Exception e) {
                                firmas = null;
                            }
                            out.print("<div class='campo_firmas'>");
                            if (!erro.equals("")) {
                                out.print("<p style='text-align: center; color: red;'>" + erro + "</p>");
                                out.print("<p>Firma Calidad: " + ((firmas != null) ? firmas[0].replace("[", "").replace("1", "") : "") + "</p>");
                                out.print("<p>Firma Insumos: " + ((firmas != null) ? firmas[1].replace("2", "") : "") + " </p>");
                                out.print("<p>Firma Proyectos: " + ((firmas != null) ? firmas[2].replace("]", "").replace("3", "") : "") + "</p>");
                            } else {
                                out.print("<p>Firma Calidad: " + ((firmas != null) ? firmas[0].replace("[", "").replace("1", "") : "") + "</p>");
                                out.print("<p>Firma Insumos: " + ((firmas != null) ? firmas[1].replace("2", "") : "") + " </p>");
                                out.print("<p>Firma Proyectos: " + ((firmas != null) ? firmas[2].replace("]", "").replace("3", "") : "") + "</p>");
                            }
                            out.print("</div>");

                            out.print("<center>");
                            out.print("<input type='hidden' name='txt_val' id='idVal' value='" + valid_firmas + "'>");
                            out.print("<button type='submit' class='btnft' style='width: 100px;'> " + ((valid_firmas == 3) ? "Guardar" : "Firmar") + " &nbsp<i class=\"fas fa-signature\"></i></button><br>");
                            out.print("</center>");

                            out.print("</form>");
                        } else {
                            out.print("<div style='display: flex; justify-content: space-between;'>");
                            out.print("<h4>Confirmar cambio</h4>");
                            out.print("<a class='btn-cls' title='Cerrar' style='cursor: pointer;' onclick='Esconder_estado()'><i class=\"fas fa-window-close\"></i></a>");
                            out.print("</div>");
                            out.print("<form action='Ficha_tecnica?opc=7' method='post'>");
                            out.print("<input type='hidden' name='val_estado' value='" + validar_est + "'>");
                            out.print("<input type='hidden' name='id_fichat' value='" + id_Ficha_firma + "'>");
                            out.print("<input type='hidden' class='form-control' name='txt_resp' id='id_resp' value='[1][2][3]'>");
                            out.print("<input type='number' class='form-control' placeholder='Codigo' name='Txt_code' style='width: 70px;'"
                                    + "min='1' max='9999' maxlength='4' oninput=\"maxLengthCheck(this)\" required>");
                            out.print("<input type='number' class='form-control' placeholder='Cedula' name='nmb_cc' "
                                    + "min='1' max='9999999999' maxlength='10' oninput=\"maxLengthCheck(this)\" required>");
                            out.print("<h5 style='color: red'>El usuario no fue encontrado.</h4>");
                            out.print("<div class='campo_firmas'>");
                            out.print("<p>Firma Insumos:</p>");
                            out.print("<p>Firma Calidad: </p>");
                            out.print("<p>Firma Proyectos: </p>");
                            out.print("</div>");
                            out.print("<center>");
                            out.print("<input type='hidden' name='txt_val' id='idVal' value='0'>");
                            out.print("<button type='submit' class='btnft' style='width: 100px;'> Firmar &nbsp<i class=\"fas fa-signature\"></i></button><br>");
                            out.print("</center>");
                            out.print("</form>");
                        }
                        out.print("</div>");
                    } else {
                        out.print("<div id='form_estad' class='cont_firmas' style='position: absolute; background: white;'>");
                        out.print("<div style='display: flex; justify-content: space-between;'>");
                        out.print("<h4>Confirmar cambio</h4>");
                        out.print("<a class='btn-cls' title='Cerrar' style='cursor: pointer;' onclick='Esconder_estado()'><i class=\"fas fa-window-close\"></i></a>");
                        out.print("</div>");
                        out.print("<form action='Ficha_tecnica?opc=7' method='post'>");
                        out.print("<input type='hidden' name='val_estado' value='" + validar_est + "'>");
                        out.print("<input type='hidden' name='id_fichat' value='" + id_Ficha_firma + "'>");
                        out.print("<input type='hidden' class='form-control' name='txt_resp' id='id_resp' value='[1][2][3]'>");
                        out.print("<input type='number' class='form-control' placeholder='Codigo' name='Txt_code' style='width: 70px;'"
                                + "min='1' max='9999' maxlength='4' oninput=\"maxLengthCheck(this)\" required>");
                        out.print("<input type='number' class='form-control' placeholder='Cedula' name='nmb_cc' "
                                + "min='1' max='99999999999' maxlength='11' oninput=\"maxLengthCheck(this)\" required>");
                        out.print("<div class='campo_firmas'>");
                        out.print("<p>Firma Insumos:</p>");
                        out.print("<p>Firma Calidad: </p>");
                        out.print("<p>Firma Proyectos: </p>");
                        out.print("</div>");
                        out.print("<center>");
                        out.print("<input type='hidden' name='txt_val' id='idVal' value='0'>");
                        out.print("<button type='submit' class='btnft' style='width: 100px;'> Firmar &nbsp<i class=\"fas fa-signature\"></i></button><br>");
                        out.print("</center>");
                        out.print("</form>");
                        out.print("</div>");
                    }

                    //</editor-fold>
                }
                if (id_herra > 1) {
                    //<editor-fold defaultstate="collapsed" desc="CONSULTAR MAQUINA">
                    lst_Herramental = HerramentalJpa.ConsultaHerramentalId(id_herra);
                    Object[] obj_nameh = (Object[]) lst_Herramental.get(0);
                    out.print("<div class='cont_maquin' id='cont_maquina' style='display: block'>");
                    out.print("<div class='' style='display: flex; justify-content: space-between;'>");
                    out.print("<h4>Molde: " + obj_nameh[4] + "</h4>");
                    out.print("<div style='position: absolute; top: 5px; right: 5px;'>");
                    out.print("<a class='btn-cls' title='Cerrar' style='cursor: pointer;' onclick='Esconder_Maquina()'><i class=\"fas fa-window-close\"></i></a>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("<h3> Maquinas asociadas &nbsp <i class=\"fas fa-link\"></i></h3>");
                    for (int i = 0; i < lst_Herramental.size(); i++) {
                        Object[] Obj_herra = (Object[]) lst_Herramental.get(i);
                        String[] maquinas = Obj_herra[3].toString().replace("][", "-").replace("[", "").replace("]", "").split("-");
                        if (!maquinas[0].equals("")) {
                            for (int j = 0; j < maquinas.length; j++) {
                                lst_maquina = MaquinaJpa.ConsultaMaquinaId(Integer.parseInt(maquinas[j].toString()));
                                if (lst_maquina.size() != 0) {
                                    Object[] Obj_maquina = (Object[]) lst_maquina.get(0);
                                    out.print("<h4 style='color: black;'>&nbsp; <i class=\"fas fa-hand-point-right\"></i> &nbsp;" + Obj_maquina[1] + "</h4>");
                                } else {
                                    out.print("<h4 style='color: black;'><i class=\"fas fa-hand-point-right\"></i> La maquina asociada no existe.</h4>");
                                }
                            }
                        } else {
                            out.print("<h4 style='color: black;'>No hay maquinas asociadas a este molde.</h4>");
                        }
                    }
                    out.print("</div>");

                    //</editor-fold>
                }
                //<editor-fold defaultstate="collapsed" desc="CONSULTAR FICHA TECNICA">
                if (!filtro.equals("")) {
                    lst_fichaTec = fichaTecnicaJpa.Filtar_datos_ficha_tecnica(filtro);
                } else if (fil_estado == 1) {
                    lst_fichaTec = fichaTecnicaJpa.Consultar_FichaTecnica_Vigente();
                } else if (fil_estado == 2) {
                    lst_fichaTec = fichaTecnicaJpa.Consultar_FichaTecnica_Proceso();
                } else if (fil_estado == 3) {
                    lst_fichaTec = fichaTecnicaJpa.Consultar_FichaTecnica_Inactivo();
                } else if (fil_estado == 4) {
                    lst_fichaTec = fichaTecnicaJpa.Fitro_Obsoleto();
                } else {
                    lst_fichaTec = fichaTecnicaJpa.ConsultaFichaTecnica();
                }

                out.print("<div style='display:flex; align-items: center; justify-content: space-between;'>");
                //<editor-fold defaultstate="collapsed" desc="Titulo y Boton registro">
                out.print("<div>");
                out.print("<h3 style='margin-top: 15px; margin-bottom: 15px; font-size: 15px;'>Ficha Técnica de Proceso<span style='color: black;'>|</span> <b class='tooltip' style='font-size: 15px;'> ");
                out.print("Convenciones <span class='tooltiptext' valign='top'>");
                out.print("<p>"
                        + "<i style='color: #03e900;' class=\"fas fa-circle\"></i> Estado Vigente<br>"
                        + "<i style='color: #efc66b;' class=\"fas fa-circle\"></i> Estado Proceso<br>"
                        + "<i style='color: #ff0000;' class=\"fas fa-circle\"></i> Estado Inactivo<br>"
                        + "<i style='color: #747474;' class=\"fas fa-circle\"></i> Estado Obsoleto"
                        + "</p>");
                out.print("</span></b></h3>");

                out.print("<div style='display:flex; align-items: center;'>");
                out.print("<a style='margin-top: -10px;' onclick='MostrarR()' id='Menu_registro' title='Agregar Ficha'><i style='font-size: 25px; cursor: pointer; position: absolute;' class=\"fas fa-plus-circle\"></i></a>");
//                out.print("<a href='Ficha_tecnica?opc=9' style='margin-top: -10px;' id='Menu_registro' title='Agregar Ficha'><i style='font-size: 25px; cursor: pointer; position: absolute; margin-left: 40px;' class=\"far fa-file-alt\"></i></a>");
                out.print("</div>");
                out.print("</div>");
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Filtro y buscador">
                out.print("<div class='filter' style='display: none; left: 35%;' id='toggle8'>");
                out.print("<div style='display: flex; align-items: center; '>");
                out.print("<div class='inp_search' style='display: flex;'>");
                out.print("<input type='text' id='Txt_filtro' onkeyup='Filtrar()' onchange='javascript:this.value=this.value.toUpperCase();' class='form-control' placeholder='Buscar..' autocomplete='off' >");

                out.print("</div>");
                out.print("</div>");
                out.print("</div>");

                out.print("<button title='Filtro' id='btn_filter' style='height: 30px; background: transparent; border: none; font-size: 20px; cursor: pointer; margin-left: 30px;'><i class=\"fas fa-filter\"></i></button>");
                //</editor-fold>
                out.print("</div>");
                out.print("<script>");
                out.print("$(Menu_registro).click(function() {");
                out.print("$(\"#toggle7\").toggle(\"slide\");");
                out.print("});");
                out.print("</script>");
                out.print("<script>");
                out.print("$(btn_filter).click(function() {");
                out.print("$(\"#toggle8\").toggle(\"slide\");");
                out.print("});");
                out.print("</script>");

                if (!lst_fichaTec.isEmpty()) {
                    out.print("<div>");
                    out.print("<div class='btns_option' style='text-align: center;'>");
                    out.print("<form id='formEstado' action='Ficha_tecnica?opc=3' method='post'>");
                    out.print("<b style='color: #000;'> TODAS<input type='radio' value='0' title='Sin filtros' name='fil_estado' onclick='HacerSubmit()' style='cursor:pointer;' " + ((fil_estado == 0) ? "checked" : "") + "></b>&nbsp;");
                    out.print("<b " + ((fil_estado == 1) ? "style='color: #03e900;'" : "style='color: #000;'") + "> VIGENTE<input type='radio' value='1' title='Estado Vigente' name='fil_estado' onclick='HacerSubmit()' style='cursor:pointer;'" + ((fil_estado == 1) ? "checked" : "") + "></b>&nbsp;");
                    out.print("<b " + ((fil_estado == 2) ? "style='color: #caa427;'" : "style='color: #000;'") + "> PROCESO<input type='radio' value='2' title='Estado Proceso' name='fil_estado' onclick='HacerSubmit()' style='cursor:pointer;'" + ((fil_estado == 2) ? "checked" : "") + "></b>&nbsp;");
                    out.print("<b " + ((fil_estado == 3) ? "style='color: #ff0000;'" : "style='color: #000;'") + "> INACTIVO<input type='radio' value='3' title='Estado Inactivo' name='fil_estado' onclick='HacerSubmit()' style='cursor:pointer;'" + ((fil_estado == 3) ? "checked" : "") + "></b>&nbsp;");
                    out.print("<b " + ((fil_estado == 4) ? "style='color: #747474;'" : "style='color: #000;'") + "> OBSOLETO<input type='radio' value='4' title='Estado Obsoleto' name='fil_estado' onclick='HacerSubmit()' style='cursor:pointer;'" + ((fil_estado == 4) ? "checked" : "") + "></b>&nbsp;");
                    out.print("</form>");
                    out.print("</div>");
                    if (id_fichaLog == 0) {
                        out.print("<div align='right' id='NavPosicion0' style='margin-bottom: 5px; margin-top: 20px;'></div>");
                    }
                    out.print("</div>");
                    out.print("<table class='table' id='resultados' style='width: 100%;'>");
                    out.print("<thead>");
                    out.print("<tr>");
                    out.print("<th>Referencia</th>");
                    out.print("<th>Version</th>");
                    out.print("<th colspan=1 >Molde &nbsp;<i class=\"fas fa-search\"></i></th>");
                    out.print("<th>Nombre</th>");
                    out.print("<th>Estado</th>");
//                out.print("<th>Firmas</th>");
                    out.print("<th colspan='1' style='width: 40px;'>Historial</th>");
                    out.print("<th colspan='1' style='width: 40px;'>Editar</th>");
                    out.print("<th colspan='1' style='width: 40px;'>Cambiar <br> Estado</th>");
                    out.print("<th colspan='1' style='width: 40px;'>Pendientes</th>");
                    out.print("</tr>");
                    out.print("</thead>");
                    out.print("<tr>");
                    for (int i = 0; i < lst_fichaTec.size(); i++) {
                        Object[] obj_fichaTec = (Object[]) lst_fichaTec.get(i);

                        out.print("<td align='center' colspan='1'><p style='color: #CAA427;'></p>" + obj_fichaTec[1] + "</td>");
                        out.print("<td align='center' colspan='1'><p style='color: #CAA427;'></p>" + obj_fichaTec[9] + "</td>");

                        if (obj_fichaTec[2].toString().contains("[")) {
                            String[] herramental = obj_fichaTec[2].toString().replace("][", "-").replace("[", "").replace("]", "").split("-");
                            out.print("<td align='center'>");
                            for (int j = 0; j < herramental.length; j++) {

                                lst_Herramental = HerramentalJpa.ConsultaHerramentalId(Integer.parseInt(herramental[j].toString()));
                                for (int k = 0; k < lst_Herramental.size(); k++) {
                                    Object[] con_herr = (Object[]) lst_Herramental.get(k);
                                    out.print("<br><a href='Ficha_tecnica?opc=3&id_herr=" + con_herr[0] + "' title='Consultar Maquinas'>"
                                            //                                            *******En el espacio en blanco de la condicion ternaria se pone el color rojo en caso de que si se validen los moldes inactivos*****
                                            + "<b style='" + (((Integer) con_herr[5] != 1) ? "" : "") + "'>" + "&middot; " + con_herr[4] + "</b></a>");
                                }
                            }
                            out.print("</td>");
                        } else {
//                        out.print("<br><a href='#'><b><i class=\"fas fa-angle-double-right\"></i>&nbsp;" + obj_fichaTec[2] + "</b></a>");
                        }
                        out.print("</td>");
                        out.print("<td align='center'><p style='color: #CAA427;'></p>" + obj_fichaTec[3] + "</td>");
//                    out.print("<td align='center'><p style='color: #CAA427;'></p>" + obj_fichaTec[11] + "</td>");
                        if (obj_fichaTec[8].equals("VIGENTE")) {
                            out.print("<td align='center'><p>"
                                    + "<i style='color: #03e900;' class=\"fas fa-circle\"></i>&nbsp;"
                                    + "<i class=\"far fa-circle\"></i>&nbsp;"
                                    + "<i class=\"far fa-circle\"></i>&nbsp;"
                                    + "<i class=\"far fa-circle\"></i>"
                                    + "</p></td>");
                        } else if (obj_fichaTec[8].equals("PROCESO")) {
                            out.print("<td align='center'><p>"
                                    + "<i class=\"far fa-circle\"></i>&nbsp;"
                                    + "<i style='color: #efc66b;' class=\"fas fa-circle\"></i>&nbsp;"
                                    + "<i class=\"far fa-circle\"></i>&nbsp;"
                                    + "<i class=\"far fa-circle\"></i>"
                                    + "</p></td>");
                        } else if (obj_fichaTec[8].equals("INACTIVO")) {
                            out.print("<td align='center'><p>"
                                    + "<i class=\"far fa-circle\"></i>&nbsp;"
                                    + "<i class=\"far fa-circle\"></i>&nbsp;"
                                    + "<i style='color: #ff0000;' class=\"fas fa-circle\"></i>&nbsp;"
                                    + "<i class=\"far fa-circle\"></i>"
                                    + "</p></td>");
                        } else if (obj_fichaTec[8].equals("OBSOLETO")) {
                            out.print("<td align='center'><p>"
                                    + "<i class=\"far fa-circle\"></i>&nbsp;"
                                    + "<i class=\"far fa-circle\"></i>&nbsp;"
                                    + "<i class=\"far fa-circle\"></i>&nbsp;"
                                    + "<i style='color: #747474;' class=\"fas fa-circle\"></i>"
                                    + "</p></td>");
                        } else if (obj_fichaTec[8].equals("")) {
                            out.print("<td align='center'><p><i class=\"fas fa-ban\" style='font-size:20px;cursor:help;' title='Error en el estado'></i></p></td>");
                        }
//                        out.print("<td align='center'><a style='color: black;' title='Consultar Historial' href='Ficha_tecnica?opc=3&id_fichaLog=" + obj_fichaTec[0] + "'><i class=\"fas fa-history\" style='font-size: 1.9em; cursor: pointer;'></i></a></td>");
                        out.print("<td align='center'><a style='color: black;' title='Consultar Historial' href='Ficha_tecnica?opc=8&id_fichaLog=" + obj_fichaTec[0] + "'><i class=\"fas fa-history\" style='font-size: 1.9em; cursor: pointer;'></i></a></td>");

                        out.print("<td align='center'><a style='color: black;' title='Editar Ficha' href='Ficha_tecnica?opc=2&id_fichat=" + obj_fichaTec[0] + "'><i class=\"fas fa-edit\" style='font-size: 1.9em; cursor: pointer;'></i></a></td>");
                        if ((Integer) obj_fichaTec[4] == 1) {
                            out.print("<td align='center'><a style='color: black;' title='Cambiar Estado' href='Ficha_tecnica?opc=3&id_fichat=" + obj_fichaTec[0] + "'><i class=\"fas fa-check-circle\" style='font-size: 1.9em; cursor: pointer;'></i></a></td>");
                        } else if ((Integer) obj_fichaTec[4] == 2) {
                            out.print("<td align='center'><a style='color: black;' title='Cambiar Estado' href='Ficha_tecnica?opc=3&id_fichat=" + obj_fichaTec[0] + "'><i class=\"fas fa-pause-circle\" style='font-size: 1.9em; cursor: pointer;'></i></a></td>");
                        } else if ((Integer) obj_fichaTec[4] == 3) {
                            out.print("<td align='center'><a style='color: black;' title='Cambiar Estado' href='Ficha_tecnica?opc=3&id_fichat=" + obj_fichaTec[0] + "'><i class=\"fas fa-times-circle\" style='font-size: 1.9em; cursor: pointer;'></i></a></td>");
                        } else if ((Integer) obj_fichaTec[4] == 4) {
                            out.print("<td align='center'><a style='color: black;' title='Cambiar Estado' href='Ficha_tecnica?opc=3&id_fichat=" + obj_fichaTec[0] + "'><i class=\"fas fa-minus-circle\" style='font-size: 1.9em; cursor: pointer;'></i></a></td>");
                        } else {
                            out.print("<td align='center'><i class=\"fas fa-ban\" title='Error en el estado' style='font-size: 20px;cursor:help;'></i></td>");
                        }
                        int idfch = Integer.parseInt(obj_fichaTec[0].toString());
                        if (idfch > 0) {
                            lst_Herramental = HerramentalJpa.Contador_pendinetes_porFicha_Lista(idfch);
                            Object[] obj_con = (Object[]) lst_Herramental.get(0);
                            out.print("<td align='center'><a style='color: black;' title='Gestionar Pendientes' href='Ficha_tecnica?opc=10&id_fichaLog=" + obj_fichaTec[0] + "'><i class=\"fas fa-clipboard-list\" style='font-size: 1.9em; cursor: pointer;'></i></a>"
                                    + "<a class='btn_cont'>" + obj_con[2] + "</a></td>");
                        } else {
                            out.print("<td align='center'><a style='color: black;' title='Gestionar Pendientes' href='Ficha_tecnica?opc=10&id_fichaLog=" + obj_fichaTec[0] + "'><i class=\"fas fa-clipboard-list\" style='font-size: 1.9em; cursor: pointer;'></i></a>"
                                    + "<a class='btn_cont'> 0 </a></td>");
                        }
                        out.print("</tr>");
                    }
                } else {
                    out.print("<div class='btns_option' style='text-align: center;'>");
                    out.print("<form id='formEstado' action='Ficha_tecnica?opc=3' method='post'>");
                    out.print("<b style='color: #000;'> TODAS<input type='radio' value='0' title='Sin filtros' name='fil_estado' onclick='HacerSubmit()' style='cursor:pointer;' " + ((fil_estado == 0) ? "checked" : "") + "></b>&nbsp;");
                    out.print("<b " + ((fil_estado == 1) ? "style='color: #03e900;'" : "style='color: #000;'") + "> VIGENTE<input type='radio' value='1' title='Estado Vigente' name='fil_estado' onclick='HacerSubmit()' style='cursor:pointer;'" + ((fil_estado == 1) ? "checked" : "") + "></b>&nbsp;");
                    out.print("<b " + ((fil_estado == 2) ? "style='color: #caa427;'" : "style='color: #000;'") + "> PROCESO<input type='radio' value='2' title='Estado Proceso' name='fil_estado' onclick='HacerSubmit()' style='cursor:pointer;'" + ((fil_estado == 2) ? "checked" : "") + "></b>&nbsp;");
                    out.print("<b " + ((fil_estado == 3) ? "style='color: #ff0000;'" : "style='color: #000;'") + "> INACTIVO<input type='radio' value='3' title='Estado Inactivo' name='fil_estado' onclick='HacerSubmit()' style='cursor:pointer;'" + ((fil_estado == 3) ? "checked" : "") + "></b>&nbsp;");
                    out.print("<b " + ((fil_estado == 4) ? "style='color: #747474;'" : "style='color: #000;'") + "> OBSOLETO<input type='radio' value='4' title='Estado Obsoleto' name='fil_estado' onclick='HacerSubmit()' style='cursor:pointer;'" + ((fil_estado == 4) ? "checked" : "") + "></b>&nbsp;");
                    out.print("</form>");
                    out.print("</div>");
                    out.print("<div class='Error_noDatos'>");
                    out.print("<h2 style='color: black;'>No hay resultados para " + '"' + ((fil_estado == 1) ? "VIGENTE" : (fil_estado == 2) ? "PROCESO" : (fil_estado == 3) ? "INACTIVO" : (fil_estado == 4) ? "OBSOLETO" : "") + '"' + "</h2>");
                    out.print("<i class=\"fas fa-exclamation-circle\"></i>");
                    out.print("</div>");
                }
                out.print("</table>");
                out.print("<script type='text/javascript'>");
                out.print("var pager0 = new Pager0('resultados', 10);");
                out.print("pager0.init();");
                out.print("pager0.showPageNav('pager0','NavPosicion0');");
                out.print("pager0.showPage(1);");
                out.print("</script>");
                out.print("</div> <!-- END of content -->");
                out.print("<div class='cleaner'></div>");
                //</editor-fold>
                //</editor-fold>
            } else if (pageContext.getRequest().getAttribute("Ficha_tecnica").toString().equals("Consulta_Historial")) {
                //<editor-fold defaultstate="collapsed" desc="HISTORIAL">
                if (id_fichaLog > 0) {
                    //<editor-fold defaultstate="collapsed" desc="CONSULTAR HISTORIAL">

                    lst_fichaTec = fichaTecnicaJpa.consultar_FichaTecnica_log(id_fichaLog);

                    if (lst_fichaTec.size() != 0) {

                        Object[] obj_fichal = (Object[]) lst_fichaTec.get(0);
                        out.print("<div class=''>");
                        out.print("<div class='header_h'>");
                        out.print("<div class='' style='display: flex; align-items: baseline;'>");
                        out.print("<a href='Ficha_tecnica?opc=9' style='color: black; font-size: 20px; margin-right: 20px;' title='Volver'><i class=\"fas fa-arrow-left\"></i></a>");
                        out.print("<h3 style='text-align: left; font-size: 1.1rem;'>Historial de Ficha: " + obj_fichal[0] + "</h3>");
                        out.print("</div>");
                        out.print("<div class=''>");
                        out.print("<input type='text' id='Txt_filtroh' onkeyup='Filtrar3()' onchange='javascript:this.value=this.value.toUpperCase();' class='form-control' placeholder='Buscar..' autocomplete='off'>"
                                + "&nbsp; <i style='font-size: 20px;' class=\"fas fa-search\"></i>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("<div class=''>");
                        out.print("<div align='right' id='NavPosicion1' style='margin-bottom: 5px;'></div>");
                        out.print("</div>");
                        out.print("<div class=''>");
                        out.print("<table class='table' id='table_history' style='width: 1250px;'>");
                        out.print("<thead>");
//                out.print("<th>id ficha tecnica</th>");
                        out.print("<th>Referencia Ficha</th>");
                        out.print("<th>Version</th>");
                        out.print("<th>Nombre</th>");
                        out.print("<th>Herramental</th>");
                        out.print("<th>Estado</th>");
//                out.print("<th>firma</th>");
                        out.print("<th>Usuario Registro</th>");
                        out.print("<th>Fecha Actualizacion</th>");
                        out.print("</thead>");
                        out.print("<tr style='justify-content: space-between;'>");
                        for (int i = 0; i < lst_fichaTec.size(); i++) {
                            Object[] obj_fichaLog = (Object[]) lst_fichaTec.get(i);
                            out.print("<td align='center'> " + obj_fichaLog[0] + "</td>");
                            out.print("<td align='center'> " + obj_fichaLog[9] + "</td>");
                            out.print("<td align='center'> " + obj_fichaLog[1] + "</td>");
//                        out.print("<td> " + obj_fichaLog[2] + "</td>");
                            if (obj_fichaLog[2].toString().contains("[")) {
                                String[] moldes = obj_fichaLog[2].toString().replace("][", "-").replace("[", "").replace("]", "").split("-");
                                out.print("<td align='center'>");
                                for (int j = 0; j < moldes.length; j++) {
                                    lst_Herramental = HerramentalJpa.ConsultaHerramentalId(Integer.parseInt(moldes[j]));
                                    for (int k = 0; k < lst_Herramental.size(); k++) {
                                        Object[] C_molde = (Object[]) lst_Herramental.get(k);
                                        out.print("<p>" + C_molde[4] + "</p>");
                                    }
                                }
                                out.print("</td>");
                            } else {
                            }
                            out.print("<td align='center'> " + obj_fichaLog[7] + "</td>");
                            out.print("<td align='center'> " + obj_fichaLog[5] + "</td>");
                            out.print("<td align='center'> " + obj_fichaLog[6] + "</td>");
                            out.print("</tr>");
                        }
                        out.print("</table>");
                        out.print("</div>");
                        out.print("<script type='text/javascript'>");
                        out.print("var pager1 = new Pager0('table_history', 5);");
                        out.print("pager1.init();");
                        out.print("pager1.showPageNav('pager1','NavPosicion1');");
                        out.print("pager1.showPage(1);");
                        out.print("</script>");
                        out.print("</div>");
                    } else {
                        out.print("<div class='' style='margin-top: 10px;'>");
                        out.print("<div style='display: flex;'>");
                        out.print("<a onclick=\"window.history.back();\" style='margin-right: 10px; cursor: pointer;'><i style='color:black; font-size: 20px;' class=\"fas fa-arrow-left\"></i></a>");
                        out.print("<h2 style='text-align: left;'>Historial de fichas tecnicas</h2>");
                        out.print("</div>");
                        out.print("<table class='table' style='width:1250px;'>");
                        out.print("<thead>");
//                out.print("<th>id ficha tecnica</th>");
                        out.print("<th>Referencia Ficha</th>");
                        out.print("<th>Nombre</th>");
                        out.print("<th>Herramental</th>");
                        out.print("<th>Estado</th>");
//                out.print("<th>firma</th>");
                        out.print("<th>Usuario Registro</th>");
                        out.print("<th>Fecha Actualizacion</th>");
                        out.print("</thead>");
                        out.print("<tr>");
                        out.print("<td align='center' colspan='10'><h3>No hay ningun historial de esta ficha</h3></td>");
                        out.print("</tr>");
                        out.print("</table>");
                        out.print("</div>");
                    }
                    //</editor-fold>
                } else {
                    out.print("<h1 style='color: red;'>Ha ocurrido un problema<h1>");
                }
                //</editor-fold>
            } else if (pageContext.getRequest().getAttribute("Ficha_tecnica").toString().equals("Gestionar_Pendientes")) {
                //<editor-fold defaultstate="collapsed" desc="PENDIENTES">
                int ficha_p = 0;
                int ficha_c = 0;
                int id_pend = 0;
                int id_pendC = 0;
                //<editor-fold defaultstate="collapsed" desc="CAPTURAR DATOS DE PENDIENTES">
                try {
                    ficha_p = Integer.parseInt(pageContext.getRequest().getAttribute("Gestionar_Pendientes").toString());
                } catch (Exception e) {
                    ficha_p = 0;
                }
                try {
                    id_pendC = Integer.parseInt(pageContext.getRequest().getAttribute("id_pendt_cargos").toString());
                } catch (Exception e) {
                    id_pendC = 0;
                }

                try {
                    id_pend = Integer.parseInt(pageContext.getRequest().getAttribute("Id_pendiente").toString());
                } catch (Exception e) {
                    id_pend = 0;
                }
                //</editor-fold>

                if (ficha_p > 0) {

                    if (id_pend > 0) {
                        //<editor-fold defaultstate="collapsed" desc="EDITAR PENDIENTE">

                        lst_fichaTec = fichaTecnicaJpa.Consultar_pendientes_Id(id_pend);

                        for (int i = 0; i < lst_fichaTec.size(); i++) {
                            Object[] Obj_editPend = (Object[]) lst_fichaTec.get(i);
                            out.print("<script src=\"Interfaz/EditorHtml/htmljquery-3.5.1.min.js\" type=\"text/javascript\"></script>");
                            out.print("<script src=\"Interfaz/EditorHtml/htmlpopper.min.js\" type=\"text/javascript\"></script>");
                            out.print("<link href=\"Interfaz/EditorHtml/htmlbootstrap2.min.css\" rel=\"stylesheet\" type=\"text/css\" />");
                            out.print("<script src=\"Interfaz/EditorHtml/htmlbootstrap.min.js\" type=\"text/javascript\"></script>");
                            out.print("<link href=\"Interfaz/EditorHtml/htmlsummernote-bs4.min.css\" rel=\"stylesheet\" type=\"text/css\" />");
                            out.print("<script src=\"Interfaz/EditorHtml/htmlsummernote-bs4.min.js\" type=\"text/javascript\"></script>");
                            out.print("<div id='toggle9' class='pdnt_registro' style='display: block;'>");
                            out.print("<h4>Editar Pendiente</h4>");
                            out.print("<form action='Ficha_tecnica?opc=12' method='post' id='FormInstruccion'>");
                            out.print("<input type='hidden' class='form-control' name='id_pndt' value='" + Obj_editPend[0] + "'><br>");
                            out.print("<input type='hidden' class='form-control' name='id_fichat' value='" + Obj_editPend[1] + "'><br>");
                            out.print("<a><i class=\"fas fa-calendar-check\" style='font-size: 20px;'></i> &nbsp;"
                                    + "<input type='text' class='form-control' name='txt_fecha' id='datepicker' value='" + Obj_editPend[2] + "' autocomplete=\"off\"></a>");
                            out.print("<br><h5 style='margin-bottom: 0px;'>Causas y Sugerencias:</h5>");
                            out.print("<script type='text/javascript'>");
                            out.print("var validation = new LiveValidation('datepicker');");
                            out.print("validation.add( Validate.Presence );");
                            out.print("</script><br />");
                            out.print("<div class=''>");
                            out.print("<textarea name='Txt_instruccion_seguridad' contenteditable='false' class='' style='heigth: 250px;' id='editor'>");
                            out.print("" + Obj_editPend[3] + "");
                            out.print("</textarea>");
                            out.print("<script>"
                                    + "    CKEDITOR.replace(\"editor\");    "
                                    + "</script>");
                            out.print("<textarea style='display: none;' name='Txt_instruccion_seguridad' id='Txt_instruccion_seguridad'></textarea>");
                            out.print("<button type='submit' class='btnft' style='margin-top: 10px;' >Editar</button>");
                            out.print("</form>");
                            out.print("</div>");
                            out.print("</div>");
                        }

                        //</editor-fold>
                    } else {
                        //<editor-fold defaultstate="collapsed" desc="REGISTRAR PENDIENTE">
                        out.print("<script src=\"Interfaz/EditorHtml/htmljquery-3.5.1.min.js\" type=\"text/javascript\"></script>");
                        out.print("<script src=\"Interfaz/EditorHtml/htmlpopper.min.js\" type=\"text/javascript\"></script>");
                        out.print("<link href=\"Interfaz/EditorHtml/htmlbootstrap2.min.css\" rel=\"stylesheet\" type=\"text/css\" />");
                        out.print("<script src=\"Interfaz/EditorHtml/htmlbootstrap.min.js\" type=\"text/javascript\"></script>");
                        out.print("<link href=\"Interfaz/EditorHtml/htmlsummernote-bs4.min.css\" rel=\"stylesheet\" type=\"text/css\" />");
                        out.print("<script src=\"Interfaz/EditorHtml/htmlsummernote-bs4.min.js\" type=\"text/javascript\"></script>");
                        out.print("<div id='toggle9' class='pdnt_registro' style='display: none;'>");
                        out.print("<h4>Nuevo Pendiente</h4>");
                        out.print("<form action='Ficha_tecnica?opc=11' method='post' id='FormInstruccion'>");
                        out.print("<input type='hidden' class='form-control' name='id_ficha' value='" + ficha_p + "'><br>");
                        out.print("<a><i class=\"fas fa-calendar-check\" style='font-size: 20px;'></i> &nbsp;"
                                + "<input type='text' class='form-control' name='txt_fecha' id='datepicker' placeholder='Seleccionar Fecha' required autocomplete=\"off\" ></a>");
                        out.print("<br><h5 style='margin-bottom: 0px;'>Causas y Sugerencias:</h5>");
                        out.print("<script type='text/javascript'>");
                        out.print("var validation = new LiveValidation('datepicker');");
                        out.print("validation.add( Validate.Presence );");
                        out.print("</script><br />");
                        out.print("<div class=''>");
                        out.print("<textarea name='Txt_instruccion_seguridad' contenteditable='false' class='' style='heigth: 250px;' id='editor'>");
                        out.print("<div contenteditable='false'><strong>Causas: </strong></div>"
                                + "<div contenteditable='true'>"
                                + "<p style='height: auto;'>"
                                + "*"
                                + "</p>"
                                + "</div>"
                                + "<hr />"
                                + "<strong>Sugerencias: </strong>"
                                + "<div contenteditable='true'>"
                                + "<p style='height: auto;'>"
                                + "*"
                                + "</p>"
                                + "</div>");
                        out.print("</textarea>");
                        out.print("<script>"
                                + "    CKEDITOR.replace(\"editor\");    "
                                + "</script>");
                        out.print("<textarea style='display: none;' name='Txt_instruccion_seguridad' id='Txt_instruccion_seguridad'></textarea>");
                        out.print("<button type='submit' class='btnft' style='margin-top: 10px;'>Registrar</button>");
                        out.print("</form>");
                        out.print("</div>");
                        out.print("</div>");
                        //</editor-fold> 
                    }
                    //<editor-fold defaultstate="collapsed" desc="SELECCIONAR Y ENVIAR EMAIL">
                    if (id_pendC > 0) {
                        lst_usuario = UsuarioJpa.ConsultaCargos();

                        out.print("<div id='' class='cont_cargos'>");
                        out.print("<div class=''>");
                        out.print("<div style='display: flex;justify-content: space-between;'>");
                        out.print("<h4>Seleccionar Cargos:</h4>");
                        out.print("<a href='Ficha_tecnica?opc=10&id_fichaLog=" + ficha_p + "' style='margin-right:3px;' title='Cerrar'><i class=\"fas fa-times-circle\" style='position:absolute;margin-top: -10px;font-size: 15px;color: #f44336;'></i></a>");
                        out.print("</div>");
//                    out.print("<hr>");
                        out.print("<form action='Ficha_tecnica?opc=13' method='post'>");
                        out.print("<input type='hidden' class='form-control' name='num_pendi' id='' value='" + id_pendC + "'>");
                        out.print("<input type='hidden' class='form-control' name='txt_idficha' id='' value='" + ficha_p + "'>");
                        out.print("<input type='hidden' class='form-control' name='txt_cargs' id='txt_cargs'>");
                        out.print("<table class='table'>");
                        for (int i = 0; i < lst_usuario.size(); i++) {
                            Object[] obj_selCargos = (Object[]) lst_usuario.get(i);
                            out.print("<tr>");
                            out.print("<td><b>" + obj_selCargos[1] + "</b></td>");
                            out.print("<td><input type='checkbox' onclick='MasivoCargos(this.value);' class='form-control' value='" + obj_selCargos[0] + "'></td>");
                            out.print("</tr>");
                        }
                        out.print("</table>");
                        out.print("<button type='submit' class='btnft'>Enviar Correo</button>");
                        out.print("</form>");
                        out.print("</div>");
                        out.print("<div class=''>");
                        out.print("</div>");
                        out.print("</div>");
                        out.print("<script>");
                        out.print("$(sel_cargos).click(function() {");
                        out.print("$(\"#toggle10\").toggle(\"slide\");");
                        out.print("});");
                        out.print("</script>");
                    }
                    //</editor-fold>

                    //<editor-fold defaultstate="collapsed" desc="CONSULTAR PENDIENTES">
                    out.print("<div class='cont_pend'>");
                    out.print("<div class='' style='display: flex;'>");
                    out.print("<a href='Ficha_tecnica?opc=9' style='color: black; font-size: 20px; margin-right: 20px;' title='Volver'><i class=\"fas fa-arrow-left\"></i></a>");
                    out.print("<h3>Pendientes</h3>");
                    out.print("</div>");
                    out.print("<div class='inpt_search'>");
                    out.print("<input type='text' id='Txt_filtrop' onkeyup='Filtrar2()' onchange='javascript:this.value=this.value.toUpperCase();' class='form-control' placeholder='Buscar..' autocomplete='off' >"
                            + "&nbsp; <i style='font-size: 20px' class=\"fas fa-search\"></i>");
                    out.print("</div>");
                    out.print("</div>");
                    out.print("<div style='display: flex; margin-top: 10px;'>");
                    out.print("<a style='margin-top: -10px;' onclick='MostrarR()' id='Register_pdnt' title='Agregar Pendiente'><i style='font-size: 25px; cursor: pointer; margin-bottom: 10px;' class=\"fas fa-plus-circle\"></i></a>");

                    if (ficha_p > 1) {
                        lst_fichaTec = fichaTecnicaJpa.ConsultaFichaTecnica_id(ficha_p);
                        Object[] obj_conID = (Object[]) lst_fichaTec.get(0);
                        out.print("&nbsp; <h5 style='margin-top: -5px;margin-left: 10px;font-size: 15px;margin-bottom: 0;'>Ficha: " + obj_conID[1] + "</h5>");
                    } else {

                    }
                    out.print("</div>");

                    out.print("</script>");
                    out.print("<script>");
                    out.print("$(Register_pdnt).click(function() {");
                    out.print("$(\"#toggle9\").toggle(\"slide\");");
                    out.print("});");
                    out.print("</script>");
                    lst_fichaTec = fichaTecnicaJpa.Consultar_Pendiente_FichaT_Id(ficha_p);

                    out.print("<div align='right' id='NavPosicion2' style='margin-bottom: 5px;'></div>");
                    out.print("<table class='table' id='cons_pdnt' style='width: 1250px;'>");
                    out.print("<thead>");
                    out.print("<tr>");
                    out.print("<th>Fecha</th>");
                    out.print("<th>Causas</th>");
                    out.print("<th>Sugerencias</th>");
                    out.print("<th>Fecha Solucion</th>");
                    out.print("<th>Solucion</th>");
                    out.print("<th>Usuario Registro</th>");
                    out.print("<th>Editar</th>");
                    out.print("<th>Enviar <br> Correo</th>");
                    out.print("</tr>");
                    out.print("</thead>");
                    if (lst_fichaTec.size() != 0) {
                        for (int i = 0; i < lst_fichaTec.size(); i++) {
                            Object[] Obj_pendiente = (Object[]) lst_fichaTec.get(i);
                            out.print("<tr>");
                            out.print("<td align='center'>" + Obj_pendiente[2] + "</td>");

//                            out.print("<td align='center'>" + Obj_pendiente[3] + "</td>");
                            String[] Pendientes = Obj_pendiente[3].toString()
                                    .replace("<strong>Causas: </strong>", "")
                                    .replace("<div contenteditable=\"true\">", "")
                                    .replace("<div contenteditable=\"true\">", "")
                                    .replace("<strong>Sugerencias: </strong>", "")
                                    .replace("<p style=\"height: auto;\">", "")
                                    .replace("</div>", "")
                                    .replace("\n", "")
                                    .replace("</p>", "")
                                    .replace("<p style='height: auto;'>\n", "")
                                    .replace("*", "")
                                    .split("<hr />");
//
                            out.print("<td align='center'>" + Pendientes[0] + "</td>");
                            out.print("<td align='center'>" + Pendientes[1] + "</td>");

                            if (Obj_pendiente[7] == null) {
                                out.print("<td align='center'> Sin fecha de solucion  </td>");
                            } else {
                                out.print("<td align='center'>" + Obj_pendiente[7] + "</td>");
                            }
                            if (Obj_pendiente[8] == null) {
                                out.print("<td align='center'> Sin Solucion </td>");
                            } else {
                                out.print("<td align='center'>" + Obj_pendiente[8] + "</td>");
                            }

                            out.print("<td align='center'>" + Obj_pendiente[5] + "</td>");

                            if ((Integer) Obj_pendiente[4] == 0) {
                                out.print("<td align='center'><a href='Ficha_tecnica?opc=10&id_pndte=" + Obj_pendiente[0] + "&id_fichaLog=" + Obj_pendiente[1] + "' title='Editar Pendiente'><i class=\"fas fa-pen\" style='font-size: 20px; cursor: pointer; color: #000;'></i></a></td>");
                            } else {
                                out.print("<td align='center'><a title='No se puede editar'><i class=\"fas fa-ban\" style='font-size: 20px; cursor: pointer;'></i></a></td>");
                            }

                            if ((Integer) Obj_pendiente[4] == 0) {
                                out.print("<td align='center'><a href='Ficha_tecnica?opc=14&id_pndt=" + Obj_pendiente[0] + "&id_fichaLog=" + Obj_pendiente[1] + "' id='' title='Enviar Correo'><i class=\"fas fa-envelope\" style='font-size: 20px; cursor: pointer;color:black;'></i></a></td>");
                            } else {
                                out.print("<td align='center'><a title='El correo ha sido enviado'><i class=\"fas fa-check\" style='font-size: 20px; cursor: pointer;'></i></a></td>");
                            }

                            out.print("</tr>");
                        }
                    } else {
                        out.print("<tr></tr>");
                        out.print("<tr>");
                        out.print("<td colspan='8' align='center'><h3>No hay pendientes.</h3></td>");
                        out.print("</tr>");
                    }
                    out.print("</table>");
                    out.print("<script type='text/javascript'>");
                    out.print("var pager0 = new Pager0('cons_pdnt', 10);");
                    out.print("pager0.init();");
                    out.print("pager0.showPageNav('pager0','NavPosicion2');");
                    out.print("pager0.showPage(1);");
                    out.print("</script>");
                    out.print("<script>");
                    out.print("$(sel_cargos).click(function() {");
                    out.print("$(\"#toggle10\").toggle(\"slide\");");
                    out.print("});");
                    out.print("</script>");
                }
                //</editor-fold>
                //</editor-fold>
            }

        } catch (IOException ex) {
            Logger.getLogger(Tag_ficha_tecnica.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
