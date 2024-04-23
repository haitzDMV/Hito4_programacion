import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VisualizadorFotos extends JFrame {
    public static void visualizadorFotos() {


        JFrame jFrame = new JFrame("Photography");

        //JPanel jpGeneral = new JPanel();
        jFrame.setLayout(new GridLayout(2,2));
        //jFrame.add(jpGeneral);

        JPanel fotografos = new JPanel();
        JLabel jlfotografos = new JLabel("Photographer:");
        JComboBox jcfotografos = new JComboBox();
        jcfotografos.addItem("");


        //Mostrar todos los fotografos
        ArrayList<Fotografo> f = listaFotografos();
        for (Fotografo e: f) {
            jcfotografos.addItem(e.getNombre());
        }




        fotografos.add(jlfotografos);
        fotografos.add(jcfotografos);
        jFrame.add(fotografos);



        JPanel fecha = new JPanel();
        JLabel jlfotos = new JLabel("Photos after:");
        JXDatePicker date = new JXDatePicker(new Date());
        //date.setDate(new Date(124, 4, 11)); //Para que no de error
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");


        Date fechaSelec = date.getDate();
        java.sql.Date fechaSQL = new java.sql.Date(fechaSelec.getTime());

        //date.addActionListener();






        fecha.add(jlfotos);
        fecha.add(date);
        jFrame.add(fecha);



        JPanel lista = new JPanel();
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> jList = new JList<>(model);
        jList.setPreferredSize(new Dimension(200,150));
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(jList);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);





        //Action Listener para buscar las fotografias asignadas a cada fotografo
        jcfotografos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.clear();
                ArrayList<String> a = buscarFotografias(jcfotografos.getSelectedIndex(),fechaSQL);
                for (String fotos: a) {
                    model.addElement(fotos);
                }
            }
        });

        JPanel panelImagen = new JPanel();
        JLabel imagen = new JLabel();
        imagen.setPreferredSize(new Dimension(100,100));

        //Action Listener para mostrar la imagen seleccionada
        jList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                String imagenSeleccionada = jList.getSelectedValue();
                ImageIcon icon = new ImageIcon(mostrarFotografia(imagenSeleccionada,jcfotografos.getSelectedIndex()));
                imagen.setIcon(icon);

            }
        });



        lista.add(scrollPane);
        jFrame.add(lista);


        panelImagen.add(imagen);
        jFrame.add(panelImagen);


        jFrame.setPreferredSize(new Dimension(500,400));
        jFrame.pack();
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }


    public static void main(String[] args) {
        visualizadorFotos();
    }

    //Obtener lista de todos los fotogrados de la DB
    public static ArrayList<Fotografo> listaFotografos() {
        conexion conexion = new conexion();
        Connection conn=conexion.MyConexion();
        ArrayList<Fotografo> ALfotografos = new ArrayList<>();

        try (PreparedStatement select = conn.prepareStatement("Select * from fotografo")) {
            ResultSet res = select.executeQuery();
            while (res.next()) {
                int id = res.getInt("idFotografo");
                String nombre = res.getString("nombre");
                Boolean premiado = res.getBoolean("premiado");

                Fotografo f = new Fotografo(id,nombre,premiado);

                ALfotografos.add(f);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ALfotografos;
    }

    //Obtener fotografias de un fotografo
    public static ArrayList<String> buscarFotografias(int idFotografo,Date fecha) {
        ArrayList<String> ALreturn =new ArrayList<>();
        conexion conexion = new conexion();
        Connection conn=conexion.MyConexion();
        System.out.println(fecha);
        try(PreparedStatement select = conn.prepareStatement("SELECT * from fotos where IDfotografo = ? AND fecha < ?")) {
            select.setInt(1,idFotografo);
            select.setDate(2, (java.sql.Date) fecha);
            ResultSet res = select.executeQuery();
            while (res.next()) {
                String titulo = res.getString("titulo");
                ALreturn.add(titulo);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return ALreturn;
    }


    public static String mostrarFotografia(String nombreFoto,int idFotografo) {
        String img="";
        conexion conexion = new conexion();
        Connection conn = conexion.MyConexion();
        try (PreparedStatement select = conn.prepareStatement("SELECT fichero from fotos where titulo = ? AND IDfotografo = ?")){
            select.setNString(1,nombreFoto);
            select.setInt(2,idFotografo);
            ResultSet res = select.executeQuery();
            if (res.next()) {
                img = res.getString("fichero");
            }

            //img = "img/ansealdams1.jpg";
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("ERROR: MOSTRAR FOTOGRAFIA");
        }
        return img;
    }
}


