package model;

public class Servico {
    private String titulo;
    private String descricao;
    private Double preco;
    private Boolean estaAtivo;

    public Servico(String novoTitulo, String novoDiscricao, Double novoPreco, Boolean novoEstaAtivo) {
        this.titulo = novoTitulo;
        this.descricao = novoDiscricao;
        this.preco = novoPreco;
        this.estaAtivo = novoEstaAtivo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setDescricao( String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getPreco() {
        return this.preco;
    }

    public void setEstaAtivo (boolean estaAtivo) {
        this.estaAtivo = estaAtivo;
    }

    public boolean getEstaAtivo () {
        return this.estaAtivo;
    }

    public void aplicarDesconto(double percentagem) {
        double valorDesconto = (this.preco * percentagem) / 100;
        this.preco = this.preco - valorDesconto;

        System.out.println("------------- Desconto ---------------");
        System.out.println("Desconto Aplicado com sucesso");
        System.out.println("valor final: " + this.preco);
    }
    public void verificarDisponibilidade() {
        if (this.estaAtivo) {
            System.out.println("Servico " + this.titulo + " esta disponivel");
        } else {
            System.out.println("Servico " + this.titulo + " nao esta disponivel");
        }
    }
}
