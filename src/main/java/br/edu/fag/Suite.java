package br.edu.fag;

public class Suite extends TipoHospedagem {
    private static final double valorNoite = 600.00;

    public Suite(String nomeHospede, String quarto, int numeroDias) {
        super(nomeHospede, quarto, numeroDias);
        //inicializa com os atributos herdados da classe pai
    }

    @Override
    public double calcularValor() {
        return calcularValorEstadia(getNumeroDias());
    }

    private double calcularValorEstadia(int dias) {
        if (dias == 0) {
            return 0;
        }
        double valor = valorNoite;
        if (dias > 5) { //taxa adicional p/ estadias longas
            valor += 100;
        }
        return valor + calcularValorEstadia(dias - 1);
        //chamada recursiva q soma o valor do dia atual no valor total dos dias restantes.
    }
}