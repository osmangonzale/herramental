function Filtrar()
{
    var table = document.getElementById('resultados');
    var filtro = document.getElementById('Txt_filtro').value.toLowerCase();
    filtro = filtro.trim(filtro);
    var cellsOfRow="";
    var found=false;
    var compareWith="";
    // Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < table.rows.length; i++)
    {
        cellsOfRow = table.rows[i].getElementsByTagName('td');
        found = false;
        // Recorremos todas las celdas
        if (filtro == "") {
            found = false;
        }else{
            for (var j = 0; j < cellsOfRow.length && !found; j++)
            {
                compareWith = cellsOfRow[j].innerHTML.toLowerCase();
                // Buscamos el texto en el contenido de la celda
                if (filtro.length == 0 || (compareWith.indexOf(filtro) > -1))
                {
                    found = true;
                }
            }
        }
        if(found)
        {
            table.rows[i].style.display = '';
        } else {
            if (filtro.length == 0) {
                // i starts from 1 to skip table header row
                    if (i > 10)
                        table.rows[i].style.display = 'none';
                    else
                        table.rows[i].style.display = '';
                       // table.rows[i].style.display = '';
            }else{
                // si no ha encontrado ninguna coincidencia, esconde la fila de la tabla
                table.rows[i].style.display = 'none';
            }
        }
    }
}
