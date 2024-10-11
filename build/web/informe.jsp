<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/tld_menu.tld" prefix="menu" %>
<%@taglib uri="/WEB-INF/tlds/tld_informe.tld" prefix="informe" %>
<%@taglib uri="/WEB-INF/tlds/tld_resultado.tld" prefix="resultados" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Informe</title>
        <jsp:include page="Encabezado.jsp"></jsp:include>
            <!--HTML editor-->
            <script language="javascript" type = "text/javascript" src = "tinyfck/tiny_mce.js"></script>
            <script language="javascript" type = "text/javascript" src = "tinyfck/HTMLEditor.js"></script>
            <!--Gantt-->
            <link href="Interfaz/Gantt/css/style.css" type="text/css" rel="stylesheet">
            <script type="text/javascript">
                function registroI() {
                    document.getElementById("btsubmit").disabled = true;
                    document.getElementById("btsubmit").value = "";
                    document.getElementById("puntos").style.display = "block";
                }
            </script>
            <script type="text/javascript" language="javascript">
                function Informe() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementsByName("txt_descripcion").value = htmleditor;
                    document.formI.submit();
                }
            </script>
            <script type="text/javascript" language="javascript">
                function nombreGantt() {
                    var c = document.getElementById("namegantt").innerHTML;
                    if (c.length > 20) {
                        document.getElementById("namegantt").contentEditable = false;
                    }

                }
            </script>
            <script type = "text/javascript" >
                history.pushState(null, null, 'informe.jsp');
                window.addEventListener('popstate', function (event) {
                    history.pushState(null, null, 'informe.jsp');
                });
            </script>
        </head>
        <body id="subpage" onload="time()">
            <div id="templatemo_wrapper">
            <menu:MuestraMenu />
            <informe:MuestraInforme />
        </div>
        <resultados:MuestraResultados/>
        <script src="Interfaz/Gantt/js/jquery.min.js"></script>
        <script src="Interfaz/Gantt/js/jquery.fn.gantt.js"></script>
        <script src="Interfaz/Calendarios/Js_normal.js"></script>
        <script src="Interfaz/Calendarios/Js_range.js"></script>
        <script type="text/javascript" src="Interfaz/Tabs/tabs.js"></script>
    </body>
</html>
