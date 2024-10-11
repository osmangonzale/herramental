<%
            HttpSession sesion = request.getSession();
            if (sesion.getAttribute("id") != null || sesion.getAttribute("Nombre") != null || sesion.getAttribute("Rol") != null) {
                sesion.removeAttribute("id");
                sesion.removeAttribute("Nombre");
                sesion.removeAttribute("Rol");
                sesion.removeAttribute("id_rol");
                sesion.removeAttribute("Mail");
                sesion.invalidate();
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
%>

