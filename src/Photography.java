import org.jdesktop.swingx.JXDatePicker;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Photography extends JFrame {
    public static void Photography() {
        JFrame jFrame = new JFrame("Photography");

        JPanel jpGeneral = new JPanel();
        jpGeneral.setLayout(new GridLayout(2,2));
        jFrame.add(jpGeneral);

        JPanel fotografos = new JPanel();
        JLabel jlfotografos = new JLabel("Photographer:");
        JComboBox jcfotografos = new JComboBox();

        //lo qe sea que haya que hacer para mostrar los fotogrados de la BD

        fotografos.setLayout(new GridLayout());

        fotografos.add(jlfotografos);
        fotografos.add(jcfotografos);
        jFrame.add(fotografos);



        JPanel fecha = new JPanel();
        JLabel jlfotos = new JLabel("Photos after:");
        JXDatePicker date = new JXDatePicker();
        date.setDate(new Date(11-5-2001)); //Para que no de error
        Date fechaSelec=  date.getDate();
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        String fechaSeleccionada = formatoFecha.format(fechaSelec);

        fecha.setLayout(new GridLayout());


        fecha.add(jlfotos);
        fecha.add(date);
        jFrame.add(fecha);




        jFrame.setPreferredSize(new Dimension(500,400));
        jFrame.pack();
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }


    public static void main(String[] args) {
        Photography();
    }
}


