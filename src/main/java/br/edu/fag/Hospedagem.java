package br.edu.fag;

public class Hospedagem {
    private final String nomeHospede;
    private final String quarto;
    private int numeroDias;

    //construtor
    public Hospedagem(String nomeHospede, String quarto, int numeroDias) {
        this.nomeHospede = nomeHospede;
        this.quarto = quarto;
        setNumeroDias(numeroDias);
    }

    public String getNomeHospede() {
        return nomeHospede;
    }

    public String getQuarto() {
        return quarto;
    }

    public int getNumeroDias() {
        return numeroDias;
    }


    public void setNumeroDias(int numeroDias) {
        if (numeroDias <= 0) {  //valida o valor atribuido ao atributo numeroDias.
            throw new IllegalArgumentException("Número de dias deve ser maior que zero.");
        }
        this.numeroDias = numeroDias;
    }
}