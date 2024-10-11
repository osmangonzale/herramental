package Mail;

import java.security.MessageDigest;

public class Control_encriptacion {
      public static String md5(String clear) throws Exception {
    MessageDigest md = MessageDigest.getInstance("MD5");
    byte[] b = md.digest(clear.getBytes());
    int size = b.length;
    StringBuilder sb = new StringBuilder(size);
    //algoritmo y arreglo md5
        for (int i = 0; i < size; i++) {
            int u = b[i] & 255;
                if (u < 16) {
                    sb.append("0").append(Integer.toHexString(u));
                }
               else {
                    sb.append(Integer.toHexString(u));
               }
           }
      //clave encriptada
      return sb.toString();
    }
}
