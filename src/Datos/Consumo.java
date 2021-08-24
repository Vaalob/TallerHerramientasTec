package Datos;

public class Consumo {
    
    private int idconsumo;
    private int idcita;
    private int idproducto;
    private int cantidad;
    private Double precio_consumo;

    public Consumo() {
    }

    public Consumo(int idconsumo, int idcita, int idproducto, int cantidad, Double precio_consumo) {
        this.idconsumo = idconsumo;
        this.idcita = idcita;
        this.idproducto = idproducto;
        this.cantidad = cantidad;
        this.precio_consumo = precio_consumo;
    }

    public int getIdconsumo() {
        return idconsumo;
    }

    public void setIdconsumo(int idconsumo) {
        this.idconsumo = idconsumo;
    }

    public int getIdcita() {
        return idcita;
    }

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    public int getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(int idproducto) {
        this.idproducto = idproducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecio_consumo() {
        return precio_consumo;
    }

    public void setPrecio_consumo(Double precio_consumo) {
        this.precio_consumo = precio_consumo;
    }
    
    
    
}
