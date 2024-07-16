package com.sandro.forum_hub.domain.topico;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private Boolean status;
    private String autor;
    private String curso;

    public Topico(DadosCadastroTopico dadosRegistroTopico) {
        this.titulo = dadosRegistroTopico.titulo();
        this.mensagem = dadosRegistroTopico.mensagem();
        this.autor = dadosRegistroTopico.autor();
        this.curso = dadosRegistroTopico.curso();
        this.dataCriacao = LocalDateTime.now();
        this.status = true;
    }

    public void desativarTopico() {
        this.status = false;
    }

    public void atualizarDados(DadosAtualizacaoTopico dadosAtualizarTopico) {
        if (dadosAtualizarTopico.titulo() != null) {
            this.titulo = dadosAtualizarTopico.titulo();
        }
        if (dadosAtualizarTopico.mensagem() != null) {
            this.mensagem = dadosAtualizarTopico.mensagem();
        }
        if (dadosAtualizarTopico.autor() != null) {
            this.autor = dadosAtualizarTopico.autor();
        }
        if (dadosAtualizarTopico.curso() != null) {
            this.curso = dadosAtualizarTopico.curso();
        }
        if (dadosAtualizarTopico.status() != null) {
            this.status = dadosAtualizarTopico.status();
        }
    }
}
