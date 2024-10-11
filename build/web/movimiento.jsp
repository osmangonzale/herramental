<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/tld_menu.tld" prefix="menu" %>
<%@taglib uri="/WEB-INF/tlds/tld_movimiento.tld" prefix="movimiento" %>
<%@taglib uri="/WEB-INF/tlds/tld_resultado.tld" prefix="resultados" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Movimiento</title>
        <jsp:include page="Encabezado.jsp"></jsp:include>
            <!--HTML editor-->
            <script language="javascript" type = "text/javascript" src = "tinyfck/tiny_mce.js"></script>
            <script language="javascript" type = "text/javascript" src = "tinyfck/HTMLEditor.js"></script>
            <script type="text/javascript">
                function Movimiento() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementById("descripcion-id").value = htmleditor;
                    document.formDes.submit();
                }
                function MovimientoC() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementById("descripcion-id").value = htmleditor;
                    document.formM.submit();
                }
                function MovimientoM() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementById("descripcion-id").value = htmleditor;
                    document.formM.submit();
                }
                function Herramental(id_movimiento) {
                    var lista = document.getElementById("selectTM-id");
                    var idM = id_movimiento.toString().split("-");
                    var id = idM[1]
                    for (var i = 0; i < lista.length; i++) {
                        var opt = lista[i];
                        if (opt.value == id) {
                            opt.disabled = true;
                        } else {
                            opt.disabled = false;
                        }
                    }
                }
                function Pendiente() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementsByName("txt_pendiente").value = htmleditor;
                    document.formP.submit();
                }
                function PendienteM() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementsByName("txt_pendienteM").value = htmleditor;
                    document.formPM.submit();
                }
                function SolucionP() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementsByName("txt_solucion").value = htmleditor;
                    document.formS.submit();
                }
                function SolucionPM() {
                    var htmleditor = document.getElementsByName("HTML_Editor").innerHTML;
                    document.getElementsByName("txt_solucionM").value = htmleditor;
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
                var statsend = false;
                function checkSubmit() {
                    if (!statsend) {
                        statsend = true;
                        return true;
                    } else {
                        swal({
                            type: "warning",
                            title: "Guardando!",
                            text: "se esta guardando la informacion por favor espere",
                            timer: 2000,
                            showConfirmButton: false
                        });
                        return false;
                    }
                }
            </script>
            <script type="text/javascript">
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
            <script type="text/javascript">
                function Firma(movS) {
                    var mov = movS.value;
                    var id = document.getElementById("idMVC").value;
                    if (mov === "2" || mov === "3") {
                        document.getElementById("Confirmar_movimiento").style.visibility = "visible";
                        document.getElementById("bloq").style.display = "block";
                        document.getElementById("idMVF").value = id;
                        document.getElementById("idTMV").value = mov;
                    } else {
                        document.getElementById("Confirmar_movimiento").style.visibility = "hidden";
                        document.getElementById("bloq").style.display = "none";
                    }
                }
            </script>
            <script type = "text/javascript" >
                function editorTemp(){
                    document.getElementById('txt_descripcion').value = document.getElementById('Td_descripcion').innerHTML;
                }
                function VinetasTd(){
                    document.getElementById('Td_descripcion').innerHTML += "<ul><li></li></ul>";
                }
            </script>
            <script type = "text/javascript" >
                history.pushState(null, null, 'movimiento.jsp');
                window.addEventListener('popstate', function (event) {
                    history.pushState(null, null, 'movimiento.jsp');
                });
            </script>
        </head>
        <body id="subpage">
            <div id="templatemo_wrapper">
            <menu:MuestraMenu />
            <movimiento:MuestraMovimiento/>
        </div>
        <resultados:MuestraResultados/>
        <script src="Interfaz/Calendarios/Js_normal.js"></script>
        <script type="text/javascript" src="Interfaz/Tabs/tabs.js"></script>
        <script src="Interfaz/Acordeon/Js_accordeon.js"></script>
    </body>
</html>
