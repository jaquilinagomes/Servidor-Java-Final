package utils;
import model.Pessoa;
import model.Servico;
import model.ServicoDigital;
import model.Vendedor;

import java.util.List;

public class Formatador {
    public  void imprimirPessoa(Pessoa pessoa) {
        System.out.println("//------Dados de: " + pessoa.getNome() + "------");
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Morada: " + pessoa.getMorada());
        System.out.println("Idade: " + pessoa.getIdade());
        System.out.println("Saldo: " + pessoa.getSaldo());

        System.out.println("//---------------------------------------//");
    }

    public void imprimirServico(Servico servico) {
        System.out.println("//------Dados de: " + servico.getTitulo() + "------");
        System.out.println("Titulo : " + servico.getTitulo());
        System.out.println("Discricao: " + servico.getDescricao());
        System.out.println("Preco : " + servico.getPreco());
        System.out.println("Estado: " + servico.getEstaAtivo());

        System.out.println("//---------------------------------------//");
    }

    public void imprimirVendedor(Vendedor vendedor) {
        System.out.println("//------Dados de: " + vendedor.getNome() + "------");
        System.out.println("Nome: " + vendedor.getNome());
        System.out.println("Morada: " + vendedor.getMorada());
        System.out.println("Idade: " + vendedor.getIdade());
        System.out.println("Saldo: " + vendedor.getSaldo());

        System.out.println("//---------------------------------------//");
    }

    public void imprimirServicoDigital(ServicoDigital ServicoDigital) {
        System.out.println("//------Dados de: " + ServicoDigital.getTitulo() + "------");
        System.out.println("Titulo: " + ServicoDigital.getTitulo());
        System.out.println("Discricao: " + ServicoDigital.getDescricao());
        System.out.println("Preco: " + ServicoDigital.getPreco());
        System.out.println("Estado: " + ServicoDigital.getEstaAtivo());
        System.out.println("Link de Download: " + ServicoDigital.linkDownload);

        System.out.println("//---------------------------------------//");
    }
    public void imprimirListaDeServicoComprado (Pessoa pessoa) {
        List<Servico> ListaDeServicos = pessoa.servicosComprados;

        System.out.println("//---------Servicos comprados por: " + pessoa.getNome() + " ------//");

        if (!ListaDeServicos.isEmpty()) {
            for (Servico s: ListaDeServicos) {
                System.out.println("Servico: " + s.getTitulo());
            }
        }
        System.out.println("//-------------------------------------//");
    }
    public void imprirListaDeServicosAVenda () {

    }
}
