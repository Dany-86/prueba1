package datos;

import static datos.Conexion.*;
import domain.Persona;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PersonaDAO {
    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona = ?";
    
    public List<Persona> seleccionar() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;
        List<Persona> personas = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()) {
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                
                persona = new Persona(idPersona, nombre, apellido, email, telefono);
                
                personas.add(persona);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally {
            try { 
                close(rs);
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return personas;
    }
    
    public int insertar(Persona persona){
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                close(stmt);
                close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int actualizar(Persona persona) {
        Connection conn = null;
        PreparedStatement smtm = null;
        int registros = 0;
        try {
            conn = getConnection();
            smtm = conn.prepareStatement(SQL_UPDATE);
            smtm.setString(1, persona.getNombre());
            smtm.setString(2, persona.getApellido());
            smtm.setString(3, persona.getEmail());
            smtm.setString(4, persona.getTelefono());
            smtm.setInt(5, persona.getIdPersona());
            registros = smtm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);  
        } finally {
            try {
                close(smtm);
                close(conn);
            } catch(SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
    
    public int eliminar(Persona persona) {
        Connection conn = null;
        PreparedStatement smtm = null;
        int registros = 0;
        try {
            conn = getConnection();
            smtm = conn.prepareStatement(SQL_DELETE);
            smtm.setInt(1, persona.getIdPersona());
            registros = smtm.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);  
        } finally {
            try {
                close(smtm);
                close(conn);
            } catch(SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }
}
