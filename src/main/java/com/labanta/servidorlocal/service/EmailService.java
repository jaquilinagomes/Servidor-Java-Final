package com.labanta.servidorlocal.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
    public void enviarEmailBoasVindas(String emailDestino, String nomeUtilizador) {

        //Criar um email simples (texto limpo)
        SimpleMailMessage mensagem = new SimpleMailMessage();

        mensagem.setTo(emailDestino);
        mensagem.setSubject("Bem-vindo ao Marketplace");
        mensagem.setText("Ola " + nomeUtilizador + "!\n\n" + "Ja podes fazer login e explorar os nossos servicos.\n\n" + "Com os melhores comprimentos,\n Equipa do Markerplace");

        //Enviar
        mailSender.send(mensagem);
    }

    //Aula 19
    public void enviarOrcamentoPorEmail(String emailDestino, String nomeServico, Double precoConvertido, String moeda) {

        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(emailDestino);
        mensagem.setSubject("O teu Orçamento do servico no Marketplace");

        // Criar o texto do corpo do email
        String corpo = String.format(
                "Olá!\n\nAqui tens o orçamento solicitado para o serviço:\n\n" +
                        "Serviço: %s\n" +
                        "Preço Final: %.2f %s\n\n" +
                        "Este valor foi calculado com a taxa de câmbio em tempo real.\n" +
                        "Obrigado por usares o nosso Marketplace!",
                nomeServico, precoConvertido, moeda
        );

        mensagem.setText(corpo);
        mailSender.send(mensagem);
    }
        //exercicio 16
    public void enviarAlertaSeguranca(String emailDestino, String cidade, String pais) {

        SimpleMailMessage mensagem = new SimpleMailMessage();

        mensagem.setTo(emailDestino);
        mensagem.setSubject("Alerta de Segurança - Marketplace");

        mensagem.setText(
                "Aviso de Segurança:\n\n" +
                        "Detetámos uma nova atividade na tua conta do Marketplace " +
                        "a partir de " + cidade + ", " + pais + ".\n\n" +
                        "Se não foste tu, altera a tua password imediatamente!");

        mailSender.send(mensagem);
    }


}
