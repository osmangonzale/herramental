<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/tld_menu.tld" prefix="menu" %>
<%@taglib uri="/WEB-INF/tlds/tld_herramental.tld" prefix="herramental" %>
<%@taglib uri="/WEB-INF/tlds/tld_resultado.tld" prefix="resultados" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Herramental</title>
        <jsp:include page="Encabezado.jsp"></jsp:include>
            <!--HTML editor-->
            <script language="javascript" type = "text/javascript" src = "tinyfck/tiny_mce.js"></script>
            <script language="javascript" type = "text/javascript" src = "tinyfck/HTMLEditor.js"></script>
            <script type="text/javascript" language="javascript">
                function agregar(check, id) {
                    id = "[" + id + "]";
                    if (check.checked) {
                        var campo_maquinas = document.getElementById("id_maquinas").value;
                        campo_maquinas = campo_maquinas + id;
                        document.getElementById("id_maquinas").value = campo_maquinas;
                    } else {
                        var campo_maquinas = document.getElementById("id_maquinas").value;
                        campo_maquinas = campo_maquinas.replace(id, "");
                        document.getElementById("id_maquinas").value = campo_maquinas;
                    }
                }
                function Movimiento() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementsByName("txt_descripcion").value = htmleditor;
                    document.getElementById("formDes").submit();
                }
                function MovimientoM() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementsByName("txt_descripcionM").value = htmleditor;
                    document.formM.submit();
                }
                function SolucionP() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementsByName("txt_solucion").value = htmleditor;
                    document.formS.submit();
                }
                function SolucionPM() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementsByName("txt_solucion").value = htmleditor;
                    document.formSM.submit();
                }
                function CorreoM() {
                    swal({
                        type: "warning",
                        title: "Enviando!",
                        text: "El correo se esta enviando por favor espere",
                        timer: 2000,
                        showConfirmButton: false
                    },
                            function () {
                                document.formMailM.submit();
                            }
                    );
                }
                function CorreoP() {
                    swal({
                        type: "warning",
                        title: "Enviando!",
                        text: "El correo se esta enviando por favor espere",
                        timer: 2000,
                        showConfirmButton: false
                    },
                            function () {
                                document.formMailP.submit();
                            }
                    );
                }

            </script>
            <script type="text/javascript">
                function registroH() {
                    document.getElementById("btsubmit").disabled = true;
                    document.getElementById("btsubmit").value = "";
                    document.getElementById("puntos").style.display = "block";
                }
                function registroM() {
                    document.getElementById("botonM").disabled = true;
                    document.getElementById("botonM").value = "";
                    document.getElementById("puntosM").style.display = "block";
                }
                function MailM() {
                    document.getElementById("botonMa").disabled = true;
                    document.getElementById("botonMa").value = "";
                    document.getElementById("puntosMa").style.display = "block";
                }
                function registroP() {
                    document.getElementById("botonP").disabled = true;
                    document.getElementById("botonP").value = "";
                    document.getElementById("puntosP").style.display = "block";
                }
                function MailP() {
                    document.getElementById("botonPe").disabled = true;
                    document.getElementById("botonPe").value = "";
                    document.getElementById("puntosPe").style.display = "block";
                }
            </script>
            <script type = "text/javascript" >
                history.pushState(null, null, 'herramental.jsp');
                window.addEventListener('popstate', function (event) {
                    history.pushState(null, null, 'herramental.jsp');
                });
            </script>
            <script>
                CKEDITOR.replace("editor");
            </script>
        </head>
        <body id="subpage" onload="time()">
            <div id="templatemo_wrapper">
            <menu:MuestraMenu />
            <herramental:MuestraHerramental />
        </div>
        <resultados:MuestraResultados/> 
        <script src="Interfaz/Calendarios/Js_normal.js"></script>
        <script type="text/javascript" src="Interfaz/Tabs/tabs.js"></script>
    </body>
</html>
