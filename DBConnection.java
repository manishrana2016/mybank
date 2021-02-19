package mybank;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBConnection{
    public Connection dbConnect() throws SQLException, ClassNotFoundException{
        String url = "jdbc:sqlite:lenabankdb.db";
        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection(url, "postgres", "postgres");
        return connection;
    }
    public void closeConnection(Connection con) throws SQLException{
        con.close();
    }
}

/*
class DBConnection{
    public Connection dbConnect() {
        Connection connection = null;
        try{
            String url = "jdbc:sqlite:LenaBankDatabase/lenabankdb.db";
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url, "postgres", "postgres");
        }catch(SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }finally{
            try{
                if(connection!=null){
                    connection.close();
                }
            }catch(SQLException e ){
                System.out.println(e);
            }

        }
        return connection;
    }
}*/
