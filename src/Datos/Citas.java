package Datos;

import java.util.*;

public class Citas {
    
    private int idcita;
    private int idservicio;
    private int idcliente;
    private int idtrabajador;
    private String tipo_cita;
    private Date fecha_cita;
    private double costo_total;

    public Citas() {
    }

    public Citas(int idcita, int idservicio, int idcliente, int idtrabajador, String tipo_cita, Date fecha_cita, double costo_total) {
        this.idcita = idcita;
        this.idservicio = idservicio;
        this.idcliente = idcliente;
        this.idtrabajador = idtrabajador;
        this.tipo_cita = tipo_cita;
        this.fecha_cita = fecha_cita;
        this.costo_total = costo_total;
    }

    public int getIdcita() {
        return idcita;
    }

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    public int getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(int idservicio) {
        this.idservicio = idservicio;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdtrabajador() {
        return idtrabajador;
    }

    public void setIdtrabajador(int idtrabajador) {
        this.idtrabajador = idtrabajador;
    }

    public Date getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(Date fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public double getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(double costo_total) {
        this.costo_total = costo_total;
    }

    public String getTipo_cita() {
        return tipo_cita;
    }

    public void setTipo_cita(String tipo_cita) {
        this.tipo_cita = tipo_cita;
    }

    public void setFecha_cita(String d, String m, String a) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
