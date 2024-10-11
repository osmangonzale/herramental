package Servlets;

import controladores.UsuarioJpaController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Usuario", urlPatterns = {"/Usuario"})
public class Usuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=ISO-8859-1");
        PrintWriter out = response.getWriter();
        try {
            HttpSession sesion = request.getSession();
            int opc = Integer.parseInt(request.getParameter("opc"));
            int idusuarioS = Integer.parseInt(sesion.getAttribute("id").toString());
            Boolean resultado = false;
            UsuarioJpaController jpa_usuario = new UsuarioJpaController();
            String Rol_Usuario = sesion.getAttribute("Rol").toString();
            String nombre_Usuario = sesion.getAttribute("Nombre").toString();
            String filtro = "";
            String nombre = "", apellido = "", codigo ="", documento = "", usuario = "", correo = "", pass = "", passConf = "", cargo = "";
            int id_rol = 0;
            int id_area = 0;
            int idusuario = 0;
            int estado = 0;
            int code = 0;
            int doc = 0;
            switch (opc) {
                case 1:
                    filtro = request.getParameter("txt_bus");
                    idusuario = Integer.parseInt(request.getParameter("idU"));
                    if (!filtro.equals("")) {
                        request.setAttribute("consulta_usuarios", jpa_usuario.ConsultaUsuariosFiltro(filtro));
                    } else {
                        request.setAttribute("consulta_usuarios", jpa_usuario.ConsultaUsuarios());
                    }
                    request.setAttribute("filtro", filtro);
                    request.setAttribute("id_usuario", idusuario);
                    request.getRequestDispatcher("Usuarios.jsp").forward(request, response);
                    break;
                case 2:
                    nombre = request.getParameter("txt_nombre");
                    apellido = request.getParameter("txt_apellido");
                    documento = request.getParameter("txt_doc");
                    codigo = request.getParameter("txt_cod");
                    id_rol = Integer.parseInt(request.getParameter("lsrol"));
                    id_area = Integer.parseInt(request.getParameter("lsarea"));
                    usuario = request.getParameter("txt_user");
                    cargo = request.getParameter("lstcargo");
                    correo = request.getParameter("txt_correo");
                    resultado = jpa_usuario.RegistroUsuario(nombre, apellido, documento, codigo, id_rol, id_area, cargo, correo, usuario, pass, nombre_Usuario);
                    request.setAttribute("Registro_usuario", resultado);
                    request.setAttribute("Pass", true);
                    request.getRequestDispatcher("Usuario?opc=1&idU=" + 0 + "&txt_bus=").forward(request, response);
                    break;
                case 3:
                    filtro = request.getParameter("txt_bus");
                    idusuario = Integer.parseInt(request.getParameter("idU").toString());
                    nombre = request.getParameter("txt_nombreM");
                    apellido = request.getParameter("txt_apellidoM");
                    doc = Integer.parseInt(request.getParameter("txt_docM"));
                    code = Integer.parseInt(request.getParameter("txt_codM"));
                    id_rol = Integer.parseInt(request.getParameter("lsrolM").toString());
                    id_area = Integer.parseInt(request.getParameter("lsareaM").toString());
                    cargo = request.getParameter("lstcargoM");
                    usuario = request.getParameter("txt_userM");
                    correo = request.getParameter("txt_correoM");
                    pass = request.getParameter("txt_contraM");
//                    passConf = request.getParameter("txt_ConfContraM");
                    if (!pass.equals("")) {
                        resultado = jpa_usuario.Editar_usuario(idusuario, id_rol, id_area, nombre, apellido, doc, code, usuario, cargo, correo, usuario);
                        request.setAttribute("Modificar_usuario", resultado);
                        request.setAttribute("Pass", true);
                    } else {
                        request.setAttribute("Registro_usuario", resultado);
                        request.setAttribute("Pass", false);
                    }
                    request.getRequestDispatcher("Usuario?opc=1&idU=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 4:
                    filtro = request.getParameter("txt_bus");
                    idusuario = Integer.parseInt(request.getParameter("idU").toString());
                    estado = Integer.parseInt(request.getParameter("est").toString());
                    resultado = jpa_usuario.ModificarEstado(idusuario, estado);
                    request.setAttribute("Estado_usuario", resultado);
                    request.setAttribute("estado", estado);
                    request.getRequestDispatcher("Usuario?opc=1&idU=" + 0 + "&txt_bus=" + filtro + "").forward(request, response);
                    break;
                case 5:
                    idusuario = Integer.parseInt(request.getParameter("idU"));
                    pass = request.getParameter("txt_pass");
                    resultado = jpa_usuario.modificarContraseña(idusuario, pass);
                    request.setAttribute("resultado_contraseñaR", resultado);
                    request.getRequestDispatcher("salir.jsp").forward(request, response);
                    break;
            }
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception ex) {
            request.getRequestDispatcher("menu.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
