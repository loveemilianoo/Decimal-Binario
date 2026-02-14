
import java.util.*;

public class Numero{
    private String valorReal;
    private int base;

    private String error;
    private double valorAprox;
    public List <String> listaSuma = new ArrayList<>();
    public List <Iteracion> iteraciones = new ArrayList<>();

    public Numero (String valorReal, int base){
        this.valorReal = valorReal;
        this.base = base;
        this.valorAprox = 0.0;
        this.error = "0";
    }

    public void conversion (){
        listaSuma.clear();
        iteraciones.clear();

        double decimal = Double.parseDouble(valorReal);
        double valorRealDouble = decimal;
        decimal = decimal % 1;
        valorAprox = 0.0;

        for (int i = 0; i < 16; i++){
            double resultado = decimal * base;
            int entera = (int) resultado;
            String bit = String.valueOf(entera);

            listaSuma.add(bit);
            decimal = resultado - entera;

            double potencia = Math.pow(base, -(i+1));
            valorAprox += entera * potencia;

            double errorAbsoluto = Math.abs(valorRealDouble - valorAprox);
            this.error = String.valueOf(errorAbsoluto);
            double errorRelativo = (errorAbsoluto/valorRealDouble) * 100;
            
            int numIter = i+1;
            iteraciones.add(new Iteracion( numIter , bit, valorAprox, errorAbsoluto, errorRelativo));

            if (decimal == 0){
                break;
            }
        }
    }

    public String mostrarConversion (){
        String conversion = String.join( "", listaSuma);
        return conversion;
    }

    public void mostrarResultadoError(){
        if (Double.parseDouble(this.error) == 0){
            System.out.println("Es un numero exacto");
        } else {
            System.out.println("Es un numero aproximado. Error: " + this.error);
        }
    }

    public void mostrarTabla(){
        System.out.println("\n" + "=".repeat(100));
        System.out.println("TABLA DE ITERACIONES - Conversión a Base " + base);
        System.out.println("Valor Real: " + valorReal);
        System.out.println("=".repeat(100));
        System.out.printf("%-12s %-10s %-25s %-30s %-25s%n", 
                         "Iteración", "Dígito", "Valor Aproximado", 
                         "Error Absoluto", "Error Relativo (%)");
        System.out.println("-".repeat(100));
        
        for (Iteracion iter : iteraciones){
            System.out.printf("%-12d %-10s %-25.18f %-30.18e %-25.18f%n",
                             iter.getNumero(), 
                             iter.getBit(), 
                             iter.getValorAproxParcial(),
                             iter.getErrorAbsoluto(), 
                             iter.getErrorRelativo());
        }
        
        System.out.println("=".repeat(100));
        System.out.println("REPRESENTACION FINAL: " + mostrarConversion());
        System.out.println("VALOR APROXIMADO FINAL: " + valorAprox);
        mostrarResultadoError();
        System.out.println("=".repeat(100) + "\n");
    }


    public String getValorReal() {
        return valorReal;
    }
    public void setValorReal(String valorReal) {
        this.valorReal = valorReal;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public double getValorAprox() {
        return valorAprox;
    }
    public void setValorAprox(double valorAprox) {
        this.valorAprox = valorAprox;
    }
    public int getBase() {
        return base;
    }
    public void setBase(int base) {
        this.base = base;
    }
}