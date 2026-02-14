

public class Iteracion {
    private int numero;
    private String bit;
    private double valorAproxParcial;
    private double errorAbsoluto;
    private double errorRelativo;

    public Iteracion (int numero, String bit, double valorAproxParcial, double errorAbsoluto, double errorRelativo){
        this.numero = numero;
        this.bit = bit;
        this.valorAproxParcial = valorAproxParcial;
        this.errorAbsoluto = errorAbsoluto;
        this.errorRelativo = errorRelativo;
    }

    
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public String getBit() {
        return bit;
    }
    public void setBit(String bit) {
        this.bit = bit;
    }
    public double getValorAproxParcial() {
        return valorAproxParcial;
    }
    public void setValorAproxParcial(double valorAproxParcial) {
        this.valorAproxParcial = valorAproxParcial;
    }
    public double getErrorAbsoluto() {
        return errorAbsoluto;
    }
    public void setErrorAbsoluto(double errorAbsoluto) {
        this.errorAbsoluto = errorAbsoluto;
    }
    public double getErrorRelativo() {
        return errorRelativo;
    }
    public void setErrorRelativo(double errorRelativo) {
        this.errorRelativo = errorRelativo;
    }

}
