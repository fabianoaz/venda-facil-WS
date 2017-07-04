package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BDConnection {

    private static String user;
    private static String pwd;
    private static String conexao;

    public Connection Conexao() {

        Connection conn = null;

        try {
// ------------------ CONEXAO COM MYSQL ----------------------------------------           
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(BDConnection.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            conexao = "jdbc:mysql://127.0.0.1:3306/venda-facil";
//            user = "root";
//            pwd = "1234";
//            conn = DriverManager.getConnection(conexao, user, pwd);
//------------------------------------------------------------------------------
//------------------ CONEXAO COM POSTGRES --------------------------------------            
            try {
                Class.forName("org.postgresql.Driver"); 
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(BDConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
            conexao = "jdbc:postgresql://localhost:5432/venda-facil";  
            user = "postgres";  
            pwd = "123456";     
            conn = DriverManager.getConnection(conexao, user, pwd);
            System.out.println("Conexão com PostgreSQL realizada com sucesso.");
//------------------------------------------------------------------------------            
        } catch (SQLException e) {
            System.out.println("\nOcorreu o erro =>> " + e.getMessage() + " <<== ao conectar");
        }
        
        return conn;
    }

}
