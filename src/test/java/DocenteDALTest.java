import accesoadatos.DocenteDAL;
import entidadesdenegocio.Docente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class DocenteDALTest {
    @Test

    public void guardarTest() throws SQLException {
        Docente docent = new Docente();
        docent.setCodigo("PH2201005");
        docent.setNombre("Raquel Tatiana");
        docent.setApellido("Perez Esquivel");
        docent.setEmail("karla@gmail.com");
        docent.setCarrera("TIDS");
        docent.setGrupo("01");

        int esperando = 1;
        int actual = DocenteDAL.guardar(docent);
        Assertions.assertEquals(esperando, actual);

    }
    @Test
    public void modificarTest() throws SQLException{
        Docente docent = new Docente();
        docent.setCodigo("PH2201005");
        docent.setNombre("Raquel Tatiana");
        docent.setApellido("Perez Esquivel");
        docent.setEmail("karla@gmail.com");
        docent.setCarrera("TIDS");
        docent.setGrupo("01");


        int esperando = 1;
        int actual = DocenteDAL.modificar(docent);
        Assertions.assertEquals(esperando, actual);
    }

    @Test
    public void eliminarTest() throws SQLException{

        Docente docent = new Docente();
        docent.setId(2);


        int esperando = 1;
        int actual = DocenteDAL.eliminar(docent);
        Assertions.assertEquals(esperando, actual);
    }
    @Test
    public void obtenerTodosTest() throws SQLException{
        int actual = DocenteDAL.obtenerTodos().size();
        Assertions.assertNotEquals(0, actual);

    }
    @Test
    public void obtenerDatosFiltradosTest() throws SQLException{
        Docente docent = new Docente();
        docent.setCarrera("TIDS");
        docent.setId(0);
        docent.setApellido("");

        int actual = DocenteDAL.obtenerDatosFiltrados(docent).size();
        Assertions.assertNotEquals(0, actual);





    }
}

