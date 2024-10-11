<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@taglib uri="/WEB-INF/tlds/tld_menu.tld" prefix="menu" %>
<%@taglib uri="/WEB-INF/tlds/tld_maquina.tld" prefix="maquina" %>
<%@taglib uri="/WEB-INF/tlds/tld_resultado.tld" prefix="resultados" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Maquina</title>
        <jsp:include page="Encabezado.jsp"></jsp:include>
            <script type="text/javascript">
                function registroM() {
                    document.getElementById("btsubmit").disabled = true;
                    document.getElementById("btsubmit").value = "";
                    document.getElementById("puntos").style.display = "block";
                }
            </script>
            <script type="text/javascript">
                function estado(est, idM, fil) {
                    var f = new Date();
                    if (est == 2) {
                        swal({
                            title: "Seguro que desea cambiar el estado de la maquina?",
                            text: "<b style='color:#CAA427;'>Disponible</b>",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#CAA427",
                            confirmButtonText: "Aceptar",
                            cancelButtonText: "Cancelar",
                            closeOnConfirm: false,
                            closeOnCancel: false,
                            html: true
                        },
                                function (isConfirm) {
                                    if (isConfirm) {
                                        location.href = "Maquina?opc=4&idM=" + idM + "&est=" + est + "&txt_bus=" + fil + "";
                                    } else {
                                        location.href = "Maquina?opc=1&idM=0&txt_bus=" + fil + "";
                                    }
                                });
                    } else if (est == 3) {
                        swal({
                            title: "Seguro que desea cambiar el estado de la maquina?",
                            text: "<b style='color:red;'>Parada</b><br /><input type='text' id='des' placeholder='descripcion' style='display:block'>",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#B72E27",
                            confirmButtonText: "Aceptar",
                            cancelButtonText: "Cancelar",
                            closeOnConfirm: false,
                            closeOnCancel: false,
                            html: true
                        },
                                function (isConfirm) {
                                    if (isConfirm) {
                                        var des = document.getElementById("des").value;
                                        location.href = "Movimiento?opc=2&idM=" + idM + "&txt_descripcion=" + des + "&txt_fecha=" + f.getFullYear() + "-" + (((f.getMonth() + 1) < 10) ? ("0" + (f.getMonth() + 1)) : ((f.getMonth() + 1))) + "-" + ((f.getDate() < 10) ? ("0" + f.getDate()) : (f.getDate())) + "&txt_bus=" + fil + "";
                                    } else {
                                        location.href = "Maquina?opc=1&idM=0&txt_bus=" + fil + "";
                                    }
                                }
                        );
                    } else if (est == 0) {
                        swal({
                            title: "Seguro que desea cambiar el estado de la maquina?",
                            text: "<b style='color:#b3b1b1;'>In-activa</b>",
                            type: "warning",
                            showCancelButton: true,
                            confirmButtonColor: "#b3b1b1",
                            confirmButtonText: "Aceptar",
                            cancelButtonText: "Cancelar",
                            closeOnConfirm: false,
                            closeOnCancel: false,
                            html: true
                        },
                                function (isConfirm) {
                                    if (isConfirm) {
                                        location.href = "Maquina?opc=4&idM=" + idM + "&est=" + est + "&txt_bus=" + fil + "";
                                    } else {
                                        location.href = "Maquina?opc=1&idM=0&txt_bus=" + fil + "";
                                    }
                                });
                    }


                }
            </script>
<!--            <script type = "text/javascript" >
                history.pushState(null, null, 'maquina.jsp');
                window.addEventListener('popstate', function (event) {
                    history.pushState(null, null, 'maquina.jsp');
                });
            </script>-->
        </head>
        <body id="subpage" onload="time()">
            <div id="templatemo_wrapper">
            <menu:MuestraMenu />
            <maquina:MuestraMaquina /> 
        </div>
        <resultados:MuestraResultados/>
    </body>
</html>