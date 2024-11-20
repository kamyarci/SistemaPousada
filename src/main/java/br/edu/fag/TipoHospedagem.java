package br.edu.fag;


//classe abstrata só serve p/ herança, n da pra instancia-la.
public abstract class TipoHospedagem extends Hospedagem {
    public TipoHospedagem(String nomeHospede, String quarto, int numeroDias) {
        super(nomeHospede, quarto, numeroDias);
        //invoca o construtor da classe pai e inicializa c/ os atributos herdados.

    }

    public abstract double calcularValor();
    //implementado nas classes suite e quartostandard p/ calcular o valor da hospedagem,
}