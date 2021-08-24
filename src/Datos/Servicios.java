package Datos;

public class Servicios {
    
    private int idservicio;
    private String descripcion;
    private double precio;
    private String tipo_servicio;

    
    public Servicios(int idservicio, String descripcion, double precio, String tipo_servicio) {
        this.idservicio = idservicio;
        this.descripcion = descripcion;
        this.precio = precio;
        this.tipo_servicio = tipo_servicio;
    }
    
    public Servicios() {
    }

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }
 
    
}
