<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/tld_menu.tld" prefix="menu" %>
<%@taglib uri="/WEB-INF/tlds/tld_tipo_maquina.tld" prefix="tipo_maquina" %>
<%@taglib uri="/WEB-INF/tlds/tld_resultado.tld" prefix="resultados" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Tipo Maquina</title>
        <jsp:include page="Encabezado.jsp"></jsp:include>
            <script type="text/javascript">
                function registroTP() {
                    document.getElementById("btsubmit").disabled = true;
                    document.getElementById("btsubmit").value = "";
                    document.getElementById("puntos").style.display = "block";
                }
            </script>
            <script type = "text/javascript" >
                history.pushState(null, null, 'tipo_maquina.jsp');
                window.addEventListener('popstate', function (event) {
                    history.pushState(null, null, 'tipo_maquina.jsp');
                });
            </script>
        </head>
        <body id="subpage" onload="time()">
            <div id="templatemo_wrapper">
            <menu:MuestraMenu />
            <tipo_maquina:MuestraResultados />
        </div>
        <resultados:MuestraResultados/>
    </body>
</html>
