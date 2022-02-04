package test;

import java.sql.*;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestMySqlJDBC {
    public static void main(String[] args) {
        // Coneccion a base de datos mysql en localhost
        // puede ser String o var: String url = "jdbc:mysql://localhost:3306/test?useSS=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        var url = "jdbc:mysql://localhost:3306/test?useSS=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        try {
            // El siguiente paso no es tanm necesario a dia de hoy pero prodriamos encontrarnoslo (sobre todo en aplicaciones web):
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url,"root", "admin");
            Statement instruccion = conexion.createStatement();
            var sql = "SELECT id_persona, nombre, apellido,email, telefono FROM persona";
            ResultSet resultado = instruccion.executeQuery(sql);
            while(resultado.next()){
                System.out.print("id_persona:" + resultado.getInt("id_persona"));
                System.out.print(" Nombre:" + resultado.getString("nombre"));
                System.out.print(" Apellido:" + resultado.getString("apellido"));
                System.out.print(" Email:" + resultado.getString("email"));
                System.out.print(" Telefono:" + resultado.getString("telefono"));
                System.out.println("");
            }
            resultado.close();
            instruccion.close();
            conexion.close();
        //} catch (ClassNotFoundException ex) {
        //    ex.printStackTrace(System.out);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
    }
}
