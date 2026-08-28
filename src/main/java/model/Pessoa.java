package model;

import java.util.ArrayList;
import java.util.List;

public class Pessoa {
    private String nome;
    private String morada;
    private int idade;
    private Double saldo;

    public List<Servico> servicosComprados;

    public Pessoa(String novoNome, String novaMorada, int novaIdade, Double novoSaldo) {
        this.nome = novoNome;
        this.morada = novaMorada;
        this.idade = novaIdade;
        this.saldo = novoSaldo;
        this.servicosComprados = new ArrayList<>();
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNome() {
        return this.nome;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }
    public String getMorada() {
        return this.morada;
    }

    public void setIdade(String morada) {
        this.idade = idade;
    }
    public int getIdade() {
        return this.idade;
    }

    public  void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    public double getSaldo() {
        return this.saldo;
    }


    public void comprarServico(Double saldo, Servico servicoEscolhido) throws Exception {
        if (this.saldo < servicoEscolhido.getPreco() || !servicoEscolhido.getEstaAtivo() == true) {

            System.out.println("Erro: Saldo insuficiente ou serviço inativo.");
            throw  new Exception("Erro insuficiente ou Servico inativo");
        }

        this.saldo -= servicoEscolhido.getPreco();
        this.servicosComprados.add(servicoEscolhido);
        System.out.println("Compra efetuada com sucesso");
    }

    public void mostrarHistorico() {
        System.out.println("----------Lista de servicos comprados--------------");
        for(Servico s: this.servicosComprados){
            System.out.println("Servico: " + s.getTitulo());
        }
    }
}
