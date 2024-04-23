public class Fotografo {
    private int idFotografo;
    private String nombre;
    private boolean premiado;
    public Fotografo(int idFotografo, String nombre, boolean premiado) {
        this.idFotografo=idFotografo;
        this.nombre=nombre;
        this.premiado=premiado;
    }

    public int getIdFotografo() {
        return idFotografo;
    }
    public String getNombre() {
        return nombre;
    }
    public boolean getPremiado() {
        return premiado;
    }
}
