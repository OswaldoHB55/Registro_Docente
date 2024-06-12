package accesoadatos;


import entidadesdenegocio.Docente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DocenteDAL {

    // método que permite guardar un nuevo registro
    public static int guardar(Docente docente) throws SQLException {
        int result = 0;
        try {
            String sql = "INSERT INTO Estudiantes(Codigo, Nombre, Apellido, Email, Carrera, Grupo) VALUES(?, ?, ?, ?, ?, ?)";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setString(1, docente.getCodigo());
            ps.setString(2, docente.getNombre());
            ps.setString(3, docente.getApellido());
            ps.setString(4, docente.getEmail());
            ps.setString(5, docente.getCarrera());
            ps.setString(6, docente.getGrupo());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite modificar un registro existente
    public static int modificar(Docente docente) throws SQLException {
        int result = 0;
        try {
            String sql = "UPDATE Estudiantes SET Codigo = ?,  Nombre = ?, Apellido = ?, Email = ?, Carrera = ? , Grupo = ?  WHERE Id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setString(1, docente.getCodigo());
            ps.setString(2, docente.getNombre());
            ps.setString(3, docente.getApellido());
            ps.setString(4, docente.getEmail());
            ps.setString(5, docente.getCarrera());
            ps.setString(6, docente.getGrupo());
            ps.setInt(7, docente.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    // método que permite eliminar un registro existente
    public static int eliminar(Docente docente) throws SQLException {
        int result = 0;
        try {
            String sql = "DELETE FROM Docente WHERE Id = ?";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ps.setInt(1, docente.getId());
            result = ps.executeUpdate();
            conexion.close();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(0);
        }
        return result;
    }

    // método que muestra todos los registros de la tabla
    public static ArrayList<Docente> obtenerTodos() throws SQLException {
        ArrayList<Docente> lista = new ArrayList<>();
        Docente docente;
        try {
            String sql = "SELECT Id, Codigo, Nombre, Apellido, Email, Carrera, Grupo FROM Docente";
            Connection conexion = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(conexion, sql);
            ResultSet rs = ComunDB.obtenerResultSet(ps);
            while (rs.next()) {
                docente = new Docente(rs.getInt(1), rs.getString(2),
                        rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                lista.add(docente);
            }
            conexion.close();
            ps.close();
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    // método para consultar mediante criterios
    public static ArrayList<Docente> obtenerDatosFiltrados(Docente docente) throws SQLException {
        ArrayList<Docente> lista = new ArrayList<>();
        Docente est;
        try {
            String sql = "SELECT id, codigo, nombre, apellido, Email, carrera, Grupo FROM Docente WHERE id = ? or apellido like'%" + docente.getApellido() + "%' or carrera like'%" + docente.getCarrera() + "%'";
            Connection connection = ComunDB.obtenerConexion();
            PreparedStatement ps = ComunDB.crearPreparedStatement(connection, sql);

            ps.setInt(1, docente.getId());
            ResultSet rs = ComunDB.obtenerResultSet(ps);
            while (rs.next()) {
                est = new Docente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
                lista.add(est);
            }
            connection.close();
            ps.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}

