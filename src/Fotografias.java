import java.util.Date;

public class Fotografias {

    private int idFoto;
    private String titulo;
    private Date fecha;
    private String archivo;
    private int visitas;
    private int idFotografo;
    public Fotografias(int idFoto, String titulo, Date fecha, String archivo, int visitas, int idFotografo) {
        this.idFoto=idFoto;
        this.titulo=titulo;
        this.fecha=fecha;
        this.archivo=archivo;
        this.visitas=visitas;
        this.idFotografo=idFotografo;
    }

    public String getArchivo() {
        return archivo;
    }

    public int getIdFoto() {
        return idFoto;
    }
}
