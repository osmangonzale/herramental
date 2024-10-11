<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/tld_menu.tld" prefix="menu" %>
<%@taglib uri="/WEB-INF/tlds/tld_resultado.tld" prefix="resultados" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Programa de verificacion metrologica</title>
        <jsp:include page="Encabezado.jsp"></jsp:include>
            <script type = "text/javascript" >
                history.pushState(null, null, 'menu.jsp');
                window.addEventListener('popstate', function (event) {
                    history.pushState(null, null, 'menu.jsp');
                });
            </script>
            <script src="https://kit.fontawesome.com/0bb7091f99.js" crossorigin="anonymous"></script>
        </head>
        <body id="subpage" onload='AlertPendiete()'>
            <div id="templatemo_wrapper">
            <menu:MuestraMenu />
<!--            <br><br><h2>¡Buen Dia!</h2>
                <br><b style="color: red; font-size:14px" >Debido a un ajuste lanzando recientemente, Favor oprimir en su teclado las teclas (Control + Shift(Fecha Arriba) + R), para actualizar cambios realizados. Muchas Gracias</b>-->
        </div>
        <resultados:MuestraResultados/>
    </body>
    <script src="Interfaz/Acordeon/Js_accordeon.js"></script>
</html>
