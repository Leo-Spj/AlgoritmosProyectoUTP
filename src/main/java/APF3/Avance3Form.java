/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package APF3;

import APF3.P3.*;
import APF3.P1.*;

import APF3.P2.Hash;
import APF3.P4.Main;
import CONTROL.ControlEstanteria;

import CONTROL.ListaEnlazadaDoble;
import MODELO.CLASIFICACION.Novela;
import CONTROL.Nodo;
import MODELO.Libro;
import MODELO.Persona_2;
import MODELO.Proceso;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import CONTROL.*;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Leo prueba git ssh
 */
public class Avance3Form extends javax.swing.JFrame {

    /**
     * Creates new form Biblioteca
     */
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    DefaultTableModel model = new DefaultTableModel();
    ControlEstanteria<Novela> estanteriaNovelas = new ControlEstanteria();
    ListaEnlazadaDoble<Novela> listalibro = new ListaEnlazadaDoble();
    PersonaColaPrioridadLinkedList colaPersonas = new PersonaColaPrioridadLinkedList();
    Hash hash = new Hash(100);
    Recursividad listaLibrosRecursivo = new Recursividad();



    public Avance3Form() {

        initComponents();
        datosPruebaEnTabla();
        guardarEnFichero(estanteriaNovelas);
        llenaListaEnlazadaDoble();

        tblLibrosEstante.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent presionar) {
                JTable tabla = (JTable) presionar.getSource();
                Point point = presionar.getPoint();
                int row = tabla.rowAtPoint(point);
                if (presionar.getClickCount() == 1) {
                    txtFrase.setText(tblLibrosEstante.getValueAt(tblLibrosEstante.getSelectedRow(), 0).toString());
                }
            }
        });

    }

    public void guardarEnFichero(ControlEstanteria estanteria) {

        try (PrintWriter pw = new PrintWriter(new File("src/main/java/estanteriaNovelas.json"))) {

            pw.write(gson.toJson(estanteria.getListaLibros()));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rellenarTabla() {
        Libro[] novelas = estanteriaNovelas.getListaLibros();
        for (int i = 0; i < novelas.length; i++) {
            Novela novela = (Novela) novelas[i];
            model.addRow(new Object[]{
                novela.getTitulo(),
                novela.getAutor(),
                novela.getAnioPublicacion(),
                novela.getISBN(),
                novela.getGenero()
            });
        }
    }

    public void rellenarTabla2(ListaEnlazadaDoble lista) {
        model.setRowCount(0);
        Nodo actual = lista.getCabeza();
        while (actual != null) {
            Novela novela = actual.getNovela();
            model.addRow(new Object[]{
                novela.getTitulo(),
                novela.getAutor(),
                novela.getAnioPublicacion(),
                novela.getISBN(),
                novela.getGenero()
            });
            actual = actual.getSiguiente();
        }
    }

    public void llenaListaEnlazadaDoble() {
        for (int row = 0; row < model.getRowCount(); row++) {
            String tempTitulo = model.getValueAt(row, 0).toString();
            String tempAutor = model.getValueAt(row, 1).toString();
            int tempAño = Integer.parseInt(model.getValueAt(row, 2).toString());
            long tempIsbn = Long.parseLong(model.getValueAt(row, 3).toString());
            String tempGenero = model.getValueAt(row, 4).toString();

            Novela tempNovela = new Novela(tempTitulo, tempAutor, tempAño, tempIsbn, tempGenero);
            if (!listalibro.contieneNovela(tempNovela)) {
                listalibro.insertarOrdenado(tempNovela);
            }
        }
    }

    public void datosPruebaEnTabla() {

        // Agregando novelas a la estantería
        estanteriaNovelas.agregarLibro(new Novela("Harry Potter y la piedra filosofal", "J.K. Rowling", 1997, 9788478884454L, "Fantasía"));
        estanteriaNovelas.agregarLibro(new Novela("Cien años de soledad", "Gabriel García Márquez", 1967, 9780307350428L, "Realismo mágico"));
        estanteriaNovelas.agregarLibro(new Novela("1984", "George Orwell", 1949, 9788420674220L, "Distopía"));
        estanteriaNovelas.agregarLibro(new Novela("Matar a un ruiseñor", "Harper Lee", 1960, 9788498383186L, "Ficción social"));
        estanteriaNovelas.agregarLibro(new Novela("Don Quijote de la Mancha", "Miguel de Cervantes", 1605, 9788424117386L, "Parodia"));
        estanteriaNovelas.agregarLibro(new Novela("En busca del tiempo perdido", "Marcel Proust", 1913, 9788420412140L, "Novela psicológica"));
        estanteriaNovelas.agregarLibro(new Novela("Orgullo y prejuicio", "Jane Austen", 1813, 9788497644881L, "Romance"));
        estanteriaNovelas.agregarLibro(new Novela("Ulises", "James Joyce", 1922, 9788489644362L, "Modernismo"));
        estanteriaNovelas.agregarLibro(new Novela("Los juegos del hambre", "Suzanne Collins", 2008, 9788427202122L, "Ciencia ficción"));
        estanteriaNovelas.agregarLibro(new Novela("Crónica de una muerte anunciada", "Gabriel García Márquez", 1981, 9780307474729L, "Ficción latinoamericana"));
        estanteriaNovelas.agregarLibro(new Novela("El gran Gatsby", "F. Scott Fitzgerald", 1925, 9788497932072L, "Ficción moderna"));
        estanteriaNovelas.agregarLibro(new Novela("Moby-Dick", "Herman Melville", 1851, 9788467028435L, "Aventura"));
        estanteriaNovelas.agregarLibro(new Novela("Los pilares de la Tierra", "Ken Follett", 1989, 9788497594758L, "Histórica"));
        estanteriaNovelas.agregarLibro(new Novela("El retrato de Dorian Gray", "Oscar Wilde", 1890, 9788491052052L, "Ficción gótica"));
        estanteriaNovelas.agregarLibro(new Novela("El guardián entre el centeno", "J.D. Salinger", 1951, 9788499890944L, "Literatura juvenil"));
        estanteriaNovelas.agregarLibro(new Novela("Los hombres me explican cosas", "Rebecca Solnit", 2014, 9788417081643L, "Ensayo"));
        estanteriaNovelas.agregarLibro(new Novela("La naranja mecánica", "Anthony Burgess", 1962, 9788433960059L, "Ficción distópica"));
        estanteriaNovelas.agregarLibro(new Novela("La sombra del viento", "Carlos Ruiz Zafón", 2001, 9788408043643L, "Misterio"));
        estanteriaNovelas.agregarLibro(new Novela("Los miserables", "Victor Hugo", 1862, 9788499897943L, "Histórica"));
        estanteriaNovelas.agregarLibro(new Novela("Rayuela", "Julio Cortázar", 1963, 9788437601720L, "Ficción experimental"));
        estanteriaNovelas.agregarLibro(new Novela("La carretera", "Cormac McCarthy", 2006, 9788433974025L, "Postapocalíptica"));
        estanteriaNovelas.agregarLibro(new Novela("Las uvas de la ira", "John Steinbeck", 1939, 9788445074699L, "Realismo"));
        estanteriaNovelas.agregarLibro(new Novela("Crimen y castigo", "Fyodor Dostoevsky", 1866, 9788420674206L, "Novela psicológica"));
        estanteriaNovelas.agregarLibro(new Novela("La Odisea", "Homero", 1800, 9788491050706L, "Épica"));
        estanteriaNovelas.agregarLibro(new Novela("La isla del tesoro", "Robert Louis Stevenson", 1883, 9788426103302L, "Aventura"));
        estanteriaNovelas.agregarLibro(new Novela("Los detectives salvajes", "Roberto Bolaño", 1998, 9788433969755L, "Narrativa latinoamericana"));
        estanteriaNovelas.agregarLibro(new Novela("Siddhartha", "Hermann Hesse", 1922, 9788499897448L, "Filosófica"));
        estanteriaNovelas.agregarLibro(new Novela("La insoportable levedad del ser", "Milan Kundera", 1984, 9788483832242L, "Filosofía existencial"));
        estanteriaNovelas.agregarLibro(new Novela("Los renglones torcidos de Dios", "Tori Kiersten", 1979, 9786070735154L, "Psicológica"));
        estanteriaNovelas.agregarLibro(new Novela("El amor en los tiempos del cólera", "Gabriel García Márquez", 1985, 9788420471833L, "Romance"));
        estanteriaNovelas.agregarLibro(new Novela("El alquimista", "Paulo Coelho", 1988, 9780062511409L, "Autoayuda"));
        estanteriaNovelas.agregarLibro(new Novela("Memorias de Adriano", "Marguerite Yourcenar", 1951, 9788435008082L, "Histórica"));
        estanteriaNovelas.agregarLibro(new Novela("La ladrona de libros", "Markus Zusak", 2005, 9788498383704L, "Ficción histórica"));
        estanteriaNovelas.agregarLibro(new Novela("El nombre de la rosa", "Umberto Eco", 1980, 9788432217523L, "Misterio"));
        estanteriaNovelas.agregarLibro(new Novela("Pedro Páramo", "Juan Rulfo", 1955, 9786074211479L, "Realismo mágico"));

        model.addColumn("Titulo");
        model.addColumn("Autor");
        model.addColumn("Año");
        model.addColumn("ISBN");
        model.addColumn("Genero");

        rellenarTabla();

        tblLibrosEstante.setModel(model);
    }



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel19 = new javax.swing.JPanel();
        jTabbedPane4 = new javax.swing.JTabbedPane();
        jPanel20 = new javax.swing.JPanel();
        btMostrar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAtributosRelevantes = new javax.swing.JTextArea();
        jLabel39 = new javax.swing.JLabel();
        txtSumaAtributo = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        btnAlmacen = new javax.swing.JButton();
        jLabel37 = new javax.swing.JLabel();
        txtIsbnHash = new javax.swing.JTextField();
        btnMostrarDatosAlmacen = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnEliminarHash = new javax.swing.JButton();
        btnBuscarLibroHash = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel38 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        btn_Avance3_Pregunta3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        btn_avance3_pregunta4 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnEliminarLibroPre1 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        txtEliminarLibrosPre1 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        txtAnioPre1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtTituloPre1 = new javax.swing.JTextField();
        txtAutorPre1 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtISBNPre1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtGeneroPre1 = new javax.swing.JTextField();
        btnAgregarLibroPre1 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        txtNombrePre2 = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        txtEdadPre2 = new javax.swing.JTextField();
        btnAgregarPersona = new javax.swing.JButton();
        btnAtenderPersona = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        txtF_tiempoTareaManual = new javax.swing.JTextField();
        btn_tiempoTareaManual = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        txtF_generarNtareasAleatorias = new javax.swing.JTextField();
        btn_generarNtareasAleatorias = new javax.swing.JButton();
        btn_detenerProcesos = new javax.swing.JButton();
        btn_continuarProcesos = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        txtF_segundoPorProceso = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtFrase = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtCola = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtConsonante = new javax.swing.JTextField();
        txtVocales = new javax.swing.JTextField();
        txtValores = new javax.swing.JTextField();
        btnIngresar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLibrosEstante = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 167, 54));

        jTabbedPane3.setBackground(new java.awt.Color(204, 167, 54));

        jPanel19.setBackground(new java.awt.Color(204, 167, 54));

        jTabbedPane4.setBackground(new java.awt.Color(255, 204, 153));

        jPanel20.setBackground(new java.awt.Color(255, 204, 153));

        btMostrar.setText("Ok");
        btMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMostrarActionPerformed(evt);
            }
        });

        txtAtributosRelevantes.setColumns(20);
        txtAtributosRelevantes.setRows(5);
        jScrollPane2.setViewportView(txtAtributosRelevantes);

        jLabel39.setFont(new java.awt.Font("Harlow Solid Italic", 1, 36)); // NOI18N
        jLabel39.setText("Función Recursiva");

        jLabel40.setText("Mostrar");

        jLabel41.setText("Dos atributos relevantes:");

        jLabel42.setText("Suma atributo numerico:");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(227, 227, 227)
                .addComponent(btMostrar)
                .addGap(77, 77, 77)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jScrollPane2)
                                .addGap(61, 61, 61)))
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel42)
                            .addComponent(txtSumaAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(224, 224, 224))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel39)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel42))
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSumaAtributo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btMostrar))
                .addGap(25, 25, 25))
        );

        jTabbedPane4.addTab("Pregunta 1", jPanel20);

        jPanel21.setBackground(new java.awt.Color(255, 204, 153));

        btnAlmacen.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        btnAlmacen.setText("Almacenar en Hash");
        btnAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlmacenActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        jLabel37.setText("Ingrese ISBN:");

        txtIsbnHash.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        txtIsbnHash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIsbnHashKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIsbnHashKeyTyped(evt);
            }
        });

        btnMostrarDatosAlmacen.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        btnMostrarDatosAlmacen.setText("Mostrar datos Almacen (Hash)");
        btnMostrarDatosAlmacen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarDatosAlmacenActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        jButton3.setText("Cargar tabla libros");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnEliminarHash.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        btnEliminarHash.setText("Eliminar libro (Hash)");
        btnEliminarHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarHashActionPerformed(evt);
            }
        });

        btnBuscarLibroHash.setFont(new java.awt.Font("Helvetica Neue", 1, 15)); // NOI18N
        btnBuscarLibroHash.setText("Buscar libro (Hash)");
        btnBuscarLibroHash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarLibroHashActionPerformed(evt);
            }
        });

        jLabel38.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        jLabel38.setText("Funcion Hash");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addComponent(btnAlmacen)
                        .addGap(18, 18, 18)
                        .addComponent(btnMostrarDatosAlmacen)))
                .addGap(18, 51, Short.MAX_VALUE)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel37)
                    .addComponent(txtIsbnHash, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarHash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscarLibroHash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(364, Short.MAX_VALUE))
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(428, 428, 428)
                .addComponent(jLabel38)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIsbnHash, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAlmacen)
                            .addComponent(btnMostrarDatosAlmacen))
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(107, 107, 107)
                                .addComponent(jButton3))
                            .addGroup(jPanel21Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(btnBuscarLibroHash)
                                .addGap(12, 12, 12)
                                .addComponent(btnEliminarHash))))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Pregunta 2", jPanel21);

        jPanel22.setBackground(new java.awt.Color(255, 204, 153));

        btn_Avance3_Pregunta3.setText("Pregunta 3 - HashTable");
        btn_Avance3_Pregunta3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_Avance3_Pregunta3ActionPerformed(evt);
            }
        });

        jButton4.setText("Pregunta 3 - HashMap");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addComponent(btn_Avance3_Pregunta3)
                .addGap(104, 104, 104)
                .addComponent(jButton4)
                .addContainerGap(577, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Avance3_Pregunta3)
                    .addComponent(jButton4))
                .addContainerGap(300, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Pregunta 3", jPanel22);

        jPanel23.setBackground(new java.awt.Color(255, 204, 153));

        btn_avance3_pregunta4.setText("Correr");
        btn_avance3_pregunta4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_avance3_pregunta4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(btn_avance3_pregunta4)
                .addContainerGap(826, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(btn_avance3_pregunta4)
                .addContainerGap(271, Short.MAX_VALUE))
        );

        jTabbedPane4.addTab("Pregunta 4", jPanel23);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jTabbedPane4)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane4)
        );

        jTabbedPane3.addTab("Tercer Avance", jPanel19);

        jPanel8.setBackground(new java.awt.Color(204, 167, 54));

        jTabbedPane1.setBackground(new java.awt.Color(255, 204, 102));

        jPanel9.setBackground(new java.awt.Color(255, 204, 102));

        jPanel14.setBackground(new java.awt.Color(255, 204, 102));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Añadir Libro");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Eliminar Libro");

        btnEliminarLibroPre1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminarLibroPre1.setText("Eliminar");
        btnEliminarLibroPre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarLibroPre1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Escribir titulo del libro:");

        txtEliminarLibrosPre1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtEliminarLibrosPre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtEliminarLibrosPre1KeyTyped(evt);
            }
        });

        jPanel15.setBackground(new java.awt.Color(255, 204, 102));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel16.setText("Año");

        txtAnioPre1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Titulo");

        txtTituloPre1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTituloPre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloPre1ActionPerformed(evt);
            }
        });

        txtAutorPre1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Autor");

        txtISBNPre1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setText("ISBN");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel20.setText("Genero");

        txtGeneroPre1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnAgregarLibroPre1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAgregarLibroPre1.setText("Agregar");
        btnAgregarLibroPre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLibroPre1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtISBNPre1)
                            .addComponent(txtAnioPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGeneroPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarLibroPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtAutorPre1)
                        .addComponent(txtTituloPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtTituloPre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(txtAnioPre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtAutorPre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtISBNPre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtGeneroPre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarLibroPre1))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(369, 369, 369))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarLibroPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEliminarLibrosPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addContainerGap())
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel14)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEliminarLibrosPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnEliminarLibroPre1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(137, Short.MAX_VALUE))))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel13)
                .addGap(37, 37, 37)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1096, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 383, Short.MAX_VALUE)
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addGap(0, 43, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Pregunta1", jPanel9);

        jPanel11.setBackground(new java.awt.Color(255, 204, 102));

        jLabel35.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel35.setText("Ingrese nombre");

        txtNombrePre2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N

        jLabel36.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        jLabel36.setText("Ingrese edad");

        txtEdadPre2.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N

        btnAgregarPersona.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        btnAgregarPersona.setText("Agregar persona");
        btnAgregarPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPersonaActionPerformed(evt);
            }
        });

        btnAtenderPersona.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        btnAtenderPersona.setText("Atender persona");
        btnAtenderPersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtenderPersonaActionPerformed(evt);
            }
        });

        jButton1.setText("Cargar tabla libros");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(jLabel35))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel36)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombrePre2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEdadPre2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAtenderPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(425, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(427, 427, 427))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(txtNombrePre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarPersona))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtEdadPre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAtenderPersona))
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(72, 72, 72))
        );

        jTabbedPane1.addTab("Pregunta2", jPanel11);

        jPanel12.setBackground(new java.awt.Color(255, 204, 102));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel31.setText("Simulacion de procesamiento");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel32.setText("Tiempo de tarea:");

        btn_tiempoTareaManual.setText("Añadir tarea");
        btn_tiempoTareaManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tiempoTareaManualActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_tiempoTareaManual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addGap(27, 27, 27)
                        .addComponent(txtF_tiempoTareaManual, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(txtF_tiempoTareaManual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_tiempoTareaManual)
                .addGap(19, 19, 19))
        );

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel33.setText("Cantidad de Tareas:");

        btn_generarNtareasAleatorias.setText("jButton2");
        btn_generarNtareasAleatorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_generarNtareasAleatoriasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_generarNtareasAleatorias, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtF_generarNtareasAleatorias, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(txtF_generarNtareasAleatorias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_generarNtareasAleatorias)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        btn_detenerProcesos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_detenerProcesos.setText("Detener");
        btn_detenerProcesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detenerProcesosActionPerformed(evt);
            }
        });

        btn_continuarProcesos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_continuarProcesos.setText("Continuar");
        btn_continuarProcesos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_continuarProcesosActionPerformed(evt);
            }
        });

        jLabel34.setText("Segundos por proceso:");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(158, 158, 158)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(btn_detenerProcesos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_continuarProcesos))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel34)
                                .addGap(18, 18, 18)
                                .addComponent(txtF_segundoPorProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(407, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel31)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(txtF_segundoPorProceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_detenerProcesos)
                            .addComponent(btn_continuarProcesos))))
                .addGap(18, 18, 18)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pregunta3", jPanel12);

        jPanel13.setBackground(new java.awt.Color(255, 204, 102));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Titulo Seleccionado");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel22.setText("Frase");
        jLabel22.setToolTipText("");

        txtFrase.setEditable(false);

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel23.setText("Frase");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel24.setText("Cola");

        txtCola.setEditable(false);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel26.setText("Consonantes");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel27.setText("Vocales");
        jLabel27.setToolTipText("");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel28.setText("Valores");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel25.setText("Pila");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel29.setText("Pila");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel30.setText("Pila");

        txtConsonante.setEditable(false);
        txtConsonante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtConsonanteActionPerformed(evt);
            }
        });

        txtVocales.setEditable(false);

        txtValores.setEditable(false);

        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        jButton2.setText("Cargar tabla libros");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnIngresar)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtFrase, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(jButton2)))
                .addGap(109, 109, 109)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel23)
                        .addGroup(jPanel13Layout.createSequentialGroup()
                            .addGap(41, 41, 41)
                            .addComponent(jLabel24)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtCola, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addGroup(jPanel13Layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel29)
                                            .addComponent(jLabel25)
                                            .addComponent(jLabel30)))
                                    .addComponent(jLabel28))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtConsonante)
                                    .addComponent(txtVocales)
                                    .addComponent(txtValores, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(16, 16, 16)))
                .addGap(88, 88, 88))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3)
                .addContainerGap())
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(txtCola, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel26)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtVocales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(txtValores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txtConsonante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 59, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addGap(33, 33, 33)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtFrase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(btnIngresar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pregunta4", jPanel13);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        jTabbedPane3.addTab("Segundo Avance", jPanel8);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 0));

        tblLibrosEstante.setBackground(new java.awt.Color(204, 181, 66));
        tblLibrosEstante.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblLibrosEstante.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jScrollPane1.setViewportView(tblLibrosEstante);
        if (tblLibrosEstante.getColumnModel().getColumnCount() > 0) {
            tblLibrosEstante.getColumnModel().getColumn(0).setHeaderValue("Title 1");
            tblLibrosEstante.getColumnModel().getColumn(1).setHeaderValue("Title 2");
            tblLibrosEstante.getColumnModel().getColumn(2).setHeaderValue("Title 3");
            tblLibrosEstante.getColumnModel().getColumn(3).setHeaderValue("Title 4");
        }

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_Avance3_Pregunta3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_Avance3_Pregunta3ActionPerformed
        // Este boton abre la ventana de la pregunta 3 que está en el paquete de APF3.P3 vistaHash
        vistaHash vHash = new vistaHash();
        vHash.setVisible(true);
    }//GEN-LAST:event_btn_Avance3_Pregunta3ActionPerformed

    private void btnBuscarLibroHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarLibroHashActionPerformed
        // TODO add your handling code here:
        try {
            long isbn = Long.parseLong(txtIsbnHash.getText());

            Novela novelaEncontrada = hash.buscar(isbn);

            if (novelaEncontrada != null) {
                // Se encontró la novela
                JOptionPane.showMessageDialog(null, "Novela encontrada:\n" + novelaEncontrada.toString(),
                    "Búsqueda Exitosa", JOptionPane.INFORMATION_MESSAGE);
                model.setRowCount(0);

                // Agregar la novela encontrada al modelo de la tabla
                Object[] fila = {
                    novelaEncontrada.getTitulo(),
                    novelaEncontrada.getAutor(),
                    novelaEncontrada.getAnioPublicacion(),
                    novelaEncontrada.getISBN(),
                    novelaEncontrada.getGenero()
                };
                model.addRow(fila);

                // Enfocar la fila recién agregada
                if (model.getRowCount() > 0) {
                    tblLibrosEstante.scrollRectToVisible(tblLibrosEstante.getCellRect(model.getRowCount() - 1, 0, true));
                }
            } else {
                // No se encontró la novela
                JOptionPane.showMessageDialog(null, "No se encontró ninguna novela con ISBN " + isbn,
                    "Búsqueda Fallida", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ingrese un ISBN válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarLibroHashActionPerformed

    private void btnEliminarHashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarHashActionPerformed
        // TODO add your handling code here:
        try {
            long isbn = Long.parseLong(txtIsbnHash.getText());

            if (hash.eliminar(isbn)) {
                JOptionPane.showMessageDialog(null, "Novela eliminada con éxito",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ninguna novela con ISBN " + isbn,
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Ingrese un ISBN válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
        model.setRowCount(0); // Limpiar la tabla antes de mostrar los nuevos datos

        Novela[] novelas = hash.mostrarDatos();

        for (Novela novela : novelas) {
            model.addRow(new Object[]{novela.getTitulo(), novela.getAutor(), novela.getAnioPublicacion(), novela.getISBN(), novela.getGenero()});
        }
    }//GEN-LAST:event_btnEliminarHashActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        model.setRowCount(0);
        model.setColumnCount(0);
        datosPruebaEnTabla();
        guardarEnFichero(estanteriaNovelas);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnMostrarDatosAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarDatosAlmacenActionPerformed
        // TODO add your handling code here:
        model.setRowCount(0); // Limpiar la tabla antes de mostrar los nuevos datos

        Novela[] novelas = hash.mostrarDatos();

        for (Novela novela : novelas) {
            model.addRow(new Object[]{novela.getTitulo(), novela.getAutor(), novela.getAnioPublicacion(), novela.getISBN(), novela.getGenero()});
        }
    }//GEN-LAST:event_btnMostrarDatosAlmacenActionPerformed

    private void txtIsbnHashKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIsbnHashKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIsbnHashKeyTyped

    private void txtIsbnHashKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIsbnHashKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIsbnHashKeyReleased

    private void btnAlmacenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlmacenActionPerformed
        // TODO add your handling code here:

        // Itera sobre las filas del modelo y almacena cada novela en la tabla hash
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            String titulo = model.getValueAt(i, 0).toString();
            String autor = model.getValueAt(i, 1).toString();
            int anio = Integer.parseInt(model.getValueAt(i, 2).toString());
            long isbn = Long.parseLong(model.getValueAt(i, 3).toString());
            String genero = model.getValueAt(i, 4).toString();

            Novela nuevaNovela = new Novela(titulo, autor, anio, isbn, genero);
            hash.insert(nuevaNovela);
        }
        hash.mostrarDatos();
        JOptionPane.showMessageDialog(null, "Datos guardados en Hash", "Información", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnAlmacenActionPerformed

    private void btMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMostrarActionPerformed

        txtAtributosRelevantes.setText(listaLibrosRecursivo.obtainTwoAttributes(estanteriaNovelas.getListaLibros(), 0));
        txtSumaAtributo.setText("" + listaLibrosRecursivo.getSuma());
        listaLibrosRecursivo.setSuma(0);
    }//GEN-LAST:event_btMostrarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        model.setRowCount(0);
        model.setColumnCount(0);
        datosPruebaEnTabla();
        guardarEnFichero(estanteriaNovelas);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed

        String frase = txtFrase.getText(); // Obtenemos el titulo clikeado

        ColaCaracteres cola = new ColaCaracteres();
        ConsonantesVocales comprobar = new ConsonantesVocales();

        PilaCaracteres consonantes = new PilaCaracteres();
        PilaCaracteres vocales = new PilaCaracteres();
        PilaCaracteres valores = new PilaCaracteres();

        ////////////////////////////////////// Rellenado de Cola ///////////////////////
        for (int i = 0; i < frase.length(); i++) {

            char c = frase.charAt(i);

            if (c != ' ') {
                cola.encolar(c);
            }
        }

        //////////////////////////////// Desencolar y apilado //////////////////////////
        String fraseCola = "";

        while (cola.getFrente() != null) {

            char c = cola.desencolar(); // aprovecho el desencolar para derivar y comprobar los caracteres a sus respectivas PILAS
            fraseCola += c;

            if (comprobar.isConsonant(c) && !comprobar.isVowel(c)) {

                consonantes.apilar(c);

            } else if (comprobar.isVowel(c)) {

                vocales.apilar(c);

            } else {

                valores.apilar(c);

            }
            System.out.print(c);
        }
        //////////////////////////////// Desapilado ////////////////////////////////////

        String fraseConsonantes = "";
        String fraseVocales = "";
        String fraseValores = "";

        for (int i = 0; i < fraseCola.length(); i++) {

            if (consonantes.getCima() != null) {
                fraseConsonantes += consonantes.desapilar();
            }
            if (vocales.getCima() != null) {
                fraseVocales += vocales.desapilar();
            }
            if (valores.getCima() != null) {
                fraseValores += valores.desapilar();
            }
        }
        //////////////////////////////// Mostrar Datos en los texField /////////////////

        txtCola.setText(fraseCola);
        txtConsonante.setText(fraseConsonantes);
        txtVocales.setText(fraseVocales);
        txtValores.setText(fraseValores);
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void txtConsonanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtConsonanteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConsonanteActionPerformed

    private void btn_continuarProcesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_continuarProcesosActionPerformed

        simulacion.setContinuar(true);
        simulacion.switchProcesado(Integer.parseInt(txtF_segundoPorProceso.getText()));

        // simular los procesos en la tabla tblLibrosEstante
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (simulacion.getContinuar()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    model.setRowCount(0);

                    Proceso[] array = simulacion.imprimir();

                    // borrar columnas y colocar solo las que necesitamos
                    model.setColumnCount(0);
                    model.addColumn("Nombre");
                    model.addColumn("Tiempo Restante");

                    //añadir a la tabla el nombre y el tiempo de cada proceso
                    for (int i = 0; i < array.length; i++) {

                        model.addRow(new Object[]{
                            "Proceso " + array[i].getNombre(),
                            array[i].getTiempoRestante()
                        });
                    }

                }
            }
        }).start();
    }//GEN-LAST:event_btn_continuarProcesosActionPerformed

    private void btn_detenerProcesosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detenerProcesosActionPerformed

        simulacion.setContinuar(false);
        //simulacion.switchProcesado(0);
    }//GEN-LAST:event_btn_detenerProcesosActionPerformed

    private void btn_generarNtareasAleatoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_generarNtareasAleatoriasActionPerformed

        // se generan n tareas aleatorias por lo que le pasemos por parametro
        int n = Integer.parseInt(txtF_generarNtareasAleatorias.getText());
        for (int i = 0; i < n; i++) {
            simulacion.agregarProceso(new Proceso((int) (Math.random() * 5) + 1)); // podriamos pasale por parametro el intervalo de tiempo
        }

        model.setRowCount(0);

        Proceso[] array = simulacion.imprimir();

        model.setColumnCount(0);
        model.addColumn("Nombre");
        model.addColumn("Tiempo Restante");

        for (int i = 0; i < array.length; i++) {
            model.addRow(new Object[]{
                "Proceso " + array[i].getNombre(),
                array[i].getTiempoRestante()
            });

        }
    }//GEN-LAST:event_btn_generarNtareasAleatoriasActionPerformed

    private void btn_tiempoTareaManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tiempoTareaManualActionPerformed

        simulacion.agregarProceso(new Proceso(Integer.parseInt(txtF_tiempoTareaManual.getText())));

        model.setRowCount(0);

        Proceso[] array = simulacion.imprimir();

        model.setColumnCount(0);
        model.addColumn("Nombre");
        model.addColumn("Tiempo Restante");

        for (int i = 0; i < array.length; i++) {
            model.addRow(new Object[]{
                "Proceso " + array[i].getNombre(),
                array[i].getTiempoRestante()
            });

        }
    }//GEN-LAST:event_btn_tiempoTareaManualActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        model.setRowCount(0);
        model.setColumnCount(0);
        datosPruebaEnTabla();
        guardarEnFichero(estanteriaNovelas);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnAtenderPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtenderPersonaActionPerformed
        // TODO add your handling code here:
        colaPersonas.atenderPersona();

        model.setRowCount(0);

        model.setColumnCount(0);
        model.addColumn("Persona");
        model.addColumn("Edad");

        String personasString = colaPersonas.mostrarPersonas();

        String[] personasArray = personasString.split(";");

        for (String persona : personasArray) {
            String[] datosPersona = persona.split(" ");
            model.addRow(datosPersona);
        }
    }//GEN-LAST:event_btnAtenderPersonaActionPerformed

    private void btnAgregarPersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPersonaActionPerformed
        // TODO add your handling code here:
        String nombre = txtNombrePre2.getText();
        int edad = Integer.parseInt(txtEdadPre2.getText());

        colaPersonas.insertarPersona(new Persona_2(nombre, edad));

        txtNombrePre2.setText("");
        txtEdadPre2.setText("");
        model.setRowCount(0);
        model.setColumnCount(0);
        model.addColumn("Persona");
        model.addColumn("Edad");

        String personasString = colaPersonas.mostrarPersonas();

        String[] personasArray = personasString.split(";");

        for (String persona : personasArray) {
            String[] datosPersona = persona.split(" ");
            model.addRow(datosPersona);
        }
    }//GEN-LAST:event_btnAgregarPersonaActionPerformed

    private void btnAgregarLibroPre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLibroPre1ActionPerformed
        // TODO add your handling code here:
        try {

            String titulo = txtTituloPre1.getText();
            String autor = txtAutorPre1.getText();
            String genero = txtGeneroPre1.getText();
            String añoStr = txtAnioPre1.getText();
            String isbnStr = txtISBNPre1.getText();

            if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty() || añoStr.isEmpty() || isbnStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (añoStr.length() != 4 || !añoStr.matches("\\d{4}")) {
                JOptionPane.showMessageDialog(this, "Ingresa un año válido (4 dígitos numéricos).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int año;
            try {
                año = Integer.parseInt(añoStr);
                if (año < 0) {
                    JOptionPane.showMessageDialog(this, "Ingresa un año válido (mayor o igual a 0).", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingresa un año válido (número entero).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            long isbn;
            try {
                isbn = Long.parseLong(isbnStr);
                if (isbn < 0) {
                    JOptionPane.showMessageDialog(this, "Ingresa un ISBN válido (mayor o igual a 0).", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Ingresa un ISBN válido (número entero).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Novela nov = new Novela(titulo, autor, año, isbn, genero);

            if (!listalibro.contieneNovela(nov)) {
                listalibro.insertarOrdenado(nov);
            }
            // Limpiar los campos del formulario
            txtAnioPre1.setText("");
            txtTituloPre1.setText("");
            txtAutorPre1.setText("");
            txtGeneroPre1.setText("");
            txtISBNPre1.setText("");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        rellenarTabla2(listalibro);
    }//GEN-LAST:event_btnAgregarLibroPre1ActionPerformed

    private void txtTituloPre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloPre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloPre1ActionPerformed

    private void txtEliminarLibrosPre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEliminarLibrosPre1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEliminarLibrosPre1KeyTyped

    private void btnEliminarLibroPre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarLibroPre1ActionPerformed
        // TODO add your handling code here:
        String titulo = txtEliminarLibrosPre1.getText();
        listalibro.borrarPorTitulo(titulo);
        rellenarTabla2(listalibro);
    }//GEN-LAST:event_btnEliminarLibroPre1ActionPerformed

    private void btn_avance3_pregunta4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_avance3_pregunta4ActionPerformed

        String[] args = {};
        Main.main(args);

    }//GEN-LAST:event_btn_avance3_pregunta4ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        vistaHashMap vHash = new vistaHashMap();
        vHash.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed

    ColaDeProcesos simulacion = new ColaDeProcesos();

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Avance3Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Avance3Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Avance3Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Avance3Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Avance3Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btMostrar;
    private javax.swing.JButton btnAgregarLibroPre1;
    private javax.swing.JButton btnAgregarPersona;
    private javax.swing.JButton btnAlmacen;
    private javax.swing.JButton btnAtenderPersona;
    private javax.swing.JButton btnBuscarLibroHash;
    private javax.swing.JButton btnEliminarHash;
    private javax.swing.JButton btnEliminarLibroPre1;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnMostrarDatosAlmacen;
    private javax.swing.JButton btn_Avance3_Pregunta3;
    private javax.swing.JButton btn_avance3_pregunta4;
    private javax.swing.JButton btn_continuarProcesos;
    private javax.swing.JButton btn_detenerProcesos;
    private javax.swing.JButton btn_generarNtareasAleatorias;
    private javax.swing.JButton btn_tiempoTareaManual;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTabbedPane jTabbedPane4;
    private javax.swing.JTable tblLibrosEstante;
    private javax.swing.JTextField txtAnioPre1;
    private javax.swing.JTextArea txtAtributosRelevantes;
    private javax.swing.JTextField txtAutorPre1;
    private javax.swing.JTextField txtCola;
    private javax.swing.JTextField txtConsonante;
    private javax.swing.JTextField txtEdadPre2;
    private javax.swing.JTextField txtEliminarLibrosPre1;
    private javax.swing.JTextField txtF_generarNtareasAleatorias;
    private javax.swing.JTextField txtF_segundoPorProceso;
    private javax.swing.JTextField txtF_tiempoTareaManual;
    private javax.swing.JTextField txtFrase;
    private javax.swing.JTextField txtGeneroPre1;
    private javax.swing.JTextField txtISBNPre1;
    private javax.swing.JTextField txtIsbnHash;
    private javax.swing.JTextField txtNombrePre2;
    private javax.swing.JTextField txtSumaAtributo;
    private javax.swing.JTextField txtTituloPre1;
    private javax.swing.JTextField txtValores;
    private javax.swing.JTextField txtVocales;
    // End of variables declaration//GEN-END:variables
}
