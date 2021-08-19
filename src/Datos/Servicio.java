package Datos;

public class Servicio {
    
    private int idservicio;
    private String tipo_servicio;
    private String descripcion;
    private double precio_servicio;

    public Servicio(int idservicio, String tipo_servicio, String descripcion, double precio_servicio) {
        this.idservicio = idservicio;
        this.tipo_servicio = tipo_servicio;
        this.descripcion = descripcion;
        this.precio_servicio = precio_servicio;
    }

    public Servicio() {
    }

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio_servicio() {
        return precio_servicio;
    }

    public void setPrecio_servicio(double precio_servicio) {
        this.precio_servicio = precio_servicio;
    }
    
    
    
}
