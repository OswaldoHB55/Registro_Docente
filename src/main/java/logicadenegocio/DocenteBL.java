package logicadenegocio;

import accesoadatos.DocenteDAL;
import entidadesdenegocio.Docente;

import java.sql.SQLException;
import java.util.ArrayList;

public class DocenteBL {
    public int guardar(Docente docente) throws SQLException {
        return DocenteDAL.guardar(docente);
    }

    public int modificar(Docente docente) throws SQLException {
        return DocenteDAL.modificar(docente);
    }
    public int eliminar(Docente docente) throws SQLException {
        return  DocenteDAL.eliminar(docente);
    }
    public ArrayList<Docente> obtenerTodos() throws SQLException {
        return  DocenteDAL.obtenerTodos();
    }

    public ArrayList<Docente> obtenerDatosFiltrados(Docente docente) throws SQLException {
        return  DocenteDAL.obtenerDatosFiltrados(docente);
    }
}
