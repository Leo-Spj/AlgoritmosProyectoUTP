/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package VISTA;

import CONTROL.ControlEstanteria;
import MODELO.CLASIFICACION.Novela;
import MODELO.Libro;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Leo
 */
public class Biblioteca extends javax.swing.JFrame {

    /**
     * Creates new form Biblioteca
     */

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    DefaultTableModel model = new DefaultTableModel();
    ControlEstanteria<Novela> estanteriaNovelas = new ControlEstanteria();


    public Biblioteca() {
        initComponents();
        datosPruebaEnTabla();
        guardarEnFichero(estanteriaNovelas);
    }

    public void guardarEnFichero(ControlEstanteria estanteria){

        try(PrintWriter pw = new PrintWriter(new File("src/main/java/estanteriaNovelas.json"))){

            pw.write(gson.toJson(estanteria.getListaLibros()));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void rellenarTabla(){
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

    public void datosPruebaEnTabla(){

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
        jPanel3 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnEliminarLibro = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cbxBuscarLibros = new javax.swing.JComboBox<>();
        txtBuscarLibros = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JTextField();
        txtAutor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtISBN = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtGenero = new javax.swing.JTextField();
        btnAgregarLibro = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnOrdNum_AnioPublic = new javax.swing.JButton();
        btnOrdNum_ISBN = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnOrdString_Titulo = new javax.swing.JButton();
        btnOrdString_Autor = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        btnAscDes = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        txtAnio2Filter = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtGenro2Filter = new javax.swing.JTextField();
        btnBuscar2Filter = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLibrosEstante = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 167, 54));

        jTabbedPane3.setBackground(new java.awt.Color(204, 167, 54));

        jPanel3.setBackground(new java.awt.Color(204, 167, 54));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("Añadir Libro");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Eliminar Libro");

        btnEliminarLibro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnEliminarLibro.setText("Eliminar");
        btnEliminarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarLibroActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Buscar por:");

        cbxBuscarLibros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbxBuscarLibros.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Titulo", "Autor", "ISBN", "Genero", "Año" }));
        cbxBuscarLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxBuscarLibrosActionPerformed(evt);
            }
        });

        txtBuscarLibros.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBuscarLibros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarLibrosActionPerformed(evt);
            }
        });
        txtBuscarLibros.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarLibrosKeyTyped(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(204, 167, 54));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Año");

        txtAnio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Titulo");

        txtTitulo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloActionPerformed(evt);
            }
        });

        txtAutor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Autor");

        txtISBN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("ISBN");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("Genero");

        txtGenero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btnAgregarLibro.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAgregarLibro.setText("Agregar");
        btnAgregarLibro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarLibroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtISBN)
                            .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(135, 135, 135)))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtAutor)
                        .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtISBN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarLibro))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(369, 369, 369))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(cbxBuscarLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel9)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbxBuscarLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtBuscarLibros, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addComponent(btnEliminarLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel8)
                .addGap(37, 37, 37)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Insertar & Eliminar", jPanel3);

        jPanel4.setBackground(new java.awt.Color(204, 167, 54));

        btnOrdNum_AnioPublic.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOrdNum_AnioPublic.setText("Año de Publicación");
        btnOrdNum_AnioPublic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdNum_AnioPublicActionPerformed(evt);
            }
        });

        btnOrdNum_ISBN.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOrdNum_ISBN.setText("ISBN");
        btnOrdNum_ISBN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdNum_ISBNActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Ordenar Por:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOrdNum_AnioPublic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnOrdNum_ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(656, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(btnOrdNum_AnioPublic, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnOrdNum_ISBN, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Num Ord", jPanel4);

        jPanel5.setBackground(new java.awt.Color(204, 167, 54));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Ordenar por:");

        btnOrdString_Titulo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOrdString_Titulo.setText("Titulo");
        btnOrdString_Titulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdString_TituloActionPerformed(evt);
            }
        });

        btnOrdString_Autor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnOrdString_Autor.setText("Autor");
        btnOrdString_Autor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdString_AutorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(88, 88, 88)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnOrdString_Titulo, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOrdString_Autor, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(588, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel6)
                .addGap(52, 52, 52)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnOrdString_Autor, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(btnOrdString_Titulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("String Ord", jPanel5);

        jPanel6.setBackground(new java.awt.Color(204, 167, 54));

        btnAscDes.setText("Asc & Desc");
        btnAscDes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAscDesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(btnAscDes, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(660, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addComponent(btnAscDes, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Asc & Desc", jPanel6);

        jPanel7.setBackground(new java.awt.Color(204, 167, 54));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel10.setText("Año");

        txtAnio2Filter.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtAnio2Filter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtAnio2FilterKeyTyped(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Genero");

        txtGenro2Filter.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtGenro2Filter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtGenro2FilterKeyTyped(evt);
            }
        });

        btnBuscar2Filter.setText("Buscar");
        btnBuscar2Filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar2FilterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtGenro2Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnio2Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(137, 137, 137)
                .addComponent(btnBuscar2Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(428, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(btnBuscar2Filter, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtGenro2Filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtAnio2Filter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("2 Filtros Busqueda", jPanel7);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        jPanel2.setBackground(new java.awt.Color(102, 102, 0));

        tblLibrosEstante.setBackground(new java.awt.Color(204, 181, 66));
        tblLibrosEstante.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tblLibrosEstante.setFont(new java.awt.Font("Segoe UI", 0, 17)); // NOI18N
        jScrollPane1.setViewportView(tblLibrosEstante);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOrdNum_AnioPublicActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdNum_AnioPublicActionPerformed

        model.setRowCount(0);

        estanteriaNovelas.deBurbuja("anioPublicacion"); // "anioPublicacion
        rellenarTabla();

    }//GEN-LAST:event_btnOrdNum_AnioPublicActionPerformed

    private void btnOrdNum_ISBNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdNum_ISBNActionPerformed


        model.setRowCount(0);

        estanteriaNovelas.deBurbuja("ISBN");
        rellenarTabla();

    }//GEN-LAST:event_btnOrdNum_ISBNActionPerformed

    private void btnAgregarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarLibroActionPerformed

        String titulo = txtTitulo.getText();
        String autor = txtAutor.getText();
        String genero = txtGenero.getText();
        String anioStr = txtAnio.getText();
        String isbnStr = txtISBN.getText();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa al menos un carácter en el campo de título.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        if (autor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa al menos un carácter en el campo de autor.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        if (genero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa al menos un carácter en el campo de género.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        if (anioStr.length() != 4 || !anioStr.matches("\\d{4}")) {
            JOptionPane.showMessageDialog(this, "Ingresa un año válido (4 dígitos numéricos).", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        int anio;
        try {
            anio = Integer.parseInt(anioStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresa un año válido (número entero).", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        long ISBN;
        try {
            ISBN = Long.parseLong(isbnStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingresa un ISBN válido (número entero).", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }

        Novela nuevaNovela = new Novela(titulo, autor, anio, ISBN, genero);
        estanteriaNovelas.agregarLibro(nuevaNovela);

        model.addRow(new Object[]{
            nuevaNovela.getTitulo(),
            nuevaNovela.getAutor(),
            nuevaNovela.getAnioPublicacion(),
            nuevaNovela.getISBN(),
            nuevaNovela.getGenero()
        });
        

    }//GEN-LAST:event_btnAgregarLibroActionPerformed

    private void cbxBuscarLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxBuscarLibrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxBuscarLibrosActionPerformed

    private void btnEliminarLibroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarLibroActionPerformed

        int filaSeleccionada = tblLibrosEstante.getSelectedRow();
        long isbn = Long.parseLong(tblLibrosEstante.getValueAt(filaSeleccionada, 3).toString());

        estanteriaNovelas.removerLibro(estanteriaNovelas.obtenerUnLibro(isbn));

        model.removeRow(filaSeleccionada);

    }//GEN-LAST:event_btnEliminarLibroActionPerformed

    private void btnOrdString_TituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdString_TituloActionPerformed

        model.setRowCount(0);

        estanteriaNovelas.porSeleccion_String("titulo");
        rellenarTabla();
    }//GEN-LAST:event_btnOrdString_TituloActionPerformed

    private void btnOrdString_AutorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdString_AutorActionPerformed

        model.setRowCount(0);

        estanteriaNovelas.deBurbuja("autor");
        rellenarTabla();
    }//GEN-LAST:event_btnOrdString_AutorActionPerformed

    private void txtBuscarLibrosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarLibrosKeyTyped
        //busqueda secuencial
        String atributo = cbxBuscarLibros.getSelectedItem().toString();
        String valor = txtBuscarLibros.getText();

        if(atributo.equals("ISBN")){
            model.setRowCount(0);

            Libro[] encontrado = estanteriaNovelas.busquedaSecuencial(atributo, valor);

            for(int i = 0; i < encontrado.length; i++){
                Novela novela = (Novela) encontrado[i];
                model.addRow(new Object[]{
                    novela.getTitulo(),
                    novela.getAutor(),
                    novela.getAnioPublicacion(),
                    novela.getISBN(),
                    novela.getGenero()
                });
            }
        }

        if (atributo.equals("Titulo")) {

            model.setRowCount(0);

            Libro[] encontrado = estanteriaNovelas.busquedaSecuencial(atributo, valor);

            for(int i = 0; i < encontrado.length; i++){
                Novela novela = (Novela) encontrado[i];
                model.addRow(new Object[]{
                        novela.getTitulo(),
                        novela.getAutor(),
                        novela.getAnioPublicacion(),
                        novela.getISBN(),
                        novela.getGenero()
                });
            }
        }

        if (atributo.equals("Autor")) {

            model.setRowCount(0);

            Libro[] encontrado = estanteriaNovelas.busquedaSecuencial(atributo, valor);

            for(int i = 0; i < encontrado.length; i++){
                Novela novela = (Novela) encontrado[i];
                model.addRow(new Object[]{
                        novela.getTitulo(),
                        novela.getAutor(),
                        novela.getAnioPublicacion(),
                        novela.getISBN(),
                        novela.getGenero()
                });
            }
        }
        if (atributo.equals("Genero")) {

            model.setRowCount(0);

            Libro[] encontrado = estanteriaNovelas.busquedaSecuencial(atributo, valor);

            for(int i = 0; i < encontrado.length; i++){
                Novela novela = (Novela) encontrado[i];
                model.addRow(new Object[]{
                        novela.getTitulo(),
                        novela.getAutor(),
                        novela.getAnioPublicacion(),
                        novela.getISBN(),
                        novela.getGenero()
                });
            }
        }
        if (atributo.equals("Año")) {

            model.setRowCount(0);

            Libro[] encontrado = estanteriaNovelas.busquedaSecuencial(atributo, valor);

            for(int i = 0; i < encontrado.length; i++){
                Novela novela = (Novela) encontrado[i];
                model.addRow(new Object[]{
                        novela.getTitulo(),
                        novela.getAutor(),
                        novela.getAnioPublicacion(),
                        novela.getISBN(),
                        novela.getGenero()
                });
            }
        }


    }//GEN-LAST:event_txtBuscarLibrosKeyTyped

    private void btnAscDesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAscDesActionPerformed
        //burbuja ascendente seleccion descendente
        model.setRowCount(0);

        estanteriaNovelas.ordenarNovelaPorGeneroYAnioPublicacion();
        rellenarTabla();


    }//GEN-LAST:event_btnAscDesActionPerformed

    private void txtAnio2FilterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAnio2FilterKeyTyped
        
    }//GEN-LAST:event_txtAnio2FilterKeyTyped

    private void txtGenro2FilterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGenro2FilterKeyTyped

        String Genero = txtGenro2Filter.getText();

        model.setRowCount(0);

        Libro[] encontradoGenero = estanteriaNovelas.busquedaSecuencial("Genero", Genero);

        for(int i = 0; i < encontradoGenero.length; i++){
            Novela novela = (Novela) encontradoGenero[i];
            model.addRow(new Object[]{
                    novela.getTitulo(),
                    novela.getAutor(),
                    novela.getAnioPublicacion(),
                    novela.getISBN(),
                    novela.getGenero()
            });
        }

    }//GEN-LAST:event_txtGenro2FilterKeyTyped

    private void btnBuscar2FilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar2FilterActionPerformed
        
        String Año = txtAnio2Filter.getText();
        String Genero = txtGenro2Filter.getText();
        
        if (Genero.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa al menos un carácter en el campo de género.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        if (Año.length() != 4) {
            JOptionPane.showMessageDialog(this, "El año debe tener exactamente 4 dígitos.", "Error", JOptionPane.ERROR_MESSAGE);
            return; 
        }
        model.setRowCount(0);

        // Realiza una búsqueda secuencial por género
        Libro[] generosEncontradosLibro = estanteriaNovelas.busquedaSecuencial("Genero", Genero);
        Novela[] encontradoGenero = new Novela[generosEncontradosLibro.length];
        for(int i = 0; i < generosEncontradosLibro.length; i++){
            encontradoGenero[i] = (Novela) generosEncontradosLibro[i];
        }

        // Realiza una búsqueda binaria por año
        Libro[] encontradoAño = estanteriaNovelas.busquedaBinariaSeleccion("Año", Año);
        
        for(int i = 0; i < encontradoGenero.length; i++){
            for(int j = 0; j < encontradoAño.length; j++){
                if(encontradoGenero[i].equals(encontradoAño[j])){
                    Novela novela = (Novela) encontradoGenero[i];
                    model.addRow(new Object[]{
                            novela.getTitulo(),
                            novela.getAutor(),
                            novela.getAnioPublicacion(),
                            novela.getISBN(),
                            novela.getGenero()
                    });
                }
            }
        }

    }//GEN-LAST:event_btnBuscar2FilterActionPerformed

    private void txtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTituloActionPerformed

    private void txtBuscarLibrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarLibrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarLibrosActionPerformed

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
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Biblioteca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Biblioteca().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarLibro;
    private javax.swing.JButton btnAscDes;
    private javax.swing.JButton btnBuscar2Filter;
    private javax.swing.JButton btnEliminarLibro;
    private javax.swing.JButton btnOrdNum_AnioPublic;
    private javax.swing.JButton btnOrdNum_ISBN;
    private javax.swing.JButton btnOrdString_Autor;
    private javax.swing.JButton btnOrdString_Titulo;
    private javax.swing.JComboBox<String> cbxBuscarLibros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable tblLibrosEstante;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtAnio2Filter;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtBuscarLibros;
    private javax.swing.JTextField txtGenero;
    private javax.swing.JTextField txtGenro2Filter;
    private javax.swing.JTextField txtISBN;
    private javax.swing.JTextField txtTitulo;
    // End of variables declaration//GEN-END:variables
}
