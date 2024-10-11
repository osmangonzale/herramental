<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Ficha_tecnica.tld" prefix="FichaTec" %>
<%@taglib uri="/WEB-INF/tlds/tld_menu.tld"  prefix="menu"%>
<%@taglib uri="/WEB-INF/tlds/tld_resultado.tld" prefix="resultados" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Herramental | Ficha Tecnica</title>
        <jsp:include page="Encabezado.jsp"></jsp:include>
            <script type="text/javascript">
                function registroU() {
                    document.getElementById("btsubmit").disabled = true;
                    document.getElementById("btsubmit").value = "";
                    document.getElementById("puntos").style.display = "block";
                }
            </script>
        </head>
        <body>
            <div id="templatemo_wrapper">
            <menu:MuestraMenu />
            <FichaTec:Ficha_tecnica/>
        </div>
        <resultados:MuestraResultados/>

        <script>
            // This is an old version, for a more recent version look at
            function maxLengthCheck(object)
            {
                if (object.value.length > object.maxLength)
                    object.value = object.value.slice(0, object.maxLength)
            }
        </script>
        <script type="text/javascript">
            function ConstruirEditor() {
                document.getElementById("Txt_instruccion_seguridad").value = document.getElementById("Txt_editor_html").innerHTML;
                document.getElementById("FormInstruccion").submit();
            }
        </script>
        <script>
            
            function MasivoCargos(ide) {
                var id = "[" + ide + "]";
                var content = document.getElementById("txt_cargs").value;
                if (content.includes(id)) {
                    document.getElementById("txt_cargs").value = content.replace(id, "
                } else {
                    document.getElementById("txt_cargs").value += id;
                }
            }
            function MasivoCorrep(ide) {
                var id = ide ;
                var content = document.getElementById("prueba").value;
                if (content.includes(id)) {
                    document.getElementById("prueba").value = content.replace(id, "");
                } else {
                    document.getElementById("prueba").value += id;
                }
            }
            function Masivo(ide) {
                var id = ide ;
                var content = document.getElementById("Txt_ids").value;
                if (content.includes(id)) {
                    document.getElementById("Txt_ids").value = content.replace(id, "");
                } else {
                    document.getElementById("Txt_ids").value += id;
                }
            }
            
        </script>
    </script>
    <script src="Interfaz/Calendarios/Js_normal.js"></script>
    <script type="text/javascript" src="Interfaz/Tabs/tabs.js"></script>
    <script src="Interfaz/Acordeon/Js_accordeon.js"></script>
</body>
</html>
