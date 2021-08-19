package Datos;

public class Cliente extends Persona{
    
    private String codigo_cliente;

    public Cliente() {
    }

    public Cliente(String codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public String getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(String codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }
    
    
    
    
}
