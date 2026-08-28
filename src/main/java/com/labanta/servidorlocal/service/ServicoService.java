package com.labanta.servidorlocal.service;

import com.labanta.servidorlocal.exception.ServicoNaoEncontradoException;
import com.labanta.servidorlocal.models.ServicoModel;
import com.labanta.servidorlocal.repository.ServicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {
    private final ServicoRepository repositorio;

    public ServicoService(ServicoRepository repositorio) {
        this.repositorio = repositorio;

    }

    public Page<ServicoModel> listarServico(Pageable pageable) {
        return repositorio.findAll(pageable);
    }

    public List<ServicoModel> aplicarDescontoEmAtivos(Double percentagem) {
            //Exercicio 11
        if (percentagem < 0 || percentagem > 100) {
            throw new IllegalArgumentException("Desconto inválido.");
        }

        List<ServicoModel> lista = repositorio.findByEstaAtivoTrue();

        for (ServicoModel servico : lista) {


            Double desconto = servico.getPreco() * (percentagem / 100);


            Double novoPreco = servico.getPreco() - desconto;

            servico.setPrecoComDesconto(novoPreco);
        }

        repositorio.saveAll(lista);


        return lista;
    }

    //Exercicio 11
    public ServicoModel buscarServicoPorId(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() ->
                        new ServicoNaoEncontradoException("O serviço com o ID " + id + " não existe no catálogo."));
    }
    public List<ServicoModel> pesquisarServicos(String termo) {
        return repositorio.findByTituloContainingIgnoreCase(termo);
    }

    public void criarServico(ServicoModel servico) {
    }
}



