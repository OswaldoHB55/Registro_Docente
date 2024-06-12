package interfazgrafica;

import entidadesdenegocio.Docente;
import logicadenegocio.DocenteBL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MantenimientoDocente extends JFrame{
    private JTextField txtId;
    private JTextField txtCodigo;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtEmail;
    private JComboBox cbCarrera;
    private JComboBox cbGrupo;
    private JButton btnNuevo;
    private JButton btnGuardar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnCancelar;
    private JButton btnSalir;
    private JRadioButton rdbId;
    private JRadioButton rdbApellido;
    private JRadioButton rdbCarrera;
    private JTextField txtCriterio;
    private JButton btnBuscar;
    private JTable jtDocentes;
    private JPanel jpPrincipal;
    private ButtonGroup criterioBusqueda;

    //Instancias propias
    ArrayList<Docente> listaDocentess;
    Docente docent;
    DocenteBL docenteBL = new DocenteBL();

    public static void main(String[] args) throws SQLException {
        new MantenimientoDocente();
    }

    public MantenimientoDocente() throws SQLException{

        inicializar();
        actualizarForm();

        btnSalir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.exit(0);
            }
        });
        btnCancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnNuevo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                txtCodigo.setEnabled(true);
                txtNombre.setEnabled(true);
                txtApellido.setEnabled(true);
                txtEmail.setEnabled(true);
                cbCarrera.setEnabled(true);
                cbGrupo.setEnabled(true);
                txtCodigo.grabFocus();
                btnGuardar.setEnabled(true);
                btnNuevo.setEnabled(false);
                btnCancelar.setEnabled(true);
            }
        });
        btnGuardar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                super.mouseClicked(e);
                docent = new Docente();
                docent.setCodigo(txtCodigo.getText());
                docent.setNombre(txtNombre.getText());
                docent.setApellido(txtApellido.getText());
                docent.setEmail(txtEmail.getText());
                docent.setCarrera(cbCarrera.getSelectedItem().toString());
                docent.setGrupo(cbGrupo.getSelectedItem().toString());
                try {
                    docenteBL.guardar(docent);
                    JOptionPane.showMessageDialog(null,"Se guardo exitosamente");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnModificar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                docent = new Docente();
                docent.setId(Integer.parseInt(txtId.getText()));
                docent.setCodigo(txtCodigo.getText());
                docent.setNombre(txtNombre.getText());
                docent.setApellido(txtApellido.getText());
                docent.setEmail(txtEmail.getText());
                docent.setCarrera(cbCarrera.getSelectedItem().toString());
                docent.setGrupo(cbGrupo.getSelectedItem().toString());
                try {
                    docenteBL.modificar(docent);
                    JOptionPane.showMessageDialog(null,"Se modifico exitosamente");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        jtDocentes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int fila = jtDocentes.getSelectedRow();
                txtId.setText(jtDocentes.getValueAt(fila, 0).toString());
                txtCodigo.setText(jtDocentes.getValueAt(fila, 1).toString());
                txtNombre.setText(jtDocentes.getValueAt(fila, 2).toString());
                txtApellido.setText(jtDocentes.getValueAt(fila, 3).toString());
                txtEmail.setText(jtDocentes.getValueAt(fila, 4).toString());
                cbCarrera.setSelectedItem(jtDocentes.getValueAt(fila, 5));
                cbGrupo.setSelectedItem(jtDocentes.getValueAt(fila, 6));

                txtCodigo.setEnabled(true);
                txtNombre.setEnabled(true);
                txtApellido.setEnabled(true);
                txtEmail.setEnabled(true);
                cbCarrera.setEnabled(true);
                cbGrupo.setEnabled(true);
                txtCodigo.grabFocus();

                btnNuevo.setEnabled(false);
                btnModificar.setEnabled(true);
                btnEliminar.setEnabled(true);
                btnCancelar.setEnabled(true);
            }
        });
        btnEliminar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                docent = new Docente();
                docent.setId(Integer.parseInt(txtId.getText()));
                try {
                    docenteBL.eliminar(docent);
                    JOptionPane.showMessageDialog(null, "Se elimino correctamente");
                    actualizarForm();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnBuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (txtCriterio.getText().equals("") || (!rdbId.isSelected() && !rdbApellido.isSelected() && !rdbCarrera.isSelected())){
                    JOptionPane.showMessageDialog(null, "Selecciones un criterio de busqueda o escriva el  valor");
                }
                docent = new Docente();

                if( rdbId.isSelected ()){
                    docent.setId(Integer.parseInt( txtCriterio.getText()));
                    try{
                        llenarTabla(docenteBL.obtenerDatosFiltrados(docent));
                    } catch ( SQLException  ex ) {
                        throw new  RuntimeException(ex);
                    }
                }
                if ( rdbApellido.isSelected()){
                    docent.setApellido(txtCriterio.getText());
                    try {
                        llenarTabla (docenteBL.obtenerDatosFiltrados (docent));
                    } catch ( SQLException  ex ) {
                        throw new  RuntimeException ( ex );
                    }
                }

                if( rdbCarrera . isSelected ()){
                    docent.setCarrera ( txtCriterio . getText ());
                    try {
                        llenarTabla ( docenteBL.obtenerDatosFiltrados (docent));
                    } catch ( SQLException  ex ) {
                        throw new RuntimeException ( ex );
                    }
                }
            }
        });
    }

    void inicializar(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(600,700);
        setTitle("Control de estudiantes");
        setLayout(null);//Estas dos nUlas sirven parar centrar
        setLocationRelativeTo(null);
        setResizable(false);

        criterioBusqueda = new ButtonGroup();
        criterioBusqueda.add(rdbId);
        criterioBusqueda.add(rdbApellido);
        criterioBusqueda.add(rdbCarrera);

        setContentPane(jpPrincipal);
        setVisible(true);
    }
    void llenarTabla(ArrayList<Docente> docentes){
        Object[] objects = new Object[7];//Depende de la cantidad de campos de la tabla
        listaDocentess = new ArrayList<>();
        String[] encabezado = {"ID", "CODIGO", "NOMBRE", "APELLIDO", "EMAIL", "CARRERAR", "GRUPO"};
        DefaultTableModel tm = new DefaultTableModel(null, encabezado);
        listaDocentess.addAll(docentes);
        for(Docente item : listaDocentess){
            objects[0] = item.getId();
            objects[1] = item.getCodigo();
            objects[2] = item.getNombre();
            objects[3] = item.getApellido();
            objects[4] = item.getEmail();
            objects[5] = item.getCarrera();
            objects[6] = item.getGrupo();

            tm.addRow(objects);
        }
        jtDocentes.setModel(tm);
    }
    void actualizarForm() throws SQLException {
        txtId.setText("");
        txtCodigo.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        cbCarrera.setSelectedIndex(0);

        txtId.setEnabled(false);
        txtCodigo.setEnabled(false);
        txtNombre.setEnabled(false);
        txtApellido.setEnabled(false);
        txtEmail.setEnabled(false);
        cbCarrera.setEnabled(false);
        cbGrupo.setEnabled(false);


        btnNuevo.setEnabled(true);
        btnGuardar.setEnabled(false);
        btnModificar.setEnabled(false);
        btnEliminar.setEnabled(false);

        txtCriterio.setText("");
        criterioBusqueda.clearSelection();

        llenarTabla(docenteBL.obtenerTodos());
    }
}
