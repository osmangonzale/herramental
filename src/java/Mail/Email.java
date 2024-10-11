package Mail;

import controladores.UsuarioJpaController;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    //<editor-fold defaultstate="collapsed" desc="pendiente herramental">¿
    public boolean mail_Pendiente_herramental(String id_usuarios, List lst_pendiente, String usuario) throws javax.mail.MessagingException {
        UsuarioJpaController jpa_usuario = new UsuarioJpaController();
        Properties propiedades = new Properties();
        propiedades.setProperty("mail.smtp.host", "mail3.plastitec-sa.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");
        propiedades.setProperty("mail.smtp.user", "aplicativo@plastitec-sa.com");
        Session session = Session.getDefaultInstance(propiedades);
        try {
            Object[] obj_pendiente = (Object[]) lst_pendiente.get(0);
            String[] arg_correo = id_usuarios.replace("[", "").split("]");
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("aplicativo@plastitec-sa.com"));
            for (int j = 0; j < arg_correo.length; j++) {
                Object[] obj_usa = (Object[]) jpa_usuario.ConsultaUsuarioId(Integer.parseInt(arg_correo[j])).get(0);
                String[] destino = (obj_usa[7] + ";").split(";");
                InternetAddress[] addresto = new InternetAddress[destino.length];
                for (int i = 0; i < destino.length; i++) {
                    addresto[i] = new InternetAddress(destino[i]);
                }
                message.setRecipients(Message.RecipientType.TO, addresto);// correo destinatario
//                message.addRecipient(Message.RecipientType.TO, new InternetAddress(obj_usa[7].toString()));
            }
            String sol = "";
            String des = "";
            String desc = "";
            if (obj_pendiente[8] == null) {
                sol = "No se ha solucionado el pendiente";
                desc = "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>El usuario " + obj_pendiente[5] + " ha registrado un pendiente en el herramental " + obj_pendiente[2] + "</p>";
            } else {
                desc = "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Se ha solucionado un pendiente en el herramental " + obj_pendiente[2] + "</p>";
                sol = obj_pendiente[8].toString();
                sol = sol.replace("<a href=\"UserFiles/", "<a href=\"http://172.16.5.99:8084/Herramental/UserFiles/");
                sol = sol.replace("<img src=\"UserFiles/", "<img src=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            }
            String[] arg_descripcion = obj_pendiente[4].toString().replace("<hr />", "<hr/>").split("<hr/>");
            for (int j = 0; j < arg_descripcion.length; j++) {
                des = des + "<td valign='top' style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + arg_descripcion[j] + "</td>";
                des = des.replace("<a href=\"UserFiles/", "<a href=\"http://172.16.5.99:8084/Herramental/UserFiles/");
                des = des.replace("<img src=\"UserFiles/", "<img src=\"http://172.16.5.99:8084/Herramental/UserFiles/");

            }
            message.setSubject("Pendiente herramental " + obj_pendiente[2] + "");
            message.setText("\n"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Herramental proceso</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Buen dia señor(a) usuario(a)</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Fecha: " + obj_pendiente[3] + "</p>"
                    + "" + desc + ""
                    + "<table style='width:100%'>"
                    + "<tr>"
                    + "<th style='width:20%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Cavidades</th>"
                    + "<th style='width:20%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Causas</th>"
                    + "<th style='width:20%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Sugerencias</th>"
                    + "<th style='width:20%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Solucion</th>"
                    + "</tr>"
                    + "<tr>"
                    + "" + des + ""
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + sol + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "\n", "ISO-8859-1", "html");
            Transport transport = session.getTransport("smtp");
            transport.connect("aplicativo@plastitec-sa.com", "ERmYDgB$LESoq9J6");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed"  desc="Mail movimiento herramental">
    public boolean mail_Movimiento_Herramental(String id_usuarios, List lst_movimiento, String usuario) throws javax.mail.MessagingException {
        UsuarioJpaController jpa_usuario = new UsuarioJpaController();
        Properties propiedades = new Properties();
        propiedades.setProperty("mail.smtp.host", "mail3.plastitec-sa.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");
        propiedades.setProperty("mail.smtp.user", "aplicativo@plastitec-sa.com");
        Session session = Session.getDefaultInstance(propiedades);
        try {
            Object[] obj_movimiento = (Object[]) lst_movimiento.get(0);
            String[] arg_correo = id_usuarios.replace("[", "").split("]");
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("aplicativo@plastitec-sa.com"));
            for (int j = 0; j < arg_correo.length; j++) {
                Object[] obj_usa = (Object[]) jpa_usuario.ConsultaUsuarioId(Integer.parseInt(arg_correo[j])).get(0);
                String[] destino = (obj_usa[7] + ";").split(";");
                InternetAddress[] addresto = new InternetAddress[destino.length];
                for (int i = 0; i < destino.length; i++) {
                    addresto[i] = new InternetAddress(destino[i]);
                }
                message.setRecipients(Message.RecipientType.TO, addresto);// correo destinatario
            }
            String des = obj_movimiento[13].toString();
            des = des.replace("<a href=\"UserFiles/", "<a href=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            des = des.replace("<img src=\"UserFiles/", "<img src=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            message.setSubject("Movimiento herramental " + obj_movimiento[4] + " " + obj_movimiento[15] + "");
            message.setText("\n"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Herramental proceso</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Buen dia señor(a) usuario(a)</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Fecha: " + obj_movimiento[7] + "</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>El usuario " + usuario + " ha registrado o modificado un movimiento en el herramental " + obj_movimiento[4] + " / " + obj_movimiento[6] + "</p>"
                    + "<table style='width:100%'>"
                    + "<tr>"
                    + "<th style='width: 7%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Maquina</th>"
                    + "<th style='width: 16%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Producto / Lote</th>"
                    + "<th style='width: 12%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Cavidades/<br />Deshabilitadas</th>"
                    + "<th style='padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Descripcion</th>"
                    + "<th style='width: 12%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Estado</th>"
                    + "<th style='width: 9%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Responsable</th>"
                    + "</tr>"
                    + "<tr>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + obj_movimiento[2] + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + obj_movimiento[8] + "<hr />" + obj_movimiento[9] + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'><b title='Numero cavidades'>#: </b>" + obj_movimiento[10] + "<hr/><b title='Numero cavidades deshabilitadas'>#: </b>" + obj_movimiento[11] + "<hr/><p title='cavidades'>" + obj_movimiento[12] + "</p></td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + des + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + obj_movimiento[6] + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + obj_movimiento[14].toString().replace("[", "").replace("]", "<br />") + "</td>"
                    + "</tr>"
                    + "\n", "ISO-8859-1", "html");
            Transport transport = session.getTransport("smtp");
            transport.connect("aplicativo@plastitec-sa.com", "ERmYDgB$LESoq9J6");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
// </editor-fold>
    //<editor-fold defaultstate="collapsed" desc="pendiente maquina">¿
    public boolean mail_Pendiente_maquina(String id_usuarios, List lst_pendiente) throws javax.mail.MessagingException {
        UsuarioJpaController jpa_usuario = new UsuarioJpaController();
        Properties propiedades = new Properties();
        propiedades.setProperty("mail.smtp.host", "mail3.plastitec-sa.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");
        propiedades.setProperty("mail.smtp.user", "aplicativo@plastitec-sa.com");
        Session session = Session.getDefaultInstance(propiedades);
        try {
            Object[] obj_pendinte = (Object[]) lst_pendiente.get(0);
            String[] arg_correo = id_usuarios.replace("[", "").split("]");
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("aplicativo@plastitec-sa.com"));
            for (int j = 0; j < arg_correo.length; j++) {
                Object[] obj_usa = (Object[]) jpa_usuario.ConsultaUsuarioId(Integer.parseInt(arg_correo[j])).get(0);
                String[] destino = (obj_usa[7] + ";").split(";");
                InternetAddress[] addresto = new InternetAddress[destino.length];
                for (int i = 0; i < destino.length; i++) {
                    addresto[i] = new InternetAddress(destino[i]);
                }
                message.setRecipients(Message.RecipientType.TO, addresto);// correo destinatario
            }
            String sol = "";
            String desc = "";
            if (obj_pendinte[8] == null) {
                sol = "No se ha solucionado el pendiente";
                desc = "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>El usuario " + obj_pendinte[5] + " ha registrado un pendiente en la maquina " + obj_pendinte[2] + "</p>";
            } else {
                desc = "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Se ha solucionado un pendiente en la maquina " + obj_pendinte[2] + "</p>";
                sol = "<b>Fecha: </b>" + obj_pendinte[7] + "<br />" + obj_pendinte[8].toString();
                sol = sol.replace("<a href=\"UserFiles/", "<a href=\"http://172.16.5.99:8084/Herramental/UserFiles/");
                sol = sol.replace("<img src=\"UserFiles/", "<img src=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            }
            String des = obj_pendinte[4].toString();
            des = des.replace("<a href=\"UserFiles/", "<a href=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            des = des.replace("<img src=\"UserFiles/", "<img src=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            message.setSubject("Pendiente maquina " + obj_pendinte[2] + "");
            message.setText("\n"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Herramental proceso</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Buen dia señor(a) usuario(a)</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Fecha: " + obj_pendinte[3] + "</p>"
                    + "" + desc + ""
                    + "<table style='width:100%'>"
                    + "<tr>"
                    + "<th style='padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Pendiente</th>"
                    + "<th style='padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Solucion</th>"
                    + "</tr>"
                    + "<tr>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + des + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + sol + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "\n", "ISO-8859-1", "html");
            Transport transport = session.getTransport("smtp");
            transport.connect("aplicativo@plastitec-sa.com", "ERmYDgB$LESoq9J6");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    // </editor-fold>
    //<editor-fold defaultstate="collapsed" desc="MAIL PENDIENTES FICHA TECNICA">
    public boolean mail_Pendiente_Ficha_t(String cargos, List lst_fichaT) throws javax.mail.MessagingException {
        UsuarioJpaController jpa_usuario = new UsuarioJpaController();
        Properties propiedades = new Properties();
        propiedades.setProperty("mail.smtp.host", "mail3.plastitec-sa.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");
        propiedades.setProperty("mail.smtp.user", "aplicativo@plastitec-sa.com");
        propiedades.setProperty("mail.debug", "true");
        Session session = Session.getDefaultInstance(propiedades);
        try {
            Object[] obj_pendinte = (Object[]) lst_fichaT.get(0);
            String[] arg_correo = cargos.replace("[", "").split("]");
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("aplicativo@plastitec-sa.com"));
            for (int j = 0; j < arg_correo.length; j++) {
                Object[] obj_usa = (Object[]) jpa_usuario.ConsultaUsuarioId(Integer.parseInt(arg_correo[j])).get(0);
                String[] destino = (obj_usa[7] + ";").split(";");
                InternetAddress[] addresto = new InternetAddress[destino.length];
                for (int i = 0; i < destino.length; i++) {
                    addresto[i] = new InternetAddress(destino[i]);
                }
                message.setRecipients(Message.RecipientType.TO, addresto);// correo destinatario
            }
            String sol = "";
            String desc = "";
            if (obj_pendinte[8] == null) {
                sol = "No se ha solucionado el pendiente";
                desc = "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>El usuario " + obj_pendinte[5] + " ha registrado un pendiente en la ficha tecnica <b style='color:black;'>" + obj_pendinte[9] + "</b> </p>";
            } else {
                desc = "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Se ha solucionado un pendiente en la ficha tecnica  <b style='color:black;'>" + obj_pendinte[9] + "</b> </p>";
                sol = "<b>Fecha: </b>" + obj_pendinte[7] + "<br />" + obj_pendinte[8].toString();
                sol = sol.replace("<a href=\"UserFiles/", "<a href=\"http://172.16.5.99:8084/Herramental/UserFiles/");
                sol = sol.replace("<img src=\"UserFiles/", "<img src=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            }
            String des = obj_pendinte[4].toString();
            des = des.replace("<a href=\"UserFiles/", "<a href=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            des = des.replace("<img src=\"UserFiles/", "<img src=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            
            String[] Pendient = obj_pendinte[3].toString()
                                    .replace("<strong>Causas: </strong><br>", "")
                                    .replace("<div contenteditable=\"true\">", "")
                                    .replace("<div contenteditable=\"true\">", "")
                                    .replace("<strong>Sugerencias: </strong><br>", "")
                                    .replace("<p style=\"height: auto;\">", "")
                                    .replace("</div>", "")
                                    .replace("\n", "")
                                    .replace("</p>", "")
                                    .replace("<p style='height: auto;'>\n", "")
                                    .replace("*", "")
                                    .split("<hr />");
            
            message.setSubject("Pendiente maquina " + obj_pendinte[2] + "");
            message.setText("\n"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Herramental proceso</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Buen dia señor(a) usuario(a)</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Fecha: " + obj_pendinte[2] + "</p>"
                    + "" + desc + ""
                    + "<table style='width:100%'>"
                    + "<tr>"
                    + "<th style='padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Fecha</th>"
                    + "<th style='padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Causas</th>"
                    + "<th style='padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Sugerencias</th>"
                    + "<th style='padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Solucion</th>"
                    + "</tr>"
                    + "<tr>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + obj_pendinte[2] + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + Pendient[0] + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + Pendient[1] + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + sol + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "\n", "ISO-8859-1", "html");
            Transport transport = session.getTransport("smtp");
            transport.connect("aplicativo@plastitec-sa.com", "ERmYDgB$LESoq9J6");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
    //</editor-fold>
    // <editor-fold defaultstate="collapsed"  desc="Mail movimiento">
    public boolean mail_Movimiento_maquina(String id_usuarios, List lst_movimiento, String usuario) throws javax.mail.MessagingException {
        UsuarioJpaController jpa_usuario = new UsuarioJpaController();
        Properties propiedades = new Properties();
        propiedades.setProperty("mail.smtp.host", "mail3.plastitec-sa.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");
        propiedades.setProperty("mail.smtp.user", "aplicativo@plastitec-sa.com");
        Session session = Session.getDefaultInstance(propiedades);
        try {
            Object[] obj_movimiento = (Object[]) lst_movimiento.get(0);
            String[] arg_correo = id_usuarios.replace("[", "").split("]");
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("aplicativo@plastitec-sa.com"));
            for (int j = 0; j < arg_correo.length; j++) {
                Object[] obj_usa = (Object[]) jpa_usuario.ConsultaUsuarioId(Integer.parseInt(arg_correo[j])).get(0);
                String[] destino = (obj_usa[7] + ";").split(";");
                InternetAddress[] addresto = new InternetAddress[destino.length];
                for (int i = 0; i < destino.length; i++) {
                    addresto[i] = new InternetAddress(destino[i]);
                }
                message.setRecipients(Message.RecipientType.TO, addresto);// correo destinatario
            }
            String des = obj_movimiento[13].toString();
            des = des.replace("<a href=\"UserFiles/", "<a href=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            des = des.replace("<img src=\"UserFiles/", "<img src=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            message.setSubject("Movimiento maquina " + obj_movimiento[2] + "");
            message.setText("\n"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Herramental proceso</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Buen dia señor(a) usuario(a)</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Fecha: " + obj_movimiento[7] + "</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>El usuario " + usuario + " ha registrado o ha modificado informacion del un movimiento en la maquina " + obj_movimiento[2] + "</p>"
                    + "<table style='width:100%'>"
                    + "<tr>"
                    + "<th style='width: 7%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Molde</th>"
                    + "<th style='width: 16%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Producto / Lote</th>"
                    + "<th style='width: 12%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Cavidades/<br />Deshabilitadas</th>"
                    + "<th style='padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Descripcion</th>"
                    + "<th style='width: 12%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Estado</th>"
                    + "<th style='width: 9%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Responsable</th>"
                    + "</tr>"
                    + "<tr>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'><b>" + obj_movimiento[4] + "</b><hr>" + obj_movimiento[7] + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + obj_movimiento[8] + "<hr />" + obj_movimiento[9] + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'><b title='Numero cavidades'>#: </b>" + obj_movimiento[10] + "<hr/><b title='Numero cavidades deshabilitadas'>#: </b>" + obj_movimiento[11] + "<hr/><p title='cavidades'>" + obj_movimiento[12] + "</p></td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + des + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + obj_movimiento[6] + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + obj_movimiento[14].toString().replace("[", "").replace("]", "<br />") + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "\n", "ISO-8859-1", "html");
            Transport transport = session.getTransport("smtp");
            transport.connect("aplicativo@plastitec-sa.com", "ERmYDgB$LESoq9J6");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }

    // </editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Confirmar Desmontado de maquina">¿
    public boolean mail_Firma_Desmontado(List lst_movimiento) throws javax.mail.MessagingException {
        Properties propiedades = new Properties();
        propiedades.setProperty("mail.smtp.host", "mail3.plastitec-sa.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");
        propiedades.setProperty("mail.smtp.user", "aplicativo@plastitec-sa.com");
        Session session = Session.getDefaultInstance(propiedades);
        try {
            Object[] obj_movimiento = (Object[]) lst_movimiento.get(0);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("aplicativo@plastitec-sa.com"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("C.CALIDAD1@PLASTITEC-SA.COM"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("A.CALIDAD9@PLASTITEC-SA.COM "));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("E.RODRIGUEZ@PLASTITEC-SA.COM"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("C.INYECCION1@PLASTITEC-SA.COM"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("C.EXTRUSION1@PLASTITEC-SA.COM"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("C.PELETIZADO1@PLASTITEC-SA.COM"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("N.CASTILLO@PLASTITEC-SA.COM"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("C.PROYECTOS@PLASTITEC-SA.COM"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("AJUS.INYECCION@PLASTITEC-SA.COM"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("p.ti@plastitec-sa.com"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("w.hernandez@plastitec-sa.com"));
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress("c.sgs@plastitec-sa.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("osmansax15@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("osmansax15@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("osmansax15@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("osmansax15@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("osmansax15@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("osmansax15@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("osmansax15@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("osmansax15@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("osmansax15@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("osmansax15@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("osmansax15@gmail.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("osmansax15@gmail.com"));
            String des = obj_movimiento[13].toString();
            des = des.replace("<a href=\"UserFiles/", "<a href=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            des = des.replace("<img src=\"UserFiles/", "<img src=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            message.setSubject("Movimiento maquina " + obj_movimiento[2] + "");
            message.setText("\n"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Herramental proceso</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Buen dia señor(a) usuario(a)</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Fecha: " + obj_movimiento[7] + "</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>El usuario " + obj_movimiento[14].toString().replace("[", "").replace("]", "") + " ha registrado un desmontaje en la maquina " + obj_movimiento[2] + "</p>"
                    + "<table style='width:100%'>"
                    + "<tr>"
                    + "<th style='width: 7%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;border-radius: 10px 0 0 0;'>Molde</th>"
                    + "<th style='width: 16%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Producto / Lote</th>"
                    + "<th style='width: 12%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Cavidades/<br />Deshabilitadas</th>"
                    + "<th style='padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Descripcion</th>"
                    + "<th style='width: 12%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;'>Estado</th>"
                    + "<th style='width: 9%;padding: 7px 15px 8px 15px;border: none;font-size: 12px;font-weight: bold;color: #FFF;background-color:#CAA427;border-radius: 0 10px 0 0;'>Responsable</th>"
                    + "</tr>"
                    + "<tr>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;' valign='top'>" + obj_movimiento[4] + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;' valign='top'>" + obj_movimiento[8] + "<hr />" + obj_movimiento[9] + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;' valign='top'><b title='Numero cavidades'>#: </b>" + obj_movimiento[10] + "<hr/><b title='Numero cavidades deshabilitadas'>#: </b>" + obj_movimiento[11] + "<hr/><p title='cavidades'>" + obj_movimiento[12] + "</p></td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;' valign='top'>" + des + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;' valign='top'>" + obj_movimiento[6] + "</td>"
                    + "<td style='padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;background-color:#fff;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;' valign='top'>" + obj_movimiento[14].toString().replace("[", "").replace("]", "<br />") + "<hr /><b style='color:#CAA427;'>Confirman: </b>" + obj_movimiento[19].toString().replace("[", "").replace("]", "<br />") + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "\n", "ISO-8859-1", "html");
            Transport transport = session.getTransport("smtp");
            transport.connect("aplicativo@plastitec-sa.com", "ERmYDgB$LESoq9J6");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed"  desc="Mail defectos">
    public boolean mail_notificacion_defecto(List lst_defecto) throws javax.mail.MessagingException {
        Properties propiedades = new Properties();
        propiedades.setProperty("mail.smtp.host", "mail3.plastitec-sa.com");
        propiedades.setProperty("mail.smtp.starttls.enable", "true");
        propiedades.setProperty("mail.smtp.port", "587");
        propiedades.setProperty("mail.smtp.auth", "true");
        propiedades.setProperty("mail.smtp.user", "aplicativo@plastitec-sa.com");
        Session session = Session.getDefaultInstance(propiedades);
        try {
            Object[] obj_defecto = (Object[]) lst_defecto.get(((lst_defecto.size() != 1) ? (lst_defecto.size() - 1) : 0));
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("aplicativo@plastitec-sa.com"));
            String[] destino = {"i.colmenares@plastitec-sa.com", "d.iso13485@plastitec-sa.com", "c.calidad1@plastitec-sa.com", "c.calidad2@plastitec-sa.com",
                "c.calidad3@plastitec-sa.com", "c.calidad4@plastitec-sa.com", "a.calidad9@plastitec-sa.com", "w.bustos@plastitec-sa.com", "c.produccionf1@plastitec-sa.com",
                "c.produccionf2@plastitec-sa.com", "a.prodprocesobolsa2@plastitec-sa.com", "e.rodriguez@plastitec-sa.com", "c.inyeccion1@plastitec-sa.com",
                "c.extrusion1@plastitec-sa.com", "n.castillo@plastitec-sa.com", "c.proyectos@plastitec-sa.com", "ajus.inyeccion@plastitec-sa.com", "j.mttofarma@plastitec-sa.com",
                "p.ti@plastitec-sa.com","c.sgs@plastitec-sa.com","w.hernandez@plastitec-sa.com"};
            InternetAddress[] addresto = new InternetAddress[destino.length];
            for (int i = 0; i < destino.length; i++) {
                addresto[i] = new InternetAddress(destino[i]);
            }
            message.setRecipients(Message.RecipientType.TO, addresto);
            String[] arg_descripcion = obj_defecto[6].toString().replace("<hr />", "<hr/>").split("<hr/>");
            String des1 = arg_descripcion[0].toString();
            String des2 = arg_descripcion[1].toString();
            des1 = des1.replace("<a href=\"UserFiles/", "<a href=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            des1 = des1.replace("<img src=\"UserFiles/", "<img src=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            des2 = des2.replace("<a href=\"UserFiles/", "<a href=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            des2 = des2.replace("<img src=\"UserFiles/", "<img src=\"http://172.16.5.99:8084/Herramental/UserFiles/");
            String color = "";
            if ((Integer) obj_defecto[1] == 1) {
                color = "#FFE0E0";
            } else if ((Integer) obj_defecto[1] == 2) {
                color = "#FFE3CB";
            } else if ((Integer) obj_defecto[1] == 3) {
                color = "#FFFEE0";
            } else if ((Integer) obj_defecto[1] == 4) {
                color = "#E6FECB";
            }
            message.setSubject("Nuevo defecto de " + obj_defecto[4] + " = " + obj_defecto[5] + "");
            message.setText("\n"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Herramental proceso</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Buen dia señor(a) usuario(a)</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Fecha: " + obj_defecto[10] + "</p>"
                    + "<p style='font-family: arial, verdana, sans-serif; font-size: 14px;'>Se ha registrado un nuevo defecto: <b>" + obj_defecto[5] + "</b></p>"
                    + "<table style='width:100%'>"
                    + "<tr>"
                    + "<td align='center' rowspan='2' style='background:" + color + ";padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'><b style='color:#CAA427'>" + obj_defecto[7] + "</b></td>"
                    + "<td align='center' style='background:" + color + ";padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'><b style='color:#CAA427'>Defecto: </b>" + obj_defecto[5] + "</td>"
                    + "<td align='center' colspan='2' style='background:" + color + ";padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'><b style='color:#CAA427'>Clasificacion: </b>" + obj_defecto[2] + "</td>"
                    + "</tr>"
                    + "<tr>"
                    + "<td align='center' style='width:50%;background:" + color + ";padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + des1 + "</td>"
                    + "<td valign='top' colspan='2' style='background:" + color + ";padding: 3px 3px 3px 3px;border-color: #CAA427;font-size: 11px;color: #292929;border-right: 2px solid #eee;border-bottom: 2px solid #eee;text-transform: uppercase;'>" + des2 + "</td>"
                    + "</tr>"
                    + "</table>"
                    + "\n", "ISO-8859-1", "html");
            Transport transport = session.getTransport("smtp");
            transport.connect("aplicativo@plastitec-sa.com", "ERmYDgB$LESoq9J6");
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
    // </editor-fold>
}