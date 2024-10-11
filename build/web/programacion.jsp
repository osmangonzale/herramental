<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/tld_menu.tld" prefix="menu" %>
<%@taglib uri="/WEB-INF/tlds/tld_programacion.tld" prefix="programacion" %>
<%@taglib uri="/WEB-INF/tlds/tld_resultado.tld" prefix="resultados" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Programacion</title>
        <jsp:include page="Encabezado.jsp"></jsp:include>
            <script language="javascript" type = "text/javascript" src = "tinyfck/tiny_mce.js"></script>
            <script language="javascript" type = "text/javascript" src = "tinyfck/HTMLEditor.js"></script>
            <script type="text/javascript">
                function registroP() {
                    document.getElementById("btsubmit").disabled = true;
                    document.getElementById("btsubmit").value = "";
                    document.getElementById("puntos").style.display = "block";
                }
                function registroNP() {
                    document.getElementById("btsubmit").disabled = true;
                    document.getElementById("btsubmit").value = "";
                    document.getElementById("puntos").style.display = "block";
                }
                function Novedad() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementsByName("txt_descripcionNP").value = htmleditor;
                    document.formNP.submit();
                }
                function Programacion() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementsByName("txt_descripcion").value = htmleditor;
                    document.formP.submit();
                }
            </script>
            <script type = "text/javascript" >
                history.pushState(null, null, 'programacion.jsp');
                window.addEventListener('popstate', function (event) {
                    history.pushState(null, null, 'programacion.jsp');
                });
            </script>
            <script>
                function ejectForm(){
                    document.getElementById("form_anio").submit();
                }
            </script>
        </head>
        <body id="subpage" onload="time()">
            <div id="templatemo_wrapper">
            <menu:MuestraMenu />
            <programacion:MuestraProgramacion />
        </div>
        <resultados:MuestraResultados/>
        <script src="Interfaz/Calendarios/Js_normal.js"></script>
        <script type="text/javascript" src="Interfaz/Tabs/tabs.js"></script>
        <script src="Interfaz/Acordeon/Js_accordeon.js"></script>
    </body>
</html>