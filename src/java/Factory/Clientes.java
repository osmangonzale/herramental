package Factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Clientes {

    public List Clientes() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection("jdbc:sqlserver://172.16.2.116:1433;databaseName=EMP001_FACT", "sa", "plast");
            String query = "SELECT COD,NOM FROM CLIENTES ORDER BY NOM ASC";
            Statement sttm = con.createStatement();
            ResultSet rs = sttm.executeQuery(query);
            List<String> lst_clientes = new ArrayList<String>();
            int count = 0;
            while (rs.next()) {
                lst_clientes.add(count, rs.getString("NOM").toString().trim());
                count++;
            }
            con.close();
            return lst_clientes;
        } catch (Exception ex) {
            return null;
        }
    }
}
