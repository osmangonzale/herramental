<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/tld_menu.tld" prefix="menu" %>
<%@taglib uri="/WEB-INF/tlds/tld_clasificacion_defectos.tld" prefix="defecto" %>
<%@taglib uri="/WEB-INF/tlds/tld_resultado.tld" prefix="resultados" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Clasificacion defecto</title>
        <jsp:include page="Encabezado.jsp"></jsp:include>
            <!--HTML editor-->
            <script language="javascript" type = "text/javascript" src = "tinyfck/tiny_mce.js"></script>
            <script language="javascript" type = "text/javascript" src = "tinyfck/HTMLEditor.js"></script>
            <script type="text/javascript">
                function registroF() {
                    document.getElementById("btsubmit").disabled = true;
                    document.getElementById("btsubmit").value = "";
                    document.getElementById("puntos").style.display = "block";
                }
            </script>
            <script type = "text/javascript" >
                history.pushState(null, null, 'Clasificacion_defecto.jsp');
                window.addEventListener('popstate', function (event) {
                    history.pushState(null, null, 'Clasificacion_defecto.jsp');
                });
            </script>
        </head>
        <body id="subpage" onload="time()">
            <div id="templatemo_wrapper">
            <menu:MuestraMenu />
            <defecto:MuestraClasificacionDefecto />
        </div>
        <resultados:MuestraResultados/>
        <script src="Interfaz/Calendarios/Js_normal.js"></script>
        <script type="text/javascript" src="Interfaz/Tabs/tabs.js"></script>
        <script src="Interfaz/Acordeon/Js_accordeon.js"></script>
    </body>
</html>