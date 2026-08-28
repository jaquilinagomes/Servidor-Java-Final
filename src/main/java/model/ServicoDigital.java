package model;

public class ServicoDigital extends Servico {
    public String linkDownload;

    public ServicoDigital(String titulo, String descricao, double preco, boolean estaAtivo) {

        super (titulo, descricao, preco, estaAtivo);
        this.linkDownload = linkDownload;
    }
}
