<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/tld_familia_producto.tld" prefix="familia" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Catalogo defectos</title>
        <link type="image/png" href="Interfaz/Contenido/images/herramental.ico" rel="icon" >
        <link href="Interfaz/Contenido/Css/CSS_Menu.css" rel="stylesheet" type="text/css" />
        <link href="Interfaz/Contenido/Css/CSS_Principal.css" rel="stylesheet" type="text/css" />
        <link href="Interfaz/Validacion/StyleSheetLiveValidation.css" rel="stylesheet" type="text/css" />
        <link href="Interfaz/Contenido/fontawesome/css/all.css" rel="stylesheet" type="text/css" />

        <!-- Acordeon -->
        <link rel="stylesheet" href="Interfaz/Acordeon/Css_accordeon.css">
        <!-- JQuery desplega menu -->
        <script type="text/javascript" src="Interfaz/Contenido/Scripts/JS_Menu_Min.js"></script>
        <!-- JQuery alertas -->
        <script type="text/javascript" src="Interfaz/Alertas/dist/sweetalert.min.js"></script>
        <link href="Interfaz/Alertas/dist/sweetalert.css" rel="stylesheet" type="text/css"/>
        <!-- JQuery desplega menu -->
        <script type="text/javascript" src="Interfaz/Paginas/paging.js"></script>
        <script type="text/javascript" src="Interfaz/Validacion/LiveValidation.js"></script>
        <!-- Menu flotante -->
        <script src="Interfaz/Contenido/Scripts/jquery-1.10.2.js"></script>
        <!-- JavaScript calendarios -->
        <script type="text/javascript" src="Interfaz/Calendarios/moment.js"></script>
        <script type="text/javascript" src="Interfaz/Calendarios/pikaday.js"></script>
        <link href="Interfaz/Calendarios/pikaday.css" rel="stylesheet" type="text/css"/>
        <!-- JavaScript html editor -->
        <style type="text/css" media="screen">@import "Interfaz/Tabs/tabs.css";</style>
        <!-- JavaScript desplega menu -->


        <script type="text/javascript">
            function checkKeyCode(evt)
            {
                var evt = (evt) ? evt : ((event) ? event : null);
                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
                if (event.keyCode == 116)
                {
                    evt.keyCode = 0;
                    return false
                }
            }
            document.onkeydown = checkKeyCode;
        </script>

        <style type="text/css">
            html, body
            {
                font-family: verdana,sans-serif;
                margin: 0;
                padding: 5px;
            }
        </style>
        <script>
            function printSection(el) {
                var getFullContent = document.body.innerHTML;
                var printsection = document.getElementById(el).innerHTML;
                document.body.innerHTML = printsection;
                window.print();
                document.body.innerHTML = getFullContent;
            }
        </script>





        <!--HTML editor-->
        <script language="javascript" type = "text/javascript" src = "tinyfck/tiny_mce.js"></script>
        <script language="javascript" type = "text/javascript" src = "tinyfck/HTMLEditor.js"></script>
        <script type="text/javascript">
            function registroC() {
                document.getElementById("btsubmit").disabled = true;
                document.getElementById("btsubmit").value = "";
                document.getElementById("puntos").style.display = "block";
            }
            function Clasificacion() {
                var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                document.getElementById("descripcion-id").value = htmleditor;
                document.formDes.submit();
            }
        </script>
    </head>
    <body id="subpage">
        <div id="templatemo_wrapper">
            <familia:MuestraCatalogo />
        </div>
        <script src="Interfaz/Calendarios/Js_normal.js"></script>
        <script type="text/javascript" src="Interfaz/Tabs/tabs.js"></script>
        <script src="Interfaz/Acordeon/Js_accordeon.js"></script>
    </body>
</html>
