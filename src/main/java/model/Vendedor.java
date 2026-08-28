package model;

public class Vendedor extends Pessoa {

    Double taxaComissao;

    public Vendedor(String nome, String morada, int telefone, double saldo , double taxaComissao) {
        super(nome, morada, telefone, saldo);
        this.taxaComissao = taxaComissao;
    }
    public double getTaxaComissao() {
        return taxaComissao;
    }


}
