package usa.sesion15.dcjd.Modelo;

public class Entidad {

    //int imagen;
    String imagen;
    String titulo;
    String descripcion;

    public Entidad(String imagen, String titulo, String descripcion) {
        this.imagen = imagen;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
