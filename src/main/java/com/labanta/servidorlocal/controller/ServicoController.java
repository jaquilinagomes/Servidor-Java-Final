package com.labanta.servidorlocal.controller;

import com.labanta.servidorlocal.models.ServicoModel;
import com.labanta.servidorlocal.repository.ServicoRepository;
import com.labanta.servidorlocal.service.EmailService;
import com.labanta.servidorlocal.service.ExchangeService;
import com.labanta.servidorlocal.service.FileStorageService;
import com.labanta.servidorlocal.service.ServicoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import model.Servico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/api/v1/servicos")
public class ServicoController {
    private final ServicoRepository repository;
    private final ServicoService servicoService;
    private final ExchangeService exchangeService;
    private final EmailService emailService;
    private final FileStorageService fileStorageService;

    public ServicoController(ServicoRepository repository, ExchangeService exchangeService, EmailService emailService, FileStorageService fileStorageService) {
        this.repository = repository;
        this.servicoService = new ServicoService(repository);
        this.exchangeService = exchangeService;
        this.emailService = emailService;
        this.fileStorageService = fileStorageService;
    }

    @Operation(
            summary = "Listar todos os serviços",
            description = "Rota para Listar todos os serviços existentes na plataforma"
    )
    /*
    @GetMapping("/teste")

        public String dizerOla() {
            return "Ola mundo! O meu servidor spring esta a funcionar";

        }

        @GetMapping
        public List<ServicoModel> listarServicos() {


            return repository.findAll();
        }
    */
        @GetMapping
        public Page<ServicoModel> listarServico(@PageableDefault(page = 0, size = 5, sort = "id", direction = Sort.Direction.DESC)
                                                Pageable pageable) {
            return servicoService.listarServico(pageable);
        }

        @Operation(
                summary = "Criar um novo serviço",
                description = "Rota para criar um novo serviço"
        )

        @SecurityRequirement(name = "BearerAuth")
        @PostMapping()
        public ServicoModel criarServico(@RequestBody ServicoModel novoServico) {
            return repository.save(novoServico);
        }

        //Exercicio 11

        @Operation(
                summary = "Buscar um serviço pelo ID",
                description = "Rota para buscar um serviço pelo ID"
        )
        @GetMapping("/{id}")
        public ServicoModel buscarServicoPorId(@PathVariable Long id) {
            return servicoService.buscarServicoPorId(id);
        }


    //exercicio 13
        @Operation(
                summary = "Pesquisar todos os serviços",
                description = "Rota para pesquisar todos os serviços"
        )
        @SecurityRequirement(name = "BearerAuth")
        @GetMapping("/pesquisa")
        public List<ServicoModel> pesquisarServicos(
            @RequestParam String termo) {

        return servicoService.pesquisarServicos(termo);
        }

        @Operation(
                summary = "Carregar capa do serviço",
                description = "Rota para carregar capas de serviço com base no ID"
        )
        @SecurityRequirement(name = "BearerAuth")
        @PostMapping(value = "/{id}/upload-capa", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity<String> uploadFile(
                @RequestParam("file") MultipartFile file,
                @PathVariable Long id
        ) {
            ServicoModel servico = servicoService.buscarServicoPorId(id);


            String fileUploaded = fileStorageService.storeImage(file);

            servico.setImagemCapa(fileUploaded);
            servicoService.criarServico(servico);
            return ResponseEntity.ok("Ficheiro carregado com sucesso:" + fileUploaded);
        }


    //aula 19
        @Operation(
               summary = "Pedido de orçamento por ID",
                description = "Rota de pedido de orçamento por ID"
        )
        @SecurityRequirement(name = "BearerAuth")
        @PostMapping("/{id}/orcamento")
        public String pedirOrcamento(@PathVariable Long id, @RequestParam String emailDestino, @RequestParam(defaultValue = "CVE") String moeda) {
            // 1. Ir à Base de Dados buscar o Serviço
            ServicoModel servico = servicoService.buscarServicoPorId(id);

            // 2. Ir à Internet converter o preço (Aula 16)
            Double precoConvertido = exchangeService.converterPreco(servico.getPreco(), moeda);

            // 3. Enviar o resultado para o Gmail do cliente (Aula 15)
            emailService.enviarOrcamentoPorEmail(emailDestino, servico.getTitulo(), precoConvertido, moeda);

            return "Orçamento calculado e enviado com sucesso para " + emailDestino + "!";
        }

}
