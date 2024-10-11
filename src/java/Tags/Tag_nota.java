package Tags;

import controladores.NotaJpaController;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class Tag_nota extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        HttpSession sesion = pageContext.getSession();
        NotaJpaController jpa_nota = new NotaJpaController();
        Date fechaA = new Date();
        List lst_nota = null;
        List lst_notas = null;
        String filtro = "";
        try {
            lst_nota = jpa_nota.ConsultaNota();
            filtro = (String) pageContext.getRequest().getAttribute("filtro").toString();
            out.print("<div id='content_sin'>");
            out.print("<div style='float:right'>");
            out.print("<form action='Nota?opc=1' onsubmit='registroN();' method='post' id='formF'>");
            out.print("<input type='text' id='datepicker' name='txt_filtro' value='' onchange='Javascript:formF.submit();' placeholder='Buscar'>");
            out.print("</form>");
            out.print("</div>");
            out.print("<h2>Notas</h2>");
            out.print("<form action='Nota?opc=2' onsubmit='registroN();' method='post' id='formN'>");
            out.print("<input type='hidden' id='fecha-id' name='txt_fecha' value=" + (fechaA.getYear() + 1900) + "" + (fechaA.getMonth() < 10 ? "-0" : "-") + "" + (fechaA.getMonth() + 1) + "" + (fechaA.getDate() < 10 ? "-0" : "-") + "" + fechaA.getDate() + ">");
            out.print("<textarea id='editor' name='txt_descripcion' style='width: 50%; height: 600' placeholder='descripcion'>");
            if (lst_nota != null) {
                Object[] obj_nota = (Object[]) lst_nota.get(0);
                out.print("" + obj_nota[2].toString().replace("<div>", "<div contenteditable='true'>") + "");
            } else {
                out.print("<font size='2'><div contenteditable='true'><p>*</p></div></font>");
            }
            out.print("</textarea>");
            out.print("<br /><input type='submit' id='btsubmit' value='Guardar'>");
            out.print("<div class=\"la-ball-fall\" style='bottom: 24px;left: 72px;display:none;' id='puntos'>\n"
                    + "          <div></div>\n"
                    + "          <div></div>\n"
                    + "          <div></div>\n"
                    + "        </div>");
            out.print("</form>");
            if (!filtro.equals("")) {
                lst_notas = jpa_nota.ConsultaNotaFecha(filtro);
                if (lst_notas != null) {
                    for (int i = 0; i < lst_notas.size(); i++) {
                        Object[] obj_notas = (Object[]) lst_notas.get(i);
                        out.print("<button class='accordion'><div style='float:right;'><b style='color:black;'>" + obj_notas[3] + "</b></div></button>");
                        out.print("<div class='panel' style='overflow:scroll;'>");
                        out.print("<table class='table' style='width:100%;'>");
                        out.print("<tr>");
                        out.print("<td valing='top' colspan='3'>" + obj_notas[2] + "</td>");
                        out.print("</tr>");
                        out.print("</table>");
                        out.print("</div>");
                    }
                } else {
                    out.print("<b>No se encontraron resultados</b>");
                }
            }
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
